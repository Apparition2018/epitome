package jar.hutool.http;

import cn.hutool.http.webservice.SoapClient;

/**
 * <a href="https://doc.hutool.cn/pages/SoapClient/">SoapClient</a> Soap 客户端
 * <p>实现简便的 WebService 请求
 * <p>使用：
 * <pre>
 * 1 使用 SoapUI 解析 WSDL 地址，找到 WebService 方法和参数，得到的 XML 模板为：
 * {@code
 *  <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:web="http://WebXml.com.cn/">
 *      <soapenv:Header/>
 *      <soapenv:Body>
 *          <web:getCountryCityByIp>
 *              <!--Optional:-->
 *              <web:theIpAddress>?</web:theIpAddress>
 *          </web:getCountryCityByIp>
 *      </soapenv:Body>
 *  </soapenv:Envelope>}
 * 2 按照 SoapUI 中的相应内容构建 SOAP 请求
 *   2.1 方法名：web:getCountryCityByIp
 *   2.2 参数:web:theIpAddress
 *   2.3 命名空间，前缀为 web，URI为 http://WebXml.com.cn/
 * </pre>
 *
 * @author ljh
 * @see <a href="https://plus.hutool.cn/apidocs/cn/hutool/http/webservice/SoapClient.html">SoapClient api</a>
 * @since 2020/11/2 23:41
 */
public class SoapClientDemo {

    public static void main(String[] args) {
        // 新建客户端
        SoapClient soapClient = SoapClient.create("https://www.webxml.com.cn/WebServices/IpAddressSearchWebService.asmx")
            // 设置要请求的方法，此接口方法前缀为web，传入对应的命名空间
            .setMethod("web:getCountryCityByIp", "http://WebXml.com.cn/")
            // 设置参数，此处自动添加方法的前缀：web
            .setParam("theIpAddress", "218.21.240.106");

        // 发送请求，参数 true 表示返回一个格式化后的 XML 内容
        System.out.println(soapClient.send(true));
    }
}
