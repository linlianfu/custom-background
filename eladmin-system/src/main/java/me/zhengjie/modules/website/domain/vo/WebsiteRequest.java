package me.zhengjie.modules.website.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @author eleven
 */
@Data
public class WebsiteRequest {

    @ApiModelProperty(value = "网站id")
    private String id;

    @ApiModelProperty(value = "网站标识")
    private String code;

    @ApiModelProperty(value = "网站地址")
    private String address;
}
