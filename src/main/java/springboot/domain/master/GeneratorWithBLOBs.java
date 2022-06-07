package springboot.domain.master;

public class GeneratorWithBLOBs extends Generator {
    private String context;

    private static final long serialVersionUID = 1L;

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context == null ? null : context.trim();
    }
}