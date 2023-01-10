package jar.hutool.extra;

import cn.hutool.extra.mail.MailUtil;
import l.demo.Demo;
import org.junit.jupiter.api.Test;

/**
 * <a href="https://hutool.cn/docs/#/extra/邮件工具-MailUtil">MailUtil</a>  邮件工具
 * <p>封装 javax.mail，需要再 resources 文件夹下配置 mail.setting 文件
 * <p><a href="https://apidoc.gitee.com/dromara/hutool/cn/hutool/extra/mail/MailUtil.html">MailUtil api</a>
 *
 * @author ljh
 * @since 2020/11/13 17:31
 */
public class MailUtilDemo extends Demo {

    @Test
    public void testMailUtil() {
        MailUtil.send(MY_EMAIL, "测试", HELLO_WORLD, false);
    }
}
