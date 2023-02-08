package com.turingthink.rabbit.service;

import com.turingthink.rabbit.service.dto.OrderDTO;

/**
 * @author GongJie Sheng
 * @version v1.0.0
 * @Date 2023-02-08 16:36:50
 * @description
 */
public interface OrderService {
    /**
     * 创建订单
     * @param orderDTO
     */
    void createOrder(OrderDTO orderDTO);
}
