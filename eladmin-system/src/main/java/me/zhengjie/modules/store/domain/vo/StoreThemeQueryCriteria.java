package me.zhengjie.modules.store.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author eleven
 * @date 2024-03-16
 **/
@Data
public class StoreThemeQueryCriteria{

    @ApiModelProperty(value = "店铺id")
    private String storeId;

    @ApiModelProperty(value = "主题id")
    private String themeId;
    /**
     * @see Tort
     */
    @ApiModelProperty(value = "是否侵权")
    private Integer tort;
    /**
     * @see RiskType
     */
    @ApiModelProperty(value = "是否侵权")
    private Integer riskType;

    /**
     * @see TortType
     */
    @ApiModelProperty(value = "侵权类型")
    private Integer tortType;

    @ApiModelProperty(value = "知识产权名称")
    private String intellectualPropertyName;

    @ApiModelProperty(value = "创建人")
    private String createId;
}