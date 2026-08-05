# Mapper 相关代码（已移除归档）

> 本项目曾引入 `tk.mybatis`（通用 Mapper）作为示例，仅 `TkMapperTest` 引用，无任何业务 service 调用。
> 以下为移除前的相关代码，留档备查。移除原因：与 MyBatis-Plus 同层冲突，二选一。

---

## 1. 通用 Mapper 基类 `springboot/mapper/tk/MyMapper.java`

```java
package springboot.mapper.tk;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * MyMapper
 *
 * @author ljh
 * @since 2022/11/1 16:23
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
```

## 2. 具体 Mapper `springboot/mapper/tk/sales/SalesTkMapper.java`

```java
package springboot.mapper.tk.sales;

import org.springframework.stereotype.Component;
import springboot.domain.master.Sales;
import springboot.mapper.tk.MyMapper;

/**
 * SalesTkMapper
 *
 * @author ljh
 * @since 2022/11/1 17:16
 */
@Component
public interface SalesTkMapper extends MyMapper<Sales> {
}
```

## 3. 启动类 tk `@MapperScan`（`springboot/EpitomeApplication.java`）

移除的 import：

```java
import tk.mybatis.spring.annotation.MapperScan;
```

移除的注解与注释：

```java
// TK Mybatis MapperScan，不能包含通用 mapper 的路径，所以 MyMapper.java 不能放在 springboot.mapper.master.tk 里
@MapperScan(basePackages = "springboot.mapper.tk.sales")
```

## 4. tk Mapper 配置（`src/main/resources/application-mybatis.properties`）

移除以下配置项：

```properties
# Mapper：https://github.com/abel533/Mapper
mapper.mapper=springboot.mapper.tk.MyMapper
mapper.identity=MYSQL
# 字符串不需要 != ""
mapper.not-empty=false
```

## 5. 测试 `springboot/TkMapperTest.java`

```java
package springboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import springboot.domain.master.Sales;
import springboot.mapper.tk.sales.SalesTkMapper;
import tk.mybatis.mapper.entity.Example;

/**
 * TkMapper
 *
 * @author ljh
 * @since 2022/11/1 17:17
 */
@SpringBootTest(classes = EpitomeApplication.class)
public class TkMapperTest {

    @Autowired
    private SalesTkMapper salesTkMapper;

    @Test
    public void testTkMapper() {
        Sales sales = new Sales();
        sales.setYearId(2013);
        System.err.println(salesTkMapper.select(sales));
    }

    @Test
    public void testExample() {
        Example example = new Example(Sales.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("yearId", "2013");
        criteria.andEqualTo("monthId", "12");
        System.err.println(salesTkMapper.selectByExample(example));
    }
}
```

## 6. pom.xml 依赖

移除的版本属性：

```xml
<!-- https://mvnrepository.com/artifact/tk.mybatis/mapper-spring-boot-starter -->
<mapper-spring-boot-starter.version>6.0.0</mapper-spring-boot-starter.version>
```

移除的依赖：

```xml
<dependency>
    <groupId>tk.mybatis</groupId>
    <artifactId>mapper-spring-boot-starter</artifactId>
    <version>${mapper-spring-boot-starter.version}</version>
</dependency>
```
