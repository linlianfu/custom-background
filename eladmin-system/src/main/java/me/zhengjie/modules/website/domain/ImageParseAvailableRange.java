package me.zhengjie.modules.website.domain;

/**
 * 图片解析地址可用范围
 * @author eleven
 */
public interface ImageParseAvailableRange {

    /**
     * 不限
     */
    int NOT_LIMIT = -1;
    /**
     * 内部人员
     */
    int INTERNAL = 1;
    /**
     * 外部人员
     */
    int EXTERNAL = 2;
}
