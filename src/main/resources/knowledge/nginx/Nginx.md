# Nginx

---
## Reference
1. [nginx documentation](http://nginx.org/en/docs/)
    - [Admin Guide](https://docs.nginx.com/nginx/admin-guide/)
2. [Nginx教程™](https://www.yiibai.com/nginx)
3. [Nginx-慕课网](https://www.imooc.com/video/23825)
---
## 常见服务器
| 服务器            | 说明                |
|----------------|-------------------|
| MS IIS         | asp.net           |
| Weblogic、Jboss | 传统行业：ERP/物流/电信/金融 |
| Tomcat、Jetty   | J2EE              |
| Apache、Nginx   | 静态服务、反向代理         |
| Netty          | 高性能服务器编程          |
---
## 正向代理 vs 反向代理
| 正向代理                        | 反向代理                        |
|-----------------------------|-----------------------------|
| 为在防火墙内的局域网提供访问 Internet 的途径 | 将防火墙后面的服务器提供给 Internet 用户访问 |
| 代理客户端                       | 代理服务器                       |
| 客户端架构                       | 服务器架构                       |
| 服务器不知道真正的用户                 | 用户不知道真正的服务器                 |
| 解决访问问题                      | 解决负载均衡、安全防护                 |
---
## [指令 (directives)](http://nginx.org/en/docs/ngx_core_module.html#directives)
1. 简单指令 (simple directives)：`<名称> <空格分隔的参数>;`
2. 块指令 (block directives)：`<名称> <空格分隔的参数> { … }`;
    - [events](http://nginx.org/en/docs/ngx_core_module.html#events)
    - [http](http://nginx.org/en/docs/http/ngx_http_core_module.html#http)
        - [server](http://nginx.org/en/docs/http/ngx_http_core_module.html#server)
            - [location](http://nginx.org/en/docs/http/ngx_http_core_module.html#location)
---
## [模块](http://nginx.org/en/docs/#development)
| 模块                       | 指令                       |
|--------------------------|--------------------------|
| Core functionality       | events, worker_processes |
| ngx_http_core_module     | http, server, location   |
| ngx_http_log_module      | access_log, log_format   |
| ngx_http_upstream_module | upstream                 |
| ngx_http_charset_module  | charset                  |
| ngx_http_index_module    | index                    |
| ngx_http_proxy_module    | proxy_pass               |
| ngx_http_access_module   | allow, deny              |
| ngx_http_headers_module  | expires                  |
---
## [.conf](nginx_0.conf)
1. [反向代理](nginx_1.conf)
    - [负载均衡](nginx_2.conf)
2. [提供静态内容](nginx_3.conf)
    - [动静分离](nginx_1.conf)
3. [正向代理](https://www.yiibai.com/nginx/nginx-main-use-scenes.html#h2-5-)
---
## [Nginx 集群](https://www.imooc.com/video/23861)
![Nginx 集群](https://img.mukewang.com/64a7cdca0001214919201080-500-284.jpg)

---