package jar.captcha;

import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;

import java.awt.*;
import java.io.IOException;
import java.util.UUID;

/**
 * <a href="https://github.com/whvcse/EasyCaptcha">通过 easy-captcha 生成图片验证码</a>
 *
 * @author ljh
 * @since 2019/12/20 10:07
 */
public class EasyCaptchaDemo {

    public static void main(String[] args) throws IOException, FontFormatException {
        // 生成验证码：宽，高，验证码位数
        SpecCaptcha captcha = new SpecCaptcha(130, 50, 4);
        // 设置字体
        captcha.setFont(Captcha.FONT_2);

        // 获取验证码
        String verCode = captcha.text().toLowerCase();
        System.out.println("verCode = " + verCode);

        // 生成 uuid，存入 redis，用于前后分离模式下检查用户输入验证码是否正确
        String key = UUID.randomUUID().toString();

        // 验证码转成 base64
        String captchaBase64 = captcha.toBase64();
        System.out.println("captchaBase64 = " + captchaBase64);
    }
}
