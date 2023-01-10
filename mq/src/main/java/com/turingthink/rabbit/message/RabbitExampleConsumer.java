package com.turingthink.rabbit.message;

import com.turingthink.rabbit.dao.entity.ExampleEntity;
import com.turingthink.rabbit.dao.mapper.ExampleMapper;
import com.turingthink.rabbit.enums.ExampleTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author GongJie Sheng
 * @version v1.0.0
 * @Date 2022-06-13 15:58
 * @description
 */
@Component
@Slf4j
public class RabbitExampleConsumer {

    @Autowired
    private ExampleMapper exampleMapper;

    /**
     * 取消订单队列
     */
    @RabbitListener(queues = RabbitExampleConfig.EXAMPLE_DELAY_QUEUE)
    public void process(ExampleTypeEnum type) {
        ExampleEntity exampleEntity = new ExampleEntity();
        exampleEntity = switch (type) {
            case DEV -> exampleEntity.setName("开发").setDescription("这是开发环境");
            case TEST -> exampleEntity.setName("测试").setDescription("这是开发环境");
            case PROD -> exampleEntity.setName("生产").setDescription("这是开发环境");
            default -> exampleEntity.setName("未知").setDescription("这是开发环境");
        };
        exampleEntity.setType(type).setCreator("盛攻杰");
        exampleMapper.insert(exampleEntity);
    }
}
