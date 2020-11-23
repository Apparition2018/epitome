package knowledge.design.creational.builder.demo2;

/**
 * @author ljh
 * created on 2020/9/26 2:51
 */
public class BuilderDemo {
    public static void main(String[] args) {
        Waiter waiter = new Waiter();

        PizzaBuilder hawaiianPizzaBuilder = new HawaiianPizzaBuilder();
        waiter.setPizzaBuilder(hawaiianPizzaBuilder);
        waiter.construct();
        System.out.println("pizza: " + waiter.getPizza());

        PizzaBuilder spicyPizzaBuilder = new SpicyPizzaBuilder();
        waiter.setPizzaBuilder(spicyPizzaBuilder);
        waiter.construct();
        System.out.println("pizza: " + waiter.getPizza());
    }
}

class Pizza {
    private String parts;

    public void setParts(String parts) {
        this.parts = parts;
    }

    public String toString() {
        return this.parts;
    }
}

abstract class PizzaBuilder {
    protected Pizza pizza;

    public Pizza getPizza() {
        return pizza;
    }

    public void createNewPizza() {
        pizza = new Pizza();
    }

    public abstract void buildParts();
}

class HawaiianPizzaBuilder extends PizzaBuilder {
    @Override
    public void buildParts() {
        pizza.setParts("cross + mild + ham&pineapple");
    }
}

class SpicyPizzaBuilder extends PizzaBuilder {
    @Override
    public void buildParts() {
        pizza.setParts("pan baked + hot + pepperoni&salami");
    }
}

class Waiter {
    private PizzaBuilder pizzaBuilder;
    public void setPizzaBuilder(PizzaBuilder pizzaBuilder) {
        this.pizzaBuilder = pizzaBuilder;
    }
    public Pizza getPizza() {
        return pizzaBuilder.getPizza();
    }
    public void construct() {
        pizzaBuilder.createNewPizza();
        pizzaBuilder.buildParts();
    }
}