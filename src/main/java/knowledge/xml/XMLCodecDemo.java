package knowledge.xml;

import l.demo.Demo;
import l.demo.Person;
import org.junit.jupiter.api.Test;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * XMLCodec
 *
 * @author ljh
 * @since 2020/11/9 14:07
 */
public class XMLCodecDemo extends Demo {

    private static final File FILE = new File(DEMO_DIR_PATH + "person.xml");

    /** Bean → XML */
    @Test
    public void testXMLEncoder() throws IOException {
        XMLEncoder encoder = new XMLEncoder(Files.newOutputStream(FILE.toPath()));
        for (Person person : personList) {
            encoder.writeObject(person);
        }
        encoder.flush();
        encoder.close();
    }

    /** XML → Bean */
    @Test
    public void testXMLDecoder() throws IOException {
        List<Person> personList = new ArrayList<>();
        try (XMLDecoder decoder = new XMLDecoder(Files.newInputStream(FILE.toPath()))) {
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
