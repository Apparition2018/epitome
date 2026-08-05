package springboot.domain.master;

import java.io.Serial;
import java.io.Serializable;

public class GeneratorKey implements Serializable {
    @Serial
    private static final long serialVersionUID = 7957605046686104965L;
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
