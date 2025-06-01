package me.zhengjie.modules.characterReplaceRule.domain;

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
@TableName("cb_character_replace_rule")
public class CharacterReplaceRule {

    @TableId(value = "crr_id",type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "规则id")
    private String id;

    @TableField(value = "crr_rule_name")
    @ApiModelProperty(value = "规则名称")
    private String ruleName;

    @TableField(value = "crr_ws_id")
    @ApiModelProperty(value = "网站id")
    private String websiteId;

    @TableField(value = "crr_enable")
    @ApiModelProperty(value = "是否可用")
    private boolean enable;

    @TableField(value = "crr_create_id")
    @ApiModelProperty(value = "创建人")
    private String createId;

    @TableField(value = "crr_create_time")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
}
