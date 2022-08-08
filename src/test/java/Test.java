// JavaIO三剑客之BIO/NIO/AIO：https://coding.imooc.com/class/chapter/381.html
// xxl-job
// Xerces, jaxb
// asm, Cglib Nodep
// iText, FOP
// YunGouOS
// WebFlux：https://mp.weixin.qq.com/s?__biz=MzA5MzQ2NTY0OA==&mid=2650796733&idx=1&sn=b54cd0c3e18a6fcf04821047e22f3119
// Arthas
// rabbitmq, kafka, rocketmq
// spring cloud, dubbo
// BladeX + Saber
// k8s
// Guns, RuoYi, SpringBlade, ShopXO
// Jeecg-Boot
// LuckyFrameWeb
// uni-app, Flutter, avue, Ant Design
// 高效使用 IntelliJ IDEA：https://fangshixiang.blog.csdn.net/article/details/119411518

// 微信 Native 支付：https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter3_4_1.shtml
// IJPay：https://gitee.com/javen205/IJPay/tree/master

// 龙虾三少
// CSDN 阿_毅
// 林祥纤 SpringBoot

// TODO-LJH springboot springmvc
// TODO-LJH file-server，把文件上传到 gitee
// TODO-LJH spring-boot-jdbc

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import l.demo.Demo;
import l.demo.Person;
public class Test extends Demo {

    public static void main(String[] args) throws Exception {
        Person person = new Person().setId(1).setName("A");
        Person person2 = new Person().setName("B");
        BeanUtil.copyProperties(person2, person, CopyOptions.create(Person.class, true));
        System.err.println(person);
    }

}
