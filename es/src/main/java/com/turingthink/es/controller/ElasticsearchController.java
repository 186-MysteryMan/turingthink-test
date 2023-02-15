package com.turingthink.es.controller;

import com.turingthink.es.common.R;
import com.turingthink.es.controller.vo.ElasticsearchVO;
import com.turingthink.es.service.ExampleService;
import com.turingthink.es.service.dto.ElasticsearchDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author GongJie Sheng
 * @date 2023/1/10 0:44
 */
@RestController
@Api(tags = "es考核测试")
public class ElasticsearchController {

    @Autowired
    private ExampleService exampleService;

    @PostMapping("/v1/addExample")
    @ApiOperation(value = "将mysql的example数据转移到es")
    public R<?> addExample() {
        exampleService.addExample();
        return R.success();
    }

    @GetMapping("/v1/exampleList")
    @ApiOperation(value = "example列表数据")
    public R<List<ElasticsearchVO.ExampleListVO>> exampleList(@Validated ElasticsearchVO.AddExampleForm vo) {
        List<ElasticsearchDTO> list = exampleService.exampleList(vo.convert());
        return R.success(ElasticsearchVO.ExampleListVO.convert(list));
    }

    @DeleteMapping("/v1/example")
    @ApiOperation(value = "删除example列表数据mysql和es")
    public R<?> deleteExample() {
        exampleService.deleteExample();
        return R.success();
    }
}
