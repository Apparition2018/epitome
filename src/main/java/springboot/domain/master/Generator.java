package springboot.domain.master;

import java.io.Serializable;

public class Generator extends GeneratorKey implements Serializable {
    private String name;

    private static final long serialVersionUID = 1L;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}