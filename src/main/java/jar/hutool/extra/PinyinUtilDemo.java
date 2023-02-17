package jar.hutool.extra;

import cn.hutool.extra.pinyin.PinyinUtil;
import l.demo.Demo;

/**
 * <a href="https://hutool.cn/docs/#/extra/拼音/拼音工具-PinyinUtil">PinyinUtil</a>   拼音工具
 * <p>需要引入 io.github.biezhi:TinyPinYin
 * <p><a href="https://apidoc.gitee.com/dromara/hutool/cn/hutool/extra/pinyin/PinyinUtil.html">PinyinUtil api</a>
 *
 * @author ljh
 * @since 2020/11/5 16:10
 */
public class PinyinUtilDemo extends Demo {

    public static void main(String[] args) {
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
