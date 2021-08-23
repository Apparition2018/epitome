package springboot.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
 * 构建 RESTful API 与单元测试：https://blog.didispace.com/spring-boot-learning-21-2-1/
 *
 * @author ljh
 * created on 2021/8/20 10:19
 */
@RestController
@RequestMapping("/persons")
@Api(tags = "Restful")
public class RestfulController {

    private final Map<Integer, Person> personMap = new ConcurrentHashMap<>();

    @PostMapping
    @ApiOperation("新增人员")
    public String postPerson(@RequestBody Person person) {
        personMap.put(person.getId(), person);
        return "success";
    }

    @GetMapping
    @ApiOperation("获取人员列表")
    public List<Person> getPersonList(@RequestParam(required = false) String keyword) {
        if (StringUtils.isNotEmpty(keyword)) {
            return personMap.values().stream().filter(p -> p.getName().contains(keyword)).collect(Collectors.toList());
        }
        return new ArrayList<>(personMap.values());
    }

    @GetMapping("/{id}")
    @ApiOperation("获取人员")
    public Person getPerson(@PathVariable Integer id) {
        return personMap.get(id);
    }

    @PutMapping("/{id}")
    @ApiOperation("修改人员")
    public String putPerson(@PathVariable Integer id, @RequestBody Person newPerson) throws InvocationTargetException, IllegalAccessException {
        Person person = personMap.get(id);
        if (person == null) return "id not exist";
        BeanUtil.copyProperties(newPerson, person, CopyOptions.create(Person.class, true));
        personMap.put(id, person);
        return "success";
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除人员")
    public String deletePerson(@PathVariable Integer id) {
        personMap.remove(id);
        return "success";
    }
}
