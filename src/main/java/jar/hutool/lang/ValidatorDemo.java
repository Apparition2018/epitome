package jar.hutool.lang;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.IdUtil;
import l.demo.Demo;

/**
 * <a href="https://doc.hutool.cn/pages/Validator/">Validator</a>   字段验证器
 * <p><a href="https://plus.hutool.cn/apidocs/cn/hutool/core/lang/Validator.html">Validator api</a>
 *
 * @author ljh
 * @since 2020/11/19 21:18
 */
public class ValidatorDemo extends Demo {

    /**
     * equal(Object t1, Object t2)
     * isEmpty(Object value)
     * isNotEmpty(Object value)
     * isNull(Object value
     * isNotNull(Object value)
     * isFalse(boolean value)
     * isNumber(CharSequence value)
     * isBetween(Number value, Number min, Number max)
     * isUpperCase(CharSequence value)
     * isLowerCase(CharSequence value)
     * <p>
     * validateXXX(..., String errorMsg)    与 isXXX 的区别是，会抛出异常
     */
    public static void main(String[] args) {
        // 通过正则
        p(Validator.isMatchRegex("\\w+", "A1_"));

        // 包含汉子
        p(Validator.hasChinese(MY_CY));
        // 都为汉子
        p(Validator.isChinese(MY_CY));
        // 英文字母、数字、下划线
        p(Validator.isGeneral("A1_"));
        // 字母、汉子
        p(Validator.isLetter("Aa一"));
        // 字母
        p(Validator.isWord("Aa"));

        // 生日
        p(Validator.isBirthday("20200808"));
        // 手机
        p(Validator.isMobile(MOBILE));
        // 身份证
        p(Validator.isCitizenId(ID_CARD));
        // 邮箱
        p(Validator.isEmail(MY_EMAIL));
        // 邮政编码
        p(Validator.isZipCode("528400"));
        // 车牌号
        p(Validator.isPlateNumber("粤T00001"));
        // 统一社会信用代码
        p(Validator.isCreditCode("91370100780603055K"));

        // URL 地址
        p(Validator.isUrl(BAIDU_URL));
        // IPV4 地址
        p(Validator.isIpv4("127.0.0.1"));
        // IPV6 地址
        p(Validator.isIpv6("0:0:0:0:0:0:0:1"));
        // MAC 地址
        p(Validator.isMac("4c-cc-6a-13-0f-31"));

        // UUID
        p(Validator.isUUID(IdUtil.simpleUUID()));
        // 货币
        p(Validator.isMoney("1000.000"));
        // 16 进制字符串
        p(Validator.isHex("789abc"));
    }
}
