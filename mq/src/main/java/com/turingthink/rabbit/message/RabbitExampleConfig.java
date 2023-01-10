package com.turingthink.rabbit.message;

import com.google.common.collect.Maps;
import org.springframework.amqp.core.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @author GongJie Sheng
 * @version v1.0.0
 * @Date 2022_06_13 15:18
 * @description 取消订单
 */
@Configuration
public class RabbitExampleConfig {

    public static final String EXAMPLE_DELAY_QUEUE = "example_delay_queue";
    public static final String EXAMPLE_DELAY_EXCHANGE = "example_delay_exchange";
    public static final String EXAMPLE_DELAY_ROUTING = "example_delay_routing";
    /**
     * 延时队列
     *
     * @return org.springframework.amqp.core.Queue
     */
    @Bean
    public Queue exampleQueue() {
        return new Queue(EXAMPLE_DELAY_QUEUE);
    }

    /**
     * 延迟交换机
     *
     * @return org.springframework.amqp.core.CustomExchange
     */
    @Bean
    public CustomExchange exampleExchange() {
        Map<String, Object> args = Maps.newHashMap();
        args.put("x-delayed-type", "direct");
        return new CustomExchange(EXAMPLE_DELAY_EXCHANGE, "x-delayed-message", true, false, args);
    }

    /**
     * 绑定队列到这个延迟交换机上
     *
     * @return org.springframework.amqp.core.Binding
     */
    @Bean
    public Binding exampleBinding() {
        return BindingBuilder.bind(exampleQueue()).to(exampleExchange()).with(EXAMPLE_DELAY_ROUTING).noargs();
    }
}
