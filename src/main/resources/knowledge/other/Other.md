# 其它

---
## [端口被占用](https://jingyan.baidu.com/article/3c48dd34491d47e10be358b8.html)
1. Win + R，输入 cmd，调出 cmd.exe 窗口
2. netstat -ano
3. netstat -ano|findstr "8080"，根据指定端口字符串查找，记下 PID
4. tasklist|findstr "9776"，根据指定 PID 字符串查找任务，记下任务名
5. taskkill /f /t /im WXWork.exe，杀死任务
---