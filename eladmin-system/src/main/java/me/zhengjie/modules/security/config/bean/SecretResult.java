package me.zhengjie.modules.security.config.bean;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author eleven
 */
@Data
public class SecretResult {

    @ApiModelProperty(value = "是否有效")
    private boolean valid;

    @ApiModelProperty(value = "身份标识 | 1管理员，2 员工")
    private int identityType;

    @ApiModelProperty(value = "tokenId")
    private String tokenId;

    @ApiModelProperty(value = "开放的网站类型")
    private List<String> webType = new ArrayList<>();

}
