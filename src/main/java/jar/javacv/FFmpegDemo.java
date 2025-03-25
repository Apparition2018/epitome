package jar.javacv;

import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static l.demo.Demo.DESKTOP;

/**
 * FFmpegFrame
 *
 * @author ljh
 * @since 2024/5/29 0:14
 */
public class FFmpegDemo {

    public static void main(String[] args) throws IOException {
        try (Stream<Path> stream = Files.walk(Paths.get(DESKTOP))) {
            stream.filter(p -> Files.isRegularFile(p) && p.toString().matches("(?i).*\\.mp3$"))
                .parallel()
                .forEach(mp3Path -> mp3ToM4a(mp3Path, buildOutputPath(mp3Path)));
        } catch (IOException e) {
            System.err.println("目录遍历异常: " + e.getMessage());
        }
    }

    private static void mp3ToM4a(Path mp3Path, Path m4aPath) {
        try (FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(mp3Path.toFile())) {
            grabber.start();

            try (FFmpegFrameRecorder recorder = buildRecorder(grabber, m4aPath)) {
                processAudio(grabber, recorder);
                System.out.println("转换成功: " + m4aPath);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Path buildOutputPath(Path input) {
        return input.resolveSibling(input.getFileName().toString().replaceAll("(?i)\\.mp3$", ".m4a")
        );
    }

    private static FFmpegFrameRecorder buildRecorder(FFmpegFrameGrabber grabber, Path output) {
        FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(output.toFile(),
            // mp3 → m4a，无需处理视频流
            // grabber.getImageWidth(), grabber.getImageHeight(),
            grabber.getAudioChannels());

        // mp3 → m4a，无需处理视频
        recorder.setVideoCodec(avcodec.AV_CODEC_ID_NONE);
        // 设置音频编码为 AAC
        recorder.setAudioCodec(avcodec.AV_CODEC_ID_AAC);
        // 设置音频比特率
        recorder.setAudioBitrate(grabber.getAudioBitrate());
        // 设置采样率
        recorder.setSampleRate(grabber.getSampleRate());
        // 设置声道数
        recorder.setAudioChannels(grabber.getAudioChannels());
        // 设置输出格式为 mp4
        recorder.setFormat("mp4");
        // 流媒体优化
        recorder.setOption("movflags", "+faststart");
        // 高质量编码
        recorder.setOption("aac_coder", "libfdk_aac");
        // 设置元数据
        recorder.setMetadata(grabber.getMetadata());

        return recorder;
    }

    private static void processAudio(FFmpegFrameGrabber grabber, FFmpegFrameRecorder recorder) throws Exception {
        recorder.start();
        Frame frame;
        // 使用无阻塞grab方法
        while ((frame = grabber.grab()) != null) {
            if (frame.image != null) {
                // mp3 → m4a，无需处理视频
            } else if (frame.samples != null) {
                recorder.record(frame);
            }
        }
    }
}
