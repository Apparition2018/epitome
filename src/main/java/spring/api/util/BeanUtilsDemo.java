package spring.api.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.Data;
import lombok.SneakyThrows;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;
import org.springframework.util.StopWatch;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * BeanUtils
 * 避免用 Apache Beanutils 进行属性的 copy；Apache BeanUtils 性能较差，
 * 可以使用其他方案比如 Spring BeanUtils, Cglib BeanCopier，注意均是浅拷贝（阿里编程规约）
 *
 * @author ljh
 * created on 2022/1/19 11:17
 */
public class BeanUtilsDemo {

    /**
     * 与 lombok 的 @Accessors(chain = true) 冲突
     */
    @Test
    public void testBeanCopier() {
        User source = new User();
        source.setBirth(new Date());

        StopWatch stopWatch = new StopWatch(RandomStringUtils.randomAlphanumeric(8));
        testCopyEfficiency(stopWatch, source, new User());
        System.out.println(stopWatch.prettyPrint());
        // ---------------------------------------------
        // ns         %     Task name
        // ---------------------------------------------
        // 339363200  033%  spring BeanUtils
        // 108626600  011%  spring cglib BeanCopier
        // 350294700  034%  spring cglib BeanCopier with Converter
        // 175357100  017%  hutool BeanUtil
        // 053863100  005%  hutool BeanCopier
    }

    @Data
    static class User {
        private Date birth;
    }

    @Data
    static class User2 {
        private String birth;
    }

    static class UsersConverter implements Converter {
        @Override
        public Object convert(Object source, Class target, Object context) {
            if (source instanceof Date) {
                return new SimpleDateFormat("yyyy-MM-dd").format(source);
            }
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
            BeanCopier beanCopier = BeanCopier.create(User.class, User2.class, true);
            beanCopier.copy(source, new User2(), new UsersConverter());
        }
        stopWatch.stop();

        stopWatch.start("hutool BeanUtil");
        for (int i = 0; i < cycle; i++) {
            BeanUtil.copyProperties(source, target, false);
        }
        stopWatch.stop();

        stopWatch.start("hutool BeanCopier");
        for (int i = 0; i < cycle; i++) {
            cn.hutool.core.bean.copier.BeanCopier<User> beanCopier = cn.hutool.core.bean.copier.BeanCopier.create(source, new User(), CopyOptions.create());
            beanCopier.copy();
        }
        stopWatch.stop();
    }
}