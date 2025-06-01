package me.zhengjie.modules.app.service.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author eleven
 */
@Data
public class ImageDataDto {

    @ApiModelProperty(value = "图片id")
    private String imageId;

    @ApiModelProperty(value = "图片标题")
    private String title;

    @ApiModelProperty(value = "图片下载地址 | 实际下载地址")
    private String downloadUrl;

}
