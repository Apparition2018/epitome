package knowledge.面向对象;

/**
 * 抽象类
 * <p>
 * 1.抽象类不能实例化对象
 * 2.抽象类不一定包含抽象方法，但是有抽象方法的类必定是抽象类
 * 3.构造方法，类方法 (static) 不能声明为抽象方法
 * 4.抽象类的子类必须重写父类的抽象方法，或者声明自身为抽象类
 */
public class AbstractClass {

    public static void main(String[] args) {
        Employee e = new Salary("Mary", "London", 1);
        e.computePay();
    }

}

/**
 * 抽象类
 */
abstract class Employee {
    private String name;
    private String address;
    private int number;

    public Employee(String name, String address, int number) {
        System.out.println("Constructing an Employee");
        this.name = name;
        this.address = address;
        this.number = number;
    }

    /**
     * 抽象方法
     * <p>
     * 1.如果一个类包含抽象方法，那么该类必须是抽象类
     * 2.抽象类的子类必须重写父类的抽象方法，或者声明自身为抽象类
     */
    public abstract double computePay();

    public void mailCheck() {
        System.out.println("Mailing a check to " + this.name
                + " " + this.address);
    }

    public String toString() {
        return name + " " + address + " " + number;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String newAddress) {
        address = newAddress;
    }

    public int getNumber() {
        return number;
    }
}

class Salary extends Employee {

    private double salary;

    public Salary(String name, String address, int number) {
        super(name, address, number);
    }

    // 重写父类抽象方法
    public double computePay() {
        System.out.println("Computing salary pay for " + getName());
        return salary / 52;
    }
}
