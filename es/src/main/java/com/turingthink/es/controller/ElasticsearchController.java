package com.turingthink.es.controller;

import com.turingthink.es.controller.vo.ElasticsearchVO;
import com.turingthink.es.service.ExampleService;
import com.turingthink.es.service.dto.ElasticsearchDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/v1/exampleList")
    @ApiOperation(value = "example列表数据")
    public List<ElasticsearchVO.ExampleListVO> exampleList() {
        List<ElasticsearchDTO> list = exampleService.exampleList();
        return ElasticsearchVO.ExampleListVO.convert(list);
    }
}
