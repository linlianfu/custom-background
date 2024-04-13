package me.zhengjie.modules.store.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author eleven
 */
@Data
public class StoreThemeDetail extends StoreTheme {

    @ApiModelProperty(value = "店铺id")
    private String storeName;

    @ApiModelProperty(value = "主题名称")
    private String themeName;

    @ApiModelProperty(value = "主题")
    private String theme;

    @ApiModelProperty(value = "标签，次关键词")
    private String label;
    /**
     * @see me.zhengjie.modules.store.domain.vo.RiskType
     */
    @ApiModelProperty(value = "主题风险")
    private int riskType;
}
