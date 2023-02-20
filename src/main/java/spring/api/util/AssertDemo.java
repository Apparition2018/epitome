package spring.api.util;

import org.springframework.util.Assert;

/**
 * Assert
 *
 * @author ljh
 * @since 2021/9/7 18:30
 */
public class AssertDemo {

    public static void main(String[] args) {
        Assert.hasLength(" ", "不能为空");
        Assert.hasText("1", "不能为空");
    }
}
