package jar.hutool.extra;

import cn.hutool.extra.cglib.CglibUtil;
import l.demo.Demo;
import l.demo.User;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Objects;

/**
 * CglibUtil
 * CGLib：一个强大的，高性能，高质量的 Code 生成类库，通过此库可以完成动态代理、Bean 拷贝等操作。
 * https://hutool.cn/docs/#/extra/cglib/Cglib%E5%B7%A5%E5%85%B7-CglibUtil
 * https://apidoc.gitee.com/dromara/hutool/cn/hutool/extra/cglib/CglibUtil.html
 *
 * @author Arsenal
 * created on 2020/11/21 17:04
 */
public class CglibUtilDemo extends Demo {

    @Test
    public void testCglibUtil() {
        User user = new User(new Date());

        User copy1 = new User();
        // static void	        copy(Object source, Object target)
        CglibUtil.copy(user, copy1);

        // static <T> T	        copy(Object source, Class<T> targetClass)
        User copy2 = CglibUtil.copy(user, User.class);

        System.out.println(Objects.equals(copy1, copy2));
    }
}
