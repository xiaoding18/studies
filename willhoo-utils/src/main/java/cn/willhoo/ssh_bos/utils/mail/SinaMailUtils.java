package cn.willhoo.ssh_bos.utils.mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;


/**
 * 发送邮件工具类
 * 
 * @author lenovo
 *
 */
public class SinaMailUtils {
	

	/**
	 * 发送基本内容的邮件
	 * @param recMail 接收方的邮件地址
	 * @param title 标题
	 * @param content 内容
	 */
	public static void sendMail(String recMail,String title,String content) {
		try {
			// 1.创建Session（连接邮箱服务器 smtp.qq.com smtp.sina.com smtp.163.com......）
			/**
			 * 参数一: 服务器参数 参数二：加密登录
			 */
			Properties props = new Properties();
			// 服务器地址
			props.setProperty("mail.smtp.host", "smtp.sina.com");
			// 加密登录
			props.setProperty("mail.smtp.auth", "true");

			Session session = Session.getInstance(props, new Authenticator() {

				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("18711724620@sina.cn", "xiaoding");
				}

			});

			// 开启调式
			session.setDebug(true);

			// 2.创建邮件
			MimeMessage mail = new MimeMessage(session);
			// 设置发件人
			mail.setFrom(new InternetAddress("18711724620@sina.cn"));
			// 设置收件人
			mail.setRecipient(RecipientType.TO, new InternetAddress(recMail));
			// 标题
			mail.setSubject(title);
			// 正文
			mail.setContent(content, "text/html;charset=utf-8");

			// 3.发送邮件
			Transport.send(mail);
		} catch (MessagingException e) {
			e.printStackTrace();
			System.out.println("发送邮件失败...."+e.getMessage());
		}
	}

}
