package knowledge.建议;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 建议91：枚举和注解实现 ACL (Access Control List) 访问控制列表
 *
 * @author ljh
 * created on 2020/10/10 19:23
 */
enum CommonIdentifier implements Identifier {
    // 权限级别
    READER, AUTHOR, ADMIN;

    @Override
    public boolean identify() {
        return false;
    }
}

interface Identifier {
    // 无权访问时的礼貌语
    String REFUSE_WORD = "您无权访问";

    // 鉴权
    public boolean identify();
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface Access {
    // 什么访问级别可以访问，默认是管理员
    CommonIdentifier level() default CommonIdentifier.ADMIN;
}

// 定义资源类 Foo，Foo 只能是作者级别的人访问
@Access(level = CommonIdentifier.AUTHOR)
class Foo {

}