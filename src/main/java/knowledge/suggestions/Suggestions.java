package knowledge.suggestions;

import knowledge.suggestions.Family.Daughter;
import knowledge.suggestions.Family.Son;
import l.demo.Demo;
import l.demo.Person;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.jupiter.api.Test;

import java.io.Serial;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * <a href="https://www.cnblogs.com/selene/category/876189.html">编写高质量代码 改善java程序的151个建议</a>
 * <p>第一章：Java 开发中通用的方法和准则
 * <pre>
 * 建议12：避免序列化类在构造函数中为不变量赋值
 * 建议13：避免序列化类为final变量复杂赋值
 * 建议14：使用序列化类的私有方法巧妙解决部分属性持久化问题
 * 建议17：慎用动态编译
 * 建议19：断言
 * </pre>
 * 第二章：基本类型
 * <pre>
 * 建议22：用 BigDecimal 或整数类型处理货币
 * 建议25：银行的四舍六入五考虑
 * 建议29：优先选择基本类型
 * 建议30：不要设置随机种子
 * </pre>
 * 第三章：类、对象及方法
 * <pre>
 * 建议36：使用构造代码块精简程序
 * 建议38：使用静态内部类提高封装性
 * 建议41：内部类实现多继承
 * 建议50：package-info
 * </pre>
 * 第五章：数组和集合
 * <pre>
 * 建议65：泛型不支持基本类型
 * 建议67：不同的列表选择不同的遍历算法
 * 建议71：使用 subList() 处理局部列表
 * 建议72：subList() 生成字列表后，不要操作原列表
 * 建议73：Comparable 可用做类的默认排序算法，Comparator 可用做扩展排序工具
 * 建议80：多线程使用 Vector 或 Hashtable
 * 第六章：枚举和注解
 * <pre>
 * 建议88：枚举实现工厂模式
 * 建议89：枚举项的数量限制在64个以内
 * 建议91：枚举和注解实现 ACL (Access Control List) 访问控制列表
 * </pre>
 * 第七章：泛型和反射
 * <pre>
 * 建议94：不能初始化泛型参数和数组
 * 建议96：泛型通配符
 * 建议97：泛型不支持协变和逆变
 * 建议98：建议使用顺序 List<T> > List<?> > List<Object>
 * 建议99：严格限定泛型类型采用多重界限
 * 建议101：Class 类的特殊性
 * 建议103：反射访问属性和方法时设置 xxx.setAccessible(true)
 * 建议105：动态加载不适合数组
 * 建议106：动态代理
 * 建议107：动态代理结合装饰器模式
 * 建议108：反射结合模板方法模式
 * </pre>
 * 第八章：异常
 * <pre>
 * 建议110：一次捕获多个异常
 * 建议112：检测性异常尽可能转化为非检测性异常
 * 建议115：使用 Throwable 获得栈信息
 * </pre>
 * 第九章：多线程和并发
 * <pre>
 * 建议120：不要使用 stop() 终止线程
 * 建议122：使用线程异常处理器提升系统可靠性
 * 建议127：Lock 和 synchronized
 * </pre>
 * 第十章：性能和效率
 * <pre>
 * 建议132：提升 Java 性能的基本方法
 * 建议137：调优 JVM 参数
 * </pre>
 * 其它
 * <pre>
 * 建议139：大胆采用开源工具
 * 建议145：不要完全依靠单元测试来发现问题
 * 建议146：让注释正确、清晰、简洁
 * 建议148：增强类的可替换性
 * </pre>
 *
 * @author ljh
 * @since 2020/10/10 19:23
 */
@SuppressWarnings("unchecked")
public class Suggestions extends Demo {

    /* 第一章：Java 开发中通用的方法和准则 */

    /* 建议12：避免序列化类在构造函数中为不变量赋值
     * 反序列化时构造函数不会执行
     */

    /* 建议13：避免序列化类为final变量复杂赋值
     * <p>
     * 反序列化时final变量在以下情况下不会被重新赋值:
     * 1.通过构造函数为final变量赋值
     * 2.通过方法返回值为final变量赋值
     * 3.final修饰的属性不是基本类型
     */

    /* 建议14：使用序列化类的私有方法巧妙解决部分属性持久化问题
     * Java调用ObjectOutputStream类把一个对象转换成数据流时，
     * 会通过反射（Refection）检查被序列化的类是否有writeObject方法，并且检查其是否符合私有，无返回值的特性，
     * 若有，则会委托该方法进行对象序列化，若没有，则由ObjectOutputStream按照默认规则继续序列化。
     * 同样，在从流数据恢复成实例对象时，也会检查是否有一个私有的readObject方法，如果有，则会通过该方法读取属性值。
     */

    /* 建议17：慎用动态编译
     * <p>
     * 使用动态编译时，需要注意以下几点：
     * 1.在框架中谨慎使用：比如要在struts中使用动态编译，动态实现一个类，它若继承自ActionSupport就希望它成为一个Action。能做到，但是debug很困难；再比如在Spring中，写一个动态类，要让它注入到Spring容器中，这是需要花费老大功夫的。
     * 2.不要在要求性能高的项目中使用：如果你在web界面上提供了一个功能，允许上传一个java文件然后运行，那就等于说:"我的机器没有密码，大家都可以看看"，这是非常典型的注入漏洞，只要上传一个恶意Java程序就可以让你所有的安全工作毁于一旦。
     * 3.记录动态编译过程：建议记录源文件，目标文件，编译过程，执行过程等日志，不仅仅是为了诊断，还是为了安全和审计，对Java项目来说，空中编译和运行时很不让人放心的，留下这些依据可以很好地优化程序
     * <p>
     * 动态编译有很好的替代方案：比如Groovy, Jruby等无缝的脚本语言
     */

