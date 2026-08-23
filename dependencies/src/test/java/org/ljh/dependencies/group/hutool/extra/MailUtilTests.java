package org.ljh.dependencies.group.hutool.extra;

import cn.hutool.extra.mail.MailUtil;
import org.junit.jupiter.api.Test;

import static com.ljh.commons.Demo.HELLO_WORLD;
import static com.ljh.commons.Demo.MY_EMAIL;

/**
 * <a href="https://doc.hutool.cn/pages/MailUtil/">MailUtil</a>  邮件工具
 * <p>封装 javax.mail，需要在 resources 文件夹下配置 mail.setting 文件
 * <p>需要引入 com.sun.mail:javax.mail
 * <p><a href="https://plus.hutool.cn/apidocs/cn/hutool/extra/mail/MailUtil.html">MailUtil api</a>
 *
 * @author ljh
 * @since 2020/11/13 17:31
 */
public class MailUtilTests {

    @Test
    public void test() {
        MailUtil.send(MY_EMAIL, "测试", HELLO_WORLD, false);
    }
}
