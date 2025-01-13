package me.zhengjie.modules.secretkey.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.secretkey.domain.SecretKey;
import me.zhengjie.modules.secretkey.domain.vo.SecretKeyCriteria;
import me.zhengjie.modules.secretkey.domain.vo.SecretKeyVo;
import me.zhengjie.modules.secretkey.mapper.SecretKeyMapper;
import me.zhengjie.modules.secretkey.service.SecretKeyService;
import me.zhengjie.modules.secretkey.service.dto.SecretKeyDto;
import me.zhengjie.utils.ModelMapperUtils;
import me.zhengjie.utils.PageResult;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

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

    @Override
    public PageResult<SecretKeyVo> queryAll(SecretKeyCriteria criteria, Page<Object> page) {
        Page<SecretKey> dbPage = new Page<>(page.getCurrent(), page.getSize());
        LambdaQueryWrapper<SecretKey> wrapper = Wrappers.lambdaQuery(SecretKey.class);
        if (StringUtils.isNotBlank(criteria.getName())) {
            wrapper.like(SecretKey::getName, criteria.getName());
        }
        if (StringUtils.isNotBlank(criteria.getSecretKey())) {
            wrapper.like(SecretKey::getSecretKey, criteria.getSecretKey());
        }
        if (StringUtils.isNotBlank(criteria.getDeviceNumber())) {
            wrapper.like(SecretKey::getDeviceNumber, criteria.getDeviceNumber());
        }
        dbPage = secretKeyMapper.selectPage(
                dbPage, wrapper.orderByDesc(SecretKey::getCreateTime)
        );

        List<SecretKeyVo> list = ModelMapperUtils.mapList(dbPage.getRecords(), SecretKeyVo.class);

        return PageUtil.toPage(list, dbPage.getTotal());
    }
}
