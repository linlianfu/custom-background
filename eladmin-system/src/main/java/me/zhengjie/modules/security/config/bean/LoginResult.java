package me.zhengjie.modules.security.config.bean;

import lombok.Data;
import me.zhengjie.modules.website.service.dto.ImageParseVo;
import me.zhengjie.modules.website.service.dto.WebsiteVo;

import java.util.ArrayList;
import java.util.List;

/**
 * 爬虫登录解雇哦
 * @author eleven
 */
@Data
public class LoginResult {

    /**
     * 是否成功
     */
    private boolean success;
    /**
     * 身份标识 | 1管理员，2 员工
     */
    private int identityType;

    /**
     * 网站类型
     */
    private List<WebsiteVo> website;
    /**
     * 授权的图片解析资源
     */
    private List<ImageParseVo> authImageParseResource = new ArrayList<>();

}
