package com.turingthink.rabbit.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.turingthink.rabbit.dao.entity.GoodsEntity;
import com.turingthink.rabbit.dao.entity.OrderEntity;
import com.turingthink.rabbit.dao.mapper.GoodsMapper;
import com.turingthink.rabbit.dao.mapper.OrderMapper;
import com.turingthink.rabbit.service.OrderService;
import com.turingthink.rabbit.service.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @author GongJie Sheng
 * @version v1.0.0
 * @Date 2023-02-08 16:37:33
 * @description
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createOrder(OrderDTO orderDTO) {
        Long goodsId = orderDTO.getGoodsId();
        Integer count = orderDTO.getCount();
        boolean reduceResult = goodsMapper.reduceStock(goodsId, count);
        if (reduceResult) {
            GoodsEntity goodsEntity = goodsMapper.selectById(goodsId);
            BigDecimal amount = goodsEntity.getAmount();
            BigDecimal fee = amount.multiply(new BigDecimal(count));
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setFee(fee);
            orderEntity.setGoodsName(goodsEntity.getName());
            orderEntity.setCount(count);
            orderMapper.insert(orderEntity);
        }
    }
}
