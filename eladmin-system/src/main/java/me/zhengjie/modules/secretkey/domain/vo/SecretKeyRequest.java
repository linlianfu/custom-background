package me.zhengjie.modules.secretkey.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author eleven
 */
@Data
public class SecretKeyRequest {
    /**
     * 密钥id
     */
    private String id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "密钥")
    private String secretKey;

    private String deviceNumber;
}
