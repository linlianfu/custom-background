package me.zhengjie.modules.website.service.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import me.zhengjie.modules.characterReplaceRule.service.dto.CharacterReplaceRuleBaseDto;

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

    @ApiModelProperty(value = "字符替换规则")
    private List<CharacterReplaceRuleBaseDto> characterReplaceRules;

    @ApiModelProperty(value = "排序字段值")
    private List<FieldValueDto> sortFieldValue;
}

