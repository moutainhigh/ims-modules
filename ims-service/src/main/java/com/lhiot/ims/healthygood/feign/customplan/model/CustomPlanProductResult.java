package com.lhiot.ims.healthygood.feign.customplan.model;

import com.lhiot.ims.healthygood.feign.customplan.type.OptionType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@ApiModel
@NoArgsConstructor
/**
 * 定制计划商品信息
 */
public class CustomPlanProductResult {
    private Integer index;

    @ApiModelProperty(value = "定制商品id", dataType = "Long")
    private Long id;

    @ApiModelProperty(value = "商品名称", dataType = "String")
    private String productName;

    @ApiModelProperty(value = "商品上架Id", dataType = "Long")
    private Long shelfId;

    @ApiModelProperty(value = "第x天（如：第1天则为1 纯数字）", dataType = "Integer")
    private Integer dayOfPeriod;

    @ApiModelProperty(value = "定制计划id", dataType = "Long")
    private Long planId;

    @ApiModelProperty(value = "描述", dataType = "String")
    private String description;

    @ApiModelProperty(value = "商品图片", dataType = "String")
    private String image;

    @ApiModelProperty(value = "益处", dataType = "String")
    private String benefit;

    @ApiModelProperty(value = "操作类型", dataType = "OptionType")
    private OptionType optionType;

    @ApiModelProperty(value = "定制周期（周、月）", dataType = "Integer")
    private Integer planPeriod;
}
