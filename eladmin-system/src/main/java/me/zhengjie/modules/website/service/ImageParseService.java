package me.zhengjie.modules.website.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.zhengjie.modules.website.domain.ImageParse;
import me.zhengjie.modules.website.domain.vo.ImageParseCriteria;
import me.zhengjie.modules.website.domain.vo.ImageParseRequest;
import me.zhengjie.modules.website.service.dto.ImageParseVo;
import me.zhengjie.utils.PageResult;

import java.util.List;

/**
 * 图片解析地址配置
 * @author eleven
 */
public interface ImageParseService {

    /**
     * 分页查询
     *
     * @param criteria 条件
     * @param page     分页参数
     * @return /
     */
    PageResult<ImageParseVo> queryAll(ImageParseCriteria criteria, Page<Object> page);

    /**
     * 查询图片解析地址
     * @param criteria
     * @return
     */
    List<ImageParseVo> listImageParse(ImageParseCriteria criteria);

    /**
     * 创建图片解析配置
     * @param request
     * @return
     */
    ImageParse createImageParse(ImageParseRequest request);

    /**
     * 更新图片解析
     * @param request
     * @return
     */
    boolean updateImageParse(ImageParseRequest request);

    /**
     * 删除id
     * @param id
     * @return
     */
    boolean deleteById(List<String> id);
}
