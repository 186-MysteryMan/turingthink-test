package com.turingthink.rabbit.common.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Objects;

/**
 * @author: fight2048
 * @e-mail: fight2048@outlook.com
 * @blog: https://github.com/fight2048
 * @time: 2018-01-08 0008 下午 11:17
 * @version: v0.0.0
 * @description:
 */
@Slf4j
@UtilityClass
public class ExceptionUtils {

    public static void handle(Throwable t) {
        if (Objects.isNull(t)) {
            return;
        }
        t.printStackTrace();
        log.error("Exception==>", t);
    }

    public static void handle(Throwable t, String message) {
        if (Objects.nonNull(t)) {
            log.error("ExceptionHelper==>{}", message);
            t.printStackTrace();
        }
    }
    public static String getException(Throwable t) {
        if (Objects.isNull(t)) {
            return null;
        }
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        t.printStackTrace(printWriter);
        printWriter.close();
        return writer.toString();
    }
}
