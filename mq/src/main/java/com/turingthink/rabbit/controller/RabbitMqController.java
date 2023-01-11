package com.turingthink.rabbit.controller;

import com.turingthink.rabbit.controller.vo.RabbitMqVO;
import com.turingthink.rabbit.service.ExampleService;
import com.turingthink.rabbit.service.RabbitService;
import com.turingthink.rabbit.service.dto.RabbitMqDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author GongJie Sheng
 * @date 2023/1/10 0:44
 */
@RestController
@Api(tags = "mq考核测试")
public class RabbitMqController {

    @Autowired
    private RabbitService rabbitService;
    @Autowired
    private ExampleService exampleService;

    @PostMapping("/v1/delayQueueExample")
    @ApiOperation(value = "延迟队列并根据传入类型插入对应数据")
    public String delayQueueExample(@Validated @RequestBody RabbitMqVO.DelayQueueExampleForm vo) {
        rabbitService.delayQueueExample(vo.convert());
        return "成功";
    }

    @GetMapping("/v1/exampleList")
    @ApiOperation(value = "example列表数据")
    public List<RabbitMqVO.ExampleListVO> exampleList() {
        List<RabbitMqDTO> rabbitMqList = exampleService.exampleList();
        return RabbitMqVO.ExampleListVO.convert(rabbitMqList);
    }

    @DeleteMapping("/v1/deleteExample")
    @ApiOperation(value = "删除example自己的数据")
    public void deleteExample() {
        exampleService.deleteExample();
    }
}
