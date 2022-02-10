# Design Pattern

## 参考网站
1. [Design Patterns | refactoring.guru](https://refactoringguru.cn/design-patterns)
2. [Design Patterns | sourcemaking](https://sourcemaking.com/design_patterns)
3. [Java Design Patterns](https://java-design-patterns.com/patterns/)
4. [Java 设计模式](http://c.biancheng.net/design_pattern/)
5. [设计模式 | 刘伟](https://www.bookstack.cn/read/design-pattern-java/README.md)
6. [设计模式之美](https://pan.baidu.com/disk/home?#/all?vmode=list&path=%2F%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F%E4%B9%8B%E7%BE%8E)
---
## 分类
<table>
    <tr>
        <td>类型</td>
        <td>模式</td>
        <td>范围</td>
        <td>流行度</td>
        <td>类型</td>
        <td>模式</td>
        <td>范围</td>
        <td>流行度</td>
    </tr>
    <tr>
        <td rowspan="5">创建</td>
        <td>抽象工厂</td>
        <td>类</td>
        <td>3</td>
        <td rowspan="11">行为</td>
        <td>命令</td>
        <td>对象</td>
        <td>3</td>
    </tr>
    <tr>
        <td>工厂方法</td>
        <td>类</td>
        <td>3</td>
        <td>迭代器</td>
        <td>对象</td>
        <td>3</td>
    </tr>
    <tr>
        <td>建造者</td>
        <td>对象</td>
        <td>3</td>
        <td>中介者</td>
        <td>对象</td>
        <td>2</td>
    </tr>
    <tr>
        <td>原型</td>
        <td>对象</td>
        <td>2</td>
        <td>备忘录</td>
        <td>对象</td>
        <td>1</td>
    </tr>
    <tr>
        <td>单例</td>
        <td>对象</td>
        <td>2</td>
        <td>责任链</td>
        <td>对象</td>
        <td>1</td>
    </tr>
    <tr>
        <td rowspan="7">结构</td>
        <td>适配器</td>
        <td>类/对象</td>
        <td>3</td>
        <td>观察者</td>
        <td>对象</td>
        <td>3</td>
    </tr>
    <tr>
        <td>桥接</td>
        <td>对象</td>
        <td>1</td>
        <td>状态</td>
        <td>对象</td>
        <td>2</td>
    </tr>
    <tr>
        <td>组合</td>
        <td>对象</td>
        <td>2</td>
        <td>策略</td>
        <td>对象</td>
        <td>3</td>
    </tr>
    <tr>
        <td>装饰器</td>
        <td>对象</td>
        <td>2</td>
        <td>模板方法</td>
        <td>类</td>
        <td>2</td>
    </tr>
    <tr>
        <td>外观</td>
        <td>对象</td>
        <td>2</td>
        <td>访问者</td>
        <td>对象</td>
        <td>1</td>
    </tr>
    <tr>
        <td>享元</td>
        <td>对象</td>
        <td>1</td>
        <td>解析器</td>
        <td>类</td>
        <td></td>
    </tr>
    <tr>
        <td>代理</td>
        <td>对象</td>
        <td>1</td>
    </tr>
</table>

---
## 对比
>### Object Pool vs Flyweight
>```
>Object Pool        每个对象都等价     主要目的是节省时间       独占
>Flyweight          每个对象不等价     主要目的是节省空间       共享
>```
>### Proxy vs Decorator
>1. 服务对象的生命周期
>```
>Proxy              通常由 Proxy 控制 服务对象(RealSubject) 的生命周期，即在 Proxy 里 new 服务对象
>Decorator          必定由 Client 控制 服务对象(Component) 的生命周期，即通过 Decorator 构造器传入 服务对象
>```
>2. Decorator 是 Proxy 的一个特例
>```
>Proxy              Proxy 实现 Subject，其持有的 RealSubject 也实现 Subject
>Decorator          Decorator 实现 Component，其持有的 Compoent 的类型就是 Component
>```
>### Proxy vs Decorator vs Object Adapter
>```
>Proxy              为对象提供相同接口   Proxy 和其持有的 服务对象(RealSubject) 都实现 Subject
>Decorator          为对象提供增强接口   Decorator 和其持有的 服务对象(Component) 都实现 Component
>Object Adapter     为对象提供不同接口   Adapter 实现 Target，其持有的 服务对象(Adaptee) 和 Target 没有关系
>```
>### Facade vs Mediator
>```
>Facade             Subsystem 之间可以直接交流
>Mediator           Colleague 之间无法直接交流
>```
>### Observer vs Mediator vs Producer Consumer vs Publish/Subscribe
>```
>Observer           Subject     ==>                 Observer
>Mediator           Colleage    <==Mediator==>      Colleage
>Producer Consumer  Producer    ==Queue==>          Consumer
>Publish/Subscribe  Publish     ==Broker/Topic==>   Subscriber
>```
>### Strategy vs Bridge
>- Strategy 可以看成是 Bridge 的一个子集：
>   - Abstraction → Context
>   - Implementor → Strategy，ConcreteImplementor → ConcreteStrategy
>### Strategy vs State
>1. 是否主动选择
>```
>Strategy           Context 接收选择的 Strategy
>State              Context 设置初始的 State
>```
>2. 是否能够改变
>```
>Strategy           选择 Strategy 后不能改变
>State              ConcreteState 可以通过其持有的 Context 引用，改变当前 State
>```
>### Strategy vs Template Method
>```
>Strategy           基于组合，动态的，context.setStrategy(strategy)
>Template Method    基于继承，静态的，AbstractClass tempalte = new ConcreteClass()
>```
>### Strategy vs Command
>1. 类似：
>   - Context → Invoker
>   - Strategy → Command，ConcreteStrategy → ConcreteCommand
>2. 区别：
>   1. Command 比 Strategy 多了个实际完成工作的角色 Receiver
>   2. 某些情况下 Strategy 之间可以互相替换，Command 不可以
>### Command vs Visitor
>- Visitor 可视为 Command 的加强版：
>   - Command → Element，ConcreteCommand → ConcreteElement
>   - Receiver → Visitor
>### Template Method vs Factory Method
>- Factory Method 是 Template Method 的一个特例
---
## 阿里编程规约
- 如果模块、接口、类、方法使用了设计模式，在命名时需体现出具体模式
---