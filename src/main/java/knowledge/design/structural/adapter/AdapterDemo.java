package knowledge.design.structural.adapter;

/**
 * 适配器模式：将已有的接口转换为需要的接口，使不兼容的接口可以一起工作
 * 主要解决：
 * 1.系统需要使用现有的类，而此类不符合系统的需要
 * 2.需要建立一个可以重复使用的类，用于一些彼此关系不大的类，并易于扩展，以便于面对将来会出现的类
 * 3.需要一个统一的输出接口，但是输入类型却不可预知
 * 使用场景：
 * 使用实例：
 * 1.Java IO：Reader（Target），InputStream（源角色），InputStreamReader（适配器角色）
 * 2.JDBC
 * 3.Spring AOP 的通知 BeforeAdvice AfterAdvice AfterReturningAdvice ThrowsAdvice 借助 AdvisorAdapter 适配器实现功能
 * <p>
 * 分类：
 * 1.类适配器模式
 * 2.对象适配器模式
 * 3.接口适配器模式 (缺省适配器模式)
 * <p>
 * 角色：
 * 抽象目标角色 Target
 * 适配器角色 Adapter
 * 被适配角色 Adaptee
 * <p>
 * 关键代码：适配器继承或依赖源角色，继承或实现目标角色
 * 优点：符合单一责任原则，开闭原则
 * <p>
 * Adapter：https://refactoringguru.cn/design-patterns/adapter
 * 掘金：https://juejin.cn/post/6844903682136342541
 * JAVA与模式：https://www.cnblogs.com/java-my-life/archive/2012/04/13/2442795.html
 * 菜鸟教程：https://www.runoob.com/design-pattern/adapter-pattern.html
 *
 * @author Arsenal
 * created on 2020/9/26 2:51
 */
public class AdapterDemo {

    /**
     * 案例：
     * 1.AudioPlayer 可以播放 MP3
     * 2.AdvancedMediaPlayer 可以播放 VLC 或 MP4
     * 让 AudioPlayer 可以播放其他格式的文件
     */
    public static void main(String[] args) {
        AudioPlayer audioPlayer = new MediaAdapter();

        audioPlayer.play("beyond the horizon.mp3");
        audioPlayer.play("alone.mp4");
        audioPlayer.play("far far away.vlc");
        audioPlayer.play("mind me.avi");
    }

    static abstract class AdvancedMediaPlayer {
        void playVlc(String fileName) {
        }

        ;

        void playMp4(String fileName) {
        }

        ;
    }

    /**
     * 目标角色
     */
    static class VlcPlayer extends AdvancedMediaPlayer {
        @Override
        public void playVlc(String fileName) {
            System.out.println("Playing vlc file. Name: " + fileName);
        }
    }

    /**
     * 源角色
     */
    static class Mp4Player extends AdvancedMediaPlayer {
        @Override
        public void playMp4(String fileName) {
            System.out.println("Playing mp4 file. Name: " + fileName);
        }
    }

    /**
     * 目标角色
     */
    static class AudioPlayer {
        public void play(String fileName) {
            // 播放 mp3 音乐文件的内置支持
            System.out.println("Playing mp3 file. Name: " + fileName);
        }
    }

    /**
     * 适配器角色
     */
    static class MediaAdapter extends AudioPlayer {
        /**
         * 接口适配器模式
         */
        private AdvancedMediaPlayer advancedMediaPlayer;

        @Override
        public void play(String fileName) {
            if (fileName.toLowerCase().endsWith("vlc")) {
                advancedMediaPlayer = new VlcPlayer();
                advancedMediaPlayer.playVlc(fileName);
            } else if (fileName.toLowerCase().endsWith("mp4")) {
                advancedMediaPlayer = new Mp4Player();
                advancedMediaPlayer.playMp4(fileName);
            } else if (fileName.toLowerCase().endsWith("mp3")) {
                super.play(fileName);
            } else {
                System.out.println("Invalid media. " + fileName.split("\\.")[1] + " format not supported");
            }
        }
    }

}
