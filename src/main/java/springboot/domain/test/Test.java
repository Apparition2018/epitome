package springboot.domain.test;

import java.io.Serializable;

public class Test implements Serializable {
    private Integer id;

    private String content;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = null == content ? null : content.trim();
    }
}