    /**
     * 建议18：避免 instanceof 非预期结果
     */
    @Test
    public void test018() {
        // 空对象是否是 String 的实例：false，instanceof 规则，弱操作数为 null，直接返回 false
        p(null instanceof String);

        // 转换后的空对象是否是 String 的实例：false，即使强转，也是 null
        p((String) null instanceof String);

        // 在泛型类型中判断String对象是否是Date的实例：false，编译成字节码时，T 是 Object 类型，所以等价于 Object instanceof Date，编译通过
        p(new GenericClass<String>().isDateInstance(""));
    }

    static class GenericClass<T> {
        // 判断是否是Date类型
        boolean isDateInstance(T t) {
            return t instanceof Date;
        }
    }

    /**
     * 建议19：断言
     * <p>assertion 检查通常在开发和测试时开启。为了提高性能，在软件发布后，assertion 检查通常是关闭的。
     * 用法：
     * <pre>
     * 1 assert <boolean expression>
     * 2 assert <boolean expression> : <error message>
     * </pre>
     * 可以是用断言的地方：
     * <pre>
     * 1 程序不应到达的地方，可以放置断言：assert false
     * 2 private 方法的参数检查
     * 3 类的不变状态（如 age >= 0）
     * </pre>
     * 不要使用断言的地方：
     * <pre>
     * 1 public 方法的参数检查
     * 2 断言语句不可以有任何边界效应，不要使用断言语句去修改变量和改变方法的返回值
     * </pre>
     *
     * @see <a href="http://www.blogjava.net/liulu/archive/2006/10/24/77005.html">Java 断言使用</a>
     * @see <a href="http://www.nowamagic.net/internet/internet_ContractProgramming.php">浅谈契约式编程</a>
     */
    @Test
    public void test019() {
        int age = -1;
        if (age >= 0) {
            p("ok");
            return;
        }
        assert false : "到达这里就表示错误";
    }

    /* 第二章：基本类型 */

    /**
     * 建议22：用 BigDecimal 或整数类型处理货币
     */
    @Test
    public void test022() {
        double d = 10 - 9.6D;
        p(d + "元"); // 0.40000000000000036元

        // 方法一：BigDecimal
        BigDecimal bd = new BigDecimal(10).subtract(new BigDecimal(String.valueOf(9.6)));
        p(bd + "元"); // 0.4元

        // 方法二
        double d2 = (10 * 100 - 9.6D * 100) / 100;
        p(d2 + "元"); // 0.4元
    }

    /**
     * 建议25：银行的四舍六入五考虑
     * <p>四舍五入：
     * <pre>
     * 舍弃   0.000, 0.001, 0.002, 0.003, 0.004
     * 进位   0.005, 0.006, 0.007, 0.008, 0.009
     * 计算   0.000 + 0.001 + 0.002 + 0.003 + 0.004 - 0.005 - 0.004 - 0.003 - 0.002 - 0.001 = 0.005
     * 等于银行要亏 0.005 元
     * </pre>
     * 四舍六入五考虑，五后非零就进一，五后为零看奇偶，五前为偶应舍去，五前为奇要进一
     */
    @Test
    public void test025() {
        // 存款
        BigDecimal d = new BigDecimal(12345678);
        // 月利率
        BigDecimal r = new BigDecimal(String.valueOf(0.001875));
        // 计算利息
        BigDecimal i = d.multiply(r).setScale(2,
                // 银行家舍入法：四舍六入五考虑
                RoundingMode.HALF_EVEN);
        p("月利息是：" + i);
    }

    /**
     * 建议29：优先选择基本类型
     */
    @Test
    public void test029() {
        Suggestions sg = new Suggestions();
        int i = 140;
        sg.testMethod(i);
        // 自动装箱：基本类型可以先加宽，再转变成宽类型的包装类型，但不能直接转变成宽类型的包装类型
        // int → Integer → int → long，所以调用了 testMethod(long a)
        sg.testMethod(new Integer(i));
    }

    public void testMethod(long a) {
        p("基本类型的方法被调用");
    }

    public void testMethod(Long a) {
        p("包装类型的方法被调用");
    }

    /**
     * 建议30：不要设置随机种子
     * <p>随机数和种子之间的关系：
     * <pre>
     * 1 种子不同，产生不同的随机数
     * 2 种子相同，即使实例不同也产生相同的随机数
     * </pre>
     */
    @Test
    public void test030() {
        // 种子默认是 System.nanoTime() 的返回值，这里设置为 1000，所以每次随机结果都相等
        Random r = new Random(1000);
        IntStream.rangeClosed(1, 4).forEach(i -> p(r.nextInt()));
        // 每次输出都如下：
        // -1244746321
        // 1060493871
        // -1826063944
        // 1976922248
    }

    /* 第三章：类、对象及方法 */

