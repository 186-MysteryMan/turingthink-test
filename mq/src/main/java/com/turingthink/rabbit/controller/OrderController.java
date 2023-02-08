package com.turingthink.rabbit.controller;

import com.turingthink.rabbit.common.R;
import com.turingthink.rabbit.controller.vo.OrderVO;
import com.turingthink.rabbit.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author GongJie Sheng
 * @version v1.0.0
 * @Date 2023-02-08 16:00:08
 * @description
 */
@RestController
@Api(tags = "订单幂等测试")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/v1/order")
    @ApiOperation(value = "下单")
    public R<?> createOrder(@Validated @RequestBody OrderVO.CreateOrderForm vo) {
        orderService.createOrder(vo.convert());
        return R.success();
    }
}
