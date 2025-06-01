package me.zhengjie.modules.website.service.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 产品分类
 * @author eleven
 */
@Data
public class ProductCategoryDto {

    @ApiModelProperty(value = "分类id")
    private String id;

    @ApiModelProperty(value = "产品code")
    private String code;

    @ApiModelProperty(value = "产品名称")
    private String name;
}
