package knowledge.api.util;

import l.demo.Demo;
import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Optional
 * <p>
 * https://docs.oracle.com/javase/8/docs/api/allclasses-noframe.html
 * https://www.cnblogs.com/xingzc/p/5778090.htmlsout
 * https://www.oschina.net/translate/understanding-accepting-and-leveraging-optional-in?lang=chs&page=1#
 *
 * @author ljh
 * created on 2020/11/17 19:09
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

        // isPresent() 用来检查 Optional 实例是否有值
        if (name.isPresent()) {
            // 调用 get() 返回 Optional 的值
            p(name.get()); // Mary
        }

        // get() 返回存在的值，或抛出 NoSuchElementException
        try {
            p(empty.get());
        } catch (NoSuchElementException ex) {
            p(ex.getMessage()); // No value present
        }

        // ifPresent() 接受 lambda 表达式参数
        // 如果 Optional 值不为空，lambda 表达式会处理并在其上执行操作
        name.ifPresent(value -> p("The length of value is: " + value.length())); // The length of value is: 8

        // 如果有值 orElse() 返回 Option 实例，否则返回传入的错误信息
        p(empty.orElse("There is no value present!")); // There is no value present!
        p(name.orElse("There is some value!"));        // Mary

        // orElseGet() 与 orElse() 类似，区别在于传入的默认值
        // orElseGet() 接受 lambda 表达式生成默认值
        p(empty.orElse("Default Value"));  // Default Value
        p(name.orElse("Default Value"));   // Mary

        // orElseThrow() 与 orElse() 类似，区别在于返回值
        // orElseThrow() 抛出由传入的 lambda 表达式/方法生成异常
        try {
            empty.orElseThrow(RuntimeException::new);
        } catch (Throwable throwable) {
            p(throwable.getMessage());  // null
        }

        // map() 通过传入的 lambda 表达式修改 Optional 实例默认值
        // lambda 表达式返回值会包装为 Optional 实例
        Optional<String> upperName = name.map(String::toUpperCase);
        p(upperName.orElse("No value found")); // MARY

        // flatMap() 与 map() 类似，区别在于 lambda 表达式的返回值
        // map() 的 lambda 表达式返回值可以是任何类型，但是返回值会包装成 Optional 实例
        // flatMap() 的 lambda 返回值只能是 Optional 类型
        upperName = name.flatMap(value -> Optional.of(value.toUpperCase()));
        p(upperName.orElse("No value found")); // MARY

        // filter() 检查 Optional 值是否满足给定条件
        // 如果满足返回 Optional 实例值，否则返回空 Optional
        Optional<String> longName = name.filter(value -> value.length() > 6);
        p(longName.orElse("The name is less than 6 characters")); // The name is less than 6 characters
        Optional<String> anotherName = Optional.of("Apparition");
        Optional<String> shortName = anotherName.filter(value -> value.length() > 6);
        p(shortName.orElse("The name is less than 6 characters")); // Apparition
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

    private static class Person {

        private Address address;

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setAddress(Address address) {
            this.address = address;
        }
    }

    private static class Address {

        private Country country;

        public Optional<Country> getCountry() {
            return Optional.ofNullable(country);
        }

        public void setCountry(Country country) {
            this.country = country;
        }
    }

    private static class Country {

        private String iso;

        public Optional<String> getIso() {
            return Optional.ofNullable(iso);
        }

        public void setIso(String iso) {
            this.iso = iso;
        }
    }


}
