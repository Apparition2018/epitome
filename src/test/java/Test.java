// 史上最强整理: https://mp.weixin.qq.com/s/kJpRgfI3zT77XqMeRfmmQQ
// SpringBoot + Spring Batch
// shiro oauth2
// Guava (140)
// Nutz: http://nutzam.com/index.html
// htmlcleaner + jsoup
// Groovy Gradle
// combinator
// Mockito
// API tester
// jmeter
// 注册中心，配置中心 nacos
// spring data jpa + Querydsl
// spring cloud, dubbo
// redis, rocketmq, mycat
// docker, k8s
// 主从数据库
// nginx
// Spring @Cache
// Spring Boot 2.x基础教程  http://blog.didispace.com/spring-boot-learning-2x/
// @HeaderParam
// ON DUPLICATE KEY UPDATE
// 上传/下载文件
// BladeX + Saber

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
// 8分钟深入浅出搞懂BIO、NIO、AIO：https://zhuanlan.zhihu.com/p/83597838

import l.demo.Demo;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.Objects;

// CSDN 阿_毅
// 林祥纤 SpringBoot
public class Test extends Demo {

    public static void main(String[] args) throws IOException {
        System.out.println(1.1 % 1);
    }

    @RequestMapping("/downloadExcel")
    @ResponseBody
    public void downloadExcelTemplate(HttpServletResponse response) {

        URL classesUrl = Thread.currentThread().getContextClassLoader().getResource("/");
        String classesUrlPath = Objects.requireNonNull(classesUrl).getPath();
        String templatePath = new File(classesUrlPath).getParentFile().getParentFile().getPath() +
                File.separator + "doc" + File.separator + "Contract_1606122467519.xlsx";
        File template = new File(templatePath);

        try (InputStream inputStream = new FileInputStream(template);
             OutputStream outputStream = response.getOutputStream();) {
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename=template.xlsx");
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}