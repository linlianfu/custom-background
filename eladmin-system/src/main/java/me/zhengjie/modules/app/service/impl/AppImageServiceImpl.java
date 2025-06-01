package me.zhengjie.modules.app.service.impl;

import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.app.service.IAppImageService;
import me.zhengjie.modules.app.service.dto.DownloadResult;
import me.zhengjie.modules.app.service.dto.ImageDataDto;
import me.zhengjie.modules.app.service.dto.SearchDataBody;
import me.zhengjie.modules.secretkey.domain.SecretKey;
import me.zhengjie.modules.secretkey.mapper.SecretKeyMapper;
import me.zhengjie.modules.website.domain.ImageDataInfo;
import me.zhengjie.modules.website.domain.UserDownloadRecord;
import me.zhengjie.modules.website.mapper.ImageDataInfoMapper;
import me.zhengjie.modules.website.mapper.UserDownloadRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author eleven
 */
@Service
@RequiredArgsConstructor
public class AppImageServiceImpl implements IAppImageService {

    @Autowired
    private SecretKeyMapper secretKeyMapper;

    @Autowired
    private UserDownloadRecordMapper userDownloadRecordMapper;
    @Autowired
    private ImageDataInfoMapper imageDataInfoMapper;

    @Override
    public DownloadResult downloadImage(SearchDataBody searchDataBody) {
        DownloadResult downloadResult = new DownloadResult();
        SecretKey token = secretKeyMapper.selectById(searchDataBody.getTokenId());
        if (token == null){
            downloadResult.setCode("501");
            downloadResult.setMessage("token不存在");
            return downloadResult;
        }
        if (!token.isEnable()){
            downloadResult.setCode("502");
            downloadResult.setMessage("token停用");
            return downloadResult;
        }

        downloadResult.setSuccess(true);

        UserDownloadRecord downloadRecord = createDownloadRecord(searchDataBody);
        createImageDataInfo(searchDataBody,downloadRecord.getId());

        return downloadResult;
    }

    private UserDownloadRecord createDownloadRecord(SearchDataBody searchDataBody){
        UserDownloadRecord record = new UserDownloadRecord();
        record.setKeyword(searchDataBody.getKeyword());
        record.setSearchUrl(searchDataBody.getSearchUrl());
        record.setTokenId(searchDataBody.getTokenId());
        record.setWebsiteId(searchDataBody.getWebsiteId());
        record.setDownloadImageCount(searchDataBody.getImages().size());
        record.setCreateTime(new Date());
        userDownloadRecordMapper.insert(record);
        return record;
    }

    private void createImageDataInfo(SearchDataBody body,String recordId){
        for (ImageDataDto image : body.getImages()) {
            ImageDataInfo info = new ImageDataInfo();
            info.setImageId(image.getImageId());
            info.setImageTitle(image.getTitle());
            info.setImageUrl(image.getDownloadUrl());
            info.setTokenId(body.getTokenId());
            info.setCreateTime(new Date());
            info.setDownloadRecordId(recordId);
            imageDataInfoMapper.insert(info);
        }
    }
}
