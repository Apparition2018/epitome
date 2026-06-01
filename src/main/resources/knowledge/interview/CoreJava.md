# Core Java
## Reference
1. [Java SE - Documentation](https://www.oracle.com/java/technologies/javase-documentation.html)
2. [Java Platform Standard Edition 8 Documentation](https://docs.oracle.com/javase/8/docs/)
3. [Java 关键字大全](http://www.mabiji.com/java/javaguanjianzi.html)
---
## 环境变量
1. 新建 JAVA_HOME：D:\Java\jdk1.8.0_221
2. Path 增加 %JAVA_HOME%\bin; %JAVA_HOME%\jre\bin;
3. JDK5 之后，JRE 能自动搜索目录下类文件，并且加载 dt.jar 和 tool.jar，不再需要配置 CLASSPATH
---
## [Java 命令](https://docs.oracle.com/javase/8/docs/technotes/tools/unix/s1-create-build-tools.html)
| 命令      | 说明                            | 例句                                               |
|:--------|:------------------------------|--------------------------------------------------|
| javac   | 读取 Java 类和接口定义，并将它们编译为字节码和类文件 |                                                  |
| java    | 启动 Java 应用程序                  |                                                  |
| jar     | 将多个文件合并为一个 JAR 文件             | jar -cvf name.jar ./a/b/index.jsp ./a/b/list.jsp |
| javadoc | 从 Java 源文件生成 API 文档的 HTML 页面  |                                                  |
---
## Java 编译和运行过程
1. 编译：Java 源文件(*.java) 经过 Java 编译器(javac) 编译成 JVM 能够识别的 Java 二进制字节码文件 (*.class)
2. 运行
    1. 代码装入：类装载器读取和装载程序所需的类（需要用到该类的时候才加载）
    2. 代码校验：1)class 文件结构检查 2)类型数据的语义检查 3)字节码校验 4)符号引用的校验
    3. 代码执行：Java 字节码经过 JVM，解析为具体平台的机器码，并运行
---
## JVM, JRE, JDK
1. JVM：Java 虚拟机，是一个虚拟的计算机，通过在实际的计算机上仿真模拟各种计算机的功能来实现的；有完善的硬体架构，如处理器，堆栈，寄存器等，还有相应的指令系统；屏蔽了与具体操纵系统平台相关的信息，使得
   Java 程序只需生成在 JVM 上运行的字节码，就可以在多平台上不加修改地运行
2. JRE：Java 运行环境，运行 Java 程序所必须的软件环境，包含 JVM 标准实现和 Java 核心类库
3. JDK：Java 开发工具包，包含 JRE，Java 开发工具和 Java 标准类库
---
## 对象 - 类
1. 对象：人们要进行研究的任何事物，它能代表具体的事物，还能表示抽象的规则，计划或事件；对象具有状态，用数据值来描述对象的状态；对象还有操作，用于改变对象的状态，对象及其操作就是对象的行为；对象实现了数据和操作的结合，使数据和操作封装于对象的统一体中
    - 对象是类的一个实例，有状态和行为
2. 类：具有相同特征（数据元素）和行为（功能）的对象的抽象就是类；类的实例是对象，类实际上就是一种数据类型；类具有属性，它是对象的状态的抽象，用数据结构来描述类的属性；类具有操作，它是对象的行为的抽象，用操作名和实现该操作的方法来描述
    - 类是一个模板，它描述一类对象的行为和状态
---
## 面向过程 - 面向对象
1. 面向过程：分析出解决问题的步骤，使用方法将这些步骤一步步实现，使用时在一个个调用一件事"该怎么做"。性能比面向对象高，因为类调用时需要实例化，开销比较大，比较消耗资源。单片机，嵌入式开发，Linux/Unix
   等（性能是最重要因素）
2. 面向对象：把构成问题的事务分解成各个对象，建立对象的目的不是为了完成一个步骤，而是为了描述某个事物在整个解决问题的步骤中的行为一件事"该让谁来做"，然后那个"谁"
   就是对象，他要怎么做是他自己的事，反正最后一群对象合力能把事做好就行了。易维护，易复用，易扩展；因为面向对象有封装，继承，多态的特性，可以设计出低耦合的系统
