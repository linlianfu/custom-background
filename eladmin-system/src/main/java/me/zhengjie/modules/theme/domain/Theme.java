package me.zhengjie.modules.theme.domain;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import me.zhengjie.base.BaseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

/**
 * @author eleven
 */
@Data
public class Theme extends BaseEntity implements Serializable {

    @ApiModelProperty(value = "主题id")
    private Long id;

    @ApiModelProperty(value = "主题名称")
    private String themeName;

    @ApiModelProperty(value = "关键词")
    private String keyword;
}
