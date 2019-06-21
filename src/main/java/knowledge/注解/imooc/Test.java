package knowledge.注解.imooc;

public class Test {

    @SuppressWarnings("deprecation")
    public void sing() {
        Person p = new Child();
        p.sing();
    }
}
