// Spring Boot 2.x基础教程  http://blog.didispace.com/spring-boot-learning-2x/
// WebFlux
// spring-boot-dependencies 中的各个依赖
// redis, rocketmq, mycat
// spring cloud, dubbo
// BladeX + Saber
// 注册中心，配置中心 nacos
// docker, k8s
// uni-app

// 记一次完整的java项目压力测试：https://www.cnblogs.com/jpfss/p/11645793.html
// https://www.jianshu.com/u/8bbac962b31a
// Java开发人员必知必会的20种常用类库和API：https://zhuanlan.zhihu.com/p/54716716
// 【小家java】java5新特性（简述十大新特性） 重要一跃：https://blog.csdn.net/f641385712/article/details/81783266
// 【小家Spring】Spring Framework提供的实用纯Java工具类大合集（一）：https://blog.csdn.net/f641385712/article/details/86749481
// To Be Top Javaer - Java工程师成神之路：https://github.com/hollischuang/toBeTopJavaer
// 2019 年度最受欢迎中国开源软件：https://www.oschina.net/question/2918182_2313492
// Java程序员从笨鸟到菜鸟全部博客目录：https://blog.csdn.net/u012426327/article/details/77765651
// 史上最全设计模式导学目录（完整版）：https://blog.csdn.net/u012426327/article/details/77766203
// Android学习：https://blog.csdn.net/u012426327/article/list/7C
// 牛逼！java程序员必看经典书单，以及各个阶段学习建议!：https://blog.csdn.net/u012426327/article/details/78491295
// 前端知识体系及修炼攻略：https://blog.csdn.net/u012426327/article/details/78665872
// java同步非阻塞IO：https://segmentfault.com/a/1190000015449773
// 玩转Java并发工具，精通JUC，成为并发多面手：https://coding.imooc.com/class/409.html
// 高并发Java NIO和AIO：https://zhuanlan.zhihu.com/p/70273939
// SpringCloud微服务实战：https://github.com/fengzhimiwu/fw-spring-cloud

// 调度与监控-spring batch(7)结合xxl-job进行批处理：https://blog.csdn.net/masson32/article/details/91503723
// 最新 Java 面试系列干货，都在这了！：https://mp.weixin.qq.com/s/kJpRgfI3zT77XqMeRfmmQQ

// https://quoters.apps.pcfone.io/api/random

import cn.hutool.core.thread.ThreadUtil;
import l.demo.Demo;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;

// CSDN 阿_毅
// 林祥纤 SpringBoot
@Slf4j
public class Test extends Demo {

    public static void main(String[] args) throws Exception {
        String s = "a-c";
        System.out.println(s.contains("-"));
    }

}