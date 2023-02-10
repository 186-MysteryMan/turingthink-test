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
public class AuthenticationException extends RuntimeException {
    private Integer code = R.UNKNOW_ERROR;

    public AuthenticationException(Integer code) {
        this.code = code;
    }

    public AuthenticationException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public static AuthenticationException accountRegistered() {
        return new AuthenticationException(R.ACCOUNT_REGISTERED);
    }

    public static AuthenticationException accountError() {
        return new AuthenticationException(R.ACCOUNT_ERROR);
    }

    public static AuthenticationException passwordError() {
        return new AuthenticationException(R.PASSWORD_ERROR);
    }

    public static AuthenticationException accountCredentialsError() {
        return new AuthenticationException(R.ACCOUNT_CREDENTIALS_ERROR);
    }

    public static AuthenticationException authenticationError() {
        return new AuthenticationException(R.AUTHENTICATION_ERROR);
    }

    public static AuthenticationException accountUnavailable(String message) {
        return new AuthenticationException(R.ACCOUNT_UNAVAILABLE, message);
    }

    public static AuthenticationException accountUnavailable() {
        return new AuthenticationException(R.ACCOUNT_UNAVAILABLE);
    }

    public static AuthenticationException tokenError() {
        return new AuthenticationException(R.TOKEN_ERROR);
    }

    public static AuthenticationException tokenExpired() {
        return new AuthenticationException(R.TOKEN_EXPIRED);
    }

    public static AuthenticationException tokenInvalid() {
        return new AuthenticationException(R.TOKEN_INVALID);
    }

    public static AuthenticationException unauthenticated() {
        return new AuthenticationException(R.UNAUTHENTICATED);
    }
}
