package org.ljh.validation.group;

import jakarta.validation.groups.Default;

/**
 * 新增分组：仅用于新增场景的校验约束。
 *
 * <p>继承 {@link Default}，因此校验 {@code Create} 分组时同时会触发所有
 * 未显式指定分组的约束（默认分组），确保新增入参的完整校验。
 */
public interface Create extends Default {
}
