package knowledge.xml.dom4j;

import l.demo.Demo;
import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Dom4j
 * Dom4j 是 JDOM 的一种智能分支，它合并了许多超出基本xml文档表示的功能。
 *
 * @author ljh
 * @since 2020/11/10 11:40
 */
public class Dom4jDemo extends Demo {

    public static final String XML_PATH = DEMO_PATH + "demo.xml";

    @Test
    public void writeXML() throws IOException {
        // 创建 Document
        Document doc = DocumentHelper.createDocument();

        // 在 Branch 添加 Element
        Element school = doc.addElement("school");

        addStudent(school, "1", "张三", "18");
        addStudent(school, "2", "李四", "19");

        // 新建 XMLWriter
        XMLWriter xmlWriter = new XMLWriter(Files.newOutputStream(Paths.get(XML_PATH)), OutputFormat.createPrettyPrint());
        xmlWriter.write(doc);
        xmlWriter.close();
    }

    private void addStudent(Element school, String id, String nameText, String ageText) {
        Element student = school.addElement("student");
        // 在 Element 添加 Attribute
        student.addAttribute("id", id);

        student.addElement("name").addText(nameText);
        student.addElement("age").addText(ageText);
    }

    @Test
    public void readXML() throws DocumentException {
        // 新建 SAXReader
        SAXReader reader = new SAXReader();
        // 读取 XML 为 Document
        Document doc = reader.read(new File(XML_PATH));

        // 获取根 Element
        Element school = doc.getRootElement();

        // 获取指定名的 Element 列表
        List<Element> studentList = school.elements("student");
        for (Element student : studentList) {
            // 获取指定名的 Attribute
            Attribute idAttr = student.attribute("id");
            // 获取 Attribute 的值
            int id = Integer.parseInt(idAttr.getValue());

            // 获取指定名的 Element
            Element nameEle = student.element("name");
            // 获取 Element 的 Text
            String name = nameEle.getTextTrim();

            // 获取指定名的 Element 的 Text
            String age = student.elementText("age");

            p(name + ": id " + id + "，年龄" + age + "岁");
        }
    }

    /**
     * 需引入 jaxen
     */
    @Test
    public void xPath() throws DocumentException {
        SAXReader reader = new SAXReader();
        Document doc = reader.read(new File(XML_PATH));
        String xpath = "//student[@id='1' or age='19']/name";
        // 根据 XPath 表达式 select
        List<Node> nodeList = doc.selectNodes(xpath);
        nodeList.forEach(node -> p(node.getText()));
    }
}
