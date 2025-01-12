package me.zhengjie.modules.secretkey.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 爬虫助手 密钥
 * @author eleven
 */
@Data
@TableName("cb_secret_key")
public class SecretKey {

    @TableId(value = "sc_id",type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "密钥id")
    private String id;

    @TableField(value = "sc_name")
    @ApiModelProperty(value = "名称")
    private String name;

    @TableField(value = "sc_secret_key")
    @ApiModelProperty(value = "密钥")
    private String secretKey;

    @TableField(value = "sc_device_number")
    @ApiModelProperty(value = "设备号")
    private String deviceNumber;

    @TableField(value = "sc_enable")
    @ApiModelProperty(value = "是否可用")
    private boolean enable;

    @TableField(value = "sc_expiration_date")
    @ApiModelProperty(value = "过期时间")
    private Date expirationDate;

    @TableField(value = "sc_create_time")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
}
