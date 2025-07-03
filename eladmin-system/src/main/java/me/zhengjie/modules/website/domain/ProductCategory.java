package me.zhengjie.modules.website.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author eleven
 */
@Data
@TableName("cb_product_category")
public class ProductCategory {

    @TableId(value = "pc_category_id",type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "分类id")
    private String id;

    @TableField(value = "pc_ws_id")
    @ApiModelProperty(value = "网站id")
    private String websiteId;

    @TableField(value = "pc_category_code")
    @ApiModelProperty(value = "分类标识")
    private String categoryCode;

    @TableField(value = "pc_category_name")
    @ApiModelProperty(value = "分类名称")
    private String categoryName;

    @TableField(value = "pc_enable")
    @ApiModelProperty(value = "是否可用")
    private boolean enable;

    @TableField(value = "pc_create_id")
    @ApiModelProperty(value = "创建人")
    private String createId;

    @TableField(value = "pc_create_time")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
}
