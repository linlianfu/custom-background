package me.zhengjie.modules.security.config.bean;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import me.zhengjie.modules.website.service.dto.ImageParseVo;
import me.zhengjie.modules.website.service.dto.WebsiteVo;

import java.util.ArrayList;
import java.util.List;

/**
 * 爬虫登录
 * @author eleven
 */
@Data
public class LoginResult {

    @ApiModelProperty(value = "登录是否成功")
    private boolean success;

    @ApiModelProperty(value = "身份标识 | 1管理员，2 员工")
    private int identityType;

    @ApiModelProperty(value = "已授权的网站")
    private List<WebsiteVo> website;

    @ApiModelProperty(value = "授权的图片解析资源 | 代码替换")
    private List<ImageParseVo> authImageParseResource = new ArrayList<>();

}
