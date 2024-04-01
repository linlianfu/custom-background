package me.zhengjie.modules.theme.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.store.domain.StoreTheme;
import me.zhengjie.modules.store.mapper.StoreThemeMapper;
import me.zhengjie.modules.system.service.UserService;
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
import me.zhengjie.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Set;


/**
 * @author eleven
 */
@Service
@RequiredArgsConstructor
public class ThemeServiceImpl extends ServiceImpl<ThemeMapper, Theme> implements ThemeService {

    @Autowired
    private ThemeMapper themeMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private StoreThemeMapper storeThemeMapper;

    @Override
    public PageResult<ThemeVo> queryAll(ThemeQueryCriteria criteria, Page<Object> page) {
        Page<Theme> dbPage = new Page<>(page.getCurrent(), page.getSize());
        LambdaQueryWrapper<Theme> wrapper = Wrappers.lambdaQuery(Theme.class);
        if (!userService.currentUserSuperRole()) {
            wrapper.eq(Theme::getCreateId, SecurityUtils.getCurrentUserId());
        }
        if (StringUtils.isNotBlank(criteria.getName())) {
            wrapper.like(Theme::getName, criteria.getName());
        }
        if (StringUtils.isNotBlank(criteria.getKeyword())) {
            wrapper.like(Theme::getKeyword, criteria.getKeyword());
        }
        if (criteria.getRiskType() != null) {
            wrapper.eq(Theme::getRiskType, criteria.getRiskType());
        }
        if (criteria.getFlow() != null) {
            wrapper.eq(Theme::getFlow, criteria.getFlow());
        }
        dbPage = themeMapper.selectPage(
                dbPage, wrapper.orderByDesc(Theme::getCreateTime)
        );
        return PageUtil.toPage(ModelMapperUtils.mapList(dbPage.getRecords(), ThemeVo.class), dbPage.getTotal());
    }

    @Override
    public boolean create(ThemeRequest request) {
        Theme entity = new Theme();
        entity.setName(request.getName());
        entity.setKeyword(request.getKeyword());
        entity.setCategoryId(request.getCategoryId());
        entity.setRiskType(request.getRiskType());
        entity.setFlow(request.getFlow());
        entity.setRemark(request.getRemark());
        entity.setCreateTime(new Date());
        entity.setCreateId(SecurityUtils.getCurrentUserId());
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
        Long count = storeThemeMapper.selectCount(Wrappers.lambdaQuery(StoreTheme.class)
                .in(StoreTheme::getThemeId, ids));
        if (count > 0) {
            throw new BadRequestException("主题被引用，无法删除");
        }
        themeMapper.deleteBatchIds(ids);
    }
}
