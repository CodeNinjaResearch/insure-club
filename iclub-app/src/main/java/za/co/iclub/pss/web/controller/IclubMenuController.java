package za.co.iclub.pss.web.controller;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;

import org.apache.commons.codec.binary.Base64;
import org.apache.cxf.jaxrs.client.WebClient;
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
import org.primefaces.json.JSONObject;

import twitter4j.Twitter;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import za.co.iclub.pss.model.ui.GooglePojo;
import za.co.iclub.pss.model.ui.OutLookUserProfileBean;
import za.co.iclub.pss.model.ui.SocialAuthResponse;
import za.co.iclub.pss.model.ui.YahooMailsBean;
import za.co.iclub.pss.model.ws.IclubCohortModel;
import za.co.iclub.pss.model.ws.IclubLoginModel;
import za.co.iclub.pss.model.ws.IclubPersonModel;
import za.co.iclub.pss.util.IclubWebHelper;
import za.co.iclub.pss.ws.model.common.ResponseModel;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@ManagedBean(name = "iclubMenuController")
@SessionScoped
@SuppressWarnings({ "resource", "deprecation" })
public class IclubMenuController implements Serializable {
	
	private static final long serialVersionUID = -5155234741934732730L;
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("iclub-web");
	private static final Logger LOGGER = Logger.getLogger(IclubMenuController.class);
	private static final String BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubLoginService/";
	private static final String U_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubPersonService/";
	private static final String CH_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubCohortService/";
	private static final ResourceBundle Y_BUNDLE = ResourceBundle.getBundle("yahoo-web");
	private String language;
	private boolean userMenu;
	private boolean manageCohorts;
	private String selPage;
	String preCode = "";
	String preVerifier = "";
	
	public void initializePage() {
		
		if (IclubWebHelper.getObjectIntoSession("social_update_profile") != null && !FacesContext.getCurrentInstance().getViewRoot().getViewId().equalsIgnoreCase("/pages/user/profile.xhtml")) {
			NavigationHandler navigationHandler = FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
			navigationHandler.handleNavigation(FacesContext.getCurrentInstance(), null, "/pages/user/profile.xhtml?faces-redirect=true");
			FacesContext.getCurrentInstance().responseComplete();
		}
	}
	
