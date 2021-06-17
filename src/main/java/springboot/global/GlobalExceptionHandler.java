package springboot.global;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * GlobalExceptionHandler
 * `@ControllerAdvice 是在类上声明的注解，用来声明一些全局性的东西
 * -    1.@ExceptionHandler 处理异常
 * -    2.@InitBinder       给 WebDataBinder 进行初始化
 * -    3.ModelAttribute    给 Model 绑定参数
 * `@RestControllerAdvice = @ControllerAdvice + @ResponseBody
 * <p>
 * `@RestControllerAdvice 作用及原理：https://www.cnblogs.com/UncleWang001/p/10949318.html
 *
 * @author ljh
 * created on 2020/11/26 17:51
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获 GET 参数异常 
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.info("MethodArgumentNotValidException");
        String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining("; "));
        Map<String, Object> map = new HashMap<>();
        map.put("code", 500);
        map.put("msg", message);
        return map;
    }

    /**
     * 捕获 POST @RequestBody 参数异常
     */
    @ExceptionHandler(BindException.class)
    public Map<String, Object> handleBindException(BindException e) {
        log.info("BindException");
        String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining("; "));
        Map<String, Object> map = new HashMap<>();
        map.put("code", 500);
        map.put("msg", message);
        return map;
    }

    /**
     * 捕获 Controller 上注解 @Validated 参数异常
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Map<String, Object> handleConstraintViolationException(ConstraintViolationException e) {
        log.info("ConstraintViolationException");
        String message = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining("; "));
        Map<String, Object> map = new HashMap<>();
        map.put("code", 500);
        map.put("msg", message);
        return map;
    }
}
