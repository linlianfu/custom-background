package me.zhengjie.modules.app.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.annotation.rest.AnonymousPostMapping;
import me.zhengjie.modules.app.service.IAppImageService;
import me.zhengjie.modules.app.service.dto.DownloadResult;
import me.zhengjie.modules.app.service.dto.SearchDataBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author eleven
 */
@Slf4j
@RestController
@RequestMapping("/appImage")
@RequiredArgsConstructor
@Api(tags = "APP : 移动APP管理")
public class AppImage {

    @Autowired
    private IAppImageService service;

    @ApiOperation("提交图片数据")
    @AnonymousPostMapping(value = "/downloadImage")
    public DownloadResult downloadImage(@RequestBody SearchDataBody searchDataBody){
        log.info("提交图片下载:"+searchDataBody.toString());
        return service.downloadImage(searchDataBody);
    }
}
