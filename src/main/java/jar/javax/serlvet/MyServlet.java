package jar.javax.serlvet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;

/**
 * 从 Servlet3.0 开始，配置 Servlet 支持注解方式
 * http://blog.csdn.net/mytt_10566/article/details/70173007
 */
@WebServlet(name = "myUserServlet", urlPatterns = "/user/test", loadOnStartup = 1, initParams = {
        @WebInitParam(name = "name", value = "小明"), @WebInitParam(name = "pwd", value = "123456")})
public class MyServlet extends HttpServlet {

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
        pw.append("Hello Servlet!<br />");
        pw.append("servletName: ").append(getServletName()).append("<br />");
        // initParam
        ServletConfig servletConfig = getServletConfig();
        Enumeration<String> paramNames = servletConfig.getInitParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            pw.append(paramName).append(": ").append(servletConfig.getInitParameter(paramName)).append("<br />");
        }
        pw.append("ServletContextName: ").append(request.getServletContext().getServletContextName()).append("<br />"); // Epitome
        pw.append("RealPath: ").append(request.getServletContext().getRealPath("")).append("<br />");                   // D:\L\git\epitome\src\main\webapp\
        
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
