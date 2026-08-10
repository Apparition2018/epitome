package org.ljh.hibernate.dto;

import lombok.Getter;

/**
 * CountryCityDTO — HQL 构造器投影结果载体
 * <p>HQL 中可用 {@code select new 包名.类名(字段...) } 直接把查询结果投影到
 * 一个普通 DTO（需要有匹配的参数构造器），避免逐列组装 {@code Object[]}。
 * 该类不是实体、不映射任何表，仅作为查询结果容器。</p>
 *
 * @author ljh
 * @since 2026/8/10
 */
@Getter
public class CountryCityDTO {

    private final String countryCode;
    private final String cityName;

    /** HQL 构造器投影需要与查询字段顺序匹配的构造器 */
    public CountryCityDTO(String countryCode, String cityName) {
        this.countryCode = countryCode;
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return "CountryCityDTO{" + "countryCode='" + countryCode + '\'' + ", cityName='" + cityName + '\'' + '}';
    }
}
