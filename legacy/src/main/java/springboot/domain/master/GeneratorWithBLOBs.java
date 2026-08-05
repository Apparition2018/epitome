package springboot.domain.master;

import java.io.Serial;

public class GeneratorWithBLOBs extends Generator {
    @Serial
    private static final long serialVersionUID = -4812799015218178530L;
    private String context;

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context == null ? null : context.trim();
    }
}
