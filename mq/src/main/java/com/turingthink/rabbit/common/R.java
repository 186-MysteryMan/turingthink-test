package com.turingthink.rabbit.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * @version v0.0.0
 * @author: fight2048
 * @e-mail: fight2048@outlook.com
 * @blog: https://github.com/fight2048
 * @time: 2020-03-07 0007 下午 10:46
 * @description:
 */
@Slf4j
@Data
@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "基础返回类", description = "基础返回类")
public class R<T> implements Serializable {
    /**
     * 未知错误
     */
    public static final Integer UNKNOW_ERROR = -1;

    /**
     * *****************200系列***************
     * 成功
     */
    public static final Integer SUCCESS = 200;

    /**
     * *****************400系列***************
     * 请求方法错误
     */
    public static final Integer REQUEST_METHOD_ERROR = 405;
    /**
     * 需要登录，有可能登出或者账户在其他地方登录
     */
    public static final Integer LOGOUT = 403;
    /**
     * 没有认证
     */
    public static final Integer UNAUTHENTICATED = 456;
    /**
     * 没有权限
     */
    public static final Integer UNAUTHORIZED = 457;
    /**
     * 认证失败
     */
    public static final Integer AUTHENTICATION_ERROR = 458;

    /**
     * *****************500系列***************
     * 应用ID错误，多租户中的租户id错误
     */
    public static final Integer APP_ID_ERROR = 567;
    /**
     * 缺少应用ID
     */
    public static final Integer APP_ID_MISSING = 568;

    /**
     * *****************600系列Controller层问题***************
     * 非法参数：可能是格式不对，可能是类型不对，具体看message提示
     */
    public static final Integer PARAMS_ERROR = 600;
    /**
     * 重复请求，如：验证码[10]分钟内有效
     */
    public static final Integer REPEAT_ERROR = 601;

    /**
     * *****************700系列Service业务层问题***************
     * 验证码错误
     */
    public static final Integer CODE_ERROR = 710;
    /**
     * 账号无效或者账号被冻结锁定
     */
    public static final Integer ACCOUNT_UNAVAILABLE = 720;
    /**
     * 验证码失效
     */
    public static final Integer CODE_DISABLED = 711;
    /**
     * 获取验证码错误
     */
    public static final Integer CODE_GET_ERROR = 712;
    /**
     * 该账号已注册
     */
    public static final Integer ACCOUNT_REGISTERED = 721;
    /**
     * 该账号不存在
     */
    public static final Integer ACCOUNT_ERROR = 722;
    /**
     * 密码错误
     */
    public static final Integer PASSWORD_ERROR = 723;
    /**
     * token错误
     */
    public static final Integer TOKEN_ERROR = 724;
    /**
     * 无效登录，比如token在其他地方登录后该token失效
     */
    public static final Integer TOKEN_INVALID = 725;
    /**
     * token过期
     */
    public static final Integer TOKEN_EXPIRED = 726;
    /**
     * 笼统的操作异常，操作失败等情况
     */
    public static final Integer OPERATION_EXCEPTION = 727;
    /**
     * 操作错误，比如有前置操作
     */
    public static final Integer OPERATION_ERROR = 728;
    /**
     * 操作错误，请先绑定手机
     */
    public static final Integer OPERATION_ERROR_NO_PHONE = 729;
    /**
     * 账号或密码错误
     */
    public static final Integer ACCOUNT_CREDENTIALS_ERROR = 789;

    /**
     * *****************800系列DAO数据层问题***************
     * 数据库异常
     */
    public static final Integer DATABASE_ERROR = 800;
    /**
     * 数据不存在
     */
    public static final Integer DATA_INEXISTENT = 801;
    /**
     * 数据已存在
     */
    public static final Integer DATA_EXISTED = 802;


    /**
     * ******************900系列业务问题***************
     * 库存不足
     */
    public static final Integer STOCK_NOT_ENOUGH = 900;
    /**
     * 订单已被取消
     */
    public static final Integer ORDER_HAS_BEEN_CANCELED = 901;

