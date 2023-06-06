# Maven

---
## 参考网站
1. [Maven – POM Reference](https://maven.apache.org/pom.html)
2. [Maven – Settings Reference](https://maven.apache.org/settings.html)
3. [POM 文件详解](https://zhuanlan.zhihu.com/p/341619947)
4. [Profile 详解](https://www.cnblogs.com/wxgblogs/p/6696229.html)
---
## 环境变量
```
1. WIN+R 输入 sysdm.cpl → 高级 → 环境变量
2. 在系统变量新建 JAVA_HOME：D:\Java\jdk1.8.0_291
3. 在系统变量新建 M2_HOME：D:\dev\apache-maven-3.8.5
4. 在系统变量找到 Path，增加 %JAVA_HOME%\bin; %M2_HOME%\bin;
5. 在 cmd 输入 mvn -v
```
---
## 常用命令
    mvn -v                                              查看版本
    mvn compile                                         编译
    mvn test                                            测试
    mvn package                                         打包
    mvn clean                                           删除 target
    mvn install                                         安装 jar 包到本地仓库
    mvn deploy                                          上传到私服
---
## 创建 maven 项目的两种方式
    1. archetype:generate                               按照提示进行选择
    2. archetype:generate   -DgroupId=组织名，公司网站的反写+项目名
                            -DartifactId=项目名-模块名
                            -Dversion=版本号
                            -Dpackage=代码所在包名
---              
## [仓库](https://developer.aliyun.com/mvn/guide)
1. 本地仓库：
    - 默认路径：${user.home}/.m2/repository
    - 自定义路径：修改 settings.xml 中的 &lt;localRepository/&gt; 
2. 远程仓库
    - 中央仓库 + 私服仓库 + 其它仓库
    - 通过各种协议如 http:// 和 file:// 访问的仓库
- [settings.xml](settings.xml)
    ```xml
    <mirror>
        <id>aliyunmaven</id>
        <mirrorOf>*</mirrorOf>
        <name>阿里云公共仓库</name>
        <url>https://maven.aliyun.com/repository/public</url>
    </mirror>
    ```
- [pom.xml](pom.xml)
    ```xml
    <repository>
        <id>aliyunmaven</id>
        <url>https://maven.aliyun.com/repository/public</url>
        <releases>
            <enabled>true</enabled>
        </releases>
        <snapshots>
            <enabled>false</enabled>
        </snapshots>
    </repository>
    ```
---
## 生命周期
1. clean 清理项目
    ```
    pre-clean                                           执行清理前的工作
    clean                                               清理上一次构建生成的文件
    post-clean                                          执行清理后的文件
    ```
2. default 构建项目（最核心）：`compile test package install`
3. site         生成项目站点
    ```
    pre-site                                            在生成项目站点前要完成的工作
    site                                                生成项目的站点文档
    post-site                                           在生成项目站点后要完成的工作
    site-deploy                                         发布生成的站点到服务器上
    ```
---
## 依赖冲突
1. 短路优先
2. 先声明优先
---
