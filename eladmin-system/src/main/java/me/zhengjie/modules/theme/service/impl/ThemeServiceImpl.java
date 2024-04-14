package me.zhengjie.modules.theme.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.store.domain.StoreTheme;
import me.zhengjie.modules.store.domain.ThemeAssociateStoreResult;
import me.zhengjie.modules.store.domain.vo.StoreThemeQueryCriteria;
import me.zhengjie.modules.store.mapper.StoreThemeMapper;
import me.zhengjie.modules.system.domain.User;
import me.zhengjie.modules.system.mapper.UserMapper;
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
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * @author eleven
 */
@Service
@RequiredArgsConstructor
public class ThemeServiceImpl extends ServiceImpl<ThemeMapper, Theme> implements ThemeService {

    @Autowired
    private ThemeMapper themeMapper;
    @Autowired
    private  UserMapper userMapper;
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
        if (StringUtils.isNotBlank(criteria.getTheme())) {
            wrapper.like(Theme::getTheme, criteria.getTheme());
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
        List<ThemeVo> list = ModelMapperUtils.mapList(dbPage.getRecords(), ThemeVo.class);
        if (CollectionUtils.isNotEmpty(list)){
            Set<String> createIdList = list.stream().map(ThemeVo::getCreateId).collect(Collectors.toSet());
            List<User> users = userMapper.selectBatchIds(createIdList);
            StoreThemeQueryCriteria storeThemeQueryCriteria = new StoreThemeQueryCriteria();
            storeThemeQueryCriteria.setThemeIdList(list.stream().map(ThemeVo::getId).collect(Collectors.toList()));
            List<ThemeAssociateStoreResult> themeStoreCount =
                    storeThemeMapper.countThemeAssociateStoreGroupByTheme(storeThemeQueryCriteria);
            if (CollectionUtils.isNotEmpty(users)){
                Map<String, String> map = users.stream().collect(Collectors.toMap(User::getId, User::getNickName));
                Map<String, Integer> themeStoreCountMap = themeStoreCount.stream().collect(Collectors.toMap(ThemeAssociateStoreResult::getThemeId,
                        ThemeAssociateStoreResult::getCount));
                for (ThemeVo p : list) {
                    p.setCreate(map.get(p.getCreateId()));
                    p.setAssociateStoreCount(themeStoreCountMap.getOrDefault(p.getId(),0));
                }
            }
        }
        return PageUtil.toPage(list, dbPage.getTotal());
    }

    @Override
    public boolean create(ThemeRequest request) {
        Assert.hasText(request.getName(),"请输入主题名称");
        Assert.hasText(request.getTheme(),"请输入主题");
        String currentUserId = SecurityUtils.getCurrentUserId();

        Long count = themeMapper.selectCount(Wrappers.lambdaQuery(Theme.class)
                .eq(Theme::getName, request.getName())
                .eq(Theme::getTheme,request.getTheme())
                .eq(Theme::getCreateId,currentUserId)
        );
        Assert.isTrue(count == null || count <=0,"存在主题名称和主题一样的数据，请勿重复创建");
        Theme entity = new Theme();
        entity.setName(request.getName());
        entity.setTheme(request.getTheme());
        entity.setLabel(request.getLabel());
        entity.setCategoryId(request.getCategoryId());
        entity.setRiskType(request.getRiskType());
        entity.setFlow(request.getFlow());
        entity.setRemark(request.getRemark());
        entity.setCreateTime(new Date());
        entity.setCreateId(currentUserId);
        save(entity);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(ThemeRequest request) {
        Assert.hasText(request.getName(),"请输入主题名称");
        Assert.hasText(request.getTheme(),"请输入主题");
        Theme theme = getById(request.getId());
        theme.copy(request);
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
