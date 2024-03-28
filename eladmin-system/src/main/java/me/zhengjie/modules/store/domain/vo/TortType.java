package me.zhengjie.modules.store.domain.vo;

/**
 * 侵权类型
 * @author eleven
 */
public enum TortType {

    PLATFORM_SAMPLING_TORT(1, "知产平台治理-一般侵权"),
    INTELLECTUAL_PROPERTY_TORT(2, "知识产权-一般侵权"),
    FROZEN(3, "资金冻结"),
    SERIOUS(4, "严重侵权");

    int tortType;

    String description;

    TortType(Integer tortType, String description) {
        this.tortType = tortType;
        this.description = description;
    }
}
