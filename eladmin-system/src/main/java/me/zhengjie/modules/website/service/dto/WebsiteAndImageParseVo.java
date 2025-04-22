package me.zhengjie.modules.website.service.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author eleven
 */
@Data
public class WebsiteAndImageParseVo extends WebsiteVo{

    @ApiModelProperty(value = "网站对用的图片解析地址")
    private List<ImageParseBaseVo> imageParses = new ArrayList<>();
}

