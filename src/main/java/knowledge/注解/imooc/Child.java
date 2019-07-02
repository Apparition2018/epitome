package knowledge.注解.imooc;

public class Child extends Person {

    @Override
    public String name() {
        return null;
    }

    @Override
    public int age() {
        return 0;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void sing() {

    }
}