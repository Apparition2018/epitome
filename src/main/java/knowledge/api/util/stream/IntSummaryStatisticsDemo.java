package knowledge.api.util.stream;

import java.util.IntSummaryStatistics;
import java.util.stream.IntStream;

import static l.demo.Demo.p;

/**
 * IntSummaryStatistics
 * <pre>
 * 用于收集计数、最小值、最大值、和和平均值等统计信息的状态对象
 * 类似的还有 LongSummaryStatistics，DoubleSummaryStatistics
 * </pre>
 *
 * @author ljh
 * @since 2021/1/14 15:41
 */
public class IntSummaryStatisticsDemo {

    public static void main(String[] args) {
        IntSummaryStatistics stats = IntStream.rangeClosed(1, 2).summaryStatistics();
        IntSummaryStatistics stats2 = IntStream.rangeClosed(3, 4).summaryStatistics();

        // 合并
        stats.combine(stats2);
        // 添加统计值
        stats.accept(5);

        p(stats.getCount());    // 5
        p(stats.getSum());      // 15
        p(stats.getMin());      // 1
        p(stats.getMax());      // 5
        p(stats.getAverage());  // 3.0
    }
}
