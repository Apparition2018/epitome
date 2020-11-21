package jar.hutool.util;

import cn.hutool.core.util.EscapeUtil;
import l.demo.Demo;
import org.junit.Test;

/**
 * EscapeUtil
 * https://hutool.cn/docs/#/core/%E5%B7%A5%E5%85%B7%E7%B1%BB/Escape%E5%B7%A5%E5%85%B7-EscapeUtil
 * https://apidoc.gitee.com/loolly/hutool/cn/hutool/core/util/EscapeUtil.html
 *
 * @author ljh
 * created on 2020/11/19 9:31
 */
public class EscapeUtilDemo extends Demo {
    
    @Test
    public void test() {
        // ISO Latin（Unicode 字符集的一个子集）字符串编码
        // escape(CharSequence) 默认不对 *@-_+" 编码 
        p(EscapeUtil.escape(MY_NAME + "*@-_+"));                        // %u674e%u767d*@-_+
        p(EscapeUtil.escapeAll(MY_NAME + "*@-_+"));                     // %u674e%u767d%2a%40%2d%5f%2b
        p(EscapeUtil.unescape("%u674e%u767d*@-_+"));                    // 李白*@-_+
        p(EscapeUtil.unescape("%u674e%u767d%2a%40%2d%5f%2b"));          // 李白*@-_+
        
        // HTML 转义
        p(EscapeUtil.escapeHtml4("<div class='test_div'>&</div>"));                     // &lt;div class='test_div'&gt;&amp;&lt;/div&gt;
        p(EscapeUtil.unescapeHtml4("&lt;div class='test_div'&gt;&amp;&lt;/div&gt;"));   // <div class='test_div'>&</div>
    }
}
