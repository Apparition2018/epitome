package spring.global;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

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
public class GlobalHandler {

    @ExceptionHandler(RuntimeException.class)
    public Map<String, Object> handleRuntimeException(Exception e) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 500);
        map.put("msg", e.getMessage());
        return map;
    }
}
