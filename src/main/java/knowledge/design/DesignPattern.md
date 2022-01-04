# Design Pattern

## 参考网站
1. [设计模式](https://refactoringguru.cn/design-patterns)
2. [Design Patterns](https://sourcemaking.com/design_patterns)
3. [design_patterns_online.pdf](http://campus.murraystate.edu/academic/faculty/wlyle/430/rc008-designpatterns_online.pdf)
4. [设计模式之美](https://pan.baidu.com/disk/home?#/all?vmode=list&path=%2F%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F%E4%B9%8B%E7%BE%8E)
5. [Java与模式](https://www.cnblogs.com/foryang/p/5849402.html)
6. [菜鸟教程](https://www.runoob.com/design-pattern/design-pattern-intro.html)
---
## 分类
|类型|模式|
|:---|:---|
|创建|单例、抽象工厂、工厂方法、建造者、原型|
|结构|组合、适配器、桥接、代理、装饰器、外观、享元|
|行为|模版方法、命令、迭代器、观察者、中介者、备忘录、解释器、状态、策略、责任链、访问者|
---
## 对比
>### Proxy vs Decorator
>```
>Proxy              通常由 Proxy 控制 服务对象(RealSubject) 的生命周期，
>                   即在 Proxy 里 new RealSubject
>Decorator          必定由 Client 控制 服务对象(Component) 的生命周期，
>                   即通过 Decorator 构造器传入 Component，
>                   Decorator 是 Proxy 的一个特例
>```
>### Proxy vs Decorator vs Object Adapter
>```
>Proxy              为对象提供相同接口   Proxy 和其持有的 服务对象(RealSubject) 都实现 Subject
>Decorator          为对象提供增强接口   Decorator 和其持有的 服务对象(Component) 都实现 Component
>Object Adapter     为对象提供不同接口   Adapter 实现 Target，其持有的 服务对象(Adaptee) 和 Target 没有关系
>```
>### Adapter vs Bridge
>```
>Adapter            在已有程序中使用
>Bridge             用于开发前期的设计
>```
---
## 阿里编程规约
- 如果模块、接口、类、方法使用了设计模式，在命名时需体现出具体模式
---
* 工作中用到的设计模式：
* https://www.cnblogs.com/dubing/archive/2011/10/22/2221138.html
* https://www.zhihu.com/question/340301316
* https://juejin.cn/post/7023536216138055716