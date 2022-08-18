package knowledge.concurrent;

import l.demo.Demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.LongStream;

/**
 * ForkJoinPool
 * JDK1.7 引入了一种新的并发框架 Fork/Join Framework，同时引入了一种新的线程池 ForkJoinPool。
 * ForkJoinPool 实现了 ExecutorService 和工作窃取算法，是 ExecutorService 的补充。
 * ForkJoinPool 主要用于实现"分而治之"的算法，特别是分治之后递归调用的函数，例如 quick sort 等。
 * ForkJoinPool 最适合的是计算密集型的任务，如果存在 I/O，线程间同步，sleep() 等会造成线程长时间阻塞的情况时，最好配合使用 ManagedBlocker。
 * 不是 CPU 密集型的任务，不建议使用 ForkJoinPool 进行处理
 * <p>
 * ForkJoinPool 线程池的使用以及原理：https://blog.csdn.net/f641385712/article/details/83749798
 * https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ForkJoinPool.html
 * https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ForkJoinTask.html
 * https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/RecursiveTask.html
 * https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/RecursiveAction.html
 *
 * @author ljh
 * created on 2020/12/1 12:58
 */
public class ForkJoinPoolDemo extends Demo {

    public static void main(String[] args) {
        long[] numbers = LongStream.rangeClosed(1, 1_0000_0000L).toArray();
        Calculator calculator;

        stopWatch.start("Fork/Join");
        calculator = new ForkJoinCalculator();
        calculator.sumUp(numbers);
        stopWatch.stop();

        stopWatch.start("For Loop");
        calculator = new ForLoopCalculator();
        calculator.sumUp(numbers);
        stopWatch.stop();

        stopWatch.start("ExecutorService");
        calculator = new ExecutorServiceCalculator();
        calculator.sumUp(numbers);
        stopWatch.stop();

        stopWatch.start("Stream");
        LongStream.rangeClosed(0, numbers.length).parallel().reduce(0, Long::sum);
        stopWatch.stop();

        p(stopWatch.prettyPrint());
        // ---------------------------------------------
        // ns         %     Task name
        // ---------------------------------------------
        // 374720700  072%  Fork/Join
        // 047458000  009%  For Loop
        // 043385600  008%  ExecutorService
        // 055527800  011%  Stream
    }

    interface Calculator {
        long sumUp(long[] numbers);
    }

    static class ForkJoinCalculator implements Calculator {

        private final ForkJoinPool threadPool;

        // 执行任务 (RecursiveTask 有返回值，RecursiveAction 无返回值)
        static class SumTask extends RecursiveTask<Long> {
            private static final long serialVersionUID = -5848348290388247808L;
            private final long[] numbers;
            private final int from;
            private final int to;

            public SumTask(long[] numbers, int from, int to) {
                this.numbers = numbers;
                this.from = from;
                this.to = to;
            }

            // ForkJoin 核心方法，对任务进行拆分
            @Override
            protected Long compute() {
                if (to - from < 6) {
                    // 当需要计算的数字个数小于 6 时，直接采用 for loop 方式计算结果
                    long total = 0;
                    for (int i = from; i <= to; i++) {
                        total += numbers[i];
                    }
                    return total;
                } else {
                    // 否则，把任务一分为二，递归拆分
                    int middle = (from + to) / 2;
                    SumTask taskLeft = new SumTask(numbers, from, middle);
                    SumTask taskRight = new SumTask(numbers, middle + 1, to);
                    // fork() 和 invokeAll() 的区别：https://blog.csdn.net/cxl0921/article/details/76460909
                    // taskLeft.fork();
                    // taskRight.fork();
                    invokeAll(taskLeft, taskRight);
                    return taskLeft.join() + taskRight.join();
                }
            }
        }

        public ForkJoinCalculator() {
            threadPool = ForkJoinPool.commonPool();
        }

        @Override
        public long sumUp(long[] numbers) {
            Long result = threadPool.invoke(new SumTask(numbers, 0, numbers.length - 1));
            threadPool.shutdown();
            return result;
        }
    }

    static class ForLoopCalculator implements Calculator {
        @Override
        public long sumUp(long[] numbers) {
            long total = 0;
            for (long number : numbers) {
                total += number;
            }
            return total;
        }
    }

    static class ExecutorServiceCalculator implements Calculator {

        private final int parallelism;
        private final ExecutorService threadPool;

        public ExecutorServiceCalculator() {
            parallelism = Runtime.getRuntime().availableProcessors();
            threadPool = Executors.newFixedThreadPool(parallelism);
        }

        static class SumTask implements Callable<Long> {
            private final long[] numbers;
            private final int from;
            private final int to;

            public SumTask(long[] numbers, int from, int to) {
                this.numbers = numbers;
                this.from = from;
                this.to = to;
            }

            @Override
            public Long call() {
                long total = 0;
                for (int i = from; i <= to; i++) {
                    total += numbers[i];
                }
                return total;
            }
        }


        @Override
        public long sumUp(long[] numbers) {
            List<Future<Long>> results = new ArrayList<>();

            // 把任务分解为 n 份，交给 n 个线程处理
            // 然后把每一份都扔个一个 SumTask 线程 进行处理
            int part = numbers.length / parallelism;
            for (int i = 0; i < parallelism; i++) {
                int from = i * part;
                int to = (i == parallelism - 1) ? numbers.length - 1 : (i + 1) * part - 1;
                results.add(threadPool.submit(new SumTask(numbers, from, to)));
            }

            // 把每个线程的结果相加，得到最终结果 get()方法 是阻塞的
            // 优化方案：可以采用 CompletableFuture 来优化
            long total = 0L;
            for (Future<Long> f : results) {
                try {
                    total += f.get();
                } catch (Exception ignore) {
                }
            }
            threadPool.shutdown();
            return total;
        }
    }
}
