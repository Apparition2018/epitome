package org.ljh.jpa.entity;

/**
 * CustomerStatus — 客户状态枚举，用于演示 {@code @Enumerated} 的两种存储方式。
 * <ul>
 *     <li>{@code STRING}：库中存枚举名（ACTIVE / INACTIVE）</li>
 *     <li>{@code ORDINAL}：库中存下标（0 / 1）</li>
 * </ul>
 *
 * @author ljh
 * @since 2026/8/12
 */
public enum CustomerStatus {
    ACTIVE,
    INACTIVE
}
