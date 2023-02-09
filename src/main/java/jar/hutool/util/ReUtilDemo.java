package jar.hutool.util;

import cn.hutool.core.util.ReUtil;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static l.demo.Demo.p;

/**
 * <a href="https://hutool.cn/docs/#/core/工具类/正则工具-ReUtil">ReUtil</a>   正则工具
 * <p><a href="https://apidoc.gitee.com/dromara/hutool/cn/hutool/core/util/ReUtil.html">ReUtil api</a>
 *
 * @author ljh
 * @since 2020/11/2 22:47
 */
public class ReUtilDemo {

    private static final String CONTENT = "ZZZaaabbbccc中文1234";
    private static final String REGEX = "(\\w)aa(\\w)";

    @Test
    public void testReUtil() {
        // escape(char/CharSequence)                                    转义
        p(ReUtil.escape("<(&)>"));                                      // <\(&\)>

        // contains(Pattern/regex, content)                             指定内容中是否有表达式匹配的内容
        p(ReUtil.contains(REGEX, CONTENT));                             // true
        // isMatch(Pattern/regex, content)                              给定内容是否匹配正则
        p(ReUtil.isMatch("\\w+[\u4E00-\u9FFF]+\\d+", CONTENT));         // true

        // count(Pattern/regex, content)                                计算指定字符串中，匹配 pattern 的个数
        p(ReUtil.count(REGEX, CONTENT));                                // 1

        // replaceAll(content, Pattern/regex, replacementTemplate)      正则替换指定值
        // replaceAll(content, Pattern/regex, Func1<Matcher,String>)    正则替换指定值
        p(ReUtil.replaceAll(CONTENT, REGEX, "$1-$2"));                  // ZZZ-abbbccc中文1234

        // extractMulti(Pattern/regex, content, template)               从 content 中匹配出多个值并根据 template 生成新的字符串
        // extractMultiAndDelPre(Pattern/regex, Holder<>, template)     从 content 中匹配出多个值并根据 template 生成新的字符串
        p(ReUtil.extractMulti(REGEX, CONTENT, "$1-$2"));                // Z-a
    }

    @Test
    public void get() {
        // getGroup0(Pattern/regex, content)                            获得匹配的字符串，获得正则中分组0的内容
        p(ReUtil.getGroup0(REGEX, CONTENT));                            // Zaaa
        // getGroup1(Pattern/regex, content)                            获得匹配的字符串，获得正则中分组1的内容
        p(ReUtil.getGroup1(REGEX, CONTENT));                            // Z
        // get(Pattern/regex, content, groupIndex)                      获得匹配的字符串，对应分组0表示整个匹配内容，1表示第一个括号分组内容，依次类推
        p(ReUtil.get(REGEX, CONTENT, 0));                               // Zaaa
        p(ReUtil.get(REGEX, CONTENT, 1));                               // Z
        p(ReUtil.get(REGEX, CONTENT, 2));                               // a
        // getAllGroups(Pattern, content[, withGroup0])                 获得匹配的字符串匹配到的所有分组
        p(ReUtil.getAllGroups(Pattern.compile(REGEX), CONTENT, true));  // [Zaaa, Z, a]

        // getFirstNumber(CharSequence StringWithNumber)                从字符串中获得第一个整数
        p(ReUtil.getFirstNumber(CONTENT));                              // 1234
    }

    @Test
    public void find() {
        // findAllGroup0(Pattern/regex, content)                        取得内容中匹配的所有结果，获得匹配的所有结果中正则对应分组0的内容
        p(ReUtil.findAllGroup0(REGEX, CONTENT));                        // [Zaaa]
        // findAllGroup1(Pattern/regex, content)                        取得内容中匹配的所有结果，获得匹配的所有结果中正则对应分组1的内容
        p(ReUtil.findAllGroup1(REGEX, CONTENT));                        // [Z]
        // findAll(Pattern/regex, content, groupIndex[, Collection)     取得内容中匹配的所有结果
        p(ReUtil.findAll(REGEX, CONTENT, 0));                           // [Zaaa]
        p(ReUtil.findAll(REGEX, CONTENT, 1));                           // [Z]
        p(ReUtil.findAll(REGEX, CONTENT, 2));                           // [a]
    }

    @Test
    public void del() {
        // delFirst(regex, content)                                     删除匹配的第一个内容
        p(ReUtil.delFirst(REGEX, CONTENT));                             // ZZbbbccc中文1234
        // delAll(regex, content)                                       删除匹配的全部内容
        p(ReUtil.delAll(REGEX, CONTENT));                               // ZZbbbccc中文1234
        // delPre(regex, content)                                       删除正则匹配到的内容及之前的字符。如果没有找到，则返回原文
        p(ReUtil.delPre(REGEX, CONTENT));                               // bbbccc中文1234
    }
}
