package me.zhengjie.modules.characterReplaceRule.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author eleven
 */
@Data
public class CharacterReplaceRuleRequest {

    @ApiModelProperty(value = "规则id")
    private String id;

    @ApiModelProperty(value = "规则名称")
    private String ruleName;

    @ApiModelProperty(value = "网站id")
    private String websiteId;

    @ApiModelProperty(value = "是否可用")
    private boolean enable;

    @ApiModelProperty(value = "字符替换组")
    private List<CharacterReplaceRuleItemRequest> ruleItem;
}
