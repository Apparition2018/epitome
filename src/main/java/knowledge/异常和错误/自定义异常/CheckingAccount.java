package knowledge.异常和错误.自定义异常;

// 此类模拟银行账户
public class CheckingAccount {

    private double balance; // 余额
    private int number;     // 卡号

    CheckingAccount(int number) {
        this.number = number;
    }

    // 存钱
    public void deposit(double amount) {
        balance += amount;
    }

    // 取钱
    public void withdraw(double amount) throws CustomException {
        if (amount <= balance) {
            balance -= amount;
        } else {
            double needs = amount - balance;
            throw new CustomException(needs);
        }
    }

    public double getBalance() {
        return balance;
    }

    public int getNumber() {
        return number;
    }


}
