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
public class CodeException extends RuntimeException {
    private Integer code = R.UNKNOW_ERROR;

    public CodeException(Integer code) {
        this.code = code;
    }

    public CodeException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public static CodeException getCodeError(String message) {
        return new CodeException(R.CODE_GET_ERROR, message);
    }

    public static CodeException codeError(String message) {
        return new CodeException(R.CODE_ERROR, message);
    }

    public static CodeException codeDisabled(String message) {
        return new CodeException(R.CODE_DISABLED, message);
    }
}
