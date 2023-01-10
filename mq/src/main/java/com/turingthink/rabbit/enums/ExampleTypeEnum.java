package com.turingthink.rabbit.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * @author GongJie Sheng
 * @Date 2022-05-28 16:31
 * @version v1.0.0
 * @description 类型：默认UNKNOWN=未知；DEV=开发；TEST=测试；PROD=生产
 */
@Getter
public enum ExampleTypeEnum {

    /**
     * 未知
     */
    UNKNOWN(0, "UNKNOWN"),
    /**
     * 开发
     */
    DEV(1, "DEV"),
    /**
     * 测试
     */
    TEST(2, "TEST"),
    /**
     * 生产
     */
    PROD(3, "PROD");

    @JsonCreator
    ExampleTypeEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    @EnumValue
    private final Integer code;
    @JsonValue
    private final String value;

    public static ExampleTypeEnum getDefault() {
        return ExampleTypeEnum.UNKNOWN;
    }

}
