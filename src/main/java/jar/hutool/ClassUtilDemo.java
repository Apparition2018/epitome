package jar.hutool;

import cn.hutool.core.util.ClassUtil;
import l.demo.Demo;
import l.demo.Person;
import l.demo.Person.Student;
import org.junit.Test;

/**
 * ClassUtil
 * https://hutool.cn/docs/#/core/%E5%B7%A5%E5%85%B7%E7%B1%BB/%E7%B1%BB%E5%B7%A5%E5%85%B7-ClassUtil
 * https://apidoc.gitee.com/loolly/hutool/cn/hutool/core/util/ClassUtil.html
 *
 * @author ljh
 * created on 2020/11/2 14:43
 */
public class ClassUtilDemo extends Demo {

    @Test
    public void testClassUtil() {
        // static <T> Class<T>  getClass(T obj)                         获取对象的类型，null-safe
        p(ClassUtil.getClass(new Person()));
        // static Class<?>[]    getClasses(Object... objects)           获取对象数组的类型数组
        p(ClassUtil.getClasses(new Person(), new Student()));

        // static String        getPackage(Class<?> clazz)              获取类所在包的名称
        p(ClassUtil.getPackage(Person.class));                          // l.demo
        // static Set<Class<?>>	scanPackage(package, Filter<Class<?>>)  扫描指定包下的所有 class 文件
        p(ClassUtil.scanPackage("l.demo"));                             // [class l.demo.Animal$Cat, class l.demo.Company, class l.demo.Animal$Chicken, class l.demo.Demo$MyCallable, class l.demo.Demo$MyTask, class l.demo.Demo$3, class l.demo.Demo, class l.demo.Animal, class l.demo.Demo$2, class l.demo.Person$Student, interface l.demo.Animal$Value, class l.demo.Person$Home, class l.demo.Person, class l.demo.Demo$1]

        // static String        getClassName(Object, isSimple)          获取类名
        p(ClassUtil.getClassName(new Person(), true));                  // Person
        // static String        getClassName(Class<?>, isSimple)        获取类名
        p(ClassUtil.getClassName(Person.class, true));                  // Person
        // static String        getShortClassName(String className)     获取短格式类名
        p(ClassUtil.getShortClassName("class l.demo.Person"));          // c.d.Person              

        // static Class<?>      getEnclosingClass(Class<?> clazz)       获取外围类
        p(ClassUtil.getEnclosingClass(Student.class));                  // class l.demo.Person

        // static String        getClassPath(boolean isEncoded)         获取 ClassPath
        p(ClassUtil.getClassPath(true));                                // D:/L/git/epitome/target/test-classes/
        // static String[]      getJavaClassPaths()
        p(ClassUtil.getJavaClassPaths());

        // static boolean       isTopLevelClass(Class<?> clazz)         是否为顶层类
        p(ClassUtil.isTopLevelClass(Student.class));                    // false
        // static boolean       equals(Class<?>, className, ignoreCase) 类与类名是否相等
        p(ClassUtil.equals(Person.class, "Person", true));              // true
    }

}
