package me.zhengjie.modules.website.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author eleven
 */
@Data
public class ProductCategoryRequest {

    @ApiModelProperty(value = "分类id")
    private String id;

    @ApiModelProperty(value = "网站id")
    private String websiteId;

    @ApiModelProperty(value = "分类标识")
    private String categoryCode;

    @ApiModelProperty(value = "分类名称")
    private String categoryName;

    @ApiModelProperty(value = "是否可用")
    private boolean enable;

}
