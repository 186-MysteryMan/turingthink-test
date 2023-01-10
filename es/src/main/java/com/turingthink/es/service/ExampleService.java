package com.turingthink.es.service;

import com.turingthink.es.service.dto.ElasticsearchDTO;

import java.util.List;

/**
 * @author GongJie Sheng
 * @date 2023/1/11 0:02
 */
public interface ExampleService {
    /**
     * example列表数据
     * @return
     */
    List<ElasticsearchDTO> exampleList();
}
