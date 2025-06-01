package me.zhengjie.modules.website.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author eleven
 */
@Data
public class ImageParseCriteria {

    @ApiModelProperty(value = "解析的网站")
    private String websiteId;

    @ApiModelProperty(value = "解析名称")
    private String parseName;
}