    /**
     * 建议36：使用构造代码块精简程序
     * <p>Java 中四种类型的代码块：
     * <pre>
     * 1 普通代码块：就是在方法后面使用"{}"括起来的代码片段，它不能单独运行，必须通过方法名调用执行；
     * 2 静态代码块：在类中使用 static 修饰，并用"{}"括起来的代码片段，用于静态变量初始化或对象创建前的环境初始化；
     * 3 同步代码块：使用 synchronized 关键字修饰，并使用"{}"括起来的代码片段，它表示同一时间只能有一个线程进入到该方法块中，是一种多线程保护机制；
     * 4 构造代码块：在类中没有任何前缀和后缀,并使用"{}"括起来的代码片段；
     *   4.1 构造代码块会插入到每个构造函数的最前端
     *   4.2 构造代码块不会插入到添加了 this() 的构造器中
     * </pre>
     *
     * @see <a href="https://www.cnblogs.com/ysocean/p/8194428.html">Java 中静态代码块、构造代码块、构造函数、普通代码块</a>
     */
    @Test
    public void test036() {

        class Demo {

            {
                p("执行构造代码块");
            }

            private Demo() {
                p("执行无参构造");
            }

            private Demo(String name) {
                p("执行有参构造");
            }

        }

        new Demo();
        new Demo("");
    }

    /**
     * 建议38：使用静态内部类提高封装性
     * <p>静态内部类优点：
     * <pre>
     * 1 加强了类的封装
     * 2 提高了代码的可读性
     * 3 形似内部，神似外部，可以通过 new 创建
     * </pre>
     * 静态内部类和普通内部类的区别：
     * <pre>
     * 1 静态内部类只能访问外部类的静态方法和静态属性
     * 2 静态内部类不依赖外部类
     * 3 普通内部类不能声明静态方法和静态变量
     * </pre>
     */
    @Test
    public void test038() {
        Person person = new Person();
        person.setHome(new Person.Home().setAddress("北京").setTel("010"));
    }

    /**
     * 建议39：使用匿名类的构造函数
     */
    @Test
    public void test039() {
        // 匿名类
        List<String> list = new ArrayList<>() {
            @Serial
            private static final long serialVersionUID = 2343608519616911909L;

            // 匿名类的构造函数
            {
                add("A");
            }
        };
        p(list);
    }

    /**
     * 建议40：匿名类的构造函数
     */
    @Test
    public void test040() {
        Calculator c = new Calculator(1, 2) {
            {
                setOperator(Ops.ADD);
            }
        };
        p(c.getResult());

        // 等价于
        class Add extends Calculator {
            {
                setOperator(Ops.ADD);
            }

            // 覆写父类的构造方法
            public Add(int _i, int _j) {
                super(_i, _j);
            }
        }
        Add a = new Add(1, 2);
        p(a.getResult());
    }

    /**
     * 建议41：内部类实现多继承
     */
    @Test
    public void test041() {
        p(new Son().strong());      // 9
        p(new Son().kind());        // 7
        p(new Daughter().strong()); // 6
        p(new Daughter().kind());   // 8
    }

    /* 第四章：字符串 */

    /**
     * 建议56：字符串拼接的各种方法
     */
    @Test
    public void test056() {
        stopWatch.start("+");
        String str = "";
        for (int i = 0; i < THOUSAND; i++) {
            str += "c";
        }
        stopWatch.stop();

        stopWatch.start("concat");
        str = "";
        for (int i = 0; i < THOUSAND; i++) {
            str = str.concat("c");
        }
        stopWatch.stop();

        // StringBuilder
        stopWatch.start("StringBuilder");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < THOUSAND; i++) {
            sb.append("c");
        }
        stopWatch.stop();

