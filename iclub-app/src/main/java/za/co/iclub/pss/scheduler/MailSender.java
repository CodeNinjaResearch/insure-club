package za.co.iclub.pss.scheduler;

import java.io.Serializable;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

import za.co.iclub.pss.orm.bean.IclubCohortInvite;

public class MailSender implements Serializable {
	
	private static final long serialVersionUID = -4232011314128276763L;
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("iclub-web");
	private static final String username = BUNDLE.getString("mail.google.username");
	private static final String password = BUNDLE.getString("mail.google.password");
	private static Properties props;
	private static final Logger LOGGER = Logger.getLogger(MailSender.class);
	
	static {
		props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
	}
	
	public static List<IclubCohortInvite> sendMail(List<IclubCohortInvite> cohorsInviteList) {
		
		try {
			
			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});
			if (cohorsInviteList != null && cohorsInviteList.size() > 0) {
				for (IclubCohortInvite bean : cohorsInviteList) {
					
					if (bean.getCiInviteUri() == null || !bean.getCiInviteUri().contains("@")) {
						continue;
					}
					
					Message message = new MimeMessage(session);
					message.setFrom(new InternetAddress(username));
					message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(bean.getCiInviteUri()));
					message.setSubject("Please Join my Club");
					StringBuilder mailstring = new StringBuilder();
					mailstring.append("<table>");
					mailstring.append("<tr><td><font face='verdana' size=2>Hi" + "," + "</font></td></tr>");
					mailstring.append("<tr><td><font face='verdana' size=2><br />I have created my own Insurance Club</font></td></tr>");
					mailstring.append("<tr><td><font face='verdana' size=2><br />We all pool our premiums into one big kitty and, what’s left after claims has been paid is ours.</font></td></tr>");
					mailstring.append("<tr><td><font face='verdana' size=2><br />It fantastic, if you interested please click on the below link, register and Search for my Clubs name</font></td></tr>");
					
					if (bean.getCiInviteUri().split("@")[1].toString().contains("gmail")) {
						mailstring.append("<tr><td><font face='verdana' size=2><br /><a href=" + BUNDLE.getString("mail.google.redirect_uri") + "google&cohortInvId=" + bean.getCiId() + " >www.insuranceclub.co.za</a></b></font></td></tr>");
					} else if (bean.getCiInviteUri().split("@")[1].toString().contains("yahoo")) {
						mailstring.append("<tr><td><font face='verdana' size=2><br /><a href=" + BUNDLE.getString("mail.google.redirect_uri") + "yahoo&cohortInvId=" + bean.getCiId() + " >www.insuranceclub.co.za</a></b></font></td></tr>");
					} else if (bean.getCiInviteUri().split("@")[1].toString().contains("outlook")) {
						mailstring.append("<tr><td><font face='verdana' size=2><br /><a href=" + BUNDLE.getString("mail.google.redirect_uri") + "outlook&cohortInvId=" + bean.getCiId() + " >www.insuranceclub.co.za</a></b></font></td></tr>");
					}
					
					mailstring.append("<tr><td><font face='verdana' size=2>Kind Regards</font></td></tr>");
					mailstring.append("<tr><td><font face='verdana' size=2>" + bean.getIclubPerson().getPFName() + " " + bean.getIclubPerson().getPLName() + "</font></td></tr>");
					
					mailstring.append("</table>");
					message.setContent(mailstring.toString(), "text/html; charset=utf-8");
					
					Transport.send(message);
					
					bean.setCiInviteSentStatus("Y");
					
					LOGGER.info(bean.getCiInviteUri() + " : Email Sent Successfully");
				}
			}
			
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		return cohorsInviteList;
	}
}