	public void languageValueChangeListener(ValueChangeEvent valueChangeEvent) {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: languageValueChangeListener");
		if (valueChangeEvent != null && valueChangeEvent.getNewValue() != null && !valueChangeEvent.getNewValue().toString().trim().equalsIgnoreCase("")) {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			session.setAttribute("languageforlocale", valueChangeEvent.getNewValue().toString());
			FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(valueChangeEvent.getNewValue().toString()));
		}
	}
	
	public void onPageChange() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: onPageChange");
		if (selPage != null && !selPage.trim().equalsIgnoreCase("")) {
			FacesContext context = FacesContext.getCurrentInstance();
			NavigationHandler navigationHandler = context.getApplication().getNavigationHandler();
			navigationHandler.handleNavigation(context, null, selPage + "?faces-redirect=true");
		}
	}
	
	public void onMasterChange() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: onMasterChange");
		if (selPage != null && !selPage.trim().equalsIgnoreCase("")) {
			FacesContext context = FacesContext.getCurrentInstance();
			NavigationHandler navigationHandler = context.getApplication().getNavigationHandler();
			navigationHandler.handleNavigation(context, null, selPage + "?faces-redirect=true");
		}
	}
	
	public String getLanguage() {
		return language;
	}
	
	public void setLanguage(String language) {
		this.language = language;
	}
	
	public boolean isUserMenu() {
		googleLogin();
		userMenu = (IclubWebHelper.getObjectIntoSession(BUNDLE.getString("logged.in.user.id")) != null);
		return userMenu;
	}
	
	public static String getAuthURL(String authCode) {
		return "https://graph.facebook.com/oauth/access_token?client_id=" + BUNDLE.getString("fb.client_id") + "&redirect_uri=" + BUNDLE.getString("fb.redirect_uri") + "&client_secret=" + BUNDLE.getString("fb.secret") + "&code=" + authCode;
	}
	
	private String readURL(URL url) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		InputStream is = url.openStream();
		int r;
		while ((r = is.read()) != -1) {
			baos.write(r);
		}
		return new String(baos.toByteArray());
	}
	
	public void googleLogin() {
		
		try {
			
			HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			String code = request.getParameter("code");
			String cohortInviteId = request.getParameter("cohortInviteId");
			String redirect = null;
			String state = request.getParameter("state");
			String from = request.getParameter("from");
			request.removeAttribute("code");
			String verifier = request.getParameter("oauth_verifier");
			if (state != null && !state.trim().equalsIgnoreCase("")) {
				try {
					
					LOGGER.info("Class :: " + this.getClass() + " :: state try block");
					byte[] decodedURLAsBytes = Base64.decodeBase64(state);
					state = new String(decodedURLAsBytes, "utf-8");
					JsonObject jsonGet = (JsonObject) new JsonParser().parse(state);
					if (jsonGet.has("cohortInviteId")) {
						cohortInviteId = jsonGet.get("cohortInviteId").toString();
						cohortInviteId = cohortInviteId.replace("\"", "");
					}
					
					if (jsonGet.has("redirect")) {
						redirect = jsonGet.get("redirect").toString();
						redirect = redirect.replace("\"", "");
					}
					if (jsonGet.has("from")) {
						from = jsonGet.get("from").toString();
						from = from.replace("\"", "");
					}
				} catch (Exception e) {
					LOGGER.error(e, e);
				}
			}
			
			if (verifier != null && !verifier.trim().equalsIgnoreCase("") && (preVerifier == null || !preVerifier.equalsIgnoreCase(verifier))) {
				preVerifier = verifier;
				Twitter twitter = (Twitter) request.getSession().getAttribute("twitter");
				LOGGER.info("TwitterCallbackServlet:requestToken");
				RequestToken requestToken = (RequestToken) request.getSession().getAttribute("requestToken");
				LOGGER.info("TwitterCallbackServlet:requestToken:" + requestToken);
				
				LOGGER.info("verifier :" + verifier);
				try {
					AccessToken access_token = twitter.getOAuthAccessToken(requestToken, verifier);
					String screenName = twitter.getScreenName();
					request.getSession().removeAttribute("requestToken");
					LOGGER.info(access_token.getScreenName() + "__________" + screenName);
					
					User user = twitter.showUser(screenName);
					if (user != null) {
						
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (code != null && (preCode == null || !preCode.equalsIgnoreCase(code)) && from != null && from.trim().equalsIgnoreCase("outlook")) {
				preCode = code;
				HttpPost post = new HttpPost("https://login.live.com/oauth20_token.srf");
				
				List<NameValuePair> arguments = new ArrayList<>(3);
				arguments.add(new BasicNameValuePair("grant_type", BUNDLE.getString("htm.grant_type")));
				arguments.add(new BasicNameValuePair("redirect_uri", BUNDLE.getString("htm.redirect_uri")));
				arguments.add(new BasicNameValuePair("client_secret", BUNDLE.getString("htm.client_secret")));
				arguments.add(new BasicNameValuePair("client_id", BUNDLE.getString("htm.client_id")));
				arguments.add(new BasicNameValuePair("code", code));
				try {
					java.util.Base64.Encoder encoder = java.util.Base64.getEncoder();
					String normalString = BUNDLE.getString("htm.client_id") + ":" + BUNDLE.getString("htm.client_secret");
					String encodedValue = encoder.encodeToString(normalString.getBytes(StandardCharsets.UTF_8));
					post.setHeader("Authorization", "Basic " + encodedValue);
					post.setHeader("Content-Type", "application/x-www-form-urlencoded");
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					HttpClient httpsClient = new DefaultHttpClient();
					post.setEntity(new UrlEncodedFormEntity(arguments));
					HttpResponse outlookResponse = httpsClient.execute(post);
					String outputString = EntityUtils.toString(outlookResponse.getEntity());
					SocialAuthResponse socialAuthResponse = new Gson().fromJson(outputString, SocialAuthResponse.class);
					
					if (socialAuthResponse != null && (redirect == null || redirect.trim().equalsIgnoreCase(""))) {
						HttpClient client = new DefaultHttpClient();
						HttpGet outlookRequest = new HttpGet("https://apis.live.net/v5.0/me?access_token=" + socialAuthResponse.getAccess_token());
						HttpResponse response = client.execute(outlookRequest);
						outputString = EntityUtils.toString(response.getEntity());
						
						OutLookUserProfileBean data = new Gson().fromJson(outputString, OutLookUserProfileBean.class);
						
						if (data != null) {
							IclubPersonModel model = new IclubPersonModel();
							
							model.setPId(UUID.randomUUID().toString());
							model.setPEmail(data.getEmails().getPreferred());
							model.setPFName(data.getFirst_name());
							model.setPLName(data.getLast_name());
							model.setPGender(data.getGender());
							model.setPCrtdDt(new Date(System.currentTimeMillis()));
							model.setIclubPerson(1 + "");
							
							if (checkExistingUserorNot(model, data.getId().replace("\"", ""), "OUTLOOK", socialAuthResponse.getAccess_token())) {
								WebClient webClient = IclubWebHelper.createCustomClient(U_BASE_URL + "add");
								
								ResponseModel outLookResponse = webClient.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
								webClient.close();
								
								if (outLookResponse.getStatusCode() == 0) {
									updatePassword(model, socialAuthResponse.getAccess_token(), "OUTLOOK", data.getId().replace("\"", ""), cohortInviteId);
									
								} else {
									IclubWebHelper.addMessage("Fail :: " + outLookResponse.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
								}
								
							}
							
						}
					} else if ((redirect != null && !redirect.trim().equalsIgnoreCase(""))) {
						newInviteRedirect(socialAuthResponse.getAccess_token(), from);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			} else if (code != null && (preCode == null || !preCode.equalsIgnoreCase(code)) && from != null && from.trim().equalsIgnoreCase("fb")) {
				preCode = code;
				String access_token = null;
				String authURL = getAuthURL(code);
				URL url = new URL(authURL);
				try {
					String result = readURL(url);
					Integer expires = null;
					String[] pairs = result.split("&");
					for (String pair : pairs) {
						String[] kv = pair.split("=");
						if (kv.length != 2) {
							throw new RuntimeException("Unexpected auth response");
						} else {
							if (kv[0].equals("access_token")) {
								access_token = kv[1];
							}
							if (kv[0].equals("expires")) {
								expires = Integer.valueOf(kv[1]);
							}
						}
					}
					if (access_token != null && expires != null) {
						
						String graph = null;
						try {
							
							String profileUrl = "https://graph.facebook.com/me?access_token=" + access_token;
							URL u = new URL(profileUrl);
							URLConnection c = u.openConnection();
							BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream()));
							String inputLine;
							StringBuffer b = new StringBuffer();
							while ((inputLine = in.readLine()) != null)
								b.append(inputLine + "\n");
							in.close();
							graph = b.toString();
							JSONObject json = new JSONObject(graph);
							
							IclubPersonModel model = new IclubPersonModel();
							String providerId = json.getString("id");
							if ((redirect == null || redirect.trim().equalsIgnoreCase(""))) {
								model.setPId(UUID.randomUUID().toString());
								if (json.has("email")) {
									model.setPEmail(json.getString("email"));
								}
								model.setPFName(json.getString("first_name"));
								model.setPLName(json.getString("last_name"));
								if (json.has("gender"))
									model.setPGender(json.getString("gender") != null ? json.getString("gender").substring(0, 1).toUpperCase() : null);
								model.setPCrtdDt(new Date(System.currentTimeMillis()));
								model.setIclubPerson(1 + "");
								
								SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
								try {
									if (json.has("birthday")) {
										
										Date date = formatter.parse(json.get("birthday").toString().replace("\"", ""));
										model.setPDob(new Date(date.getTime()));
									}
								} catch (ParseException e) {
									e.printStackTrace();
								}
								if (checkExistingUserorNot(model, providerId, "FB", access_token)) {
									WebClient client = IclubWebHelper.createCustomClient(U_BASE_URL + "add");
									
									ResponseModel response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
									client.close();
									if (response.getStatusCode() == 0) {
										updatePassword(model, access_token, "FB", providerId, cohortInviteId);
										
									} else {
										IclubWebHelper.addMessage("Fail :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
									}
								}
							} else if ((redirect != null && !redirect.trim().equalsIgnoreCase(""))) {
								newInviteRedirect(access_token, from);
							}
							
						} catch (Exception e) {
							e.printStackTrace();
							throw new RuntimeException("ERROR in getting FB graph data. " + e);
						}
						
					}
					
					else {
						throw new RuntimeException("Access token and expires not found");
					}
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			} else if (code != null && (preCode == null || !preCode.equalsIgnoreCase(code)) && from != null && from.trim().equalsIgnoreCase("yahoo")) {
				preCode = code;
				String encodedValue = "";
				HttpClient client = new DefaultHttpClient();
				HttpPost post = new HttpPost("https://api.login.yahoo.com/oauth2/get_token");
				
				List<NameValuePair> arguments = new ArrayList<>(3);
				arguments.add(new BasicNameValuePair("grant_type", Y_BUNDLE.getString("grant_type")));
				arguments.add(new BasicNameValuePair("redirect_uri", Y_BUNDLE.getString("redirect_uri")));
				arguments.add(new BasicNameValuePair("client_secret", Y_BUNDLE.getString("client_secret")));
				arguments.add(new BasicNameValuePair("client_id", Y_BUNDLE.getString("client_id")));
				arguments.add(new BasicNameValuePair("code", code));
				try {
					java.util.Base64.Encoder encoder = java.util.Base64.getEncoder();
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
						List<YahooMailsBean> mailsList = mapper.readValue(emails.toString(), TypeFactory.collectionType(List.class, YahooMailsBean.class));
						if ((redirect == null || redirect.trim().equalsIgnoreCase(""))) {
							IclubPersonModel model = new IclubPersonModel();
							
							model.setPId(UUID.randomUUID().toString());
							model.setPEmail(mailsList.get(0).getPrimary() != null && (mailsList.get(0).getPrimary().equalsIgnoreCase("true") || mailsList.size() == 1) ? mailsList.get(0).getHandle() : mailsList.get(1).getHandle());
							model.setPFName(jsonGet.get("givenName").toString().replace("\"", ""));
							model.setPLName(jsonGet.get("familyName").toString().replace("\"", ""));
							model.setPGender(jsonGet.get("gender") != null ? jsonGet.get("gender").toString().replace("\"", "") : "");
							model.setPCrtdDt(new Date(System.currentTimeMillis()));
							model.setIclubPerson(1 + "");
							
							SimpleDateFormat formatter = new SimpleDateFormat("yyyy/dd/MM");
							try {
								Date date = formatter.parse(jsonGet.get("birthYear").toString().replace("\"", "") + "/" + jsonGet.get("birthdate").toString().replace("\"", ""));
								model.setPDob(new Date(date.getTime()));
							} catch (Exception e) {
								
							}
							if (checkExistingUserorNot(model, xoauth_yahoo_guid.replace("\"", ""), "YAHOO", access_token)) {
								WebClient webClient = IclubWebHelper.createCustomClient(U_BASE_URL + "add");
								
								ResponseModel response = webClient.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
								webClient.close();
								
								if (response.getStatusCode() == 0) {
									updatePassword(model, access_token, "YAHOO", xoauth_yahoo_guid.replace("\"", ""), cohortInviteId);
									
								} else {
									IclubWebHelper.addMessage("Fail :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
								}
							}
						} else {
							newInviteRedirect(access_token, from);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			else if (code != null && (preCode == null || !preCode.equalsIgnoreCase(code)) && from != null && from.trim().equalsIgnoreCase("GOOGLE")) {
				request.removeAttribute("code");
				preCode = code;
				String urlParameters = "code=" + code + "&client_id=" + BUNDLE.getString("client_id") + "&client_secret=" + BUNDLE.getString("client_secret") + "&redirect_uri=" + BUNDLE.getString("redirect_uri") + "&grant_type=" + BUNDLE.getString("grant_type");
				
				URL url = new URL("https://accounts.google.com/o/oauth2/token");
				URLConnection urlConn = url.openConnection();
				urlConn.setDoOutput(true);
				OutputStreamWriter writer = new OutputStreamWriter(urlConn.getOutputStream());
				writer.write(urlParameters);
				writer.flush();
				writer.close();
				
				String line, outputString = "";
				BufferedReader reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
				while ((line = reader.readLine()) != null) {
					outputString += line;
				}
				
				JsonObject json = (JsonObject) new JsonParser().parse(outputString);
				String access_token = json.get("access_token").getAsString();
				if ((redirect == null || redirect.trim().equalsIgnoreCase(""))) {
					
					try {
						String callUrl = "https://www.google.com/m8/feeds/contacts/default/full?access_token=" + access_token;
						url = new URL(callUrl);
						urlConn = url.openConnection();
						outputString = "";
						reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
						while ((line = reader.readLine()) != null) {
							outputString += line;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					try {
						
						String callUrl1 = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=" + access_token;
						url = new URL(callUrl1);
						urlConn = url.openConnection();
						outputString = "";
						reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
						while ((line = reader.readLine()) != null) {
							outputString += line;
						}
						GooglePojo data = new Gson().fromJson(outputString, GooglePojo.class);
						reader.close();
						
						callUrl1 = "https://www.googleapis.com/plus/v1/people/" + data.getId() + "?fields=birthday&access_token=" + access_token;
						url = new URL(callUrl1);
						urlConn = url.openConnection();
						outputString = "";
						
						reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
						while ((line = reader.readLine()) != null) {
							outputString += line;
						}
						if (data != null && data.getEmail() != null) {
							IclubPersonModel model = new IclubPersonModel();
							model.setPId(UUID.randomUUID().toString());
							model.setPEmail(data.getEmail());
							model.setPFName(data.getName());
							model.setPLName(data.getFamily_name());
							model.setPGender(data.getGender() != null ? data.getGender().substring(0, 1).toUpperCase() : null);
							model.setPCrtdDt(new Date(System.currentTimeMillis()));
							model.setIclubPerson(1 + "");
							
							SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
							try {
								JsonObject jsonGet = (JsonObject) new JsonParser().parse(outputString);
								Date date = formatter.parse(jsonGet.get("birthday").toString().replace("\"", ""));
								model.setPDob(new Date(date.getTime()));
							} catch (Exception e) {
								
							}
							if (checkExistingUserorNot(model, data.getId(), "GOOGLE", access_token)) {
								WebClient client = IclubWebHelper.createCustomClient(U_BASE_URL + "add");
								
								ResponseModel response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
								client.close();
								
								if (response.getStatusCode() == 0) {
									
									updatePassword(model, access_token, "GOOGLE", data.getId(), cohortInviteId);
									
								} else {
									IclubWebHelper.addMessage("Fail :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
								}
							}
							
						}
					} catch (Exception e) {
						LOGGER.error(e, e);
					}
					
				} else {
					newInviteRedirect(access_token, from);
				}
			}
			
		} catch (MalformedURLException e) {
			LOGGER.error(e, e);
		} catch (ProtocolException e) {
			LOGGER.error(e, e);
		} catch (IOException e) {
			LOGGER.error(e, e);
		}
	}
	
	public void newInviteRedirect(String access_token, String from) {
		IclubWebHelper.addObjectIntoSession("key", access_token);
		IclubWebHelper.addObjectIntoSession("newInvites", "true");
		if (IclubWebHelper.getObjectIntoSession(BUNDLE.getString("logged.in.user.id")) != null) {
			String userId = IclubWebHelper.getObjectIntoSession(BUNDLE.getString("logged.in.user.id")).toString();
			WebClient personClient = IclubWebHelper.createCustomClient(U_BASE_URL + "get/" + userId);
			IclubPersonModel personModel = personClient.accept(MediaType.APPLICATION_JSON).get(IclubPersonModel.class);
			personClient.close();
			IclubWebHelper.addObjectIntoSession("cohortId", personModel.getIclubCohort());
		}
		
		IclubWebHelper.addObjectIntoSession(BUNDLE.getString("socail.access.token.expires"), new Date(System.currentTimeMillis() + 55 * 60 * 1000));
		IclubWebHelper.addObjectIntoSession(BUNDLE.getString("socail.access.token"), access_token);
		IclubWebHelper.addObjectIntoSession(BUNDLE.getString("socail.access.provider"), from);
		
		NavigationHandler navigationHandler = FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
		navigationHandler.handleNavigation(FacesContext.getCurrentInstance(), null, "/pages/admin/cohorts/newCohortInvites.xhtml?faces-redirect=true&from=" + from + "&key=" + access_token);
	}
	
	public ResponseModel updatePassword(IclubPersonModel personModel, String access_token, String from, String guid, String cohortInviteId) {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: updatePassword");
		try {
			
			IclubLoginModel model = new IclubLoginModel();
			WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "add");
			model.setLId(UUID.randomUUID().toString());
			
			model.setLCrtdDt(new Date(System.currentTimeMillis()));
			model.setLLastDate(new Date(System.currentTimeMillis()));
			model.setLName(personModel.getPEmail());
			model.setIclubPersonAByLCrtdBy(personModel.getPId());
			model.setIclubPersonBByLPersonId(personModel.getPId());
			model.setIclubRoleType(2l);
			model.setLProviderId(guid);
			model.setLProviderCd(from);
			
			ResponseModel response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
			if (response.getStatusCode() == 0) {
				IclubWebHelper.addObjectIntoSession(BUNDLE.getString("logged.in.user.id"), model.getIclubPersonBByLPersonId());
				IclubWebHelper.addObjectIntoSession(BUNDLE.getString("logged.in.user.scname"), model.getLName());
				IclubWebHelper.addObjectIntoSession(BUNDLE.getString("logged.in.user.name"), personModel.getPFName() + (personModel.getPLName() == null ? "" : personModel.getPLName() + " "));
				IclubWebHelper.addObjectIntoSession(BUNDLE.getString("logged.in.role.id"), model.getIclubRoleType());
				IclubWebHelper.addObjectIntoSession("googlelogin", true);
				IclubWebHelper.addObjectIntoSession("key", access_token);
				IclubWebHelper.addObjectIntoSession("cohortInviteId", cohortInviteId);
				IclubWebHelper.addObjectIntoSession(BUNDLE.getString("socail.access.token.expires"), new Date(System.currentTimeMillis() + 55 * 60 * 1000));
				IclubWebHelper.addObjectIntoSession(BUNDLE.getString("socail.access.token"), access_token);
				IclubWebHelper.addObjectIntoSession(BUNDLE.getString("socail.access.provider"), from);
				NavigationHandler navigationHandler = FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
				
				if (guid != null) {
					navigationHandler.handleNavigation(FacesContext.getCurrentInstance(), null, "/pages/admin/cohorts/allCohorts.xhtml?faces-redirect=true&from=" + from + "&key=" + access_token + "&guid=" + guid);
				} else {
					navigationHandler.handleNavigation(FacesContext.getCurrentInstance(), null, "/pages/admin/cohorts/allCohorts.xhtml?faces-redirect=true&from=" + from + "&key=" + access_token);
				}
				IclubWebHelper.addMessage("Person Registered successfully", FacesMessage.SEVERITY_INFO);
			} else {
				IclubWebHelper.addMessage("Fail :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
			}
			
			return response;
			
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage("Fail :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
		return new ResponseModel();
	}
	
	public boolean checkExistingUserorNot(IclubPersonModel model, String providerId, String providerCd, String access_toke) {
		WebClient client = null;
		IclubLoginModel loginModel = null;
		try {
			
			client = IclubWebHelper.createCustomClient(BASE_URL + "/socailLogin/" + model.getPEmail() + "/" + providerId + "/" + providerCd);
			loginModel = client.get(IclubLoginModel.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (loginModel != null && loginModel.getLId() != null) {
			
			client = IclubWebHelper.createCustomClient(U_BASE_URL + "get/" + loginModel.getIclubPersonBByLPersonId());
			model = client.get(IclubPersonModel.class);
			NavigationHandler navigationHandler = FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
			IclubWebHelper.addObjectIntoSession(BUNDLE.getString("logged.in.user.id"), model.getPId());
			IclubWebHelper.addObjectIntoSession(BUNDLE.getString("logged.in.login.id"), loginModel.getLId());
			IclubWebHelper.addObjectIntoSession(BUNDLE.getString("logged.in.user.scname"), loginModel.getLName());
			IclubWebHelper.addObjectIntoSession(BUNDLE.getString("socail.access.token"), access_toke);
			IclubWebHelper.addObjectIntoSession(BUNDLE.getString("socail.access.provider"), providerCd);
			IclubWebHelper.addObjectIntoSession(BUNDLE.getString("logged.in.user.name"), model.getPFName() + (model.getPLName() == null ? "" : model.getPLName() + " "));
			IclubWebHelper.addObjectIntoSession(BUNDLE.getString("logged.in.role.id"), loginModel.getIclubRoleType());
			if (loginModel.getLPasswd() == null || loginModel.getLPasswd().trim().equalsIgnoreCase("")) {
				IclubWebHelper.addObjectIntoSession("social_update_profile", true);
				navigationHandler.handleNavigation(FacesContext.getCurrentInstance(), null, "/pages/user/profile.xhtml?faces-redirect=true");
				
			} else {
				navigationHandler.handleNavigation(FacesContext.getCurrentInstance(), null, "/pages/dashboard/user/main.xhtml?faces-redirect=true");
			}
			
			return false;
			
		} else {
			return true;
		}
	}
	
	public void setUserMenu(boolean userMenu) {
		this.userMenu = userMenu;
	}
	
	public String getSelPage() {
		return selPage;
	}
	
	public void setSelPage(String selPage) {
		this.selPage = selPage;
	}
	
	public boolean isManageCohorts() {
		if (!manageCohorts && IclubWebHelper.getObjectIntoSession(BUNDLE.getString("logged.in.user.id")) != null) {
			WebClient client = IclubWebHelper.createCustomClient(CH_BASE_URL + "/get/user/" + IclubWebHelper.getObjectIntoSession(BUNDLE.getString("logged.in.user.id")).toString());
			Collection<? extends IclubCohortModel> models = new ArrayList<IclubCohortModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubCohortModel.class));
			client.close();
			if (models != null && models.size() > 0) {
				manageCohorts = true;
			}
		}
		return manageCohorts;
	}
	
	public void setManageCohorts(boolean manageCohorts) {
		this.manageCohorts = manageCohorts;
	}
	
}
