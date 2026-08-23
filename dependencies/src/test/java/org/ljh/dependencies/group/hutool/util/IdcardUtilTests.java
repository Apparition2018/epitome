package org.ljh.dependencies.group.hutool.util;

import cn.hutool.core.util.IdcardUtil;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static com.ljh.commons.Demo.ID_CARD;
import static com.ljh.commons.Demo.p;

/**
 * <a href="https://doc.hutool.cn/pages/IdcardUtil/">IdcardUtil</a>   身份证工具
 * <p>支持大陆15位、18位身份证，港澳台10位身份证
 * <p><a href="https://plus.hutool.cn/apidocs/cn/hutool/core/util/IdcardUtil.html">IdcardUtil api</a>
 *
 * @author ljh
 * @since 2020/11/13 17:39
 */
public class IdcardUtilTests {

    @Test
    public void test() {
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
