package me.zhengjie.modules.characterReplaceRule.service.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @author eleven
 */
@Data
public class CharacterReplaceRuleDto extends CharacterReplaceRuleBaseDto {

    @ApiModelProperty(value = "规则id")
    private String id;

    @ApiModelProperty(value = "网站id")
    private String websiteId;

    @ApiModelProperty(value = "是否可用")
    private boolean enable;

    @ApiModelProperty(value = "网站")
    private String website;
}
