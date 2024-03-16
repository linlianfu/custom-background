package me.zhengjie.modules.theme.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.store.domain.Store;
import me.zhengjie.modules.store.mapper.StoreMapper;
import me.zhengjie.modules.theme.domain.Theme;
import me.zhengjie.modules.theme.domain.vo.ThemeQueryCriteria;
import me.zhengjie.modules.theme.domain.vo.ThemeRequest;
import me.zhengjie.modules.theme.domain.vo.ThemeVo;
import me.zhengjie.modules.theme.mapper.ThemeMapper;
import me.zhengjie.modules.theme.service.ThemeService;
import me.zhengjie.utils.ModelMapperUtils;
import me.zhengjie.utils.PageResult;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Set;


/**
 * @author eleven
 */
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "theme")
public class ThemeServiceImpl extends ServiceImpl<ThemeMapper, Theme> implements ThemeService {

    private ThemeMapper themeMapper;

    @Override
    public PageResult<ThemeVo> queryAll(ThemeQueryCriteria criteria, Page<Object> page) {
        Page<Theme> dbPage = new Page<>(page.getCurrent(), page.getSize());
        dbPage = themeMapper.selectPage(dbPage, Wrappers.lambdaQuery(Theme.class));
        return PageUtil.toPage(ModelMapperUtils.mapList(dbPage.getRecords(),ThemeVo.class),dbPage.getTotal());
    }

    @Override
    public boolean create(ThemeRequest request) {
        Theme entity = new Theme();
        entity.setName(request.getName());
        entity.setKeyword(request.getKeyword());
        entity.setCategoryId(request.getCategoryId());
        entity.setTortType(request.getTortType());
        entity.setFlow(request.getFlow());
        entity.setRemark(request.getRemark());
        entity.setCreateTime(new Date());
        entity.setCreatedId(SecurityUtils.getCurrentUserId()+"");
        save(entity);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(ThemeRequest resources) {
        Theme theme = getById(resources.getId());
        theme.copy(resources);
        saveOrUpdate(theme);
        return true;
    }


    @Override
    public void delete(Set<String> ids) {
        themeMapper.deleteBatchIds(ids);
    }
}
