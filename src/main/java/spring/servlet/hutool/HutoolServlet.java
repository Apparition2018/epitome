package spring.servlet.hutool;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * HutoolServlet
 *
 * @author ljh
 * created on 2020/11/20 14:36
 */
@WebServlet(name = "HutoolServlet", urlPatterns = "/HutoolServlet", initParams = {
        @WebInitParam(name = "password", value = "123456")
})
public class HutoolServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        ServletConfig servletConfig = getServletConfig();
        System.out.println(servletConfig);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        doPost(req, resp);
    }
}
