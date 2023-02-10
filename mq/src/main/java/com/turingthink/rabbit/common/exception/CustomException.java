package com.turingthink.rabbit.common.exception;

/**
 * @author GongJie Sheng
 * @Date 2022-05-20 17:58
 * @version v1.0.0
 * @description:
 */
public class CustomException extends RuntimeException {

    private Integer code;

    public CustomException(Integer code) {
        this.code = code;
    }

    public CustomException(Integer code, String message) {
        super(message);
        this.code = code;
    }
    public static void customError(Integer code) {
        throw new CustomException(code);
    }

    public static void customError(Integer code, String message) {
        throw new CustomException(code, message);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
