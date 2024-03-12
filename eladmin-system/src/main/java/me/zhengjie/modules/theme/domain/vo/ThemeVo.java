package me.zhengjie.modules.theme.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author eleven
 */
@Data
public class ThemeVo {

    @ApiModelProperty(value = "主题id")
    private String id;

    @ApiModelProperty(value = "主题名称")
    private String name;

    @ApiModelProperty(value = "关键词")
    private String keyword;

    @ApiModelProperty(value = "主题分类")
    private String categoryId;

    @ApiModelProperty(value = "风险等级 | 1、常规主题  2、一般侵权 3、资金冻结 4、严重侵权")
    private int tortType;

    @ApiModelProperty(value = "流量等级 | 1、普通 2、爆款")
    private int flow;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "创建人")
    private String createdId;

    @ApiModelProperty(value = "创建时间")
    private String createTime;
}
