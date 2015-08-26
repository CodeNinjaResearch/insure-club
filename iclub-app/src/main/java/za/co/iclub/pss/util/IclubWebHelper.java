package za.co.iclub.pss.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.log4j.Logger;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.ObjectMapper;

import za.co.iclub.pss.model.ui.GoogleResponse;
import za.co.iclub.pss.model.ui.Result;
import za.co.iclub.pss.model.ws.IclubGeoLocModel;
import za.co.iclub.pss.web.controller.IclubMenuController;

public class IclubWebHelper {
	
	private static final String URL = "http://maps.googleapis.com/maps/api/geocode/json";
	private static final Logger LOGGER = Logger.getLogger(IclubMenuController.class);
	
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
	
	public static int calculateYearDiff(Long timeStamp) {
		
		Calendar birthCal = Calendar.getInstance();
		birthCal.setTimeInMillis(timeStamp);
		Calendar nowCal = new GregorianCalendar();
		int years = nowCal.get(Calendar.YEAR) - birthCal.get(Calendar.YEAR);
		boolean isMonthGreater = birthCal.get(Calendar.MONTH) > nowCal.get(Calendar.MONTH);
		boolean isMonthSameButDayGreater = birthCal.get(Calendar.MONTH) == nowCal.get(Calendar.MONTH) && birthCal.get(Calendar.DAY_OF_MONTH) > nowCal.get(Calendar.DAY_OF_MONTH);
		if (isMonthGreater || isMonthSameButDayGreater) {
			years = years - 1;
		}
		return years;
	}
	
	@SuppressWarnings("deprecation")
	public static boolean isCurrentDate(Long timeStamp) {
		
		try {
			if (timeStamp != null) {
				Date currentDate = new Date(System.currentTimeMillis());
				currentDate.setHours(0);
				currentDate.setMinutes(0);
				currentDate.setSeconds(0);
				Date issueDate = new Date(timeStamp);
				issueDate.setHours(0);
				issueDate.setMinutes(0);
				issueDate.setSeconds(0);
				
				return issueDate.compareTo(currentDate) > 0;
			}
		} catch (Exception e) {
			
		}
		
		return false;
		
	}
	
	public static IclubGeoLocModel getLatAndLong(IclubGeoLocModel model) {
		try {
			if (model != null) {
				synchronized (model) {
					GoogleResponse res = convertToLatLong((model.getGlAddress() != null ? model.getGlAddress() + "," : "") + (model.getGlSuburb() != null ? model.getGlSuburb() + "," : "") + (model.getGlProvince() != null ? model.getGlProvince() : ""));
					if (res.getStatus().equals("OK")) {
						for (Result result : res.getResults()) {
							LOGGER.info("Lattitude of address is :" + result.getGeometry().getLocation().getLat());
							LOGGER.info("Longitude of address is :" + result.getGeometry().getLocation().getLng());
							LOGGER.info("Location is " + result.getGeometry().getLocation_type());
							model.setGlLat(new Double(result.getGeometry().getLocation().getLat()));
							model.setGlLong(new Double(result.getGeometry().getLocation().getLng()));
						}
						
						try {
							long time = 500;
							Thread.sleep(time);
						} catch (Exception e) {
							LOGGER.error(e, e);
						}
						
					} else {
						LOGGER.info(res.getStatus());
					}
				}
			}
		} catch (Exception e) {
			
		}
		return model;
		
	}
	
	public static Long getRandomNumber() {
		Random r = new Random();
		int Low = 1000000;
		int High = 9999999;
		int R = r.nextInt(High - Low) + Low;
		SimpleDateFormat formate = new SimpleDateFormat("YYYYMMDD");
		return Long.parseLong((formate.format(new Date()) + R));
		
	}
	
	public static GoogleResponse convertToLatLong(String fullAddress) throws IOException {
		
		URL url = new URL(URL + "?address=" + URLEncoder.encode(fullAddress, "UTF-8") + "&sensor=false");
		URLConnection conn = url.openConnection();
		
		InputStream in = conn.getInputStream();
		ObjectMapper mapper = new ObjectMapper();
		GoogleResponse response = (GoogleResponse) mapper.readValue(in, GoogleResponse.class);
		in.close();
		return response;
	}
	
