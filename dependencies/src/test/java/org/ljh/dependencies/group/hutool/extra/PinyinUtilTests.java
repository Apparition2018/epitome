package org.ljh.dependencies.group.hutool.extra;

import cn.hutool.extra.pinyin.PinyinUtil;
import org.junit.jupiter.api.Test;

import static com.ljh.commons.Demo.MY_CY;
import static com.ljh.commons.Demo.p;

/**
 * <a href="https://doc.hutool.cn/pages/PinyinUtil/">PinyinUtil</a>   拼音工具
 * <p>需要引入 io.github.biezhi:TinyPinYin
 * <p><a href="https://plus.hutool.cn/apidocs/cn/hutool/extra/pinyin/PinyinUtil.html">PinyinUtil api</a>
 *
 * @author ljh
 * @since 2020/11/5 16:10
 */
public class PinyinUtilTests {

    @Test
    public void test() {
        // static boolean	isChinese(char c)
        // 是否为中文字符
        p(PinyinUtil.isChinese(MY_CY.toCharArray()[0]));// true

        // static String    getPinyin(String str[, String separator])
        // 将输入字符串转为拼音，以字符为单位插入分隔符
        p(PinyinUtil.getPinyin(MY_CY));                 // zhong guo
        p(PinyinUtil.getPinyin(MY_CY, "-"));            // zhong-guo

        // static String	getFirstLetter(String str[, String separator])
        // 将输入字符串转为拼音首字母，其它字符原样返回
        p(PinyinUtil.getFirstLetter(MY_CY, "-"));       // z-g
    }
}
