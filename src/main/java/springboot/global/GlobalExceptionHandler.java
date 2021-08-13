package springboot.global;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.NoHandlerFoundException;
import springboot.exception.ServiceException;
import springboot.result.Result;
import springboot.result.ResultCode;

import javax.servlet.Servlet;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * GlobalExceptionHandler
 * <p>
 * 参考 SpringBlade 异常处理器 BladeRestExceptionTranslator.java
 * <p>
 * `@ControllerAdvice 是在类上声明的注解，用来声明一些全局性的东西
 * -    1.@ExceptionHandler 处理异常
 * -    2.@InitBinder       给 WebDataBinder 进行初始化
 * -    3.ModelAttribute    给 Model 绑定参数
 * `@RestControllerAdvice = @ControllerAdvice + @ResponseBody
 *
 * @author ljh
 * created on 2020/11/26 17:51
 */
// @Configuration 属性 proxyBeanMethods 详解：https://mingyang.blog.csdn.net/article/details/108238121
@Configuration(proxyBeanMethods = false)
// @ConditionalOnXXX：https://www.1024sky.cn/blog/article/511
@ConditionalOnClass({Servlet.class, DispatcherServlet.class})
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    @Autowired
    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * `@RequestParam
     */
    @ExceptionHandler({MissingServletRequestParameterException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<Object> handleException(MissingServletRequestParameterException e) {
        log.warn("缺少请求参数：{}", e.getMessage());
        return Result.failure(ResultCode.PARAM_MISS, String.format("缺少必要的请求参数: %s", e.getParameterName()));
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<Object> handleException(MethodArgumentTypeMismatchException e) {
        log.warn("请求参数格式错误：{}", e.getMessage());
        return Result.failure(ResultCode.PARAM_TYPE_ERROR, String.format("请求参数格式错误: %s", e.getName()));
    }

    /**
     * 对象接收参数 + @Valid
     */
    @ExceptionHandler({BindException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<Object> handleException(BindException e) {
        log.warn("参数绑定失败：{}", e.getMessage());
        String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining("; "));
        return Result.failure(ResultCode.PARAM_BIND_ERROR, message);
    }

    public Result<Object> handleException2(BindException e) {
        log.warn("参数绑定失败：{}", e.getMessage());
        return this.handleException(e.getBindingResult());
    }

    /**
     * 对象接收参数 + @Valid + @RequestBody
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<Object> handleException(MethodArgumentNotValidException e) {
        log.warn("参数验证失败：{}", e.getMessage());
        String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining("; "));
        return Result.failure(ResultCode.PARAM_BIND_ERROR, message);
    }

    public Result<Object> handleException2(MethodArgumentNotValidException e) {
        log.warn("参数验证失败：{}", e.getMessage());
        return this.handleException(e.getBindingResult());
    }

    private Result<Object> handleException(BindingResult result) {
        List<FieldError> fieldErrors = result.getFieldErrors();
        Locale locale = LocaleContextHolder.getLocale();
        String message = fieldErrors.stream().map(fieldError -> {
                    String field = fieldError.getField();
                    String errorMessage = messageSource.getMessage(fieldError, locale);
                    return String.format("%s: %s", field, errorMessage);
                }
        ).collect(Collectors.joining("; "));
        return Result.failure(ResultCode.PARAM_BIND_ERROR, message);
    }

    /**
     * Controller 类上 @Validated + JSR303 注解
     */
    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<Object> handleException(ConstraintViolationException e) {
        log.warn("参数验证失败：{}", e.getMessage());
        String message = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining("; "));
        return Result.failure(ResultCode.PARAM_VALID_ERROR, message);
    }

    public Result<Object> handleException2(ConstraintViolationException e) {
        log.warn("参数验证失败：{}", e.getMessage());
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        ConstraintViolation<?> violation = violations.iterator().next();
        String path = ((PathImpl) violation.getPropertyPath()).getLeafNode().getName();
        String message = String.format("%s:%s", path, violation.getMessage());
        return Result.failure(ResultCode.PARAM_VALID_ERROR, message);
    }

    @ExceptionHandler({NoHandlerFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Result<Object> handleError(NoHandlerFoundException e) {
        log.error("404 找不到请求：{}", e.getMessage());
        return Result.failure(ResultCode.NOT_FOUND, e.getMessage());
    }

    /**
     * 1.GET 请求使用了 @RequestBody
     * 2.@RequestBody request body is missing
     */
    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<Object> handleError(HttpMessageNotReadableException e) {
        log.error("消息不能读取：{}", e.getMessage());
        return Result.failure(ResultCode.MSG_NOT_READABLE, e.getMessage());
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public Result<Object> handleError(HttpRequestMethodNotSupportedException e) {
        log.error("不支持当前请求方法：{}", e.getMessage());
        return Result.failure(ResultCode.METHOD_NOT_SUPPORTED, e.getMessage());
    }

    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public Result<Object> handleError(HttpMediaTypeNotSupportedException e) {
        log.error("不支持当前媒体类型：{}", e.getMessage());
        return Result.failure(ResultCode.MEDIA_TYPE_NOT_SUPPORTED, e.getMessage());
    }

    @ExceptionHandler({ServiceException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<Object> handleError(ServiceException e) {
        log.error("业务异常", e);
        return Result.failure(e.getResultCode(), e.getMessage());
    }

//    @ExceptionHandler({Throwable.class})
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public R handleError(Throwable e) {
//        log.error("服务器异常", e);
//        ErrorLogPublisher.publishEvent(e, UrlUtil.getPath(WebUtil.getRequest().getRequestURI()));
//        return R.fail(ResultCode.INTERNAL_SERVER_ERROR, Func.isEmpty(e.getMessage()) ? ResultCode.INTERNAL_SERVER_ERROR.getMessage() : e.getMessage());
//    }
}
