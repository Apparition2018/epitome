package jar.jakarta.xml.jaxb;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.experimental.Accessors;

import static l.demo.Demo.MY_NAME;

/**
 * JAXB
 *
 * @author ljh
 * @since 2023/2/9 9:30
 */
public class JaxbDemo {

    public static void main(String[] args) throws JAXBException {
        Person me = new Person().setName(MY_NAME).setHome(new Person.Home().setAddress("zs"));
        System.out.println(me);
        JAXBContext jaxbContext = JAXBContext.newInstance(Person.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(me, System.out);
    }

    @Data
    @Accessors(chain = true)
    @XmlRootElement
    static class Person {
        @XmlElement
        private String name;
        @XmlElement
        private Home home;

        @Data
        private static class Home {
            @XmlElement
            private String address;
        }
    }
}
