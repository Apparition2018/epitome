package springboot.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import l.demo.Person;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Spring Boot 2.x基础教程：构建RESTful API与单元测试：https://blog.didispace.com/spring-boot-learning-21-2-1/
 *
 * @author ljh
 * created on 2021/8/20 10:19
 */
@RestController
@RequestMapping("/persons")
public class RestFulController {

    private final Map<Integer, Person> personMap = new ConcurrentHashMap<>();

    @PostMapping("")
    public String postPerson(@RequestBody Person person) {
        personMap.put(person.getId(), person);
        return "success";
    }

    @GetMapping("")
    public List<Person> getPersonList(@RequestParam(required = false) String keyword) {
        if (StringUtils.isNotEmpty(keyword)) {
            return personMap.values().stream().filter(p -> p.getName().contains(keyword)).collect(Collectors.toList());
        }
        return new ArrayList<>(personMap.values());
    }

    @GetMapping("/{id}")
    public Person getPerson(@PathVariable Integer id) {
        return personMap.get(id);
    }

    @PutMapping("/{id}")
    public String putPerson(@PathVariable Integer id, @RequestBody Person newPerson) throws InvocationTargetException, IllegalAccessException {
        Person person = personMap.get(id);
        if (person == null) return "id not exist";
        BeanUtil.copyProperties(newPerson, person, CopyOptions.create(Person.class, true));
        personMap.put(id, person);
        return "success";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable Integer id) {
        personMap.remove(id);
        return "success";
    }
}
