package me.zhengjie.modules.secretkey.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author eleven
 */
@Data
public class SecretKeyVo {
    @ApiModelProperty(value = "密钥id")
    private String id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "密钥")
    private String secretKey;

    @ApiModelProperty(value = "设备号")
    private String deviceNumber;

    @ApiModelProperty(value = "是否可用")
    private boolean enable;

    @ApiModelProperty(value = "过期时间")
    private Date expirationDate;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;
}
