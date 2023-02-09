package com.turingthink.rabbit.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.turingthink.rabbit.dao.entity.GoodsEntity;
import com.turingthink.rabbit.dao.entity.OrderEntity;
import com.turingthink.rabbit.dao.mapper.GoodsMapper;
import com.turingthink.rabbit.dao.mapper.OrderMapper;
import com.turingthink.rabbit.enums.OrderStatusEnum;
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
        //使用数据库锁保证超卖
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancelOrder(Long orderId) {
        LambdaUpdateWrapper<OrderEntity> orderUw = Wrappers.lambdaUpdate(OrderEntity.class)
                .set(OrderEntity::getStatus, OrderStatusEnum.CANCEL)
                .eq(OrderEntity::getUid, orderId)
                .eq(OrderEntity::getStatus, OrderStatusEnum.SUCCESS);
        //使用数据库锁保证幂等
        int update = orderMapper.update(null, orderUw);
        if (update > 0) {
            OrderEntity orderEntity = orderMapper.selectById(orderId);
            Integer count = orderEntity.getCount();
            Long goodsId = orderEntity.getGoodsId();
            goodsMapper.restoreStock(goodsId, count);
        }
    }
}
