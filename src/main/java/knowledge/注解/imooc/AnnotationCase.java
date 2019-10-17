package knowledge.注解.imooc;

public class AnnotationCase {

    @SuppressWarnings("deprecation")
    public void sing() {
        Person p = new Child();
        p.sing();
    }
}
