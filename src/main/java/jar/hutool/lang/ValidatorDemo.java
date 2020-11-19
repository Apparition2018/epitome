package jar.hutool.lang;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.IdUtil;
import l.demo.Demo;
import org.junit.Test;

/**
 * Validator        字段验证器
 * https://hutool.cn/docs/#/core/%E8%AF%AD%E8%A8%80%E7%89%B9%E6%80%A7/%E5%AD%97%E6%AE%B5%E9%AA%8C%E8%AF%81%E5%99%A8-Validator
 * https://apidoc.gitee.com/loolly/hutool/cn/hutool/core/lang/Validator.html
 *
 * @author Arsenal
 * created on 2020/11/19 21:18
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
    @Test
    public void testValidator() {
        // 通过正则
        p(Validator.isMatchRegex("\\w+", "A1_"));

        // 包含汉子
        p(Validator.hasChinese(MY_NAME));
        // 都为汉子
        p(Validator.isChinese(MY_NAME));
        // 英文字母、数字、下划线
        p(Validator.isGeneral("A1_"));
        // 字母、汉子
        p(Validator.isLetter("Aa一"));
        // 字母
        p(Validator.isWord("Aa"));

        // 生日
        p(Validator.isBirthday("20200808"));
        // 手机
        p(Validator.isMobile(MY_PHONE));
        // 身份证
        p(Validator.isCitizenId(MY_ID_CARD));
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
