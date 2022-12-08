# OAuth2

---
## Reference
1. [RFC 6749: The OAuth 2.0 Authorization Framework](https://www.rfc-editor.org/rfc/rfc6749)
2. [OAuth 2.0 的四种方式 - 阮一峰的网络日志](https://www.ruanyifeng.com/blog/2019/04/oauth-grant-types.html)
---
## Roles
1. resource owner
2. resource server
3. client
4. authorization server
---
## Protocol Flow
```
+-----------+                               +---------------+
|           |--(A)- Authorization Request ->|   Resource    |
|           |<-(B)-- Authorization Grant ---|     Owner     |
|           |                               +---------------+
|           |                               +---------------+
|  Client   |--(C)-- Authorization Grant -->| Authorization |
|           |<-(D)----- Access Token -------|    Server     |
|           |                               +---------------+
|           |                               +---------------+
|           |--(E)----- Access Token ------>|   Resource    |
|           |<-(F)--- Protected Resource ---|    Server     |
+-----------+                               +---------------+
```
---
## Obtaining Authorization
1. Authorization Code Grant
```
+-----------+
| Resource  |
|   Owner   |
+-----------+
      |
     (B)
+-----|-----+        Client Identifier      +---------------+
|           |--(A)-- & Redirection URI ----→| Authorization |
|User-Agent |--(B)-- User Authenticiates --→|    Server     |
|           |--(C)-- Authorization Code ----|               |
+-----------+                               +---------------+
   |     |                                      ↑       |
  (A)   (C)                                     |       |
   |     ↓                                      |       |
+-----------+                                   |       |
|           |--(D)-- Authorization Code --------‘       |
|  Client   |        & Redirection URI                  |
|           |←-(E)---- Access Token --------------------’
+-----------+   (w/ Optional Refresh Token)
```
2. Implicit Grant
```
+-----------+
| Resource  |
|   Owner   |
+-----------+
      |
     (B)
+-----|-----+        Client Identifier      +---------------+
|           |--(A)-- & Redirection URI ----→| Authorization |
|           |--(B)-- User Authenticiates --→|    Server     |
|           |<-(C)---- Redirection URI -----|               |
|User-Agent | with Access Token in Fragment +---------------+
|           |                               +---------------+
|           |--(D)---- Redirection URI ----→|  Web-Hosted   |
|           |         without Fragment      |    Client     |
|       (F) |←-(E)--------- Script ---------|   Resource    |
+-----------+                               +---------------+
   |     |
  (A)   (G) Access Token
   |     ↓
+-----------+
|  Client   |
+-----------+
```
3. Resource Owner Password Credentials Grant
```
+-----------+
| Resource  |
|   Owner   |
+-----------+
      |
     (A) Resource Owner Password Credentials
+-----↓-----+                               +---------------+
|           |--(B)--- Resource Owner ------→| Authorization |
|  Client   |       Password Credentials    |    Server     |
|           |←-(C)---- Access Token --------|               |
+-----------+   (w/ Optional Refresh Token) +---------------+
```
4. Client Credentials Grant
```
+-----------+                                   +---------------+
|  Client   |--(A)-- Client Authentication ----→| Authorization |
|           |←-(B)------- Acess Token ----------|    Server     |
+-----------+                                   +---------------+
```
---
