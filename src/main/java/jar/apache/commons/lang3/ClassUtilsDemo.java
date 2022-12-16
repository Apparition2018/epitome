package jar.apache.commons.lang3;

import l.demo.Demo;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ClassUtils;

import java.util.Collections;

/**
 * ClassUtils
 * http://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/ClassUtils.html
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class ClassUtilsDemo extends Demo {

    public static void main(String[] args) throws ClassNotFoundException {
        // static List<String>	        convertClassesToClassNames(List<Class<?>> classes)
        // 类 → 类名
        p(ClassUtils.convertClassesToClassNames(Collections.singletonList(Integer.class)));        // [java.lang.Integer]
        // static List<Class<?>>	    convertClassNamesToClasses(List<String> classNames)
        // 类名 → 类
        p(ClassUtils.convertClassNamesToClasses(Collections.singletonList("java.lang.Integer")));  // [class java.lang.Integer]

        // static Class<?>	            getClass(String className[, boolean initialize])
        // 加强版 Class.forName() 可以指定值是否马上要初始化
        p(ClassUtils.getClass("java.lang.Integer")); // class java.lang.Integer

        // static List<Class<?>>	    getAllInterfaces(Class<?> cls)
        // 获取所有父接口
        p(ClassUtils.getAllInterfaces(Integer.class));  // [interface java.lang.Comparable, interface java.io.Serializable]
        // 获取所有父类
        // static List<Class<?>>	    getAllSuperclasses(Class<?> cls)
        p(ClassUtils.getAllSuperclasses(Integer.class));//

        // static String	            getShortClassName(Class<?> cls)
        // static String	            getShortClassName(Object object, String valueIfNull)
        // static String	            getShortClassName(String className)
        // 获取简单类名
        p(ClassUtils.getShortClassName(Integer.class));         // Integer
        p(ClassUtils.getShortClassName("java.lang.Integer"));   // Integer

        // static String	            getPackageCanonicalName/getPackageName(Class<?> cls)
        // static String	            getPackageCanonicalName/getPackageName(Object object, String valueIfNull)
        // static String	            getPackageCanonicalName/getPackageName(String canonicalName)
        // 获取包名
        p(ClassUtils.getPackageCanonicalName(Integer.class));       // java.lang
        p(ClassUtils.getPackageCanonicalName("java.lang.Integer")); // java.lang
        p(ClassUtils.getPackageName(Integer.class));                // java.lang
        p(ClassUtils.getPackageName("java.lang.Integer"));// java.lang

        // static Iterable<Class<?>>    hierarchy(Class<?> type[, ClassUtils.Interfaces interfacesBehavior])
        // 获取该类的继承结构
        // Iterable it = ClassUtils.hierarchy(Integer.class);   // since 3.2

        // static boolean	            isAssignable(Class<?>[] classArray, Class<?>[] toClassArray[, boolean autoboxing])
        // static boolean	            isAssignable(Class<?> cls, Class<?> toClass[, boolean autoboxing])
        // 判断是否可以转型
        p(ClassUtils.isAssignable(Integer.class, Integer.class));   // true
        p(ClassUtils.isAssignable(Integer.class, Number.class));    // true

        // static boolean	            isInnerClass(Class<?> cls)
        // 判断是否为内部类
        p(ClassUtils.isInnerClass(Integer.class));  // false

        // static Class<?>	            primitiveToWrapper(Class<?> cls)
        // static Class<?>[]	        primitivesToWrappers(Class<?>... classes)
        // static Class<?>	            wrapperToPrimitive(Class<?> cls)
        // static Class<?>[]	        wrappersToPrimitives(Class<?>... classes)
        // 基本类型类 和 包装类型类 转换
        p(ClassUtils.wrapperToPrimitive(Integer.class));  // int

        // static Class<?>[]	        toClass(Object... array)
        // 对象 → Class 对象
        p(ClassUtils.toClass(ArrayUtils.toArray(1, "2", new ClassUtilsDemo()))); // [class java.lang.Integer, class java.lang.String, class jar.apache.commons.lang.ClassUtilsDemo]
    }
}
