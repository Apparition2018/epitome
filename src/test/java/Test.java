// SpringBoot——启动初始化数据：https://www.jianshu.com/p/01e08aef73c9
// Spring @Cache                            *** *** ***
// 最小 rpc：https://link.zhihu.com/?target=https%3A//github.com/yeecode/EasyRPC
// springboot+redis+shiro+spring-session实现session共享：https://blog.csdn.net/qq_29281307/article/details/90041380
// Mysql慢查询日志
// springboot中使用h2数据库（内存模式）:https://www.cnblogs.com/TheoryDance/p/11941180.html
// Spring Boot 2.x基础教程  http://blog.didispace.com/spring-boot-learning-2x/
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

import l.demo.Demo;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Random;

// CSDN 阿_毅
// 林祥纤 SpringBoot
@Slf4j
public class Test extends Demo {

    public static void main(String[] args) throws Exception {
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static int[] arr = new int[10];

    static {
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100);
        }
    }

    private static void bubbleSort(int[] arr) {
        int temp;
        for (int i = 0, len = arr.length; i < len - 1; i++) {
            boolean hasChange = false;
            for (int j = 0; j < len - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    hasChange = true;
                }
            }
            if (!hasChange) {
                break;
            }
        }
    }
}