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
@TableName("cb_user_download_record")
public class UserDownloadRecord {

    @TableId(value = "udr_id",type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "下载记录id")
    private String id;

    @TableField(value = "udr_token_id")
    @ApiModelProperty(value = "token id")
    private String tokenId;

    @TableField(value = "udr_ws_id")
    @ApiModelProperty(value = "网站id")
    private String websiteId;

    @TableField(value = "udr_image_count")
    @ApiModelProperty(value = "下载图片数量")
    private int downloadImageCount;

    @TableField(value = "udr_keyword")
    @ApiModelProperty(value = "搜索关键字")
    private String keyword;

    @TableField(value = "udr_search_url")
    @ApiModelProperty(value = "搜索url")
    private String searchUrl;

    @TableField(value = "udr_create_time")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
}
