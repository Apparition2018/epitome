package spring.api.util;

import l.demo.Person;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

import static l.demo.Demo.p;

/**
 * ClassUtils
 *
 * @author ljh
 * @since 2021/9/9 9:22
 */
public class ClassUtilsDemo {

    public static void main(String[] args) throws ClassNotFoundException {
        p(ClassUtils.ARRAY_SUFFIX);                 // []
        p(ClassUtils.CGLIB_CLASS_SEPARATOR);        // $$，CGLIB 代理类
        p(ClassUtils.CLASS_FILE_SUFFIX);            // .class

        // 1. Thread.currentThread().getContextClassLoader()
        // 2. ClassUtils.class.getClassLoader()
        // 3. ClassLoader.getSystemClassLoader()
        ClassLoader defaultClassLoader = ClassUtils.getDefaultClassLoader();

        // 加强版 Class.forName()，可加载 primitives, array, nested class
        p(ClassUtils.forName("java.lang.Integer[]", defaultClassLoader));   // class [Ljava.lang.Integer;
        // 同上，区别在加载失败时抛出的异常
        p(ClassUtils.resolveClassName("int", defaultClassLoader));          // int
        // 专门加载原始类型
        p(ClassUtils.resolvePrimitiveClassName("int"));                     // int
        // 如果是原始类型，则返回其包装类型
        p(ClassUtils.resolvePrimitiveIfNecessary(int.class));               // class java.lang.Integer

        // 判断左边是否与右边相等，是其超类或超接口
        p(Object.class.isAssignableFrom(int.class));            // false
        // 同上，区别是原始类型通过其包装类型来判断
        p(ClassUtils.isAssignable(Object.class, int.class));    // true
        p(ClassUtils.isAssignableValue(Object.class, 0));       // true

        // "/" → "."
        p(ClassUtils.convertResourcePathToClassName("a/b/c"));  // a.b.c
        // "." → "/"
        p(ClassUtils.convertClassNameToResourcePath("a.b.c"));  // a/b/c

        // 返回指定类或接口的 String
        p(ClassUtils.classNamesToString(ClassUtils.class));                             // [org.springframework.util.ClassUtils]
        // 返回指定类所属包的资源路径
        p(ClassUtils.classPackageAsResourcePath(ClassUtils.class));                     // org/springframework/util
        // 返回指定类所属包下指定文件的资源路径
        p(ClassUtils.addResourcePathToPackagePath(ClassUtils.class, "ObjectUtils"));    // org/springframework/util/ObjectUtils

        // 返回对象类型的描述性名称
        p(ClassUtils.getDescriptiveType(ClassUtils.class)); // java.lang.Class
        p(ClassUtils.getDescriptiveType(new int[]{0}));     // int[]

        // 获取 short name
        p(ClassUtils.getShortName("class l.demo.Person$Student"));  // Person.Student
        p(ClassUtils.getShortName(Person.Student.class));           // Person.Student
        p(ClassUtils.getShortNameAsProperty(Person.Student.class)); // student
        // 获取类文件名称
        p(ClassUtils.getClassFileName(Person.Student.class));       // Person$Student.class
        // 获取类的包的名称
        p(ClassUtils.getPackageName(Person.Student.class));         // l.demo
        p(ClassUtils.getPackageName("l.demo.Person$Student"));      // l.demo
        // 获取 qualified name
        p(ClassUtils.getQualifiedName(Person.Student.class));       // l.demo.Person$Student
        // 获取方法的 qualified name
        p(ClassUtils.getQualifiedMethodName(
                ReflectionUtils.findMethod(Person.Student.class, "setName", String.class))); // l.demo.Person.setName
    }
}
