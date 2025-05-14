package me.zhengjie.modules.website.service.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import me.zhengjie.modules.website.domain.ParseType;

/**
 * @author eleven
 */
@Data
public class ImageParseVo {

    @ApiModelProperty(value = "地址id")
    private String id;

    @ApiModelProperty(value = "解析名称")
    private String parseName;

    @ApiModelProperty(value = "解析的网站id")
    private String websiteId;

    @ApiModelProperty(value = "解析的网站")
    private String websiteCode;

    @ApiModelProperty(value = "解析地址 | 代码替换")
    private String parseUrl;

    /**
     * @see me.zhengjie.modules.website.domain.ImageParseAvailableRange
     */
    @ApiModelProperty(value = "可用范围")
    private int availableRange;

    @ApiModelProperty(value = "已授权用户数")
    private int authUserCount;

    /**
     * @see ParseType
     */
    @ApiModelProperty(value = "解析类型 | 1 PNG | 2 JPG")
    private int parseType;
}
