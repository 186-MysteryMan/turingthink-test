package com.turingthink.es.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author GongJie Sheng
 * @date 2023/1/10 0:44
 */
@RestController
public class ElasticsearchController {

    @GetMapping("/test")
    public String test() {
        return "hello,world";
    }
}
