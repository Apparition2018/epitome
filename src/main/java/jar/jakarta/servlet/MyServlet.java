package jar.jakarta.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;

/**
 * <a href="http://blog.csdn.net/mytt_10566/article/details/70173007">@WebServlet 注解配置 Servlet，since Servlet3.0</a>
 *
 * @author ljh
 * @since 2020/11/23 19:39
 */
@WebServlet(name = "myUserServlet", urlPatterns = "/user/test", loadOnStartup = 1, initParams = {
        @WebInitParam(name = "name", value = "小明"), @WebInitParam(name = "pwd", value = "123456")})
public class MyServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 7107543066863138876L;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("init...");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        PrintWriter pw = response.getWriter();
        pw.append("***** Hello Servlet! *****<br />");
        pw.append("servletName: ").append(getServletName()).append("<br />");   // myUserServlet

        pw.append("<br />***** ServletContext *****<br />");
        ServletContext servletContext = getServletContext();
        Enumeration<String> contextParamNames = servletContext.getInitParameterNames();
        while (contextParamNames.hasMoreElements()) {
            String paramName = contextParamNames.nextElement();
            pw.append(paramName).append(": ").append(servletContext.getInitParameter(paramName)).append("<br />");
        }

        pw.append("<br />***** ServletConfig *****<br />");
        Enumeration<String> initParamNames = getInitParameterNames();
        while (initParamNames.hasMoreElements()) {
            String paramName = initParamNames.nextElement();
            pw.append(paramName).append(": ").append(getInitParameter(paramName)).append("<br />");
        }

        pw.append("<br />***** Request.ServletContext *****<br />");
        ServletContext requestServletContext = request.getServletContext();
        pw.append("ServletContextName: ").append(requestServletContext.getServletContextName()).append("<br />"); // Epitome
        pw.append("RealPath: ").append(requestServletContext.getRealPath("")).append("<br />");                   // D:\L\git\epitome\src\main\webapp\

        pw.append("<br />***** Request.Session.ServletContext *****<br />");
        ServletContext sessionServletContext = request.getSession().getServletContext();
        pw.append("Online: ").append(sessionServletContext.getAttribute("count").toString()).append("<br />");

        pw.append("<br />***** Request *****<br />");
        pw.append("RequestURL: ").append(request.getRequestURL()).append("<br />");                     // http://localhost:8080/epitome/user/test
        pw.append("RequestURI: ").append(request.getRequestURI()).append("<br />");                     // /epitome/user/test
        pw.append("Schema: ").append(request.getScheme()).append("<br />");                             // http
        pw.append("ServerName: ").append(request.getServerName()).append("<br />");                     // localhost
        pw.append("ServerPort: ").append(String.valueOf(request.getServerPort())).append("<br />");     // 8080
        pw.append("ContextPath: ").append(request.getContextPath()).append("<br />");                   // /epitome
        pw.append("ServletPath: ").append(request.getServletPath()).append("<br />");                   // /user/test
        pw.append("QueryString: ").append(request.getQueryString()).append("<br />");                   // null
        pw.close();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request, response);
    }
}
