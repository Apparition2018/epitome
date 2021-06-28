package jar.hutool.util;

import cn.hutool.core.lang.ObjectId;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.junit.jupiter.api.Test;

import static l.demo.Demo.p;

/**
 * IdUtil   唯一ID工具
 * 1.UUID
 * 2.ObjectId (MongoDB)
 * 3.Snowflake (Twitter)
 * https://hutool.cn/docs/#/core/%E5%B7%A5%E5%85%B7%E7%B1%BB/%E5%94%AF%E4%B8%80ID%E5%B7%A5%E5%85%B7-IdUtil
 * https://apidoc.gitee.com/dromara/hutool/cn/hutool/core/util/IdUtil.html
 * 服务化框架－分布式Unique ID的生成方法一览：http://www.voidcn.com/article/p-akujgwvu-hx.html
 *
 * @author ljh
 * created on 2020/11/19 16:40
 */
public class IdUtilDemo {
    @Test
    public void testIdUtil() {
        // UUID
        p(IdUtil.randomUUID()); // b174a93f-c148-41b9-96ab-35959de81b21
        p(IdUtil.simpleUUID()); // f2843351a7d8486ca84fcb2857b6283d

        // ObjectId：UUID version1 的变种
        p(ObjectId.next());     // 605417e6f6735b3a8062c04d
        p(IdUtil.objectId());   // 605417e6f6735b3a8062c04e

        // Snowflake：简短且按时间有序生成
        // 终端 ID，数据中心 ID
        Snowflake snowflake = IdUtil.getSnowflake(1, 1);
        p(snowflake.nextId());  // 1372749159878234112
    }
}
