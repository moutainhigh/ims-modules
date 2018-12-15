package com.lhiot.ims.ordercenter.api;

import com.leon.microx.web.result.Pages;
import com.leon.microx.web.swagger.ApiParamType;
import com.lhiot.ims.ordercenter.feign.DeliveryFeign;
import com.lhiot.ims.ordercenter.feign.model.DeliverFeeRuleParam;
import com.lhiot.ims.ordercenter.feign.model.DeliverFeeRulesResult;
import com.lhiot.ims.ordercenter.feign.model.DeliverFeeSearchParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author hufan created in 2018/12/15 19:35
 **/
@Api(description = "邮费规格配置接口")
@RestController
@Slf4j
public class DeliveryFeeApi {
    private final DeliveryFeign deliveryFeign;

    @Autowired
    public DeliveryFeeApi(DeliveryFeign deliveryFeign) {
        this.deliveryFeign = deliveryFeign;
    }

    @ApiOperation("添加配送费计算规则")
    @ApiImplicitParam(paramType = ApiParamType.BODY, name = "deliverFeeRuleParam", value = "添加配送费规则入参", dataType = "DeliverFeeRuleParam", required = true)
    @PostMapping("/delivery-fee-rule")
    public ResponseEntity createRule(@RequestBody DeliverFeeRuleParam deliverFeeRuleParam) {
        log.debug("添加配送费计算规则\t param:{}", deliverFeeRuleParam);

        ResponseEntity entity = deliveryFeign.createRule(deliverFeeRuleParam);
        return entity.getStatusCode().isError() ? ResponseEntity.badRequest().body(entity.getBody()) : ResponseEntity.ok(entity.getBody());
    }

    @ApiOperation("修改配送费计算规则")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = ApiParamType.PATH, name = "id", value = "配送费规则Id", dataType = "Long", required = true),
            @ApiImplicitParam(paramType = ApiParamType.BODY, name = "deliverFeeRuleParam", value = "需要修改的配送费规则模板以及详细规则", dataType = "DeliverFeeRuleParam", required = true)
    })
    @PutMapping("/delivery-fee-rule/{id}")
    public ResponseEntity updateRules(@PathVariable("id") Long ruleId, @RequestBody DeliverFeeRuleParam deliverFeeRuleParam) {
        log.debug("修改配送费计算规则\t id:{} param:{}", ruleId, deliverFeeRuleParam);

        ResponseEntity entity = deliveryFeign.updateRules(ruleId, deliverFeeRuleParam);
        return entity.getStatusCode().isError() ? ResponseEntity.badRequest().body(entity.getBody()) : ResponseEntity.ok(entity.getBody());
    }

    @ApiOperation(value = "后台管理查询配送费规则列表", response = DeliverFeeRulesResult.class, responseContainer = "Set")
    @ApiImplicitParam(paramType = ApiParamType.BODY, name = "param", value = "查询条件", dataType = "DeliverFeeSearchParam", required = true)
    @PostMapping("/delivery-fee-rule/pages")
    public ResponseEntity query(@RequestBody DeliverFeeSearchParam param) {
        log.debug("查询商品分类信息列表\t param:{}", param);

        ResponseEntity<Pages<DeliverFeeRulesResult>> entity = deliveryFeign.query(param);
        return entity.getStatusCode().isError() ? ResponseEntity.badRequest().body(entity.getBody()) : ResponseEntity.ok(entity.getBody());
    }

    @ApiOperation("根据配送费详细规则Id删除")
    @ApiImplicitParam(paramType = ApiParamType.PATH, name = "id", value = "配送费详细规则Id", dataType = "Long", required = true)
    @DeleteMapping("/delivery-fee-rule/detail/{id}")
    public ResponseEntity deleteDetail(@PathVariable("id") Long id) {
        log.debug("根据配送费详细规则Id删除\t param:{}", id);

        ResponseEntity entity = deliveryFeign.deleteDetail(id);
        return entity.getStatusCode().isError() ? ResponseEntity.badRequest().body(entity.getBody()) : ResponseEntity.noContent().build();
    }

    @ApiOperation("根据配送费规则模板Id删除")
    @ApiImplicitParam(paramType = ApiParamType.PATH, name = "id", value = "配送费规则Id", dataType = "Long", required = true)
    @DeleteMapping("/delivery-fee-rule/{id}")
    public ResponseEntity deleteRule(@PathVariable("id") Long id) {
        log.debug("根据配送费规则模板Id删除\t param:{}", id);

        ResponseEntity entity = deliveryFeign.deleteRule(id);
        return entity.getStatusCode().isError() ? ResponseEntity.badRequest().body(entity.getBody()) : ResponseEntity.noContent().build();
    }
}