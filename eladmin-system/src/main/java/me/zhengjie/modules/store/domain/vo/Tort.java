package me.zhengjie.modules.store.domain.vo;

/**
 * 是否侵权
 *
 * @author eleven
 */
public enum Tort {

    NO(0, "否"),
    YES(1, "是");

    int value;

    String description;

    Tort(int value, String description) {
        this.value = value;
        this.description = description;
    }
}
