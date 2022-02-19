# Koa

---
## 参考网站
1. [koa | Github](https://github.com/koajs/koa)
2. [Koajs 中文文档](https://koa.bootcss.com/)
---
```
url                                     method      param           desc
localhost:9000/api/contactList          get
localhost:9000/api/contact/new/form     post        name, tel       content-type:form-data
localhost:9000/api/contact/new/json     post        name, tel       content-type:application/json
localhost:9000/api/contact/edit         put         name, tel, id   content-type:application/json
localhost:9000/api/contact/edit         patch       name, tel, id   content-type:application/json
localhost:9000/api/contact/{id}         delete      id
localhost:9000/api/longtime             get
```