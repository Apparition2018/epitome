package jar.hutool.util;

import cn.hutool.core.util.PhoneUtil;
import l.demo.Demo;
import org.junit.Test;

/**
 * PhoneUtil
 * https://apidoc.gitee.com/loolly/hutool/cn/hutool/core/util/PhoneUtil.html
 *
 * @author Arsenal
 * created on 2020/11/22 4:04
 */
public class PhoneUtilDemo extends Demo {
    
    @Test
    public void testPhoneUtil() {
        // 座机号 或 手机号（中国）
        p(PhoneUtil.isPhone(MOBILE));
        // 座机号（中国）
        p(PhoneUtil.isTel(MOBILE));   
        // 手机号（中国）
        p(PhoneUtil.isMobile(MOBILE));   
        
        // 获取前三位
        p(PhoneUtil.subBefore(MOBILE));
        // 获取中四位
        p(PhoneUtil.subBetween(MOBILE));
        // 获取后四位
        p(PhoneUtil.subAfter(MOBILE));

        // 隐藏前七位
        p(PhoneUtil.hideBefore(MOBILE));
        // 隐藏中四位
        p(PhoneUtil.hideBetween(MOBILE));
        // 获取中四位
        p(PhoneUtil.hideAfter(MOBILE));
    }
}
