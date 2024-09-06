package jar.apache.axis2.tokenService;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

/**
 * Root
 *
 * @author ljh
 * @since 2024/9/5 10:30
 */
@Getter
@Setter
@XmlRootElement(name = "root")
@XmlAccessorType(XmlAccessType.FIELD)
public class Root {

    @XmlElement(name = "data")
    private Data data;

    public Root() {
        this.data = new Data();
    }

    @Getter
    @Setter
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Data {
        @XmlElement(name = "username")
        private String username;
        @XmlElement(name = "md5key")
        private String md5key;
        @XmlElement(name = "timestamp")
        private String timestamp;
    }
}
