package me.zhengjie.modules.characterReplaceRule.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author eleven
 */
@Data
public class CharacterReplaceRuleCriteria {

    @ApiModelProperty(value = "规则名称")
    private String ruleName;

    @ApiModelProperty(value = "网站id")
    private String websiteId;
}
