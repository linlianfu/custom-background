package me.zhengjie.modules.secretkey.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.zhengjie.modules.secretkey.domain.vo.SecretKeyCriteria;
import me.zhengjie.modules.secretkey.domain.vo.SecretKeyRequest;
import me.zhengjie.modules.secretkey.domain.vo.SecretKeyVo;
import me.zhengjie.modules.secretkey.service.dto.SecretKeyDto;
import me.zhengjie.utils.PageResult;

import java.util.List;

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

    /**
     * 分页查询
     *
     * @param criteria 条件
     * @param page     分页参数
     * @return /
     */
    PageResult<SecretKeyVo> queryAll(SecretKeyCriteria criteria, Page<Object> page);


    /**
     * 创建密钥
     * @param request
     * @return
     */
    boolean createSecretKey(SecretKeyRequest request);

    /**
     * 更新密钥
     * @param request
     * @return
     */
    boolean updateSecretKey(SecretKeyRequest request);

    /**
     * 删除id
     * @param id
     * @return
     */
    boolean deleteById(List<String> id);

    /**
     * 更新密钥状态
     * @param id
     */
    void updateStatus(String id);
}
