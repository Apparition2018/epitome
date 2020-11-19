package jar.hutool.util;

import cn.hutool.core.util.EnumUtil;
import l.demo.Company;
import l.demo.Demo;
import org.junit.Test;

/**
 * EnumUtil
 * https://hutool.cn/docs/#/core/%E5%B7%A5%E5%85%B7%E7%B1%BB/%E6%9E%9A%E4%B8%BE%E5%B7%A5%E5%85%B7-EnumUtil
 * https://apidoc.gitee.com/loolly/hutool/cn/hutool/core/util/EnumUtil.html
 *
 * @author ljh
 * created on 2020/11/2 13:58
 */
public class EnumUtilDemo extends Demo {

    @Test
    public void testEnumUtil() {

        // getEnumMap
        p(EnumUtil.getEnumMap(Company.class));
        // {SF=Company{company='顺丰速运', code=1001}, YTO=Company{company='圆通速递', code=1002}, STO=Company{company='申通物流', code=1003}, EMS=Company{company='中国邮政', code=1004}, DHL=Company{company='中外运敦豪', code=1005}}

        // getNames
        p(EnumUtil.getNames(Company.class));                // [SF, YTO, STO, EMS, DHL]
        
        //getFieldNames
        p(EnumUtil.getFieldNames(Company.class));           // [company, code, name]

        // getFieldValues
        p(EnumUtil.getFieldValues(Company.class, "code"));  // [1001, 1002, 1003, 1004, 1005]

        // getEnumAt
        p(EnumUtil.getEnumAt(Company.class, 0));            // Company{company='顺丰速运', code=1001}

        // fromString, fromStringQuietly
        p(EnumUtil.fromString(Company.class, "SF"));        // Company{company='顺丰速运', code=1001}

        // likeValueOf
        p(EnumUtil.likeValueOf(Company.class, "SF"));       // Company{company='顺丰速运', code=1001} 

        // isEnum
        p(EnumUtil.isEnum(Company.SF));                     // true
        
        // contains, notContains
        p(EnumUtil.contains(Company.class, "DHL"));         // true
        
        // equals, equalsIgnoreCase
        p(EnumUtil.equals(Company.DHL, "DHL"));             // true
    }
}
