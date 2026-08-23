package org.ljh.dependencies.group.hutool.math;

import cn.hutool.core.math.Money;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Locale;

/**
 * <a href="https://plus.hutool.cn/apidocs/cn/hutool/core/math/Money.html">Money</a>    货币
 *
 * @author ljh
 * @since 2020/11/22 2:43
 */
public class MoneyTests {

    @Test
    public void test() {
        Money money = new Money(new BigDecimal("12.34"), Currency.getInstance(Locale.CHINA), RoundingMode.HALF_EVEN);
        System.out.println(money.getCurrency());// CNY
        System.out.println(money.getCent());    // 1234
        System.out.println(money.add(new Money(new BigDecimal("43.21"))));
    }
}
