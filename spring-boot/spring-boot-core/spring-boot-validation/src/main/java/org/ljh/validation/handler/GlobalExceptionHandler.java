package org.ljh.validation.handler;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.validation.method.ParameterValidationResult;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 统一捕获三类校验异常并返回结构化 JSON。
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * {@code @RequestBody} 校验失败（DTO 字段级错误）。
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleBody(MethodArgumentNotValidException e) {
        Map<String, String> errors = e.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        fe -> fe.getDefaultMessage() == null ? "参数不合法" : fe.getDefaultMessage(),
                        (a, b) -> a,
                        LinkedHashMap::new));
        return Map.of("code", 400, "type", "method_argument", "errors", errors);
    }

    /**
     * {@code @PathVariable}/{@code @RequestParam} 校验失败（Spring 6.1+）。
     */
    @ExceptionHandler(HandlerMethodValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleParam(HandlerMethodValidationException e) {
        Map<String, String> errors = new LinkedHashMap<>();
        for (ParameterValidationResult r : e.getParameterValidationResults()) {
            String param = r.getMethodParameter().getParameterName();
            for (MessageSourceResolvable error : r.getResolvableErrors()) {
                errors.putIfAbsent(param,
                        error.getDefaultMessage() == null ? "参数不合法" : error.getDefaultMessage());
            }
        }
        return Map.of("code", 400, "type", "handler_method", "errors", errors);
    }

    /**
     * Service 方法级校验失败（AOP 拦截方法调用）。
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleConstraint(ConstraintViolationException e) {
        Map<String, String> errors = new LinkedHashMap<>();
        for (ConstraintViolation<?> cv : e.getConstraintViolations()) {
            errors.putIfAbsent(cv.getPropertyPath().toString(),
                    cv.getMessage() == null ? "参数不合法" : cv.getMessage());
        }
        return Map.of("code", 400, "type", "constraint_violation", "errors", errors);
    }
}
