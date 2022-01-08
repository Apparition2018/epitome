package knowledge.design.structural.proxy;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 代理模式：为原始对象提供一个代理以控制对这个对象的访问，在到达原始对象之前或之后执行某些操作
 * 使用场景：控制访问
 * 1. 非业务需求：鉴权、缓存、事务、监控、统计、限流、幂等、日志，可使用 Spring AOP 实现
 * 2. 延迟初始化
 * 使用实例：
 * 1. @PreAuthorize，@Cacheable，@Transactional
 * 2. java.rmi.*
 * <p>
 * 角色：
 * 抽象主题角色 Subject
 * 真实主题角色 RealSubject：实现 Subject
 * 代理角色 Proxy：实现 Subject，或直接继承 RealSubject，持有 RealSubject 的引用
 * <p>
 * 优点：符合开闭原则
 * <p>
 * Proxy：https://refactoringguru.cn/design-patterns/proxy
 * Java设计模式：http://c.biancheng.net/view/1359.html
 * 菜鸟教程：https://www.runoob.com/design-pattern/proxy-pattern.html
 * JavaGuide：https://github.com/Snailclimb/JavaGuide/blob/main/docs/java/basis/%E4%BB%A3%E7%90%86%E6%A8%A1%E5%BC%8F%E8%AF%A6%E8%A7%A3.md
 * 敖丙：https://mp.weixin.qq.com/s/lNw1yjn_xMOLpzbunaS10A
 *
 * @author Arsenal
 * created on 2020/9/26 2:51
 */
public class ProxyDemo {

    /**
     * 案例1：延迟初始化
     */
    static class ProxyLazyDemo {

        /**
         * Subject
         */
        interface Query {
            Object request();
        }

        /**
         * RealSubject
         */
        static class QueryService implements Query {
            @SneakyThrows
            public QueryService() {
                Thread.sleep(1000);
            }

            @Override
            public Object request() {
                return new Object();
            }
        }

        /**
         * Proxy
         */
        static class QueryProxyService implements Query {
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
    static class ProxyCacheDemo {
        @Test
        public void testCache() {
            // RealSubject
            downloadFiveSameVideo(new NaiveDownloader());
            // Proxy
            downloadFiveSameVideo(new SmartDownloader());
        }

        private void downloadFiveSameVideo(Downloader downloader) {
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
        static class NaiveDownloader implements Downloader {
            @SneakyThrows
            @Override
            public Video download(int id) {
                Thread.sleep(100);
                return new Video(id);
            }
        }

        /**
         * Proxy
         */
        static class SmartDownloader implements Downloader {
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

        static class Video {
            public Integer id;

            Video(Integer id) {
                this.id = id;
            }
        }
    }
}
