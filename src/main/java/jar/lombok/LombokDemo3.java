package jar.lombok;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;


/**
 * Slf4j
 * 日志
 */
@Slf4j
public class LombokDemo3 {

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
    public void cleanup() {
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
    public void sneakyThrows() {
        p(System.currentTimeMillis());

        TimeUnit.SECONDS.sleep(2);

        p(System.currentTimeMillis());
    }

    /**
     * synchronized
     * 几乎相当于 synchronized method
     */
    @Test
    @Synchronized
    public void synchronized_() {
    }

    /**
     * Getter(lazy = true)
     * 实际使用到的时候才生成
     * 提高代码效率，同时由 lombok 管理线程安全问题
     */
    @Getter(lazy = true)
    private final double[] cached = expensive();

    private double[] expensive() {
        double[] result = new double[1000000];
        for (int i = 0, len = result.length; i < len; i++) {
            result[i] = (int) (Math.random() * 100);
        }
        return result;
    }

    @Test
    public void lazy() {
        p(Arrays.toString(getCached()));
    }

    private static <T> void p(T obj) {
        if (obj == null) return;
        System.out.println(obj);
    }

}


