package org.ljh.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * I18n 国际化测试
 * <p>覆盖 MessageSource 消息解析 和 LocaleResolver 区域解析</p>
 */
@SpringBootTest
@DisplayName("I18n 测试")
class I18nTest {

    @Autowired
    private MessageSource messageSource;

    // ================================================
    // MessageSource 消息解析
    // ================================================

    @Test
    @DisplayName("根据 Locale 返回对应语言的文本")
    void resolveByLocale() {
        assertThat(messageSource.getMessage("welcome", null, Locale.ENGLISH)).isEqualTo("Welcome");
        assertThat(messageSource.getMessage("welcome", null, Locale.CHINA)).isEqualTo("欢迎");
        assertThat(messageSource.getMessage("welcome", null, Locale.JAPAN)).isEqualTo("ようこそ");
    }

    @Test
    @DisplayName("带占位符的消息，{0} 会被替换为传入的参数")
    void resolveWithArgs() {
        assertThat(messageSource.getMessage("user.greeting", new Object[]{"Tom"}, Locale.ENGLISH)).isEqualTo("Hello, Tom!");
        assertThat(messageSource.getMessage("user.greeting", new Object[]{"Tom"}, Locale.CHINA)).isEqualTo("你好，Tom！");
        assertThat(messageSource.getMessage("user.greeting", new Object[]{"Tom"}, Locale.JAPAN)).isEqualTo("Tomさん、こんにちは！");
    }

    @Test
    @DisplayName("不存在的 code，有默认值则返回默认值")
    void notFoundWithDefault() {
        String result = messageSource.getMessage("unknown", null, "默认文本", Locale.CHINA);
        assertThat(result).isEqualTo("默认文本");
    }

    @Test
    @DisplayName("不存在的 code 且无默认值，抛 NoSuchMessageException")
    void notFoundThrowsException() {
        assertThatThrownBy(() -> messageSource.getMessage("unknown", null, Locale.CHINA))
                .isInstanceOf(NoSuchMessageException.class);
    }

    @Test
    @DisplayName("LocaleContextHolder 绑定线程级 Locale，无需反复传参")
    void localeContextHolder() {
        Locale original = LocaleContextHolder.getLocale();
        try {
            LocaleContextHolder.setLocale(Locale.JAPAN);
            assertThat(messageSource.getMessage("order.status", new Object[]{"paid"}, LocaleContextHolder.getLocale()))
                    .isEqualTo("注文ステータス：paid");
        } finally {
            LocaleContextHolder.setLocale(original);
        }
    }

    // ================================================
    // LocaleResolver
    // ================================================

    @Test
    @DisplayName("AcceptHeaderLocaleResolver：从 Accept-Language 请求头解析区域")
    void acceptHeaderLocaleResolver() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Accept-Language", "zh-CN,zh;q=0.9");
        assertThat(new AcceptHeaderLocaleResolver().resolveLocale(request))
                .isEqualTo(Locale.SIMPLIFIED_CHINESE);
    }

    @Test
    @DisplayName("AcceptHeaderLocaleResolver：无请求头时返回默认区域")
    void acceptHeaderLocaleResolverDefault() {
        AcceptHeaderLocaleResolver resolver = new AcceptHeaderLocaleResolver();
        resolver.setDefaultLocale(Locale.JAPAN);
        assertThat(resolver.resolveLocale(new MockHttpServletRequest())).isEqualTo(Locale.JAPAN);
    }

    @Test
    @DisplayName("CookieLocaleResolver：写入后再读取，验证读写一致")
    void cookieLocaleResolverWriteAndRead() {
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        resolver.setLocale(request, response, Locale.JAPAN);

        assertThat(response.getCookies()).isNotEmpty();
        jakarta.servlet.http.Cookie cookie = response.getCookies()[0];

        // 用写入的 Cookie 构造新请求验证读取
        MockHttpServletRequest readRequest = new MockHttpServletRequest();
        readRequest.setCookies(cookie);
        assertThat(resolver.resolveLocale(readRequest)).isEqualTo(Locale.JAPAN);
    }

    @Test
    @DisplayName("CookieLocaleResolver：无 Cookie 时返回默认区域")
    void cookieLocaleResolverDefault() {
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setDefaultLocale(Locale.UK);
        assertThat(resolver.resolveLocale(new MockHttpServletRequest())).isEqualTo(Locale.UK);
    }

    @Test
    @DisplayName("SessionLocaleResolver：从 Session 中读取已存储的区域")
    void sessionLocaleResolver() {
        SessionLocaleResolver resolver = new SessionLocaleResolver();
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, Locale.US);
        assertThat(resolver.resolveLocale(request)).isEqualTo(Locale.US);
    }

    @Test
    @DisplayName("SessionLocaleResolver：无 Session 属性时返回默认区域")
    void sessionLocaleResolverDefault() {
        SessionLocaleResolver resolver = new SessionLocaleResolver();
        resolver.setDefaultLocale(Locale.FRANCE);
        assertThat(resolver.resolveLocale(new MockHttpServletRequest())).isEqualTo(Locale.FRANCE);
    }
}
