package me.zhengjie.modules.website.rest;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.modules.website.domain.vo.ImageDataInfoCriteria;
import me.zhengjie.modules.website.service.IImageDownloadManagerService;
import me.zhengjie.modules.website.service.dto.ImageDataInfoDto;
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
@Api(tags = "图片下载管理")
@RequestMapping("/api/imageDownloadManager")
public class ImageDownloadManagerController {

    @Autowired
    private IImageDownloadManagerService service;



    @ApiOperation("图片分页")
    @GetMapping(value = "/page")
    public ResponseEntity<PageResult<ImageDataInfoDto>> queryTheme(ImageDataInfoCriteria criteria, Page<Object> page){
        return new ResponseEntity<>(service.queryAll(criteria, page), HttpStatus.OK);
    }
}
