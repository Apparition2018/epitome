# Maven
---
## 常用命令
```
    mvn -v                                              查看版本
    mvn compile                                         编译
    mvn test                                            测试
    mvn package                                         打包
    mvn clean                                           删除 target
    mvn install                                         安装 jar 包到本地仓库
    mvn deploy                                          上传到私服
```
---
## 创建 maven 项目的两种方式
```
    1. archetype:generate                               按照提示进行选择
    2. archetype:generate   -DgroupId=组织名，公司网站的反写+项目名
                            -DartifactId=项目名-模块名
                            -Dversion=版本号
                            -Dpackage=代码所在包名
```
---              
## 仓库
#### 1. 本地仓库：
1. 默认路径：${user.home}/.m2/repository
2. 自定义路径：修改 settings.xml 中的 <localRepository>xxx</localRepository> 路径 
#### 2. 远程仓库：
1. settings.xml
```
    <mirrors>
        <mirror>
            <id>alimaven</id>
            <name>aliyun maven</name>
            <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
            <mirrorOf>central</mirrorOf>
        </mirror>
    <mirrors>
```
2. pom.xml
```
    <repositories>
        <repository>
            <id>alimaven</id>
            <name>aliyun maven</name>
            <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
        </repository>
    </repositories> 
```
3. 远程仓库地址
```
    https://repo.maven.apache.org/maven2/
    https://search.maven.org/
```
---
## 生命周期
1. clean 清理项目
```
    pre-clean                                           执行清理前的工作
    clean                                               清理上一次构建生成的文件
    post-clean                                          执行清理后的文件
```    
2. default 构建项目（最核心）
```
    compile test package install
```
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