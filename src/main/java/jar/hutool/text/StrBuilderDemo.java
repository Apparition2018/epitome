package jar.hutool.text;

import cn.hutool.core.text.StrBuilder;
import l.demo.Demo;

/**
 * <a href="https://doc.hutool.cn/pages/StrBuilder/">StrBuilder</a> 可复用字符串生成器
 * <p><a href="https://plus.hutool.cn/apidocs/cn/hutool/core/text/StrBuilder.html">StrBuilder api</a>
 *
 * @author ljh
 * @since 2020/11/5 2:20
 */
public class StrBuilderDemo extends Demo {

    public static void main(String[] args) {
        stopWatch.start("JDK StringBuilder");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < MILLION; i++) {
            sb.append("test");
            // 重新构建新的字符串，需要重新构建StringBuilder对象，造成性能损耗和内存浪费
            sb = new StringBuilder();
        }
        stopWatch.stop();

        stopWatch.start("HuTool StrBuilder");
        StrBuilder sb2 = new StrBuilder();
        for (int i = 0; i < MILLION; i++) {
            sb2.append("test");
            // 重新构建新的字符串，不必开辟新内存
            sb2.reset();
        }
        stopWatch.stop();

        p(stopWatch.prettyPrint());
        // ---------------------------------------------
        // ns         %     Task name
        // ---------------------------------------------
        // 024636000  076%  JDK StringBuilder
        // 007733500  024%  HuTool StrBuilder
    }
}
