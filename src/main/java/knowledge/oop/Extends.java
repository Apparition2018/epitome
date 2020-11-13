package knowledge.oop;

import java.io.Serializable;

/**
 * 继承
 * <p>
 * 继承提供高代码的复用性
 * <p>
 * 子类拥有父类非 private 属性，方法
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
     * extends
     * Java 不支持多继承，但支持多重继承
     * <p>
     * implements
     * Java 支持多实现
     */
    private static class Son extends Parent implements Serializable, Comparable<Son> {

        private int sonId;

        /**
         * 子类构造器
         * <p>
         * 如果父类的构造器带有参数，则必须在子类的构造器中显式地通过 super 关键字调用父类的构造器并配以适当的参数列表
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
