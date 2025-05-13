package me.zhengjie.modules.website.service.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author eleven
 */
@Data
public class WebsiteAndImageParseVo extends WebsiteVo{

    @ApiModelProperty(value = "网站对应的图片解析地址")
    private List<ImageParseBaseVo> imageParses;

    @ApiModelProperty(value = "授权的预览图片解析资源")
    private List<ImageParseBaseVo> previewImageParse;

    @ApiModelProperty(value = "网站产品分类")
    private List<ProductCategoryDto> productCategory;
}

