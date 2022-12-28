package knowledge.design.pattern.gof.structural.adapter;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * 适配器模式：使现有不兼容的接口可以一起工作
 * 使用场景：
 * 1.现有接口不兼容
 * 2.复用无法添加到超类的通用功能的多个现有子类 ???
 * 3.只想使用接口中的某些方法 (Default Adapter，使用抽象类实现接口)
 * 使用实例：                                    Adaptee             Adapter                     Target
 * Java IO                                      InputStream         InputStreamReader           Reader
 * Java IO                                      OutputStream        OutputStreamWriter          Writer
 * {@link Arrays#asList(Object[])}              E[]                 Arrays#ArrayList            AbstractList
 * {@link Collections#list(Enumeration)}        Enumeration<T>      ArrayList                   ArrayList
 * {@link Collections#enumeration(Collection)}  Collection<T>       实现 Enumeration             Enumeration
 * Spring AOP                                   MethodBeforeAdvice  MethodBeforeAdviceAdapter   AdvisorAdapter
 * Spring MVC                                   HttpRequestHandler  HttpRequestHandlerAdapter   HandlerAdapter
 * <p>
 * 角色：
 * 目标 Target：与 Client 交互的接口
 * 被适配 Adaptee：现有的一些功能类，Client 与其不兼容
 * 适配器 Adapter：实现或继承 Target，类适配器继承 Adaptee，对象适配器持有 Adaptee 的引用
 * <p>
 * 分类：
 * 1.Object Adapter     class Adapter extends Adaptee implement Target {}               继承
 * 2.Class Adapter      class Adapter implement Target { private Adaptee adaptee}       委派
 * 3.Default Adapter    abstract class Adapter implement Adaptee {}
 * <p>
 * 优点：符合单一职责原则、开闭原则
 * 扩展：同时实现或继承 Target 和 Adaptee，同时持有 Target 和 Adaptee 的引用，实现双向适配器
 * <p>
 * Adapter：https://refactoringguru.cn/design-patterns/adapter
 * 设计模式之美：适配器模式：代理、适配器、桥接、装饰，这四个模式有何区别？
 *
 * @author ljh
 * @since 2020/9/26 2:51
 */
public class AdapterDemo {

    /**
     * 1.MediaPlayer 可以播放 MP3
     * 2.VideoPlayer 可以播放 VLC 或 MP4
     * 让 MediaPlayer 可以播放所有文件
     */
    @Test
    public void testAdapter() {
        MediaPlayer player = new MediaPlayerAdapter();

        player.play("beyond the horizon.mp3");
        player.play("alone.mp4");
        player.play("far far away.vlc");
        player.play("mind me.avi");
    }

    /**
     * Adaptee
     */
    static abstract class VideoPlayer {
        void playVlc(String fileName) {
        }

        void playMp4(String fileName) {
        }
    }

    static class VlcPlayer extends VideoPlayer {
        @Override
        public void playVlc(String fileName) {
            System.out.println("Playing vlc file. Name: " + fileName);
        }
    }

    static class Mp4Player extends VideoPlayer {
        @Override
        public void playMp4(String fileName) {
            System.out.println("Playing mp4 file. Name: " + fileName);
        }
    }

    /**
     * Target
     */
    static class MediaPlayer {
        public void play(String fileName) {
            System.out.println("Playing mp3 file. Name: " + fileName);
        }
    }

    /**
     * Adapter
     * <p>对象适配器模式
     */
    static class MediaPlayerAdapter extends MediaPlayer {
        private VideoPlayer videoPlayer;

        @Override
        public void play(String fileName) {
            Locale.setDefault(Locale.ENGLISH);
            if (fileName.toLowerCase().endsWith("vlc")) {
                videoPlayer = new VlcPlayer();
                videoPlayer.playVlc(fileName);
            } else if (fileName.toLowerCase().endsWith("mp4")) {
                videoPlayer = new Mp4Player();
                videoPlayer.playMp4(fileName);
            } else if (fileName.toLowerCase().endsWith("mp3")) {
                super.play(fileName);
            } else {
                System.out.println("Invalid media. " + fileName.split("\\.")[1] + " format not supported");
            }
        }
    }
}
