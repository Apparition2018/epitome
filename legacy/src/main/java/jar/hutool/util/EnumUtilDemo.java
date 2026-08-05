package jar.hutool.util;

import cn.hutool.core.util.EnumUtil;
import l.demo.CompanyEnum;

import static l.demo.Demo.p;

/**
 * <a href="https://doc.hutool.cn/pages/EnumUtil/">EnumUtil</a>
 * <p><a href="https://plus.hutool.cn/apidocs/cn/hutool/core/util/EnumUtil.html">EnumUtil api</a>
 *
 * @author ljh
 * @since 2020/11/2 13:58
 */
public class EnumUtilDemo {

    public static void main(String[] args) {
        // getEnumMap
        p(EnumUtil.getEnumMap(CompanyEnum.class));
        // {SF=Company{company='顺丰速运', code=1001}, YTO=Company{company='圆通速递', code=1002}, STO=Company{company='申通物流', code=1003}, EMS=Company{company='中国邮政', code=1004}, DHL=Company{company='中外运敦豪', code=1005}}

        // getNames
        p(EnumUtil.getNames(CompanyEnum.class));                // [SF, YTO, STO, EMS, DHL]

        // getFieldNames
        p(EnumUtil.getFieldNames(CompanyEnum.class));           // [company, code, name]

        // getFieldValues
        p(EnumUtil.getFieldValues(CompanyEnum.class, "code"));  // [1001, 1002, 1003, 1004, 1005]

        // getEnumAt
        p(EnumUtil.getEnumAt(CompanyEnum.class, 0));            // Company{company='顺丰速运', code=1001}

        // fromString, fromStringQuietly
        p(EnumUtil.fromString(CompanyEnum.class, "SF"));        // Company{company='顺丰速运', code=1001}

        // likeValueOf
        p(EnumUtil.likeValueOf(CompanyEnum.class, "SF"));       // Company{company='顺丰速运', code=1001}

        // isEnum
        p(EnumUtil.isEnum(CompanyEnum.SF));                     // true

        // contains, notContains
        p(EnumUtil.contains(CompanyEnum.class, "DHL"));         // true

        // equals, equalsIgnoreCase
        p(EnumUtil.equals(CompanyEnum.DHL, "DHL"));             // true
    }
}
