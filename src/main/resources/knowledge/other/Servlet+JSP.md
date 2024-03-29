# Servlet + [JSP](https://www.runoob.com/jsp/jsp-tutorial.html)

---
## 网络模型
![网络模型](https://mmbiz.qpic.cn/mmbiz_png/GBXuAtjkoVG3F1Bz4mlQxF82KQNrYNNiav1HXicXtVrH60iarUuibWeT4fHN5CHhMHejs2yponrEw1WgqzTUypn2uw/640)
### OSI 七层模型
- OSI（Open System Interconnection，开放系统互连）七层网络模型称为开放式系统互联参考模型 ，是一个逻辑上的定义，一个规范，它把网络从逻辑上分为了7层。
- 主要目的：解决异种网络互连时所遇到的兼容性问题
- 主要功能：帮助不同类型的主机实现数据传输
- 最大优点：将服务、接口和协议这三个概念明确地区分开来，通过七个层次化的结构模型使不同的系统不同的网络之间实现可靠的通讯

| 层级    | 功能                                 | 硬件&协议              |
|:------|:-----------------------------------|:-------------------|
| 物理层   | 在终端设备间传送比特流，定义了电压、借口、电缆标准和传输距离     | 中继器、集线器、双绞线、光纤、电缆  |
| 数据链路层 | 将源自网络层来的数据可靠地传输到相邻节点的目标机网络层        | 网桥、以太网交换机、网卡（半物理层） |
| 网络层   | 将网络地址翻译成对应的物理地址，并决定如何将数据从发送方路由到接收方 | 路由器、三层交换机 IP       |
| 传输层   |                                    | TCP UDP            |
| 会话层   |                                    |                    |
| 表示层   |                                    |                    |
| 应用层   |                                    | HTTP HTTPS FTP     |
- 把 IP 想象成一种高速公路，它允许其它协议在上面行驶并找到其它电脑的出口。TCP 和 UD P是高速公路上的"卡车"，它们携带的货物就是 HTTP, FTP 等包装出来的数据包
### TCP/IP 四层模型
- 更加侧重的是互联网通信核心（也是就是围绕 TCP/IP 协议展开的一系列通信协议）的分层，因此它不包括物理层，以及其他一些不想干的协议
|层级|数据名称|
|:---|:---|
|传输层|段 Segments|
|网络层|包 Packages|
|数据链路层|帧 Frames|
|物理层|比特流 Bits|
### 五层模型
- 为了方便学习计算机网络原理而采用的，综合了 OSI 七层模型和 TCP/IP 的四层模型而得到的五层模型
---
## HTTP 协议
1. [HTTP是一个无状态的协议。这句话里的无状态是什么意思？](https://www.zhihu.com/question/23202402/answer/527748675)
## HTTP 数据包
### Request Headers
```
POST /demo/post2 HTTP/1.1                   // 请求行
Host: localhost:3333
Connection: keep-alive
Content-Length: 28
Accept: */*
Sec-Fetch-Dest: empty
User-Agent: Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.87 Safari/537.36
Content-Type: application/x-www-form-urlencoded; charset=UTF-8
Origin: null
Sec-Fetch-Site: cross-site
Sec-Fetch-Mode: cors
Accept-Encoding: gzip, deflate, br
Accept-Language: zh-CN,zh;q=0.9,en;q=0.8,ja;q=0.7
```
### Response Headers
```
HTTP/1.1 200                                // 状态行
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers
Access-Control-Allow-Origin: null
Access-Control-Allow-Credentials: true
Content-Type: application/json
Transfer-Encoding: chunked
Date: Tue, 24 Nov 2020 15:40:10 GMT
Keep-Alive: timeout=60
Connection: keep-alive
```
## HTTP 状态码
| 状态码 | 英文名称                   | 描述                      |
|:----|:-----------------------|:------------------------|
| 200 | OK                     | 请求成功                    |
| 301 | Moved Permanently      | 请求的页面已被移动到新的 URL        |
| 302 | Found                  | 请求的页面暂时移动到新的 URL        |
| 304 | Not Modified           | 请求的页面未修改，服务器告诉客户端读取缓存   |
| 400 | Bad Request            | 服务器无法理解的请求              |
| 401 | Unauthorized           | 请求的页面需要用户名和密码           |
| 403 | Forbidden              | 服务器理解请求客户端的请求，但是拒绝执行此请求 |
| 404 | Not Found              | 服务器找不到请求的页面             |
| 405 | Method Not Allowed     | 客户端请求中的方法被禁止            |
| 415 | Unsupported Media Type | 服务器无法处理请求附带的媒体格式        |
| 405 | Method Not Allowed     | 请求中的方法是被禁止              |
| 500 | Internal Server Error  | 服务器内部错误，无法完成请求          |
---
## Servlet - JSP
| Servlet                                                                     | JSP                                                                                           |
|:----------------------------------------------------------------------------|:----------------------------------------------------------------------------------------------|
| sun 公司制定的一种用来扩展 Web 服务器功能的组件规范。早起的 Web 服务器只能处理静态资源请求，无法根据请求计算后生成相应的 HTML 内容 | sun 公司制订的一种服务器端的动态页面技术规范，是 Servlet 技术的发展，本质上是 Servlet 的简易方式，更强调应用的外表表达。JSP 编译后转换成一个 Servlet 类 |
| 应用逻辑在 Java 文件中，并且完全从表示层中的 HTML 里分离开来                                        | Java 和 HTML 可以组合成一个扩展名为 .jsp 的文件                                                              |
| 主要用于控制逻辑                                                                    | 侧重于视图                                                                                         |
---
## Servlet 运行步骤
1. 浏览器依据 ip，port 建立与 web 服务器（Servlet容器）之间的连接
2. 浏览器将相关数据打包（按 http 协议创建一个请求数据报），发送给 web 服务器
3. web 服务器拆包（按 http 协议将请求数据报中的相关数据解析出来），将解析到的数据添加到 request 对象里，同时，创建一个 response 对象
4. web 服务器依据路径找到 Servlet 对象，调用 service 处理请求，处理结果写到 response 对象里
5. web 服务器将 response 对象中的数据取出来，打包（按 http 协议创建数据包），发送给浏览器
6. 浏览器拆包（按 http 协议将数据包中的数据解析出来），生成相应的页面
---
## 重定向 - 转发
| 重定向                                                      | 转发                                                  |
|:---------------------------------------------------------|:----------------------------------------------------|
| 服务器向浏览器发送一个302状态码和一个 Location 消息头（地址），浏览器收到后立即向重定向地址发出请求 | 一个 web 组件（Servlet/JSP）将未完成的处理通过容器转交给另外一个 web 组件继续完成 |
| response.sendRedirect("xxx.jsp"); <br/>                  | response.sendRedirect("xxx.do");                    |1. 绑定数据到 request 对象：<br/> request.setAttribute(String name, Object o); <br/> 2. 获得转发器: <br/> request.getRequestDispatcher("xxx.jsp"); <br/> 3. 转发：<br/> rd.forward(request, response);|
| 重定向地址栏的地址发生改变                                            | 转发后地址栏地址不变                                          |
| 重定向地址可以是任意的                                              | 转发的地址必须是同一个应用内部的某个地址                                |
| 重定向所涉及到的 Web 组件不共享 request 和 response                    | 转发所涉及到的 Web 组件共享同 request 和 response                |
---
## [Servlet 生命周期](https://www.runoob.com/servlet/servlet-life-cycle.html)
| 时期   | 说明                                                                                                 |
|:-----|:---------------------------------------------------------------------------------------------------|
| 实例化  | 容器调用 Servlet 的构造器，创建一个 Servlet 对象 <br/> 1)默认：容器收到请求之后，开始创建 <br/> 2)配置<load-on-start-up>：容器启动之后立即创建 |
| 初始化  | 容器在创建好 Servlet 之后，立即调用 init 方法，只执行一次                                                               |
| 处理请求 | 容器收到请求之后调用 Servlet 对象的 service()，根据请求的不同调用不同的方法                                                    |
| 销毁   | 容器依据自身算法删除 Servlet 对象，删除前调用 destroy 方法                                                             |
---
## Servlet 线程安全问题
- 线程不同步：Servlet 工作机制是单实例提供多线程的服务，它是通过一个调度线程来维护多个工作线程的有序执行。但由于多个线程共享一份实例，在对公共资源进行访问时一定会出现线程不同步的问题
- 解决办法:
    1. 避免使用全局变量或者静态变量，使用局部变量，局部变量在每个线程中都有各自的实例
    2. 采用同步锁来保证安全
