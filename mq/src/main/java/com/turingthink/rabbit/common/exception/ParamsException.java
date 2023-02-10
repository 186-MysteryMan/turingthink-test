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
public class ParamsException extends RuntimeException {
    private Integer code = R.PARAMS_ERROR;
    private Object data;

    public ParamsException(Integer code, Object data) {
        super();
        this.code = code;
        this.data = data;
    }

    public ParamsException(Integer code, String message, Object data) {
        super(message);
        this.code = code;
        this.data = data;
    }

    public static ParamsException paramsError(Object data) {
        return new ParamsException(R.PARAMS_ERROR, data);
    }
}
