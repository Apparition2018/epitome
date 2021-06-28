package jar.hutool.lang;

import cn.hutool.core.lang.Dict;
import l.demo.Demo;
import l.demo.Person;
import org.junit.jupiter.api.Test;

/**
 * Dict
 * Dict 继承 HashMap，key 为 String，value 为 Object，提供了更灵活多样的使用
 * https://hutool.cn/docs/#/core/%E8%AF%AD%E8%A8%80%E7%89%B9%E6%80%A7/HashMap%E6%89%A9%E5%B1%95-Dict
 * https://apidoc.gitee.com/dromara/hutool/cn/hutool/core/lang/Dict.html
 * 
 * Dict	        filter(String... keys)                      过滤 Dict 保留指定键值对，如果键不存在跳过
 * <T> T	    getXXX(String attr)                         获得特定类型值
 * void	        putAll(Map<? extends String,?> m)           添加所有
 * Dict	        setIgnoreNull(String attr, Object value)    设置列，当键或值为 null 时忽略
 *
 * @author Arsenal
 * created on 2020/11/19 19:29
 */
public class DictDemo extends Demo {

    @Test
    public void testDict() {
        // static Dict	    of(Object... keysAndValues)
        // 根据给定的键值对数组创建 Dict 对象，传入参数必须为 key,value,key,value...
        p(Dict.of(1, "A", 2, "B", 3, "C", 4, "D", 5, "E")); // {1=A, 2=B, 3=C, 4=D, 5=E}

        // Bean → Dict
        Dict dict = Dict.parse(personList.get(0)).filter("id", "name");
        Dict dict2 = Dict.parse(personList.get(1)).filter("id", "name");
        // Dict → Bean
        Person person = dict.toBean(Person.class);
        
        dict.set("age", 18);
        dict2.set("age", 18);
        // 去除相同的部分
        dict.removeEqual(dict2);
        p(dict); // {id=1, name=张三}
    }
}
