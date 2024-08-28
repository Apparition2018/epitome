package jar.apache.axis2;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * WebServices 客户端
 * <p>参考：
 * <pre>
 * <a href="https://www.bilibili.com/video/BV1WJ41167da/">WebServices Java 调用</a>
 * <a href="http://www.webxml.com.cn/zh_cn/web_services.aspx">WEB服务（Web Services）</a>
 * <a href="https://www.postman.com/cs-demo/public-soap-apis/collection/eebj1yq/public-soap-apis">Public SOAP APIs</a>
 * <a href="https://www.microfocus.com/documentation/silk-performer/195/en/silkperformer-195-webhelp-en/SILKPERF-9B8724D2-SOA-PUBLICWEBSERVICES.html">Public Web Services</a>
 * </pre>
 *
 * @author ljh
 * @since 2024/8/27 8:50
 */
public class WebServicesClientDemo {

    /**
     * getChildElements & getChildrenWithName
     *
     * @see <a href="http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso">CountryInfoService</a>
     */
    @Test
    public void testServiceClient() throws AxisFault {
        final String WSDL_URL = "http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso?WSDL";
        // SOAP 1.1 <xs:schema targetNamespace/> 的值
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
        // SOAP 1.2 <s:schema targetNamespace/> 的值
        final String NAMESPACE = "http://tempuri.org/";

        Map<String, Object> usr = Map.of(
            "FirstName", "ABC",
            "LastName", "L",
            "Street", "1 Street",
            "City", "ZS",
            "Age", "18"
        );
        Map<String, Object> params = new HashMap<>();
        params.put("usr", usr);
        OMElement result = this.invoke(WSDL_URL, NAMESPACE, "SetUserObject", params);
        System.err.println(result.getFirstElement().getText());
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
