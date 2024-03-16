package me.zhengjie.modules.store.rest;

import me.zhengjie.annotation.Log;
import me.zhengjie.modules.store.domain.StoreTheme;
import me.zhengjie.modules.store.domain.vo.StoreThemeVo;
import me.zhengjie.modules.store.service.StoreThemeService;
import me.zhengjie.modules.store.domain.vo.StoreThemeQueryCriteria;
import lombok.RequiredArgsConstructor;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.zhengjie.utils.PageResult;

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
    @ApiOperation("查询StoreTheme")
    @PreAuthorize("@el.check('storeTheme:list')")
    public ResponseEntity<PageResult<StoreThemeVo>> pageStoreTheme(StoreThemeQueryCriteria criteria, Page<Object> page){
        return new ResponseEntity<>(storeThemeService.pageStoreTheme(criteria,page),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增StoreTheme")
    @ApiOperation("新增StoreTheme")
    @PreAuthorize("@el.check('storeTheme:add')")
    public ResponseEntity<Object> createStoreTheme(@Validated @RequestBody StoreTheme resources){
        storeThemeService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改StoreTheme")
    @ApiOperation("修改StoreTheme")
    @PreAuthorize("@el.check('storeTheme:edit')")
    public ResponseEntity<Object> updateStoreTheme(@Validated @RequestBody StoreTheme resources){
        storeThemeService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除StoreTheme")
    @ApiOperation("删除StoreTheme")
    @PreAuthorize("@el.check('storeTheme:del')")
    public ResponseEntity<Object> deleteStoreTheme(@RequestBody List<String> ids) {
        storeThemeService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}