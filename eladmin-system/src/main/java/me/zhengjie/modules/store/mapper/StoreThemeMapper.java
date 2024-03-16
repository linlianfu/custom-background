package me.zhengjie.modules.store.mapper;

import me.zhengjie.modules.store.domain.StoreTheme;
import me.zhengjie.modules.store.domain.vo.StoreThemeQueryCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author eleven
 * @date 2024-03-16
 **/
@Mapper
public interface StoreThemeMapper extends BaseMapper<StoreTheme> {

    IPage<StoreTheme> findAll(@Param("criteria") StoreThemeQueryCriteria criteria, Page<Object> page);

    List<StoreTheme> findAll(@Param("criteria") StoreThemeQueryCriteria criteria);
}