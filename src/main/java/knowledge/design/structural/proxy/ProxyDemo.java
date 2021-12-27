package knowledge.design.structural.proxy;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 代理模式：为原始对象提供一个代理以控制对这个对象的访问，在到达原始对象之前或之后执行某些操作
 * 主要解决：访问原始对象相关的问题
 * <p>
 * 角色：
 * 抽象主题角色 Subject
 * 真实主题角色 RealSubject：实现或继承 Subject
 * 代理角色 Proxy：实现或继承 Subject，持有 RealSubject 的引用
 * <p>
 * 关键代码：RealSubject 和 Proxy 共同实现或继承 Subject
 * 优点：符合开闭原则
 * <p>
 * 使用场景：
 * 1. @PreAuthorize，@Cacheable，@Transactional
 * 2. 非业务需求：鉴权、缓存、事务、监控、统计、限流、幂等、日志，可使用 Spring AOP 实现
 * <p>
 * Proxy：https://refactoring.guru/design-patterns/proxy
 * JavaGuide：https://github.com/Snailclimb/JavaGuide/blob/main/docs/java/basis/%E4%BB%A3%E7%90%86%E6%A8%A1%E5%BC%8F%E8%AF%A6%E8%A7%A3.md
 * 敖丙：https://mp.weixin.qq.com/s/lNw1yjn_xMOLpzbunaS10A
 * 菜鸟教程：https://www.runoob.com/design-pattern/proxy-pattern.html
 * 设计模式之美：代理在RPC、缓存、监控等场景中的应用
 *
 * @author Arsenal
 * created on 2020/9/26 2:51
 */
public class ProxyDemo {

    @Test
    public void testProxy() {
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
