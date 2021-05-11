package jar.hutool;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.TypeReference;
import l.demo.CompanyEnum;
import l.demo.Demo;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Convert  类型转换工具类
 * 实现将任意可能的类型转换为指定类型，同时支持第二个参数 default Value 用于在转换失败时返回一个默认值
 * https://hutool.cn/docs/#/core/%E7%B1%BB%E5%9E%8B%E8%BD%AC%E6%8D%A2/%E7%B1%BB%E5%9E%8B%E8%BD%AC%E6%8D%A2%E5%B7%A5%E5%85%B7%E7%B1%BB-Convert
 * https://apidoc.gitee.com/loolly/hutool/cn/hutool/core/convert/Convert.html
 *
 * @author ljh
 * created on 2020/11/18 16:42
 */
public class ConvertDemo extends Demo {

    @Test
    public void testConvert() {
        p(Convert.toStr(list));
        p(Convert.toStr(arr));
        p(Convert.toStrArray(arr));
        p(Convert.toLongArray(arr));
        p(Convert.toList(arr));
        p(Convert.toMap(Integer.class, Object.class, map));
        p(Convert.toDate("2020-08-08"));
        p(Convert.toEnum(CompanyEnum.class, "SF"));

        // 转换为指定类型
        p(Convert.convert(String.class, arr));
        p(Convert.convert(new TypeReference<List<Integer>>() {
        }, arr));

        // 时间单位转换
        p(Convert.convertTime(444444, TimeUnit.SECONDS, TimeUnit.MINUTES));

        // Unicode
        p(Convert.strToUnicode(MY_NAME));
        p(Convert.unicodeToStr("\\u6881\\u6770\\u8f89"));

        // 编码转换
        p(Convert.convertCharset(MY_NAME, UTF_8, StandardCharsets.ISO_8859_1.name()));

        // 16进制
        p(Convert.toHex(HELLO_WORLD, StandardCharsets.UTF_8));
        p(Convert.hexToStr("48656c6c6f20576f726c6421", StandardCharsets.UTF_8));

        // 原始类和包装类转换
        p(Convert.wrap(int.class));
        p(Convert.unWrap(Integer.class));

        // 阿拉伯金额转换
        p(Convert.digitToChinese(123456.789));          // 壹拾贰万叁仟肆佰伍拾陆元柒角玖分
        p(Convert.numberToChinese(123456.789, true));   // 壹拾贰万叁仟肆佰伍拾陆点柒玖
        p(Convert.numberToChinese(123456.789, false));  // 一十二万三千四百五十六点七九
        p(Convert.numberToWord(123456.789));            // ONE HUNDRED AND TWENTY THREE THOUSAND FOUR HUNDRED AND FIFTY SIX AND CENTS SEVENTY EIGHT ONLY

        // 半角转全角
        p(Convert.toSBC("123456789"));
        // 全角转半角
        p(Convert.toDBC("１２３４５６７８９"));
    }
}
