package me.zhengjie.modules.secretkey.service.dto;

import lombok.Data;

import java.util.Date;

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
}
