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

    @ApiModelProperty(value = "关键词")
    private String keyword;
    /**
     * @see me.zhengjie.modules.store.domain.vo.RiskType
     */
    @ApiModelProperty(value = "主题风险")
    private int riskType;
}
