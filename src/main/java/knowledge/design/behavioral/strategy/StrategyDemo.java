package knowledge.design.behavioral.strategy;

import lombok.Data;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 策略模式：定义一系列算法，并将每一个算法封装到具有共同接口的类中，使得它们可以互相替换
 * 使用场景：多行为、多算法、if-else、switch-case
 * 使用实例：
 * 1.{@link java.util.Comparator#compare(Object, Object)}
 * 2.{@link java.util.concurrent.ThreadPoolExecutor} 的构造器参数 RejectedExecutionHandler 的四个实现：
 * -    https://blog.csdn.net/yangsen159/article/details/103146038
 * 3.{@link javax.servlet.Filter#doFilter(ServletRequest, ServletResponse, FilterChain)}
 * 4.{@link javax.servlet.http.HttpServlet#service(ServletRequest, ServletResponse)}
 * 5.{@link javax.servlet.http.HttpServlet} 的 doXXX
 * 6.{@link org.springframework.core.io.Resource}
 * <p>
 * 角色:
 * 上下文角色 Context：接收 Strategy 的引用
 * 抽象策略角色 Strategy：声明 Context 执行策略的方法
 * 具体策略角色 ConcreteStrategy：实现 Strategy
 * <p>
 * 优点：符合开闭原则
 * 缺点：使用者必须事先知道有哪些策略和策略之间的不同才能选择策略
 * <p>
 * Strategy：https://refactoringguru.cn/design-patterns/strategy
 * Java设计模式：http://c.biancheng.net/view/1378.html
 * 菜鸟教程：https://www.runoob.com/design-pattern/strategy-pattern.html
 *
 * @author ljh
 * created on 2020/9/26 2:51
 */
public class StrategyDemo {
    private static final Map<Integer, Integer> priceOnProducts = new HashMap<>();
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final Order order = new Order();
    private static PayStrategy strategy;

    static {
        priceOnProducts.put(1, 2200);
        priceOnProducts.put(2, 1850);
        priceOnProducts.put(3, 1100);
        priceOnProducts.put(4, 890);
    }

    /**
     * 线上购物选择不同的支付方式
     * https://refactoringguru.cn/design-patterns/strategy/java/example
     */
    public static void main(String[] args) throws IOException {
        while (!order.isClosed()) {
            int cost;

            String continueChoice;
            do {
                System.out.print("请选择产品:" + "\n" +
                        "1 - 主板" + "\n" +
                        "2 - CPU" + "\n" +
                        "3 - 硬盘" + "\n" +
                        "4 - 内存条" + "\n");
                int choice = Integer.parseInt(reader.readLine());
                cost = priceOnProducts.get(choice);
                System.out.print("数量: ");
                int count = Integer.parseInt(reader.readLine());
                order.setTotalCost(cost * count);
                System.out.print("需要继续选择产品吗? Y/N: ");
                continueChoice = reader.readLine();
            } while (continueChoice.equalsIgnoreCase("Y"));

            if (strategy == null) {
                System.out.println("请选择付款方式:" + "\n" +
                        "1 - PalPay" + "\n" +
                        "2 - Credit Card");
                String paymentMethod = reader.readLine();

                // 客户端根据用户输入或应用配置等创建不同的策略
                if (paymentMethod.equals("1")) {
                    strategy = new PayByPayPal();
                } else {
                    strategy = new PayByCreditCard();
                }
            }

            order.processOrder(strategy);

            System.out.print("支付 " + order.getTotalCost() + " 元或继续购物? P/C: ");
            String proceed = reader.readLine();
            if (proceed.equalsIgnoreCase("P")) {
                if (strategy.pay(order.getTotalCost())) {
                    System.out.println("支付成功.");
                } else {
                    System.out.println("失败! 请检查您的数据.");
                }
                order.setClosed();
            }
        }
    }

    /**
     * Strategy
     */
    interface PayStrategy {
        boolean pay(int paymentAmount);

        void collectPaymentDetails();
    }

    /**
     * ConcreteStrategy
     */
    static class PayByPayPal implements PayStrategy {
        private static final Map<String, String> DATA_BASE = new HashMap<>();
        private final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
        private String email;
        private String password;
        private boolean signedIn;

        static {
            DATA_BASE.put("amanda1985", "amanda@ya.com");
            DATA_BASE.put("qwerty", "john@amazon.eu");
        }

        @Override
        public void collectPaymentDetails() {
            try {
                while (!signedIn) {
                    System.out.print("请输入用户邮箱: ");
                    email = READER.readLine();
                    System.out.print("请输入密码: ");
                    password = READER.readLine();
                    if (verify()) {
                        System.out.println("数据验证成功.");
                    } else {
                        System.out.println("邮箱地址或密码错误!");
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        private boolean verify() {
            setSignedIn(email.equals(DATA_BASE.get(password)));
            return signedIn;
        }

        @Override
        public boolean pay(int paymentAmount) {
            if (signedIn) {
                System.out.println("使用 PayPal 支付 " + paymentAmount + " 中.");
                return true;
            } else {
                return false;
            }
        }

        private void setSignedIn(boolean signedIn) {
            this.signedIn = signedIn;
        }
    }

    /**
     * ConcreteStrategy
     */
    static class PayByCreditCard implements PayStrategy {
        private final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
        private CreditCard card;

        @Override
        public void collectPaymentDetails() {
            try {
                System.out.print("请输入卡号: ");
                String number = READER.readLine();
                System.out.print("请输入卡截止日期 'mm/yy': ");
                String date = READER.readLine();
                System.out.print("请输入 CVV 码: ");
                String cvv = READER.readLine();
                card = new CreditCard(number, date, cvv);

                // 验证卡号...

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        @Override
        public boolean pay(int paymentAmount) {
            if (cardIsPresent()) {
                System.out.println("使用 Credit Card 支付 " + paymentAmount + " 中.");
                card.setAmount(card.getAmount() - paymentAmount);
                return true;
            } else {
                return false;
            }
        }

        private boolean cardIsPresent() {
            return card != null;
        }
    }

    @Data
    static class CreditCard {
        private int amount;
        private final String number;
        private final String date;
        private final String cvv;

        CreditCard(String number, String date, String cvv) {
            this.amount = 100_000;
            this.number = number;
            this.date = date;
            this.cvv = cvv;
        }
    }

    /**
     * Context
     */
    static class Order {
        private int totalCost = 0;
        private boolean isClosed = false;

        public void processOrder(PayStrategy strategy) {
            strategy.collectPaymentDetails();
        }

        public void setTotalCost(int cost) {
            this.totalCost += cost;
        }

        public int getTotalCost() {
            return totalCost;
        }

        public boolean isClosed() {
            return isClosed;
        }

        public void setClosed() {
            isClosed = true;
        }
    }
}