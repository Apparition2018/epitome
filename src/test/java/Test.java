import cn.hutool.crypto.SecureUtil;
import com.google.common.collect.Lists;
import l.utils.LUtils;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

// 史上最强整理: https://mp.weixin.qq.com/s/kJpRgfI3zT77XqMeRfmmQQ
// SpringBoot 之整合 Swagger2: https://www.cnblogs.com/zhangyinhua/p/9286391.html
// Gradle / Groovy
// SMB
// Desktop
// ACL
// JAVA与模式
// SpringBoot + Spring Batch
// JWT
// Guava (140)
// shiro
// 多种 Collections (143)
// SSL：https://www.cnblogs.com/crazyacking/p/5648520.html

// https://blog.csdn.net/u012426327/article/details/77152537

// CSDN 阿_毅
// 林祥纤 SpringBoot
public class Test {

    public static void main(String[] args) throws ParseException, IOException {

        System.out.println(SecureUtil.md5().digestHex16("123"));

        System.out.println(LUtils.getHostAddress());

    }

}