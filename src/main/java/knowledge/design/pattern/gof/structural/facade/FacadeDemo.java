package knowledge.design.pattern.gof.structural.facade;

import lombok.Getter;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.support.JdbcUtils;

import java.io.File;

/**
 * 外观模式：又称门面模式，给复杂的子系统、程序、类库、框架提供一组高层接口，使其更容易被使用
 * <p>使用场景：
 * <pre>
 * 1 同定义
 * 2 构建分层结构系统
 * </pre>
 * 使用实例：
 * <pre>
 * 1 slf4j
 * 2 各种工具类 {@link JdbcUtils}
 * 3 给第三方使用的接口
 * 4 MVC
 * </pre>
 * 角色：
 * <pre>
 * 外观 Facade
 * 附加外观 AdditionalFacade (可选)
 * 子系统 Subsystem
 * </pre>
 * 优点：符合迪米特法则<br/>
 * 缺点：违反开闭原则
 * <p>优化：
 * <pre>
 * 1 引入 AbstractFacade 和 ConcreteFacade 的概念，可以一定程度上解决违反开闭原则的缺点
 * 2 由于 Facade 持有多个 Subsystem 的引用，可以把 Facade 设计为 Singleton，控制内存开销
 * </pre>
 * 参考：
 * <pre>
 * <a href="https://refactoringguru.cn/design-patterns/facade">Facade</a>
 * <a href="http://c.biancheng.net/view/1369.html">Java设计模式</a>
 * </pre>
 *
 * @author ljh
 * @since 2020/9/26 2:51
 */
public class FacadeDemo {

    /**
     * <a href="https://refactoring.guru/design-patterns/facade/java/example">复杂视频转换</a>
     */
    @Test
    public void testFacade() {
        VideoConversionFacade converter = new VideoConversionFacade();
        File mp4Video = converter.convertVideo("youtube-video.ogg", "mp4");
    }

    /**
     * Subsystem
     */
    @Getter
    static class VideoFile {
        private final String name;
        private final String codeType;

        public VideoFile(String name) {
            this.name = name;
            this.codeType = name.substring(name.indexOf('.') + 1);
        }
    }

    /**
     * Subsystem
     */
    interface Codec {
    }

    static class MPEG4CompressionCodec implements Codec {
        public String type = "mp4";
    }

    static class OggCompressionCodec implements Codec {
        public String type = "ogg";
    }

    /**
     * Subsystem
     */
    static class CodecFactory {
        public static Codec extract(VideoFile file) {
            String type = file.getCodeType();
            if ("mp4".equals(type)) {
                System.out.println("CodecFactory: extracting mpeg audio...");
                return new MPEG4CompressionCodec();
            } else {
                System.out.println("CodecFactory: extracting ogg audio...");
                return new OggCompressionCodec();
            }
        }
    }

    /**
     * Subsystem
     */
    static class BitrateReader {
        public static VideoFile read(VideoFile file, Codec codec) {
            System.out.println("BitrateReader: reading file...");
            return file;
        }

        public static VideoFile convert(VideoFile buffer, Codec codec) {
            System.out.println("BitrateReader: writing file...");
            return buffer;
        }
    }

    /**
     * Subsystem
     */
    static class AudioMixer {
        public File fix(VideoFile result) {
            System.out.println("AudioMixer: fixing audio...");
            return new File("tmp");
        }
    }

    /**
     * Facade
     */
    static class VideoConversionFacade {
        public File convertVideo(String fileName, String format) {
            System.out.println("VideoConversionFacade: conversion started.");
            VideoFile file = new VideoFile(fileName);
            Codec sourceCodec = CodecFactory.extract(file);
            Codec destinationCodec;
            if ("mp4".equals(format)) {
                destinationCodec = new OggCompressionCodec();
            } else {
                destinationCodec = new MPEG4CompressionCodec();
            }
            VideoFile buffer = BitrateReader.read(file, sourceCodec);
            VideoFile intermediateResult = BitrateReader.convert(buffer, destinationCodec);
            File result = new AudioMixer().fix(intermediateResult);
            System.out.println("VideoConversionFacade: conversion completed.");
            return result;
        }
    }
}
