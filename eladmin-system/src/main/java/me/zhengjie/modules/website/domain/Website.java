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
 * CREATE TABLE `cb_website` (
 *   `ws_id` varchar(32) NOT NULL COMMENT '网站id',
 *   `ws_code` varchar(255) DEFAULT NULL COMMENT '网站标识',
 *   `ws_address` varchar(255) DEFAULT NULL COMMENT '网站地址',
 *   `ws_create_id` varchar(32) DEFAULT NULL COMMENT '创建人',
 *   `ws_create_time` datetime DEFAULT NULL COMMENT '创建时间',
 *   PRIMARY KEY (`ws_id`)
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='网站管理';
 */
@Data
@TableName("cb_website")
public class Website {

    @TableId(value = "ws_id",type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "网站id")
    private String id;

    @TableField(value = "ws_code")
    @ApiModelProperty(value = "网站标识")
    private String code;

    @TableField(value = "ws_address")
    @ApiModelProperty(value = "网站地址")
    private String address;

    @TableField(value = "ws_create_id")
    @ApiModelProperty(value = "创建人")
    private String createId;

    @TableField(value = "ws_create_time")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
}
