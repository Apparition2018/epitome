package jar.hutool.math;

import cn.hutool.core.math.Money;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Locale;

/**
 * Money    货币
 * https://apidoc.gitee.com/loolly/hutool/cn/hutool/core/math/Money.html
 *
 * @author Arsenal
 * created on 2020/11/22 2:43
 */
public class MoneyDemo {

    @Test
    public void testMoney() {
        Money money = new Money(new BigDecimal("12.34"), Currency.getInstance(Locale.CHINA), RoundingMode.HALF_EVEN);
        System.out.println(money.getCurrency());// CNY
        System.out.println(money.getCent());    // 1234
        System.out.println(money.add(new Money(new BigDecimal("43.21"))));
    }
}
