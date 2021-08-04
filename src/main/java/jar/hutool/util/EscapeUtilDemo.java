package jar.hutool.util;

import cn.hutool.core.util.EscapeUtil;
import l.demo.Demo;
import org.junit.jupiter.api.Test;

/**
 * EscapeUtil
 * https://hutool.cn/docs/#/core/%E5%B7%A5%E5%85%B7%E7%B1%BB/Escape%E5%B7%A5%E5%85%B7-EscapeUtil
 * https://apidoc.gitee.com/dromara/hutool/cn/hutool/core/util/EscapeUtil.html
 *
 * @author ljh
 * created on 2020/11/19 9:31
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
