package me.zhengjie.modules.secretkey.service.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author eleven
 */
@Data
public class PermissionGroup {

    @ApiModelProperty(value = "展示规则内容")
    private boolean showRuleContent;

    @ApiModelProperty(value = "展示自定义规则")
    private boolean showCustomRule;
}
