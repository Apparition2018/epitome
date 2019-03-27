package other.参数传递;

/**
 * Java内存分配全面浅析:
 * http://www.cnblogs.com/lixiaolun/p/4311775.html
 */
public class ParameterPassing1 {

    private int d;

    public int get() {
        return d;
    }

    public void set(int d) {
        this.d = d;
    }

    private static void change1(int a) {
        a = 50;
    }

    private static void change2(Integer b) {
        b = 50;
    }

    private static void change3(String c) {
        c = "d";
    }

    private static void change4(ParameterPassing1 zcd, int d) {
        zcd.set(d);
    }

    public static void main(String[] args) {
        int a = 10;
        change1(a); // 传递的是值的一份拷贝(a=10)，这份拷贝与原来的值没什么关系
        System.out.println(a); // 10

        Integer b = 10;
        change2(b);
        System.out.println(b); // 10

        String c = "c";
        change3(c);
        System.out.println(c); // c

        ParameterPassing1 zcd = new ParameterPassing1();
        zcd.set(4);
        change4(zcd, 8);
        System.out.println(zcd.get()); // 8
    }
}
