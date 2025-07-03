package me.zhengjie.modules.website.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.website.domain.ProductCategory;
import me.zhengjie.modules.website.domain.Website;
import me.zhengjie.modules.website.domain.vo.ProductCategoryCriteria;
import me.zhengjie.modules.website.domain.vo.ProductCategoryRequest;
import me.zhengjie.modules.website.mapper.ProductCategoryMapper;
import me.zhengjie.modules.website.mapper.WebsiteMapper;
import me.zhengjie.modules.website.service.IProductCategoryService;
import me.zhengjie.modules.website.service.dto.ProductCategoryDto;
import me.zhengjie.modules.website.service.dto.ProductCategoryVo;
import me.zhengjie.utils.ModelMapperUtils;
import me.zhengjie.utils.PageResult;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.SecurityUtils;
import me.zhengjie.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author eleven
 */
@Service
@RequiredArgsConstructor
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements IProductCategoryService {


    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Autowired
    protected WebsiteMapper websiteMapper;

    @Override
    public List<ProductCategoryDto> findProductCategory(String websiteId,Boolean enable) {
        List<ProductCategoryDto> list = new ArrayList<>();

        LambdaQueryWrapper<ProductCategory> wrapper = Wrappers.lambdaQuery(ProductCategory.class);
        if (enable != null){
            wrapper.eq(ProductCategory::isEnable,enable);
        }
        if (StringUtils.isNotBlank(websiteId)){
            wrapper.eq(ProductCategory::getWebsiteId,websiteId);
        }
        List<ProductCategory> productCategories = productCategoryMapper.selectList(wrapper);
        if (CollectionUtils.isNotEmpty(productCategories)){
            for (ProductCategory productCategory : productCategories) {
                ProductCategoryDto item = new ProductCategoryDto();
                item.setId(productCategory.getId());
                item.setCode(productCategory.getCategoryCode());
                item.setName(productCategory.getCategoryName());
                list.add(item);
            }
        }
        return list;
    }


    @Override
    public boolean createProductCategory(ProductCategoryRequest request) {
        ProductCategory bean = new ProductCategory();
        bean.setWebsiteId(request.getWebsiteId());
        bean.setCategoryName(request.getCategoryName());
        bean.setCategoryCode(request.getCategoryCode());
        bean.setCreateId(SecurityUtils.getCurrentUserId());
        bean.setEnable(request.isEnable());
        bean.setCreateTime(new Date());
        save(bean);
        return true;
    }


    @Override
    public boolean updateProductCategory(ProductCategoryRequest request) {
        ProductCategory productCategory = productCategoryMapper.selectById(request.getId());
        productCategory.setEnable(request.isEnable());
        productCategory.setCategoryCode(request.getCategoryCode());
        productCategory.setCategoryName(request.getCategoryName());
        productCategory.setWebsiteId(request.getWebsiteId());
        updateById(productCategory);
        return true;
    }

    @Override
    public PageResult<ProductCategoryVo> queryAll(ProductCategoryCriteria criteria, Page<Object> page) {
        LambdaQueryWrapper<ProductCategory> wrapper = Wrappers.lambdaQuery(ProductCategory.class);

        if (StringUtils.isNotBlank(criteria.getWebsiteId())){
            wrapper.like(ProductCategory::getWebsiteId,criteria.getWebsiteId());
        }
        if(StringUtils.isNotBlank(criteria.getCategoryCode())){
            wrapper.eq(ProductCategory::getCategoryCode,criteria.getCategoryCode());
        }

        if(StringUtils.isNotBlank(criteria.getCategoryName())){
            wrapper.eq(ProductCategory::getCategoryName,criteria.getCategoryName());
        }

        if(criteria.getEnable() != null){
            wrapper.eq(ProductCategory::isEnable,criteria.getEnable());
        }
        Page<ProductCategory> dbPage = new Page<>(page.getCurrent(), page.getSize());
        dbPage = productCategoryMapper.selectPage(dbPage, wrapper.orderByDesc(ProductCategory::getCreateTime));

        List<ProductCategoryVo> list = ModelMapperUtils.mapList(dbPage.getRecords(), ProductCategoryVo.class);

        if (CollectionUtils.isNotEmpty(list)){
            Set<String> websiteIdSet = list.stream().map(ProductCategoryVo::getWebsiteId).collect(Collectors.toSet());
            List<Website> websites = websiteMapper.selectBatchIds(websiteIdSet);
            if (CollectionUtils.isNotEmpty(websites)){
                Map<String, String> websiteMap = websites.stream().collect(
                        Collectors.toMap(Website::getId, Website::getSiteName)
                );
                for (ProductCategoryVo productCategoryVo : list) {
                    productCategoryVo.setWebsiteName(websiteMap.get(productCategoryVo.getWebsiteId()));
                }
            }
        }
        return PageUtil.toPage(list, dbPage.getTotal());
    }

    @Override
    public boolean updateStatus(String id) {
        ProductCategory productCategory = productCategoryMapper.selectById(id);
        productCategory.setEnable(!productCategory.isEnable());
        updateById(productCategory);
        return true;
    }
}
