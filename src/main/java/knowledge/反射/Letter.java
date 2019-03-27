package knowledge.反射;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

class Letter {

    private final static String LETTER = "LETTER";

    @Value
    private String a;

    private String b;

    private String c;

    Letter() {
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

}

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface Value {

    String value() default "";

}