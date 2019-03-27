package knowledge.异常和错误.自定义异常;

/**
 * 自定义异常
 * <p>
 * 1.所有异常都必须是 Throwable 的子类
 * 2.写一个检查性异常类，需要继承 Exception 类
 * 3.写一个运行时异常类，需要继承 RuntimeException 类
 */
public class CustomException extends RuntimeException {

    // 此处的amount用来储存当出现异常（取出钱多于余额时）所缺乏的钱
    private double amount;

    CustomException(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

}
