package springboot.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import l.demo.Person;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import springboot.exception.ServiceException;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <a href="https://blog.didispace.com/spring-boot-learning-21-2-1/">构建 RESTful API 与单元测试</a>
 *
 * @author ljh
 * @since 2021/8/20 10:19
 */
@RestController
@RequestMapping("persons")
@Tag(name = "Restful")
public class RestfulController {

    @Resource(name = "personMap")
    private Map<Integer, Person> personMap;

    @PostMapping
    @Operation(summary = "新增人员")
    public Person postPerson(@RequestBody Person person) {
        personMap.put(person.getId(), person);
        return person;
    }

    /**
     * 获取人员列表
     *
     * @param keyword    关键字
     * @param sort       排序          +id, -create_time
     * @param fields     返回字段      id, name, age
     * @param pageNumber 页面号码
     * @param pageSize   页面条数
     */
    @GetMapping
    @Operation(summary = "获取人员列表")
    public List<Person> getPersonList(@RequestParam(required = false) String keyword,
                                      @RequestParam(required = false) List<String> sort,
                                      @RequestParam(required = false) List<String> fields,
                                      @RequestParam(required = false) Integer pageNumber,
                                      @RequestParam(required = false) Integer pageSize) {
        if (StringUtils.isNotEmpty(keyword)) {
            return personMap.values().stream().filter(p -> p.getName().contains(keyword)).collect(Collectors.toList());
        }
        return new ArrayList<>(personMap.values());
    }

    @GetMapping("{id}")
    @Operation(summary = "获取人员")
    public Person getPerson(@PathVariable Integer id) {
        return personMap.get(id);
    }

    @PutMapping("{id}")
    @Operation(summary = "修改人员")
    public Person putPerson(@PathVariable Integer id, @RequestBody Person newPerson) {
        Person person = personMap.get(id);
        if (person == null) throw new ServiceException("id not exist");
        // put 更新所有字段（没有提供的字段设为 null）
        BeanUtil.copyProperties(newPerson, person, CopyOptions.create(Person.class, true));
        personMap.put(id, person);
        return person;
    }

    @PatchMapping("{id}")
    @Operation(summary = "修改人员")
    public Person patchPerson(@PathVariable Integer id, @RequestBody Person newPerson) {
        Person person = personMap.get(id);
        if (person == null) throw new ServiceException("id not exist");
        // patch 只更新提供的字段
        Field[] fields = Person.class.getDeclaredFields();
        for (Field field : fields) {
            if (Modifier.isStatic(field.getModifiers()) || Modifier.isFinal(field.getModifiers())) continue;
            if ("id".equals(field.getName())) continue;
            field.setAccessible(true);
            try {
                Object newValue = field.get(newPerson);
                if (newValue != null) field.set(person, newValue);
            } catch (IllegalAccessException e) {
                return null;
            }
        }
        personMap.put(id, person);
        return person;
    }

    @DeleteMapping("{id}")
    @Operation(summary = "删除人员")
    public Person deletePerson(@PathVariable Integer id) {
        personMap.remove(id);
        return personMap.get(id);
    }
}
