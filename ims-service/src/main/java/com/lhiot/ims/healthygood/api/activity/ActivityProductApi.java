package com.lhiot.ims.healthygood.api.activity;

import com.leon.microx.web.swagger.ApiHideBodyProperty;
import com.leon.microx.web.swagger.ApiParamType;
import com.lhiot.ims.healthygood.feign.activity.ActivityProductFeign;
import com.lhiot.ims.healthygood.feign.activity.entity.ActivityProduct;
import com.lhiot.ims.healthygood.feign.activity.model.ActivityProductParam;
import com.lhiot.ims.healthygood.feign.activity.model.ActivityProductResult;
import com.lhiot.ims.rbac.aspect.LogCollection;
import com.lhiot.util.FeginResponseTools;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author hufan created in 2018/12/3 15:44
 **/
@Api(description = "新品尝鲜活动商品接口")
@Slf4j
@RestController
public class ActivityProductApi {
    private final ActivityProductFeign activityProductFeign;

    public ActivityProductApi(ActivityProductFeign activityProductFeign) {
        this.activityProductFeign = activityProductFeign;
    }

    @LogCollection
    @ApiOperation("添加新品尝鲜活动商品")
    @PostMapping("/activity-products")
    @ApiHideBodyProperty("id")
    public ResponseEntity create(@Valid @RequestBody ActivityProduct activityProduct) {
        log.debug("添加新品尝鲜活动商品\t param:{}", activityProduct);

        ResponseEntity entity = activityProductFeign.create(activityProduct);
        return FeginResponseTools.convertCreateResponse(entity);
    }

    @LogCollection
    @ApiOperation("修改新品尝鲜活动商品")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = ApiParamType.PATH, name = "id", value = "新品尝鲜活动商品id", dataType = "Long", required = true),
            @ApiImplicitParam(paramType = ApiParamType.BODY, name = "activityProduct", value = "新品尝鲜活动商品", dataType = "ActivityProduct", required = true)
    })
    @PutMapping("/activity-products/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @Valid @RequestBody ActivityProduct activityProduct) {
        log.debug("修改新品尝鲜活动商品\t param:{}", activityProduct);

        ResponseEntity entity = activityProductFeign.update(id, activityProduct);
        return FeginResponseTools.convertUpdateResponse(entity);
    }

    @LogCollection
    @ApiOperation("根据ids删除新品尝鲜活动商品")
    @ApiImplicitParam(paramType = ApiParamType.PATH, name = "ids", value = "多个新品尝鲜活动商品id以英文逗号分隔", dataType = "String", required = true)
    @DeleteMapping("/activity-products/{ids}")
    public ResponseEntity batchDelete(@PathVariable("ids") String ids) {
        log.debug("批量删除新品尝鲜活动商品\t param:{}", ids);

        ResponseEntity entity = activityProductFeign.batchDelete(ids);
        return FeginResponseTools.convertDeleteResponse(entity);
    }

    @ApiOperation(value = "根据条件分页查询新品尝鲜活动商品信息列表", response = ActivityProductResult.class, responseContainer = "Set")
    @PostMapping("/activity-products/pages")
    public ResponseEntity search(@RequestBody ActivityProductParam param) {
        log.debug("根据条件分页查询新品尝鲜活动商品信息列表\t param:{}", param);

        ResponseEntity entity = activityProductFeign.search(param);
        return FeginResponseTools.convertNoramlResponse(entity);
    }
}