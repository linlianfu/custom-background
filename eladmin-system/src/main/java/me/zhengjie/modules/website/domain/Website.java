package me.zhengjie.modules.website.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 *
 * @author eleven
 *
 */
@Data
@TableName("cb_website")
public class Website {

    @TableId(value = "ws_id",type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "网站id")
    private String id;

    @TableField(value = "ws_site_name")
    @ApiModelProperty(value = "网站名称")
    private String siteName;

    @TableField(value = "ws_code")
    @ApiModelProperty(value = "网站标识")
    private String code;

    @TableField(value = "ws_address")
    @ApiModelProperty(value = "网站地址")
    private String address;

    @TableField(value = "ws_pixel_length")
    @ApiModelProperty(value = "网站地址")
    private String pixelLength;

    @TableField(value = "ws_pixel_width")
    @ApiModelProperty(value = "网站地址")
    private String pixelWidth;

    @TableField(value = "ws_create_id")
    @ApiModelProperty(value = "创建人")
    private String createId;

    @TableField(value = "ws_create_time")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
}
