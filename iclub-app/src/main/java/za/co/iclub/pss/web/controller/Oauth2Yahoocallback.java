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

import org.apache.commons.codec.digest.DigestUtils;
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

@SuppressWarnings("deprecation")
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
			// get code
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
				System.out.println(outputString);
				JsonObject json = (JsonObject) new JsonParser().parse(outputString);
				System.out.println(json);
				String access_token = json.get("access_token").getAsString();
				String xoauth_yahoo_guid = json.get("xoauth_yahoo_guid").toString();
				
				try {
					// https://social.yahooapis.com/v1/user/3344/profile?format=json
					// https://social.yahooapis.com/v1/user/6677/contacts
					// SELECT * FROM social.contacts WHERE guid='6677'
					String callUrl1 = "https://social.yahooapis.com/v1/user/" + xoauth_yahoo_guid.replace("\"", "") + "/profile?format=json";
					HttpGet httpGet = new HttpGet(callUrl1);
					httpGet.setHeader("Authorization", "Bearer " + access_token);
					httpGet.setHeader("Content-Type", "application/x-www-form-urlencoded");
					HttpResponse response2 = client.execute(httpGet);
					outputString = EntityUtils.toString(response2.getEntity());
					JsonObject jsonGet = (JsonObject) new JsonParser().parse(outputString);
					System.out.println(outputString + "------outputString   :\n");
					jsonGet = (JsonObject) new JsonParser().parse(jsonGet.get("profile").toString());
					String emails = jsonGet.get("emails").toString();
					ObjectMapper mapper = new ObjectMapper();
					
					List<YahooMailsBean> mailsList = mapper.readValue(emails.toString(), TypeFactory.collectionType(List.class, YahooMailsBean.class));
					System.out.println(mailsList);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("leaving doGet");
	}
	
	public static void main(String[] args) {
		System.out.println(DigestUtils.md5Hex("TestUserT3stus3rP@ssw0rd1427109538"));
	}
}