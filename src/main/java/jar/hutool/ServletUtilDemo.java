package jar.hutool;

import cn.hutool.extra.servlet.ServletUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ServletUtil
 * https://hutool.cn/docs/#/extra/Servlet%E5%B7%A5%E5%85%B7-ServletUtil?id=%e4%bd%bf%e7%94%a8
 * https://apidoc.gitee.com/loolly/hutool/cn/hutool/extra/servlet/ServletUtil.html
 *
 * @author ljh
 * created on 2020/11/20 14:03
 */
@Slf4j
@WebServlet(name = "HutoolServlet", urlPatterns = "/HutoolServlet", initParams = {
        @WebInitParam(name = "password", value = "123456")
})
public class ServletUtilDemo extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        log.info("ParamMap: {}", ServletUtil.getParamMap(req));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        doPost(req, resp);
    }
}
