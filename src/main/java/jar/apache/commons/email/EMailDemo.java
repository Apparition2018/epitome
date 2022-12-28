package jar.apache.commons.email;

import l.demo.Demo;
import org.apache.commons.mail.*;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * <a href="https://commons.apache.org/proper/commons-email/index.html">Commons Email</a>
 * <p>参考：<a href="https://www.cnblogs.com/jimboi/p/6406237.html">Commons Email 发送邮件</a>
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class EMailDemo extends Demo {

    /**
     * <a href="http://commons.apache.org/proper/commons-email/apidocs/org/apache/commons/mail/SimpleEmail.html">SimpleEmail</a>
     * <p>发送没有附件的简单 Internet 电子邮件消息
     */
    @Test
    public void sendSimpleEmail() throws EmailException {
        Email email = new SimpleEmail();

        common(email);

        // 内容
        email.setMsg("This is a test mail ...");

        email.send();
    }

    /**
     * <a href="http://commons.apache.org/proper/commons-email/apidocs/org/apache/commons/mail/MultiPartEmail.html">MultiPartEmail</a>
     * <p>发送 Multi-Part Internet 电子邮件，如带有附件的消息
     */
    @Test
    public void sendMultiPartEmail() throws EmailException, MalformedURLException {

        EmailAttachment attachment = new EmailAttachment();
        // 附件路径，可以是绝对路径，也可以是相对路径
        attachment.setPath(ARSENAL_LOGO);
        // 配置 ?
        attachment.setDescription(EmailAttachment.ATTACHMENT);
        // 描述
        attachment.setDescription("A Football Club");
        // 名称
        attachment.setName("Arsenal");


        MultiPartEmail email = new MultiPartEmail();

        common(email);

        // 内容
        email.setMsg("This is a test mail ...");

        // 附件   EmailAttachment
        email.attach(attachment);

        // 附件   URL 定位的文件
        email.attach(new URL(ARSENAL_LOGO), "Arsenal", "A Football Club", "attachment");

        email.send();
    }

    /**
     * <a href="http://commons.apache.org/proper/commons-email/apidocs/org/apache/commons/mail/HtmlEmail.html">HtmlEmail</a>
     * <p>发送 HTML 格式的电子邮件
     */
    @Test
    public void sendHtmlEmail() throws EmailException, MalformedURLException {
        HtmlEmail email = new HtmlEmail();

        common(email);

        // 嵌入，返回 id
        String cid = email.embed(new URL(ARSENAL_LOGO), "Arsenal");

        // HTML 内容
        email.setHtmlMsg("<html>Arsenal - <img src=\"cid:" + cid + "\"></html>");

        // 文本内容
        email.setTextMsg("Arsenal Football Club");

        email.send();
    }

    /**
     * <a href="http://commons.apache.org/proper/commons-email/apidocs/org/apache/commons/mail/ImageHtmlEmail.html">ImageHtmlEmail</a>
     */
    @Test
    public void sendImageEmail() throws EmailException {
        ImageHtmlEmail email = new ImageHtmlEmail();

        common(email);

        String htmlEmailTemplate = "<html>Arsenal - <img src=\"" + ARSENAL_LOGO + "\"></html>";

        email.setHtmlMsg(htmlEmailTemplate);

        email.setTextMsg("Arsenal Football Club");

        email.send();
    }

    public static void common(Email email) throws EmailException {

        // 邮件服务器主机名
        email.setHostName("172.20.200.209");

        // 邮件服务器端口号，默认 25
        // email.setSmtpPort(smtpPort);

        // 从邮件服务器请求身份验证时使用的身份验证器
        // email.setAuthenticator(new DefaultAuthenticator("", ""));

        // 否应在连接时为 SMTP 传输 (SMTPS pop) 启用 SSL/TLS 加密
        // email.setSSLOnConnect(true);

        // 消息字符集，在添加 message content 前添加
        email.setCharset(StandardCharsets.UTF_8.name());

        // 主题
        email.setSubject("Simple Email");

        // 发件人邮件地址，名称
        email.setFrom("mail_assistanter@dayang.com", "邮件助手", StandardCharsets.UTF_8.name());

        // 收件人
        email.addTo("234607@dayang.com");

        // 抄送人
        email.addCc("234607@dayang.com");

        // 密送人
        email.addBcc("234607@dayang.com");
    }
}
