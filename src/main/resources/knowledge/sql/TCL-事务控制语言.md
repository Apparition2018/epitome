# 事务控制语言 Transaction Control Language

---
## ACID
原子性 - Atomicity，确保工作单元内的所有操作成功完成; 否则，事务在故障点处中止，并且先前的操作被回滚到它们的原先状态。  
一致性 - Consistency，确保数据库在成功提交的事务后正确更改状态。  
隔离性 - Isolation，事务之间是独立运行互不相关的。  
持久性 - Durability，事务一旦被执行，即使系统故障，其结果依然有效。
---
## SQL Server
>### 开启事务
>```
>BEGIN TRAN
>```
>### 保存事务
>```
>SAVE TRAN sp1
>```
>### 回滚事务 
>```
>ROLLBACK TRAN sp1
>ROLLBACK TRAN
>```
>### 提交事务
>```
>COMMIT TRAN
>```