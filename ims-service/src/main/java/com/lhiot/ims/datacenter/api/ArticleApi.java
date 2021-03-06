package com.lhiot.ims.datacenter.api;

import com.leon.microx.web.swagger.ApiHideBodyProperty;
import com.leon.microx.web.swagger.ApiParamType;
import com.lhiot.ims.datacenter.feign.ArticleFeign;
import com.lhiot.ims.datacenter.feign.entity.Article;
import com.lhiot.ims.datacenter.feign.model.ArticleParam;
import com.lhiot.ims.datacenter.feign.type.ArticleStatus;
import com.lhiot.ims.rbac.aspect.LogCollection;
import com.lhiot.util.FeginResponseTools;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.Instant;
import java.util.Objects;

/**
 * @author hufan created in 2018/12/7 18:45
 **/
@Api(description = "文章相关接口")
@Slf4j
@RestController
public class ArticleApi {
    private final ArticleFeign articleFeign;

    public ArticleApi(ArticleFeign articleFeign) {
        this.articleFeign = articleFeign;
    }

    @LogCollection
    @ApiOperation("添加文章")
    @PostMapping("/articles")
    public ResponseEntity create(@RequestBody Article article) {
        log.debug("添加文章\t param:{}", article);

        if (Objects.equals(ArticleStatus.PUBLISH,article.getArticleStatus())) {
            article.setPublishAt(Date.from(Instant.now()));
        }
        ResponseEntity entity = articleFeign.create(article);
        return FeginResponseTools.convertCreateResponse(entity);
    }


    @LogCollection
    @ApiOperation("修改文章")
    @ApiImplicitParam(paramType = ApiParamType.PATH, name = "id", value = "文章Id", dataType = "Long", required = true)
    @PutMapping("/articles/{id}")
    @ApiHideBodyProperty("id")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Article article) {
        log.debug("修改文章\t id:{} param:{}", id, article);


        ResponseEntity findEntity = articleFeign.single(id, false);
        if (findEntity.getStatusCode().isError() || Objects.isNull(findEntity.getBody())) {
            return ResponseEntity.badRequest().body("调用基础数据服务失败");
        }
        Article findArticle = (Article) findEntity.getBody();
        if (Objects.equals(ArticleStatus.UN_PUBLISH, findArticle.getArticleStatus()) && Objects.equals(ArticleStatus.PUBLISH, article.getArticleStatus())) {
            article.setPublishAt(Date.from(Instant.now()));
        }
        ResponseEntity entity = articleFeign.update(id, article);
        return FeginResponseTools.convertUpdateResponse(entity);
    }

    @ApiOperation(value = "根据Id查找文章", response = Article.class)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = ApiParamType.PATH, name = "id", value = "文章Id", dataType = "Long", required = true)
    })
    @GetMapping("/articles/{id}")
    public ResponseEntity single(@PathVariable("id") Long id) {
        log.debug("根据Id查找商品规格\t id:{}", id);

        ResponseEntity entity = articleFeign.single(id, false);
        return FeginResponseTools.convertNoramlResponse(entity);
    }


    @LogCollection
    @ApiOperation("根据Id删除文章")
    @ApiImplicitParam(paramType = ApiParamType.PATH, name = "ids", value = "多个文章Id以英文逗号分隔", dataType = "String", required = true)
    @DeleteMapping("/articles/{ids}")
    public ResponseEntity batchDelete(@PathVariable("ids") String ids) {
        log.debug("根据Id删除文章\t param:{}", ids);

        ResponseEntity entity = articleFeign.batchDelete(ids);
        return FeginResponseTools.convertDeleteResponse(entity);
    }


    @ApiOperation(value = "根据条件分页查询文章信息列表", response = Article.class, responseContainer = "Set")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = ApiParamType.BODY, name = "param", value = "查询条件", dataType = "ArticleParam")
    })
    @PostMapping("/articles/pages")
    public ResponseEntity search(@RequestBody ArticleParam param) {
        log.debug("查询商品规格信息列表\t param:{}", param);

        ResponseEntity entity = articleFeign.search(param);
        return FeginResponseTools.convertNoramlResponse(entity);
    }
}