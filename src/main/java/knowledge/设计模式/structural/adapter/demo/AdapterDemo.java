package knowledge.设计模式.structural.adapter.demo;

/**
 * Target: 目标角色
 * Adaptee: 源角色
 * Adapter: 适配器角色
 * 适配器模式：将已有的接口转换为需要的接口，使得原本由于接口不兼容而不能一起工作的那些类可以一起工作
 * <p>
 * http://www.cnblogs.com/java-my-life/archive/2012/04/13/2442795.html
 * https://www.runoob.com/design-pattern/adapter-pattern.html
 *
 * @author Arsenal
 * created on 2020/9/26 2:51
 */
public class AdapterDemo {
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

    // 目标角色
    private static class VlcPlayer implements AdvancedMediaPlayer {

        @Override
        public void playVlc(String fileName) {
            System.out.println("Playing vlc file. Name: " + fileName);
        }

        @Override
        public void playMp4(String fileName) {
            // 什么也不做
        }
    }

    // 目标角色
    private static class Mp4Player implements AdvancedMediaPlayer {

        @Override
        public void playVlc(String fileName) {
            // 什么也不做
        }

        @Override
        public void playMp4(String fileName) {
            System.out.println("Playing mp4 file. Name: " + fileName);
        }
    }

    interface MediaPlayer {
        void play(String audioType, String fileName);
    }

    // 适配器角色
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

    // 源角色
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

}
