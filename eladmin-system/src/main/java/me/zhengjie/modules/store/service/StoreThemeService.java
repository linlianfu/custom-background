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
package me.zhengjie.modules.store.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import me.zhengjie.modules.store.domain.StoreTheme;
import me.zhengjie.modules.store.domain.vo.StoreThemeQueryCriteria;
import me.zhengjie.modules.store.domain.vo.StoreThemeVo;
import me.zhengjie.utils.PageResult;

import java.util.List;

/**
 * @description 服务接口
 * @author eleven
 * @date 2024-03-16
 **/
public interface StoreThemeService extends IService<StoreTheme> {

    /**
     * 查询数据分页
     * @param criteria 条件
     * @param page 分页参数
     * @return PageResult
     */
    PageResult<StoreThemeVo> pageStoreTheme(StoreThemeQueryCriteria criteria, Page<Object> page);

    /**
     * 获取店铺主题
     * @return
     */
    StoreThemeVo getStoreTheme(String id);

    /**
     * 查询所有数据不分页
     * @param criteria 条件参数
     * @return List<StoreThemeDto>
     */
    List<StoreTheme> queryAll(StoreThemeQueryCriteria criteria);

    /**
     * 创建
     * @param resources /
     */
    void create(StoreTheme resources);

    /**
     * 编辑
     * @param resources /
     */
    void update(StoreTheme resources);

    /**
     * 多选删除
     * @param ids /
     */
    void deleteAll(List<String> ids);
}