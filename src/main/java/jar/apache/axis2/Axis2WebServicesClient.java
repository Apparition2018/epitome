package jar.apache.axis2;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jar.apache.axis2.BorlandSampleService.Service1Stub;
import jar.apache.axis2.tokenService.Root;
import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import javax.xml.namespace.QName;
import java.io.StringWriter;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.Map;

/**
 * Axis2 Web Services 客户端
 * <p>公开的 Web Services：
 * <pre>
 * <a href="http://www.webxml.com.cn/zh_cn/web_services.aspx">WEB服务（Web Services）</a>
 * <a href="https://www.postman.com/cs-demo/public-soap-apis/collection/eebj1yq/public-soap-apis">Public SOAP APIs</a>
 * <a href="https://www.microfocus.com/documentation/silk-performer/195/en/silkperformer-195-webhelp-en/SILKPERF-9B8724D2-SOA-PUBLICWEBSERVICES.html">Public Web Services</a>
 * </pre>
 *
 * @author ljh
 * @see <a href="https://axis.apache.org/axis2/java/core/index.html">Apache Axis2</a>
 * @see <a href="https://www.bilibili.com/video/BV1WJ41167da/">WebServices Java 调用</a>
 * @since 2024/8/27 8:50
 */
public class Axis2WebServicesClient {

    /**
     * getChildElements & getChildrenWithName
     *
     * @see <a href="http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso">CountryInfoService</a>
     */
    @Test
    public void testServiceClient() throws AxisFault {
        final String WSDL_URL = "http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso?WSDL";
        // targetNamespace 的值
        final String NAMESPACE = "http://www.oorsprong.org/websamples.countryinfo";
        OMElement response = this.invoke(WSDL_URL, NAMESPACE, "FullCountryInfoAllCountries", Map.of());
        OMElement result = response.getFirstElement();
        // 获取所有子元素
        Iterator<OMElement> countryInfos = result.getChildElements();
        // 获取指定名字的元素
        countryInfos = result.getChildrenWithName(new QName(NAMESPACE, "tCountryInfo"));
        countryInfos.forEachRemaining(countryInfo -> {
            if (StringUtils.equals(countryInfo.getFirstChildWithName(new QName(NAMESPACE, "sName")).getText(), "China")) {
                System.err.println(countryInfo);
            }
        });
    }

    /**
     * 传递参数
     *
     * @see <a href="https://demo.borland.com/BorlandSampleService/BorlandSampleService.asmx">BorlandSampleService</a>
     */
    @Test
    public void testServiceClient2() throws AxisFault {
        final String WSDL_URL = "https://demo.borland.com/BorlandSampleService/BorlandSampleService.asmx?wsdl";
        final String NAMESPACE = "http://tempuri.org/";
        Map<String, Object> usr = Map.of(
            "FirstName", "ABC",
            "LastName", "L",
            "Street", "1 Street",
            "City", "ZS",
            "Age", "18"
        );
        OMElement response = this.invoke(WSDL_URL, NAMESPACE, "SetUserObject", Map.of("usr", usr));
        System.err.println(response.getFirstElement().getText());
    }

    /**
     * 生成客户端
     * <pre>
     * 1 下载 axis-1.x.x-bin.zip 并解压到指定目录
     * 2 设置环境变量 AXIS2_HOME 为解压后的目录路径，并将 AXIS2_HOME\bin 添加到 PATH
     * 3 在 cmd 输入：%AXIS2_HOME%\bin\wsdl2java.bat -uri xxx -p com.ljh
     * </pre>
     *
     * @see <a href="https://axis.apache.org/axis2/java/core/docs/userguide-creatingclients.html">Creating Clients</a>
     */
    @Test
    public void testGeneratedClient() throws RemoteException {
        final String WSDL_URL = "https://demo.borland.com/BorlandSampleService/BorlandSampleService.asmx?wsdl";
        Service1Stub stub = new Service1Stub(WSDL_URL);
        Service1Stub.User user = new Service1Stub.User();
        user.setFirstName("ABC");
        user.setLastName("L");
        user.setStreet("1 Street");
        user.setCity("ZS");
        user.setAge(18);
        Service1Stub.SetUserObject setUserObject10 = new Service1Stub.SetUserObject();
        setUserObject10.setUsr(user);
        Service1Stub.SetUserObjectResponse response = stub.setUserObject(setUserObject10);
        System.err.println(response.getSetUserObjectResult());
    }

    /**
     * 传递 xml 参数
     */
    @Test
    public void testXmlParam() throws AxisFault, JAXBException {
        final String WSDL_URL = "http://120.234.108.89/services/tokenService";
        final String NAMESPACE = "http://service.creditquery.portal.neusoft.com";
        final String METHOD = "getUploadingToken";
        final String username = "zss_tysbblpt";
        final String pwd = "zss_tysbblpt@0826";
        final String timestamp = String.valueOf(System.currentTimeMillis());
        String md5key = DigestUtils.md5Hex(username + pwd + timestamp);

        Root root = new Root();
        root.getData().setUsername(username);
        root.getData().setMd5key(md5key);
        root.getData().setTimestamp(timestamp);
        JAXBContext jaxbContext = JAXBContext.newInstance(Root.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter writer = new StringWriter();
        marshaller.marshal(root, writer);
        String in0 = writer.toString();

        OMElement response = this.invoke(WSDL_URL, NAMESPACE, METHOD, Map.of("in0", in0));
        System.err.println(response);
        // soap:operation 没有指定 style，所以也可以通过 rpc 调用
        response = this.invokeByRpc(WSDL_URL, NAMESPACE, METHOD, OMElement.class, in0);
        System.err.println(response);
    }

    /**
     * 使用 ServiceClient 调用 WebServices
     */
    private OMElement invoke(String address, String namespace, String method, Map<String, Object> params) throws AxisFault {
        ServiceClient serviceClient = new ServiceClient();
        EndpointReference endpointReference = new EndpointReference(address);
        Options options = serviceClient.getOptions();
        options.setTo(endpointReference);
        options.setAction(namespace.endsWith("/") ? namespace + method : namespace + "/" + method);

        OMFactory omFactory = OMAbstractFactory.getOMFactory();
        OMNamespace omNamespace = omFactory.createOMNamespace(namespace, StringUtils.EMPTY);
        OMElement omMethod = omFactory.createOMElement(method, omNamespace);
        this.setParamsText(omFactory, omNamespace, omMethod, params);
        omMethod.build();

        return serviceClient.sendReceive(omMethod);
    }

    private void setParamsText(OMFactory omFactory, OMNamespace omNamespace, OMElement omElement, Map<String, Object> params) {
        params.forEach((key, value) -> {
            OMElement param = omFactory.createOMElement(key, omNamespace);
            if (value instanceof Map) {
                this.setParamsText(omFactory, omNamespace, param, (Map<String, Object>) value);
            }
            param.setText(String.valueOf(value));
            omElement.addChild(param);
        });
    }

    /**
     * 使用 RPCServiceClient 调用 WebServices
     * <p>注意查看 soap:operation style 是否支持 rpc
     */
    @SuppressWarnings("unchecked")
    private <T> T invokeByRpc(String address, String namespace, String method, Class<T> resultTypes, Object... params) throws AxisFault {
        RPCServiceClient serviceClient = new RPCServiceClient();
        Options options = serviceClient.getOptions();
        options.setTo(new EndpointReference(address));
        options.setAction(namespace.endsWith("/") ? namespace + method : namespace + "/" + method);
        return (T) serviceClient.invokeBlocking(new QName(namespace, method), params, new Class[]{resultTypes})[0];
    }
}