	public static GoogleResponse convertFromLatLong(String latlongString) throws IOException {
		URL url = new URL(URL + "?latlng=" + URLEncoder.encode(latlongString, "UTF-8") + "&sensor=false");
		URLConnection conn = url.openConnection();
		InputStream in = conn.getInputStream();
		ObjectMapper mapper = new ObjectMapper();
		GoogleResponse response = (GoogleResponse) mapper.readValue(in, GoogleResponse.class);
		in.close();
		return response;
	}
	
	public static String validateId(String id, String gender) {
		
		if (id != null && !id.trim().equalsIgnoreCase("")) {
			if (id.length() == 13) {
				String dob = id.substring(0, 6);
				SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
				try {
					Date dateOfBirth = format.parse(dob);
					if (!isCurrentDate(dateOfBirth.getTime())) {
						
						String SeqNum = id.substring(6, 10);
						int sqNum = Integer.parseInt(SeqNum);
						if (gender != null && (gender.trim().equalsIgnoreCase("m") || gender.trim().equalsIgnoreCase("f"))) {
							if (gender.trim().equalsIgnoreCase("m") && !(sqNum >= 5000 && sqNum <= 9999)) {
								return "Invalid Id";
							}
							if (gender.trim().equalsIgnoreCase("f") && !(sqNum >= 0000 && sqNum <= 4999)) {
								return "Invalid Id";
							}
						} else {
							return "Please select Gender";
						}
					}
					
					String citizenship = id.substring(10, 11);
					
					if (Integer.parseInt(citizenship) == 0 || Integer.parseInt(citizenship) == 1) {
						LOGGER.info("citizenship------:" + citizenship);
					}
					
					String holdersrace = id.substring(11, 12);
					
					if (Integer.parseInt(holdersrace) == 8 || Integer.parseInt(holdersrace) == 9) {
						LOGGER.info("holdersrace------:" + holdersrace);
					}
					
					String checkSum = id.substring(12, 13);
					
					LOGGER.info("checkSum------:" + checkSum);
					if (getCheckSum(id.substring(0, 12), Integer.parseInt(checkSum))) {
						return "";
					}
					return "Invalid Id";
				} catch (Exception e) {
					
					return "Invalid Id";
				}
				
			}
			return "Id Length Should be 13 Charecters";
		}
		
		return "Id Cann't Be Empty";
	}
	
	public static boolean getCheckSum(String id, int checksum) {
		
		LOGGER.info("id==========:" + id);
		
		char[] idArrya = id.toCharArray();
		int a = 0;
		String b = "";
		int c = 0;
		int d = 0;
		for (int i = 0; i < 12; i++) {
			
			if (((i + 1) % 2) == 1) {
				
				String as = idArrya[i] + "";
				a += Integer.parseInt(as);
				
			} else {
				b += idArrya[i];
			}
			
		}
		LOGGER.info("a====" + a);
		b = (Integer.parseInt(b) * 2) + "";
		LOGGER.info("b====" + b);
		for (int i = 0; i < b.length(); i++) {
			
			String bs = b.toCharArray()[i] + "";
			c += Integer.parseInt(bs);
		}
		LOGGER.info("====C======:" + c);
		d = a + c;
		LOGGER.info("====D======:" + d);
		int z = 10 - (d % 10);
		LOGGER.info("Z========" + z);
		
		return z == checksum;
	}
	
	public static boolean validateCountry(String latLongString) {
		
		try {
			
			GoogleResponse response = convertFromLatLong(latLongString);
			if (response != null && response.getStatus().equals("OK")) {
				for (Result result : response.getResults()) {
					if (result.getFormatted_address() != null && result.getFormatted_address().trim().replace(" ", "").toLowerCase().contains("southafrica")) {
						return true;
					}
				}
			}
		} catch (Exception e) {
			
		}
		return false;
	}
}
