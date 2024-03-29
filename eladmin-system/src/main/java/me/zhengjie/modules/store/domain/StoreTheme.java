/*
 *  Copyright 2019-2023 Zheng Jie
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package me.zhengjie.modules.store.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import me.zhengjie.modules.store.domain.vo.Tort;
import me.zhengjie.modules.store.domain.vo.TortType;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @description /
 * @author eleven
 * @date 2024-03-16
 **/
@Data
@TableName("cb_store_theme")
public class StoreTheme implements Serializable {

    @TableId(value = "id",type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "id")
    private String id;

    @TableField("sth_st_id")
    @ApiModelProperty(value = "店铺id")
    private String storeId;

    @TableField("sth_th_id")
    @ApiModelProperty(value = "主题id")
    private String themeId;

    @TableField("sth_up_time")
    @ApiModelProperty(value = "上架时间")
    private LocalDate upTime;

    @TableField("sth_product_count")
    @ApiModelProperty(value = "上架产品数量")
    private Integer productCount;

    /**
     * @see Tort
     */
    @TableField("sth_tort")
    @ApiModelProperty(value = "是否侵权")
    private Integer tort;

    /**
     * @see TortType
     */
    @TableField("sth_tort_type")
    @ApiModelProperty(value = "侵权类型")
    private Integer tortType;

    @TableField("sth_tort_time")
    @ApiModelProperty(value = "侵权时间")
    private LocalDate tortTime;

    @TableField("sth_intellectual_property_name")
    @ApiModelProperty(value = "知识产权名称")
    private String intellectualPropertyName;

    @TableField("sth_remark")
    @ApiModelProperty(value = "备注")
    private String remark;

    @TableField("sth_create_id")
    @ApiModelProperty(value = "创建人")
    private String createId;

    @TableField("sth_create_time")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    public void copy(StoreTheme source){
        BeanUtil.copyProperties(source,this, CopyOptions.create());
    }
}
