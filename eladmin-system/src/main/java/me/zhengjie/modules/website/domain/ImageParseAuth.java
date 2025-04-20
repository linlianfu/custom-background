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
@TableName("cb_image_parse_auth")
public class ImageParseAuth {

    @TableId(value = "ipa_id",type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "授权id")
    private String id;

    @TableField(value = "ipa_ip_id")
    @ApiModelProperty(value = "地址id")
    private String imageParseId;

    @TableField(value = "ipa_sc_id")
    @ApiModelProperty(value = "token id")
    private String tokenId;

    @TableField(value = "ipa_create_id")
    @ApiModelProperty(value = "创建人")
    private String createId;

    @TableField(value = "ipa_create_time")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
}
