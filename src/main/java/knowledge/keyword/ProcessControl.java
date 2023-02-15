package knowledge.keyword;

import org.junit.jupiter.api.Test;

import static l.demo.Demo.p;

/**
 * 程序控制
 * <p>阿里编程规约：
 * <pre>
 * 1 在 if / else / for / while / do 语句中必须使用大括号，即使只有一行代码
 * 2 表达异常的分支时，少用 if-else 方式，这种方式可以改写成：if (condition) { ...; return obj; }
 *   超过 3 层的 if-else 的逻辑判断代码可以使用卫语句 (guard clauses)、策略模式、状态模式等来实现
 * 3 除常用方法（如 getXxx / isXxx）等外，不要在条件判断中执行其它复杂的语句，将复杂逻辑判断的结果赋值给一个有意义的布尔变量名，以提高可读性
 * 4 循环体中的语句要考量性能，以下操作尽量移至循环体外处理，如定义对象、变量、获取数据库连接，进行不必要的 try-catch 操作（这个 try-catch 是否可以移至循环体外）
 * 5 避免采用取反逻辑运算符。如：if (!(x >= 628))
 * 6 下列情形，不需要进行参数校验
 *   6.1 极有可能被循环调用的方法。但在方法说明里必须注明外部参数检查。
 *   6.2 底层调用频度比较高的方法。毕竟是像纯净水过滤的最后一道，参数错误不太可能到底层才会暴露问题。一般 DAO层与 Service 层都在同一个应用中，部署在同一台服务器中，所以 DAO 的参数校验，可以省略。
 *   6.3 被声明成 private 只会被自己代码所调用的方法，如果能够确定调用方法的代码传入参数已经做过检查或者肯定不会有问题，此时可以不校验参数。
 * </pre>
 *
 * @author ljh
 * @since 2021/5/21 11:45
 */
public class ProcessControl {

    /**
     * Switch
     * <pre>
     * 1 JDK5-  byte, short, char, int
     * 2 JDK5   Byte, Short, Character, Integer, enum
     * 3 JDK7   String
     * </pre>
     * 阿里编程规约：
     * <pre>
     * 1 在一个 switch 块内，每个 case 要么通过 continue / break / return 等来终止，要么注释说明程序将继续执行到哪一个 case 为止；
     *   在一个 switch 块内，都必须包含一个 default 语句并且放在最后，即使它什么代码也没有
     * 2 当 switch 括号内的变量类型为 String 并且此变量为外部参数时，必须先进行 null 判断
     * </pre>
     */
    @Test
    public void testSwitch() {
        p(season("春"));
        p(season("秋"));
    }

    /**
     * @see <a href="https://openjdk.org/jeps/361">JDK14 JEP 361: Switch Expressions</a>
     */
    private String season(String season) {
        return switch (season) {
            case "春" -> "Spring";
            case "夏" -> "Summer";
            case "秋" -> "Autumn";
            case "冬" -> "Winter";
            default -> {
                p("Unknown season");
                yield "Unknown season";
            }
        };
    }
}
