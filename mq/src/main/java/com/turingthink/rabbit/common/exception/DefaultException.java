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
public class DefaultException extends RuntimeException {
    private Integer code = R.UNKNOW_ERROR;

    public DefaultException() {
    }

    public DefaultException(Integer code) {
        this.code = code;
    }

    public DefaultException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public static DefaultException accountExisted() {
        return new DefaultException(R.ACCOUNT_REGISTERED);
    }

    public static DefaultException accountError() {
        return new DefaultException(R.ACCOUNT_ERROR);
    }

    public static DefaultException passwordError() {
        return new DefaultException(R.PASSWORD_ERROR);
    }

    public static DefaultException appIdError() {
        return new DefaultException(R.APP_ID_ERROR);
    }
}
