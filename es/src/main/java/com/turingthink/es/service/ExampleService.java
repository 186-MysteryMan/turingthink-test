package com.turingthink.es.service;

import com.turingthink.es.service.dto.ElasticsearchDTO;

import java.util.List;

/**
 * @author GongJie Sheng
 * @date 2023/1/11 0:02
 */
public interface ExampleService {
    /**
     * 将mysql的example数据转移到es
     * @return
     */
    void addExample();

    /**
     * 查询example列表
     * @param dto
     * @return
     */
    List<ElasticsearchDTO> exampleList(ElasticsearchDTO dto);

    /**
     * 删除自己的数据
     */
    void deleteExample();
}
