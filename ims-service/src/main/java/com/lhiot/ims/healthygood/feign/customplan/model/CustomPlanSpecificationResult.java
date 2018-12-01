package com.lhiot.ims.healthygood.feign.customplan.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.lhiot.ims.healthygood.feign.customplan.entity.CustomPlanProduct;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author hufan created in 2018/11/26 18:38
 **/
@Data
@ToString(callSuper = true)
@ApiModel
@NoArgsConstructor
public class CustomPlanSpecificationResult {

    /**
     *
     */
    @JsonProperty("id")
    @ApiModelProperty(value = "", dataType = "Long", readOnly = true)
    private Long id;

    /**
     * 定制计划id
     */
    @JsonProperty("planId")
    @ApiModelProperty(value = "定制计划id", dataType = "Long")
    private Long planId;

    /**
     * 价格
     */
    @JsonProperty("price")
    @ApiModelProperty(value = "价格", dataType = "String")
    private String price;

    /**
     * 数量
     */
    @JsonProperty("quantity")
    @ApiModelProperty(value = "数量", dataType = "String")
    private String quantity;

    /**
     * 描述
     */
    @JsonProperty("description")
    @ApiModelProperty(value = "描述", dataType = "String")
    private String description;

    /**
     * 定制计划周期
     */
    @JsonProperty("planPeriod")
    @ApiModelProperty(value = "定制计划周期", dataType = "String")
    private String planPeriod;

    /**
     * 定制商品
     */
    @ApiModelProperty(value = "定制商品", dataType = "List")
    @NotNull
    private List<CustomPlanProduct> customPlanProducts;


}
