package jar.jave;

import ws.schild.jave.EncoderException;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.info.AudioInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static l.demo.Demo.DESKTOP;

/**
 * FFmpegCliConverter
 *
 * @author ljh
 * @since 2025/3/24 23:25
 */
public class FFmpegCliConverter {

    public static void main(String[] args) throws EncoderException, IOException, InterruptedException {
        try (Stream<Path> stream = Files.walk(Paths.get(DESKTOP))) {
            stream.filter(p -> Files.isRegularFile(p) && p.toString().matches("(?i).*\\.mp3$"))
                .parallel()
                .forEach(mp3Path -> {
                    convert(mp3Path, buildOutputPath(mp3Path));
                });
        } catch (IOException e) {
            System.err.println("目录遍历异常: " + e.getMessage());
        }
    }

    private static Path buildOutputPath(Path input) {
        return input.resolveSibling(input.getFileName().toString().replaceAll("(?i)\\.mp3$", ".m4a")
        );
    }

    public static void convert(Path input, Path output) {
        try {
            MultimediaObject multimediaObject = new MultimediaObject(input.toFile());
            AudioInfo audioInfo = multimediaObject.getInfo().getAudio();

            // 构建 FFmpeg 命令
            String[] cmd = {
                "ffmpeg",
                "-y",                                               // 覆盖输出文件
                "-i", input.toString(),                             // 输入文件
                "-c:a", "aac",                                      // 使用 aac 编码器
                "-b:a", String.valueOf(audioInfo.getBitRate()),     // 设置比特率
                "-ar", String.valueOf(audioInfo.getSamplingRate()), // 设置采样率
                "-ac", String.valueOf(audioInfo.getChannels()),     // 设置声道数
                "-c:v", "copy",                                     // 复制视频流（如封面图片）
                "-movflags", "+faststart",                          // 优化流媒体播放
                "-map_metadata", "0",                               // 复制所有元数据
                "-map_chapters", "0",                               // 复制所有章节信息
                output.toString()                                   // 输出文件
            };

            ProcessBuilder pb = new ProcessBuilder(cmd).redirectErrorStream(true);

            // 启动进程并处理输出
            Process process = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                throw new IOException("FFmpeg exited with code: " + exitCode);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
