package springboot.init;

import l.demo.Person;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static l.demo.Demo.personList;

/**
 * DataLoader
 *
 * @author ljh
 * @since 2021/9/3 9:18
 */
@Configuration
public class DataLoader implements CommandLineRunner {

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
