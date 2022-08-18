package knowledge.oop.class_;

import static l.demo.Demo.p;

/**
 * 内部类
 * 把类定义在另一个类的内部，该类就被称为内部类
 * 内部类仍然是独立的类，在编译之后会被编译成独立的.class文件，命名：主类名+$+内部类名/1/2/3…
 * 分类：
 * 1.成员内部类
 * 2.局部内部类
 * 3.静态内部类
 * 4.匿名内部类
 * 访问规则：
 * 1.内部类可以直接访问外部类的成员
 * 2.外部类访问内部类成员，需要创建对象
 * 应用场景：
 * 1.封装代码
 * 2.解决继承的类和实现的接口出现相同方法名的问题
 * 3.实现多继承：建议41
 * <p>
 * Java 内部类有什么作用？：https://www.zhihu.com/question/26954130
 *
 * @author Arsenal
 * created on 2020/11/17 23:49
 */
public class InnerClass {

    private interface S {
    }

    static class A {

        private int x = 9;

        /**
         * 1成员内部类：定义在外部类成员位置的类
         * 特点：可以使用外部类中所有的成员变量和成员方法
         */
        private class AInner implements S {
            private int x = 8;

            public void test() {
                int x = 7;
                p(x);       // 7
                p(this.x);  // 8
                p(A.this.x);// 9
            }
        }

        /**
         * 内部类应用场景一：封装代码
         * 如果我们的内部类不想轻易被任何人访问，可以选择使用 private 修饰内部类，这样就无法通过创建对象的方法来访问，
         * 想要访问只需要在外部类中定义一个 public 修饰的方法，间接调用。
         * 这样做的好处是，可以在这个对外课件的方法中增加一些判断语句，起到数据安全的作用。
         * 其次，此方法返回的类型是内部类实现的接口类型，从而隐藏了内部类的名称。
         */
        public S getInner() {
            return new AInner();
        }

        public static void main(String[] args) {
            AInner aInner = new A().new AInner();
            aInner.test();
        }
    }

    static class B {
        private int x = 20;

        public void bMethod() {
            int y = 30;
            /*
             * 2.局部内部类：定义在一个方法或者一个作用域里面的类
             * 特点：只能在自身所在方法中被使用；就像一个局部变量，所以不能有访问权限修饰符和 static 修饰符
             */
            class BInner {
                public void test() {
                    p(x);
                    /*
                     * 在一个方法的局部内部类中想引用这个方法的其它局部变，需要将变量声明为 final
                     * JDK1.8 重构了 JVM 内存分配，解决了这个问题，不再需要声明为 final
                     */
                    p(y);
                }
            }
            BInner bInner = new BInner();
            bInner.test();
        }
    }

    static class C {
        /**
         * 3.静态内部类：使用 static 修饰的内部类，也称作嵌套内部类
         * 特点：不能使用外部类的非 static 成员变量和成员方法
         * 注意：非静态内部类中不可以声明静态成员变量和方法
         */
        static class CInner {
            public void test() {

            }
        }

        public static void main(String[] args) {
            CInner cInner = new C.CInner();
            cInner.test();
        }
    }

    static class D {

        protected void DMethod() {
            p("DMethod");
        }

        public static void main(String[] args) {
            /*
             * 4.匿名内部类：没有名字的类，是内部类的简化写法
             * 特点：唯一一种没有构造器的类
             * 本质：继承该类或者实现接口的子类匿名对象
             */
            new Runnable() {
                @Override
                public void run() {

                }
            };

            /*
             * 使用匿名内部类调用 protected 方法
             */
            new D() {
                void callDMethod() {
                    super.DMethod();
                }
            }.callDMethod();
        }
    }

    private interface X {
        void method();
    }

    static class Y {
        public void method() {
            p("extends class Y");
        }
    }

    /**
     * 内部类应用场景二：
     * 解决继承的类和实现的接口出现相同方法名的问题
     */
    static class XY extends Y {
        private class XYInner implements X {

            @Override
            public void method() {
                p("implement interface X");
            }
        }

        public static void main(String[] args) {
            XY xy = new XY();

            X x = xy.new XYInner();
            x.method();     // implement interface X

            xy.method();    // extends class Y
        }
    }

}
