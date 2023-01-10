package com.turingthink.rabbit.service.impl;

import com.turingthink.rabbit.enums.ExampleTypeEnum;
import com.turingthink.rabbit.message.RabbitExampleConfig;
import com.turingthink.rabbit.service.RabbitService;
import com.turingthink.rabbit.service.dto.RabbitMqDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author GongJie Sheng
 * @version v1.0.0
 * @Date 2023-01-10 15:07:50
 * @description
 */
@Service
@Slf4j
public class RabbitServiceImpl implements RabbitService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void delayQueueExample(RabbitMqDTO dto) {
        Integer second = dto.getSecond();
        ExampleTypeEnum type = dto.getType();
        rabbitTemplate.convertAndSend(RabbitExampleConfig.EXAMPLE_DELAY_EXCHANGE,
                RabbitExampleConfig.EXAMPLE_DELAY_ROUTING,
                type,
                message -> {
                    //设置过期时间，分钟转换成毫秒
                    message.getMessageProperties().setHeader(MessageProperties.X_DELAY, (long) (second * 1000));
                    return message;
                });
    }
}
