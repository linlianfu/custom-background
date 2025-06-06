package me.zhengjie.modules.secretkey.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import me.zhengjie.modules.secretkey.domain.IdentityType;
import me.zhengjie.modules.secretkey.service.dto.PermissionGroup;
import me.zhengjie.modules.website.service.dto.ImageParseVo;

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

    @ApiModelProperty(value = "归属 | 1 内部 | 2 外部")
    private int belong;

    @ApiModelProperty(value = "安全对象内容")
    private PermissionGroup permissionGroup;

    /**
     * @see me.zhengjie.modules.secretkey.domain.WebType
     */
    @ApiModelProperty(value = "开放的网站类型")
    private List<String> webType = new ArrayList<>();

    @ApiModelProperty(value = "授权的图片解析资源")
    private List<ImageParseVo> authImageParse =new ArrayList<>();

    @ApiModelProperty(value = "授权的预览图片解析资源")
    private List<ImageParseVo> previewImageParse =new ArrayList<>();

    @ApiModelProperty(value = "设备号")
    private String deviceNumber;

    @ApiModelProperty(value = "是否可用")
    private boolean enable;

    @ApiModelProperty(value = "过期时间")
    private Date expirationDate;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "最后登录时间")
    private Date lastLoginTime;
}
