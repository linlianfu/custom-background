package me.zhengjie.modules.website.service.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 排序字段表达式
 * @author eleven
 */
@Data
public class FieldValueDto {

    @ApiModelProperty(value = "字段名称")
    private String  fieldName;

    @ApiModelProperty(value = "字段标题")
    private String title;

    @ApiModelProperty(value = "字段值")
    private String value;

    @ApiModelProperty(value = "字段查询表达式")
    private String express;


    public void buildExpress(){
        this.express = fieldName +"="+ value;
    }

}
