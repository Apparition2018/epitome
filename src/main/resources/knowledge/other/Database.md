# Database

---
## 分库分表
- [分库分表](https://mp.weixin.qq.com/s/lULiWhy2UK7QzD_gqyOvAA)
- [分库分表](https://mp.weixin.qq.com/s/fKDOm5-yKlkgLw5HxewJsw)
1. 分库：解决并发量大的问题，通过增加数据库实例的方式来提供更多的可用数据库链接
    - 场景：微服务拆分时，按照业务边界，分表把订单、物流、商品、会员等数据单独放到独立的数据库中
2. 分表：解决数据量大的问题，一般单表行数超过500万行或容量超过2GB才考虑做分表
3. 横向拆分：把一张表中的记录拆分到不同的表中
4. 纵向拆分
   1. 把一张表中的字段拆分到不同的表中
   2. 把一个数据库按业务边界拆分成多个数据库
5. 分表字段选择：交易订单一般选择 买家ID 进行分表
---