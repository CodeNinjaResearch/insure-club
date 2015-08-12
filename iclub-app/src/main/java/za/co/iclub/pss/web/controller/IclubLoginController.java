package za.co.iclub.pss.web.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;
import za.co.iclub.pss.model.ui.IclubLoginBean;
import za.co.iclub.pss.model.ws.IclubLoginModel;
import za.co.iclub.pss.model.ws.IclubPersonModel;
import za.co.iclub.pss.util.IclubWebHelper;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@ManagedBean(name = "iclubLoginController")
@SessionScoped
public class IclubLoginController implements Serializable {
	
	private static final long serialVersionUID = 8092436101540887282L;
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("iclub-web");
	private static final ResourceBundle Y_BUNDLE = ResourceBundle.getBundle("yahoo-web");
	protected static final Logger LOGGER = Logger.getLogger(IclubLoginController.class);
	private static final String BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubLoginService/";
	private static final String U_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubPersonService/";
	public static final String FRAUD_CHECK_USERID = "4050";
	public static final String FRAUD_CHECK_PWD = "AeB7uvv5mO+dt7YH6OBal+JHESTppNG0GowXD/IJhT4=";
	private List<IclubLoginBean> beans;
	private IclubLoginBean bean;
	private boolean showAddPanel;
	private boolean showModPanel;
	
