package me.zhengjie.modules.characterReplaceRule.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author eleven
 */
@Data
public class CharacterReplaceRuleItemRequest {

    @ApiModelProperty(value = "原字符")
    private String originCharacter;


    @ApiModelProperty(value = "新字符，替换后的字符，为空表示删除")
    private String newCharacter;
}
