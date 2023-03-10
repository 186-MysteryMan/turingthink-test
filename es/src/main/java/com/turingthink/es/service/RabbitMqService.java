package com.turingthink.es.service;

import com.turingthink.es.common.R;
import com.turingthink.es.service.dto.ElasticsearchDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author GongJie Sheng
 * @date 2023/1/10 0:48
 */
@FeignClient(value = "shenggongjie-mq")
@Service
public interface RabbitMqService {

    /**
     * 查询example列表
     *
     * @return
     */
    @GetMapping("/v1/exampleList")
    R<List<ElasticsearchDTO>> exampleList();

    /**
     * 删除example自己的数据
     *
     * @return
     */
    @DeleteMapping("/v1/deleteExample")
    R<?> deleteExample();
}
