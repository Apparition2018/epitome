# Maven

---
## Reference
1. [Maven](https://maven.apache.org/)
2. [Maven – POM Reference](https://maven.apache.org/pom.html)
    - [pom.xml](pom.xml)
3. [Maven – Settings Reference](https://maven.apache.org/settings.html)
    - [settings.xml](settings.xml)
---
## 课程
1. [Maven 项目依赖管理](https://www.imooc.com/learn/1282)
2. [Maven Guide](https://www.jetbrains.com/idea/guide/tutorials/marco-codes-maven/introduction/)
---
## [安装](https://maven.apache.org/install.html)
1. 安装 JDK（以下二选一）
    1. 设置系统变量 JAVA_HOME
    2. 设置系统变量 PATH
2. [下载](https://maven.apache.org/download.cgi)并解压 Maven
    - maven 1.x   设置 MAVEN_HOME、PATH 变量
    - maven 2.x   设置 M2_HOME、PATH 变量
    - maven 3.x   只需设置 PATH 变量
3. `mvn -v`
---
## [创建项目](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html#creating-a-project)
- 命令：`mvn archetype:generate -DgroupId=com.mycompany.app -DartifactId=my-app -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false`
- [标准目录布局](https://maven.apache.org/guides/introduction/introduction-to-the-standard-directory-layout.html)
---
## [仓库](https://maven.apache.org/repositories/index.html)
1. 本地仓库：运行 Maven 的计算机上的一个目录。它缓存远程下载，并包含尚未发布的临时构建 artifacts
    - 默认路径：${user.home}/.m2/repository
    - 自定义路径：修改 settings.xml 中的 &lt;localRepository/&gt; 
2. 远程仓库：通过各种协议（如：file:// 和 http://）访问的任何其它类型的仓库（远程仓库、中央仓库、私有仓库）
    - [settings.xml](settings.xml)
    ```xml
    <!-- 阿里云云效 Maven：https://developer.aliyun.com/mvn/guide -->
    <mirror>
        <id>aliyunmaven</id>
        <mirrorOf>*,!central</mirrorOf>
        <name>阿里云公共仓库</name>
        <url>https://maven.aliyun.com/repository/public</url>
    </mirror>
    ```
    ```xml
    <!-- central 使用其它代理仓库 -->
    <repository>
        <id>central</id>
        <url>https://maven.aliyun.com/repository/central</url>
        <releases>
            <enabled>true</enabled>
        </releases>
        <snapshots>
            <enabled>true</enabled>
        </snapshots>
    </repository>
    ```
---
## [生命周期](https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html#Lifecycle_Reference)
1. clean：删除上一个生成生成的所有文件
2. default：处理项目部署
    ```
    validate        验证项目   验证项目是否正确，所有必要的信息是否可用
    compile         编译      编译项目的源代码
    test            测试      使用合适的单元测试框架运行测试。这些测试不应该要求打包或部署代码
    package         打包      将编译后的代码打包为可分发的格式，例如 JAR
    verify          验证包     运行任何检查以验证包是否有效并符合质量标准
    install         安装      将包安装到本地存储库中，以便在本地其他项目中用作依赖项
        依赖冲突：①短路优先；②声明优先
    deploy          部署      在集成或发布环境中完成，将最终包复制到远程存储库，以便与其他开发人员和项目共享
    ```
3. site：生成项目的站点文档
---
## [Maven Wrapper](https://maven.apache.org/wrapper/)
- 作用：确保用户拥有运行 Maven build 所需的一切
- 将所有必要的 Maven Wrapper 文件添加或更新到项目中：`mvn -N wrapper:wrapper`，-N 表示非递归
    - Linux：`./mvnw clean install`
    - Windows：`mvnw.cmd celan install`
---
## [私有服务器](https://www.imooc.com/video/22615)
- [sonatype nexus repository](https://www.sonatype.com/products/sonatype-nexus-repository)
---
