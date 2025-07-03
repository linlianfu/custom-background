package me.zhengjie.modules.website.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.zhengjie.modules.website.domain.vo.ProductCategoryCriteria;
import me.zhengjie.modules.website.domain.vo.ProductCategoryRequest;
import me.zhengjie.modules.website.service.dto.ProductCategoryDto;
import me.zhengjie.modules.website.service.dto.ProductCategoryVo;
import me.zhengjie.utils.PageResult;

import java.util.List;

/** 网站产品分类
 * @author eleven
 */
public interface IProductCategoryService {

    /**
     * 查询分类
     * @param websiteCode
     * @return
     */
    List<ProductCategoryDto> findProductCategory(String websiteId,Boolean enable);

    /**
     * 创建分类
     * @param request
     * @return
     */
    boolean createProductCategory(ProductCategoryRequest request);

    /**
     * 更新分类
     * @param request
     * @return
     */
    boolean updateProductCategory(ProductCategoryRequest request);

    /**
     * 分页查询
     *
     * @param criteria 条件
     * @param page     分页参数
     * @return /
     */
    PageResult<ProductCategoryVo> queryAll(ProductCategoryCriteria criteria, Page<Object> page);

    /**
     * 更新状态
     * @param id
     * @return
     */
    boolean updateStatus(String id);
}
