package me.zhengjie.modules.website.rest;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.annotation.Log;
import me.zhengjie.modules.website.domain.vo.ProductCategoryCriteria;
import me.zhengjie.modules.website.domain.vo.ProductCategoryRequest;
import me.zhengjie.modules.website.service.IProductCategoryService;
import me.zhengjie.modules.website.service.dto.ProductCategoryVo;
import me.zhengjie.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author eleven
 */
@Slf4j
@RestController
@Api(tags = "产品分类")
@RequestMapping("/api/productCategory")
public class ProductCategoryController {

    @Autowired
    private IProductCategoryService service;


    @ApiOperation("产品分类分页")
    @GetMapping(value = "/page")
    public ResponseEntity<PageResult<ProductCategoryVo>> queryTheme(ProductCategoryCriteria criteria, Page<Object> page){
        return new ResponseEntity<>(service.queryAll(criteria, page), HttpStatus.OK);
    }

    /**
     * 创建产品分类
     * @param request
     * @return
     */
    @PostMapping
    @Log("新增ProductCategory")
    @ApiOperation("新增ProductCategory")
    public ResponseEntity<Object> createProductCategory(@Validated @RequestBody ProductCategoryRequest request){
        service.createProductCategory(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * 创建产品分类
     * @param request
     * @return
     */
    @PutMapping
    @Log("更新ProductCategory")
    @ApiOperation("更新ProductCategory")
    public ResponseEntity<Object> updateProductCategory(@Validated @RequestBody ProductCategoryRequest request){
        service.updateProductCategory(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PutMapping("/updateStatus")
    @Log("更新状态")
    @ApiOperation("更新状态ProductCategory")
    public ResponseEntity<Object> updateStatus(String id) {
        service.updateStatus(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
