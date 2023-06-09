package spring.api.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

/**
 * Assert
 *
 * @author ljh
 * @since 2021/9/7 18:30
 */
public class AssertDemo {

    public static void main(String[] args) {
        Assert.hasLength(StringUtils.SPACE, "不能为空");
        Assert.hasText("1", "不能为空");
    }
}
