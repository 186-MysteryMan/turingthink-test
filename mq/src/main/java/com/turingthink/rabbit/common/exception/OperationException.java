package com.turingthink.rabbit.common.exception;

import com.turingthink.rabbit.common.R;
import lombok.Data;

/**
 * @author: fight2048
 * @e-mail: fight2048@outlook.com
 * @blog: https://github.com/fight2048
 * @time: 2018-01-08 0008 下午 11:17
 * @version: v0.0.0
 * @description:
 */
@Data
public class OperationException extends RuntimeException {
    private Integer code = R.OPERATION_EXCEPTION;

    public OperationException() {
    }

    public OperationException(Integer code) {
        this.code = code;
    }

    public OperationException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public static OperationException get() {
        return new OperationException();
    }

    public static OperationException operationError(String message) {
        return new OperationException(R.OPERATION_ERROR, message);
    }

    public static OperationException noPhone() {
        return new OperationException(R.OPERATION_ERROR_NO_PHONE);
    }
}
