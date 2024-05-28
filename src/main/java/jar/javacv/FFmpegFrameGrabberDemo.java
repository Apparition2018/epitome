package jar.javacv;

import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.javacv.*;

/**
 * FFmpegFrameGrabber
 *
 * @author ljh
 * @since 2024/5/29 0:14
 */
public class FFmpegFrameGrabberDemo {

    private static final String fileName = "陈小春 - 啼笑姻缘";
    public static void main(String[] args) {
        String inputFile = "C:\\Users\\Administrator\\Desktop\\" + fileName + ".mp3";
        String outputFile = "C:\\Users\\Administrator\\Desktop\\" + fileName + ".m4a";
        convertAudio(inputFile, outputFile, 320);
    }

    public static void convertAudio(String inputFile, String outputFile, int bitrate) {
        try (FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(inputFile);
             FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(outputFile, grabber.getImageWidth(), grabber.getImageHeight(), grabber.getAudioChannels())) {

            grabber.start();
            // 不需要视频编码，因为我们只处理音频
            recorder.setVideoCodec(avcodec.AV_CODEC_ID_NONE);
            // 设置音频编码为 AAC
            recorder.setAudioCodec(avcodec.AV_CODEC_ID_AAC);
            // 设置音频比特率
            recorder.setAudioBitrate(grabber.getAudioBitrate());
            // 设置采样率与输入相同
            recorder.setSampleRate(grabber.getSampleRate());
            // 设置声道数与输入相同
            recorder.setAudioChannels(grabber.getAudioChannels());
            // 设置输出格式为 m4a
            recorder.setFormat("m4a");
            // 如果可用，使用 libfdk_aac 编码器（可能需要额外配置或依赖）
            recorder.setOption("aac_coder", "libfdk_aac");
            // 如果需要，启用实验性功能
            // recorder.setOption("strict", "experimental");
            recorder.start();

            Frame frame;
            while ((frame = grabber.grabFrame()) != null) {
                if (frame.image != null) {
                    // 这里不处理视频帧，因为我们只处理音频
                } else if (frame.samples != null) {
                    recorder.record(frame);
                }
            }

            recorder.stop();
            grabber.stop();
            System.out.println("MP3 to M4A conversion completed.");
        } catch (FrameGrabber.Exception | FrameRecorder.Exception e) {
            throw new RuntimeException(e);
        }
    }
}
