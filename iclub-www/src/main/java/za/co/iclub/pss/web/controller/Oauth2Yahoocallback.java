package za.co.iclub.pss.web.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.EncoderException;
import org.apache.log4j.Logger;

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
			
			String pa = request.getParameter("a");
			System.out.println(pa + "       kkkkkkkkk");
			System.out.println("helloWorld:doGet:accessToken" + request.getAttribute("access_token"));
			System.out.println("helloWorld:doGet:accessToken" + request.getParameter("access_token"));
			// format parameters to post
			// String urlParameters = "code=" + code + "&client_id=" +
			// Y_BUNDLE.getString("client_id") + "&client_secret=" +
			// Y_BUNDLE.getString("client_secret") + "&redirect_uri=" +
			// Y_BUNDLE.getString("redirect_uri") +
			// "&grant_type=authorization_code";
			String urlParameters = "grant_type=authorization_code&redirect_uri=https%3A%2F%2Fwww.insuranceclub.org/iclub-www/Oauth2Yahoocallback&code=" + code;
			// post parameters
			/*
			 * URL url = new
			 * URL("https://api.login.yahoo.com/oauth2/get_token");
			 * URLConnection urlConn = url.openConnection();
			 * urlConn.setDoOutput(true); OutputStreamWriter writer = new
			 * OutputStreamWriter(urlConn.getOutputStream());
			 * writer.write(urlParameters); writer.flush();
			 */
			doPost(request, response);
			System.out.println("=========");
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("leaving doGet");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");
		
		String getAccessToken = "https://api.login.yahoo.com/oauth2/get_token?grant_type=authorization_code&redirect_uri=" + Y_BUNDLE.getString("redirect_uri") + "&code=" + code;
		try {
			// client_id=" + Y_BUNDLE.getString("client_id") + "&client_secret=" + Y_BUNDLE.getString("client_secret") + "&
			Base64.Encoder encoder = Base64.getEncoder();
			String normalString = Y_BUNDLE.getString("client_id") + ":" + Y_BUNDLE.getString("client_secret");
			encodedValue = encoder.encodeToString(normalString.getBytes(StandardCharsets.UTF_8));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("helloWorld:doPost:encodedVAlue-->" + encodedValue);
		response.setHeader("Authorization", "Basic " + encodedValue);
		response.setHeader("Content-Type", "application/x-www-form-urlencoded");
		
		System.out.println("helloWorld:doGet:contactsUri" + getAccessToken);
		response.sendRedirect(getAccessToken);
	}
	
}