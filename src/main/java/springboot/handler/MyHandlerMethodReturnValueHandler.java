package springboot.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import org.apache.hc.core5.http.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.AsyncHandlerMethodReturnValueHandler;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import springboot.result.Result;

import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

/**
 * 返回值处理器
 *
 * @author ljh
 * created on 2021/8/11 16:07
 */
public class MyHandlerMethodReturnValueHandler implements HandlerMethodReturnValueHandler, AsyncHandlerMethodReturnValueHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public boolean isAsyncReturnValue(Object returnValue, @NonNull MethodParameter returnType) {
        return supportsReturnType(returnType);
    }

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return !returnType.getParameterType().equals(Result.class);
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
