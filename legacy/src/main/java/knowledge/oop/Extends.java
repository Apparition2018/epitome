package knowledge.oop;

import java.io.Serial;
import java.io.Serializable;

/**
 * 继承
 * <pre>
 * 继承提供高代码的复用性
 * 子类拥有父类非 private 属性和方法
 * </pre>
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class Extends {

    public static void main(String[] args) {
        Parent p = new Son(1, "apple");
        System.out.println(p.name);
    }

    private static class Parent {
        private int id;
        public String name;

        Parent(int i, String s) {
            // this: 指向自己的引用
            this.id = i;
            this.name = s;
        }

        public int getId() {
            return this.id;
        }
    }

    /**
     * <pre>
     * extends      不支持多继承，支持多重继承
     * implements   支持多实现
     * </pre>
     */
    private static class Son extends Parent implements Serializable, Comparable<Son> {
        @Serial
        private static final long serialVersionUID = -7624170257458741051L;
        private int sonId;

        /**
         * 子类构造器
         * <p>如果父类的构造器带有参数，则必须在子类的构造器中显式地通过 super 关键字调用父类的构造器
         */
        Son(int i, String s) {
            // super: 指向当前对象的父类
            super(i, s);
        }

        @Override
        public int compareTo(Son o) {
            return 0;
        }
    }
}
