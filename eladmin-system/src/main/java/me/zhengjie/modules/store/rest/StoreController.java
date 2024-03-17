package me.zhengjie.modules.store.rest;

import me.zhengjie.annotation.Log;
import me.zhengjie.modules.store.domain.Store;
import me.zhengjie.modules.store.service.StoreService;
import me.zhengjie.modules.store.domain.vo.StoreQueryCriteria;
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
 * @date 2024-03-15
 **/
@RestController
@RequiredArgsConstructor
@Api(tags = "Store管理")
@RequestMapping("/api/store")
public class StoreController {

    private final StoreService storeService;

    @GetMapping
    @Log("查询Store")
    @ApiOperation("查询Store")
    public ResponseEntity<PageResult<Store>> pageStore(StoreQueryCriteria criteria, Page<Object> page){
        return new ResponseEntity<>(storeService.pageStore(criteria,page),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增Store")
    @ApiOperation("新增Store")
    public ResponseEntity<Object> createStore(@Validated @RequestBody Store resources){
        storeService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改Store")
    @ApiOperation("修改Store")
    public ResponseEntity<Object> updateStore(@Validated @RequestBody Store resources){
        storeService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除Store")
    @ApiOperation("删除Store")
    public ResponseEntity<Object> deleteStore(@RequestBody List<String> ids) {
        storeService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}