        p(stopWatch.prettyPrint());
        // ---------------------------------------------
        // ns         %     Task name
        // ---------------------------------------------
        // 000581200  071%  +
        // 000188900  023%  concat
        // 000051600  006%  StringBuilder
    }

    /* 第五章：数组和集合 */

    /**
     * 建议65：泛型不支持基本类型
     */
    @Test
    public void test065() {
        int[] arr1 = {1, 2, 3, 4, 5};
        // static <T> List<T>	asList(T... a)
        List<int[]> list1 = Collections.singletonList(arr1);
        p(list1); // [[I@75f32542]
        // 在Java中任何一个一维数组的类型都是 "[I"，究其原因就是 Java 并没有定义数组这一个类，它是在编译器编译的时候生成的，是一个特殊的类

        Integer[] arr2 = {1, 2, 3, 4, 5};
        List<Integer> list2 = List.of(arr2);
        p(list2); // [1, 2, 3, 4, 5]
    }

    /**
     * 建议67：不同的列表选择不同的遍历算法
     * <pre>
     * 1 instanceof RandomAccess 为 true 的列表：使用下表遍历 x.get(i)
     * 2 其它：foreach 遍历
     * </pre>
     */
    @Test
    public void test067() {
        // List 集合，记录所有学生的份数
        List<Integer> scores = new ArrayList<>(); // 10ms
        // List<Integer> scores = new LinkedList<>(); // 15ms
        // 学生人数 100 万
        IntStream.rangeClosed(1, TEN_MILLION).forEach(i -> scores.add(new Random().nextInt(150)));
        StopWatch watch = StopWatch.createStarted();
        p("平均分是：" + this.average(scores));
        p("执行时间：" + watch.getTime() + "ms");
    }

    private int average(List<Integer> scores) {
        int sum = 0;
        if (scores instanceof RandomAccess) {
            // 随机存取，使用下标遍历
            for (int i = 0, len = scores.size(); i < len; i++) {
                sum += scores.get(i);
            }
        } else {
            // 有序存取，使用foreach遍历
            for (int i : scores) {
                sum += i;
            }
        }
        return sum / scores.size();
    }

    /**
     * 建议71：使用 subList() 处理局部列表
     */
    @Test
    public void test071() {
        /* 需求：一个列表有100个元素，现在要删除索引位置为20~30的元素 */
        // 初始化一个固定长度，不可变 List
        List<Integer> initData = Collections.nCopies(100, 0);
        // 转换为可变 List
        List<Integer> list = new LinkedList<>(initData);
        // 删除指定范围内的元素
        list.subList(20, 30).clear();
        p(list.size()); // 90
    }

    /**
     * 建议72：subList() 生成字列表后，不要操作原列表
     * <p>可通过 Collections.unmodifiableList() 设置列表为只读状态
     */
    @Test
    public void test072() {
        List<Integer> list = new ArrayList<>(List.of(1, 2, 3));

        List<Integer> subList = list.subList(0, 2);

        // 原列表增加一个元素
        list.add(4);
        p("原列表长度：" + list.size());    // 4
        p("子列表长度：" + subList.size()); // ConcurrentModificationException
    }

    /**
     * 建议73：Comparable 可用做类的默认排序算法，Comparator 可用做扩展排序工具
     */
    @Test
    public void test073() {
        List<Employee> list = new ArrayList<>(5);
        // 一个老板
        list.add(new Employee().setId(1001).setName("张三").setPosition(Position.BOSS));
        // 两个经理
        list.add(new Employee().setId(1006).setName("赵七").setPosition(Position.MANAGER));
        list.add(new Employee().setId(1003).setName("王五").setPosition(Position.MANAGER));
        // 两个职员
        list.add(new Employee().setId(1002).setName("李四").setPosition(Position.STAFF));
        list.add(new Employee().setId(1005).setName("马六").setPosition(Position.STAFF));

        p("----- Comparable -----");
        Collections.sort(list);
        list.forEach(System.out::println);

        p("----- Comparator -----");
        list.sort(new PositionComparator());
        list.forEach(System.out::println);
    }

    /**
     * 建议80：多线程使用 Vector 或 Hashtable
     */
    @Test
    public void test080() throws InterruptedException {
        // 火车票列表
        Vector<String> tickets = new Vector<>(THOUSAND);
        // 初始化票据池
        IntStream.rangeClosed(1, THOUSAND).forEach(i -> tickets.add("火车票 " + i));
        // 10个窗口售票
        setCountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                do {
                    System.out.printf("窗口 %s：卖出 %s%n", Thread.currentThread().getId(), tickets.remove(0));
                } while (tickets.size() != 0);
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
    }

    /* 第六章：枚举和注解 */

    /**
     * 建议88：枚举实现工厂模式
     * <pre>
     * 1 通过非静态方法实现工厂模式，{@link CarEnumFactory1}
     * 2 通过抽象方法实现工厂模式，{@link CarEnumFactory2}
     * </pre>
     * 枚举实现工厂模式的优点：
     * <pre>
     * 1 一般工厂模式可接受3种类型参数：类型参数、String、int
     *   这三种参数是宽泛的数据类型，很容易发生错误，且这类错误编译器不会警报
     * 2 性能好，使用简洁
     * 3 降低耦合，不需接受参数
     * </pre>
     */
    @Test
    public void test088() {
        // 非静态方法实现
        p(CarEnumFactory1.BUICK_CAR.create());
        // 抽象方法实现
        p(CarEnumFactory2.BUICK_CAR.create());
        // 一般工厂模式
        p(CarFactory.createCar(BuickCar.class));
    }

    /* 建议89：枚举项的数量限制在64个以内
     * 1.枚举项少于等于64时，创建一个 RegularEnumSet 实例对象，把每个枚举项的 ordinal 映射到一个 long 的每个位置上
     * 2.枚举项大于64时，创建一个 JumboEnumSet 实例对象，把枚举项按照64个元素一组拆分成多组，每组都映射到一个 long
     */

    /**
     * 建议91：枚举和注解实现 ACL (Access Control List) 访问控制列表 ???
     * <p>ACL 三元素：
     * <pre>
     * 1 资源，有哪些信息是要被控制起来的
     * 2 权限级别，不同的访问者规划在不同的级别中
     * 3 控制器(也叫鉴权人)，控制不同的级别访问不同的资源
     * </pre>
     */
    @Test
    public void test091() {
        // 初始化商业逻辑
        Foo b = new Foo();

        // 获取注解
        Access access = b.getClass().getAnnotation(Access.class);

        // 没有 Access 注解或者鉴权失败
        if (null == access || !access.level().identify()) {
            p(access.level().REFUSE_WORD);
        }
    }

    /* 第七章：泛型和反射 */

    /**
     * 建议94：不能初始化泛型参数和数组
     * <p>Java 不支持声明泛型数组，更确切的表达式：数组的类型不可以是类型变量，除非是采用通配符的方式 ?
     */
    @Test
    public void test094() {

        class Test<T> {
            // private T t = new T();       // Type parameter 'T' cannot be instantiated directly
            // private T[] tArr = new T[5]; // Type parameter 'T' cannot be instantiated directly
            private final List<T> list = new ArrayList<>();
        }

        // 实现泛型数组
        @Getter
        class Demo<T> {
            // 不再初始化，由构造函数初始化
            private T t;
            private T[] tArr;
            private final List<T> list = new ArrayList<>();

            // 构造函数初始化
            private Demo(Class<T> tType, int size) {
                try {
                    t = tType.getConstructor().newInstance();
                    // 通过反射在运行时构造出实际类型为 tType[] 的对象数组，避免了类型擦除
                    tArr = (T[]) Array.newInstance(tType, size);
                } catch (IllegalAccessException | InstantiationException |
                         InvocationTargetException | NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        }

        Demo<String> demo = new Demo<>(String.class, 10);
        String[] ia = demo.getTArr();
        p(ia);
    }

    /**
     * 建议96：泛型通配符
     * <pre>
     * 1 只参与"读"操作则限定上界 <? extends E>
     * 2 只参与"写"操作则限定下届 <? super E>
     * 3 既参与"读"操作，又参与"写"操作，则都不限定 <E>
     * </pre>
     */
    @Test
    public void test096() {
        List<Number> list = new ArrayList<>();

        // 限定下届 (super)
        write(list);

        // 限定上界 (extends)
        read(list);
    }

    private <E> void read(List<? extends E> list) {
        for (E e : list) {
            p(e);
        }
    }

    private void write(List<? super Number> list) {
        list.add(1);
        list.add(2.3);
    }

    /**
     * 建议97：泛型不支持协变和逆变
     * <pre>
     * 协变：窄类型替换宽类型
     * 逆变：宽类型覆盖窄类型
     * </pre>
     */
    @Test
    public void test097() {
        // List<Number> list = new ArrayList<Integer>(); // 编译错误，泛型不支持协变

        // 使用 extends 模拟协变
        List<? extends Number> list = new ArrayList<Integer>();
        // ? extends Number 表示允许 Number 的所有子类（包括自身）作为泛型参数类型，但在运行期只能是一个具体类型

        // 使用 super 模拟逆变
        List<? super Integer> list2 = new ArrayList<Number>();
        // ? super Integer 表示允许把所有 Integer 父类型作为泛型参数
    }


    /**
     * 建议98：建议使用顺序 List<T> > List<?> > List<Object>
     * <pre>
     * List<T>      表示 List 中的元素为 T 类型，具体类型在运行期决定
     * List<?>      表示的是任意类型；读取的元素都是 Object 类型，需要主动转型，常用于泛型方法的返回值
     * List<Object> 表示 List 中的元素为 Object 类型；读取时要向下转型，写入时需要向上转型
     * </pre>
     */
    @Test
    public void test098() {
        /* List<T> 一般用于 */
        // 1.泛型类的成员变量，成员方法，参数
        class Box<T> {
            private List<T> item;

            public List<T> get() {
                return item;
            }

            public void set(List<T> t) {
                item = t;
            }
        }
        // 2.定义一个通用的泛型方法
        class Function {
            // T 限制了返回类型 List<T> 和 参数 List<T> 是要相同的类型
            public <T> List<T> reduce(List<T> list) {
                return list;
            }
        }
    }

    /**
     * 建议99：严格限定泛型类型采用多重界限
     * <p>只有上界才有多重界限
     */
    @Test
    public void test099() {
        discount(new People());
    }

    // 工资低于2500的并且站立的乘客车票打8折
    private <T extends Staff & Passenger> void discount(T t) {
        if (t.getSalary() < 2500 && t.isStanding()) {
            p("恭喜你，您的车票打八折！");
        }
    }

    static class People implements Staff, Passenger {
        @Override
        public int getSalary() {
            return 2000;
        }

        @Override
        public boolean isStanding() {
            return true;
        }
    }

    interface Staff {
        int getSalary();
    }

    interface Passenger {
        boolean isStanding();
    }


    /**
     * 建议100：泛型数组的真实类型必须是泛型类型的子类型（包括自身）
     */
    @Test
    public void test100() {
        List<Integer> list = List.of(1, 3, 5, 7, 9);
        Integer[] ia = Suggestions.toArray(list, Integer.class);
        p(ia);
    }

    // 自定义一个 List → 数组 方法
    public static <T> T[] toArray(List<T> list, Class<T> tClass) {
        T[] t = (T[]) Array.newInstance(tClass, list.size());
        IntStream.range(0, list.size()).forEach(i -> t[i] = list.get(i));
        return t;
    }

    /**
     * 建议101：Class 类的特殊性
     * <pre>
     * 1 没有公共构造方法：Class 对象是在加载类时由 JVM 以及通过调用类加载器中的 defineClass() 自动构造的
     * 2 可以描述基本类型：虽然8个基本类型在 JVM 中并不是一个对象（一般存在于栈内存中）但是 class 类仍然可以描述它们，如：int.class
     * 3 其对象都是单例模式：一个 Class 实例对象只描述一个类，一个类只有一个 Class 实例对象
     * </pre>
     */
    @Test
    public void test101() throws ClassNotFoundException {
        p("ABC".getClass().equals(String.class)); // true
        p(ArrayList.class.equals(new ArrayList<String>().getClass())); // true

        // Class 类是 Java 的反射入口，只有获得一个类的描述对象才能动态加载和调用
        // 获得一个 Class 对象有三种途径：
        // 1.类属性方式
        Class<?> clazz1 = String.class;
        // 2.getClass()
        String str = "";
        Class<?> clazz2 = str.getClass();
        // 3.forName()
        Class<?> clazz3 = Class.forName("java.lang.String");
    }

    /**
     * 建议103：反射访问属性和方法时设置 xxx.setAccessible(true)
     * <pre>
     * Accessible：并不是访问权限的意思，而是指是否更容易获得，是否进行安全检查
     * 设置为 true 将大幅提升系统性能（20倍）
     * </pre>
     */
    @Test
    public void test103() throws ClassNotFoundException {
        Class<?> clazz = Class.forName("java.lang.String");
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            method.setAccessible(true);
            p(method.getName());
        }
    }

    /**
     * 建议105：动态加载不适合数组
     * <pre>
     * Class.forName(className) 要求必须是一个类，所以8个基本类型排除在外
     * 其次，必须具有可追溯的类路径，否则报 ClassNotFoundException
     * 数组是一个非常特殊的类，虽然它是一个类，但没有定义类路径
     *
     * 元素类型                 编译后的类型
     * byte[]                   [B
     * char[]                   [C
     * double[]                 [D
     * float[]                  [F
     * int[]                    [I
     * long[]                   [J
     * short[]                  [S
     * boolean[]                [Z
     * 引用类型(如String[])  [L引用类型(如：[Ljava.lang.String;)
     * </pre>
     */
    @Test
    public void test105() throws ClassNotFoundException {
        // 加载一个数组
        Class.forName("[Ljava.lang.String");
        // 加载一个 Long 数组
        Class.forName("[J");

        // 以上代码可以把一个数组类加载到内存中（如果内存中没有该类的话），
        // 但不能通过 newInstance() 生成一个实例对象，因为没有定义数组的长度，
        // 在 Java 中数组是定长的，没有长度的数组是不存在的

        // 依据参数动态生成数组
        // 动态创建数组
        String[] strs = (String[]) Array.newInstance(String.class, 8);
        // 创建一个多维数组
        int[][] ints = (int[][]) Array.newInstance(int.class, 2, 3);
    }

    /**
     * 建议106：动态代理
     */
    @Test
    public void test106() {
        // 被代理对象
        TeamService ts = new TeamServiceImpl();

        // 代理对象
        ProxyHandler handler = new ProxyHandler();

        // 动态代理
        TeamService proxy = (TeamService) handler.newProxy(ts);
        proxy.hello();
        proxy.goodbye();
    }

    /**
     * 建议107：动态代理结合装饰器模式
     */
    @Test
    public void test107() {
        SonGoKu sgk = new Monkey();
        sgk.ability();

        sgk = new DecorateSonGoKu(sgk, Fly.class);
        sgk.ability();

        sgk = new DecorateSonGoKu(sgk, Burrow.class);
        sgk.ability();
    }

    /**
     * 建议108：反射结合模板方法模式
     */
    @Test
    public void test108() throws InvocationTargetException, IllegalAccessException {
        Initializer ier = new UserInitializer();
        ier.dataInitialing();
    }

    /* 第八章：异常 */

    /**
     * 建议110：一次捕获多个异常
     */
    @Test
    public void test110() {
        try {
            validate("[Arsenal]", "123");
        } catch (MyException e) {
            for (Throwable throwable : e.getExceptions()) {
                throwable.printStackTrace();
            }
        }
    }

    static class MyException extends Exception {
        @Serial
        private static final long serialVersionUID = 4220795768762558938L;
        private final List<Throwable> causes = new ArrayList<>();

        MyException(List<? extends Throwable> causes) {
            this.causes.addAll(causes);
        }

        List<Throwable> getExceptions() {
            return causes;
        }
    }

    private void validate(String account, String pwd) throws MyException {
        List<Throwable> list = new ArrayList<>();
        try {
            validateAccount(account);
        } catch (Exception e) {
            list.add(e);
        }
        try {
            validatePWD(pwd);
        } catch (Exception e) {
            list.add(e);
        }
        if (list.size() > 0) {
            throw new MyException(list);
        }
    }

    private void validateAccount(String account) {
        if (!account.matches("\\w{4,10}")) {
            throw new RuntimeException("账号只能包含字母、数字、下划线，且4到10位");
        }
    }

    private void validatePWD(String pwd) {
        if (!pwd.matches("[a-zA-Z]{6,12}")) {
            throw new RuntimeException("密码只能包含字母、数字，且6到12位");
        }
    }


    /* 建议112：检测性异常尽可能转化为非检测性异常
     * 1.检测性异常使接口声明脆弱：可能出现实现类"逆影响"接口的情景
     * 2.检测性异常使代码可读性降低
     * 3.检测性异常增加了开发工作量
     */

    /**
     * 建议115：使用 Throwable 获得栈信息
     */
    @Test
    public void test115() {
        class Foo {
            public boolean method() {
                // 获得当前栈信息
                StackTraceElement[] stes = new Throwable().getStackTrace();
                // 检查是否是 methodA() 调用
                for (StackTraceElement ste : stes) {
                    if ("methodA".equals(ste.getMethodName())) return true;
                }
                throw new RuntimeException("除了 methodA() 外，该方法不允许其它方法调用");
            }
        }

        class Invoker {
            private void methodA() {
                p(new Foo().method());
            }

            private void methodB() {
                p(new Foo().method());
            }
        }

        new Invoker().methodA();
        new Invoker().methodB();
    }

    /* 第九章：多线程和并发 */

    /**
     * 建议120：不要使用 stop() 终止线程
     * <pre>
     * 1 stop() 是过时的
     * 2 stop() 导致代码逻辑不完整：一旦执行stop()，即终止正在运行的线程，如果还有重要的代码没有执行（如：线程的主逻辑、资源回收、情景初始化等），这样做就产生了业务逻辑不完整的情况
     * 3 stop() 破坏原子性：stop() 会丢弃所有的锁，导致原子逻辑受损
     * </pre>
     * 终止线程的方法：
     * <pre>
     * 1 自定义标志位
     * 2 interrupt(), isInterrupted()
     * </pre>
     */
    @Test
    public void test120() {
        // 1.自定义标志位
        class SafeStopThread extends Thread {
            // 标志位要使用 volatile 修饰
            private volatile boolean stop = false;

            public void run() {
                while (stop) {
                    p("doSomething ...");
                }
            }

            public void terminate() {
                stop = true;
            }
        }

        // 2.interrupt(), isInterrupted()
        class SafeStopThread2 extends Thread {
            public void run() {
                while (!isInterrupted()) {
                    p("doSomething ...");
                }
            }
        }
    }

    /**
     * 建议122：使用线程异常处理器提升系统可靠性
     * <p>实际应用中需注意以下三个方面：
     * <pre>
     * 1 共享资源锁定
     * 2 脏数据引起系统逻辑混乱
     * 3 内存溢出
     * </pre>
     *
     * @see TcpServer
     */
    @Test
    public void test122() throws InterruptedException {
        TcpServer ts = new TcpServer();
        new Thread(ts).start();

        TimeUnit.SECONDS.sleep(10);
    }

    /**
     * 建议124：异步运算考虑使用Callable接口
     */
    @Test
    public void test124() throws InterruptedException, ExecutionException {
        // 税款计算器
        @Getter
        @Setter
        class TaxCalculator implements Callable<Integer> {
            // 本金
            private int seedMoney;

            // 接收主线程提供的参数
            private TaxCalculator(int seedMonkey) {
                this.seedMoney = seedMonkey;
            }

            @Override
            public Integer call() throws Exception {
                // 模拟复杂计算
                TimeUnit.SECONDS.sleep(2);
                countDownLatch.countDown();
                return seedMoney / 10;
            }
        }

        // 生成一个单线程的异步执行器
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        setCountDownLatch(1);
        // 线程执行后的期望值
        Future<Integer> future = threadPool.submit(new TaxCalculator(100));
        while (!future.isDone()) {
            // 还没有运算完成，等待200毫秒
            TimeUnit.MILLISECONDS.sleep(20);
            // 输出进度符号
            System.out.print("*");
        }
        p("\n计算完成，税金是：" + future.get() + " 元");
        countDownLatch.await();
        threadPool.shutdown();
    }

    /**
     * 建议127：Lock 和 synchronized
     */
    @Test
    public void test127() throws IllegalAccessException, InterruptedException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        // 1.synchronized
        runTasks(TaskWithSync.class);

        // 2.lock
        // TaskWithLock 里的 lock 是对象级别的锁，每次 new 对象不一样
        // 所以没有实现互斥锁效果
        runTasks(TaskWithLock.class);

        // 3.除非把 lock 定义为所有线程的共享变量
        final Lock lock = new ReentrantLock();
        p("***开始执行 TaskWithLock 任务***");
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                try {
                    lock.lock();
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
                StringBuilder sb = new StringBuilder();
                // 线程名称
                sb.append("线程名称：").append(Thread.currentThread().getName());
                // 运行时间戳
                sb.append("，执行事件：").append(Calendar.getInstance().get(Calendar.SECOND)).append("s");
                p(sb);
            }).start();
        }
        p("---TaskWithLock 任务执行完毕---");

        TimeUnit.SECONDS.sleep(10);
    }

    private void runTasks(Class<? extends Runnable> clz) throws IllegalAccessException, InstantiationException, InterruptedException, NoSuchMethodException, InvocationTargetException {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        p("***开始执行 " + clz.getSimpleName() + " 任务***");
        // 启动3个线程
        for (int i = 0; i < 3; i++) {
            threadPool.submit(clz.getConstructor().newInstance());
        }
        // 等待足够长的时间，然后关闭执行器
        TimeUnit.SECONDS.sleep(10);
        p("---" + clz.getSimpleName() + " 任务执行完毕---\n");
        // 关闭执行器
        threadPool.shutdown();
    }

    /* 第十章：性能和效率 */

    /**
     * 建议132：提升 Java 性能的基本方法
     * <pre>
     * 1 不要在循环条件中计算
     * 2 尽可能把变量，方法声明为 final static
     * 3 缩小变量的作用范围：关于变量，能定义在方法内、循环体内、try catch 快内就在里面定义，目的是加快 GC 的回收
     * 4 频繁字符串操作使用 StringBuilder
     * 5 在数据量巨大的情况下使用 Collections.binarySearch() 检索元素，否则使用 List 的 indexOf()
     * 6 覆写 Exception 的 fillInStackTrace()
     * 7 不要建立冗余对象
     * </pre>
     * 三个方面：CPU，内存，I/O
     */
    @Test
    public void test132() {
        // 6.覆写 Exception 的 fillInStackTrace()
        // 性能提升10倍以上
        class MyException extends Exception {
            @Serial
            private static final long serialVersionUID = 4982421851007758674L;

            public Throwable fillInStackTrace() {
                return this;
            }
        }
    }

    /* 建议137：调优 JVM 参数
     * 1.调整堆内存大小
     *      栈内存：空间小，速度快，用来存放对象的引用及程序中的基本类型
     *      堆内存：空间大，速度慢，一般对象在这里生成、使用和消亡
     *      栈空间是由线程开辟，线程结束，栈空间由 JVM 回收，因此它的大小一般不会对性能有太大的影响；
     *  但它会影响系统的稳定性，在超过栈内存的容量时，系统会报 StackOverflowError 错误，
     *  可以通过 "java -Xss <size>" 设置栈内存大小来解决此类问题
     *      堆内存调整得太小会导致 Full GC 频繁执行，轻则导致系统性能急速下降，重则导致系统根本无法使用；
     *      堆内存调整得太大，一是浪费资源，二是产生系统不稳定的情况
     *      设置初始化堆内存为1GB和最大内存为1.5GB：java -Xmx1536m -Xms1024m
     * 2.调整堆内存中各分区的比例
     *      新生区 (Young Generation Space), 养老区 (Tenure Generation Space), 永久储存区 (Permanent Space)
     *      新生区又分为：伊甸区 (Eden Space), 幸存0区 (Survivor 0 Space), 幸存1区 (Survivor 1 Space)
     *      伊甸区满：垃圾回收器进行回收，把剩余的对象移动到幸存区
     *      幸存区满：垃圾回收器进行回收，把剩余的对象移动到养老区
     *      一般情况下，新生区和养老区的比例为1:3：java -XX:NewSize=32m -XX:MaxNewSize=640m -XX:MaxPermSize=1280m -XX:NewRatio=5
     * 3.变更 GC 的垃圾回收策略
     *      启用并行垃圾回收、规定并行回收的线程数量等：
     *      java -XX:+UseParallelGC -XX:parallelGCThreads=20
     * 4.更换 JVM
     *
     * PS：带有"-XX"的 JVM 参数可能是不健壮的，SUN 不推荐使用，但它又是非常好用，这需要在系统升级、迁移时谨慎考虑
     * 生产环境的jvm会把Xms和Xmx配置为相等：https://blog.csdn.net/u010900754/article/details/86629240
     */

    /* 第十一章：开源世界 */

    /* 建议139：大胆采用开源工具
     * 1.普适性原则：选择一个工具或框架要考虑项目成员的整体技术水平，不能有太大的跨度或跳跃性
     * 2.唯一性原则：相同的工具只选择一个或一种，不要让多种相同或相似职能的工具共存
     * 3.大树纳凉原则：使用有名的开源组织的开源工具，如 Apache, Spring, Google 等
     * 4.精而专原则：比如虽然 Spring 提供了 Utils 工具包，但一般不使用，要使用 Apache Commons 的 BeanUtils, lang 等工具包
     * 5.高热度原则：一个开源项目的热度越高，更新就越频繁，使用人群就越广，Bug 的曝光率就越快，修复效率也越高，这对于我们项目的稳定性非常重要
     */

    /* 第十二章：思想为源 */

    /* 建议145：不要完全依靠单元测试来发现问题
     * 1.单元测试不可能测试所有的场景
     *      单元测试必须测试的三种场景：正常场景，边界场景，异常场景
     *      一般情况下，如果这三种测试场景都能出现预期的结果则认为代码正确
     * 2.代码整合错误是不可避免的
     *      单元测试保证了分割的独立单元的正确性，它不能保证一个功能整体的完整性
     * 3.部分代码无法（或很难）测试
     *      多线程测试很复杂，很难进行全面的多线程测试。如果要保证在多线程下测试通过，就必须对代码增加大量的修饰，
     *  这必然会导致代码的可读性和可维护性降低。
     *      另外，单元测试只能对确定算法进行假设，不能对不确定算法进行验证。不确定算法指，条件不能够确认，
     *  如：计算明天通过某一条大桥的车辆数量，必须根据专家经验、天气、交通情况等进行计算，这些条件很多都是非确认的。
     * 4.单元测试验证的是编码人员的假设
     *      单元测试是白箱测试，一般情况下测试代码是编码人员自行编写的。
     *
     * TDD (Test-Driven Development) 测试驱动开发：单元测试先行，而后才编写代码
     */

    /* 建议146：让注释正确、清晰、简洁
     * 1.法律版权信息
     * 2.解释意图的注释
     * 3.警示性注释
     * 4.TODO注释
     */

    /* 建议148：增强类的可替换性
     * 1.子类型必须完全实现父类型的方法
     * 2.前置条件可以被放大：方法中的输入参数称为前置条件
     *      class Base {
     *          public void doStuff(HashMap map) {}
     *      }
     *      class Sub extends Base {
     *          public void doStuff(Map map) {}
     *      }
     * 3.后置条件可以被缩小：方法的返回值称为后置条件
     */
}
