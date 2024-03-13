package me.zhengjie.modules.theme.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.zhengjie.modules.system.domain.Job;
import me.zhengjie.modules.system.domain.vo.JobQueryCriteria;
import me.zhengjie.modules.theme.domain.Theme;
import me.zhengjie.modules.theme.domain.vo.ThemeQueryCriteria;
import me.zhengjie.modules.theme.domain.vo.ThemeRequest;
import me.zhengjie.modules.theme.domain.vo.ThemeVo;
import me.zhengjie.utils.PageResult;

import java.util.Set;

/**
 * @author eleven
 */
public interface ThemeService {
    /**
     * 分页查询
     *
     * @param criteria 条件
     * @param page     分页参数
     * @return /
     */
    PageResult<ThemeVo> queryAll(ThemeQueryCriteria criteria, Page<Object> page);

    /**
     * 创建按主题
     * @param request
     * @return
     */
    boolean create(ThemeRequest request);

    /**
     * 删除
     * @param ids /
     */
    void delete(Set<String> ids);
}
