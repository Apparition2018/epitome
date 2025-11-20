package jar.jave;

import cn.hutool.core.img.ImgUtil;
import l.demo.Demo;
import org.junit.jupiter.api.Test;
import ws.schild.jave.Encoder;
import ws.schild.jave.EncoderException;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.encode.AudioAttributes;
import ws.schild.jave.encode.EncodingAttributes;
import ws.schild.jave.encode.VideoAttributes;
import ws.schild.jave.info.AudioInfo;
import ws.schild.jave.info.VideoInfo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * <a href="https://github.com/a-schild/jave2">Jave</a>
 * <p><a href="https://www.cnblogs.com/rchao/p/10282144.html">Jave 简单使用</a>
 *
 * @author ljh
 * @since 2021/10/20 9:30
 */
public class JaveDemo extends Demo {

    /**
     * flac/mp3 → m4a
     */
    @Test
    public void testConvertToM4a() throws IOException {
        try (Stream<Path> stream = Files.walk(Paths.get(DESKTOP))) {
            stream.filter(p -> Files.isRegularFile(p) && p.toString().matches("(?i).*\\.(mp3|flac)$"))
                .parallel()
                .forEach(path -> convertToM4a(path.toFile(), buildOutputFile(path), BitRate.VBR.create(2f)));
        }
    }

    private static File buildOutputFile(Path input) {
        return input.resolveSibling(input.getFileName().toString().replaceAll("(?i)\\.(mp3|flac)$", ".m4a")).toFile();
    }

    private static void convertToM4a(File source, File target, BitRate targetBitRate) {
        try {
            MultimediaObject multimediaObject = new MultimediaObject(source);
            AudioInfo audioInfo = multimediaObject.getInfo().getAudio();

            // Audio Attributes
            AudioAttributes audioAttrs = new AudioAttributes();
            audioAttrs.setCodec("aac");
            audioAttrs.setChannels(audioInfo.getChannels());
            audioAttrs.setSamplingRate(audioInfo.getSamplingRate());
            if (targetBitRate instanceof BitRate.CBR) {
                audioAttrs.setBitRate(((BitRate.CBR) targetBitRate).getM4a());
            } else if (targetBitRate instanceof BitRate.VBR) {
                audioAttrs.setBitRate(0);
                audioAttrs.setQuality((int) ((BitRate.VBR) targetBitRate).getQuality());
            }

            // Encoding Attributes
            EncodingAttributes encodingAttrs = new EncodingAttributes();
            encodingAttrs.setOutputFormat("mp4");
            encodingAttrs.setAudioAttributes(audioAttrs);

            // Encoder
            Encoder encoder = new Encoder();
            encoder.encode(multimediaObject, target, encodingAttrs);
        } catch (EncoderException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 转换视频格式
     */
    @Test
    public void testConvertToVideo() throws EncoderException {
        File source = new File(VIDEO);
        File target = new File(RESOURCES_ABSOLUTE_PATH + "static/public/video/movie.mp4");

        MultimediaObject multimediaObject = new MultimediaObject(source);
        AudioInfo audioInfo = multimediaObject.getInfo().getAudio();
        VideoInfo videoInfo = multimediaObject.getInfo().getVideo();

        // Audio Attributes
        AudioAttributes audioAttrs = new AudioAttributes();
        audioAttrs.setCodec("libmp3lame");
        audioAttrs.setBitRate(audioInfo.getBitRate());
        audioAttrs.setChannels(audioInfo.getChannels());
        audioAttrs.setSamplingRate(audioInfo.getSamplingRate());

        // Video Attributes
        VideoAttributes videoAttrs = new VideoAttributes();
        videoAttrs.setCodec("libx264");
        videoAttrs.setBitRate(160000);
        videoAttrs.setFrameRate((int) videoInfo.getFrameRate());
        videoAttrs.setSize(videoInfo.getSize());

        // Encoding Attributes
        EncodingAttributes encodingAttrs = new EncodingAttributes();
        encodingAttrs.setOutputFormat("mp4");
        encodingAttrs.setAudioAttributes(audioAttrs);
        encodingAttrs.setVideoAttributes(videoAttrs);

        // Encoder
        Encoder encoder = new Encoder();
        encoder.encode(multimediaObject, target, encodingAttrs);
    }

    /**
     * 截图
     */
    @Test
    public void testCapture() throws EncoderException {
        File source = new File(VIDEO);
        File target = new File(RESOURCES_ABSOLUTE_PATH + "static/public/img/movie.png");

        MultimediaObject multimediaObject = new MultimediaObject(source);
        VideoInfo videoInfo = multimediaObject.getInfo().getVideo();

        // Video Attributes
        VideoAttributes videoAttrs = new VideoAttributes();
        videoAttrs.setCodec(ImgUtil.IMAGE_TYPE_PNG);
        videoAttrs.setSize(videoInfo.getSize());

        // Encoding Attributes
        EncodingAttributes encodingAttrs = new EncodingAttributes();
        encodingAttrs.setOutputFormat("image2");
        // 设置偏移位置，即开始转码位置（3秒）
        encodingAttrs.setOffset(3f);
        // 设置转码持续时间（1秒）
        encodingAttrs.setDuration(0.01f);
        encodingAttrs.setVideoAttributes(videoAttrs);

        // Encoder
        Encoder encoder = new Encoder();
        encoder.encode(multimediaObject, target, encodingAttrs);
    }
}
