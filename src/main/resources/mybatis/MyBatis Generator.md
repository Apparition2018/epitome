# [MyBatis Generator](http://mybatis.org/generator/index.html)

---
## Generator.xml
- @see generator-config.xml
---
## mybatis-generator-maven-plugin
- pom.xml
```xml
<plugin>
    <groupId>org.mybatis.generator</groupId>
    <artifactId>mybatis-generator-maven-plugin</artifactId>
    <version>${mybatis-generator-core.version}</version>
    <executions>
        <!-- 编译代码时，自动执行 mybatis generator -->
        <execution>
            <id>Generate MyBatis Artifacts</id>
            <goals>
                <goal>generate</goal>
            </goals>
        </execution>
    </executions>
    <configuration>
        <configurationFile>${basedir}/src/main/resources/mybatis/generator-config.xml</configurationFile>
        <!-- 是否重写文件
            注意：XML 不会重写，总是将新生成的内容合并到旧文件之下 -->
        <overwrite>false</overwrite>
        <!-- 是否在输出日志中打印进度信息 -->
        <verbose>true</verbose>
    </configuration>
    <dependencies>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
    </dependencies>
</plugin>
```
---
## 运行 MyBatis Generator
1. [命令提示符](http://mybatis.org/generator/running/runningFromCmdLine.html) ：`java -jar mybatis-generator-core-x.x.x.jar -configfile generator-config.xml`
2. [mybatis-generator-maven-plugin](http://mybatis.org/generator/running/runningWithMaven.html)
    - Maven → <project_name> → Plugins → mybatis-generator → mybatis-generator:generate
---
## insert 语句相关
```xml
<insert keyProperty="id" useGeneratedKeys="true"/>
```
---