package me.zhengjie.modules.store.rest;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.zhengjie.annotation.Log;
import me.zhengjie.modules.store.domain.StoreTheme;
import me.zhengjie.modules.store.domain.vo.StoreThemeQueryCriteria;
import me.zhengjie.modules.store.domain.vo.StoreThemeVo;
import me.zhengjie.modules.store.service.StoreThemeService;
import me.zhengjie.utils.PageResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author eleven
 * @date 2024-03-16
 **/
@RestController
@RequiredArgsConstructor
@Api(tags = "StoreTheme管理")
@RequestMapping("/api/storeTheme")
public class StoreThemeController {

    private final StoreThemeService storeThemeService;

    @GetMapping
    @Log("查询StoreTheme")
    public ResponseEntity<PageResult<StoreThemeVo>> pageStoreTheme(StoreThemeQueryCriteria criteria, Page<Object> page){
        return new ResponseEntity<>(storeThemeService.pageStoreTheme(criteria,page),HttpStatus.OK);
    }

    @GetMapping("/getStoreTheme")
    @Log("获取店铺主题详情")
    public ResponseEntity<StoreThemeVo> getStoreTheme(String id){
        return new ResponseEntity<>(storeThemeService.getStoreTheme(id),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增StoreTheme")
    @ApiOperation("新增StoreTheme")
    public ResponseEntity<Object> createStoreTheme(@Validated @RequestBody StoreTheme resources){
        storeThemeService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改StoreTheme")
    @ApiOperation("修改StoreTheme")
    public ResponseEntity<Object> updateStoreTheme(@Validated @RequestBody StoreTheme resources){
        storeThemeService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除StoreTheme")
    @ApiOperation("删除StoreTheme")
    public ResponseEntity<Object> deleteStoreTheme(@RequestBody List<String> ids) {
        storeThemeService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}