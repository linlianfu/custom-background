package me.zhengjie.modules.website.service.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @author eleven
 */
@Data
public class WebsiteVo {

    @ApiModelProperty(value = "网站id")
    private String id;

    @ApiModelProperty(value = "网站名称")
    private String siteName;

    @ApiModelProperty(value = "网站标识")
    private String code;

    @ApiModelProperty(value = "网站地址")
    private String address;

    @ApiModelProperty(value = "像素大小")
    private String pixelLength;

    @ApiModelProperty(value = "像素大小")
    private String pixelWidth;
}
