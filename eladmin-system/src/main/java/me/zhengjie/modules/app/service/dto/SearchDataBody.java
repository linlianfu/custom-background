package me.zhengjie.modules.app.service.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author eleven
 */
@Data
public class SearchDataBody {

    @ApiModelProperty(value = "网站id")
    private String websiteId;

    @ApiModelProperty(value = "搜索结果地址")
    private String searchUrl;

    @ApiModelProperty(value = "搜索关键字")
    private String keyword;

    @ApiModelProperty(value = "图片数据")
    private List<ImageDataDto> images;
}
