package me.zhengjie.modules.secretkey.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import me.zhengjie.modules.secretkey.domain.IdentityType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    /**
     * @see IdentityType
     */
    @ApiModelProperty(value = "身份标识 | 1管理员，2 员工 ")
    private int identityType;

    /**
     * @see me.zhengjie.modules.secretkey.domain.WebType
     */
    @ApiModelProperty(value = "开放的网站类型")
    private List<String> webType = new ArrayList<>();

    @ApiModelProperty(value = "设备号")
    private String deviceNumber;

    @ApiModelProperty(value = "是否可用")
    private boolean enable;

    @ApiModelProperty(value = "过期时间")
    private Date expirationDate;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;
}
