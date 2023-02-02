package jar.hutool.extra;

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
import java.io.Serial;
import java.net.HttpCookie;
import java.util.Map;

/**
 * <a href="https://hutool.cn/docs/#/extra/Servlet工具-ServletUtil">ServletUtil</a>
 * <p><a href="https://apidoc.gitee.com/dromara/hutool/cn/hutool/extra/servlet/ServletUtil.html">ServletUtil api</a>
 *
 * @author ljh
 * @since 2020/11/20 14:03
 */
@Slf4j
@WebServlet("/ServletUtil")
public class ServletUtilDemo extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 770260830268343491L;

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
                .cookie(new HttpCookie("cookie", "zero"))
                .execute();
    }

    static class M extends Demo {
    }
}
