package knowledge.xml;

import l.demo.Demo;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.Stack;
import java.util.stream.IntStream;

/**
 * Dom
 *
 * @author ljh
 * @since 2020/11/10 9:38
 */
public class DomDemo extends Demo {

    public static final String XML_PATH = DEMO_PATH + "demo.xml";

    /**
     * org.w3c.dom
     * <pre>
     * 定义：org.w3c.dom 的 XML 分析器将一个 XML 文档转换成一个对象模型的集合，又称 DOM 树。
     *      应用程序通过对这个对象模型的操作，来实现对 XML 文档数据的操作。
     * 优点：解析过程中树结构在内存中是持久的，方便修改数据和结构。
     * 缺点：当 XML 文件较大时，对内存耗费比较大，容易影响解析性能并造成内存溢出。
     * </pre>
     */
    static class OrgW3cDom {

        // 新建 Document 档建造器的工厂实例
        private final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        // 新建 Document 建造器
        private final DocumentBuilder builder = factory.newDocumentBuilder();

        public OrgW3cDom() throws ParserConfigurationException {
        }

        @Test
        public void writeXML() throws TransformerException {
            // 新建 Document
            Document doc = builder.newDocument();

            // 创建 Element
            Element school = doc.createElement("school");
            // 在节点列表的末端添加节点
            doc.appendChild(school);

            appendStudent(doc, school, "1", "张三", "18");
            appendStudent(doc, school, "2", "李四", "19");

            // 新建 Transformer 的工厂实例
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            // 新建 Transformer
            Transformer transformer = transformerFactory.newTransformer();
            // 设置输出属性
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            // 输出 XML 文件
            transformer.transform(new DOMSource(doc), new StreamResult(new File(XML_PATH)));
        }

        private void appendStudent(Document doc, Element school, String id, String nameText, String ageText) {
            Element student = doc.createElement("student");
            // 在 Element 添加 Attribute
            student.setAttribute("id", id);
            school.appendChild(student);

            Element name = doc.createElement("name");
            name.appendChild(doc.createTextNode(nameText));
            student.appendChild(name);

            Element age = doc.createElement("age");
            age.appendChild(doc.createTextNode(ageText));
            student.appendChild(age);
        }

        @Test
        public void readXML() throws IOException, SAXException {
            // 解析 XML 为 Document
            Document doc = builder.parse(XML_PATH);
            // 根据 Tag Name 获取 NodeList
            NodeList schools = doc.getElementsByTagName("school");

            for (int i = 0; i < schools.getLength(); i++) {
                // 根据 index 获取 Node，并向下转型为 Element
                Element school = (Element) schools.item(i);
                NodeList students = school.getElementsByTagName("student");
                for (int j = 0; j < students.getLength(); j++) {
                    Element student = (Element) students.item(j);
                    Node name = student.getElementsByTagName("name").item(0);
                    Node age = student.getElementsByTagName("age").item(0);
                    // 获取次节点及其子节点的文本内容
                    p(name.getTextContent() + ": " + age.getTextContent() + "岁");
                }
            }
        }
    }

    /**
     * org.xml.sax
     * <pre>
     * 定义：org.xml.sax 提供的访问模式是一种顺序模式，这是一种快速读写 XML 数据的方式。
     *      当使用 SAX 分析器对 XML 文档进行分析时，会触发一系列事件，并激活相应的事件处理函数，
     *      应用程序通过这些事件处理函数实现对 XML 文档的访问，因而 SAX 接口也被称作事件驱动接口。
     * 优点：分析能够立即开始，而不是等待所有的数据被处理，速度快，没有内存压力；适用于只需要访问 XML 文档中的数据
     * 缺点：编码比较困难，而且很难同时访问同一个文档中的多处不同数据；一旦经过了某个元素，我们没有办法返回去再去访问它，缺乏灵活性。
     * </pre>
     */
    static class OrgXmlSax {

        public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
            // 新建 SAXParser 的工厂实例
            SAXParserFactory factory = SAXParserFactory.newInstance();
            // 新建 SAXParser
            SAXParser saxParser = factory.newSAXParser();
            // 解析 XML，传入自定义 Handler
            saxParser.parse(new File(XML_PATH), new MyHandler());
        }

        static class MyHandler extends DefaultHandler {
            // 用来保存标签
            private final Stack<String> stack = new Stack<>();

            // 数据字段
            private String name;
            private String id;
            private String age;

            @Override
            public void startDocument() throws SAXException {
                super.startDocument();
            }

            @Override
            public void endDocument() throws SAXException {
                super.endDocument();
            }

            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                super.startElement(uri, localName, qName, attributes);

                // 将标签名压入栈
                stack.push(qName);

                // 输出属性
                IntStream.range(0, attributes.getLength()).forEach(i -> id = attributes.getValue(i));
            }

            @Override
            public void characters(char[] ch, int start, int length) throws SAXException {
                super.characters(ch, start, length);

                // 从栈 peek 标签
                String tag = stack.peek();
                switch (tag) {
                    case "name":
                        name = new String(ch, start, length);
                        break;
                    case "age":
                        age = new String(ch, start, length);
                        break;
                    default:
                        assert false : "has tags not been parsed";
                }
            }

            @Override
            public void endElement(String uri, String localName, String qName) throws SAXException {
                super.endElement(uri, localName, qName);

                // 从栈弹出标签，表示该标签解析完毕
                stack.pop();
                if ("student".equals(qName)) {
                    p(name + ": id " + id + "，年龄" + age + "岁");
                }
            }
        }
    }
}
