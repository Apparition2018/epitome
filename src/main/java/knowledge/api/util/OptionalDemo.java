package knowledge.api.util;

import l.demo.Demo;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * <a href="https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html">Optional</a>
 * <p>阿里编程规约：
 * <pre>
 * 1 方法的返回值可以为 null，不强制返回空集合，或者空对象等，必须添加注释充分说明什么情况下会返回 null 值；
 * 2 防止 NPE 是调用者的责任。即使被调用方法返回空集合或者空对象，对调用者来说，也并非高枕无忧，必须考虑到远程调用失败、序列化失败、运行时异常等场景返回 null 的情况；
 * </pre>
 * <a href="https://www.cnblogs.com/xingzc/p/5778090.html">Optional 深度解析</a>
 *
 * @author ljh
 * @since 2020/11/17 19:09
 */
public class OptionalDemo extends Demo {

    @Test
    public void testOptional1() {
        // of()         创建 Optional 实例，不接受 null
        // ofNullable() 创建 Optional 实例，接受 null
        // empty()      创建空的 Optional 实例
        Optional<String> name = Optional.of("Mary");
        Optional<Object> empty = Optional.ofNullable(null);
        empty = Optional.empty();

        // get() 如果存在值，返回该值，否则抛出 NoSuchElementException
        try {
            p(empty.get());
        } catch (NoSuchElementException ex) {
            p(ex.getMessage()); // No value present
        }

        // isPresent() 如果存在值，返回 true，否则返回 false
        if (name.isPresent()) {
            // 调用 get() 返回 Optional 的值
            p(name.get()); // Mary
        }

        // ifPresent(Consumer<? super T> action) 如果存在值，执行 action
        name.ifPresent(value -> p("The length of value is: " + value.length())); // The length of value is: 8

        // ifPresentOrElse(Consumer<? super T> action, Runnable emptyAction)
        // 如果存在值，执行 action，否者执行 emptyAction，JDK9 引入

        // or(Supplier<? extends Optional<? extends T>> supplier)
        // 如果存在值，返回该值的 Optional，否者返回 supplier 生成 Optional，JDK9 引入

        // orElse(T other) 如果存在值，返回该值，否则返回 other
        p(empty.orElse("There is no value present!")); // There is no value present!
        p(name.orElse("There is some value!"));        // Mary

        // orElseGet(Supplier<? extends T> supplier)
        // 如果存在值，返回该值，否则返回 supplier 生成的结果
        p(empty.orElseGet(() -> "Default Value"));  // Default Value
        p(name.orElseGet(() -> "Default Value"));   // Mary

        // orElseThrow()
        // 如果存在值，返回该值，否则抛出 NoSuchElementException，JDK10 引入

        // orElseThrow(Supplier<? extends X> exceptionSupplier)
        // 如果存在值，返回该值，否则抛出 exceptionSupplier 生成的异常
        try {
            empty.orElseThrow(RuntimeException::new);
        } catch (Throwable throwable) {
            p(throwable.getMessage());  // null
        }

        // map(Function<? super T, ? extends U> mapper)
        // 如果存在值，返回 mapper 应用于该值的结果，否则返回一个空的 Optional
        Optional<String> upperName = name.map(String::toUpperCase);
        p(upperName.orElse("No value found")); // MARY

        // flatMap(Function<? super T, ? extends Optional<? extends U>> mapper)
        // 如果存在值，返回 mapper 应用于该值的结果，否则返回一个空的 Optional
        // map() 的 mapper 返回值可以是任何类型
        // flatMap() 的 mapper 返回值只能是 Optional 类型
        upperName = name.flatMap(value -> Optional.of(value.toUpperCase()));
        p(upperName.orElse("No value found")); // MARY

        // filter(Predicate<? super T> predicate)
        // 如果存在值，并且该值于 predicate 匹配，返回该值的 Optional，否则返回一个空的 Optional
        Optional<String> shortName = name.filter(value -> value.length() > 6);
        p(shortName.orElse("The name is less than 6 characters")); // The name is less than 6 characters
        Optional<String> anotherName = Optional.of("Apparition");
        Optional<String> longName = anotherName.filter(value -> value.length() > 6);
        p(longName.orElse("The name is less than 6 characters")); // Apparition

        // stream()
        // 如果存在值，返回包含该值的 Stream，否则返回 Stream.empty()，JDK9 引入
        Stream<String> stream = name.stream();
    }

    @Test
    public void testOptional2() {
        Person person = new Person();
        Address address = new Address();
        Country country = new Country();
        address.setCountry(country);
        person.setAddress(address);

        Optional<Person> p = Optional.of(person);

        p(p
                .flatMap(Person::getAddress)
                .flatMap(Address::getCountry)
                .flatMap(Country::getIso)
                .filter(x -> x.length() > 2)
                .isPresent());
    }

    static class Person {
        private Address address;

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setAddress(Address address) {
            this.address = address;
        }
    }

    static class Address {

        private Country country;

        public Optional<Country> getCountry() {
            return Optional.ofNullable(country);
        }

        public void setCountry(Country country) {
            this.country = country;
        }
    }

    static class Country {
        private String iso;

        public Optional<String> getIso() {
            return Optional.ofNullable(iso);
        }

        public void setIso(String iso) {
            this.iso = iso;
        }
    }
}