	public String getState(String from) {
		String state = "{from:" + from + "}";
		try {
			if (IclubWebHelper.getObjectIntoSession("newInvite") != null) {
				state = "{from:" + from + ",redirect:newInvite}";
			}
			
			state = Base64.encodeBase64URLSafeString(state.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return state;
	}
	
	public void newCohortInviteAction() {
		setTheme();
		IclubWebHelper.addObjectIntoSession("newInvite", "true");
		
	}
	
	public String googleAction() {
		
		String redirectUrl = "https://accounts.google.com/o/oauth2/auth?scope=" + BUNDLE.getString("scope") + "&redirect_uri=" + BUNDLE.getString("redirect_uri") + "&response_type=code&client_id=" + BUNDLE.getString("client_id") + "&approval_prompt=force&state=" + getState("GOOGLE");
		try {
			setTheme();
			FacesContext.getCurrentInstance().getExternalContext().redirect(redirectUrl);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public String getFaceBookLogin() {
		
		String redirectUrl = "https://graph.facebook.com/oauth/authorize?client_id=" + BUNDLE.getString("fb.client_id") + "&display=page&redirect_uri=" + BUNDLE.getString("fb.redirect_uri") + "&scope=" + BUNDLE.getString("fb.perms2") + "&state=" + getState("fb");
		try {
			setTheme();
			FacesContext.getCurrentInstance().getExternalContext().redirect(redirectUrl);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public String yahooAction() {
		String redirectUrl = "https://api.login.yahoo.com/oauth2/request_auth?redirect_uri=" + Y_BUNDLE.getString("redirect_uri") + "&response_type=code&client_id=" + Y_BUNDLE.getString("client_id") + "&language=en-us&state=" + getState("YAHOO");
		try {
			setTheme();
			FacesContext.getCurrentInstance().getExternalContext().redirect(redirectUrl);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public String hotmailAction() {
		
		String redirectUrl = "https://login.live.com/oauth20_authorize.srf?scope=wl.birthday wl.contacts_birthday wl.contacts_phone_numbers wl.emails wl.offline_access wl.signin wl.basic" + "&redirect_uri=http://www.insuranceclub.co.za/iclub-app/templates/home.xhtml" + "&response_type=code&client_id=000000004C1516D6&state=" + getState("OUTLOOK");
		try {
			setTheme();
			FacesContext.getCurrentInstance().getExternalContext().redirect(redirectUrl);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public String twitterLogin() {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		
		cb.setDebugEnabled(true).setOAuthConsumerKey(BUNDLE.getString("twt.client_id")).setOAuthConsumerSecret(BUNDLE.getString("twt.secret")).setOAuthRequestTokenURL("https://api.twitter.com/oauth/request_token").setOAuthAuthorizationURL(("https://api.twitter.com/oauth/authorize")).setOAuthAccessTokenURL(("https://api.twitter.com/oauth/access_token"));
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		request.getSession().setAttribute("twitter", twitter);
		try {
			RequestToken requestToken = twitter.getOAuthRequestToken(BUNDLE.getString("twt.redirect_uri"));
			request.getSession().setAttribute("requestToken", requestToken);
			System.out.println("requestToken.getAuthenticationURL():" + requestToken.getAuthenticationURL());
			try {
				response.sendRedirect(requestToken.getAuthenticationURL());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public String encodeKeys(String consumerKey, String consumerSecret) {
		
		try {
			
			String encodedConsumerKey = URLEncoder.encode(consumerKey, "UTF-8");
			
			String encodedConsumerSecret = URLEncoder.encode(consumerSecret, "UTF-8");
			
			String fullKey = encodedConsumerKey + ":" + encodedConsumerSecret;
			
			byte[] encodedBytes = Base64.encodeBase64(fullKey.getBytes());
			
			return new String(encodedBytes);
			
		}
		
		catch (UnsupportedEncodingException e) {
			return new String();
			
		}
		
	}
	
	public String requestBearerToken() throws IOException {
		HttpsURLConnection connection = null;
		String encodedCredentials = encodeKeys(BUNDLE.getString("twt.client_id"), BUNDLE.getString("twt.secret"));
		String endPointUrl = "https://api.twitter.com/oauth2/token";
		try {
			HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
			response.setHeader("Host", "api.twitter.com");
			response.setHeader("User-Agent", "Iclub");
			response.setHeader("Authorization", "Basic " + encodedCredentials);
			response.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
			response.setHeader("Content-Length", "29");
			ServletOutputStream fdsaf = response.getOutputStream();
			fdsaf.write("grant_type=client_credentials".getBytes());
			fdsaf.close();
			response.sendRedirect(endPointUrl);
			// FacesContext.getCurrentInstance().getExternalContext().redirect(url);
			/*
			 * writeRequest(connection, "grant_type=client_credentials");
			 * 
			 * // Parse the JSON response into a JSON mapped object to fetch
			 * fields // from. JSONObject obj = new
			 * JSONObject(readResponse(connection));
			 * 
			 * if (obj != null) { String tokenType = (String)twt.redirect_uri
			 * obj.get("token_type"); String token = (String)
			 * obj.get("access_token");
			 * 
			 * return ((tokenType.equals("bearer")) && (token != null)) ? token
			 * : ""; }
			 */
			return new String();
		} catch (MalformedURLException e) {
			throw new IOException("Invalid endpoint URL specified.", e);
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}
	
	public boolean writeRequest(HttpsURLConnection connection, String textBody) {
		
		try {
			BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
			wr.write(textBody);
			wr.flush();
			wr.close();
			return true;
		} catch (IOException e) {
			return false;
		}
	}
	
	public String readResponse(HttpsURLConnection connection) {
		try {
			StringBuilder str = new StringBuilder();
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line = "";
			while ((line = br.readLine()) != null) {
				str.append(line + System.getProperty("line.separator"));
			}
			return str.toString();
		} catch (IOException e) {
			return new String();
		}
	}
	
	public static void setTheme() {
		String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
		
		if (viewId != null && viewId.contains("spark")) {
			
			IclubWebHelper.addObjectIntoSession(BUNDLE.getString("logged.in.user.theme"), "-spark");
			IclubWebHelper.addObjectIntoSession(BUNDLE.getString("logged.in.user.webTheme"), "spark");
			
		} else {
			IclubWebHelper.addObjectIntoSession(BUNDLE.getString("logged.in.user.theme"), "");
			IclubWebHelper.addObjectIntoSession(BUNDLE.getString("logged.in.user.webTheme"), "sentinel");
		}
		
	}
	
	public String doIclubLogin() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: doIclubLogin");
		if (!validateLogin()) {
			try {
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "person/login/" + bean.getLName() + "/" + Base64.encodeBase64URLSafeString(DigestUtils.md5(bean.getLPasswd())));
				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).get(ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {
					setTheme();
					IclubWebHelper.addMessage("Person Logged-In successfully", FacesMessage.SEVERITY_INFO);
					WebClient loginClient = IclubWebHelper.createCustomClient(BASE_URL + "person/" + bean.getLName());
					IclubLoginModel model = loginClient.accept(MediaType.APPLICATION_JSON).get(IclubLoginModel.class);
					loginClient.close();
					if (model != null && model.getLId() != null) {
						IclubWebHelper.addObjectIntoSession(BUNDLE.getString("logged.in.user.id"), model.getIclubPersonBByLPersonId());
						IclubWebHelper.addObjectIntoSession(BUNDLE.getString("logged.in.user.scname"), bean.getLName());
						WebClient personClient = IclubWebHelper.createCustomClient(U_BASE_URL + "get/" + model.getIclubPersonBByLPersonId());
						IclubPersonModel personModel = personClient.accept(MediaType.APPLICATION_JSON).get(IclubPersonModel.class);
						personClient.close();
						IclubWebHelper.addObjectIntoSession(BUNDLE.getString("logged.in.user.name"), personModel.getPFName() + (personModel.getPLName() == null ? "" : personModel.getPLName() + " "));
						IclubWebHelper.addObjectIntoSession(BUNDLE.getString("logged.in.role.id"), 1l);
						
						return "userDashboard";
						
					} else {
						IclubWebHelper.addMessage("Person Profile Fetch Error - Contact Admin", FacesMessage.SEVERITY_ERROR);
						return "";
					}
				} else {
					IclubWebHelper.addMessage("Login error :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
					return "";
				}
			} catch (Exception e) {
				LOGGER.error(e, e);
				IclubWebHelper.addMessage("Login error :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
				return "";
			}
		} else {
			return "";
		}
	}
	
	public void doIclubLogout() {
		
		String theme = "";
		String webTheme = "";
		if (IclubWebHelper.getObjectIntoSession(BUNDLE.getString("logged.in.user.theme")) != null) {
			theme = IclubWebHelper.getObjectIntoSession(BUNDLE.getString("logged.in.user.theme")).toString();
		}
		if (IclubWebHelper.getObjectIntoSession(BUNDLE.getString("logged.in.user.webTheme")) != null) {
			webTheme = IclubWebHelper.getObjectIntoSession(BUNDLE.getString("logged.in.user.webTheme")).toString();
		}
		IclubWebHelper.invalidateSession();
		IclubWebHelper.addObjectIntoSession(BUNDLE.getString("logged.in.user.theme"), theme);
		IclubWebHelper.addObjectIntoSession(BUNDLE.getString("logged.in.user.webTheme"), webTheme);
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navigationHandler = context.getApplication().getNavigationHandler();
		
		navigationHandler.handleNavigation(context, null, "/templates/login" + theme + ".xhtml?faces-redirect=true");
		
		String url = "https://testclientapi.fraudcheck.co.za/api/authenticate/test";
		String currentTimeStamp = System.currentTimeMillis() + "";
		
		String fcHash = DigestUtils.md5Hex(FRAUD_CHECK_USERID + FRAUD_CHECK_PWD + currentTimeStamp);
		
		HttpClient client = getHttpClient();
		
		try {
			HttpPost post = new HttpPost(url);
			post.setHeader("Content-Type", "application/json");
			post.setHeader("FcAccId", FRAUD_CHECK_USERID);
			post.setHeader("FcHash", fcHash);
			post.setHeader("FcTimestamp", currentTimeStamp);
			
			List<NameValuePair> arguments = new ArrayList<>(3);
			arguments.add(new BasicNameValuePair("data", "test"));
			post.setEntity(new UrlEncodedFormEntity(arguments));
			HttpResponse response1 = client.execute(post);
			String outputString = EntityUtils.toString(response1.getEntity());
			System.out.println(outputString);
		} catch (Exception e) {
			 
		}
		
	}
	
	private static HttpClient getHttpClient() {
		
		try {
			SSLContext sslContext = SSLContext.getInstance("SSL");
			
			sslContext.init(null, new TrustManager[] { new X509TrustManager() {
				public X509Certificate[] getAcceptedIssuers() {
					
					return null;
				}
				
				public void checkClientTrusted(X509Certificate[] certs, String authType) {
					
				}
				
				public void checkServerTrusted(X509Certificate[] certs, String authType) {
					
				}
			} }, new SecureRandom());
			
			SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			
			HttpClient httpClient = HttpClientBuilder.create().setSSLSocketFactory(socketFactory).build();
			
			return httpClient;
			
		} catch (Exception e) {
			e.printStackTrace();
			return HttpClientBuilder.create().build();
		}
	}
	
	public boolean validateLogin() {
		boolean ret = false;
		if (bean.getLName() == null || bean.getLName().trim().length() == 0) {
			IclubWebHelper.addMessage("Login Name Cannot be null", FacesMessage.SEVERITY_ERROR);
			ret = true;
		}
		if (bean.getLPasswd() == null || bean.getLPasswd().trim().length() == 0) {
			IclubWebHelper.addMessage("Password Cannot be null", FacesMessage.SEVERITY_ERROR);
			ret = true;
		}
		return ret;
	}
	
	public List<IclubLoginBean> getBeans() {
		return beans;
	}
	
	public void setBeans(List<IclubLoginBean> beans) {
		this.beans = beans;
	}
	
	public IclubLoginBean getBean() {
		if (bean == null)
			bean = new IclubLoginBean();
		return bean;
	}
	
	public void setBean(IclubLoginBean bean) {
		this.bean = bean;
	}
	
	public boolean isShowAddPanel() {
		return showAddPanel;
	}
	
	public void setShowAddPanel(boolean showAddPanel) {
		this.showAddPanel = showAddPanel;
	}
	
	public boolean isShowModPanel() {
		return showModPanel;
	}
	
	public void setShowModPanel(boolean showModPanel) {
		this.showModPanel = showModPanel;
	}
	
}
