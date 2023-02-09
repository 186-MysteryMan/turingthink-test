package com.turingthink.rabbit.controller;

import com.turingthink.rabbit.common.R;
import com.turingthink.rabbit.controller.vo.OrderVO;
import com.turingthink.rabbit.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
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
@Api(tags = "订单测试")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/v1/createOrder")
    @ApiOperation(value = "下单")
    public R<?> createOrder(@Validated @RequestBody OrderVO.CreateOrderForm vo) {
        orderService.createOrder(vo.convert());
        return R.success();
    }

    @PostMapping("/v1/cancelOrder/{orderId}")
    @ApiOperation(value = "取消订单")
    public R<?> cancelOrder(@ApiParam(required = true, value = "订单ID", example = "1")
                            @PathVariable Long orderId) {
        orderService.cancelOrder(orderId);
        return R.success();
    }
}
