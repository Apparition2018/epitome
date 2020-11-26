package spring.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * GlobalExceptionHandler
 *
 * @author ljh
 * created on 2020/11/26 17:51
 */
@ControllerAdvice
public class BaseExceptionHandler {
    
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public JsonResult handleRuntimeException(Exception e) {
        return new JsonResult(e);
    }
}
