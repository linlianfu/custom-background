package me.zhengjie.modules.website.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.zhengjie.modules.website.domain.vo.ImageDataInfoCriteria;
import me.zhengjie.modules.website.service.dto.ImageDataInfoDto;
import me.zhengjie.utils.PageResult;

/**
 * @author eleven
 */
public interface IImageDownloadManagerService {

    /**
     * 分页查询
     *
     * @param criteria 条件
     * @param page     分页参数
     * @return /
     */
    PageResult<ImageDataInfoDto> queryAll(ImageDataInfoCriteria criteria, Page<Object> page);
}
