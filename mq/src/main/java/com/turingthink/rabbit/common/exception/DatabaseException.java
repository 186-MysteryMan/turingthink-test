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
public class DatabaseException extends RuntimeException {
    private Integer code = R.UNKNOW_ERROR;

    public DatabaseException() {
    }

    public DatabaseException(Integer code) {
        this.code = code;
    }

    public DatabaseException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public static DatabaseException dataExisted() {
        return new DatabaseException(R.DATA_EXISTED);
    }

    public static DatabaseException dataExisted(String message) {
        return new DatabaseException(R.DATA_EXISTED, message);
    }

    public static DatabaseException dataInexistent() {
        return new DatabaseException(R.DATA_INEXISTENT);
    }

    public static DatabaseException dataInexistent(String message) {
        return new DatabaseException(R.DATA_INEXISTENT, message);
    }

    public static DatabaseException databaseError() {
        return new DatabaseException(R.DATABASE_ERROR);
    }
}
