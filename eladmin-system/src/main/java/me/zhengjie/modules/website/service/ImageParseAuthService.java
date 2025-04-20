package me.zhengjie.modules.website.service;

import me.zhengjie.modules.website.service.dto.ImageParseVo;

import java.util.List;

/**
 * @author eleven
 */
public interface ImageParseAuthService {

    /**
     *  授权图片解析地址
     * @param tokenId
     * @param imageParseIds
     * @return
     */
    boolean authImageParse(String tokenId, List<String> imageParseIds);

    /**
     * 取消某个token用户的图片解析配置
     * @param tokenId
     * @return
     */
    boolean cancelAuthImageParseByTokenId(String tokenId);

    /**
     * 查询指定用户已授权的图片解析资源
     * @param tokenId
     * @return
     */
    List<ImageParseVo> findAuthImageParse(String tokenId);
}
