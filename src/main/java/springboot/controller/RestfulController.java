package springboot.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import l.demo.Person;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import springboot.exception.ServiceException;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    @Resource(name = "personMap")
    private Map<Integer, Person> personMap;

    @PostMapping
    @ApiOperation("新增人员")
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
    @ApiOperation("获取人员列表")
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

    @GetMapping("/{id}")
    @ApiOperation("获取人员")
    public Person getPerson(@PathVariable Integer id) {
        return personMap.get(id);
    }

    @PutMapping("/{id}")
    @ApiOperation("修改人员")
    public Person putPerson(@PathVariable Integer id, @RequestBody Person newPerson) {
        Person person = personMap.get(id);
        if (person == null) throw new ServiceException("id not exist");
        BeanUtil.copyProperties(newPerson, person, CopyOptions.create(Person.class, true));
        personMap.put(id, person);
        return person;
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除人员")
    public Person deletePerson(@PathVariable Integer id) {
        personMap.remove(id);
        return personMap.get(id);
    }
}
