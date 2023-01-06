package spring.servlet.captcha;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * CaptchaRandomServlet
 * <p>随机字符串验证码
 *
 * @author ljh
 * @since 2019/12/23 10:20
 */
public class CaptchaRandomServlet extends HttpServlet {

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
