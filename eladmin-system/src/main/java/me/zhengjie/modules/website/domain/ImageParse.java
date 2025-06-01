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
@TableName("cb_image_parse")
public class ImageParse {

    @TableId(value = "ip_id",type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "地址id")
    private String id;

    @TableField(value = "ip_parse_name")
    @ApiModelProperty(value = "解析名称")
    private String parseName;

    @TableField(value = "ip_ws_id")
    @ApiModelProperty(value = "解析的网站")
    private String websiteId;

    @TableField(value = "ip_parse_url")
    @ApiModelProperty(value = "解析地址")
    private String parseUrl;

    /**
     * @see ParseType
     */
    @TableField(value = "ip_parse_type")
    @ApiModelProperty(value = "解析类型 | 1 PNG | 2 JPG")
    private int parseType;

    @TableField(value = "ip_available_range")
    @ApiModelProperty(value = "可用范围 | -1 不限 | 1 内部 | 2 外部")
    private int availableRange;


    @TableField(value = "ip_create_id")
    @ApiModelProperty(value = "创建人")
    private String createId;

    @TableField(value = "ip_create_time")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
}
