package jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * <a href="https://openjdk.org/jeps/230">JDK12 JEP 230: Microbenchmark Suite</a>
 * <pre>
 * <a href="https://www.baeldung.com/java-microbenchmark-harness">Microbenchmarking with Java</a>
 * <a href="https://www.imooc.com/video/19005">微基准测试</a>
 * <a href="https://www.zhihu.com/question/276455629">为什么要使用 JMH</a>
 * </pre>
 *
 * @author ljh
 * @since 2023/2/14 9:20
 */
@Fork(1)
@Threads(4)
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 5, time = 5)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(value = Scope.Benchmark)
public class MicrobenchmarkTest {

    @Param(value = {"10", "50", "100"})
    private int length;

    @Benchmark
    public void testString(Blackhole blackhole) {
        String s = "";
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

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(MicrobenchmarkTest.class.getSimpleName())
                .result("result.json")
                .resultFormat(ResultFormatType.JSON)
                .build();
        new Runner(options).run();
    }
}
