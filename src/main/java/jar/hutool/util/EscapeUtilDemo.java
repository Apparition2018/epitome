package jar.hutool.util;

import cn.hutool.core.util.EscapeUtil;
import l.demo.Demo;
import org.junit.jupiter.api.Test;

/**
 * <a href="https://hutool.cn/docs/#/core/工具类/Escape工具-EscapeUtil">EscapeUtil</a>
 * <p><a href="https://apidoc.gitee.com/dromara/hutool/cn/hutool/core/util/EscapeUtil.html">EscapeUtil api</a>
 *
 * @author ljh
 * @since 2020/11/19 9:31
 */
public class EscapeUtilDemo extends Demo {

    @Test
    public void testEscapeUtil() {
        // ISO Latin（Unicode 字符集的一个子集）字符串编码
        // escape(CharSequence) 默认不对 *@-_+" 编码 
        p(EscapeUtil.escape(MY_CY + "*@-_+"));                          // %u4e2d%u56fd*@-_+
        p(EscapeUtil.escapeAll(MY_CY + "*@-_+"));                       // %u4e2d%u56fd%2a%40%2d%5f%2b
        p(EscapeUtil.unescape("%u4e2d%u56fd*@-_+"));                    // 中国*@-_+
        p(EscapeUtil.unescape("%u4e2d%u56fd%2a%40%2d%5f%2b"));          // 中国*@-_+

        // HTML 转义
        p(EscapeUtil.escapeHtml4("<div class='test_div'>&</div>"));                     // &lt;div class='test_div'&gt;&amp;&lt;/div&gt;
        p(EscapeUtil.unescapeHtml4("&lt;div class='test_div'&gt;&amp;&lt;/div&gt;"));   // <div class='test_div'>&</div>
    }
}
