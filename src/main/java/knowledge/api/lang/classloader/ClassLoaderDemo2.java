package knowledge.api.lang.classloader;

/**
 * 初始化静态块，变量，构造器的初始化顺序为：
 * 1.静态块初始化
 * 2.父类变量初始化
 * 3.父类构造器初始化
 * 4.子类变量初始化
 * 5.子类构造器初始化
 */
public class ClassLoaderDemo2 {
    static {
        System.out.println("classloader 静态块");
    }

    ClassLoaderDemo2(String str) {
        System.out.println(str);
    }

    public static void main(String[] args) {
        new Son();
    }
}

class Father {
    ClassLoaderDemo2 person = new ClassLoaderDemo2("father 对象");

    static {
        System.out.println("father 静态块");
    }

    Father() {
        System.out.println("father 构造器");
    }
}

class Son extends Father {
    ClassLoaderDemo2 person = new ClassLoaderDemo2("son 对象");

    static {
        System.out.println("son 静态块");
    }

    Son() {
        System.out.println("son 构造器");
    }
}