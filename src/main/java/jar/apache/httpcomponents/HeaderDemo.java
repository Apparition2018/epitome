package jar.apache.httpcomponents;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.message.BasicHttpResponse;


public class HeaderDemo {
	
	public static void main(String[] args) {
		/*
		 * HTTP响应是由服务器在接收和解释请求报文之后返回发送给客户端的报文
		 * 响应报文的第一行包含了协议版本，之后是数字状态码和相关联的文本段
		 */
		HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, "OK");
		System.out.println(response.getProtocolVersion()); 	// HTTP/1.1
		System.out.println(response.getStatusLine());		// 200
		System.out.println(response.getStatusLine().getStatusCode());	// OK
		System.out.println(response.getStatusLine().getReasonPhrase());	// HTTP/1.1 200 OK
		System.out.println("----------------------------------------");
		
		// 头部信息
		response.addHeader("Set-Cookie","c1=a; path=/; domain=localhost");
		response.addHeader("Set-Cookie","c2=b; path=\"/\", c3=c; domain=\"localhost\"");
		Header h1 = response.getFirstHeader("Set-Cookie");
		System.out.println(h1);
		Header h2 = response.getLastHeader("Set-Cookie"); // Set-Cookie: c1=a; path=/; domain=localhost
		System.out.println(h2);
		Header[] hs = response.getHeaders("Set-Cookie");  // Set-Cookie: c2=b; path="/", c3=c; domain="localhost"
		System.out.println(hs.length); // 2
		// 使用HeaderIterator接口获取所有头部信息
		HeaderIterator it = response.headerIterator("Set-Cookie");
		while (it.hasNext()) {
			System.out.println(it.next());
		}
		// BasicHeaderElementIterator提供解析HTTP报文到独立头部信息元素的方法方法
		BasicHeaderElementIterator it2 = new BasicHeaderElementIterator(response.headerIterator("Set-Cookie"));
		while (it2.hasNext()) {
			HeaderElement elem = it2.nextElement();
			System.out.println(elem.getName() + "=" + elem.getValue());
			NameValuePair[] params = elem.getParameters();
			for (NameValuePair param : params) {
				System.out.println("" + param);
			}
		}
		
	}

}
