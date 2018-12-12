package com.lhiot.ims.ordercenter.feign.entity;

import com.lhiot.ims.ordercenter.feign.type.OrderStatus;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 订单操作流水记录
 */
@EqualsAndHashCode(callSuper = false)
@Data
@ApiModel
public class OrderFlow{
    private Long id;
    private Long orderId;
    private OrderStatus status;
    private OrderStatus preStatus;
    private Date createAt;
}