---
## 面向对象三大特征
1. 封装：隐藏对象的属性和实现细节，仅对外提供公共访问方式
2. 继承：在定义和实现一个类的时候，可以在一个已经存在的类的基础之上来进行，把这个已经存在的类所定义的内容作为自己的内容，并可以加入若干新的内容，或修改原来的方法使之更适合特殊的需要，这就是继承。继承是子类自动共享父类数据和方法的机制，这是类之间的一种关系，提高了软件的可重用性和可扩展性。
3. 多态：
    - 静态绑定：重载（根据参数实现多态），看引用的类型
    - 动态绑定：看对象的类型
        1. 继承
        2. 重写（根据对象实现多态）
        3. 向上转型
---
## JVM 运行时数据区
![JVM 运行时数据区](https://i.postimg.cc/nrCFPKB9/jvm-runtime-data-areas.png)
- ⚪老年代：默认约占堆内存 2/3
    - 存放对象：长期存活对象、大对象（超过阈值直接进入）
    - 进入条件：对象在新生代的 Survivor 区年龄达到阈值 15
    - GC 方式：Major GC / Full GC，频率低、单次耗时长
---
## JVM 加载 class 文件的原理机制
- JVM 中类的装载是由 ClassLoader 和它的子类来实现的，Java ClassLoader 是一个重要的 Java 运行时系统组件。它负责在运行时查找和装入类文件的类。
- 类加载到卸载的生命周期：1)加载(Loading) 2)验证(Verification) 3)准备(Preparation) 4)解析(Resolution) 5)初始化(Initialization) 6)使用(Using) 7)卸载(Unloading)
- 获得 ClassLoader：
    1. 使用当前类：this.getClass().getClassLoader();
    2. 使用当前线程：Thread.currentThread().getContextClassLoader();
    3. 使用系统 ClassLoader：ClassLoader.getSystemClassLoader();
- JVM 加载类：
    ```
        // 1.
        Dog dog = new Dog(); // 如无法找到 Dog，抛出 NoClassDefFoundError
        // 2.
        Class clazz = Class.forName("...Dog"); // 会初始化
        Object dog = clazz.newInstance(); // 如无法找到 Dog，抛出 ClassNotFoundException
        // 3.
        Class clazz = classLoader.loadClass("Dog"); // 不会初始化
        Object dog = clazz.newInstance(); // 如无法找到 Dog，抛出 ClassNotFoundException
    ```
---
## [堆 & 栈](https://www.zhihu.com/question/29833675/answer/82661572)
|      | Heap (堆)              | Stack (栈)              |
|:-----|:----------------------|:-----------------------|
| 线程共享 | 线程共享                  | 线程私有                   |
| 创建时机 | JVM 启动时创建             | 随线程创建而分配               |
| 存储内容 | 对象实例（含对象头、实例变量等）、数组   | 栈帧（局部变量表、操作数栈、方法返回地址等） |
| 数据类型 | 完整的对象数据（包括对象头信息和实例变量） | 局部变量、方法参数、返回值          |
| 内存管理 | GC 自动回收               | 方法自动分配与释放              |
| 溢出错误 | OutOfMemoryError      | StackOverflowError     |
| 空间大小 | 较大，可通过 -Xms / -Xmx 调整 | 较小，可通过 -Xss 调整，有上限     |
---
## 垃圾回收器
- Java 的一个重要特征，JVM 自带的一个线程，在系统认为需要时自动启动一个线程处理
- 作用：用于回收没有任何引用所指向的对象，释放不再被使用的内存
- ⚪GC 通过有向图的进行可达性分析，不可达的对象就被视为是垃圾对象
    - GC Root：JVM 从这些根节点出发，遍历整个对象引用图，不在引用图上的对象即为可回收对象
        - 方法区中 static / final 引用的对象
        - 虚拟机栈中引用的对象
        - 本地方法栈中 JNI 引用的对象
        - synchronized 持有的对象
        - JVM 内部引用：基本类型对相应的 Class 对象、常驻异常（NPE）、系统类加载器
- 触发条件：
    1. GC 在优先级最低的线程中进行，所以当应用忙时，GC 线程就不会被调用
    2. Java 堆内存不足时，GC 会被调用。若 GC 一次后仍不能满足内存分配，JVM 会再进行两次 GC，若仍无法满足要求，则报 "out of memory"
- 减少 GC 开销：
    1. 不要显式调用 System.gc()
    2. 尽量减少临时对象的使用
    3. 对象不用时最好显式置为 null：有利于 GC 收集器判定垃圾，从而提高 GC 的效率
    4. 尽量使用 StringBuffer，而不是 String 累加字符串
    5. 能用基本类型，就不用包装类：基本类型变量占用的内存资源比相应对象占用的少得多
    6. 尽量少用静态对象变量：属于全局变量，不会被 GC 回收
    7. 分散对象创建，删除的时间
