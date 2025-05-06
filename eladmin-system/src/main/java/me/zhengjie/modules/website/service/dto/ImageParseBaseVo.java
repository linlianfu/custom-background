package me.zhengjie.modules.website.service.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author eleven
 */
@Data
public class ImageParseBaseVo {
    @ApiModelProperty(value = "地址id")
    private String id;

    @ApiModelProperty(value = "网站名称")
    private String siteName;

    @ApiModelProperty(value = "解析名称")
    private String parseName;

    @ApiModelProperty(value = "解析地址 | 代码替换")
    private String parseUrl;
}
