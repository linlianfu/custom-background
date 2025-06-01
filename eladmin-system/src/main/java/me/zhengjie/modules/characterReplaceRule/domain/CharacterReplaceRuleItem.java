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
@TableName("cb_character_replace_rule_item")
public class CharacterReplaceRuleItem {

    @TableId(value = "crri_id",type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "子规则id")
    private String id;

    @TableField(value = "crri_crr_id")
    @ApiModelProperty(value = "规则id")
    private String ruleId;

    @TableField(value = "crri_origin_character")
    @ApiModelProperty(value = "原字符")
    private String originCharacter;

    @TableField(value = "crri_new_character")
    @ApiModelProperty(value = "新字符，替换后的字符，为空表示删除")
    private String newCharacter;

    @TableField(value = "crri_create_id")
    @ApiModelProperty(value = "创建人")
    private String createId;

    @TableField(value = "crri_create_time")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
}
