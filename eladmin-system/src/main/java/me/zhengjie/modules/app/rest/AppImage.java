package me.zhengjie.modules.app.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.annotation.rest.AnonymousPostMapping;
import me.zhengjie.modules.app.service.dto.SearchDataBody;
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


    @ApiOperation("提交图片数据")
    @AnonymousPostMapping(value = "/downloadImage")
    public boolean downloadImage(@RequestBody SearchDataBody searchDataBody){
        log.info("提交图片下载:"+searchDataBody.toString());
        return true;
    }
}
