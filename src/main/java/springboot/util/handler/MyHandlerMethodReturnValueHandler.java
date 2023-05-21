package springboot.util.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.apache.hc.core5.http.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.AsyncHandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import springboot.controller.WebMvcConfigController;
import springboot.result.Result;

import java.nio.charset.StandardCharsets;

/**
 * 返回值处理器
 *
 * @author ljh
 * @since 2021/8/11 16:07
 */
public class MyHandlerMethodReturnValueHandler implements AsyncHandlerMethodReturnValueHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public boolean isAsyncReturnValue(Object returnValue, @NonNull MethodParameter returnType) {
        return supportsReturnType(returnType);
    }

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return WebMvcConfigController.class.equals(returnType.getDeclaringClass()) &&
                !BasicErrorController.class.equals(returnType.getDeclaringClass()) &&
                !Result.class.equals(returnType.getParameterType());
    }

    @Override
    public void handleReturnValue(Object returnValue, @NonNull MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        mavContainer.setRequestHandled(true);
        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
        if (response != null) {
            response.setContentType(ContentType.APPLICATION_JSON.toString());
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            Result<?> result = Result.success(returnValue);
            response.getWriter().write(objectMapper.writeValueAsString(result));
        }
    }
}
