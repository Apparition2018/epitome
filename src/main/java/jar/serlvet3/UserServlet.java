package jar.serlvet3;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * 从Servlet3.0开始，配置Servlet支持注解方式
 * http://blog.csdn.net/mytt_10566/article/details/70173007
 */
@WebServlet(name = "myUserServlet", urlPatterns = "/user/test", loadOnStartup = 1, initParams = {
        @WebInitParam(name = "name", value = "小明"), @WebInitParam(name = "pwd", value = "123456")})
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("init...");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter pw = response.getWriter();
        pw.append("Hello Servlet!<br />");
        pw.append("servletName: ").append(getServletName()).append("<br />"); // servletName
        // initParam
        ServletConfig servletConfig = getServletConfig();
        Enumeration<String> paramNames = servletConfig.getInitParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            pw.append(paramName).append(": ").append(servletConfig.getInitParameter(paramName)).append("<br />");
        }
        pw.append("RequestURL: ").append(String.valueOf(request.getRequestURL())).append("<br />");        // http://localhost:8088/mavenTest/user/test
        pw.append("RequestURI: ").append(request.getRequestURI()).append("<br />");                        // /mavenTest/user/test
        pw.append("Schema: ").append(request.getScheme()).append("<br />");                            // http
        pw.append("ServerName: ").append(request.getServerName()).append("<br />");                        // localhost
        pw.append("ServerPort: ").append(String.valueOf(request.getServerPort())).append("<br />");        // 8080
        pw.append("ContextPath: ").append(request.getContextPath()).append("<br />");                    // /mavenTest
        pw.append("ServletPath: ").append(request.getServletPath()).append("<br />");                // /user/test
        pw.append("QueryString: ").append(request.getQueryString()).append("<br />");                    // null
        pw.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        doGet(request, response);
    }

}
