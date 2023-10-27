package jar.hutool.cache;

import cn.hutool.captcha.AbstractCaptcha;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.generator.CodeGenerator;
import cn.hutool.captcha.generator.MathGenerator;
import cn.hutool.captcha.generator.RandomGenerator;
import l.demo.Demo;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * <a href="https://hutool.cn/docs/#/captcha/概述">Captcha</a>    验证码
 * <pre>
 * <a href="https://apidoc.gitee.com/dromara/hutool/cn/hutool/captcha/ICaptcha.html">ICaptcha api</a>
 * <a href="https://apidoc.gitee.com/dromara/hutool/cn/hutool/captcha/CaptchaUtil.html">CaptchaUtil api</a>
 * <a href="https://apidoc.gitee.com/dromara/hutool/cn/hutool/captcha/generator/RandomGenerator.html">RandomGenerator api</a>
 * </pre>
 *
 * @author ljh
 * @since 2020/11/21 17:37
 */
public class CaptchaDemo extends Demo {

    private static final int WIDTH = 80;
    private static final int HEIGHT = 30;
    private static final int CODE_COUNT = 4;
    private static final int Interference_COUNT = 50;

    public static void main(String[] args) {
        // 直线干扰
        CreateCaptcha(CaptchaUtil.createLineCaptcha(WIDTH, HEIGHT, CODE_COUNT, Interference_COUNT), HU_DEMO_DIR_PATH + "captcha_line.jpg", null);
        // 圆圈干扰
        CreateCaptcha(CaptchaUtil.createCircleCaptcha(WIDTH, HEIGHT, CODE_COUNT, Interference_COUNT), HU_DEMO_DIR_PATH + "captcha_circle.jpg", null);
        // 扭曲干扰
        CreateCaptcha(CaptchaUtil.createShearCaptcha(WIDTH, HEIGHT, CODE_COUNT, CODE_COUNT), HU_DEMO_DIR_PATH + "captcha_shear.jpg", null);
        // 自定义验证码
        CreateCaptcha(CaptchaUtil.createShearCaptcha(WIDTH, HEIGHT, CODE_COUNT, CODE_COUNT), HU_DEMO_DIR_PATH + "captcha_random.jpg", new RandomGenerator("0123456789", CODE_COUNT));
        // 数学验证码
        CreateCaptcha(CaptchaUtil.createShearCaptcha(WIDTH, HEIGHT, CODE_COUNT, CODE_COUNT), HU_DEMO_DIR_PATH + "captcha_math.jpg", new MathGenerator());
    }

    private static void CreateCaptcha(AbstractCaptcha captcha, String captchaPath, CodeGenerator generator) {
        if (null != generator) captcha.setGenerator(generator);
        try (OutputStream os = Files.newOutputStream(Paths.get(captchaPath))) {
            // 写到流，也可以写到文件
            captcha.write(os);
            p(captcha.verify(captcha.getCode()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
