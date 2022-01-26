package knowledge.design.other;

import lombok.Getter;

import java.nio.charset.StandardCharsets;
import java.util.function.BiFunction;
import java.util.function.Predicate;

/**
 * 回调
 * <p>
 * 1.同步回调：在函数返回之前执行回调函数，像模板方法模式
 * 共同目的：复用和扩展
 * 同步回调基于组合关系，把一个对象传递给另一个对象
 * 模板方法基于继承关系，子类重写父类方法
 * 设计模式之美：模板模式（下）：模板模式与Callback回调函数有何区别和联系？
 * <p>
 * 2.异步回调：在函数返回之后执行回调函数，像只有一个观察者的观察者模式
 *
 * @author ljh
 * created on 2022/1/25 22:30
 */
public class CallbackDemo {

    static class SyncCallback {
        /**
         * @see knowledge.design.behavioral.template.method.TemplateMethodDemo
         */
        public static void main(String[] args) {
            Network network = new Network("ljh", "123");

            // loginIn 回调
            BiFunction<String, String, Boolean> loginInCallback = (userName, password) -> {
                System.out.println("\nChecking user's parameters");
                System.out.println("Name: " + userName);
                System.out.print("Password: ");
                for (int i = 0; i < password.length(); i++) {
                    System.out.print("*");
                }
                simulateNetworkLatency();
                System.out.println("\n\nLoginIn success on Facebook");
                return true;
            };

            // sendData 回调
            Predicate<byte[]> sendDataCallback = data -> {
                boolean messagePosted = true;
                if (messagePosted) {
                    System.out.println("Message: '" + new String(data) + "' was posted on Facebook");
                    return true;
                } else {
                    return false;
                }
            };

            // loginOut 回调
            Runnable loginOutCallback = () -> System.out.println("User: '" + network.getUserName() + "' was logged out from Facebook");

            network.post("msg", loginInCallback, sendDataCallback, loginOutCallback);
        }

        @Getter
        static class Network {
            String userName;
            String password;

            public Network(String userName, String password) {
                this.userName = userName;
                this.password = password;
            }

            public boolean post(String message,
                                BiFunction<String, String, Boolean> biFunction,
                                Predicate<byte[]> predicate,
                                Runnable runnable) {
                if (loginIn(userName, password, biFunction)) {
                    boolean result = sendData(message.getBytes(StandardCharsets.UTF_8), predicate);
                    loginOut(runnable);
                    return result;
                }
                return false;
            }

            boolean loginIn(String userName, String password, BiFunction<String, String, Boolean> loginInCallback) {
                return loginInCallback.apply(userName, password);
            }

            boolean sendData(byte[] data, Predicate<byte[]> sendDataCallback) {
                return sendDataCallback.test(data);
            }

            void loginOut(Runnable loginOutCallback) {
                loginOutCallback.run();
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
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
