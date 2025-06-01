package me.zhengjie.modules.website.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author eleven
 */
@Data
@TableName("cb_image_data_info")
public class ImageDataInfo {

    @TableId(value = "idi_id",type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "id")
    private String id;

    @TableField(value = "idi_udr_id")
    @ApiModelProperty(value = "下载记录id")
    private String downloadRecordId;

    @TableField(value = "idi_image_id")
    @ApiModelProperty(value = "图片id")
    private String imageId;

    @TableField(value = "idi_image_title")
    @ApiModelProperty(value = "图片标题")
    private String imageTitle;

    @TableField(value = "idi_image_url")
    @ApiModelProperty(value = "图片链接")
    private String imageUrl;

    @TableField(value = "idi_product_url")
    @ApiModelProperty(value = "产品链接")
    private String productUrl;

    @TableField(value = "idi_token_id")
    @ApiModelProperty(value = "token id")
    private String tokenId;

    @TableField(value = "idi_create_time")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
}
