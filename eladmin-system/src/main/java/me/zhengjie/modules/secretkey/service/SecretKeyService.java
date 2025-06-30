package me.zhengjie.modules.secretkey.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.zhengjie.modules.secretkey.domain.SecurityObject;
import me.zhengjie.modules.secretkey.domain.vo.SecretKeyCriteria;
import me.zhengjie.modules.secretkey.domain.vo.SecretKeyRequest;
import me.zhengjie.modules.secretkey.domain.vo.SecretKeyVo;
import me.zhengjie.modules.secretkey.service.dto.PermissionGroup;
import me.zhengjie.modules.secretkey.service.dto.SecretKeyDto;
import me.zhengjie.utils.PageResult;

import java.util.Date;
import java.util.List;

/**
 * @author eleven
 */
public interface SecretKeyService {

    /**
     * 获取唯一的Token
     * @param token
     * @return
     */
    SecretKeyDto getToken(String token);
    /**
     * 获取唯一的密钥数据
     * @param secretKey
     * @param deviceNumber
     * @return
     */
    SecretKeyDto getSecretKey(String secretKey, String deviceNumber);

    /**
     * token绑定设备
     * @param token
     * @param deviceNumber
     * @return
     */
    boolean bindToken(String token,String deviceNumber);
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


    /**
     * 根据token获取对应的网站类型
     * @param token
     * @return
     */
    List<String> getWebTypeByToken(String tokenId);


    /**
     * 更新用户最后登录时间
     * @param tokenId
     * @param loginDate
     * @return
     */
    boolean updateLastLoginTime(String tokenId, Date loginDate);


    /**
     * 获取指定用户的权限组
     * @param tokenId
     * @param type
     * @see me.zhengjie.modules.secretkey.domain.SecurityObjectType
     * @return
     */
    List<SecurityObject> getPermissionGroup(String tokenId,int type);

    /**
     * 转换安全对象
     * @param securityObjectList
     * @return
     */
     PermissionGroup convertSecurityObject(List<SecurityObject> securityObjectList);


    /**
     * 重置设备号
     * @param tokenId
     * @return
     */
     boolean resetDeviceNumber(String tokenId);
}
