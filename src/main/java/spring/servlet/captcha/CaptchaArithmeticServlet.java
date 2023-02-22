package spring.servlet.captcha;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Serial;

/**
 * CaptchaArithmeticServlet
 * <p>算术验证码
 *
 * @author ljh
 * @since 2019/12/23 22:52
 */
public class CaptchaArithmeticServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1857020615347822774L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CaptchaUtils.createCaptcha(request, response, "arithmetic");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doPost(request, response);
    }
}
