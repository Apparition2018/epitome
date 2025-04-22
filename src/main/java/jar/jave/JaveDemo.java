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

/**
 * <a href="https://github.com/a-schild/jave2">Jave</a>
 * <p><a href="https://www.cnblogs.com/rchao/p/10282144.html">Jave 简单使用</a>
 *
 * @author ljh
 * @since 2021/10/20 9:30
 */
public class JaveDemo extends Demo {

    /**
     * 转换视频格式
     */
    @Test
    public void testConvertFormat() throws EncoderException {
        File source = new File(VIDEO);
        File target = new File(RESOURCES_ABSOLUTE_PATH + "static/public/video/movie.mp4");

        MultimediaObject multimediaObject = new MultimediaObject(source);
        AudioInfo audioInfo = multimediaObject.getInfo().getAudio();
        VideoInfo videoInfo = multimediaObject.getInfo().getVideo();

        // Audio Attributes
        AudioAttributes audio = new AudioAttributes();
        audio.setCodec("libmp3lame");
        audio.setBitRate(audioInfo.getBitRate());
        audio.setChannels(audioInfo.getChannels());
        audio.setSamplingRate(audioInfo.getSamplingRate());

        // Video Attributes
        VideoAttributes video = new VideoAttributes();
        video.setCodec("libx264");
        video.setBitRate(160000);
        video.setFrameRate((int) videoInfo.getFrameRate());
        video.setSize(videoInfo.getSize());

        // Encoding Attributes
        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setOutputFormat("mp4");
        attrs.setAudioAttributes(audio);
        attrs.setVideoAttributes(video);

        // Encode
        Encoder encoder = new Encoder();
        encoder.encode(multimediaObject, target, attrs);
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

        VideoAttributes video = new VideoAttributes();
        video.setCodec(ImgUtil.IMAGE_TYPE_PNG);
        video.setSize(videoInfo.getSize());

        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setOutputFormat("image2");
        // 设置偏移位置，即开始转码位置（3秒）
        attrs.setOffset(3f);
        // 设置转码持续时间（1秒）
        attrs.setDuration(0.01f);
        attrs.setVideoAttributes(video);

        Encoder encoder = new Encoder();
        encoder.encode(multimediaObject, target, attrs);
    }
}
