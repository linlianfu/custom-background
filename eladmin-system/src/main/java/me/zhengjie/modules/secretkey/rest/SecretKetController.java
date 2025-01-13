package me.zhengjie.modules.secretkey.rest;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.modules.secretkey.domain.vo.SecretKeyCriteria;
import me.zhengjie.modules.secretkey.domain.vo.SecretKeyVo;
import me.zhengjie.modules.secretkey.service.SecretKeyService;
import me.zhengjie.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author eleven
 */
@Slf4j
@RestController
@Api(tags = "密钥管理：密钥管理")
@RequestMapping("/api/secretKey")
public class SecretKetController {

    @Autowired
    private SecretKeyService service;


    @ApiOperation("密钥分页")
    @GetMapping(value = "/page")
    public ResponseEntity<PageResult<SecretKeyVo>> queryTheme(SecretKeyCriteria criteria, Page<Object> page){
        return new ResponseEntity<>(service.queryAll(criteria, page), HttpStatus.OK);
    }
}
