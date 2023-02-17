package jar.hutool.util;

import cn.hutool.core.util.IdcardUtil;
import l.demo.Demo;

import java.util.Date;

/**
 * <a href="https://hutool.cn/docs/#/core/工具类/对象工具-ObjectUtil">IdcardUtil</a>   身份证工具
 * <p>支持大陆15位、18位身份证，港澳台10位身份证
 * <p><a href="https://apidoc.gitee.com/dromara/hutool/cn/hutool/core/util/IdcardUtil.html">IdcardUtil api</a>
 *
 * @author ljh
 * @since 2020/11/13 17:39
 */
public class IdcardUtilDemo extends Demo {

    public static void main(String[] args) {
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
