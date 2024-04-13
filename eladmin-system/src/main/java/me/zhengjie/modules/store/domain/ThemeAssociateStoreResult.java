package me.zhengjie.modules.store.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author eleven
 */
@Data
public class ThemeAssociateStoreResult implements Serializable {
    /**
     * 主题id
     */
    private String themeId;
    /**
     * 店铺数量
     */
    private int count;
}
