package jar.hutool.extra;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import l.demo.Demo;
import lombok.extern.slf4j.Slf4j;

import java.io.Serial;
import java.net.HttpCookie;
import java.util.Map;

/**
 * <a href="https://doc.hutool.cn/pages/ServletUtil/">ServletUtil</a>
 * <p>需要引入 javax.servlet:javax.servlet-api
 * <p><a href="https://plus.hutool.cn/apidocs/cn/hutool/extra/servlet/ServletUtil.html">ServletUtil api</a>
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
        // ServletUtil 不支持 jakarta.servlet 需要引入 javax.servlet:javax.servlet-api
        // log.info("Header: {}", ServletUtil.getHeaderMap(req));
        // log.info("Client IP: {}", ServletUtil.getClientIP(req));
        // log.info("Param: {}", ServletUtil.getParamMap(req));
        // log.info("Body: {}", ServletUtil.getBody(req));
        // log.info("Cookie: {}", ServletUtil.readCookieMap(req));
        // log.info("Person: {}", ServletUtil.fillBean(req, Person.class, true));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        doPost(req, resp);
    }

    public static void main(String[] args) {
        Map<String, Object> params = BeanUtil.beanToMap(M.personList.get(0));
        HttpResponse httpResponse = HttpRequest.post("http://localhost:8080/ServletUtil")
                .form(params)
                .body(JSONUtil.toJsonStr(params))
                .cookie(new HttpCookie("cookie", "zero"))
                .execute();
    }

    private static class M extends Demo {
    }
}
