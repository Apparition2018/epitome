package jar.hutool;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.extra.pinyin.PinyinUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import l.demo.Demo;
import org.junit.Test;

import java.awt.*;
import java.io.File;
import java.util.concurrent.ExecutorService;

/**
 * Hutool
 *
 * @author Arsenal
 * created on 2020/10/24 21:53
 */
public class HutoolDemo extends Demo {

    /**
     * PinyinUtil   拼音工具
     * https://hutool.cn/docs/#/extra/%E6%8B%BC%E9%9F%B3/%E6%8B%BC%E9%9F%B3%E5%B7%A5%E5%85%B7-PinyinUtil
     */
    @Test
    public void testPinYin() {
        p(PinyinUtil.getPinyin("梁杰辉"));             // liang jie hui
        p(PinyinUtil.getPinyin("梁杰辉", "-"));        // liang-jie-hui
        p(PinyinUtil.getFirstLetter("梁杰辉", "-"));   // l-j-h
    }

    /**
     * QrCodeUtil   二维码工具
     * https://hutool.cn/docs/#/extra/%E4%BA%8C%E7%BB%B4%E7%A0%81%E5%B7%A5%E5%85%B7-QrCodeUtil
     */
    @Test
    public void testQRCode() {
        // 自定义参数
        QrConfig config = new QrConfig(300, 300);
        // 设置边距，既二维码和背景之间的边距
        config.setMargin(2);
        // 设置前景色，既二维码颜色
        config.setForeColor(Color.BLACK);
        // 设置背景色
        config.setBackColor(Color.WHITE);
        // 设置 logo
        config.setImg(NOHARA_SINNOSUKE);

        // 生成指定 content 对应的二维码到文件，宽和高都是 300 像素
        QrCodeUtil.generate("999999999999", config, FileUtil.file(USER_DIR + File.separator + DEMO_PATH + "QRCode.png"));

        // 识别二维码
        p(QrCodeUtil.decode(FileUtil.file(USER_DIR + File.separator + DEMO_PATH + "QRCode.png")));
    }

    /**
     * ThreadUtil   线程工具
     * https://hutool.cn/docs/#/core/%E7%BA%BF%E7%A8%8B%E5%92%8C%E5%B9%B6%E5%8F%91/%E7%BA%BF%E7%A8%8B%E5%B7%A5%E5%85%B7-ThreadUtil?id=%e7%ba%bf%e7%a8%8b%e5%b7%a5%e5%85%b7-threadutil
     */
    @Test
    public void testThread() {
        // 创建 核心线程数为2，最大线程数为5，等待工作队列容量为1024 的线程池
        ExecutorService pool = ThreadUtil.newExecutor(2, 5, 1024);
//
//        for (int i = 0; i < 10; i++) {
//            final int num = i + 1;
//            pool.execute(() -> {
//                Thread t = Thread.currentThread();
//                p(t.getName() + "：正在运行任务 " + num + " ...");
//                ThreadUtil.sleep(1000);
//                p(t.getName() + "：运行任务 " + num + " 完毕！");
//            });
//        }

        for (int i = 0; i < 10; i++) {
            final int num = i + 1;
            ThreadUtil.execute(() -> {
                Thread t = Thread.currentThread();
                p(t.getName() + "：正在运行任务 " + num + " ...");
                ThreadUtil.sleep(1000);
                p(t.getName() + "：运行任务 " + num + " 完毕！");
            });
        }
    }
}
