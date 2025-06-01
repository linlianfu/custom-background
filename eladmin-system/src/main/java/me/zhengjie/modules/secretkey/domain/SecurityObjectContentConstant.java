package me.zhengjie.modules.secretkey.domain;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author eleven
 */
public interface SecurityObjectContentConstant {

    @ApiModelProperty(value = "规则内容")
    String RULE_CONTENT = "ruleContent";

    @ApiModelProperty(value = "自定义规则")
    String CUSTOM_RULE = "customRule";

}
