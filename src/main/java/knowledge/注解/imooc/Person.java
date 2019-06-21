package knowledge.注解.imooc;

@Description(desc = "I am class")
public class Person {

    @Description(desc = "I am class method")
    public String name() {
        return null;
    }

    public int age() {
        return 0;
    }

    @Deprecated
    public void sing() {

    }
}