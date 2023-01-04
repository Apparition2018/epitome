package jar.hutool.util;

import cn.hutool.core.lang.ObjectId;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.junit.jupiter.api.Test;

import static l.demo.Demo.p;

/**
 * <a href="https://hutool.cn/docs/#/core/工具类/唯一ID工具-IdUtil">IdUtil</a>   唯一ID工具
 * <pre>
 * 1 UUID
 * 2 ObjectId (MongoDB)
 * 3 Snowflake (Twitter)
 * </pre>
 * 参考：
 * <pre>
 * <a href="https://apidoc.gitee.com/dromara/hutool/cn/hutool/core/util/IdUtil.html">IdUtil api</a>
 * <a href="https://mp.weixin.qq.com/s/a5kT_XBGkkvP9xsMlhWBYw">分布式 Unique ID 的生成方法一览</a>
 * <a href="https://tech.meituan.com/2017/04/21/mt-leaf.html">Leaf——美团点评分布式ID生成系统</a>
 * </pre>
 *
 * @author ljh
 * @since 2020/11/19 16:40
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
