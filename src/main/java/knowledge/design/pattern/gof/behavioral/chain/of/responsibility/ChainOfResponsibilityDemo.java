package knowledge.design.pattern.gof.behavioral.chain.of.responsibility;

import knowledge.design.pattern.other.behavioral.PipelineDemo;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;

/**
 * 责任链模式：允许请求沿着多个处理者组成的链进行传递，避免了发送方和接收方之间的耦合
 * 使用场景：
 * 1.处理程序能细分成多个处理部分，①固定顺序②动态顺序③动态指定处理部分
 * 2.多个对象可以处理一个请求，但具体由哪个对象处理该请求在运行时确定
 * 使用实例：
 * 1.{@link javax.servlet.Filter#doFilter(ServletRequest, ServletResponse, FilterChain)}
 * 2.{@link java.util.logging.Logger#log(Level, String)}
 * 3.{@link org.springframework.web.servlet.HandlerExecutionChain} 和 {@link org.springframework.web.servlet.HandlerInterceptor}
 * <p>
 * 角色：
 * 抽象处理者 Handler：定义处理请求接口
 * 基础处理者 BaseHandler (可选)：接收下一个 ConcreteHandler 的引用用来创建链，抽取 ConcreteHandler 的共有代码
 * 具体处理者 ConcreteHandler：实现处理请求接口
 * -    第一种 (纯的)：一个请求必定被某一个处理者处理，每个处理者只能自己处理，或者把请求传递给下一个处理者 (策略模式?)
 * -    第二种 (不纯)：每个处理者都可能对请求进行处理，然后把请求传递给下一个处理者，或者终止处理
 * 客户端 Client：根据程序逻辑一次性或动态生成链
 * <p>
 * 优点：符合单一职责原则、开闭原则
 * 扩展：
 * 1.使用 Builder 创建 Handler 链
 * 2.管道模式 {@link PipelineDemo}
 * 3.与 Command 联合使用：https://www.kancloud.cn/sstd521/design/193638
 * <p>
 * Chain of Responsibility：https://refactoringguru.cn/design-patterns/chain-of-responsibility
 * Java设计模式：http://c.biancheng.net/view/1383.html
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
        server.setMiddleware(new Middleware.Builder()
                .setNext(new ThrottlingMiddleware(2))
                .setNext(new UserExistsMiddleware(server))
                .setNext(new RoleCheckMiddleware()).build());
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
     * Handler / BaseHandler
     */
    static abstract class Middleware {
        private Middleware next;

        private void setNext(Middleware next) {
            this.next = next;
        }

        protected abstract boolean check(String email, String password);

        protected boolean checkNext(String email, String password) {
            if (next == null) return true;
            return next.check(email, password);
        }

        static class Builder {
            private Middleware head;
            private Middleware tail;

            public Builder setNext(Middleware next) {
                if (this.head == null) {
                    this.head = this.tail = next;
                    return this;
                }
                this.tail.setNext(next);
                this.tail = next;
                return this;
            }

            public Middleware build() {
                return this.head;
            }
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
                return false;
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
            if ("admin@example.com".equals(email)) {
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
            return Objects.equals(users.get(email), password);
        }
    }
}
