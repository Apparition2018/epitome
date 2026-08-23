package org.ljh.freemarker.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * 用户模型
 *
 * @param id       用户 id
 * @param name     姓名
 * @param email    邮箱
 * @param roles    角色
 * @param createdAt 创建日期
 * @param balance  账户余额
 * @param admin    是否管理员
 */
public record User(
        Long id,
        String name,
        String email,
        List<String> roles,
        LocalDate createdAt,
        BigDecimal balance,
        boolean admin
) {

    /** 示例用户列表 */
    public static List<User> demo() {
        return List.of(
                new User(1L, "张三", "zhangsan@example.com", List.of("admin", "user"),
                        LocalDate.of(2020, 3, 5), new BigDecimal("128.50"), true),
                new User(2L, "李四", "lisi@example.com", List.of("user"),
                        LocalDate.of(2021, 7, 12), new BigDecimal("0.00"), false),
                new User(3L, "王五", "wangwu@example.com", List.of("viewer"),
                        LocalDate.of(2023, 11, 20), new BigDecimal("3200.75"), false)
        );
    }
}
