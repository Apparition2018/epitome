package spring.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * SpringBoot 拦截器：https://blog.csdn.net/qq_35098526/article/details/88734991
 * <p>
 * 实现自定义拦截器只需要3步
 * 1、创建我们自己的拦截器类并实现 HandlerInterceptor 接口。
 * 2、创建一个 Java 类继承 WebMvcConfigurationSupport，并重写 addInterceptors 方法。
 * 3、实例化我们自定义的拦截器，然后将对像手动添加到拦截器链中（在addInterceptors方法中添加）。
 * StopWatch + Interceptor 的使用：https://www.jb51.net/article/178518.html
 * Spring Interceptor 与 Servlet Filter：https://blog.csdn.net/qq_41788977/article/details/103610068
 *
 * @author ljh
 * created on 2019/8/8 19:39
 */
@Slf4j
public class HttpInterceptor extends HandlerInterceptorAdapter {

    private static final ThreadLocal<StopWatch> stopWatchThreadLocal = new ThreadLocal<>();

    // 请求处理之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        StopWatch stopWatch = new StopWatch();
        stopWatchThreadLocal.set(stopWatch);
        stopWatch.start();
        return true;
    }

    // 请求正常处理完之后
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        stopWatchThreadLocal.get().stop();
        stopWatchThreadLocal.get().start();
    }

    // 请求处理完之后
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        StopWatch stopWatch = stopWatchThreadLocal.get();
        stopWatch.stop();
        String fullMethodName = "";
        if (handler instanceof HandlerMethod) {
            String beanType = ((HandlerMethod) handler).getBeanType().getName();
            String method = ((HandlerMethod) handler).getMethod().getName();
            fullMethodName = beanType + "." + method;
        }
        if (null == ex) {
            log.info("requestUri[{}] method[{}] spend[{}ms-{}ms-{}ms]",
                    request.getRequestURI(),
                    fullMethodName,
                    stopWatch.getTotalTimeMillis(),
                    stopWatch.getTotalTimeMillis() - stopWatch.getLastTaskTimeMillis(),
                    stopWatch.getLastTaskTimeMillis());
        } else {
            log.info("requestUri[{}] method[{}] exception[{}] spend[{}ms-{}ms-{}ms]",
                    request.getRequestURI(),                            // 请求 URI
                    fullMethodName,                                     // 方法全路径
                    ex.getClass().getName() + ":" + ex.getMessage(),    // 异常
                    stopWatch.getTotalTimeMillis(),                     // 请求总消耗时间
                    stopWatch.getTotalTimeMillis() - stopWatch.getLastTaskTimeMillis(),
                    stopWatch.getLastTaskTimeMillis());
        }
        stopWatchThreadLocal.remove();
    }

}