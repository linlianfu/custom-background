/*
 *  Copyright 2019-2020 Zheng Jie
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package me.zhengjie.modules.security.rest;

import cn.hutool.core.util.IdUtil;
import com.wf.captcha.base.Captcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.annotation.Log;
import me.zhengjie.annotation.rest.AnonymousDeleteMapping;
import me.zhengjie.annotation.rest.AnonymousGetMapping;
import me.zhengjie.annotation.rest.AnonymousPostMapping;
import me.zhengjie.config.RsaProperties;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.secretkey.service.SecretKeyService;
import me.zhengjie.modules.secretkey.service.dto.SecretKeyDto;
import me.zhengjie.modules.security.config.bean.LoginCodeEnum;
import me.zhengjie.modules.security.config.bean.LoginProperties;
import me.zhengjie.modules.security.config.bean.LoginRequest;
import me.zhengjie.modules.security.config.bean.LoginResult;
import me.zhengjie.modules.security.config.bean.SecretResult;
import me.zhengjie.modules.security.config.bean.SecurityProperties;
import me.zhengjie.modules.security.security.TokenProvider;
import me.zhengjie.modules.security.service.OnlineUserService;
import me.zhengjie.modules.security.service.dto.AuthUserDto;
import me.zhengjie.modules.security.service.dto.JwtUserDto;
import me.zhengjie.modules.website.domain.vo.WebsiteCriteria;
import me.zhengjie.modules.website.service.ImageParseAuthService;
import me.zhengjie.modules.website.service.WebsiteService;
import me.zhengjie.modules.website.service.dto.ImageParseBaseVo;
import me.zhengjie.modules.website.service.dto.ImageParseVo;
import me.zhengjie.modules.website.service.dto.WebsiteAndImageParseVo;
import me.zhengjie.modules.website.service.dto.WebsiteVo;
import me.zhengjie.utils.ModelMapperUtils;
import me.zhengjie.utils.RedisUtils;
import me.zhengjie.utils.RsaUtils;
import me.zhengjie.utils.SecurityUtils;
import me.zhengjie.utils.StringUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author Zheng Jie
 * @date 2018-11-23
 * 授权、根据token获取用户详细信息
 *
 *
 * 热部署：
 * 第一步：file -setting- compiler - build project automatically
 *  第二步：在项目中按：CTRL + ALT + SHIFT + / 找到Registry点击
 *
 */
@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Api(tags = "系统：系统授权接口")
public class AuthorizationController {
    private final SecurityProperties properties;
    private final RedisUtils redisUtils;
    private final OnlineUserService onlineUserService;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    @Autowired
    private SecretKeyService secretKeyService;
    @Resource
    private LoginProperties loginProperties;
    @Autowired
    private WebsiteService websiteService;

    @Autowired
    private ImageParseAuthService imageParseAuthService;

    @Log("用户登录")
    @ApiOperation("登录授权")
    @AnonymousPostMapping(value = "/login")
    public ResponseEntity<Object> login(@Validated @RequestBody AuthUserDto authUser, HttpServletRequest request) throws Exception {
        // 密码解密
        String password = RsaUtils.decryptByPrivateKey(RsaProperties.privateKey, authUser.getPassword());
        // 查询验证码
        String code = (String) redisUtils.get(authUser.getUuid());
        // 清除验证码
        redisUtils.del(authUser.getUuid());
        if (StringUtils.isBlank(code)) {
            throw new BadRequestException("验证码不存在或已过期");
        }
        if (StringUtils.isBlank(authUser.getCode()) || !authUser.getCode().equalsIgnoreCase(code)) {
            throw new BadRequestException("验证码错误");
        }
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(authUser.getUsername(), password);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 生成令牌与第三方系统获取令牌方式
        // UserDetails userDetails = userDetailsService.loadUserByUsername(userInfo.getUsername());
        // Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        // SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenProvider.createToken(authentication);
        final JwtUserDto jwtUserDto = (JwtUserDto) authentication.getPrincipal();
        // 返回 token 与 用户信息
        Map<String, Object> authInfo = new HashMap<String, Object>(2) {{
            put("token", properties.getTokenStartWith() + token);
            put("user", jwtUserDto);
        }};
        if (loginProperties.isSingleLogin()) {
            // 踢掉之前已经登录的token
            onlineUserService.kickOutForUsername(authUser.getUsername());
        }
        // 保存在线信息
        onlineUserService.save(jwtUserDto, token, request);
        // 返回登录信息
        return ResponseEntity.ok(authInfo);
    }

