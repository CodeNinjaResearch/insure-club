package za.co.iclub.pss.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

@SuppressWarnings({ "resource", "deprecation" })
public class IclubFraudCheckController {
	
	public static final String FRAUD_CHECK_USERID = "4050";
	public static final String FRAUD_CHECK_PWD = "AeB7uvv5mO+dt7YH6OBal+JHESTppNG0GowXD/IJhT4=";
	private static final Logger LOGGER = Logger.getLogger(IclubMenuController.class);
	
	public static void main(String[] args) {
		
		String url = "https://testclientapi.fraudcheck.co.za/api/authenticate/test";
		String currentDate = System.currentTimeMillis() + "";
		
		String fcHash = DigestUtils.md5Hex(FRAUD_CHECK_USERID + FRAUD_CHECK_PWD + currentDate);
		
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		
		try {
			post.setHeader("Content-Type", "application/json");
			post.setHeader("FcAccId", FRAUD_CHECK_USERID);
			post.setHeader("FcHash", fcHash);
			post.setHeader("FcDate", currentDate);
			
			List<NameValuePair> arguments = new ArrayList<>(3);
			arguments.add(new BasicNameValuePair("data", "test"));
			post.setEntity(new UrlEncodedFormEntity(arguments));
			HttpResponse response1 = client.execute(post);
			EntityUtils.toString(response1.getEntity());
			
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		
	}
}
