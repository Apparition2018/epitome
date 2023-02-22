package spring.servlet.captcha;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serial;
import java.util.Properties;

/**
 * KaptchaServlet
 *
 * @author ljh
 * @since 2019/12/24 0:23
 */
public class KaptchaServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = -2566109140238951865L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 设置属性集
        Properties props = new Properties();
        props.put("kaptcha.border", "no");
        props.put("kaptcha.image.width", "80");
        props.put("kaptcha.image.height", "30");
        props.put("kaptcha.textproducer.char.string", "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
        props.put("kaptcha.textproducer.char.length", "4");
        props.put("kaptcha.textproducer.char.space", "3");
        props.put("kaptcha.textproducer.font.color", "red");
        props.put("kaptcha.textproducer.font.size", "24");
        props.put("kaptcha.obscurificator.impl", "com.google.code.kaptcha.impl.ShadowGimpy");
        props.put("kaptcha.noise.impl", "com.google.code.kaptcha.impl.NoNoise");
        Config config = new Config(props);
        // 创建 kaptcha 对象
        DefaultKaptcha kaptcha = new DefaultKaptcha();
        kaptcha.setConfig(config);
        // 生成验证码并保存到 Session
        String code = kaptcha.createText();
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, code);
        // 生成图片
        BufferedImage image = kaptcha.createImage(code);
        // 返回图片
        ServletOutputStream sos = response.getOutputStream();
        ImageIO.write(image, "png", sos);
        sos.flush();
        sos.close();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doPost(request, response);
    }
}
