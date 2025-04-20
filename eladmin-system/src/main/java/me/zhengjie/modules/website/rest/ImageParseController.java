package me.zhengjie.modules.website.rest;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.annotation.Log;
import me.zhengjie.modules.website.domain.vo.ImageParseCriteria;
import me.zhengjie.modules.website.domain.vo.ImageParseRequest;
import me.zhengjie.modules.website.service.ImageParseService;
import me.zhengjie.modules.website.service.dto.ImageParseVo;
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
@Api(tags = "图片解析地址管理")
@RequestMapping("/api/imageParse")
public class ImageParseController {

    @Autowired
    private ImageParseService service;


    @ApiOperation("图片解析地址分页")
    @GetMapping(value = "/page")
    public ResponseEntity<PageResult<ImageParseVo>> queryImageParse(ImageParseCriteria criteria, Page<Object> page){
        return new ResponseEntity<>(service.queryAll(criteria, page), HttpStatus.OK);
    }

    @ApiOperation("查询图片解析地址")
    @GetMapping(value = "/listImageParse")
    public ResponseEntity<List<ImageParseVo>> listImageParse(ImageParseCriteria criteria){
        return new ResponseEntity<>(service.listImageParse(criteria), HttpStatus.OK);
    }

    @PostMapping
    @Log("新增ImageParse")
    @ApiOperation("新增ImageParse")
    public ResponseEntity<Object> createImageParse(@Validated @RequestBody ImageParseRequest request){
        service.createImageParse(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @Log("新增ImageParse")
    @ApiOperation("新增ImageParse")
    public ResponseEntity<Object> updateImageParse(@Validated @RequestBody ImageParseRequest request){
        service.updateImageParse(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping
    @Log("删除ImageParse")
    @ApiOperation("删除ImageParse")
    public ResponseEntity<Object> deleteImageParse(@RequestBody List<String> ids) {
        service.deleteById(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
