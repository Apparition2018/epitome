package spring.servlet.captcha;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Serial;

/**
 * ValidServlet
 *
 * @author ljh
 * @since 2019/12/23 16:25
 */
public class ValidServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = -5740434607883686032L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CaptchaUtils.valid(request, response, "captcha");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doPost(request, response);
    }
}
