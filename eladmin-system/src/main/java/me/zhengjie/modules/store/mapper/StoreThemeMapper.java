package me.zhengjie.modules.store.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.zhengjie.modules.store.domain.StoreTheme;
import me.zhengjie.modules.store.domain.StoreThemeDetail;
import me.zhengjie.modules.store.domain.ThemeAssociateStoreResult;
import me.zhengjie.modules.store.domain.vo.StoreThemeQueryCriteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author eleven
 * @date 2024-03-16
 **/
@Mapper
public interface StoreThemeMapper extends BaseMapper<StoreTheme> {

    IPage<StoreThemeDetail> pageStoreTheme(@Param("criteria") StoreThemeQueryCriteria criteria, Page<Object> page);

    /**
     * 统计主题关联的店铺数量
     * @param criteria
     * @return
     */
    List<ThemeAssociateStoreResult> countThemeAssociateStoreGroupByTheme(@Param("criteria")StoreThemeQueryCriteria criteria);
}