package knowledge.design.structural.facade;

import lombok.Getter;
import org.junit.jupiter.api.Test;

import java.io.File;

/**
 * 外观模式：又称门面模式，给复杂的子系统、程序、类库、框架提供一组高层接口，使其更容易被使用
 * 使用场景：
 * 1.同定义
 * 2.构建分层结构系统
 * 使用实例：
 * 1.各种工具类
 * 2.给第三方使用的接口
 * 3.MVC
 * <p>
 * 角色：
 * 外观角色 Facade
 * 附加外观角色 AdditionalFacade：可选
 * 子系统角色 SubSystem
 * <p>
 * 优点：符合迪米特法则
 * 缺点：不符合开闭原则
 * 优化：引入 AbstractFacade 和 ConcreteFacade 的概念，可以一定程度上解决不符合开闭原则的问题
 * <p>
 * Facade：https://refactoringguru.cn/design-patterns/facade
 * Java设计模式：http://c.biancheng.net/view/1369.html
 * 菜鸟教程：https://www.runoob.com/design-pattern/facade-pattern.html
 *
 * @author Arsenal
 * created on 2020/9/26 2:51
 */
public class FacadeDemo {

    /**
     * 复杂视频转换
     * https://refactoring.guru/design-patterns/facade/java/example
     */
    @Test
    public void testFacade() {
        VideoConversionFacade converter = new VideoConversionFacade();
        File mp4Video = converter.convertVideo("youtube-video.ogg", "mp4");
    }

    /**
     * SubSystem
     */
    @Getter
    static class VideoFile {
        private final String name;
        private final String codeType;

        public VideoFile(String name) {
            this.name = name;
            this.codeType = name.substring(name.indexOf(".") + 1);
        }
    }

    /**
     * SubSystem
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
     * SubSystem
     */
    static class CodecFactory {
        public static Codec extract(VideoFile file) {
            String type = file.getCodeType();
            if (type.equals("mp4")) {
                System.out.println("CodecFactory: extracting mpeg audio...");
                return new MPEG4CompressionCodec();
            } else {
                System.out.println("CodecFactory: extracting ogg audio...");
                return new OggCompressionCodec();
            }
        }
    }

    /**
     * SubSystem
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
     * SubSystem
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
            if (format.equals("mp4")) {
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
