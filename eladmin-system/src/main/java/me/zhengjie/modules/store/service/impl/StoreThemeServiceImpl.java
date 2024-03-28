/*
 *  Copyright 2019-2023 Zheng Jie
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package me.zhengjie.modules.store.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.store.domain.Store;
import me.zhengjie.modules.store.domain.StoreTheme;
import me.zhengjie.modules.store.domain.vo.StoreThemeQueryCriteria;
import me.zhengjie.modules.store.domain.vo.StoreThemeVo;
import me.zhengjie.modules.store.mapper.StoreMapper;
import me.zhengjie.modules.store.mapper.StoreThemeMapper;
import me.zhengjie.modules.store.service.StoreThemeService;
import me.zhengjie.modules.theme.domain.Theme;
import me.zhengjie.modules.theme.mapper.ThemeMapper;
import me.zhengjie.utils.ModelMapperUtils;
import me.zhengjie.utils.PageResult;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.SecurityUtils;
import me.zhengjie.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description 服务实现
 * @author eleven
 * @date 2024-03-16
 **/
@Service
@RequiredArgsConstructor
public class StoreThemeServiceImpl extends ServiceImpl<StoreThemeMapper, StoreTheme> implements StoreThemeService {

    @Autowired
    private StoreThemeMapper storeThemeMapper;

    @Autowired
    private final StoreMapper storeMapper;

    @Autowired
    private final ThemeMapper themeMapper;

    @Override
    public PageResult<StoreThemeVo> pageStoreTheme(StoreThemeQueryCriteria criteria, Page<Object> page){

        Page<StoreTheme> dbPage = new Page<>(page.getCurrent(), page.getSize());
        LambdaQueryWrapper<StoreTheme> wrapper = Wrappers.lambdaQuery(StoreTheme.class);
        if (StringUtils.isNotBlank(criteria.getStoreId())){
            wrapper.eq(StoreTheme::getStoreId,criteria.getStoreId());
        }
        if (StringUtils.isNotBlank(criteria.getThemeId())){
            wrapper.eq(StoreTheme::getThemeId,criteria.getThemeId());
        }
        if (criteria.getTortType() != null){
            wrapper.eq(StoreTheme::getTortType,criteria.getTortType());
        }
        dbPage = storeThemeMapper.selectPage(
                dbPage, wrapper.orderByDesc(StoreTheme::getCreateTime)
        );
        List<StoreThemeVo> storeThemeList = ModelMapperUtils.mapList(dbPage.getRecords(), StoreThemeVo.class);

        if (CollectionUtils.isEmpty(storeThemeList)) {
            return PageUtil.toPage(storeThemeList,dbPage.getTotal());
        }

        List<String> storeIdList = storeThemeList.stream().map(StoreThemeVo::getStoreId).collect(Collectors.toList());
        List<String> themeIdList = storeThemeList.stream().map(StoreThemeVo::getThemeId).collect(Collectors.toList());

        Map<String, String> storeMap = storeMapper.selectBatchIds(storeIdList).stream()
                .collect(Collectors.toMap(Store::getId, Store::getStoreName));
        Map<String, String> themeMap = themeMapper.selectBatchIds(themeIdList).stream()
                .collect(Collectors.toMap(Theme::getId, Theme::getName));
        storeThemeList =  storeThemeList.stream().peek(p->{
            p.setStoreName(storeMap.get(p.getStoreId()));
            p.setThemeName(themeMap.get(p.getThemeId()));
        }).collect(Collectors.toList());
        return PageUtil.toPage(storeThemeList,dbPage.getTotal());
    }

    @Override
    public StoreThemeVo getStoreTheme(String id) {
        StoreTheme storeTheme = storeThemeMapper.selectById(id);
        StoreThemeVo result = ModelMapperUtils.map(storeTheme, StoreThemeVo.class);
        Store store = storeMapper.selectById(result.getStoreId());
        Theme theme = themeMapper.selectById(result.getThemeId());
        result.setStoreName(store.getStoreName());
        result.setThemeName(theme.getName());
        return result;
    }

    @Override
    public List<StoreTheme> queryAll(StoreThemeQueryCriteria criteria){
        return storeThemeMapper.findAll(criteria);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(StoreTheme resources) {
        resources.setCreateId(SecurityUtils.getCurrentUserId().toString());
        resources.setCreateTime(LocalDateTime.now());
        save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(StoreTheme resources) {
        StoreTheme storeTheme = getById(resources.getId());
        storeTheme.copy(resources);
        saveOrUpdate(storeTheme);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteAll(List<String> ids) {
        removeBatchByIds(ids);
    }
}