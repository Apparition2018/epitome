package spring.global;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

/**
 * GlobalExceptionHandler
 * `@ControllerAdvice 是在类上声明的注解，用来声明一些全局性的东西
 * -    1.@ExceptionHandler 用于捕获 Controller 中抛出的不同类型的异常，从而达到异常全局处理的目的
 * -    2.@InitBinder       用于请求中注册自定义参数的解析，从而达到自定义请求参数格式的目的
 * -    3.ModelAttribute    表示此方法会在执行目标 Controller 方法之前执行
 * `@RestControllerAdvice = @ControllerAdvice + @ResponseBody
 *
 * @author ljh
 * created on 2020/11/26 17:51
 */
@RestControllerAdvice
public class GlobalHandler {

    @ExceptionHandler(RuntimeException.class)
    public ModelAndView handleRuntimeException(Exception e) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", e.getMessage());
        modelAndView.setViewName("error");
        return modelAndView;
    }
}
