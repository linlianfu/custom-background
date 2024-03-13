package me.zhengjie.modules.theme.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author eleven
 */
@Data
public class ThemeRequest {

    @ApiModelProperty(value = "主题id")
    private String id;

    @NotBlank(message = "请填写主题名称")
    @ApiModelProperty(value = "主题名称")
    private String name;

    @NotBlank(message = "请填写关键词")
    @ApiModelProperty(value = "关键词")
    private String keyword;

    @ApiModelProperty(value = "主题分类")
    private String categoryId;

    @NotNull(message = "请选择风险等级")
    @ApiModelProperty(value = "风险等级 | 1、常规主题  2、一般侵权 3、资金冻结 4、严重侵权")
    private Integer tortType;

    @NotNull(message = "请选择流量等级")
    @ApiModelProperty(value = "流量等级 | 1、常规主题 2、爆款主题")
    private Integer flow;

    @ApiModelProperty(value = "备注")
    private String remark;
}
