package me.zhengjie.modules.website.rest;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.annotation.Log;
import me.zhengjie.modules.website.domain.vo.WebsiteCriteria;
import me.zhengjie.modules.website.domain.vo.WebsiteRequest;
import me.zhengjie.modules.website.service.IWebsiteService;
import me.zhengjie.modules.website.service.dto.WebsiteVo;
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
@Api(tags = "网站管理")
@RequestMapping("/api/website")
public class WebsiteController {

    @Autowired
    private IWebsiteService service;


    @ApiOperation("网站分页")
    @GetMapping(value = "/page")
    public ResponseEntity<PageResult<WebsiteVo>> queryTheme(WebsiteCriteria criteria, Page<Object> page){
        return new ResponseEntity<>(service.queryAll(criteria, page), HttpStatus.OK);
    }

    /**
     * 创建密钥
     * @param request
     * @return
     */
    @PostMapping
    @Log("新增Website")
    @ApiOperation("新增Website")
    public ResponseEntity<Object> createWebsite(@Validated @RequestBody WebsiteRequest request){
        service.createWebsite(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @Log("更新Website")
    @ApiOperation("更新Website")
    public ResponseEntity<Object> updateWebsite(@Validated @RequestBody WebsiteRequest request){
        service.updateWebsite(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    /**
     * 网站查询
     *
     * @param criteria 条件
     * @return /
     */
    @ApiOperation("网站分页")
    @GetMapping( "/listWebsite")
    public ResponseEntity<List<WebsiteVo>> listWebsite(WebsiteCriteria criteria){
        return new ResponseEntity<>(service.listWebsite(criteria), HttpStatus.OK);
    }

    @DeleteMapping
    @Log("删除Website")
    @ApiOperation("删除Website")
    public ResponseEntity<Object> deleteWebsite(@RequestBody List<String> ids) {
        service.deleteById(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
