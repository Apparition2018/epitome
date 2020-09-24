package knowledge.注解;

import l.demo.Animal.Chicken;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * JDKAnnotation
 * <p>
 * JDK 自带注解
 * 1. @Override
 * 2. @Deprecation
 * 3. @SuppressWarnings 抑制编译器产生警告信息
 * 4. @SafeVarargs
 * https://www.cnblogs.com/fsjohnhuang/p/4040785.html
 *
 * @author JDKAnnotation
 * created on 2020/9/18 10:13
 */
public class JDKAnnotation {

    /**
     * deprecation  过期
     */
    @SuppressWarnings("deprecation")
    public void testDeprecation() {
        Chicken chicken = new Chicken();
        chicken.fly();
    }

    /**
     * unused       未使用
     */
    public void testUnused() {
        @SuppressWarnings("unused")
        int max = Math.max(2, 1);
    }

    /**
     * rawtypes     原生类型未使用泛型
     * unchecked    未检测转换
     */
    @SuppressWarnings(value = {"unchecked", "rawtypes"})
    public void testRawTypesAndUnchecked(String item) {
        List<Object> items = new ArrayList();
        items.add(item);
        System.out.println(items.size());
    }

    /**
     * SafeVarargs
     * https://www.cnblogs.com/cxuanBlog/p/10927483.html
     */
    @SafeVarargs
    public static <T> List<T> testSafeVarargs(T... t) {
        List<T> list = new ArrayList<>();
        Collections.addAll(list, t);
        return list;
    }

}
