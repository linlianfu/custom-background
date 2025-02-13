package me.zhengjie.modules.secretkey.service.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author eleven
 */
@Data
public class SecretKeyDto {
    /**
     * 密钥id
     */
    private String id;
    /**
     * 名称
     */
    private String name;
    /**
     * 密钥
     */
    private String secretKey;

    /**
     * 身份标识 | 1管理员，2 员工
     */
    private int identityType;
    /**
     *设备号
     */
    private String deviceNumber;
    /**
     * 是否可用
     */
    private boolean enable;
    /**
     *
     */
    private Date expirationDate;

    /**
     * 开放的网站类型
     */
    private List<String> webType = new ArrayList<>();
}
