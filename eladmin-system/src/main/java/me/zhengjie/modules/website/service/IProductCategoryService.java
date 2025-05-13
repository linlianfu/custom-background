package me.zhengjie.modules.website.service;

import me.zhengjie.modules.website.service.dto.ProductCategoryDto;

import java.util.List;

/** 网站产品分类
 * @author eleven
 */
public interface IProductCategoryService {

    List<ProductCategoryDto> findProductCategory(String websiteCode);
}
