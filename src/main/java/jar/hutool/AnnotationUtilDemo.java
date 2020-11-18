package jar.hutool;

import cn.hutool.core.annotation.AnnotationUtil;
import knowledge.注解.CustomAnnotation.Apple;
import knowledge.注解.CustomAnnotation.Description;
import l.demo.Demo;
import org.junit.Test;

/**
 * AnnotationUtil   注解工具
 * https://hutool.cn/docs/#/core/%E6%B3%A8%E8%A7%A3/%E6%B3%A8%E8%A7%A3%E5%B7%A5%E5%85%B7-AnnotationUtil
 * https://apidoc.gitee.com/loolly/hutool/cn/hutool/core/annotation/AnnotationUtil.html
 *
 * @author ljh
 * created on 2020/11/9 16:25
 */
public class AnnotationUtilDemo extends Demo {

    @Test
    public void testAnnotationUtil() throws NoSuchFieldException {
        // static Annotation[]              getAnnotations(AnnotatedElement, isToCombination)
        // 获取注解数组
        p(AnnotationUtil.getAnnotations(Apple.class, true));
        // [@knowledge.注解.CustomAnnotation$Description(desc=Apple)]
        p(AnnotationUtil.getAnnotations(Apple.class.getDeclaredField("appleProvider"), true));
        // [@knowledge.注解.CustomAnnotation$FruitProvider(name=陕西红富士集团, address=陕西省西安市延安路89号红富士大厦, id=1)]


        Description annotation;
        // static boolean	                hasAnnotation(AnnotatedElement, Class<? extends Annotation>)
        // 是否有指定注解
        if (AnnotationUtil.hasAnnotation(Apple.class, Description.class)) {
            // static <T> T	                getAnnotationValue(AnnotatedElement, Class<? extends Annotation>[, propertyName])
            // 获取指定注解属性的值
            p(AnnotationUtil.getAnnotationValue(Apple.class, Description.class, "desc"));   // Apple
            // static Map<String,Object>	getAnnotationValueMap(AnnotatedElement, Class<? extends Annotation>)
            // 获取指定注解中属性键值 Map
            p(AnnotationUtil.getAnnotationValueMap(Apple.class, Description.class));        // {desc=Apple}

            // static <A e Annotation> A    getAnnotation(AnnotatedElement, Class<A>)
            // 获取指定注解
            annotation = AnnotationUtil.getAnnotation(Apple.class, Description.class);

            // 是否有元注解 @Documented
            p(AnnotationUtil.isDocumented(annotation.getClass()));          // false
            // 是否有元注解 @Inherited
            p(AnnotationUtil.isInherited(annotation.getClass()));           // false
            // 获取注解的 @Retention 的值
            p(AnnotationUtil.getRetentionPolicy(annotation.getClass()));    // CLASS
            // 获取注解的 @Target 的值
            p(AnnotationUtil.getTargetType(annotation.getClass()));         // [TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE, ANNOTATION_TYPE, PACKAGE]
        }


    }
}
