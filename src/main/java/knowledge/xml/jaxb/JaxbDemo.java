package knowledge.xml.jaxb;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.experimental.Accessors;

import static l.demo.Demo.MY_NAME;

/**
 * Jaxb
 *
 * @author ljh
 * @since 2024/9/5 10:26
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
    @XmlRootElement(name = "person")
    @XmlAccessorType(XmlAccessType.FIELD)
    private static class Person {
        @XmlElement
        private String name;
        @XmlElement
        private Person.Home home;

        @Data
        @XmlAccessorType(XmlAccessType.FIELD)
        private static class Home {
            @XmlElement
            private String address;
        }
    }
}
