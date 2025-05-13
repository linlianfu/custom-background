package me.zhengjie.modules.website.service.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author eleven
 */
@Data
public class UserImageParseVo extends ImageParseVo{

    @ApiModelProperty(value = "类型 | 1 预览 | 2 下载")
    private int type;
}
