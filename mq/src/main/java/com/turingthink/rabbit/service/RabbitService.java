package com.turingthink.rabbit.service;

import com.turingthink.rabbit.service.dto.RabbitMqDTO;

import java.util.List;

/**
 * @author GongJie Sheng
 * @version v1.0.0
 * @Date 2022-06-25 19:23
 * @description
 */
public interface RabbitService {
    /**
     * 延迟队列，并插入对应数据根据传入的type
     * @param dto
     */
    void delayQueueExample(RabbitMqDTO dto);


}
