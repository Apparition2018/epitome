# Middleware

---
## Tomcat
### 中文乱码
1. 打开 D:\dev\apache-tomcat\conf\logging.properties：修改 GBK 为 UTF-8
2. VM options：-Dfile.encoding=UTF-8
### 热部署
1. On 'Update' action: Update classes and resources
2. Debug 启动项目 (修改 Java 文件时可以立刻生效)
---
## 消息队列
1. 使用场景：异步、削峰、解耦
2. 使用缺点：系统复杂性、数据一致性、可用性
>- [消息队列是什么？](https://www.zhihu.com/question/54152397/answer/923992679)
>- [消息队列的优缺点，区别](https://www.jianshu.com/p/eaafb1581e55)
---
