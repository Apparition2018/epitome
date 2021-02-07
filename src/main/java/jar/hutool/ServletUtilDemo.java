package jar.hutool;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import l.demo.Demo;
import l.demo.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.HttpCookie;
import java.util.Map;

/**
 * ServletUtil
 * https://hutool.cn/docs/#/extra/Servlet%E5%B7%A5%E5%85%B7-ServletUtil?id=%e4%bd%bf%e7%94%a8
 * https://apidoc.gitee.com/loolly/hutool/cn/hutool/extra/servlet/ServletUtil.html
 *
 * @author ljh
 * created on 2020/11/20 14:03
 */
@Slf4j
@WebServlet("/ServletUtil")
public class ServletUtilDemo extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Header: {}", ServletUtil.getHeaderMap(req));
        log.info("Client IP: {}", ServletUtil.getClientIP(req));
        log.info("Param: {}", ServletUtil.getParamMap(req));
        log.info("Body: {}", ServletUtil.getBody(req));
        log.info("Cookie: {}", ServletUtil.readCookieMap(req));
        log.info("Person: {}", ServletUtil.fillBean(req, Person.class, true));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        doPost(req, resp);
    }

    @Test
    public void test() {
        Map<String, Object> params = BeanUtil.beanToMap(M.personList.get(0));
        HttpRequest.post("http://localhost:8080/ServletUtil")
                .form(params)
                .body(JSONUtil.toJsonStr(params))
                .cookie(new HttpCookie("cookie", "oreo"))
                .execute();
    }

    private static class M extends Demo {

    }
}
