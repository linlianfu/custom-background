package me.zhengjie.modules.theme.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author eleven
 */
@Data
public class ThemeVo {

    @ApiModelProperty(value = "主题id")
    private String id;

    @ApiModelProperty(value = "主题名称")
    private String name;

    @ApiModelProperty(value = "主题")
    private String theme;

    @ApiModelProperty(value = "标签，次关键词")
    private String label;

    @ApiModelProperty(value = "主题分类")
    private String categoryId;

    /**
     * @see me.zhengjie.modules.store.domain.vo.RiskType
     */
    @ApiModelProperty(value = "风险等级 | 1、常规主题  2、一般侵权 3、资金冻结 4、严重侵权")
    private int riskType;

    @ApiModelProperty(value = "流量等级 | 1、常规主题 2、爆款主题")
    private int flow;

    @ApiModelProperty(value = "关联店铺数")
    private int associateStoreCount;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "创建人")
    private String createId;

    @ApiModelProperty(value = "创建人")
    private String create;

    @ApiModelProperty(value = "创建时间")
    private String createTime;
}
