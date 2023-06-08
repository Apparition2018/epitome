package spring.servlet.captcha;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Serial;

/**
 * <a href="http://localhost:3333/captchaRandom.do">CaptchaRandomServlet</a>
 * <p>随机字符串验证码
 *
 * @author ljh
 * @since 2019/12/23 10:20
 */
public class CaptchaRandomServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 3291499956991044738L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CaptchaUtils.createCaptcha(request, response, "random");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doPost(request, response);
    }
}
