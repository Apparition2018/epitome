package knowledge.design.structural.adapter;

/**
 * 适配器模式：使不兼容的接口可以一起工作
 * 使用实例：                    Adaptee             Adapter                     Target
 * Java IO                      InputStream         InputStreamReader           Reader
 * java.util.Arrays#asList()    E[]                 Arrays#ArrayList            AbstractList
 * java.util.Collections#list() Collection<T>       实现 Enumeration             Enumeration
 * Spring AOP                   MethodBeforeAdvice  MethodBeforeAdviceAdapter   AdvisorAdapter
 * <p>
 * 分类：
 * 1.Object Adapter     class Adapter extends Adaptee implement Target {}               继承
 * 2.Class Adapter      class Adapter implement Target { private Adaptee adaptee}       委派
 * 3.Default Adapter
 * <p>
 * 角色：
 * 抽象目标角色 Target
 * 适配器角色 Adapter
 * 被适配角色 Adaptee
 * <p>
 * 优点：符合单一责任原则，开闭原则
 * <p>
 * Adapter：https://refactoringguru.cn/design-patterns/adapter
 * JAVA与模式：https://www.cnblogs.com/java-my-life/archive/2012/04/13/2442795.html
 * 菜鸟教程：https://www.runoob.com/design-pattern/adapter-pattern.html
 * 设计模式之美：适配器模式：代理、适配器、桥接、装饰，这四个模式有何区别？
 *
 * @author Arsenal
 * created on 2020/9/26 2:51
 */
public class AdapterDemo {

    /**
     * 案例：
     * 1.MediaPlayer 可以播放 MP3
     * 2.VideoPlayer 可以播放 VLC 或 MP4
     * 让 MediaPlayer 可以播放所有文件
     */
    public static void main(String[] args) {
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
     * 对象适配器模式
     */
    static class MediaPlayerAdapter extends MediaPlayer {
        private VideoPlayer videoPlayer;

        @Override
        public void play(String fileName) {
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
