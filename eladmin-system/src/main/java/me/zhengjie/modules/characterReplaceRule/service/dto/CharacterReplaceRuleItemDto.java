package me.zhengjie.modules.characterReplaceRule.service.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author eleven
 */
@Data
public class CharacterReplaceRuleItemDto {

    @ApiModelProperty(value = "原字符")
    private String originCharacter;


    @ApiModelProperty(value = "新字符，替换后的字符，为空表示删除")
    private String newCharacter;
}
