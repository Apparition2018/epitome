package knowledge.异常和错误.自定义异常;

public class BankDemo {

    public static void main(String[] args) {
        CheckingAccount ca = new CheckingAccount(101);
        System.out.println("Depositing $500...");
        ca.deposit(500.00);

        try {
            System.out.println("\nWithdrawing $100...");
            ca.withdraw(100.00);
            System.out.println("\nWithdrawing $600...");
            ca.withdraw(600.00);
        } catch (CustomException e) {
            System.out.println("Sorry, but you are short $" + e.getAmount());
            e.printStackTrace();
        }
    }

}
