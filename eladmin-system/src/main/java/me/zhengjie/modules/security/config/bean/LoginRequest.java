package me.zhengjie.modules.security.config.bean;

import lombok.Data;

/**
 * @author eleven
 */
@Data
public class LoginRequest {

    /**
     * 登录token
     */
    String token;

    /**
     * 登录设备
     */
    String deviceNumber;
}
