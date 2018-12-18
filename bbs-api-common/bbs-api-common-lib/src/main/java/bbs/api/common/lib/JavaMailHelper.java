package bbs.api.common.lib;

import bbs.api.common.lib.application.BeanTools;
import bbs.api.common.lib.view.MailInfoView;
import bbs.api.common.lib.view.ServerInfoView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * 邮件处理类
 *
 * @author AndyChen
 * @since 2017-06-27
 */
public class JavaMailHelper {
    private static JavaMailSender javaMailSender = (JavaMailSender) BeanTools.getBean(JavaMailSender.class);
    private static Logger logger = LoggerFactory.getLogger(JavaMailHelper.class);
    private static String from = ConfigProperties.getValue("mailFrom");

    /**
     * 根据配置信息，当 evn=localDev，也即本地开发时，不发送异常日志邮件
     */
    public static void emailErrInfo(String subject, String content) {
        String env = ConfigProperties.getValue("evn");
        subject = env + "-->" + subject;

        MailInfoView mailInfoView = new MailInfoView();
        mailInfoView.setTo(ConfigProperties.getValue("whenExceptionMailTo"));
        mailInfoView.setSubject(subject);
        mailInfoView.setBody(content);

        JavaMailHelper.sendHtmlMail(mailInfoView);
    }

    /**
     * 发送纯文本的简单邮件
     */
    public static void sendSimpleMail(MailInfoView mailInfoView) {
        SimpleMailMessage message = new SimpleMailMessage();
        mailInfoViewAppendOsInfo(mailInfoView);

        message.setFrom(from);
        message.setTo(mailInfoView.getTo());
        message.setSubject(mailInfoView.getSubject());
        message.setText(mailInfoView.getBody());

        try {
            javaMailSender.send(message);
            logger.info("sendSimpleMail to " + mailInfoView.getTo() + " succeeded!");
        } catch (Exception e) {
            logger.error("sendSimpleMail to " + mailInfoView.getTo() + " failed!", e);
        }
    }

    /**
     * 发送html格式的邮件
     */
    public static void sendHtmlMail(MailInfoView mailInfoView) {
        MimeMessage message = javaMailSender.createMimeMessage();
        mailInfoViewAppendOsInfo(mailInfoView);

        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            setMimeMessageHelper(helper, mailInfoView);

            javaMailSender.send(message);
            logger.info("sendHtmlMail to " + mailInfoView.getTo() + " succeeded!");
        } catch (MessagingException e) {
            logger.info("sendHtmlMail to " + mailInfoView.getTo() + " failed!");
        }
    }

    /**
     * 发送带附件的邮件
     */
    public static void sendAttachmentsMail(MailInfoView mailInfoView) {
        MimeMessage message = javaMailSender.createMimeMessage();
        mailInfoViewAppendOsInfo(mailInfoView);

        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            setMimeMessageHelper(helper, mailInfoView);

            FileSystemResource file = new FileSystemResource(new File(mailInfoView.getFilePath()));
            String fileName = mailInfoView.getFilePath().substring(mailInfoView.getFilePath().lastIndexOf(File.separator));
            helper.addAttachment(fileName, file);

            javaMailSender.send(message);
            logger.info("sendAttachmentsMail to " + mailInfoView.getTo() + " succeeded!");
        } catch (MessagingException e) {
            logger.info("sendAttachmentsMail to " + mailInfoView.getTo() + " failed!");
        }
    }

    /**
     * 发送嵌入静态资源（一般是图片）的邮件
     */
    public static void sendInlineResourceMail(MailInfoView mailInfoView) {
        MimeMessage message = javaMailSender.createMimeMessage();
        mailInfoViewAppendOsInfo(mailInfoView);

        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            setMimeMessageHelper(helper, mailInfoView);

            FileSystemResource res = new FileSystemResource(new File(mailInfoView.getRscPath()));
            helper.addInline(mailInfoView.getRscId(), res);

            javaMailSender.send(message);
            logger.info("sendInlineResourceMail to " + mailInfoView.getTo() + " succeeded!");
        } catch (MessagingException e) {
            logger.info("sendInlineResourceMail to " + mailInfoView.getTo() + " failed!");
        }
    }

    private static void setMimeMessageHelper(MimeMessageHelper helper, MailInfoView mailInfoView) throws MessagingException {
        helper.setFrom(from);
        helper.setTo(mailInfoView.getTo().split(","));
        helper.setSubject(mailInfoView.getSubject());
        helper.setText(mailInfoView.getBody(), true);
    }

    private static void mailInfoViewAppendOsInfo(MailInfoView mailInfoView) {
        if (!mailInfoView.isAddServerInfoFlag()) {
            return;
        }

        mailInfoView.setBody(
                "mailContent : " + mailInfoView.getBody()
                + "<br><br>hostIP : " + ServerInfoView.hostIP + "<br>"
                + "hostName : " + ServerInfoView.hostName + "<br>"
                + "osName : " + ServerInfoView.osName + "<br>"
                + "osArch : " + ServerInfoView.osArch + "<br>"
                + "osVersion : " + ServerInfoView.osVersion + "<br>"
                + "processors : " + ServerInfoView.processors + "<br>"
                + "javaVersion : " + ServerInfoView.javaVersion + "<br>"
                + "javaHome : " + ServerInfoView.javaHome + "<br>"
                + "javaVendor : " + ServerInfoView.javaVendor + "<br>"
        );
    }
}
