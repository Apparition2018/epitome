// 史上最强整理: https://mp.weixin.qq.com/s/kJpRgfI3zT77XqMeRfmmQQ
// SpringBoot + Spring Batch
// Guava (140)
// Nutz: http://nutzam.com/index.html
// Groovy Gradle
// 注册中心，配置中心 nacos
// spring data jpa + Querydsl
// spring cloud, dubbo
// BladeX + Saber
// redis, rocketmq, mycat
// docker, k8s
// nginx
// Spring @Cache
// Spring Boot 2.x基础教程  http://blog.didispace.com/spring-boot-learning-2x/
// @HeaderParam
// 上传/下载文件
// uni-app
// mybatis-plus activeRecord
// 最小 rpc：https://link.zhihu.com/?target=https%3A//github.com/yeecode/EasyRPC
// springboot+redis+shiro+spring-session实现session共享：https://blog.csdn.net/qq_29281307/article/details/90041380

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

import l.demo.Demo;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

// CSDN 阿_毅
// 林祥纤 SpringBoot
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
            boolean flag = true;
            for (int j = 0; j < len - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j +1] = temp;
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
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