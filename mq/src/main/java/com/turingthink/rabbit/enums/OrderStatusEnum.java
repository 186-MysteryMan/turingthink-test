package com.turingthink.rabbit.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * @author GongJie Sheng
 * @Date 2022-05-28 16:31
 * @version v1.0.0
 * @description 订单状态：默认SUCCESS=下单成功；CANCEL=取消订单
 */
@Getter
public enum OrderStatusEnum {

    /**
     * 下单成功
     */
    SUCCESS(1, "下单成功"),
    /**
     * 取消订单
     */
    CANCEL(2, "取消订单");

    @JsonCreator
    OrderStatusEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    @EnumValue
    private final Integer code;
    @JsonValue
    private final String value;

    public static OrderStatusEnum getDefault() {
        return OrderStatusEnum.SUCCESS;
    }

}
