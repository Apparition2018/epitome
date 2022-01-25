package knowledge.design.behavioral.template.method;

import knowledge.design.other.CallbackDemo;
import lombok.NoArgsConstructor;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.AbstractList;
import java.util.AbstractMap;
import java.util.AbstractSet;

/**
 * 模板方法模式：定义一个操作中的算法骨架，而将算法的一些步骤延迟到子类中，使得子类可以不改变该算法结构的情况下重定义该算法的某些特定步骤
 * 使用场景：多个类可抽象出相同的算法步骤，算法步骤的实现可以相同（复用），也可以有各自不同的实现（扩展)
 * 使用实例：
 * 1.{@link InputStream} {@link OutputStream} {@link Reader} {@link Writer} 所有非抽象方法
 * 2.{@link AbstractList} {@link AbstractSet} {@link AbstractMap} 所有非抽象方法
 * 3.{@link javax.servlet.http.HttpServlet} 所有默认发送 HTTP 405 错误相应的 doXXX()
 * 基于同步回调：{@link CallbackDemo}
 * 4.{@link org.springframework.jdbc.core.JdbcTemplate}
 * 5.{@link org.springframework.data.redis.core.RedisTemplate}
 * 6.{@link org.springframework.transaction.support.TransactionTemplate}
 * <p>
 * 角色：
 * 抽象类 AbstractClass：
 * 1.基本方法：
 * -    1.1 抽象方法：子类必须实现
 * -    1.2 默认方法：子类可重写，注意不要违反里氏替换原则
 * -    1.3 钩子方法：子类可重写，使得子类可以控制父类的行为
 * -        1.3.1 用于逻辑判断的方法，返回类型通常为 boolean，方法名通常为 isXXX()
 * -        1.3.2 空方法
 * 2.模板方法：按某种顺序调用基本方法
 * 实现类 ConcreteClass：重写步骤方法
 * <p>
 * 优点：符合单一职责原则、开闭原则
 * <p>
 * Template Method：https://refactoringguru.cn/design-patterns/template-method
 * Java设计模式：http://c.biancheng.net/view/1376.html
 * 菜鸟教程：https://www.runoob.com/design-pattern/template-pattern.html
 *
 * @author ljh
 * created on 2020/9/26 2:51
 */
public class TemplateMethodDemo {

    /**
     * 社交网络
     * https://refactoringguru.cn/design-patterns/template-method/java/example
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Input user name: ");
        String userName = reader.readLine();
        System.out.print("Input password: ");
        String password = reader.readLine();

        System.out.print("Input message: ");
        String message = reader.readLine();
        System.out.println("\nChoose social network for posting message.\n" +
                "1 - Facebook\n" +
                "2 - Twitter");
        int choice = Integer.parseInt(reader.readLine());
        Network network = null;
        if (choice == 1) {
            network = new Facebook(userName, password);
        } else if (choice == 2) {
            network = new Twitter(userName, password);
        }
        network.post(message);
    }

    /**
     * AbstractClass
     */
    @NoArgsConstructor
    abstract static class Network {
        String userName;
        String password;

        /**
         * 模板方法：修饰为 final，防止子类重写
         */
        public final boolean post(String message) {
            if (loginIn(this.userName, this.password)) {
                boolean result = sendData(message.getBytes(StandardCharsets.UTF_8));
                loginOut();
                return result;
            }
            return false;
        }

        abstract boolean loginIn(String userName, String password);

        abstract boolean sendData(byte[] data);

        abstract void loginOut();
    }

    /**
     * ConcreteClass
     */
    static class Facebook extends Network {
        public Facebook(String userName, String password) {
            this.userName = userName;
            this.password = password;
        }

        @Override
        boolean loginIn(String userName, String password) {
            System.out.println("\nChecking user's parameters");
            System.out.println("Name: " + this.userName);
            System.out.print("Password: ");
            for (int i = 0; i < password.length(); i++) {
                System.out.print("*");
            }
            simulateNetworkLatency();
            System.out.println("\n\nLoginIn success on Facebook");
            return true;
        }

        @Override
        boolean sendData(byte[] data) {
            boolean messagePosted = true;
            if (messagePosted) {
                System.out.println("Message: '" + new String(data) + "' was posted on Facebook");
                return true;
            } else {
                return false;
            }
        }

        @Override
        void loginOut() {
            System.out.println("User: '" + userName + "' was logged out from Facebook");
        }
    }

    /**
     * ConcreteClass
     */
    static class Twitter extends Network {
        public Twitter(String userName, String password) {
            this.userName = userName;
            this.password = password;
        }

        @Override
        boolean loginIn(String userName, String password) {
            System.out.println("\nChecking user's parameters");
            System.out.println("Name: " + this.userName);
            System.out.print("Password: ");
            for (int i = 0; i < this.password.length(); i++) {
                System.out.print("*");
            }
            simulateNetworkLatency();
            System.out.println("\n\nLoginIn success on Twitter");
            return true;
        }

        @Override
        boolean sendData(byte[] data) {
            boolean messagePosted = true;
            if (messagePosted) {
                System.out.println("Message: '" + new String(data) + "' was posted on Twitter");
                return true;
            } else {
                return false;
            }
        }

        @Override
        void loginOut() {
            System.out.println("User: '" + userName + "' was logged out from Twitter");
        }
    }

    private static void simulateNetworkLatency() {
        try {
            int i = 0;
            System.out.println();
            while (i < 10) {
                System.out.print(".");
                Thread.sleep(500);
                i++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
