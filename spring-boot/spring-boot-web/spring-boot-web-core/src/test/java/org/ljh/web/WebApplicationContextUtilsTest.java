package org.ljh.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * {@link WebApplicationContextUtils} 使用示例
 * <p>演示从 {@code ServletContext} 中获取 {@code WebApplicationContext} 的几种方式，</p>
 * <p>常用于 Filter、Servlet 等非 Spring 管理的组件中获取 Spring 容器。</p>
 *
 * @author ljh
 * @since 2026/07/30
 */
@SpringBootTest
@DisplayName("WebApplicationContextUtils 测试")
class WebApplicationContextUtilsTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    @DisplayName("getWebApplicationContext(ServletContext)：获取根 WebApplicationContext（未注册返回 null）")
    void getRootContext() {
        MockServletContext servletContext = new MockServletContext();
        // 模拟 ContextLoaderListener 将 root WebApplicationContext 注册到 ServletContext
        servletContext.setAttribute(
                WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE,
                applicationContext);

        WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        assertThat(wac).isNotNull();
        assertThat(wac).isSameAs(applicationContext);
        assertThat(wac.getBean(WebTestApplication.class)).isNotNull();
    }

    @Test
    @DisplayName("getWebApplicationContext(ServletContext, String)：按自定义属性名获取 WebApplicationContext")
    void getContextByCustomAttrName() {
        String customAttr = "myCustomContext";
        MockServletContext servletContext = new MockServletContext();
        servletContext.setAttribute(customAttr, applicationContext);

        WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(servletContext, customAttr);
        assertThat(wac).isNotNull();
        assertThat(wac).isSameAs(applicationContext);
    }

    @Test
    @DisplayName("getRequiredWebApplicationContext(ServletContext)：获取根 WebApplicationContext（未注册抛异常）")
    void getRequiredRootContext() {
        MockServletContext servletContext = new MockServletContext();
        servletContext.setAttribute(
                WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE,
                applicationContext);

        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        assertThat(wac).isNotNull();
    }

    @Test
    @DisplayName("从 WebApplicationContext 中获取 Bean 实例")
    void getBeanFromExtractedContext() {
        MockServletContext servletContext = new MockServletContext();
        servletContext.setAttribute(
                WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE,
                applicationContext);

        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        assertThat(wac.getBeanDefinitionNames()).isNotEmpty();

        WebTestApplication app = wac.getBean(WebTestApplication.class);
        assertThat(app).isNotNull();
    }
}
