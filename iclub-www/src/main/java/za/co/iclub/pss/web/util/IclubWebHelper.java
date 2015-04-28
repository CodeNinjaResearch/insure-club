package za.co.iclub.pss.web.util;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import za.co.iclub.pss.ws.util.CustomObjectMapper;

public class IclubWebHelper {
	public static WebClient createCustomClient(String url) {
		WebClient client = WebClient.create(url, Collections.singletonList(new JacksonJsonProvider(new CustomObjectMapper())));
		client.header("Content-Type", "application/json");
		client.header("Accept", "application/json");
		return client;
	}

	public static void addMessage(String desc, Severity sev) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(sev, desc, desc));
	}

	public static void addObjectIntoSession(String key, Object obj) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().put(key, obj);
	}

	public static void invalidateSession() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().invalidateSession();
	}

	public static Object getObjectIntoSession(String key) {
		FacesContext context = FacesContext.getCurrentInstance();
		return context.getExternalContext().getSessionMap().get(key);
	}

	public static int calculateMyAge(Long timeStamp) {

		Calendar birthCal = Calendar.getInstance();
		birthCal.setTimeInMillis(timeStamp);

		Calendar nowCal = new GregorianCalendar();

		int age = nowCal.get(Calendar.YEAR) - birthCal.get(Calendar.YEAR);

		boolean isMonthGreater = birthCal.get(Calendar.MONTH) > nowCal.get(Calendar.MONTH);

		boolean isMonthSameButDayGreater = birthCal.get(Calendar.MONTH) == nowCal.get(Calendar.MONTH) && birthCal.get(Calendar.DAY_OF_MONTH) > nowCal.get(Calendar.DAY_OF_MONTH);

		if (isMonthGreater || isMonthSameButDayGreater) {
			age = age - 1;
		}
		return age;
	}

	@SuppressWarnings("deprecation")
	public static boolean isCurrentDate(Long timeStamp) {

		try {
			if (timeStamp != null) {
				Timestamp currentDate = new Timestamp(System.currentTimeMillis());
				currentDate.setHours(0);
				currentDate.setMinutes(0);
				currentDate.setSeconds(0);
				currentDate.setNanos(0);
				Timestamp issueDate = new Timestamp(timeStamp);
				issueDate.setHours(0);
				issueDate.setMinutes(0);
				issueDate.setSeconds(0);
				issueDate.setNanos(0);

				return issueDate.compareTo(currentDate) < 0;
			}
		} catch (Exception e) {

		}

		return false;

	}

}
