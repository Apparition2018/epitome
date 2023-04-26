# Gradle
<img alt="主流构建工具" src="https://img.mukewang.com/6065f52d0001055212800720.jpg" width="500">

---
## 参考网站
1. [Gradle User Manual](https://docs.gradle.org/current/userguide/userguide.html)
2. [新一代构建工具gradle-慕课网](https://www.imooc.com/learn/833)
3. [配置镜像](https://www.cnblogs.com/junejs/p/12686834.html)
4. [解决每次打开项目都要下载 gradle-xx-bin.zip](https://www.cnblogs.com/passedbylove/p/11232278.html)
5. [配置 daemon 提高程序的编译速度](https://blog.csdn.net/changsimeng/article/details/62421202)
---
## Groovy
- 用于 Java 虚拟机的一种敏捷的动态语言
- 即可以用于面向对象编程，亦可用作纯粹的脚本语言
- 不必编写过多的代码，同时又具有闭包和动态语言中的其他特性
### 与 Java 对比
- Groovy 完全兼容 Java 的语法
- 分号是可选的
- 类、方法默认是 public
- 编译器给属性自动添加 getter/setter 方法
- 属性可以直接用.获取
- 最后一个表达式的值会被作为返回值
- == 等同于 equals()，不会有 NullPointerExceptions
### 高效的 Groovy 特性
1. assert 语句
2. 可选类型定义
3. 可选的括号
4. 字符串：' ', " ", ''' '''
5. 集合 API
6. 闭包
---
## Tasks
1. jar
    ```
    java -classpath build/libs/gradle-1.0-SNAPSHOT.jar com.ljh.gradle.todo.App
    ```
2. war
    ```
    1. 把 gradle-1.0-SNAPSHOT.war 复制到 %TOMCAT_HOME%\webapps
    2. %TOMCAT_HOME%\bin\startup.bat 启动 Tomcat
    3. http://localhost:8080/gradle-1.0-SNAPSHOT/index.html
    ```
---
## 构建脚本
<img alt="项目-任务关系" src="https://img3.mukewang.com/606612910001be5b13660768.jpg" width="500">

- Gradle 构件中两个基本概念是项目(project)和任务(task)
- 每个构建至少包含一个项目，项目中包含一个或多个任务
- 多项目构建中，一个项目可以以来于其他项目；类似的，任务可以形成一个依赖关系图来确保他们的执行顺序
### project
- 一个项目代表一个正在构建的组件(比如一个jar文件)，当构建启动后，Gradle 会基于 build.gradle 实例化一个 org.gradle.api.Project 类，并且能够通过 project 变量使其隐式可用
1. group, name, version
2. apply, dependencies, repositories, task
3. 属性的其他配置方式：ext, gradle.properties
### task
- 任务对应 org.gradle.api.Task。主要包括任务动作和任务依赖。任务动作定义了一个最小的工作单元。可以定义依赖于其他任务、动作序列和执行条件。
- dependsOn
- doFirst, doLast, <<
---
## 构建生命周期
<img alt="构建生命周期1" src="https://img.mukewang.com/6066172100016f7d13660768.jpg" width="500">
<img alt="构建生命周期2" src="https://img.mukewang.com/6066179a00017f5213660768.jpg" width="500">

---
## 依赖管理
[gradle 新的依赖方式](https://blog.csdn.net/wangliblog/article/details/81366095)
### 自动化依赖管理
<img alt="自动化依赖管理" src="https://img1.mukewang.com/60661a6c0001769113660768.jpg" width="500">

### 依赖阶段关系
<img alt="依赖阶段关系" src="https://img2.mukewang.com/60661b0e0001cc1b13660768.jpg" width="500">

### 解决冲突
1. 默认选用相对高的版本
    - 修改默认解决策略
        ```
        configurations.all{
            resolutionStrategy {
                failOnVersionConflict()
            }
        }
        ```
2. 排除传递性依赖
    ```
    dependencies {
        implementation('org.hibernate:hibernate-core:3.6.3.Final') {
            exclude group: 'org.slf4j', module: 'slf4j-api'
        }
    }
    ```
3. 强制一个版本
    ```
    configurations.all{
        resolutionStrategy {
            force 'org.slf4j:slf4j-api:1.7.24'
        }
    }
    ```
---
## 多项目构建
- 项目模块化  
<img alt="模块依赖关系" src="https://img4.mukewang.com/606696430001dfd213660768.jpg" width="500"/>
- 配置要求
    - 所有项目应用 Java 插件
    - web 子项目打包成 war
    - 所有项目添加 logback
    - 统一配置公共属性
- 项目范围  
<img alt="项目范围" src="https://img2.mukewang.com/606bc4dc00015a8f13660768.jpg" width="500"/>

---
## 测试
- 测试发现
    - 任何继承 junit.framework.TestCase 或 groovy.unit.GroovyTestCase 的类
    - 任何被 @RunWith 注解的类
    - 任何至少包含一个 @Test 注解的类
- 测试报告：build/reports
---
## 发布
<img alt="发布" src="https://img3.mukewang.com/606bfa310001854f13660768.jpg" width="500"/>

---
