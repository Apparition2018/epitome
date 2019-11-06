# Maven
---
## 常用命令
```
mvn -v              查看版本
mvn compile         编译
mvn test            测试
mvn package         打包
mvn clean           删除 target
mvn install         安装 jar 包到本地仓库
mvn deploy          上传到私服
```
---
## 创建 maven 项目的两种方式
```
1. archetype:generate                                               按照提示进行选择
2. archetype:generate   -DgroupId=组织名，公司网站的反写+项目名
                        -DartifactId=项目名-模块名
                        -Dversion=版本号
                        -Dpackage=代码所在包名
```
---              
## 仓库
#### 1. 本地仓库：
    修改 settings.xml 中的 <localRepository>xxx</localRepository> 路径
#### 2. 远程仓库：
##### 1. settings.xml 中：
    <mirrors>
        <mirror>
            <id>alimaven</id>
            <name>aliyun maven</name>
            <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
            <mirrorOf>central</mirrorOf>
        </mirror>
    <mirrors>
##### 2. pom.xml 中：
    <repositories>
        <repository>
            <id>alimaven</id>
            <name>aliyun maven</name>
            <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
        </repository>
    </repositories>
---