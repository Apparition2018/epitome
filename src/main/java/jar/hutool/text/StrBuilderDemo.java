package jar.hutool.text;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.text.StrBuilder;
import org.junit.jupiter.api.Test;

import static l.demo.Demo.p;

/**
 * StrBuilder       可复用字符串生成器
 * https://hutool.cn/docs/#/core/%E6%96%87%E6%9C%AC%E6%93%8D%E4%BD%9C/%E5%8F%AF%E5%A4%8D%E7%94%A8%E5%AD%97%E7%AC%A6%E4%B8%B2%E7%94%9F%E6%88%90%E5%99%A8-StrBuilder
 * https://apidoc.gitee.com/loolly/hutool/cn/hutool/core/text/StrBuilder.html
 *
 * @author Arsenal
 * created on 2020/11/5 2:20
 */
public class StrBuilderDemo {

    @Test
    public void testStrBuilder() {
        TimeInterval timer = DateUtil.timer();
        // 1.JDK StringBuilder
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000000; i++) {
            sb.append("test");
            // 重新构建新的字符串，需要重新构建StringBuilder对象，造成性能损耗和内存浪费
            sb = new StringBuilder();
        }
        p(timer.interval());

        timer.restart();
        // 2.HuTool StrBuilder
        StrBuilder sb2 = new StrBuilder();
        for (int i = 0; i < 1000000; i++) {
            sb2.append("test");
            // 重新构建新的字符串，不必开辟新内存
            sb2.reset();
        }
        p(timer.interval());
    }
}
