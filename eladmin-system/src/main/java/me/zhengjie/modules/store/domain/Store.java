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

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @description /
 * @author eleven
 * @date 2024-03-15
 **/
@Data
@TableName("cb_store")
public class Store implements Serializable {

    @TableId(value = "st_id",type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "店铺 id")
    private String id;

    @TableField(value = "st_store_name")
    @ApiModelProperty(value = "店铺名称")
    private String storeName;

    @TableField(value = "st_legal_person")
    @ApiModelProperty(value = "法人")
    private String legalPerson;

    @TableField(value = "st_business_license")
    @ApiModelProperty(value = "营业执照")
    private String businessLicense;

    @TableField(value = "st_registration_time")
    @ApiModelProperty(value = "注册时间")
    private LocalDate registrationTime;

    @TableField(value = "st_tort_fraction")
    @ApiModelProperty(value = "侵权扣分")
    private Integer tortFraction;

    @TableField(value = "st_serious_trot_count")
    @ApiModelProperty(value = "严重侵权次数")
    private Integer seriousTrotCount;

    @TableField(value = "st_status")
    @ApiModelProperty(value = "店铺状态 | 1：运营  2、退出运营")
    private Integer status;

    @TableField(value = "st_exit_time")
    @ApiModelProperty(value = "退出时间")
    private LocalDate exitTime;

    @TableField(value = "st_shop_id")
    @ApiModelProperty(value = "商户 id")
    private String shopId;

    @TableField(value = "st_platform_type")
    @ApiModelProperty(value = "所属平台")
    private Integer platformType;

    @TableField(value = "st_create_time")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @TableField(value = "st_create_id")
    @ApiModelProperty(value = "创建人")
    private String createId;

    public void copy(Store source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
