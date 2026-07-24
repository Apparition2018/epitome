package org.ljh.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator;

/**
 * MybatisApplication — normal 模块
 * <p>扫描 {@code org.ljh.mybatis.mapper.normal} 下的 Mapper，对应 MyBatis3 + XML 方式。</p>
 *
 * @author ljh
 * @since 2026/7/18 13:07
 */
@SpringBootApplication
@MapperScan(value = "org.ljh.mybatis.mapper.normal", nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class)
public class MybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class, args);
    }
}
