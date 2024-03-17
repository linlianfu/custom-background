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
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import me.zhengjie.modules.store.domain.Store;
import me.zhengjie.modules.theme.domain.Theme;
import me.zhengjie.modules.theme.domain.vo.ThemeVo;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.zhengjie.modules.store.service.StoreService;
import me.zhengjie.modules.store.domain.vo.StoreQueryCriteria;
import me.zhengjie.modules.store.mapper.StoreMapper;
import me.zhengjie.utils.ModelMapperUtils;
import me.zhengjie.utils.SecurityUtils;
import me.zhengjie.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import me.zhengjie.utils.PageUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import me.zhengjie.utils.PageResult;

/**
 * @description 服务实现
 * @author eleven
 * @date 2024-03-15
 **/
@Service
@RequiredArgsConstructor
public class StoreServiceImpl extends ServiceImpl<StoreMapper, Store> implements StoreService {

    @Autowired
    private StoreMapper storeMapper;

    @Override
    public PageResult<Store> pageStore(StoreQueryCriteria criteria, Page<Object> page){
        Page<Store> dbPage = new Page<>(page.getCurrent(), page.getSize());
        LambdaQueryWrapper<Store> wrapper = Wrappers.lambdaQuery(Store.class);
        if (StringUtils.isNotBlank(criteria.getStoreName())){
            wrapper.like(Store::getStoreName,criteria.getStoreName());
        }
        if (criteria.getStatus() != null){
            wrapper.eq(Store::getStatus,criteria.getStatus());
        }
        dbPage = storeMapper.selectPage(
                dbPage, wrapper.orderByDesc(Store::getCreateTime)
        );
        return PageUtil.toPage(ModelMapperUtils.mapList(dbPage.getRecords(), Store.class),dbPage.getTotal());
    }

    @Override
    public List<Store> queryAll(StoreQueryCriteria criteria){
        return storeMapper.findAll(criteria);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(Store resources) {
        resources.setCreateTime(LocalDateTime.now());
        resources.setCreateId(SecurityUtils.getCurrentUserId()+"");
        save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Store resources) {
        Store store = getById(resources.getId());
        store.copy(resources);
        saveOrUpdate(store);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteAll(List<String> ids) {
        removeBatchByIds(ids);
    }
}