package me.zhengjie.modules.secretkey.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.secretkey.domain.SecretKey;
import me.zhengjie.modules.secretkey.domain.SecurityObject;
import me.zhengjie.modules.secretkey.domain.SecurityObjectType;
import me.zhengjie.modules.secretkey.domain.vo.SecretKeyCriteria;
import me.zhengjie.modules.secretkey.domain.vo.SecretKeyRequest;
import me.zhengjie.modules.secretkey.domain.vo.SecretKeyVo;
import me.zhengjie.modules.secretkey.mapper.SecretKeyMapper;
import me.zhengjie.modules.secretkey.mapper.SecurityObjectMapper;
import me.zhengjie.modules.secretkey.service.SecretKeyService;
import me.zhengjie.modules.secretkey.service.dto.SecretKeyDto;
import me.zhengjie.modules.website.service.ImageParseAuthService;
import me.zhengjie.modules.website.service.dto.ImageParseVo;
import me.zhengjie.utils.ModelMapperUtils;
import me.zhengjie.utils.PageResult;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.StringUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author eleven
 */
@Service
@RequiredArgsConstructor
public class SecretKeyServiceImpl extends ServiceImpl<SecretKeyMapper, SecretKey> implements SecretKeyService {
    @Autowired
    private SecretKeyMapper secretKeyMapper;

    @Autowired
    private SecurityObjectMapper securityObjectMapper;
    @Autowired
    private ImageParseAuthService imageParseAuthService;



    @Override
    public SecretKeyDto getToken(String token) {
        Assert.hasText(token,"密钥不能为空");

        SecretKey key = secretKeyMapper.selectOne(Wrappers.lambdaQuery(SecretKey.class)
                .eq(SecretKey::getSecretKey, token)
                .eq(SecretKey::isEnable,true)
        );
        if (key == null)
            return null;

        SecretKeyDto dto = new SecretKeyDto();
        dto.setId(key.getId());
        dto.setName(key.getName());
        dto.setSecretKey(key.getSecretKey());
        dto.setIdentityType(key.getIdentityType());
        dto.setDeviceNumber(key.getDeviceNumber());
        dto.setEnable(key.isEnable());
        dto.setExpirationDate(key.getExpirationDate());
        dto.setWebType(getWebTypeByToken(token));
        return dto;
    }

    @Override
    public SecretKeyDto getSecretKey(String secretKey, String deviceNumber) {
        Assert.hasText(secretKey,"密钥不能为空");
        Assert.hasText(deviceNumber,"设备号不能为空");

        SecretKey key = secretKeyMapper.selectOne(Wrappers.lambdaQuery(SecretKey.class)
                .eq(SecretKey::getSecretKey, secretKey)
                .eq(SecretKey::getDeviceNumber, deviceNumber)
                .eq(SecretKey::isEnable,true)
        );
        if (key == null)
            return null;

        SecretKeyDto dto = new SecretKeyDto();
        dto.setId(key.getId());
        dto.setName(key.getName());
        dto.setSecretKey(key.getSecretKey());
        dto.setDeviceNumber(key.getDeviceNumber());
        dto.setEnable(key.isEnable());
        dto.setExpirationDate(key.getExpirationDate());
        return dto;
    }


    @Override
    public boolean bindToken(String token, String deviceNumber) {
        Assert.hasText(deviceNumber,"设备码不能为空");
        Assert.hasText(token,"token不能为空");
        SecretKey tokenResult = secretKeyMapper.selectOne(Wrappers.lambdaQuery(SecretKey.class)
                .eq(SecretKey::getSecretKey, token)
        );
        Assert.notNull(tokenResult,"无效token");
        Assert.isTrue(StringUtils.isBlank(tokenResult.getDeviceNumber()),"token已经被占用");
        tokenResult.setDeviceNumber(deviceNumber);
        secretKeyMapper.updateById(tokenResult);
        return true;
    }

    @Override
    public PageResult<SecretKeyVo> queryAll(SecretKeyCriteria criteria, Page<Object> page) {
        Page<SecretKey> dbPage = new Page<>(page.getCurrent(), page.getSize());
        LambdaQueryWrapper<SecretKey> wrapper = Wrappers.lambdaQuery(SecretKey.class);
        if (StringUtils.isNotBlank(criteria.getName())) {
            wrapper.like(SecretKey::getName, criteria.getName());
        }
        if (StringUtils.isNotBlank(criteria.getSecretKey())) {
            wrapper.like(SecretKey::getSecretKey, criteria.getSecretKey());
        }
        if (StringUtils.isNotBlank(criteria.getDeviceNumber())) {
            wrapper.like(SecretKey::getDeviceNumber, criteria.getDeviceNumber());
        }
        dbPage = secretKeyMapper.selectPage(
                dbPage, wrapper.orderByDesc(SecretKey::getCreateTime)
        );

        List<SecretKeyVo> list = ModelMapperUtils.mapList(dbPage.getRecords(), SecretKeyVo.class);
        list.forEach(p->{
            p.setWebType(getWebTypeByToken(p.getSecretKey()));
            List<ImageParseVo> authImageParse = imageParseAuthService.findAuthImageParse(p.getId());
            p.setAuthImageParse(authImageParse);
        });

        return PageUtil.toPage(list, dbPage.getTotal());
    }

