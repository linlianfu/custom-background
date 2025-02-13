package me.zhengjie.modules.secretkey.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 网站类型
 *
 * @author eleven
 */
@Getter
@AllArgsConstructor
public enum WebType {

    TP("teepublic"),

    TL("threadless"),

    DB("designbyhumans"),

    AS("artistshot"),

    PI("pinterest"),

    TD("tostadora"),

    TSP("teeshirtpalace");

    String webSite;


    public static WebType getValue(String webSite) {
        for (WebType value : WebType.values()) {
            if (value.getWebSite().equals(webSite))
                return value;
        }
        return null;
    }
}
