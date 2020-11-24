# Core Java  

---
## 环境变量
1. 新建 MAV_HOME：D:\Java\jdk1.8.0_221
2. Path 增加 %JAVA_HOME%\bin; %JAVA_HOME%\jre\bin;
---
## Java 命令
1. javac：将 Java 源文件编译为 class 字节码文件
2. java：运行 Java 程序
3. jar：将编译后的 class 文件达打成 jar 包
4. javadoc：生成 Java 文档
---
## Java 编译级运行过程
1. 编译：Java 源文件(\*.java) 经过 Java 编译器(javac) 编译成 JVM 能够识别的 Java 二进制字节码文件 (\*.class)
2. 运行
    1.  代码装入：类装载器读取和装载程序所需的类（需要用到该类的时候才加载）
    2.	代码校验：1)class 文件结构检查 2)类型数据的语义检查 3)字节码校验 4)符号引用的校验 
    3.	代码执行：Java 字节码经过 JVM，解析为具体平台的机器码，并运行
---
## JVM, JRE, JDK
1. JVM：Java 虚拟机，是一个虚拟的计算机，通过在实际的计算机上仿真模拟各种计算机的功能来实现的；有完善的硬体架构，如处理器，堆栈，寄存器等，还有相应的指令系统；屏蔽了与具体操纵系统平台相关的信息，使得 Java 程序只需生成在 JVM 上运行的字节码，就可以在多平台上不加修改地运行
2. JRE：Java 运行环境，运行 Java 程序所必须的软件环境，包含 JVM 标准实现和 Java 核心类库
3. JDK：Java 开发工具包，包含 JRE，Java 开发工具和 Java 标准类库
---