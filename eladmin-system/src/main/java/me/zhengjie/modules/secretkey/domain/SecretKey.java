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

    /**
     * @see IdentityType
     */
    @TableField(value = "sc_identity_type")
    @ApiModelProperty(value = "身份标识 | 1管理员，2 员工 ")
    private int identityType;

    @TableField(value = "sc_device_number")
    @ApiModelProperty(value = "设备号")
    private String deviceNumber;

    @TableField(value = "sc_belong")
    @ApiModelProperty(value = "归属 | 1 内部 | 2 外部")
    private int belong;

    @TableField(value = "sc_enable")
    @ApiModelProperty(value = "是否可用")
    private boolean enable;

    @TableField(value = "sc_expiration_date")
    @ApiModelProperty(value = "过期时间")
    private Date expirationDate;

    @TableField(value = "sc_create_time")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @TableField(value = "sc_last_login_time")
    @ApiModelProperty(value = "最后登录时间")
    private Date lastLoginTime;
}
