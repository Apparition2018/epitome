package springboot.init;

import l.demo.Demo;
import l.demo.Person;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * DataLoader
 *
 * @author ljh
 * created on 2021/9/3 9:18
 */
@Configuration
public class DataLoader extends Demo implements CommandLineRunner {

    private final Map<Integer, Person> personMap = new ConcurrentHashMap<>();

    @Bean("personMap")
    public Map<Integer, Person> initPersonMap() {
        return personMap;
    }

    @Override
    public void run(String... args) throws Exception {
        personList.forEach(person -> personMap.put(person.getId(), person));
    }
}
