package spring.servlet.captcha;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ValidCodeServlet
 *
 * @author NL-PC001
 * created on 2019/12/23 16:25
 */
public class ValidCodeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.得到数据
        String inCode = request.getParameter("inCode");
        String validCode = request.getSession().getAttribute("validCode").toString();
        // 2.验证是否正确
        if (inCode.equalsIgnoreCase(validCode)) {
            response.sendRedirect("index.jsp");
        } else {
            request.getSession().setAttribute("err", "验证码输入错误，请重新输入");
            // 返回上一页
            response.sendRedirect(request.getHeader("Referer"));
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
