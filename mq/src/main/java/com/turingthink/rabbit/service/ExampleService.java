package com.turingthink.rabbit.service;

import com.turingthink.rabbit.service.dto.RabbitMqDTO;

import java.util.List;

/**
 * @author GongJie Sheng
 * @version v1.0.0
 * @Date 2022-06-25 19:23
 * @description
 */
public interface ExampleService {

    /**
     * example列表
     *
     * @return
     */
    List<RabbitMqDTO> exampleList();

    /**
     * 删除example自己的数据
     */
    void deleteExample();
}
