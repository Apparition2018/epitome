package spring.servlet.captcha;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * CaptchaArithmeticServlet
 * 算术验证码
 *
 * @author ljh
 * @since 2019/12/23 22:52
 */
public class CaptchaArithmeticServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CaptchaUtils.createCaptcha(request, response, "arithmetic");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doPost(request, response);
    }
}
