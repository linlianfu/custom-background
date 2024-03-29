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
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.store.domain.Store;
import me.zhengjie.modules.store.domain.StoreTheme;
import me.zhengjie.modules.store.domain.vo.StoreQueryCriteria;
import me.zhengjie.modules.store.mapper.StoreMapper;
import me.zhengjie.modules.store.mapper.StoreThemeMapper;
import me.zhengjie.modules.store.service.StoreService;
import me.zhengjie.modules.system.service.UserService;
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
    @Autowired
    private UserService userService;
    @Autowired
    private StoreThemeMapper storeThemeMapper;

    @Override
    public PageResult<Store> pageStore(StoreQueryCriteria criteria, Page<Object> page){
        Page<Store> dbPage = new Page<>(page.getCurrent(), page.getSize());
        LambdaQueryWrapper<Store> wrapper = Wrappers.lambdaQuery(Store.class);
        if (!userService.currentUserSuperRole()){
            wrapper.eq(Store::getCreateId,SecurityUtils.getCurrentUserId());
        }
        if (StringUtils.isNotBlank(criteria.getStoreName())){
            wrapper.like(Store::getStoreName,criteria.getStoreName());
        }
        if (StringUtils.isNotBlank(criteria.getLegalPerson())){
            wrapper.like(Store::getLegalPerson,criteria.getLegalPerson());
        }
        if (StringUtils.isNotBlank(criteria.getBusinessLicense())){
            wrapper.like(Store::getBusinessLicense,criteria.getBusinessLicense());
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
        resources.setCreateId(SecurityUtils.getCurrentUserId());
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
        verification(ids);
        removeBatchByIds(ids);
    }

    /**
     * 验证是否有引用关系
     * @return
     */
    public void verification(List<String> idList){
        Long count = storeThemeMapper.selectCount(Wrappers.lambdaQuery(StoreTheme.class)
                .in(StoreTheme::getStoreId, idList)
        );
        if (count >0)
            throw new BadRequestException("店铺有登记主题，无法删除");
    }
}