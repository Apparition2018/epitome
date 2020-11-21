package jar.hutool.extra;

import cn.hutool.extra.cglib.CglibUtil;
import l.demo.Demo;
import l.demo.Person;
import l.demo.Person.Student;
import org.junit.Test;

/**
 * CglibUtil
 * CGLib：一个强大的,高性能,高质量的Code生成类库，通过此库可以完成动态代理、Bean拷贝等操作。
 * https://hutool.cn/docs/#/extra/cglib/Cglib%E5%B7%A5%E5%85%B7-CglibUtil
 * https://apidoc.gitee.com/loolly/hutool/cn/hutool/extra/cglib/CglibUtil.html
 *
 * @author Arsenal
 * created on 2020/11/21 17:04
 */
public class CglibUtilDemo extends Demo {
    
    @Test
    public void testCglibUtil() {
        // Bean 拷贝方式一
        Person person = new Person();
        CglibUtil.copy(personList.get(0), person);
        p(person);
        
        // Bean 拷贝方式二
        Student student = CglibUtil.copy(personList.get(0), Student.class);
        p(student);
    }
}
