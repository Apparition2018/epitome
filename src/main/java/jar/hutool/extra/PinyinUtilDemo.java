package jar.hutool.extra;

import cn.hutool.extra.pinyin.PinyinUtil;
import l.demo.Demo;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;

/**
 * PinyinUtil   拼音工具
 * 需要引入 io.github.biezhi:TinyPinYin
 * https://hutool.cn/docs/#/extra/%E6%8B%BC%E9%9F%B3/%E6%8B%BC%E9%9F%B3%E5%B7%A5%E5%85%B7-PinyinUtil
 * https://apidoc.gitee.com/loolly/hutool/cn/hutool/extra/pinyin/PinyinUtil.html
 *
 * @author ljh
 * created on 2020/11/5 16:10
 */
public class PinyinUtilDemo extends Demo {

    @Test
    public void testPinyinUtil() {
        // static boolean	isChinese(char c)
        // 是否为中文字符
        p(PinyinUtil.isChinese(MY_NAME.toCharArray()[0]));  // true

        StopWatch stopWatch = StopWatch.createStarted();
        for (int i = 0; i < 3000000; i++) {
            // static String    getPinyin(String str[, String separator])
            // 将输入字符串转为拼音，以字符为单位插入分隔符
            PinyinUtil.getPinyin(MY_NAME);                   // liang jie hui
            PinyinUtil.getPinyin(MY_NAME, "-");              // liang-jie-hui

            // static String	getFirstLetter(String str[, String separator])
            // 将输入字符串转为拼音首字母，其它字符原样返回
            PinyinUtil.getFirstLetter(MY_NAME, "-");         // l-j-h
        }
        p(stopWatch.getTime());
    }
}
