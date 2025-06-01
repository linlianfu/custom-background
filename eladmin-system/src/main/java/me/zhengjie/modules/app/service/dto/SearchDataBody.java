package me.zhengjie.modules.app.service.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author eleven
 */
@Data
public class SearchDataBody {

    @ApiModelProperty(value = "当前登录着tokenId,从登录结果获取")
    private String tokenId;

    @ApiModelProperty(value = "网站id")
    private String websiteId;

    @ApiModelProperty(value = "搜索结果地址")
    private String searchUrl;

    @ApiModelProperty(value = "分类id，RDB的搜索参数")
    private String categoryId;

    @ApiModelProperty(value = "搜索关键字")
    private String keyword;

    @ApiModelProperty(value = "图片数据")
    private List<ImageDataDto> images;
}
