package jmh;

import org.apache.commons.lang3.StringUtils;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * <a href="https://openjdk.org/jeps/230">JDK12 JEP 230: Microbenchmark Suite</a>
 * <pre>
 * <a href="https://www.baeldung.com/java-microbenchmark-harness">Microbenchmarking with Java</a>
 * <a href="https://www.zhihu.com/question/276455629">为什么要使用 JMH</a>
 * <a href="https://www.imooc.com/video/19005">微基准测试</a>
 * </pre>
 *
 * @author ljh
 * @since 2023/2/14 9:20
 */
// 基准模式
//  Throughput：吞吐量，ops/time
//  AverageTime：平均时间，time/op
//  SampleTime：随机取样，最后输出取样结果的分布
//  SingleShotTime：单次操作时间，通常用于隐藏预热调用(warmup=0)测试冷启动性能
//  All
@BenchmarkMode(Mode.Throughput)
// 分叉
@Fork(1)
// 线程
@Threads(2)
// 预热
//  iterations：预热迭代次数
//  time：每次预热迭代时间
@Warmup(iterations = 2, time = 1)
// 测量
//  iterations：测量迭代次数
//  time：每次测量迭代时间
@Measurement(iterations = 4, time = 1)
// 状态
//  Benchmark：所有线程共享实例
//  Group：组内线程共享实例
//  Thread
@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.SECONDS)
public class MicrobenchmarkTest {

    @Param(value = {"10", "50", "100"})
    private int length;

    @Benchmark
    public void testString(Blackhole blackhole) {
        String s = StringUtils.EMPTY;
        for (int i = 0; i < length; i++) {
            s += i;
        }
        blackhole.consume(s);
    }

    @Benchmark
    public void testStringBuilder(Blackhole blackhole) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(i);
        }
        blackhole.consume(sb.toString());
    }

    public static void main(String[] args) throws RunnerException, IOException {
        // org.openjdk.jmh.Main.main(args);

        Options options = new OptionsBuilder()
                .include(MicrobenchmarkTest.class.getSimpleName())
                .result("result.json")
                .resultFormat(ResultFormatType.JSON)
                .build();
        new Runner(options).run();
    }
}
