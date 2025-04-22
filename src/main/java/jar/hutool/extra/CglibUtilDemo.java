package jar.hutool.extra;

import cn.hutool.extra.cglib.CglibUtil;
import l.demo.User;

import java.util.Date;
import java.util.Objects;

/**
 * <a href="https://doc.hutool.cn/pages/CglibUtil/">CglibUtil</a>
 * <p>CGLib：一个强大的，高性能，高质量的 Code 生成类库，通过此库可以完成动态代理、Bean 拷贝等操作。
 * <p>需要引入 cglib
 * <p><a href="https://plus.hutool.cn/apidocs/cn/hutool/extra/cglib/CglibUtil.html">CglibUtil api</a>
 *
 * @author ljh
 * @since 2020/11/21 17:04
 */
public class CglibUtilDemo {

    public static void main(String[] args) {
        User user = new User(new Date());

        User copy1 = new User();
        // static void	        copy(Object source, Object target)
        CglibUtil.copy(user, copy1);

        // static <T> T	        copy(Object source, Class<T> targetClass)
        User copy2 = CglibUtil.copy(user, User.class);

        System.out.println(Objects.equals(copy1, copy2));
    }
}
