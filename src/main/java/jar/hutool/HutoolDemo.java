package jar.hutool;

import cn.hutool.extra.pinyin.PinyinUtil;
import l.demo.Demo;
import org.junit.Test;

/**
 * Hutool
 *
 * @author Arsenal
 * created on 2020/10/24 21:53
 */
public class HutoolDemo extends Demo {

    /**
     * PinyinUtil   拼音工具类
     * https://hutool.cn/docs/#/extra/%E6%8B%BC%E9%9F%B3/%E6%8B%BC%E9%9F%B3%E5%B7%A5%E5%85%B7-PinyinUtil
     */
    @Test
    public void testPinYin() {
        p(PinyinUtil.getPinyin("梁杰辉"));             // liang jie hui
        p(PinyinUtil.getPinyin("梁杰辉", "-"));        // liang-jie-hui
        p(PinyinUtil.getFirstLetter("梁杰辉", "-"));   // l-j-h
    } 
}
