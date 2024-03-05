package me.zhengjie.modules.theme.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.theme.domain.Theme;
import me.zhengjie.modules.theme.domain.vo.ThemeQueryCriteria;
import me.zhengjie.modules.theme.service.ThemeService;
import me.zhengjie.utils.PageResult;
import me.zhengjie.utils.PageUtil;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author eleven
 */
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "theme")
public class ThemeServiceImpl implements ThemeService {

    @Override
    public PageResult<Theme> queryAll(ThemeQueryCriteria criteria, Page<Object> page) {
        List<Theme> list = new ArrayList<>();

        Theme item = new Theme();
        item.setKeyword("one piece");
        item.setThemeName("海贼王");
        list.add(item);

        item = new Theme();
        item.setKeyword("ricard");
        item.setThemeName("茴香酒");
        list.add(item);

        item = new Theme();
        item.setKeyword("red bull");
        item.setThemeName("红牛");
        list.add(item);

        item = new Theme();
        item.setKeyword("Browning");
        item.setThemeName("布郎宁");
        list.add(item);

        return PageUtil.toPage(list,100);
    }
}