    @ApiOperation("获取用户信息")
    @GetMapping(value = "/info")
    public ResponseEntity<UserDetails> getUserInfo() {
        return ResponseEntity.ok(SecurityUtils.getCurrentUser());
    }

    @ApiOperation("获取验证码")
    @AnonymousGetMapping(value = "/code")
    public ResponseEntity<Object> getCode() {
        // 获取运算的结果
        log.info("============开始请求获取验证码=====================");
        Captcha captcha = loginProperties.getCaptcha();
        String uuid = properties.getCodeKey() + IdUtil.simpleUUID();
        //当验证码类型为 arithmetic时且长度 >= 2 时，captcha.text()的结果有几率为浮点型
        String captchaValue = captcha.text();
        if (captcha.getCharType() - 1 == LoginCodeEnum.ARITHMETIC.ordinal() && captchaValue.contains(".")) {
            captchaValue = captchaValue.split("\\.")[0];
        }
        // 保存
        redisUtils.set(uuid, captchaValue, loginProperties.getLoginCode().getExpiration(), TimeUnit.MINUTES);
        // 验证码信息
        Map<String, Object> imgResult = new HashMap<String, Object>(2) {{
            put("img", captcha.toBase64());
            put("uuid", uuid);
        }};
        return ResponseEntity.ok(imgResult);
    }

    @ApiOperation("退出登录")
    @AnonymousDeleteMapping(value = "/logout")
    public ResponseEntity<Object> logout(HttpServletRequest request) {
        String token = tokenProvider.getToken(request);
        onlineUserService.logout(token);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation("爬虫token验证")
    @AnonymousGetMapping(value = "/secretKey")
    public SecretResult verifySecretKey(String secretKey, String deviceNumber){
        log.info("secretKey:"+secretKey+",deviceNumber:"+deviceNumber);

        SecretResult  result = new SecretResult();

        SecretKeyDto secret = secretKeyService.getToken(secretKey);
        if (secret == null)
            return result;
        String tokenDeviceNumber = secret.getDeviceNumber();
        if (StringUtils.isNotBlank(tokenDeviceNumber)){
            if (deviceNumber.equals(tokenDeviceNumber)){
                result.setValid(true);
            }else {
                log.warn("token错误，与绑定的设备不一致");
                return result;
            }
        }else {
            secretKeyService.bindToken(secretKey,deviceNumber);
        }
        result.setValid(true);
        result.setIdentityType(secret.getIdentityType());
        result.setWebType(secret.getWebType());
        return result;
    }

    /**
     * 新版本登录接口
     * 必须使用Post，否则很容易拿到返回数据
     * @param request
     * @return
     */
    @ApiOperation("新版爬虫token登录")
    @AnonymousPostMapping(value = "/tokenLogin")
    public LoginResult login(@Validated @RequestBody LoginRequest request){

        String token = request.getToken();

        String deviceNumber = request.getDeviceNumber();
        log.info("token:"+token+",deviceNumber:"+deviceNumber);

        LoginResult  result = new LoginResult();

        SecretKeyDto secret = secretKeyService.getToken(token);
        if (secret == null)
            return result;
        String tokenDeviceNumber = secret.getDeviceNumber();
        if (StringUtils.isNotBlank(tokenDeviceNumber)){
            if (deviceNumber.equals(tokenDeviceNumber)){
                result.setSuccess(true);
            }else {
                log.warn("token错误，与绑定的设备不一致");
                return result;
            }
        }else {
            secretKeyService.bindToken(token,deviceNumber);
        }
        result.setSuccess(true);
        result.setIdentityType(secret.getIdentityType());
        if (CollectionUtils.isNotEmpty(secret.getWebType())){
            WebsiteCriteria criteria = new WebsiteCriteria();
            criteria.setCodeList(secret.getWebType());
            List<WebsiteVo> websiteVoList = websiteService.listWebsite(criteria);
            List<WebsiteAndImageParseVo> website = ModelMapperUtils.mapList(websiteVoList,WebsiteAndImageParseVo.class);

            List<ImageParseVo> authImageParse = imageParseAuthService.findAuthImageParse(secret.getId());
            if (CollectionUtils.isNotEmpty(authImageParse)){
                for (WebsiteAndImageParseVo websiteAndImageParseVo : website) {
                    List<ImageParseVo> collect = authImageParse.stream().filter(p -> p.getWebsiteId().equals(websiteAndImageParseVo.getId()))
                            .collect(Collectors.toList());
                    List<ImageParseBaseVo> imageParses = ModelMapperUtils.mapList(collect,ImageParseBaseVo.class);
                    websiteAndImageParseVo.setImageParses(imageParses);
                }
            }
            result.setWebsite(website);
        }
        return result;
    }
}
