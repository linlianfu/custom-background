package me.zhengjie.modules.theme.rest;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.lettuce.core.output.ListOfGenericMapsOutput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.modules.system.domain.Job;
import me.zhengjie.modules.system.domain.vo.JobQueryCriteria;
import me.zhengjie.modules.theme.domain.Theme;
import me.zhengjie.modules.theme.domain.vo.ThemeQueryCriteria;
import me.zhengjie.modules.theme.service.ThemeService;
import me.zhengjie.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;

/**
 * @author eleven
 */
@Slf4j
@RestController
@Api(tags = "主题管理：风险主题")
@RequestMapping("/api/theme/tort")
public class ThemeController {

    @Autowired
    private ThemeService service;

    public ThemeController(){
        log.info("=================ThemeController  初始化================");
    }

    @ApiOperation("查询风险主题")
    @GetMapping(value = "/page")
    @PreAuthorize("@el.check('job:list','user:list')")
    public ResponseEntity<PageResult<Theme>> queryTrotTheme(ThemeQueryCriteria criteria, Page<Object> page){
        return new ResponseEntity<>(service.queryAll(criteria, page), HttpStatus.OK);
    }

}