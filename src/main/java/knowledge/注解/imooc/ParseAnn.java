package knowledge.注解.imooc;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

// 解析注解：通过反射获取类、函数或成员成的运行时注解信息，从而实现动态控制程序运行的逻辑
public class ParseAnn {

    public static void main(String[] args) {

        try {
            // 1.使用类加载器加载类
            Class clazz = Class.forName("knowledge.注解.imooc.Child");
            // 2.找到类上面的注解
            // boolean	isAnnotationPresent(Class<? extends Annotation> annotationClass)
            // 如果指定类型的注释存在于此元素上，则返回 true，否则返回 false
            boolean isExist = clazz.isAnnotationPresent(Description.class);
            if (isExist) {
                // 3.拿到注解实例
                //<A extends Annotation> A	getAnnotation(Class<A> annotationClass)
                // 如果存在该元素的指定类型的注释，则返回这些注释，否则返回 null
                Description description = (Description) clazz.getAnnotation(Description.class);
                System.out.println(description.desc());
            }

            // 4.找到方法上的注解
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                boolean isExistM = method.isAnnotationPresent(Description.class);
                if(isExistM) {
                    Description description = method.getAnnotation(Description.class);
                    System.out.println(description.desc());
                }
            }

            // 另外一种解析方法
            for (Method method: methods) {
                // Annotation[]	getAnnotations()
                // 返回此元素上存在的所有注释
                Annotation[] annotations = method.getAnnotations();
                for (Annotation annotation : annotations) {
                    if (annotation instanceof Description) {
                        System.out.println(((Description) annotation).desc());
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
