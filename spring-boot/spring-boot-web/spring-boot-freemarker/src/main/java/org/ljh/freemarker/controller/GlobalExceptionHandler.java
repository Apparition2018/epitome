package org.ljh.freemarker.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 全局异常处理：将异常渲染到 error.ftlh
 *
 * @author ljh
 * @since 2026/08/23
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    public String notFound(NoHandlerFoundException ex, HttpServletRequest request, Model model) {
        model.addAttribute("status", 404);
        model.addAttribute("error", "Not Found");
        model.addAttribute("message", "请求的路径不存在：" + request.getRequestURI());
        model.addAttribute("path", request.getRequestURI());
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String serverError(Exception ex, HttpServletRequest request, Model model) {
        model.addAttribute("status", 500);
        model.addAttribute("error", ex.getClass().getSimpleName());
        model.addAttribute("message", ex.getMessage());
        model.addAttribute("path", request.getRequestURI());
        return "error";
    }
}
