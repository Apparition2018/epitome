# Jenkins
- 开源
- 常用于自动化测试、持续集成
---
## 参考网站
1. [用Jenkins自动化搭建测试环境-慕课网](https://www.imooc.com/learn/1008)
2. [Jenkins](https://www.jenkins.io)
---
## 安装
1. https://www.jenkins.io/download/
2. 下载 Generic Java package (.war)
3. java -jar jenkins.war
4. localhost:8080
5. 安装推荐的插件
6. 创建管理员账号 (admin)
---
## 安装插件
1. Dashboard → Manage Jenkins → System Configuration → Manage Plugins
2. Available
3. 搜索 Rebuilder 并勾选
4. 搜索 Safe Restart 并勾选
5. Install without restart
---
## Jenkins 基础配置
1. 配置全局安全属性
    - Dashboard → Manage Jenkins → Security → Configure Global Security
    - 搜全策略 → 安全矩阵 → Add user or group... → admin (管理员) → 在右边 Grant all permissions → save
2. 添加用户
    - Dashboard → Manage Jenkins → Security → Manage Users
    - 新建用户 (tester01)
    - Dashboard → Manage Jenkins → Security → Configure Global Security
    - 搜全策略 → 安全矩阵 → Add user or group... → tester01 → 在右边 Grant all permissions → 取消勾选 Administer - save
---