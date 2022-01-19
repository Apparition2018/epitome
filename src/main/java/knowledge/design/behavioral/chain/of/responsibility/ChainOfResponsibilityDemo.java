package knowledge.design.behavioral.chain.of.responsibility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 责任链模式：
 * 使用场景：
 * 1.
 * 2.
 * 使用实例：
 * 1.javax.servlet.Filter#doFilter()
 * 2.java.util.logging.Logger#log()
 * <p>
 * 角色：
 * 抽象处理者角色 Handler
 * 基础处理者橘色 BaseHandler：可选
 * 具体处理者角色 ConcreteHandler
 * <p>
 * 优点：符合单一职责原则、开闭原则
 * <p>
 * Chain of Responsibility：https://refactoringguru.cn/design-patterns/state
 * Java设计模式：http://c.biancheng.net/view/1383.html
 * 菜鸟教程：https://www.runoob.com/design-pattern/chain-of-responsibility-pattern.html
 *
 * @author Arsenal
 * created on 2020/9/26 2:51
 */
public class ChainOfResponsibilityDemo {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final Server server;

    static {
        server = new Server();
        server.register("admin@example.com", "admin_pass");
        server.register("user@example.com", "user_pass");

        Middleware middleware = new ThrottlingMiddleware(2);
        middleware.linkWith(new UserExistsMiddleware(server))
                .linkWith(new RoleCheckMiddleware());

        server.setMiddleware(middleware);
    }

    /**
     * 过滤访问
     * 1.限流
     * 2.账号密码验证
     * 3.角色验证
     * https://refactoringguru.cn/design-patterns/chain-of-responsibility/java/example
     */
    public static void main(String[] args) throws IOException {
        boolean success;
        do {
            System.out.print("Enter email: ");
            String email = reader.readLine();
            System.out.print("Input password: ");
            String password = reader.readLine();
            success = server.logIn(email, password);
        } while (!success);
    }

    /**
     * BaseHandler
     */
    abstract static class Middleware {
        private Middleware next;

        public Middleware linkWith(Middleware next) {
            this.next = next;
            return next;
        }

        public abstract boolean check(String email, String password);

        protected boolean checkNext(String email, String password) {
            if (next == null) return true;
            return next.check(email, password);
        }
    }

    /**
     * ConcreteHandler
     * 限流
     */
    static class ThrottlingMiddleware extends Middleware {
        private final int requestPerMinute;
        private int request;
        private long currentTime;

        public ThrottlingMiddleware(int requestPerMinute) {
            this.requestPerMinute = requestPerMinute;
            this.currentTime = System.currentTimeMillis();
        }

        @Override
        public boolean check(String email, String password) {
            if (System.currentTimeMillis() > currentTime + 60_000) {
                request = 0;
                currentTime = System.currentTimeMillis();
            }
            request++;
            if (request > requestPerMinute) {
                System.out.println("Request limit exceeded!");
                Thread.currentThread().stop();
            }
            return checkNext(email, password);
        }
    }

    /**
     * ConcreteHandler
     * 账号密码验证
     */
    static class UserExistsMiddleware extends Middleware {
        private final Server server;

        public UserExistsMiddleware(Server server) {
            this.server = server;
        }

        @Override
        public boolean check(String email, String password) {
            if (!server.hasEmail(email)) {
                System.out.println("This email is not registered!");
                return false;
            }
            if (!server.isValidPassword(email, password)) {
                System.out.println("Wrong password!");
                return false;
            }
            return checkNext(email, password);
        }
    }

    /**
     * ConcreteHandler
     * 角色检查
     */
    static class RoleCheckMiddleware extends Middleware {

        @Override
        public boolean check(String email, String password) {
            if (email.equals("admin@example.com")) {
                System.out.println("Hello, admin!");
                return true;
            }
            System.out.println("Hello, user!");
            return checkNext(email, password);
        }
    }

    static class Server {
        private final Map<String, String> users = new HashMap<>();
        private Middleware middleware;

        public void setMiddleware(Middleware middleware) {
            this.middleware = middleware;
        }

        public boolean logIn(String email, String password) {
            if (middleware.check(email, password)) {
                System.out.println("Authorization have been successful!");

                // Do something useful here for authorized users.

                return true;
            }
            return false;
        }

        public void register(String email, String password) {
            users.put(email, password);
        }

        public boolean hasEmail(String email) {
            return users.containsKey(email);
        }

        public boolean isValidPassword(String email, String password) {
            return users.get(email).equals(password);
        }
    }

}
