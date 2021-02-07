package jar.hutool.extra;

import cn.hutool.extra.mail.MailUtil;
import l.demo.Demo;
import org.junit.jupiter.api.Test;

/**
 * MailUtil     邮件工具
 * 封装 javax.mail，需要再 resources 文件夹下配置 mail.setting 文件
 * https://hutool.cn/docs/#/extra/%E9%82%AE%E4%BB%B6%E5%B7%A5%E5%85%B7-MailUtil
 * https://apidoc.gitee.com/loolly/hutool/cn/hutool/extra/mail/MailUtil.html
 *
 * @author ljh
 * created on 2020/11/13 17:31
 */
public class MailUtilDemo extends Demo {

    @Test
    public void testMailUtil() {
        MailUtil.send(MY_EMAIL, "测试", HELLO_WORLD, false);
    }
}
