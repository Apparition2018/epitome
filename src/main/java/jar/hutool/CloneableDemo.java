package jar.hutool;

import cn.hutool.core.clone.CloneSupport;
import cn.hutool.core.clone.Cloneable;
import org.junit.jupiter.api.Test;

/**
 * Cloneable
 * 支持泛型的克隆接口和克隆类
 * https://hutool.cn/docs/#/core/%E5%85%8B%E9%9A%86/%E6%94%AF%E6%8C%81%E6%B3%9B%E5%9E%8B%E7%9A%84%E5%85%8B%E9%9A%86%E6%8E%A5%E5%8F%A3%E5%92%8C%E5%85%8B%E9%9A%86%E7%B1%BB
 * https://apidoc.gitee.com/dromara/hutool/cn/hutool/core/clone/Cloneable.html
 * https://apidoc.gitee.com/dromara/hutool/cn/hutool/core/clone/CloneSupport.html
 *
 * @author ljh
 * @since 2020/11/18 16:24
 */
public class CloneableDemo {

    @Test
    public void testCloneable() {
        X x = new X();
        X xClone = x.clone();

        Y y = new Y();
        Y yClone = y.clone();
    }

    /**
     * 支持泛型的克隆接口
     */
    static class X implements Cloneable<X> {

        @Override
        public X clone() {
            try {
                return (X) super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    /**
     * 支持泛型的克隆类
     */
    static class Y extends CloneSupport<Y> {

    }
}
