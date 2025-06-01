package me.zhengjie.modules.secretkey.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 *  安全对象
 * @author eleven
 */
@Data
@TableName("cb_security_object")
public class SecurityObject {

    @TableId(value = "so_id",type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * @see SecurityObject#getTokenId()
     */
    @Deprecated
    @TableField(value = "so_token")
    @ApiModelProperty(value = "密钥")
    private String token;

    @TableField(value = "so_token_id")
    @ApiModelProperty(value = "tokenId")
    private String tokenId;

    /**
     * @see me.zhengjie.modules.secretkey.domain.SecurityObjectType
     */
    @TableField(value = "so_type")
    @ApiModelProperty(value = "安全对象类型")
    private int type;

    @TableField(value = "so_content")
    @ApiModelProperty(value = "安全对象内容")
    private String content;

    @TableField(value = "so_create_time")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
}
