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
import java.util.ArrayList;
import java.util.List;
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
            stream.filter(p -> Files.isRegularFile(p) && p.toString().matches("(?i).*\\.(mp3|flac|wav)$"))
                .parallel()
                // .forEach(path -> convertToM4a(path, buildOutputPath(path), BitRate.OBR.create()));
                .forEach(path -> convertToM4a(path, buildOutputPath(path), BitRate.CBR.ULTRA));
        }
    }

    private static Path buildOutputPath(Path input) {
        return input.resolveSibling(input.getFileName().toString().replaceAll("(?i)\\.(mp3|flac|wav)$", ".m4a"));
    }

    /**
     * @see <a href="https://trac.ffmpeg.org/wiki/Encode/AAC">Encode/AAC</a>
     */
    public static void convertToM4a(Path source, Path target, BitRate targetBitRate) {
        try {
            MultimediaObject multimediaObject = new MultimediaObject(source.toFile());
            AudioInfo audioInfo = multimediaObject.getInfo().getAudio();
            int bitRate = audioInfo.getBitRate();
            int samplingRate = audioInfo.getSamplingRate();
            int channels = audioInfo.getChannels();
            System.err.printf("bitRate: %s samplingRate: %s channels: %s%n", bitRate, samplingRate, channels);

            // 构建 FFmpeg 命令
            // ①Global Options  -y
            List<String> globalCmd = List.of("ffmpeg", "-y");
            // ②Input Options
            // ③Input File      -i
            List<String> inputFileCmd = List.of("-i", source.toString());
            // ④Output Options  -c:a, -b:a, -q:a,
            List<String> outputOptionsCmd = new ArrayList<>(List.of(
                "-ar", String.valueOf(Math.min(samplingRate, 96000)),   // 采样率
                "-ac", String.valueOf(channels),                        // 声道数
                // "-af", "volume=4dB",                                    // 音量提升4dB
                "-c:v", "copy",                                         // 视频处理方式：复制（视频或封面）
                "-movflags", "+faststart",                              // 文件结构优化：将元数据从文件的末尾移动到开头，使得无需下载整个文件就能开始播放
                "-map_metadata", "0",                                   // 元数据映射：复制输入文件（索引0）中的所有全局元数据
                "-map_chapters", "0"                                    // 章节信息映射：复制输入文件（索引 0）中的所有章节信息
            ));
            if (targetBitRate instanceof BitRate.CBR || targetBitRate instanceof BitRate.OBR) {
                outputOptionsCmd.add("-b:a");           // 恒定比特率 (CBR)
                if (targetBitRate instanceof BitRate.CBR) {
                    outputOptionsCmd.add(String.valueOf(((BitRate.CBR) targetBitRate).getM4a()));
                } else {
                    outputOptionsCmd.add(String.valueOf(bitRate == -1 ? BitRate.CBR.HIGH.getM4a() : bitRate));
                }
            } else if (targetBitRate instanceof BitRate.VBR) {
                outputOptionsCmd.add("-q:a");           // 可变比特率 (VBR)
                outputOptionsCmd.add(String.valueOf(((BitRate.VBR) targetBitRate).getQuality()));
            }
            // ⑤Output File
            List<String> outFileCmd = List.of(target.toString());

            String[] cmd = Stream.of(globalCmd, inputFileCmd, outputOptionsCmd, outFileCmd).flatMap(List::stream).toArray(String[]::new);
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
