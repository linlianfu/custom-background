package me.zhengjie.modules.store.domain.vo;

/**
 *
 * 风险等级
 * @author eleven
 */
public enum RiskType {

    NORMAL(1, "常规主题"),
    GENERAL_TORT(2, "一般侵权"),
    FROZEN(3, "资金冻结"),
    SERIOUS(4, "严重侵权");

    int riskType;

    String description;

    RiskType(Integer riskType, String description) {
        this.riskType = riskType;
        this.description = description;
    }
}
