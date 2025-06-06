package me.zhengjie.modules.website.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.website.domain.ImageParse;
import me.zhengjie.modules.website.domain.ImageParseAuth;
import me.zhengjie.modules.website.domain.Website;
import me.zhengjie.modules.website.mapper.ImageParseAuthMapper;
import me.zhengjie.modules.website.mapper.ImageParseMapper;
import me.zhengjie.modules.website.mapper.WebsiteMapper;
import me.zhengjie.modules.website.service.ImageParseAuthService;
import me.zhengjie.modules.website.service.dto.UserImageParseVo;
import me.zhengjie.utils.SecurityUtils;
import me.zhengjie.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author eleven
 */
@Service
@RequiredArgsConstructor
public class ImageParseAuthServiceImpl extends ServiceImpl<ImageParseAuthMapper, ImageParseAuth> implements ImageParseAuthService {
    @Autowired
    private ImageParseAuthMapper mapper;
    @Autowired
    private ImageParseMapper imageParseMapper;
    @Autowired
    private WebsiteMapper websiteMapper;

    @Override
    public boolean authImageParse(String tokenId,int type, List<String> imageParseIds) {
        for (String imageParseId : imageParseIds) {
            ImageParseAuth auth = new ImageParseAuth();
            auth.setTokenId(tokenId);
            auth.setImageParseId(imageParseId);
            auth.setType(type);
            auth.setCreateId(SecurityUtils.getCurrentUserId());
            auth.setCreateTime(new Date());
            save(auth);
        }
        return true;
    }

    @Override
    public boolean cancelAuthImageParseByTokenId(String tokenId) {
        if (StringUtils.isNotBlank(tokenId)){
            mapper.delete(Wrappers.lambdaQuery(ImageParseAuth.class)
                    .eq(ImageParseAuth::getTokenId, tokenId));
        }
        return true;
    }

    @Override
    public List<UserImageParseVo> findAuthImageParse(String tokenId) {
        List<UserImageParseVo> result = new ArrayList<>();
        List<ImageParseAuth> imageParseAuths = mapper.selectList(Wrappers.lambdaQuery(ImageParseAuth.class)
                .eq(ImageParseAuth::getTokenId, tokenId));
        if (CollectionUtils.isNotEmpty(imageParseAuths)){
            List<ImageParse> imageParses = imageParseMapper.selectBatchIds(imageParseAuths.stream()
                    .map(ImageParseAuth::getImageParseId).collect(Collectors.toList()));
            Map<String, ImageParse> imageParseMap = imageParses.stream()
                    .collect(Collectors.toMap(ImageParse::getId, p -> p));


            List<Website> websiteList = websiteMapper.selectBatchIds(imageParses.stream()
                    .map(ImageParse::getWebsiteId).collect(Collectors.toList()));
            Map<String, Website> websiteMap = websiteList.stream().collect(Collectors.toMap(Website::getId, p -> p));
            for (ImageParseAuth imageParseAuth : imageParseAuths) {
                ImageParse imageParse = imageParseMap.get(imageParseAuth.getImageParseId());
                if (imageParse != null){
                    UserImageParseVo vo = new UserImageParseVo();
                    vo.setId(imageParse.getId());
                    vo.setParseName(imageParse.getParseName());
                    vo.setWebsiteId(imageParse.getWebsiteId());
                    Website website = websiteMap.get(imageParse.getWebsiteId());
                    vo.setWebsiteCode(website.getCode());
                    vo.setParseUrl(imageParse.getParseUrl());
                    vo.setType(imageParseAuth.getType());
                    result.add(vo);
                }
            }
        }
        return result;
    }
}
