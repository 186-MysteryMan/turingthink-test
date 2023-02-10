package com.turingthink.rabbit.common.exception;

import com.turingthink.rabbit.common.R;
import lombok.Data;

/**
 * @author: fight2048
 * @e-mail: fight2048@outlook.com
 * @blog: https://github.com/fight2048
 * @time: 2019-01-08 0008 下午 11:17
 * @version: v0.0.0
 * @description:
 */
@Data
public class AuthorizationException extends RuntimeException {
    private Integer code = R.UNKNOW_ERROR;

    public AuthorizationException(Integer code) {
        this.code = code;
    }

    public static AuthorizationException accountRegistered() {
        return new AuthorizationException(R.ACCOUNT_REGISTERED);
    }

    public static AuthorizationException accountError() {
        return new AuthorizationException(R.ACCOUNT_ERROR);
    }

    public static AuthorizationException passwordError() {
        return new AuthorizationException(R.PASSWORD_ERROR);
    }

    public static AuthorizationException accountCredentialsError() {
        return new AuthorizationException(R.ACCOUNT_CREDENTIALS_ERROR);
    }

    public static AuthorizationException accountUnavailable() {
        return new AuthorizationException(R.ACCOUNT_UNAVAILABLE);
    }

    public static AuthorizationException tokenError() {
        return new AuthorizationException(R.TOKEN_ERROR);
    }

    public static AuthorizationException tokenExpired() {
        return new AuthorizationException(R.TOKEN_EXPIRED);
    }

    public static AuthorizationException tokenInvalid() {
        return new AuthorizationException(R.TOKEN_INVALID);
    }
}
