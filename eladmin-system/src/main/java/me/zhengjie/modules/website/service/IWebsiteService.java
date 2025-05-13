package me.zhengjie.modules.website.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.zhengjie.modules.website.domain.Website;
import me.zhengjie.modules.website.domain.vo.WebsiteCriteria;
import me.zhengjie.modules.website.domain.vo.WebsiteRequest;
import me.zhengjie.modules.website.service.dto.WebsiteVo;
import me.zhengjie.utils.PageResult;

import java.util.List;

/**
 * @author eleven
 */
public interface IWebsiteService {


    /**
     * 分页查询
     *
     * @param criteria 条件
     * @param page     分页参数
     * @return /
     */
    PageResult<WebsiteVo> queryAll(WebsiteCriteria criteria, Page<Object> page);

    /**
     * 网站查询
     *
     * @param criteria 条件
     * @return /
     */
    List<WebsiteVo> listWebsite(WebsiteCriteria criteria);

    /**
     * 创建网站
     * @param request
     * @return
     */
    Website createWebsite(WebsiteRequest request);

    /**
     * 更新网站
     * @param request
     * @return
     */
    boolean updateWebsite(WebsiteRequest request);

    /**
     * 删除id
     * @param id
     * @return
     */
    boolean deleteById(List<String> id);
}
