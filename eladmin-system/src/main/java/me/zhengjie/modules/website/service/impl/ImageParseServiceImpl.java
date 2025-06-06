package me.zhengjie.modules.website.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.website.domain.ImageParse;
import me.zhengjie.modules.website.domain.ImageParseAuth;
import me.zhengjie.modules.website.domain.Website;
import me.zhengjie.modules.website.domain.vo.ImageParseCriteria;
import me.zhengjie.modules.website.domain.vo.ImageParseRequest;
import me.zhengjie.modules.website.mapper.ImageParseAuthMapper;
import me.zhengjie.modules.website.mapper.ImageParseMapper;
import me.zhengjie.modules.website.mapper.WebsiteMapper;
import me.zhengjie.modules.website.service.ImageParseService;
import me.zhengjie.modules.website.service.dto.ImageParseVo;
import me.zhengjie.utils.ModelMapperUtils;
import me.zhengjie.utils.PageResult;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.SecurityUtils;
import me.zhengjie.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author eleven
 */
@Service
@RequiredArgsConstructor
public class ImageParseServiceImpl extends ServiceImpl<ImageParseMapper, ImageParse> implements ImageParseService {

    @Autowired
    private ImageParseMapper mapper;
    @Autowired
    private WebsiteMapper websiteMapper;
    @Autowired
    private ImageParseAuthMapper imageParseAuthMapper;

    @Override
    public PageResult<ImageParseVo> queryAll(ImageParseCriteria criteria, Page<Object> page) {
        LambdaQueryWrapper<ImageParse> wrapper = Wrappers.lambdaQuery(ImageParse.class);

        if (StringUtils.isNotBlank(criteria.getParseName())){
            wrapper.like(ImageParse::getParseName,criteria.getParseName());
        }
        if(StringUtils.isNotBlank(criteria.getWebsiteId())){
            wrapper.eq(ImageParse::getWebsiteId,criteria.getWebsiteId());
        }
        Page<ImageParse> dbPage = new Page<>(page.getCurrent(), page.getSize());
        dbPage = mapper.selectPage(dbPage, wrapper.orderByDesc(ImageParse::getCreateTime));
        List<ImageParseVo> list = ModelMapperUtils.mapList(dbPage.getRecords(), ImageParseVo.class);
        if (CollectionUtils.isNotEmpty(list)){
            List<Website> websites = websiteMapper.selectBatchIds(list.stream().map(ImageParseVo::getWebsiteId)
                    .collect(Collectors.toList()));
            Map<String, String> websiteMap = websites.stream().collect(
                    Collectors.toMap(Website::getId, Website::getCode)
            );
            list.forEach(p->{
                p.setWebsiteCode(websiteMap.get(p.getWebsiteId()));
                Long authUserCount = imageParseAuthMapper.selectCount(Wrappers.lambdaQuery(ImageParseAuth.class)
                        .in(ImageParseAuth::getImageParseId, p.getId()));
                p.setAuthUserCount(Integer.parseInt(authUserCount.toString()));
            });
        }
        return PageUtil.toPage(list, dbPage.getTotal());
    }


    @Override
    public List<ImageParseVo> listImageParse(ImageParseCriteria criteria) {
        LambdaQueryWrapper<ImageParse> wrapper = Wrappers.lambdaQuery(ImageParse.class);
        List<ImageParse> imageParses = mapper.selectList(wrapper);
        List<ImageParseVo> list = ModelMapperUtils.mapList(imageParses, ImageParseVo.class);
        if (CollectionUtils.isNotEmpty(list)){
            List<Website> websites = websiteMapper.selectBatchIds(list.stream().map(ImageParseVo::getWebsiteId)
                    .collect(Collectors.toList()));
            Map<String, String> websiteMap = websites.stream().collect(
                    Collectors.toMap(Website::getId, Website::getCode)
            );
            list.forEach(p-> {
                p.setWebsiteCode(websiteMap.get(p.getWebsiteId()));
                p.setParseUrl(null);
            });
        }
        return list;
    }

    @Override
    public ImageParse createImageParse(ImageParseRequest request) {
        Assert.isTrue(request.getParseUrl().contains("%imageId%"),"缺少图片id占位符，请按照要求格式调整");
        ImageParse bean = new ImageParse();
        bean.setWebsiteId(request.getWebsiteId());
        bean.setParseName(request.getParseName());
        bean.setParseUrl(request.getParseUrl());
        bean.setParseType(request.getParseType());
        bean.setAvailableRange(request.getAvailableRange());
        bean.setCreateId(SecurityUtils.getCurrentUserId());
        bean.setCreateTime(new Date());
        save(bean);
        return bean;
    }

    @Override
    public boolean updateImageParse(ImageParseRequest request) {
        Assert.isTrue(request.getParseUrl().contains("%imageId%"),"缺少图片id占位符，请按照要求格式调整");
        ImageParse imageParse = mapper.selectById(request.getId());
        imageParse.setParseName(request.getParseName());
        imageParse.setParseUrl(request.getParseUrl());
        imageParse.setWebsiteId(request.getWebsiteId());
        imageParse.setParseType(request.getParseType());
        imageParse.setAvailableRange(request.getAvailableRange());
        updateById(imageParse);
        return true;
    }

    @Override
    public boolean deleteById(List<String> id) {
        Long count = imageParseAuthMapper.selectCount(Wrappers.lambdaQuery(ImageParseAuth.class)
                .in(ImageParseAuth::getImageParseId, id));
        Assert.isTrue(count<=0,"当前图片解析地址存在授权记录，无法删除");
        mapper.deleteBatchIds(id);
        return false;
    }
}
