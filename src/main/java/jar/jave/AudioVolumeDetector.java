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
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static l.demo.Demo.DESKTOP;

/**
 * AudioVolumeDetector
 * 音频文件音量检测器
 *
 * @author ljh
 * @since 2025/3/24 23:25
 */
public class AudioVolumeDetector {

    public static void main(String[] args) throws EncoderException, IOException, InterruptedException {
        try (Stream<Path> stream = Files.walk(Paths.get(DESKTOP))) {
            List<Path> audioFiles = stream
                .filter(p -> Files.isRegularFile(p) && p.toString().matches("(?i).*\\.(mp3|flac|wav|m4a)$"))
                .toList();

            System.out.println("=== 音频文件音量检测报告 ===");
            System.out.printf("检测到 %d 个音频文件%n%n", audioFiles.size());

            // 串行处理以便顺序输出
            audioFiles.forEach(AudioVolumeDetector::detectVolume);
        }
    }

    /**
     * 检测音频文件音量信息
     */
    public static void detectVolume(Path source) {
        try {
            // 获取基础音频信息
            MultimediaObject multimediaObject = new MultimediaObject(source.toFile());
            AudioInfo audioInfo = multimediaObject.getInfo().getAudio();
            int bitRate = audioInfo.getBitRate();
            int samplingRate = audioInfo.getSamplingRate();
            int channels = audioInfo.getChannels();
            long duration = multimediaObject.getInfo().getDuration() / 1000; // 转换为秒

            System.out.printf("文件: %s%n", source.getFileName());
            System.out.printf("  格式: %s, 时长: %d秒, 采样率: %d Hz, 声道: %d, 比特率: %d kbps%n",
                getFileExtension(source), duration, samplingRate, channels, bitRate / 1000);

            // 构建 FFmpeg 音量检测命令
            List<String> cmd = List.of(
                "ffmpeg",
                "-i", source.toString(),
                "-af", "volumedetect",
                "-f", "null",
                "-"
            );

            ProcessBuilder pb = new ProcessBuilder(cmd);
            // 重定向错误流到输入流（音量信息在错误流中输出）
            pb.redirectErrorStream(true);

            Process process = pb.start();
            StringBuilder output = new StringBuilder();

            // 读取输出
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line).append("\n");
                }
            }

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                System.out.printf("  错误: FFmpeg 退出代码: %d%n", exitCode);
                return;
            }

            // 解析音量信息
            parseAndPrintVolumeInfo(output.toString());
            System.out.println();

        } catch (Exception e) {
            System.out.printf("文件: %s - 处理失败: %s%n", source.getFileName(), e.getMessage());
        }
    }

    /**
     * 解析并打印音量信息
     */
    private static void parseAndPrintVolumeInfo(String ffmpegOutput) {
        // 正则表达式匹配音量信息
        Pattern meanPattern = Pattern.compile("mean_volume:\\s*(-?\\d+\\.?\\d*)\\s*dB");
        Pattern maxPattern = Pattern.compile("max_volume:\\s*(-?\\d+\\.?\\d*)\\s*dB");

        Matcher meanMatcher = meanPattern.matcher(ffmpegOutput);
        Matcher maxMatcher = maxPattern.matcher(ffmpegOutput);

        if (meanMatcher.find() && maxMatcher.find()) {
            double meanVolume = Double.parseDouble(meanMatcher.group(1));
            double maxVolume = Double.parseDouble(maxMatcher.group(1));

            System.out.printf("  平均音量: %.1f dB%n", meanVolume);
            System.out.printf("  峰值音量: %.1f dB%n", maxVolume);

            // 音量等级评估
            System.out.printf("  音量评估: %s%n", evaluateVolumeLevel(meanVolume, maxVolume));
        } else {
            System.out.println("  未检测到音量信息");
        }
    }

    /**
     * 评估音量等级
     */
    private static String evaluateVolumeLevel(double meanVolume, double maxVolume) {
        StringBuilder evaluation = new StringBuilder();

        // 平均音量评估
        if (meanVolume > -9) {
            evaluation.append("平均音量过高(可能爆音)");
        } else if (meanVolume > -16) {
            evaluation.append("平均音量良好");
        } else if (meanVolume > -23) {
            evaluation.append("平均音量偏低");
        } else {
            evaluation.append("平均音量过低");
        }

        evaluation.append(" | ");

        // 峰值音量评估
        if (maxVolume > -3) {
            evaluation.append("峰值接近极限(有爆音风险)");
        } else if (maxVolume > -6) {
            evaluation.append("峰值音量较高");
        } else {
            evaluation.append("峰值音量正常");
        }

        return evaluation.toString();
    }

    /**
     * 获取文件扩展名
     */
    private static String getFileExtension(Path path) {
        String fileName = path.getFileName().toString();
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1).toUpperCase();
    }

    /**
     * 批量对比两个文件的音量差异
     */
    public static void compareVolume(Path file1, Path file2) {
        System.out.println("=== 音频文件音量对比 ===");
        System.out.printf("对比文件: %s 和 %s%n%n", file1.getFileName(), file2.getFileName());

        // 检测第一个文件
        System.out.println("文件1信息:");
        detectVolume(file1);

        // 检测第二个文件
        System.out.println("文件2信息:");
        detectVolume(file2);
    }

    /**
     * 使用 EBU R128 标准检测响度（更专业的音量检测）
     */
    public static void detectLoudness(Path source) {
        try {
            List<String> cmd = List.of(
                "ffmpeg",
                "-i", source.toString(),
                "-af", "ebur128=peak=true",
                "-f", "null",
                "-"
            );

            ProcessBuilder pb = new ProcessBuilder(cmd);
            pb.redirectErrorStream(true);

            Process process = pb.start();
            StringBuilder output = new StringBuilder();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line).append("\n");
                }
            }

            int exitCode = process.waitFor();
            if (exitCode == 0) {
                parseAndPrintLoudnessInfo(output.toString(), source.getFileName().toString());
            }

        } catch (Exception e) {
            System.out.printf("文件: %s - 响度检测失败: %s%n", source.getFileName(), e.getMessage());
        }
    }

    /**
     * 解析并打印响度信息（EBU R128 标准）
     */
    private static void parseAndPrintLoudnessInfo(String ffmpegOutput, String fileName) {
        Pattern iPattern = Pattern.compile("I:\\s*(-?\\d+\\.?\\d*)\\s*LUFS");
        Pattern lraPattern = Pattern.compile("LRA:\\s*(\\d+\\.?\\d*)\\s*LU");

        Matcher iMatcher = iPattern.matcher(ffmpegOutput);
        Matcher lraMatcher = lraPattern.matcher(ffmpegOutput);

        if (iMatcher.find() && lraMatcher.find()) {
            double integratedLoudness = Double.parseDouble(iMatcher.group(1));
            double loudnessRange = Double.parseDouble(lraMatcher.group(1));

            System.out.printf("文件: %s - EBU R128 响度分析%n", fileName);
            System.out.printf("  综合响度: %.1f LUFS%n", integratedLoudness);
            System.out.printf("  响度范围: %.1f LU%n", loudnessRange);
            System.out.printf("  标准符合: %s%n", evaluateLoudnessStandard(integratedLoudness));
        }
    }

    /**
     * 评估响度标准符合性
     */
    private static String evaluateLoudnessStandard(double loudness) {
        if (loudness >= -23 && loudness <= -22) {
            return "符合广播标准(-23 LUFS)";
        } else if (loudness >= -15 && loudness <= -14) {
            return "符合流媒体标准(-14 LUFS)";
        } else if (loudness > -14) {
            return "响度过高(可能被平台压缩)";
        } else {
            return "响度偏低";
        }
    }
}
