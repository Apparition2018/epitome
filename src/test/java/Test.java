// spring-boot-dependencies
// NIO, AIO：https://coding.imooc.com/class/chapter/381.html
// Mockito
// Xerces, jaxb
// asm, Cglib Nodep
// iText, FOP
// YunGouOS
// WebFlux：https://mp.weixin.qq.com/s?__biz=MzA5MzQ2NTY0OA==&mid=2650796733&idx=1&sn=b54cd0c3e18a6fcf04821047e22f3119
// Activiti
// Arthas
// rabbitmq, kafka, rocketmq
// mycat
// spring cloud, dubbo
// BladeX + Saber
// k8s
// Guns, RuoYi, SpringBlade, ShopXO
// Jeecg-Boot, Jeecg-Boot
// LuckyFrameWeb
// uni-app, Flutter, avue, Ant Design

// 【小家Spring】Spring Framework提供的实用纯Java工具类大合集（一）：https://blog.csdn.net/f641385712/article/details/86749481
// To Be Top Javaer - Java工程师成神之路：https://github.com/hollischuang/toBeTopJavaer
// Java程序员从笨鸟到菜鸟全部博客目录：https://blog.csdn.net/u012426327/article/details/77765651
// 史上最全设计模式导学目录（完整版）：https://blog.csdn.net/u012426327/article/details/77766203
// 牛逼！java程序员必看经典书单，以及各个阶段学习建议!：https://blog.csdn.net/u012426327/article/details/78491295
// 前端知识体系及修炼攻略：https://blog.csdn.net/u012426327/article/details/78665872
// java同步非阻塞IO：https://segmentfault.com/a/1190000015449773

// 调度与监控-spring batch(7)结合xxl-job进行批处理：https://blog.csdn.net/masson32/article/details/91503723
// 最新 Java 面试系列干货，都在这了！：https://mp.weixin.qq.com/s/kJpRgfI3zT77XqMeRfmmQQ

// https://quoters.apps.pcfone.io/api/random

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import l.demo.Demo;
import l.demo.Person;
import lombok.extern.slf4j.Slf4j;

// CSDN 阿_毅
// 林祥纤 SpringBoot
@Slf4j
public class Test extends Demo {

    public static void main(String[] args) throws Exception {
        Person person = new Person().setId(1).setName("A");
        Person person2 = new Person().setName("B");
        BeanUtil.copyProperties(person2, person, CopyOptions.create(Person.class, true));
        System.err.println(person);
    }

}