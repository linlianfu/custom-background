package me.zhengjie.modules.website.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author eleven
 */
@Data
public class WebsiteCriteria {

    /**
     * 网站编码
     */
    private List<String> codeList;

    @ApiModelProperty(value = "网站名称")
    private String siteName;

    @ApiModelProperty(value = "网站标识")
    private String code;
}