    @Override
    public boolean createSecretKey(SecretKeyRequest request) {
        Assert.hasText(request.getName(),"名称不能为空");
        Assert.hasText(request.getSecretKey(),"Token不能为空");
        Long count = secretKeyMapper.selectCount(Wrappers.lambdaQuery(SecretKey.class)
                .eq(SecretKey::getSecretKey, request.getSecretKey()));
        Assert.isTrue(count<=0,"Token重复");
        SecretKey record = new SecretKey();
        record.setName(request.getName());
        record.setSecretKey(request.getSecretKey());
        record.setIdentityType(request.getIdentityType());
        record.setDeviceNumber(request.getDeviceNumber());
        record.setEnable(true);
        record.setCreateTime(new Date());
        save(record);

        if (CollectionUtils.isNotEmpty(request.getWebType())){
            request.getWebType().forEach(p->createObject(record.getSecretKey(),SecurityObjectType.WEB,p));
        }

        imageParseAuthService.authImageParse(record.getId(),request.getAuthImageParseId());
        return true;
    }

    @Override
    public boolean updateSecretKey(SecretKeyRequest request) {
        Assert.hasText(request.getName(),"名称不能为空");
        Assert.hasText(request.getSecretKey(),"Token不能为空");
        SecretKey secretKey = secretKeyMapper.selectById(request.getId());

        Long count = secretKeyMapper.selectCount(Wrappers.lambdaQuery(SecretKey.class)
                .eq(SecretKey::getSecretKey, request.getSecretKey())
                .ne(SecretKey::getId, request.getId()));
        Assert.isTrue(count<=0,"Token重复");
        secretKey.setName(request.getName());
        secretKey.setSecretKey(request.getSecretKey());
        secretKey.setIdentityType(request.getIdentityType());
        updateById(secretKey);

        securityObjectMapper.delete(Wrappers.lambdaQuery(SecurityObject.class)
                .eq(SecurityObject::getToken,secretKey.getSecretKey())
                .eq(SecurityObject::getType, SecurityObjectType.WEB));

        if (CollectionUtils.isNotEmpty(request.getWebType())){
            request.getWebType().forEach(p->createObject(secretKey.getSecretKey(),SecurityObjectType.WEB,p));
        }
        imageParseAuthService.cancelAuthImageParseByTokenId(request.getId());
        imageParseAuthService.authImageParse(request.getId(),request.getAuthImageParseId());
        return true;
    }

    @Override
    public boolean deleteById(List<String> idList) {
        for (String id : idList) {
            SecretKey secretKey = secretKeyMapper.selectById(id);
            securityObjectMapper.delete(Wrappers.lambdaQuery(SecurityObject.class)
                    .eq(SecurityObject::getToken,secretKey.getSecretKey())
                    .eq(SecurityObject::getType, SecurityObjectType.WEB));
            secretKeyMapper.deleteById(id);
            imageParseAuthService.cancelAuthImageParseByTokenId(id);
        }
        return true;
    }

    @Override
    public void updateStatus(String id) {
        SecretKey secretKey = secretKeyMapper.selectById(id);
        if (secretKey.isEnable()){
            secretKey.setEnable(false);
        }else {
            secretKey.setEnable(true);
        }
        updateById(secretKey);
    }

    @Override
    public List<String> getWebTypeByToken(String token) {
        List<String> webList = new ArrayList<>();
        LambdaQueryWrapper<SecurityObject> wrapper = Wrappers.lambdaQuery(SecurityObject.class)
                .eq(SecurityObject::getToken,token)
                .eq(SecurityObject::getType, SecurityObjectType.WEB);
        List<SecurityObject> list = securityObjectMapper.selectList(wrapper);

        if (CollectionUtils.isEmpty(list))
            return webList;
        return list.stream().map(SecurityObject::getContent).collect(Collectors.toList());
    }


    private void createObject(String token,int type,String content){
        SecurityObject object = new SecurityObject();
        object.setToken(token);
        object.setType(type);
        object.setContent(content);
        object.setCreateTime(new Date());
        securityObjectMapper.insert(object);
    }
}
