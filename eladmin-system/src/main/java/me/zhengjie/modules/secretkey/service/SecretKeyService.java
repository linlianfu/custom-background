package me.zhengjie.modules.secretkey.service;

import me.zhengjie.modules.secretkey.service.dto.SecretKeyDto;

/**
 * @author eleven
 */
public interface SecretKeyService {

    /**
     * 获取唯一的密钥数据
     * @param secretKey
     * @param deviceNumber
     * @return
     */
    SecretKeyDto getSecretKey(String secretKey, String deviceNumber);
}