---
## Cookie - Session
- 状态管理：HTTP 是无状态的，无状态是指协议对于事务处理没有记忆能力。将浏览器与 web 服务器之间多次交互当做一个整体来看待（即为了完成某个业务，需要多次交互，比如购物），并且将多次交互所涉及的数据（即状态）保存下来

| Cookie                                                                    | Session                                                        |
|:--------------------------------------------------------------------------|:---------------------------------------------------------------|
| 服务器临时存放在浏览器端的少量的数据，当浏览器再次访问服务器时，会将这些数据发送给服务器                              | 服务器端为了保存状态而创建的一个特殊对象，服务器会将这个对象唯一的 sessionId 以 cookie 的形式发送给浏览器 |
| new Cookie(String name, String value)                                     | request.getSession()                                           |
| cookie.setMaxAge(0)                                                       | session.invalidate()                                           |
| 将状态保存在浏览器，安全性低，可以被改写                                                      | 将状态保存在服务器的内存中，安全性高；也可以持久化到 file，数据库，memcache，redis 等           |
| 只能保存4kb左右的数据，且个数在20个左右                                                    | 能保存更多的数据                                                       |
| 只能保存 ASCII 字符串 <br/> new Cookie("city", URLEncoder.encode("北京", "UTF-8"); | 能存放对象，可设置生命周期，但当存放对象过多时，会影响服务器的性能                              |
| 可被用户禁止，如 Cookie 被完全禁用，Session 也将失效                                        | 当用户第一次请求生成 Session 对象时会生成 SessionID，SessionID 保存在 Cookie 中     |
---
## Scriptlet
| <% %>   | <%= %>                   | <%! %>              | \<!-- -->   | <%-- --%>    |
|:--------|:-------------------------|:--------------------|-------------|--------------|
| JSP 小脚本 | JSP 表达式                  | JSP 声明              | JSP 注释      | 隐藏注释         |
| -       | 变量、变量加运算符组合的表达式、有返回值的方法  | 成员属性或成员方法的声明        | 可带有 JSP 表达式 | 不会被 JSP 引擎处理 |
| 原封不动    | service() 中的 out.print() | Servlet 类中成员属性或成员方法 |             |              |
---
## JSP → Servlet
|                                  |                                                                        |
|:---------------------------------|:-----------------------------------------------------------------------|
| 容器将 .jsp 文件转换为 .java 文件(servlet) | JVM 首先会获取 pageEncoding 的值，如果该值存在则采用它设定的编码来编译，否则则采用 file.encoding 编码来编译 |
| JVM 将 .java 文件转换为 .class 文件      | 与任何编码的设置都没有关系，.java 文件就转换成了统一的 Unicode 格式的 .class 文件                   |
| 输出到浏览器                           | 如果设置了 charset 则浏览器就会使用指定的编码格式进行解码，否则采用默认的 ISO-8859-1                   | 
---
## [JSP 隐式对象](https://www.runoob.com/jsp/jsp-implicit-objects.html)
- 容器会依次从 pageContext → request → session → application 中查找，调用"getXXX"方法输出

| 隐含对象        | 类型                  | 说明                              |
|:------------|:--------------------|:--------------------------------|
| request     | HttpServletRequest  | 请求信息，在一 JSP 网页发出请求到另一个JSP网页之间有效 |
| response    | HttpServletResponse | 响应信息                            |
| out         | JSPWriter           | 输出的数据流                          |
| pageContext | PageContext         | JSP 页面上下文，适用于当前页面范围             |
| session     | HttpSession         | 会话，用户持续和服务器所连接的时间内有效            |
| application | ServletContext      | 全局的上下文对象，从服务器开始执行服务到服务器关闭为止     |
| page        | Object              | JSP 页面本身                        |
| config      | ServletConfig       | Servlet 配置对象                    |
| exception   | Throwable           | 捕获网页异常                          |
--- 
## [JSP 指令](https://www.runoob.com/jsp/jsp-directives.html)
<table>
    <tr>
        <td rowspan="14">page</td>
        <td>contentType="text/html;charset=utf-8"</td>
        <td>指定服务器发送给客户端的文本类型和编码格式</td>
    </tr>
    <tr>
        <td>pageEncoding="utf-8"</td>
        <td>指定当前 JSP 页面的字符编码</td>
    </tr>
    <tr>
        <td>import="java.util.Date"</td>
        <td>导入要使用的 Java 类</td>
    </tr>
    <tr>
        <td>language="java"</td>
        <td>定义 JSP 页面锁用的脚本语言，目前只支持 Java，默认也是 Java</td>
    </tr>
    <tr>
        <td>errorPage="error.jsp"</td>
        <td>指定当 JSP 页面发生异常时需要转向的错误处理页面</td>
    </tr>
    <tr>
        <td>isErrorPage="true"</td>
        <td>指定当前页面是否可以作为另一个 JSP 页面的错误处理页面</td>
    </tr>
    <tr>
        <td>buffer="10kb"</td>
        <td>指定 out 对象使用缓冲区的大小，默认8kb</td>
    </tr>
    <tr>
        <td>autoFlush="true"</td>
        <td>控制 out 对象的缓存区</td>
    </tr>
    <tr>
        <td>extends</td>
        <td>指定 servlet 从哪一个类继承，一般不需要设置</td>
    </tr>
    <tr>
        <td>info="测试页面"</td>
        <td>定义 JSP 页面的描述信息，可通过 servlet.getServletInfo() 获得</td>
    </tr>
    <tr>
        <td>isThreadSafe="true"</td>
        <td>指定对 JSP 页面的访问是否为线程安全。设置为 false，则以单线程运行</td>
    </tr>
    <tr>
        <td>session="true"</td>
        <td>指定 JSP 页面是否使用 session</td>
    </tr>
    <tr>
        <td>isELIgnored="ture"</td>
        <td>指定是否执行 EL 表达式。设置为 true，则忽略"${}的计算"</td>
    </tr>
    <tr>
        <td>isScriptingEnabled="true"</td>
        <td>默认值为 true，它表示启用脚本，表达式和声明</td>
    </tr>
    <tr>
        <td>include</td>
        <td>file="header.jsp"</td>
        <td>用来包含其他外部文件（JSP, HTML），用于导航栏，版权声明，logo 等</td>
    </tr>
    <tr>
        <td>taglib</td>
        <td colspan="2">引入一个自定义标签集合的定义，包括库路径、自定义标签 <br/> <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %></td>
    </tr>
</table>

---
## [EL 表达式](https://www.runoob.com/jsp/jsp-expression-language.html)
- 一套简单的运算规则，用于给 JSP 标签的属性赋值，也可以直接用来输出
- 作用：
    1. 访问 Bean 属性：如果没有赋值或名字写错，输出""
        - ${sessionScope.user.name};
        - ${user['name']};
        - ${user.interest[0]}
    2. 进行简单计算：
        - 算术运算：${"1"+"2"}; // 3，+ 只能求和，不能连接字符串
        - 关系运算：${1>2}; ${str=="abc"}
        - 逻辑运算：${1>0 && 2<3}
        - empty 运算：${empty 引用} 判断是否为空字符串，空集合，null，找不到对应值
    3. 获取请求参数：
        - ${param.name}; 相当于 request.getParameter("name");
        - ${paramValues.name}; 相当于 request.getParameterValues("name");
---
## [JSTL](https://www.runoob.com/jsp/jsp-jstl.html)
- JSP Standard Tag Library
- pom.xml 引包
```xml
<dependency>
    <groupId>org.glassfish.web</groupId>
    <artifactId>jakarta.servlet.jsp.jstl</artifactId>
</dependency>
```
- [spring-servlet.xml](../../spring/spring-servlet.xml) 配置视图解析器
```xml  
<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
    <property name="viewClass" value="org.springframework.web.servlet.view.JstlView"/>
</bean>
```
### [JSTL 标签](https://jakarta.ee/specifications/tags/1.2/tagdocs/)
- [c 标签](../../../../../web/WEB-INF/jsp/jstl/core.jsp)
    ```html
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <!-- value      必要，输出的内容
         default    可选，默认值 -->
    <c:out value="${x}" deafult="100"/>
     
    <!-- var        可选，存储值的变量
         value      可选，变量存储的值 -->
    <c:set value="1" var="x"/>
    
    <!-- test       必要，条件
         var        可选，存储条件结果的变量 -->
    <c:if test="${x > 1}">…</c:if>

    <c:choose>
        <!-- 可以出现多次 -->
        <c:when test="${x > 1}">…</c:when>
        <!-- 可以出现0次或1次 -->
        <c:otherwise>…</c:otherwise>
    </c:choose>
    
    <!-- items      可选，要被迭代的集合
         var        可选，当前迭代条目的变量名称
         varStatus  可选，当前迭代状态的变量名称 -->
    <c:forEach items="${list}" var="i" varStatus="s">
        <span>i</span> - <span>s.index</span> - <span>s.count</span>
    </c:forEach>
    <!-- begin      可选，开始的元素，默认值0
         end        可选，最后的元素，默认最后一个元素
         step       可选，每次迭代的步长  -->
    <:forEach var="i" begin="1" end="9" step="2">${i}</:forEach>

    <!-- 具有 <c:forEach> 相似的属性，此外还有
         delims     必要，分隔符 -->    
    <:forTokens items="1,2,3,4,5" var="i" step="2">${i}</:forTokens>
    ```
- [fmt 标签](../../../../../web/WEB-INF/jsp/jstl/fmt.jsp)
    ```html
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

    <!-- value      必要，要显示的日期
         type       可选，date | time | both，默认 date
         dateStyle  可选，full | long | medium | short | default
         timeStyle  可选，full | long | medium | short | default
         pattern    可选，自定义格式模式
         timeZone   可选，日期时区
         var        可选，存储格式化日期的变量名 -->
    <fmt:formatDate value="<%= new java.util.Date()%>" pattern="yyyy-MM-dd hh:mm:ss"/>
    <fmt:parseDate value="2023-01-01" pattern="yyyy-MM-dd"/>
    ```
### [JSTL 函数](../../../../../web/WEB-INF/jsp/jstl/fn.jsp)

---
