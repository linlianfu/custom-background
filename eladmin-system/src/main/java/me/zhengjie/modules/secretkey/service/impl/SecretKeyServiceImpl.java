package me.zhengjie.modules.secretkey.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.secretkey.domain.SecretKey;
import me.zhengjie.modules.secretkey.mapper.SecretKeyMapper;
import me.zhengjie.modules.secretkey.service.SecretKeyService;
import me.zhengjie.modules.secretkey.service.dto.SecretKeyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author eleven
 */
@Service
@RequiredArgsConstructor
public class SecretKeyServiceImpl extends ServiceImpl<SecretKeyMapper, SecretKey> implements SecretKeyService {
    @Autowired
    private SecretKeyMapper secretKeyMapper;

    @Override
    public SecretKeyDto getSecretKey(String secretKey, String deviceNumber) {
        Assert.hasText(secretKey,"密钥不能为空");
        Assert.hasText(deviceNumber,"设备号不能为空");

        SecretKey key = secretKeyMapper.selectOne(Wrappers.lambdaQuery(SecretKey.class)
                .eq(SecretKey::getSecretKey, secretKey)
                .eq(SecretKey::getDeviceNumber, deviceNumber)
        );
        if (key == null)
            return null;

        SecretKeyDto dto = new SecretKeyDto();
        dto.setId(key.getId());
        dto.setName(key.getName());
        dto.setSecretKey(key.getSecretKey());
        dto.setDeviceNumber(key.getDeviceNumber());
        dto.setEnable(key.isEnable());
        dto.setExpirationDate(key.getExpirationDate());
        return dto;
    }
}
