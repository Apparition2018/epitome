package org.ljh.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator;

/**
 * MybatisDynamicApplication — dynamic 模块
 * <p>扫描 {@code org.ljh.mybatis.mapper.dynamic} 下的 Mapper，对应 MyBatis3DynamicSql 方式。</p>
 *
 * @author ljh
 * @since 2026/7/24
 */
@SpringBootApplication
@MapperScan(value = "org.ljh.mybatis.mapper.dynamic", nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class)
public class MybatisDynamicApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisDynamicApplication.class, args);
    }
}
