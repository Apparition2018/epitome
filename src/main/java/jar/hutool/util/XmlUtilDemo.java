package jar.hutool.util;

import cn.hutool.core.util.XmlUtil;
import l.demo.Demo;
import l.demo.Person.Student;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.xpath.XPathConstants;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * XmlUtil  XML工具
 * XmlUtil 封装了 JDK 自带的 w3c dom 解析和构建工具，简化 XML 的创建、读和写的过程
 * https://hutool.cn/docs/#/core/%E5%B7%A5%E5%85%B7%E7%B1%BB/XML%E5%B7%A5%E5%85%B7-XmlUtil
 * https://apidoc.gitee.com/loolly/hutool/cn/hutool/core/util/XmlUtil.html
 *
 * @author ljh
 * created on 2020/11/9 10:32
 */
public class XmlUtilDemo extends Demo {

    @Test
    public void writeXML() {
        Document doc = XmlUtil.createXml();

        Element school = XmlUtil.appendChild(doc, "school");

        appendStudent(school, "1", "张三", "18");
        appendStudent(school, "2", "李四", "19");

        XmlUtil.toFile(doc, DEMO_ABSOLUTE_PATH + "demo.xml");
    }

    private void appendStudent(Element school, String id, String nameText, String ageText) {
        Element student = XmlUtil.appendChild(school, "student");
        student.setAttribute("id", id);

        Element name = XmlUtil.appendChild(student, "name");
        XmlUtil.appendText(name, nameText);

        Element age = XmlUtil.appendChild(student, "age");
        XmlUtil.appendText(age, ageText);
    }

    @Test
    public void readXML() {
        Document doc = XmlUtil.readXML(DEMO_ABSOLUTE_PATH + "demo.xml");

        // 格式化输出
        p(XmlUtil.format(doc));

        // 获取根节点
        Element school = XmlUtil.getRootElement(doc);

        // 根据节点名获得子节点列表
        List<Element> studentEleList = XmlUtil.getElements(school, "student");
        for (Element studentEle : studentEleList) {
            // Node → Bean
            Student student = XmlUtil.xmlToBean(studentEle, Student.class);
            // Node → Map
            Map<String, Object> studentMap = XmlUtil.xmlToMap(studentEle);

            // Object → XML File
            XmlUtil.writeObjectAsXml(new File(DEMO_ABSOLUTE_PATH + "student.xml"), student);
            // XML File → Object
            p(XmlUtil.readObjectFromXml(new File(DEMO_ABSOLUTE_PATH + "student.xml")));

            // Map → Document
            Document studentDoc = XmlUtil.mapToXml(studentMap, "student");
            p(XmlUtil.format(studentDoc));
        }
    }

    /**
     * Xpath的更多介绍请看文章：https://www.ibm.com/developerworks/cn/doc/x-javaxpathapi.html
     */
    @Test
    public void testXPath() {
        Document doc = XmlUtil.readXML(DEMO_ABSOLUTE_PATH + "demo.xml");

        Element student = XmlUtil.getElementByXPath("//student[age='18']", doc);
        p(student.getElementsByTagName("name").item(0).getNodeValue()); // 张三

        String name = (String) XmlUtil.getByXPath("//student[age='18']/name/text()", doc, XPathConstants.STRING);
        p(name); // 张三
    }

}
