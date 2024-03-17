package me.zhengjie.modules.store.domain.vo;

/**
 * 侵权类型
 * @author eleven
 */
public enum TortType {

    NORMAL(1, "常规主题"),
    PLATFORM_SAMPLING_TORT(2, "知产平台治理-一般侵权"),
    INTELLECTUAL_PROPERTY_TORT(3, "知识产权-一般侵权"),
    FROZEN(4, "资金冻结"),
    SERIOUS(5, "严重侵权");

    int tortType;

    String description;

    TortType(Integer tortType, String description) {
        this.tortType = tortType;
        this.description = description;
    }
}
