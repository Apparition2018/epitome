package spring.api.util;

import l.demo.Demo;
import l.demo.Person;
import org.junit.jupiter.api.Test;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Constructor;

/**
 * ClassUtils
 *
 * <p>
 * static boolean       isPresent(String className, ClassLoader classLoader)            判断是否存在并可以加载
 * static boolean       isVisible(Class<?> clazz, ClassLoader classLoader)              判断是否在指定加载器可见
 * static boolean       isCacheSafe(Class<?> clazz, ClassLoader classLoader)            判断是否在指定加载缓存安全，即是否由指定加载器或其父类加载
 * static boolean       isPrimitiveWrapper(Class<?> clazz)                              判断是否为原始类型的包装类型
 * static boolean       isPrimitiveOrWrapper(Class<?> clazz)                            判断是否为原始类型或包装类型
 * static boolean       isPrimitiveArray(Class<?> clazz)                                判断是否为原始类型数组
 * static boolean       isPrimitiveWrapperArray(Class<?> clazz)                         判断是否为原始类型的包装类型数组
 * static boolean       isJavaLanguageInterface(Class<?> ifc)                           判断是否为 通过 Java 接口
 * static boolean       isInnerClass(Class<?> clazz)                                    判断是否为 内部类
 * static boolean       matchesTypeName(Class<?> clazz, String typeName)                判断是否匹配
 * static boolean       hasConstructor(Class<?> clazz, Class<?>... paramTypes)          判断是否有构造器
 * static boolean       hasMethod(Class<?> clazz, Method method[, Class<?>... paramTypes) 判断是否有方法
 * static boolean       hasAtLeastOneMethodWithName(Class<?> clazz, String methodName)  判断是否包含方法
 * static boolean       isUserLevelMethod(Method method)                                判断是否可用方法
 * <p>
 * static Class<?>[]    toClassArray(Collection<Class<?>> collection)                   Coll<Class> → Class[]
 * static Class<?>[]    getAllInterfaces(Object instance)                               获取对象的所有接口
 * static Set<Class<?>> getAllInterfacesAsSet(Object instance)                          获取对象的所有接口 Set
 * static Class<?>[]    getAllInterfacesForClass(Class<?> clazz[,ClassLoader cl])       获取类的所有接口
 * static Set<Class<?>> getAllInterfacesForClass(Class<?> clazz[,ClassLoader cl])       获取类的所有接口 Set
 * static Class<?>      determineCommonAncestor(Class<?> clazz1, Class<?> clazz2)       返回共同祖先类
 * static Class<?>      getUserClass(Object instance / Class<?> clazz)                  如果是 cglib 生成的类，返回其原来的类
 * <p>
 * static <T> Constructor<T> getConstructorIfAvailable(Class<T> clazz, Class<?>... paramTypes)  获取构造器
 * static Method        getMethod(Class<?> clazz, String methodName, Class<?>... paramTypes)    获取方法
 * static Method        getMethodIfAvailable(Class<?> clazz, String methodName, Class<?>... paramTypes) 获取方法
 * static int           getMethodCountForName(Class<?> clazz, String methodName)                获取指定方法名的方法个数
 * static Method        getMostSpecificMethod(Method method, Class<?> targetClass)              获取最匹配方法
 * static Method        getInterfaceMethodIfPossible(Method method)                             获取接口方法
 * static Method        getStaticMethod(Class<?> clazz, String methodName, Class<?>... args)    获取静态方法
 *
 * static ClassLoader   overrideThreadContextClassLoader(ClassLoader classLoaderToUse)  替换当前线程 ClassLoader
 *
 * @author ljh
 * created on 2021/9/9 9:22
 */
public class ClassUtilsDemo extends Demo {

    @Test
    public void testClassUtils() throws ClassNotFoundException {
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
