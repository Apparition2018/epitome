package springboot.domain.master;

import java.io.Serial;

public class Generator extends GeneratorKey {
    @Serial
    private static final long serialVersionUID = 6725387122281561550L;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}
