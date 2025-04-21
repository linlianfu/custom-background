package me.zhengjie.modules.security.config.bean;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author eleven
 */
@Data
public class LoginRequest {

    @ApiModelProperty(value = "登录token")
    String token;

    @ApiModelProperty(value = "登录设备")
    String deviceNumber;
}
