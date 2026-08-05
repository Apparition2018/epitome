package jar.hutool;

import cn.hutool.core.clone.CloneSupport;
import cn.hutool.core.clone.Cloneable;

/**
 * <a href="https://doc.hutool.cn/pages/Cloneable/">Cloneable</a>   支持泛型的克隆接口和克隆类
 * <pre>
 * <a href="https://plus.hutool.cn/apidocs/cn/hutool/core/clone/Cloneable.html">Cloneable api</a>
 * <a href="https://plus.hutool.cn/apidocs/cn/hutool/core/clone/CloneSupport.html">CloneSupport api</a>
 * </pre>
 *
 * @author ljh
 * @since 2020/11/18 16:24
 */
public class CloneableDemo {

    public static void main(String[] args) {
        X x = new X();
        X xClone = x.clone();

        Y y = new Y();
        Y yClone = y.clone();
    }

    /**
     * 支持泛型的克隆接口
     */
    private static class X implements Cloneable<X> {

        @Override
        public X clone() {
            try {
                return (X) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 支持泛型的克隆类
     */
    private static class Y extends CloneSupport<Y> {
    }
}
