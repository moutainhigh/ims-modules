package com.lhiot.ims.ordercenter.feign.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lhiot.ims.ordercenter.feign.type.DeliverAtType;
import lombok.Data;

import java.util.Date;

/**
 * @author zhangfeng create in 11:40 2018/12/11
 */
@Data
public class DeliverFeeRule {
    private Long id;

    private Integer minOrderAmount;

    private Integer maxOrderAmount;

    private DeliverAtType deliveryAtType;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateAt;

    private String createBy;
}
