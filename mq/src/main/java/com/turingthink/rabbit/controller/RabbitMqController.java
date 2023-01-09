package com.turingthink.rabbit.controller;

import com.turingthink.rabbit.service.ElasticsearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author GongJie Sheng
 * @date 2023/1/10 0:44
 */
@RestController
public class RabbitMqController {

    @Autowired
    private ElasticsearchService elasticsearchService;

    @GetMapping("/rabbitTest")
    public String test(){
        return elasticsearchService.test();
    }
}
