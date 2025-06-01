package me.zhengjie.modules.website.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.website.domain.ImageParse;
import me.zhengjie.modules.website.domain.Website;
import me.zhengjie.modules.website.domain.vo.WebsiteCriteria;
import me.zhengjie.modules.website.domain.vo.WebsiteRequest;
import me.zhengjie.modules.website.mapper.ImageParseMapper;
import me.zhengjie.modules.website.mapper.WebsiteMapper;
import me.zhengjie.modules.website.service.IWebsiteService;
import me.zhengjie.modules.website.service.dto.FieldValueDto;
import me.zhengjie.modules.website.service.dto.WebsiteVo;
import me.zhengjie.utils.ModelMapperUtils;
import me.zhengjie.utils.PageResult;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.SecurityUtils;
import me.zhengjie.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author eleven
 */
@Service
@RequiredArgsConstructor
public class WebsiteServiceImpl extends ServiceImpl<WebsiteMapper, Website> implements IWebsiteService {

    @Autowired
    private WebsiteMapper mapper;
    @Autowired
    private ImageParseMapper imageParseMapper;

    @Override
    public PageResult<WebsiteVo> queryAll(WebsiteCriteria criteria, Page<Object> page) {
        Page<Website> dbPage = new Page<>(page.getCurrent(), page.getSize());
        dbPage = mapper.selectPage(dbPage, buildCriteria(criteria).orderByDesc(Website::getCreateTime));
        List<WebsiteVo> list = ModelMapperUtils.mapList(dbPage.getRecords(), WebsiteVo.class);
        return PageUtil.toPage(list, dbPage.getTotal());
    }

    @Override
    public List<WebsiteVo> listWebsite(WebsiteCriteria criteria) {
        List<WebsiteVo> result = new ArrayList<>();
        List<Website> websiteList = mapper.selectList(buildCriteria(criteria));
        if (CollectionUtils.isNotEmpty(websiteList)){
            result = ModelMapperUtils.mapList(websiteList, WebsiteVo.class);
        }
        return result;
    }

    @Override
    public Website createWebsite(WebsiteRequest request) {
        Website record = new Website();
        record.setCode(request.getCode());
        record.setSiteName(request.getSiteName());
        record.setAddress(request.getAddress());
        record.setPixelLength(request.getPixelLength());
        record.setPixelWidth(request.getPixelWidth());
        record.setCreateId(SecurityUtils.getCurrentUserId());
        record.setCreateTime(new Date());
        save(record);
        return record;
    }

    @Override
    public boolean updateWebsite(WebsiteRequest request) {
        Website website = mapper.selectById(request.getId());
        website.setAddress(request.getAddress());
        website.setSiteName(request.getSiteName());
        website.setCode(request.getCode());
        website.setPixelLength(request.getPixelLength());
        website.setPixelWidth(request.getPixelWidth());
        updateById(website);
        return true;
    }

    @Override
    public boolean deleteById(List<String> id) {
        Long referenceCount = imageParseMapper.selectCount(Wrappers.lambdaQuery(ImageParse.class)
                .in(ImageParse::getWebsiteId, id));
        Assert.isTrue(referenceCount<=0,"当前网站存在图片解析地址，无法删除");
        mapper.deleteBatchIds(id);
        return true;
    }


    @Override
    public List<FieldValueDto> getSortFieldValue(String websiteId) {
        List<FieldValueDto> list = new ArrayList<>();
        FieldValueDto relevant = new FieldValueDto();
        relevant.setTitle("Most Relevant");
        relevant.setValue("relevant");
        relevant.setFieldName("sortOrder");
        relevant.buildExpress();
        list.add(relevant);

        FieldValueDto bestSelling = new FieldValueDto();
        bestSelling.setTitle("Best Selling");
        bestSelling.setValue("top%20selling");
        bestSelling.setFieldName("sortOrder");
        bestSelling.buildExpress();
        list.add(bestSelling);

        FieldValueDto newest = new FieldValueDto();
        newest.setTitle("Newest");
        newest.setValue("recent");
        newest.setFieldName("sortOrder");
        newest.buildExpress();
        list.add(newest);

        return list;
    }

    public LambdaQueryWrapper<Website> buildCriteria(WebsiteCriteria criteria){
        LambdaQueryWrapper<Website> wrapper = Wrappers.lambdaQuery(Website.class);
        if (CollectionUtils.isNotEmpty(criteria.getCodeList())){
            wrapper.in(Website::getCode,criteria.getCodeList());
        }
        if (StringUtils.isNotBlank(criteria.getCode())){
            wrapper.eq(Website::getCode,criteria.getCode());
        }
        if (StringUtils.isNotBlank(criteria.getSiteName())){
            wrapper.like(Website::getSiteName,criteria.getSiteName());
        }

        return wrapper;
    }
}
