package com.turingthink.es.controller;

import com.turingthink.es.controller.vo.RabbitMqVO;
import com.turingthink.es.service.RabbitMqService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
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
    private RabbitMqService rabbitMqService;

    @GetMapping("/v1/exampleList")
    @ApiOperation(value = "example列表数据")
    public List<RabbitMqVO.ExampleListVO> exampleList() {
        return rabbitMqService.exampleList();
    }
}
