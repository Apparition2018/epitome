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
 * 分布式 Unique ID 的生成方法一览：https://mp.weixin.qq.com/s/a5kT_XBGkkvP9xsMlhWBYw
 * Leaf——美团点评分布式ID生成系统：https://tech.meituan.com/2017/04/21/mt-leaf.html
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

        // MongoDB 数据库的一种唯一ID 生成策略，UUID version1 的变种
        p(ObjectId.next());     // 605417e6f6735b3a8062c04d
        p(IdUtil.objectId());   // 605417e6f6735b3a8062c04e

        // Snowflake
        // 1 bit    41 bit      10 bit    12 bit
        // 符号位     时间戳      workerId   序列号
        // workerId，datacenterId
        Snowflake snowflake = IdUtil.getSnowflake(1, 1);
        p(snowflake.nextId());  // 1372749159878234112
    }
}
