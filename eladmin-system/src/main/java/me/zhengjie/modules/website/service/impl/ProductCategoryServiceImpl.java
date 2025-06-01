package me.zhengjie.modules.website.service.impl;

import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.website.service.IProductCategoryService;
import me.zhengjie.modules.website.service.dto.ProductCategoryDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author eleven
 */
@Service
@RequiredArgsConstructor
public class ProductCategoryServiceImpl implements IProductCategoryService {

    @Override
    public List<ProductCategoryDto> findProductCategory(String websiteCode) {
        ProductCategoryDto tees = new ProductCategoryDto();
        List<ProductCategoryDto> list = new ArrayList<>();
        tees.setId("001");
        tees.setCode("u-tees");
        tees.setName("t shirt");
        list.add(tees);


        ProductCategoryDto pillowCase = new ProductCategoryDto();
        pillowCase.setId("002");
        pillowCase.setCode("u-pillows");
        pillowCase.setName("pillow");
        list.add(pillowCase);

        ProductCategoryDto stickers = new ProductCategoryDto();
        stickers.setId("003");
        stickers.setCode("all-stickers");
        stickers.setName("stickers");
        list.add(stickers);
        return list;
    }
}
