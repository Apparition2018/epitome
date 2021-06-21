package springboot.global;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.DispatcherServlet;
import springboot.result.Result;
import springboot.result.ResultCode;

import javax.servlet.Servlet;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
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
 * <p>
 * `@RestControllerAdvice 作用及原理：https://www.cnblogs.com/UncleWang001/p/10949318.html
 * 利用@ControllerAdvice和ResponseBodyAdvice接口统一处理返回值：https://diamondfsd.com/make-you-web-app-graceful-return-value-process/
 *
 * @author ljh
 * created on 2020/11/26 17:51
 */
// springboot注解@Configuration属性proxyBeanMethods详解：https://mingyang.blog.csdn.net/article/details/108238121
@Configuration(proxyBeanMethods = false)
// spring注解@ConditionalOnXXX：https://www.1024sky.cn/blog/article/511
@ConditionalOnClass({Servlet.class, DispatcherServlet.class})
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    //    @ExceptionHandler({MissingServletRequestParameterException.class})
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public Result<Object> handleError(MissingServletRequestParameterException e) {
//        log.warn("缺少请求参数{}", e.getMessage());
//        String message = String.format("缺少必要的请求参数: %s", e.getParameterName());
//        return Result.failure(ResultCode.PARAM_MISS, message);
//    }
//
//    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public Result<Object> handleError(MethodArgumentTypeMismatchException e) {
//        log.warn("请求参数格式错误{}", e.getMessage());
//        String message = String.format("请求参数格式错误: %s", e.getName());
//        return Result.failure(ResultCode.PARAM_TYPE_ERROR, message);
//    }
//
//    @ExceptionHandler({MethodArgumentNotValidException.class})
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public Result<Object> handleError(MethodArgumentNotValidException e) {
//        log.warn("参数验证失败{}", e.getMessage());
//        return this.handleError(e.getBindingResult());
//    }
//
//    @ExceptionHandler({BindException.class})
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public Result<Object> handleError(BindException e) {
//        log.warn("参数绑定失败{}", e.getMessage());
//        return this.handleError(e.getBindingResult());
//    }
//
    private Result<Object> handleError(BindingResult result) {
        FieldError error = result.getFieldError();
        String message = String.format("%s:%s", error.getField(), error.getDefaultMessage());
        return Result.failure(ResultCode.PARAM_BIND_ERROR, message);
    }
//
//    @ExceptionHandler({ConstraintViolationException.class})
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public Result<Object> handleError(ConstraintViolationException e) {
//        log.warn("参数验证失败{}", e.getMessage());
//        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
//        ConstraintViolation<?> violation = violations.iterator().next();
//        String path = ((PathImpl) violation.getPropertyPath()).getLeafNode().getName();
//        String message = String.format("%s:%s", path, violation.getMessage());
//        return Result.failure(ResultCode.PARAM_VALID_ERROR, message);
//    }
//
//    @ExceptionHandler({NoHandlerFoundException.class})
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public Result<Object> handleError(NoHandlerFoundException e) {
//        log.error("404没找到请求:{}", e.getMessage());
//        return Result.failure(ResultCode.NOT_FOUND, e.getMessage());
//    }
//
//    @ExceptionHandler({HttpMessageNotReadableException.class})
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public Result<Object> handleError(HttpMessageNotReadableException e) {
//        log.error("消息不能读取:{}", e.getMessage());
//        return Result.failure(ResultCode.MSG_NOT_READABLE, e.getMessage());
//    }
//
//    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
//    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
//    public Result<Object> handleError(HttpRequestMethodNotSupportedException e) {
//        log.error("不支持当前请求方法:{}", e.getMessage());
//        return Result.failure(ResultCode.METHOD_NOT_SUPPORTED, e.getMessage());
//    }
//
//    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
//    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
//    public Result<Object> handleError(HttpMediaTypeNotSupportedException e) {
//        log.error("不支持当前媒体类型:{}", e.getMessage());
//        return Result.failure(ResultCode.MEDIA_TYPE_NOT_SUPPORTED, e.getMessage());
//    }
//
//    @ExceptionHandler({ServiceException.class})
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public Result<Object> handleError(ServiceException e) {
//        log.error("业务异常", e);
//        return Result.failure(e.getResultCode(), e.getMessage());
//    }

//    @ExceptionHandler({Throwable.class})
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public Result<Object> handleError(Throwable e) {
//        log.error("服务器异常", e);

//        ErrorLogPublisher.publishEvent(e, UrlUtil.getPath(WebUtil.getRequest().getRequestURI()));
//        return Result.failure(ResultCode.INTERNAL_SERVER_ERROR, Func.isEmpty(e.getMessage()) ? ResultCode.INTERNAL_SERVER_ERROR.getMessage() : e.getMessage());
//    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.info("MethodArgumentNotValidException");
//        String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining("; "));
        String message = e.getBindingResult().getAllErrors().stream().map(error -> {
            String s = error.getArguments()[0].toString();
            log.info(s);
            return error.getDefaultMessage();
        }).collect(Collectors.joining("; "));
        return Result.failure(ResultCode.PARAM_BIND_ERROR, message);
    }

    @ExceptionHandler(BindException.class)
    public Result<Object> handleBindException(BindException e) {
        log.info("BindException");
        String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining("; "));
        return Result.failure(ResultCode.PARAM_BIND_ERROR, message);
    }

    public Map<String, Object> handleConstraintViolationException(ConstraintViolationException e) {
        log.info("ConstraintViolationException");
        String message = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining("; "));
        Map<String, Object> map = new HashMap<>();
        map.put("code", 500);
        map.put("msg", message);
        return map;
    }

    public Map<String, Object> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.info("MissingServletRequestParameterException");
        String message = e.getMessage();
        Map<String, Object> map = new HashMap<>();
        map.put("code", 500);
        map.put("msg", message);
        return map;
    }
}
