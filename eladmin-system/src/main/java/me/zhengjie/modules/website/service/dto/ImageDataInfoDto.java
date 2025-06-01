package me.zhengjie.modules.website.service.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author eleven
 */
@Data
public class ImageDataInfoDto {

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "下载记录id")
    private String downloadRecordId;

    @ApiModelProperty(value = "图片id")
    private String imageId;

    @ApiModelProperty(value = "图片标题")
    private String imageTitle;

    @ApiModelProperty(value = "图片链接")
    private String imageUrl;

    @ApiModelProperty(value = "产品链接")
    private String productUrl;

    @ApiModelProperty(value = "token id")
    private String tokenId;

    @ApiModelProperty(value = "用户")
    private String userName;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;
}
