package me.zhengjie.modules.store.domain.vo;

/**
 * 侵权类型
 * @author eleven
 */
public enum TortType {

    NORMAL(1, "常规主题"),
    GENERAL_TORT(2, "一般侵权"),
    FROZEN(3, "资金冻结"),
    SERIOUS(4, "严重侵权");


    int tortType;

    String description;

    TortType(Integer tortType, String description) {
        this.tortType = tortType;
        this.description = description;
    }
}
