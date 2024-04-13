package me.zhengjie.modules.theme.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import me.zhengjie.modules.theme.domain.vo.ThemeRequest;

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
    @ApiModelProperty(value = "主题名称(中文)")
    private String name;

    @TableField(value = "th_theme")
    @ApiModelProperty(value = "主题")
    private String theme;

    @TableField(value = "th_label")
    @ApiModelProperty(value = "标签,次关键词")
    private String label;

    @TableField(value = "th_category_id")
    @ApiModelProperty(value = "主题分类")
    private String categoryId;

    /**
     * @see me.zhengjie.modules.store.domain.vo.RiskType
     */
    @TableField(value = "th_risk_type")
    @ApiModelProperty(value = "风险等级 | 1、常规主题  2、一般侵权 3、资金冻结 4、严重侵权")
    private int riskType;

    @TableField(value = "th_flow")
    @ApiModelProperty(value = "流量等级 | 1、普通 2、爆款")
    private int flow;

    @TableField(value = "th_remark")
    @ApiModelProperty(value = "备注")
    private String remark;

    @TableField(value = "th_create_id")
    @ApiModelProperty(value = "创建人")
    private String createId;

    @TableField(value = "th_create_time")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    public void copy(ThemeRequest source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
