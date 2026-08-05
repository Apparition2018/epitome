package jar.hutool.extra;

import cn.hutool.extra.mail.MailUtil;
import l.demo.Demo;

/**
 * <a href="https://doc.hutool.cn/pages/MailUtil/">MailUtil</a>  邮件工具
 * <p>封装 javax.mail，需要在 resources 文件夹下配置 mail.setting 文件
 * <p>需要引入 com.sun.mail:javax.mail
 * <p><a href="https://plus.hutool.cn/apidocs/cn/hutool/extra/mail/MailUtil.html">MailUtil api</a>
 *
 * @author ljh
 * @since 2020/11/13 17:31
 */
public class MailUtilDemo extends Demo {

    public static void main(String[] args) {
        MailUtil.send(MY_EMAIL, "测试", HELLO_WORLD, false);
    }
}
