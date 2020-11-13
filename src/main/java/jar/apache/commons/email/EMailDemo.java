package jar.apache.commons.email;

import l.demo.Demo;
import org.apache.commons.mail.*;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * 使用 Apache Commons Email 发生邮件：https://www.cnblogs.com/jimboi/p/6406237.html
 * http://commons.apache.org/proper/commons-email/apidocs/org/apache/commons/mail/Email.html
 */
public class EMailDemo extends Demo {

    /**
     * SimpleEmail
     * 没有附件的 simple internet 电子邮件
     * http://commons.apache.org/proper/commons-email/apidocs/org/apache/commons/mail/SimpleEmail.html
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
     * MultiPartEmail
     * 带有附件的 multi-part internet 电子邮件
     * http://commons.apache.org/proper/commons-email/apidocs/org/apache/commons/mail/MultiPartEmail.html
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
     * HtmlEmail
     * HTML 格式电子邮件
     * http://commons.apache.org/proper/commons-email/apidocs/org/apache/commons/mail/HtmlEmail.html
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
     * ImageHtmlEmail
     * http://commons.apache.org/proper/commons-email/apidocs/org/apache/commons/mail/ImageHtmlEmail.html
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
        email.setCharset(UTF_8);

        // 主题
        email.setSubject("Simple Email");

        // 发件人邮件地址，名称
        email.setFrom("mail_assistanter@dayang.com", "邮件助手", UTF_8);

        // 收件人
        email.addTo("234607@dayang.com");

        // 抄送人
        email.addCc("234607@dayang.com");

        // 密送人
        email.addBcc("234607@dayang.com");
    }

}
