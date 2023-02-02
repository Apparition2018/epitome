package springboot.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * Interceptor  拦截器
 * <p>自定义拦截器步骤：
 * <pre>
 * 1 创建我们自己的拦截器类并实现 HandlerInterceptor 接口
 * 2 创建一个 Java 类继承 WebMvcConfigurer/WebMvcConfigurationSupport，并重写 addInterceptors 方法
 * 3 实例化自定义的拦截器，并添加到拦截器链中
 * </pre>
 * 参考：
 * <pre>
 * <a href="http://doc.ruoyi.vip/ruoyi/document/htsc.html#防重复提交">RuoYi 防重复提交</a> (RepeatSubmitInterceptor)
 * <a href="https://blog.csdn.net/qq_35098526/article/details/88734991">Web MvcConfigurer & HandlerInterceptorAdapter</a>
 * <a href="https://blog.csdn.net/qq_41788977/article/details/103610068">Spring Interceptor & Servlet Filter</a>
 * <a href="https://www.baeldung.com/spring-mvc-handlerinterceptor-vs-filter">HandlerInterceptors vs. Filters in Spring MVC</a>
 * </pre>
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
@Slf4j
public class HttpInterceptor implements HandlerInterceptor {

    private static final ThreadLocal<StopWatch> stopWatchThreadLocal = new ThreadLocal<>();

    /**
     * <pre>
     * 调用时间：Controller 方法处理之前
     * 执行顺序：按照 Interceptor 声明顺序执行
     * </pre>
     */
    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) {
        StopWatch stopWatch = new StopWatch();
        stopWatchThreadLocal.set(stopWatch);
        stopWatch.start();
        return true;
    }

    /**
     * <pre>
     * 调用前提：preHandle 返回 true
     * 调用时间：Controller 方法处理完之后，DispatcherServlet 进行视图渲染之前
     * 执行顺序：按照 Interceptor 声明倒序执行
     * </pre>
     */
    @Override
    public void postHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler, ModelAndView modelAndView) {
        stopWatchThreadLocal.get().stop();
        stopWatchThreadLocal.get().start();
    }

    /**
     * <pre>
     * 调用前提：preHandle 返回 true
     * 调用时间：DispatcherServlet 进行视图渲染之后
     * </pre>
     */
    @Override
    public void afterCompletion(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler, Exception ex) {
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
