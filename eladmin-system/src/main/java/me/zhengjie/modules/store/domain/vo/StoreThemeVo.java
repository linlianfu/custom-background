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
package me.zhengjie.modules.store.domain.vo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @description /
 * @author eleven
 * @date 2024-03-16
 **/
@Data
public class StoreThemeVo implements Serializable {

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "店铺id")
    private String storeId;

    @ApiModelProperty(value = "店铺id")
    private String storeName;

    @ApiModelProperty(value = "主题id")
    private String themeId;

    @ApiModelProperty(value = "主题名称")
    private String themeName;

    @ApiModelProperty(value = "上架时间")
    private LocalDate upTime;

    @ApiModelProperty(value = "上架产品数量")
    private Integer productCount;

    /**
     * @see Tort
     */
    @ApiModelProperty(value = "是否侵权")
    private Integer tort;

    /**
     * @see TortType
     */
    @ApiModelProperty(value = "侵权类型")
    private Integer tortType;

    @ApiModelProperty(value = "侵权时间")
    private LocalDateTime tortTime;

    @ApiModelProperty(value = "创建人")
    private String createId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    public void copy(StoreThemeVo source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
