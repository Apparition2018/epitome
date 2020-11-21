package knowledge.设计模式.creational.builder.demo;

/**
 * Builder: 抽象建造者角色
 * ConcreteBuilder: 具体建造者角色
 * Director: 导演者角色
 * Product: 产品角色
 * <p>
 * 建造者模式：将一个复杂对象的构建与它的表示分离，使得同样的构建过程可以创建不同的表示
 * <p>
 * http://www.cnblogs.com/java-my-life/archive/2012/04/07/2433939.html
 */
public class BuilderDemo {

    public static void main(String[] args) {
        Builder builder = new ConcreteBuilder();
        Director director = new Director(builder);
        director.construct();
        Product product = builder.getResult();
        System.out.println(product.getPart1());
        System.out.println(product.getPart2());
    }

}

// 产品类
class Product {
    /**
     * 定义一些关于产品的操作
     */
    private String part1;
    private String part2;

    public String getPart1() {
        return part1;
    }

    public void setPart1(String part1) {
        this.part1 = part1;
    }

    public String getPart2() {
        return part2;
    }

    public void setPart2(String part2) {
        this.part2 = part2;
    }
}

// 抽象建造者类
interface Builder {
    void buildPart1();

    void buildPart2();

    Product getResult();
}

// 具体建造者类
class ConcreteBuilder implements Builder {

    private Product product = new Product();

    /**
     * 产品零件建造方法一
     */
    @Override
    public void buildPart1() {
        // 构建产品的第一个零件
        product.setPart1("编号：9527");
    }

    /**
     * 产品零件建造方法二
     */
    @Override
    public void buildPart2() {
        // 构建产品的第二个零件
        product.setPart2("名称：XXX");
    }

    /**
     * 产品返还方法
     */
    @Override
    public Product getResult() {
        return product;
    }
}

// 导演类
class Director {
    /**
     * 持有当前需要使用的建造器对象
     */
    private Builder builder;

    /**
     * 构造方法，传入建造器对象
     *
     * @param builder 建造器对象
     */
    Director(Builder builder) {
        this.builder = builder;
    }

    /**
     * 产品构造方法，负责调用各个零件建造方法
     */
    public void construct() {
        builder.buildPart1();
        builder.buildPart2();
    }
}