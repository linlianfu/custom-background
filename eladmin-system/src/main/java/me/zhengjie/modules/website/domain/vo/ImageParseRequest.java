package me.zhengjie.modules.website.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import me.zhengjie.modules.website.domain.ParseType;

/**
 * @author eleven
 */
@Data
public class ImageParseRequest {

    @ApiModelProperty(value = "地址id")
    private String id;

    @ApiModelProperty(value = "解析名称")
    private String parseName;

    @ApiModelProperty(value = "解析的网站")
    private String websiteId;

    @ApiModelProperty(value = "解析地址")
    private String parseUrl;

    /**
     * @see ParseType
     */
    @ApiModelProperty(value = "解析类型 | 1 PNG | 2 JPG")
    private int parseType;

    /**
     * @see me.zhengjie.modules.website.domain.ImageParseAvailableRange
     */
    @ApiModelProperty(value = "可用范围")
    private int availableRange;
}
