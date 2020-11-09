package knowledge.api.beans;

import l.demo.Demo;
import l.demo.Person;
import org.junit.Test;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * XML
 *
 * @author ljh
 * created on 2020/11/9 14:07
 */
public class XMLDemo extends Demo {

    private static final File FILE = new File(DEMO_PATH + "person.xml");

    /**
     * Bean → XML
     */
    @Test
    public void testXMLEncoder() throws FileNotFoundException {
        XMLEncoder encoder = new XMLEncoder(new FileOutputStream(FILE));
        for (Person person : personList) {
            encoder.writeObject(person);
        }
        encoder.flush();
        encoder.close();
    }

    /**
     * XML → Bean
     */
    @Test
    public void testXMLDecoder() throws FileNotFoundException {
        List<Person> personList = new ArrayList<>();
        try (XMLDecoder decoder = new XMLDecoder(new FileInputStream(FILE))) {
            Object obj;
            while (null != (obj = decoder.readObject())) {
                personList.add((Person) obj);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            p("已读取所有" + e.getMessage() + "个对象");
        }
        p(personList);
    }

}
