# [JDK9 JEP 261: Module System](https://openjdk.org/jeps/261)

---
## Reference
- [Java 为何需要模块化](https://achang.blog.csdn.net/article/details/126632186)
- [Java 模块系统](https://achang.blog.csdn.net/article/details/126652674)
---
## option
1. `--add-exports <source-module>/<package>=<target-module>(,<target-module>)*`
2. `--add-opens <source-module>/<package>=<target-module>(,<target-module>)*`
3. `--add-modules <module>(,<module>)*`
- `-parameters -Xlint:unchecked --add-exports java.base/jdk.internal.vm.annotation=ALL-UNNAMED --add-modules jdk.incubator.vector --add-modules jdk.incubator.vector`
---
