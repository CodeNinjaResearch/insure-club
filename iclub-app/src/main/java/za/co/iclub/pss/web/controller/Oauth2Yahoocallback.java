package za.co.iclub.pss.web.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;

import za.co.iclub.pss.model.ui.YahooMailsBean;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@SuppressWarnings({ "resource", "deprecation" })
public class Oauth2Yahoocallback extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final ResourceBundle Y_BUNDLE = ResourceBundle.getBundle("yahoo-web");
	protected static final Logger LOGGER = Logger.getLogger(Oauth2Yahoocallback.class);
	private static String encodedValue = "";
	
	public Oauth2Yahoocallback() {
		super();
		
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.err.println("entering doGet");
		
		LOGGER.info("==========hello:");
		try {
			String code = request.getParameter("code");
			
			HttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost("https://api.login.yahoo.com/oauth2/get_token");
			
			List<NameValuePair> arguments = new ArrayList<>(3);
			arguments.add(new BasicNameValuePair("grant_type", Y_BUNDLE.getString("grant_type")));
			arguments.add(new BasicNameValuePair("redirect_uri", Y_BUNDLE.getString("redirect_uri")));
			arguments.add(new BasicNameValuePair("client_secret", Y_BUNDLE.getString("client_secret")));
			arguments.add(new BasicNameValuePair("client_id", Y_BUNDLE.getString("client_id")));
			arguments.add(new BasicNameValuePair("code", code));
			try {
				Base64.Encoder encoder = Base64.getEncoder();
				String normalString = Y_BUNDLE.getString("client_id") + ":" + Y_BUNDLE.getString("client_secret");
				encodedValue = encoder.encodeToString(normalString.getBytes(StandardCharsets.UTF_8));
				post.setHeader("Authorization", "Basic " + encodedValue);
				post.setHeader("Content-Type", "application/x-www-form-urlencoded");
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				post.setEntity(new UrlEncodedFormEntity(arguments));
				HttpResponse response1 = client.execute(post);
				String outputString = EntityUtils.toString(response1.getEntity());
				JsonObject json = (JsonObject) new JsonParser().parse(outputString);
				String access_token = json.get("access_token").getAsString();
				String xoauth_yahoo_guid = json.get("xoauth_yahoo_guid").toString();
				
				try {
					String callUrl1 = "https://social.yahooapis.com/v1/user/" + xoauth_yahoo_guid.replace("\"", "") + "/profile?format=json";
					HttpGet httpGet = new HttpGet(callUrl1);
					httpGet.setHeader("Authorization", "Bearer " + access_token);
					httpGet.setHeader("Content-Type", "application/x-www-form-urlencoded");
					HttpResponse response2 = client.execute(httpGet);
					outputString = EntityUtils.toString(response2.getEntity());
					JsonObject jsonGet = (JsonObject) new JsonParser().parse(outputString);
					jsonGet = (JsonObject) new JsonParser().parse(jsonGet.get("profile").toString());
					String emails = jsonGet.get("emails").toString();
					ObjectMapper mapper = new ObjectMapper();
					
					mapper.readValue(emails.toString(), TypeFactory.collectionType(List.class, YahooMailsBean.class));
				} catch (Exception e) {
					LOGGER.error(e, e);
				}
				
			} catch (IOException e) {
				LOGGER.error(e, e);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("leaving doGet");
	}
	
}