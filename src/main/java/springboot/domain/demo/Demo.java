package springboot.domain.demo;

import java.io.Serializable;

public class Demo implements Serializable {

    private static final long serialVersionUID = -388873145067363215L;
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}