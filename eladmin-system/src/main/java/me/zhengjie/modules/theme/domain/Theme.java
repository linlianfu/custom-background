package me.zhengjie.modules.theme.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import me.zhengjie.base.BaseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.Date;

/**
 * 主题管理
 * @author eleven
 */
@Data
@TableName("cb_theme")
public class Theme implements Serializable {

    @TableId(value = "th_id",type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "主题id")
    private String id;

    @TableField(value = "th_name")
    @ApiModelProperty(value = "主题名称")
    private String name;

    @TableField(value = "th_keyword")
    @ApiModelProperty(value = "关键词")
    private String keyword;

    @TableField(value = "th_category_id")
    @ApiModelProperty(value = "主题分类")
    private String categoryId;

    @TableField(value = "th_tort_type")
    @ApiModelProperty(value = "风险等级 | 1、常规主题  2、一般侵权 3、资金冻结 4、严重侵权")
    private int tortType;

    @TableField(value = "th_flow")
    @ApiModelProperty(value = "流量等级 | 1、普通 2、爆款")
    private int flow;

    @TableField(value = "th_remark")
    @ApiModelProperty(value = "备注")
    private String remark;

    @TableField(value = "th_create_id")
    @ApiModelProperty(value = "创建人")
    private String createdId;

    @TableField(value = "th_create_time")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
}
