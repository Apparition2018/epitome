package springboot.domain.master;

import java.io.Serializable;

public class GeneratorWithBLOBs extends Generator implements Serializable {
    private String context;

    private static final long serialVersionUID = 1L;

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context == null ? null : context.trim();
    }
}