package knowledge.api.lang.class_;

import l.demo.Demo;

/**
 * 类的初始化初始化顺序：
 * 1.父类静态成员
 * 2.父类静态代码块
 * 3.子类静态成员
 * 4.子类静态代码块
 * 5.父类非静态成员
 * 6.父类非静态代码块
 * 7.父类构造器
 * 8.子类非静态成员
 * 9.子类非静态代码块
 * 10.子类构造器
 * https://blog.csdn.net/mmd0308/article/details/75453720
 *
 * @author ljh
 * created on 2019/8/8 19:39
 */
public class ClassInitialization extends Demo {

    static {
        p("classloader 静态块");
    }

    ClassInitialization(String str) {
        p(str);
    }

    public static void main(String[] args) {
        new Son();
    }

    private static class Father {
        static ClassInitialization sPerson = new ClassInitialization("father 静态成员");
        ClassInitialization person = new ClassInitialization("father 非静态成员");

        static {
            p("father 静态代码块");
        }

        {
            p("father 非静态代码块");
        }

        Father() {
            p("father 构造器");
        }
    }

    private static class Son extends Father {
        static ClassInitialization sSon = new ClassInitialization("son 静态成员");
        ClassInitialization son = new ClassInitialization("son 非静态成员");

        static {
            p("son 静态代码块");
        }

        {
            p("son 非静态代码块");
        }

        Son() {
            p("son 构造器");
        }
    }
}