package com.turingthink.rabbit.common.exception;

import com.turingthink.rabbit.common.R;
import com.turingthink.rabbit.common.utils.ExceptionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author: fight2048
 * @e-mail: fight2048@outlook.com
 * @blog: https://github.com/fight2048
 * @time: 2018-01-08 0008 下午 11:17
 * @version: v0.0.0
 * @description:
 */
@Slf4j
@RestControllerAdvice
public class BaseExceptionHandler {
    @Autowired
    private MessageSource messageSource;

    public String getMessage(String code, Object params) {
        return getMessage(code, new Object[]{Objects.isNull(params) ? "" : params});
    }

    public String getMessage(String code, @Nullable Object[] args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }

    /**
     * 笼统的异常处理，应对未知的异常
     *
     * @param e
     * @return
     */
    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(value = Exception.class)
    public Object exceptionHandler(Exception e) {
        ExceptionUtils.handle(e);
        return R.error(R.UNKNOW_ERROR, ExceptionUtils.getException(e));
    }
    /**********以下为Controller层异常************/
    /**
     * 请求方法不对
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public Object requestMethodErrorHandler(HttpRequestMethodNotSupportedException e) {
        ExceptionUtils.handle(e);
        String message = e.getMessage() + ".  Supported Methods:"
                + Arrays.deepToString(e.getSupportedMethods());
        return R.errorRequestMethod(message);
    }

    /**
     * 路径参数不对，参数作为路径的一部分
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = MissingPathVariableException.class)
    public Object missingPathVariableException(MissingPathVariableException e) {
        ExceptionUtils.handle(e);
        return R.errorRequestMethod(e.getMessage());
    }

    /**
     * 参数异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = ParamsException.class)
    public Object paramsExceptionHandler(ParamsException e) {
        ExceptionUtils.handle(e);
        String message = e.getMessage();
        if (e.getData() != null) {
            List<FieldError> fieldErrors = ((List<FieldError>) e.getData());
            List<String> data = fieldErrors.stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            message = data.toString();
        }
        return R.errorParams(message);
    }

    @ExceptionHandler(value = HttpMessageConversionException.class)
    public Object httpMessageConversionException(HttpMessageConversionException e) {
        ExceptionUtils.handle(e);
        return R.errorParams(e.getMessage());
    }

    @ExceptionHandler(value = TypeMismatchException.class)
    public Object typeMismatchException(TypeMismatchException e) {
        ExceptionUtils.handle(e);
        return R.errorParams(e.getMessage());
    }

    @ExceptionHandler(value = CustomException.class)
    public Object customExceptionHandler(CustomException e) {
        String message = getMessage(e.getCode().toString(), e.getMessage());
        ExceptionUtils.handle(e, message);
        return R.error(e.getCode(), message);
    }

    /**
     * 对应的是Hibernate-validator的@Valid注解
     * 对方法参数校验异常处理方法(仅对于表单提交有效，对于以json格式提交将会失效)
     * 如果是表单类型的提交，则spring会采用表单数据的处理类进行处理（进行参数校验错误时会抛出BindException异常）
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = BindException.class)
    public Object validateExceptionHandler(BindException e) {
        ExceptionUtils.handle(e);
        String message = e.getMessage();
        List<FieldError> errors = e.getBindingResult().getFieldErrors();
        if (errors != null) {
            List<String> data = errors.stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            message = data.toString();
        }
        return R.errorParams(message);
    }

    /**
     * 对应的是Spring的@Validated注解，@Validated对@Valid进行了二次封装
     * 在使用上并没有区别，但在分组、注解位置、嵌套验证等功能上有所不同，这里主要就这几种情况进行说明。
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public Object constraintViolationExceptionHandler(ConstraintViolationException e) {
        ExceptionUtils.handle(e);
        String message = e.getMessage();
        Set<ConstraintViolation<?>> errors = e.getConstraintViolations();
        if (errors != null) {
            List<String> data = errors.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toList());
            message = data.toString();
        }
        return R.errorParams(message);
    }

    /**
     * 对方法参数校验异常处理方法(前端提交的方式为json格式出现异常时会被该异常类处理)
     * json格式提交时，spring会采用json数据的数据转换器进行处理（进行参数校验时错误是抛出MethodArgumentNotValidException异常）
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handlerArgumentNotValidException(MethodArgumentNotValidException e) {
        ExceptionUtils.handle(e);
        String message = e.getMessage();
        List<FieldError> errors = e.getBindingResult().getFieldErrors();
        if (errors != null) {
            List<String> data = errors.stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            message = data.toString();
        }
        return R.errorParams(message);
    }

    /**********以下为Service层异常************/

    /**
     * Service层默认通用异常，由Service层传递出来结合国际化后返回前端
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = DefaultException.class)
    public Object defaultExceptionHandler(DefaultException e) {
        ExceptionUtils.handle(e);
        return R.error(e.getCode(), getMessage(e.getCode() + "", e.getMessage()));
    }

    @ExceptionHandler(value = OperationException.class)
    public Object operationExceptionHandler(OperationException e) {
        ExceptionUtils.handle(e);
        return R.error(e.getCode(), getMessage(e.getCode() + "", null));
    }

    @ExceptionHandler(value = CodeException.class)
    public Object codeExceptionHandler(CodeException e) {
        ExceptionUtils.handle(e);
        return R.error(e.getCode(), getMessage(e.getCode() + "", e.getMessage()));
    }

    /**
     * 基于自定义的异常处理，没有使用shiro或spring security的异常类
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = AuthenticationException.class)
    public Object authenticationExceptionHandler(AuthenticationException e) {
        ExceptionUtils.handle(e);
        return R.error(e.getCode(), getMessage(e.getCode() + "", e.getMessage()));
    }

    /**
     * 数据库异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = DuplicateKeyException.class)
    public Object duplicateKeyExceptionHandler(DuplicateKeyException e) {
        ExceptionUtils.handle(e);
        return R.error(R.DATABASE_ERROR, e.getMessage());
    }

    @ExceptionHandler(value = DatabaseException.class)
    public Object databaseExceptionHandler(DatabaseException e) {
        ExceptionUtils.handle(e);
        return R.error(e.getCode(), getMessage(e.getCode() + "", e.getMessage()));
    }
}
