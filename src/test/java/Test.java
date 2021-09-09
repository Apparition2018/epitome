// spring-boot-dependencies
// NIO, AIO：https://coding.imooc.com/class/chapter/381.html
// Mockito
// xxl-job：https://blog.csdn.net/masson32/article/details/91503723
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