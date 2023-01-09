package com.turingthink.rabbit.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author GongJie Sheng
 * @date 2023/1/10 0:48
 */
@FeignClient(value = "es")
@Service
public interface ElasticsearchService {

    /**
     * 测试
     * @return
     */
    @GetMapping("/test")
    String test();
}