    @ApiModelProperty(required = true, value = "状态码，200表示成功，非200表示其他意义", example = "200")
    public Integer code;
    @ApiModelProperty(required = true, value = "业务提示语", example = "OK")
    public String message;
    @ApiModelProperty(required = true, value = "当前页码", example = "1")
    public Long page;
    @ApiModelProperty(required = true, value = "每一页的数目", example = "20")
    public Long pageSize;
    @ApiModelProperty(value = "第一页url")
    public String first;
    @ApiModelProperty(value = "下一页url")
    public String next;
    @ApiModelProperty(value = "上一页url")
    public String previous;
    @ApiModelProperty(value = "最后一页url")
    public String last;
    @ApiModelProperty(value = "总记录数", example = "200")
    public Long total;
    @ApiModelProperty(value = "总页数", example = "10")
    public Long pageCount;
    @ApiModelProperty(value = "具体业务的数据对象")
    public T data;

    public R() {
    }

    public R(Integer code) {
        this.code = code;
        this.message = getMessage(code + "", null);
    }

    public R(Integer code, String message) {
        this(code);
        this.message = message;
    }

    public R(Integer code, String message, T data) {
        this(code, message);
        this.data = data;
    }

    public R(Integer code, T data) {
        this(code);
        this.data = data;
    }

    public R(R<T> response) {
        this.code = response.code;
        this.message = response.message;
        this.data = response.data;
        this.page = response.page;
        this.pageSize = response.pageSize;
        this.first = response.first;
        this.next = response.next;
        this.previous = response.previous;
        this.last = response.last;
    }

    @JsonIgnore
    public Boolean isSuccessful() {
        return Objects.equals(code, SUCCESS);
    }

    @JsonIgnore
    public Boolean isLogout() {
        return Objects.equals(code, LOGOUT);
    }

    @Override
    public String toString() {
        String json = JsonHelper.object2Json(this);
        log.debug("json-->" + json);
        return json;
    }

    public R<T> newBuilder() {
        return new R<T>(this);
    }

    public static R<?> success() {
        return success(null);
    }

    public static <T> R<T> pagination(Long page, Long pageSize, Long total, T data) {
        R<T> response = success(data);
        response.setTotal(total);
        response.setPage(page);
        response.setPageSize(pageSize);
        response.setPageCount((long) Math.ceil((double) total / (double) pageSize));
        return response;
    }

    public static <T> R<T> success(String message) {
        return new R<>(SUCCESS, message);
    }

    public static <T> R<T> success(T data) {
        R<T> response = new R<>(SUCCESS, "success");
        response.setData(data);
        return response;
    }

    public static <T> R<T> success(String message, T data) {
        R<T> response = new R<>(SUCCESS, message);
        response.setData(data);
        return response;
    }

    public static <T> R<T> error(Integer code, String message) {
        return new R<T>(code, message);
    }

    public static <T> R<T> error(Integer code, String message, T data) {
        return new R<T>(code, message, data);
    }

    public static <T> R<T> error(Integer code, T data) {
        return new R<T>(code, data);
    }

    public static <T> R<T> error(Integer code) {
        return new R<T>(code);
    }

    public static <T> R<T> errorParams(T data) {
        return error(R.PARAMS_ERROR, data);
    }

    public static <T> R<T> errorLogout() {
        return error(R.LOGOUT);
    }

    public static <T> R<T> errorAuthorize() {
        return error(R.LOGOUT);
    }

    public static <T> R<T> errorUnauthorized() {
        return error(R.UNAUTHORIZED);
    }

    public static <T> R<T> operationException() {
        return error(R.OPERATION_EXCEPTION);
    }

    public static <T> R<T> errorRequestMethod(T data) {
        return error(R.REQUEST_METHOD_ERROR, data);
    }

    public String getMessage(String result, Object[] params) {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setCacheSeconds(-1);
        messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
        messageSource.setBasenames("classpath:i18n/message");

        String message = "";
        try {
            message = messageSource.getMessage(result, params, LocaleContextHolder.getLocale());
        } catch (Exception e) {
            log.error("parse message error! ", e);
        }
        return message;
    }
}
