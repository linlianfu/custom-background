package me.zhengjie.modules.security.config.bean;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author eleven
 */
@Data
public class SecretResult {
    /**
     * 是否有效
     */
    private boolean valid;
    /**
     * 身份标识 | 1管理员，2 员工
     */
    private int identityType;
    /**
     * 开放的网站类型
     */
    private List<String> webType = new ArrayList<>();

}
