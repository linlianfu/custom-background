package me.zhengjie.modules.secretkey.rest;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.annotation.Log;
import me.zhengjie.modules.secretkey.domain.vo.SecretKeyCriteria;
import me.zhengjie.modules.secretkey.domain.vo.SecretKeyRequest;
import me.zhengjie.modules.secretkey.domain.vo.SecretKeyVo;
import me.zhengjie.modules.secretkey.service.SecretKeyService;
import me.zhengjie.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    /**
     * 创建密钥
     * @param request
     * @return
     */
    @PostMapping
    @Log("新增SecretKey")
    @ApiOperation("新增SecretKey")
    public ResponseEntity<Object> createSecretKey(@Validated @RequestBody SecretKeyRequest request){
        service.createSecretKey(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * 更新密钥
     * @param request
     * @return
     */
    @PutMapping
    @Log("更新密钥")
    @ApiOperation("更新密钥")
    public ResponseEntity<Object> updateSecretKey(@Validated @RequestBody SecretKeyRequest request){
        service.updateSecretKey(request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除SecretKey")
    @ApiOperation("删除SecretKey")
    public ResponseEntity<Object> deleteSecretKey(@RequestBody List<String> ids) {
        service.deleteById(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/updateStatus")
    @Log("更新状态")
    @ApiOperation("更新状态SecretKey")
    public ResponseEntity<Object> updateStatus(String id) {
        service.updateStatus(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
