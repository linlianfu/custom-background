package me.zhengjie.modules.website.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.secretkey.domain.SecretKey;
import me.zhengjie.modules.secretkey.mapper.SecretKeyMapper;
import me.zhengjie.modules.website.domain.ImageDataInfo;
import me.zhengjie.modules.website.domain.UserDownloadRecord;
import me.zhengjie.modules.website.domain.Website;
import me.zhengjie.modules.website.domain.vo.ImageDataInfoCriteria;
import me.zhengjie.modules.website.mapper.ImageDataInfoMapper;
import me.zhengjie.modules.website.mapper.UserDownloadRecordMapper;
import me.zhengjie.modules.website.mapper.WebsiteMapper;
import me.zhengjie.modules.website.service.IImageDownloadManagerService;
import me.zhengjie.modules.website.service.dto.ImageDataInfoDto;
import me.zhengjie.modules.website.service.dto.WebsiteVo;
import me.zhengjie.utils.ModelMapperUtils;
import me.zhengjie.utils.PageResult;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author eleven
 */
@Service
@RequiredArgsConstructor
public class ImageDownloadManagerServiceImpl extends ServiceImpl<UserDownloadRecordMapper, UserDownloadRecord>
        implements IImageDownloadManagerService {
    @Autowired
    private UserDownloadRecordMapper mapper;
    @Autowired
    private ImageDataInfoMapper imageDataInfoMapper;
    @Autowired
    private SecretKeyMapper secretKeyMapper;
    @Autowired
    private WebsiteMapper websiteMapper;


    @Override
    public PageResult<ImageDataInfoDto> queryAll(ImageDataInfoCriteria criteria, Page<Object> page) {
        Page<ImageDataInfo> dbPage = new Page<>(page.getCurrent(), page.getSize());
        LambdaQueryWrapper<ImageDataInfo> wrapper = Wrappers.lambdaQuery(ImageDataInfo.class);
        if (StringUtils.isNotBlank(criteria.getWebsiteId())){
            wrapper.eq(ImageDataInfo::getWebsiteId,criteria.getWebsiteId());
        }

        if (StringUtils.isNotBlank(criteria.getTokenId())){
            wrapper.eq(ImageDataInfo::getTokenId,criteria.getTokenId());
        }
        dbPage = imageDataInfoMapper.selectPage(dbPage, wrapper.orderByDesc(ImageDataInfo::getCreateTime));
        List<ImageDataInfo> records = dbPage.getRecords();
        List<ImageDataInfoDto> list = ModelMapperUtils.mapList(records,ImageDataInfoDto.class);
        if (CollectionUtils.isEmpty(list)){
            return PageUtil.toPage(list, dbPage.getTotal());
        }
        Set<String> tokenIdSet = list.stream().map(ImageDataInfoDto::getTokenId).collect(Collectors.toSet());
        Map<String, String> websiteIdMap = records.stream().collect(
                Collectors.toMap(ImageDataInfo::getId, ImageDataInfo::getWebsiteId));

        List<SecretKey> secretKeys = secretKeyMapper.selectBatchIds(tokenIdSet);
        Map<String, String> userMap = secretKeys.stream().collect(Collectors.toMap(SecretKey::getId, SecretKey::getName));
        for (ImageDataInfoDto imageDataInfoDto : list) {
            imageDataInfoDto.setUserName(userMap.get(imageDataInfoDto.getTokenId()));
            WebsiteVo website = new WebsiteVo();
            website.setId(websiteIdMap.get(imageDataInfoDto.getId()));
            Website website1 = websiteMapper.selectById(website.getId());
            website.setCode(website1.getCode());
            website.setSiteName(website1.getSiteName());
            imageDataInfoDto.setWebsite(website);
        }
        return PageUtil.toPage(list, dbPage.getTotal());
    }
}
