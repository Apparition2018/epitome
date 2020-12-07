package knowledge.design.structural.adapter.demo;

/**
 * 适配器模式：将已有的接口转换为需要的接口，使得原本由于接口不兼容而不能一起工作的那些类可以一起工作
 * 适用场合：
 * 1.系统需要使用现有的类，而此类不符合系统的需要
 * 2.需要建立一个可以重复使用的类，用于一些彼此关系不大的类，并易于扩展，以便于面对将来会出现的类
 * 3.需要一个统一的输出接口，但是输入类型却不可预知
 * 使用场景：
 * 1.IO
 * 2.JDBC
 * 3.Spring AOP @BeforeAdvice @AfterAdvice @ThrowsAdvice 借助 AdvisorAdapter 适配器实现功能
 * 优点：提高了系统现有类的复用
 * 缺点：过多的使用适配器，会让系统非常零乱，不易整体进行把握，应优先考虑直接对功能进行重构
 * 角色：
 * 目标角色 Target
 * 源角色 Adaptee
 * 适配器角色 Adapter
 * <p>
 * 适配器模式 | 菜鸟教程：https://www.runoob.com/design-pattern/adapter-pattern.html
 * 浅谈适配器设计模式 - 知乎：https://zhuanlan.zhihu.com/p/56518978
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
        AudioPlayer audioPlayer = new AudioPlayer();

        audioPlayer.play("mp3", "beyond the horizon.mp3");
        audioPlayer.play("mp4", "alone.mp4");
        audioPlayer.play("vlc", "far far away.vlc");
        audioPlayer.play("avi", "mind me.avi");
    }

    interface AdvancedMediaPlayer {
        void playVlc(String fileName);

        void playMp4(String fileName);
    }

    /**
     * 目标角色
     */
    private static class VlcPlayer implements AdvancedMediaPlayer {

        @Override
        public void playVlc(String fileName) {
            System.out.println("Playing vlc file. Name: " + fileName);
        }

        @Override
        public void playMp4(String fileName) {
        }
    }

    /**
     * 源角色
     */
    private static class Mp4Player implements AdvancedMediaPlayer {

        @Override
        public void playVlc(String fileName) {
        }

        @Override
        public void playMp4(String fileName) {
            System.out.println("Playing mp4 file. Name: " + fileName);
        }
    }

    interface MediaPlayer {
        void play(String audioType, String fileName);
    }

    /**
     * 目标角色
     */
    private static class AudioPlayer implements MediaPlayer {

        private MediaAdapter mediaAdapter;

        @Override
        public void play(String audioType, String fileName) {
            // 播放 mp3 音乐文件的内置支持
            if (audioType.equalsIgnoreCase("mp3")) {
                System.out.println("Playing mp3 file. Name: " + fileName);
            }
            // mediaAdapter 提供了播放其他文件格式的支持
            else if (audioType.equalsIgnoreCase("vlc") || audioType.equalsIgnoreCase("mp4")) {
                mediaAdapter = new MediaAdapter(audioType);
                mediaAdapter.play(audioType, fileName);
            } else {
                System.out.println("Invalid media. " + audioType + " format not supported");
            }
        }
    }

    /**
     * 适配器角色
     */
    private static class MediaAdapter implements MediaPlayer {

        private AdvancedMediaPlayer advancedMediaPlayer;

        MediaAdapter(String audioType) {
            if (audioType.equalsIgnoreCase("vlc")) {
                advancedMediaPlayer = new VlcPlayer();
            } else if (audioType.equalsIgnoreCase("mp4")) {
                advancedMediaPlayer = new Mp4Player();
            }
        }

        @Override
        public void play(String audioType, String fileName) {
            if (audioType.equalsIgnoreCase("vlc")) {
                advancedMediaPlayer.playVlc(fileName);
            } else if (audioType.equalsIgnoreCase("mp4")) {
                advancedMediaPlayer.playMp4(fileName);
            }
        }
    }

}
