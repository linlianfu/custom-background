package me.zhengjie.modules.theme.rest;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.lettuce.core.output.ListOfGenericMapsOutput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.annotation.Log;
import me.zhengjie.modules.store.domain.Store;
import me.zhengjie.modules.system.domain.Job;
import me.zhengjie.modules.system.domain.vo.JobQueryCriteria;
import me.zhengjie.modules.theme.domain.Theme;
import me.zhengjie.modules.theme.domain.vo.ThemeQueryCriteria;
import me.zhengjie.modules.theme.domain.vo.ThemeRequest;
import me.zhengjie.modules.theme.domain.vo.ThemeVo;
import me.zhengjie.modules.theme.service.ThemeService;
import me.zhengjie.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * @author eleven
 */
@Slf4j
@RestController
@Api(tags = "主题管理：主题管理")
@RequestMapping("/api/theme")
public class ThemeController {

    @Autowired
    private ThemeService service;

    @ApiOperation("主题分页")
    @GetMapping(value = "/page")
    public ResponseEntity<PageResult<ThemeVo>> queryTheme(ThemeQueryCriteria criteria, Page<Object> page){
        return new ResponseEntity<>(service.queryAll(criteria, page), HttpStatus.OK);
    }

    /**
     * 创建主题
     * @param request
     * @return
     */
    @ApiOperation("创建主题")
    @PostMapping
    public ResponseEntity<Object> create(@Validated @RequestBody ThemeRequest request){
        service.create(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改Theme")
    @ApiOperation("修改Theme")
    public ResponseEntity<Object> updateStore(@Validated @RequestBody ThemeRequest resources){
        service.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除主题")
    @ApiOperation("删除主题")
    @DeleteMapping
    public ResponseEntity<Object> deleteJob(@RequestBody Set<String> ids){
        service.delete(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
