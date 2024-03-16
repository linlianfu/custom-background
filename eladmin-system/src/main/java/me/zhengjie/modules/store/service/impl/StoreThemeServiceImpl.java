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

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import me.zhengjie.modules.store.domain.Store;
import me.zhengjie.modules.store.domain.StoreTheme;
import me.zhengjie.modules.store.domain.vo.StoreThemeVo;
import me.zhengjie.modules.store.mapper.StoreMapper;
import me.zhengjie.modules.theme.domain.Theme;
import me.zhengjie.modules.theme.mapper.ThemeMapper;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.zhengjie.modules.store.service.StoreThemeService;
import me.zhengjie.modules.store.domain.vo.StoreThemeQueryCriteria;
import me.zhengjie.modules.store.mapper.StoreThemeMapper;
import me.zhengjie.utils.ModelMapperUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import me.zhengjie.utils.PageUtil;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

import me.zhengjie.utils.PageResult;

/**
 * @description 服务实现
 * @author eleven
 * @date 2024-03-16
 **/
@Service
@RequiredArgsConstructor
public class StoreThemeServiceImpl extends ServiceImpl<StoreThemeMapper, StoreTheme> implements StoreThemeService {

    private final StoreThemeMapper storeThemeMapper;

    private final StoreMapper storeMapper;

    private final ThemeMapper themeMapper;

    @Override
    public PageResult<StoreThemeVo> pageStoreTheme(StoreThemeQueryCriteria criteria, Page<Object> page){

        Page<StoreTheme> dbPage = new Page<>(page.getCurrent(), page.getSize());
        dbPage = storeThemeMapper.selectPage(dbPage, Wrappers.lambdaQuery(StoreTheme.class));
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
    public List<StoreTheme> queryAll(StoreThemeQueryCriteria criteria){
        return storeThemeMapper.findAll(criteria);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(StoreTheme resources) {
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