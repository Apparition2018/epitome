package jar.apache.http;

import org.apache.http.*;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.message.BasicHttpResponse;

import static l.demo.Demo.p;

/**
 * @author ljh
 * created on 2020/11/12 21:35
 */
public class HeaderDemo {

    public static void main(String[] args) {
        /*
         * HTTP 响应是由服务器在接收和解释请求报文之后返回发送给客户端的报文
         * 响应报文的第一行包含了协议版本，之后是数字状态码和相关联的文本段
         */
        HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, "OK");
        p(response.getProtocolVersion());               // HTTP/1.1
        p(response.getStatusLine());                    // 200
        p(response.getStatusLine().getStatusCode());    // OK
        p(response.getStatusLine().getReasonPhrase());  // HTTP/1.1 200 OK
        p("----------------------------------------");


        //********** Header **********
        response.addHeader("Set-Cookie", "c1=a; path=/; domain=localhost");
        response.addHeader("Set-Cookie", "c2=b; path=\"/\", c3=c; domain=\"localhost\"");
        Header h1 = response.getFirstHeader("Set-Cookie");
        p(h1); // Set-Cookie: c1=a; path=/; domain=localhost
        Header h2 = response.getLastHeader("Set-Cookie");
        p(h2); // Set-Cookie: c2=b; path="/", c3=c; domain="localhost"
        Header[] hs = response.getHeaders("Set-Cookie");
        p(hs.length + "\n"); // 2

        // 使用 HeaderIterator 接口获取所有头部信息
        HeaderIterator it = response.headerIterator("Set-Cookie");
        while (it.hasNext()) {
            p(it.next());
        }
        p();

        // BasicHeaderElementIterator 提供解析 HTTP 报文到独立头部信息元素的方法方法
        BasicHeaderElementIterator it2 = new BasicHeaderElementIterator(response.headerIterator("Set-Cookie"));
        while (it2.hasNext()) {
            HeaderElement elem = it2.nextElement();
            p(elem.getName() + "=" + elem.getValue());
            NameValuePair[] params = elem.getParameters();
            for (NameValuePair param : params) {
                p(param);
            }
        }
    }

}
