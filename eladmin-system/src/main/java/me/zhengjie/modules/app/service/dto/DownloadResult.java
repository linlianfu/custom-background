package me.zhengjie.modules.app.service.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author eleven
 */
@Data
public class DownloadResult {

    @ApiModelProperty(value = "下载是否成功 | true 可以下载 | false 则不允许下载")
    private boolean success;

    /**
     *  下载结果响应码 | 200 可以下载 | 501 token 不存在 | 502 token停用，不允许下载
     */
    @ApiModelProperty(value = "下载结果响应码")
    private String code;

    @ApiModelProperty(value = "错误提示语，用于前端消息提示")
    private String message;
}
