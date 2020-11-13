package jar.hutool;

import cn.hutool.core.util.IdcardUtil;
import l.demo.Demo;
import org.junit.Test;

import java.util.Date;

/**
 * IdcardUtil   身份证工具
 * https://hutool.cn/docs/#/core/%E5%B7%A5%E5%85%B7%E7%B1%BB/%E8%BA%AB%E4%BB%BD%E8%AF%81%E5%B7%A5%E5%85%B7-IdcardUtil
 * https://apidoc.gitee.com/loolly/hutool/cn/hutool/core/util/IdcardUtil.html
 * <p>
 * 支持大陆15位、18位身份证，港澳台10位身份证
 *
 * @author ljh
 * created on 2020/11/13 17:39
 */
public class IdcardUtilDemo extends Demo {
    
    private static final String ID_CARD = "442000199010160716";
    
    @Test
    public void testIdcardUtil() {
        // 是否合法
        p(IdcardUtil.isValidCard(ID_CARD));
        
        // 年龄
        p(IdcardUtil.getAgeByIdCard(ID_CARD, new Date()));
        // 生日
        p(IdcardUtil.getBirth(ID_CARD));
        // 生日，DateTime
        p(IdcardUtil.getBirthDate(ID_CARD));
        // 性别
        p(IdcardUtil.getGenderByIdCard(ID_CARD));
        // 省份
        p(IdcardUtil.getProvinceByIdCard(ID_CARD));
        // 城市 code
        p(IdcardUtil.getCityCodeByIdCard(ID_CARD));
        
        // 隐藏
        p(IdcardUtil.hide(ID_CARD, 4, 14));
    }
    
}
