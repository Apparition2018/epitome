package spring.servlet.captcha;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Serial;

/**
 * ValidKaptcha2Servlet
 *
 * @author ljh
 * @since 2019/12/24 0:09
 */
public class ValidKaptcha2Servlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 6347393967459354067L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CaptchaUtils.valid(request, response, "kaptcha2");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doPost(request, response);
    }
}
