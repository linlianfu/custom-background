package me.zhengjie.modules.characterReplaceRule.service.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 元素替换规则
 * @author eleven
 */
@Data
public class CharacterReplaceRuleBaseDto {

    @ApiModelProperty(value = "规则id")
    private String ruleId;

    @ApiModelProperty(value = "规则名称")
    private String ruleName;

    @ApiModelProperty(value = "字符替换组")
    private List<CharacterReplaceRuleItemDto> characterReplaceGroup;



}
