package jar.lombok;

import l.demo.Demo;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author ljh
 * created on 2019/8/8 19:39
 */
@Slf4j
public class LombokDemo3 extends Demo {

    /**
     * var （已过时）
     * 从初始化表达式中推断出变量的类型
     * 使用前需要在配置文件 lombok.config 配置 lombok.var.flagUsage = ALLOW
     */
    @Test
    public void var() {
        var x = 2;
        var y = 8;
        p(x + y);

        var list = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4));
        p(list);
    }

    /**
     * cleanup
     * 自动资源管理，不用再使用 close() 释放资源
     */
    @Test
    public void testCleanup() {
        try {
            @Cleanup InputStream is = new ByteArrayInputStream("Arsenal".getBytes());

            int data;
            StringBuilder sb = new StringBuilder();

            while ((data = is.read()) != -1) {
                sb.append((char) data);
            }

            p(sb);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * SneakyThrows
     * 不用捕捉或抛出异常了，谨慎使用
     */
    @Test
    @SneakyThrows
    public void testSneakyThrows() {
        p("sleep start");
        TimeUnit.SECONDS.sleep(2);
        p("sleep end");
    }

    /**
     * synchronized
     * 几乎相当于 synchronized method
     */
    @Test
    @Synchronized
    public void testSynchronized() {
    }

    /**
     * Getter(lazy = true)
     * 实际使用到的时候才生成
     * 提高代码效率，同时由 lombok 管理线程安全问题
     */
    @Test
    public void testGetterLazy() {
        p(getCached());
    }

    @Getter(lazy = true)
    private final double[] cached = expensive();

    private double[] expensive() {
        double[] result = new double[1000000];
        IntStream.rangeClosed(0, result.length - 1).forEach(i -> result[i] = new Random().nextInt(100));
        return result;
    }

}


