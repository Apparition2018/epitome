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
 * <a href="https://hutool.cn/docs/#/core/类型转换/类型转换工具类-Convert">Convert</a>     类型转换工具类
 * <p>实现将任意可能的类型转换为指定类型，同时支持第二个参数 default Value 用于在转换失败时返回一个默认值
 * <p><a href="https://apidoc.gitee.com/dromara/hutool/cn/hutool/core/convert/Convert.html">Convert api</a>
 *
 * @author ljh
 * @since 2020/11/18 16:42
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
        p(Convert.strToUnicode(MY_CY));
        p(Convert.unicodeToStr("\\u4e2d\\u56fd"));

        // 编码转换
        p(Convert.convertCharset(MY_CY, StandardCharsets.UTF_8.name(), StandardCharsets.ISO_8859_1.name()));

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
