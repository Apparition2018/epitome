package spring.api.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.extra.cglib.CglibUtil;
import l.demo.User;
import lombok.SneakyThrows;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;
import org.springframework.util.StopWatch;

import java.util.Date;

/**
 * BeanUtils
 * Apache BeanUtils 性能较差，可以使用其他方案比如 Spring BeanUtils，Cglib BeanCopier，注意均是浅拷贝（阿里编程规约）
 *
 * @author ljh
 * created on 2022/1/19 11:17
 */
public class BeanUtilsDemo {

    /**
     * Cglib BeanCopier 和 Apache Beanutils 与 lombok 的 @Accessors(chain = true) 冲突
     */
    @Test
    public void testBeanCopier() {
        User source = new User(new Date());

        StopWatch stopWatch = new StopWatch(RandomStringUtils.randomAlphanumeric(8));
        testCopyEfficiency(stopWatch, source, new User());
        System.out.println(stopWatch.prettyPrint());
        // ---------------------------------------------
        // ns         %     Task name
        // ---------------------------------------------
        // 379565400  035%  spring BeanUtils
        // 137157300  013%  spring cglib BeanCopier
        // 069867600  006%  spring cglib BeanCopier with Converter
        // 235593600  022%  hutool BeanUtil
        // 070777500  007%  hutool BeanCopier
        // 195409400  018%  hutool CglibUtil
    }

    static class UsersConverter implements Converter {
        @Override
        public Object convert(Object source, Class target, Object context) {
            return null;
        }
    }

    @SneakyThrows
    public static void testCopyEfficiency(StopWatch stopWatch, User source, User target) {
        int cycle = 100000;

        stopWatch.start("spring BeanUtils");
        for (int i = 0; i < cycle; i++) {
            BeanUtils.copyProperties(source, target);
        }
        stopWatch.stop();

        stopWatch.start("spring cglib BeanCopier");
        for (int i = 0; i < cycle; i++) {
            BeanCopier beanCopier = BeanCopier.create(User.class, User.class, false);
            beanCopier.copy(source, target, null);
        }
        stopWatch.stop();

        stopWatch.start("spring cglib BeanCopier with Converter");
        for (int i = 0; i < cycle; i++) {
            BeanCopier beanCopier = BeanCopier.create(User.class, User.class, true);
            beanCopier.copy(source, target, new UsersConverter());
        }
        stopWatch.stop();

        stopWatch.start("hutool BeanUtil");
        for (int i = 0; i < cycle; i++) {
            BeanUtil.copyProperties(source, target, false);
        }
        stopWatch.stop();

        stopWatch.start("hutool BeanCopier");
        for (int i = 0; i < cycle; i++) {
            cn.hutool.core.bean.copier.BeanCopier<User> beanCopier = cn.hutool.core.bean.copier.BeanCopier.create(source, target, CopyOptions.create());
            beanCopier.copy();
        }
        stopWatch.stop();

        stopWatch.start("hutool CglibUtil");
        for (int i = 0; i < cycle; i++) {
            CglibUtil.copy(source, target);
        }
        stopWatch.stop();
    }
}
