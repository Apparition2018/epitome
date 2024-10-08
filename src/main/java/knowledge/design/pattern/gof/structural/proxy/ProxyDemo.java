package knowledge.design.pattern.gof.structural.proxy;

import lombok.SneakyThrows;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 代理模式：为原始对象提供一个代理以控制对这个对象的访问，在到达原始对象之前或之后执行某些操作
 * <p>使用场景：控制访问
 * <pre>
 * 1 非业务需求：鉴权、缓存、事务、监控、统计、限流、幂等、日志，可使用 Spring AOP 实现
 * 2 延迟初始化
 * </pre>
 * 使用实例：
 * <pre>x
 * 1 @PreAuthorize，@Cacheable，@Transactional
 * 2 {@link java.rmi}
 * 3 {@link Proxy}
 * 4 {@link org.springframework.aop.framework.JdkDynamicAopProxyJdkDynamicAopProxy}
 * 5 {@link org.springframework.aop.framework.CglibAopProxyCglibAopProxy}
 * </pre>
 * 角色：
 * <pre>
 * 抽象主题 Subject
 * 真实主题 RealSubject
 * 代理 Proxy：实现 Subject，或直接继承 RealSubject，持有 RealSubject 的引用
 * </pre>
 * 优点：符合开闭原则<br/>
 * 缺点：RealSubject 与 Proxy 一一对应，RealSubject 增加，Proxy 也要跟着增加
 * <p>优化：动态代理、Spring AOP
 *
 * @author ljh
 * @see <a href="https://refactoringguru.cn/design-patterns/proxy">Proxy</a>
 * @see <a href="http://c.biancheng.net/view/1359.html">Java设计模式</a>
 * @see <a href="https://zhuanlan.zhihu.com/p/381795745">设计模式最佳套路3 —— 愉快地使用代理模式</a>
 * @see <a href="https://gupaoedu-tom.blog.csdn.net/article/details/120984573">Tom</a>
 * @see <a href="https://github.com/Snailclimb/JavaGuide/blob/main/docs/java/basis/proxy.md">JavaGuide</a>
 * @since 2020/9/26 2:51
 */
public class ProxyDemo {

    /**
     * 案例1：延迟初始化
     */
    private static class ProxyLazyDemo {

        /**
         * Subject
         */
        interface Query {
            Object request();
        }

        /**
         * RealSubject
         */
        private static class QueryService implements Query {
            @SneakyThrows
            public QueryService() {
                TimeUnit.SECONDS.sleep(1);
            }

            @Override
            public Object request() {
                return new Object();
            }
        }

        /**
         * Proxy
         */
        private static class QueryProxyService implements Query {
            QueryService queryService = null;

            @Override
            public Object request() {
                // 第一次使用时才实例化 RealSubject
                if (queryService == null) queryService = new QueryService();
                return queryService.request();
            }
        }
    }

    /**
     * 案例2：缓存
     */
    private static class ProxyCacheDemo {
        public static void main(String[] args) {
            // RealSubject
            downloadFiveSameVideo(new NaiveDownloader());
            // Proxy
            downloadFiveSameVideo(new SmartDownloader());
        }

        private static void downloadFiveSameVideo(Downloader downloader) {
            System.out.println("-------------------------------");
            long startTime = System.currentTimeMillis();
            downloader.download(1);
            downloader.download(1);
            downloader.download(1);
            downloader.download(1);
            downloader.download(1);
            long estimatedTime = System.currentTimeMillis() - startTime;
            System.out.print("Time elapsed: " + estimatedTime + "ms\n");
            System.out.println("-------------------------------\n");
        }

        /**
         * Subject
         */
        interface Downloader {
            Video download(int id);
        }

        /**
         * RealSubject
         */
        private static class NaiveDownloader implements Downloader {
            @SneakyThrows
            @Override
            public Video download(int id) {
                TimeUnit.MILLISECONDS.sleep(100);
                return new Video(id);
            }
        }

        /**
         * Proxy
         */
        private static class SmartDownloader implements Downloader {
            private final Downloader downloader;
            private final Map<Integer, Video> cache = new HashMap<>();

            public SmartDownloader() {
                this.downloader = new NaiveDownloader();
            }

            @Override
            public Video download(int id) {
                // 查看缓存中是否已经存在
                Video video = cache.get(id);
                if (video == null) {
                    video = downloader.download(id);
                    cache.put(id, video);
                } else {
                    System.out.println("download video " + id + " from cache.");
                }
                return video;
            }
        }

        private static class Video {
            public Integer id;

            Video(Integer id) {
                this.id = id;
            }
        }
    }
}
