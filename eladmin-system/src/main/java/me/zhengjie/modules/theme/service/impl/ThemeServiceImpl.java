package me.zhengjie.modules.theme.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.theme.domain.Theme;
import me.zhengjie.modules.theme.domain.vo.ThemeQueryCriteria;
import me.zhengjie.modules.theme.domain.vo.ThemeVo;
import me.zhengjie.modules.theme.mapper.ThemeMapper;
import me.zhengjie.modules.theme.service.ThemeService;
import me.zhengjie.utils.ModelMapperUtils;
import me.zhengjie.utils.PageResult;
import me.zhengjie.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;


/**
 * @author eleven
 */
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "theme")
public class ThemeServiceImpl implements ThemeService {

    @Autowired
    private ThemeMapper themeMapper;

    @Override
    public PageResult<ThemeVo> queryAll(ThemeQueryCriteria criteria, Page<Object> page) {
        Page<Theme> dbPage = new Page<>(page.getCurrent(), page.getSize());
        dbPage = themeMapper.selectPage(dbPage, Wrappers.lambdaQuery(Theme.class));
        return PageUtil.toPage(ModelMapperUtils.mapList(dbPage.getRecords(),ThemeVo.class),dbPage.getTotal());
    }
}
