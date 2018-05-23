package study_mail;

import java.util.Properties;

import javax.mail.Session;

/**   
* @Description 
* @author xiaoding
* @date 2018年4月20日 上午8:51:39   
*/
public class MailMessageTest {

	public static void main(String[] args) {
		Properties prop = new Properties();
		prop.setProperty("mail.smtp.host", "smtp.sina.com");
		prop.setProperty("mail.smtp.auth", "true");
		
		Session session  = Session.getInstance(prop);
	}
}
