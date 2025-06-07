package me.zhengjie.modules.website.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author eleven
 */
@Data
public class ImageDataInfoCriteria {

    @ApiModelProperty(value = "网站id")
    private String websiteId;

    @ApiModelProperty(value = "tokenId")
    private String tokenId;
}
