# Design Pattern

## 参考网站
1. [设计模式](https://refactoringguru.cn/design-patterns)
2. [Design Patterns](https://sourcemaking.com/design_patterns)
3. [Java 设计模式](http://c.biancheng.net/design_pattern/)
4. [设计模式之美](https://pan.baidu.com/disk/home?#/all?vmode=list&path=%2F%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F%E4%B9%8B%E7%BE%8E)
5. [设计模式 | 菜鸟教程](https://www.runoob.com/design-pattern/design-pattern-intro.html)
6. [设计模式 | 刘伟](hhttps://blog.csdn.net/lovelion/category_738450.html)
7. [如何学习设计模式？|知乎](https://www.zhihu.com/question/308850392/answer/1324509357)
---
## 分类
<table>
    <tr>
        <td>类型</td>
        <td>模式</td>
        <td>流行度</td>
        <td>类型</td>
        <td>模式</td>
        <td>流行度</td>
    </tr>
    <tr>
        <td rowspan="5">创建</td>
        <td>单例</td>
        <td>2</td>
        <td rowspan="11">行为</td>
        <td>责任链</td>
        <td>1</td>
    </tr>
    <tr>
        <td>抽象工厂</td>
        <td>3</td>
        <td>命令</td>
        <td>3</td>
    </tr>
    <tr>
        <td>工厂方法</td>
        <td>3</td>
        <td>迭代器</td>
        <td>3</td>
    </tr>
    <tr>
        <td>建造者</td>
        <td>3</td>
        <td>中介者</td>
        <td>2</td>
    </tr>
    <tr>
        <td>原型</td>
        <td>2</td>
        <td>备忘录</td>
        <td>1</td>
    </tr>
    <tr>
        <td rowspan="7">结构</td>
        <td>适配器</td>
        <td>3</td>
        <td>观察者</td>
        <td>3</td>
    </tr>
    <tr>
        <td>桥接</td>
        <td>1</td>
        <td>状态</td>
        <td>2</td>
    </tr>
    <tr>
        <td>组合</td>
        <td>2</td>
        <td>策略</td>
        <td>3</td>
    </tr>
    <tr>
        <td>装饰器</td>
        <td>2</td>
        <td>模板方法</td>
        <td>2</td>
    </tr>
    <tr>
        <td>外观</td>
        <td>2</td>
        <td>访问者</td>
        <td>1</td>
    </tr>
    <tr>
        <td>享元</td>
        <td>1</td>
        <td>解析器</td>
        <td></td>
    </tr>
    <tr>
        <td>代理</td>
        <td>1</td>
    </tr>
</table>

---
## 对比
>### Factory vs Builder
>```
>Factory            产品角色分为抽象产品和具体产品
>Builder            产品角色只有具体产品
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
>### Bridge vs Strategy
>- Strategy 可以看成是 Bridge 的一个子集：
>   - Abstraction → Context
>   - Implementor → Strategy，ConcreteImplementor → ConcreteStrategy
>### State vs Strategy
>1. 是否主动选择
>```
>State              Context 设置初始的 State
>Strategy           Context 接收选择的 Strategy 
>```
>2. 是否能够改变
>```
>State              ConcreteState 可以通过其持有的 Context 引用，改变当前 State
>Strategy           选择 Strategy 后不能改变 
>```
---
## 阿里编程规约
- 如果模块、接口、类、方法使用了设计模式，在命名时需体现出具体模式
---
* 工作中用到的设计模式：
* https://www.cnblogs.com/dubing/archive/2011/10/22/2221138.html
* https://www.zhihu.com/question/340301316
* https://juejin.cn/post/7023536216138055716
---
各自满足的原则：https://blog.csdn.net/qq_44824148/article/details/108184915
软件设计师-设计模式真题