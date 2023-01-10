package com.turingthink.es.service;

import com.turingthink.es.controller.vo.RabbitMqVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author GongJie Sheng
 * @date 2023/1/10 0:48
 */
@FeignClient(value = "mq")
@Service
public interface RabbitMqService {

    /**
     * 查询example列表
     * @return
     */
    @GetMapping("/v1/exampleList")
    List<RabbitMqVO.ExampleListVO> exampleList();
}