---
## 内存泄露 - 内存溢出
1. 内存泄漏：memory leak，无法释放已申请的内存，最终可能会导致内存溢出
    1. 典型表现
        1. 老年代内存持续增长，触发频繁 GC
        2. GC 后老年代内存释放很少或不释放
        3. 应用响应慢，最终可能内存溢出
        4. 堆内存使用率随时间呈线性上升趋势
    2. 排查与解决
        1. 确认内存泄露
            1. 监控 JVM 内存指标：通过工具观察堆内存各区域的使用趋势，判断是否存在内存无法释放的情况
            2. 分析 GC 日志：查看 Full GC 频率、耗时及内存释放情况
        2. 捕获内存快照：在内存泄露复现或接近 OOM 时，抓取堆内存快照（.hprof）
            1. 使用 jmap 生成快照：`jamp -dump:format=b,file=heapdump.hprof <进程ID>`
            2. OOM 自动 dump @see JVM 参数调优
        3. 分析内存快照：通过工具分析快照，定位泄露对象
            1. 识别异常对象：查看哪些对象数量异常多，且生命周期过长
            2. 查看引用链：分析对象被哪些 GC Root 引用，导致无法被回收
        4. 定位代码并修改

        | 场景                | 原因                                  | 解决方案                                               |
        |:------------------|:------------------------------------|:---------------------------------------------------|
        | 静态集合框架不断添加元素      | `static` Map / List 长期持有引用，无法被 GC   | 限制容量、定时清理、改用 `WeakReference`                       |
        | 未关闭资源             | 数据库连接、文件流、网络连接等未释放，句柄泄漏             | `try-with-resources`、`finally` 块中手动关闭              |
        | 缓存无过期策略           | Guava Cache / Caffeine 未设置过期时间，无限增长 | 配置 `expireAfterWrite` / `expireAfterAccess` / 最大容量 |
        | 线程池核心线程持有大对象引用    | 核心线程不会销毁，长期持有对象引用，导致无法 GC           | 及时释放引用、缩小核心线程数、使用 `WeakReference`                  |
        | 内部类持有外部类引用        | 非静态内部类隐式持有外部类 `this` 引用             | 改为**静态内部类**，必要时使用 `WeakReference`                  |
        | `ThreadLocal` 未清理 | 线程池场景下线程复用，未 `remove()` 导致旧数据残留     | `finally { threadLocal.remove(); }`                |
        | 监听器未移除            | 事件监听器长期持有对象引用，造成隐性引用链               | 页面销毁 / 对象注销时执行 `removeListener`                    |
    3. 常用工具
        1. JDK 自带工具
            - jps：查看 Java 进程 ID
            1. jstat：实时监控 JVM 内存和 GC 状态，`jstat -gcutil <进程ID> 1000 10`，每1秒输出1次，共10次
            2. jmap：生成堆快照、查看对象分布，`jmap -histo <进程ID>`，查看对象数量和大小
            3. jconsole/jvisualvm：图形化工具，监控内存、线程，支持生成快照和分析
        2. 第三方分析工具
            1. MAT (Eclipse Memory Analyzer)：堆快照分析工具，可自动检测泄露疑点(Leak Suspects)，展示支配树和引用链
            2. VisualVM 插件：如 Visual GC 插件，直观展示 GC 区域变化趋势
            3. YourKit/JProfiler：商业工具，提供内存泄露检测、CPU 分析等功能，支持实时跟踪创建和销毁
    4. 预防措施
        1. 代码评审：终点检查长生命周期对象对短生命周期对象的引用
        2. 压力测试：通过压测提前暴露内存泄露问题
2. 内存溢出：out of memory，申请不到足够的内存
3. 🔺JVM 参数调优
```
# 堆
-Xms4g                      初始堆大小           建议和 Xmx 一样，避免动态扩容
-Xmx4g                      最大堆大小

# GC 策略
-XX:+UseG1GC                Parallel GC-吞吐量优先；GC-平衡停顿和吞吐；ZGC-停顿
-XX:MaxGCPauseMillis=200    G1 目标停顿时间

# GC 日志 -Xlog:gc*:file=<路径>:<输出格式>，gc* 表示捕获所有 GC 相关日志
-Xlog:gc*:file=/app/logs/gc.log:time,uptime,level,tags
-XX:+PrintGCDetails
-XX:+PrintGCTimeStamps

## OOM 自动 dump
-XX:+HeapDumpOnOutOfMemoryError
-XX:HeapDumpPath=/app/logs/heapdump.hprof
```
---
