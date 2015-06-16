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
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.log4j.Logger;
import org.primefaces.json.JSONObject;

import za.co.iclub.pss.web.bean.GooglePojo;
import za.co.iclub.pss.web.util.IclubWebHelper;
import za.co.iclub.pss.ws.model.IclubLoginModel;
import za.co.iclub.pss.ws.model.IclubPersonModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@ManagedBean(name = "iclubMenuController")
@SessionScoped
public class IclubMenuController implements Serializable {
	
	private static final long serialVersionUID = -5155234741934732730L;
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("iclub-web");
	private static final Logger LOGGER = Logger.getLogger(IclubMenuController.class);
	private static final String BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubLoginService/";
	private static final String U_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubPersonService/";
	private String language;
	private boolean userMenu;
	private String selPage;
	String preCode = "";
	
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
		
		System.out.println("entering doGet");
		try {
			
			// get code
			HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			String code = request.getParameter("code");
			
			String from = request.getParameter("from");
			// format parameters to post
			request.removeAttribute("code");
			if (code != null && (preCode == null || !preCode.equalsIgnoreCase(code)) && from != null && !from.trim().equalsIgnoreCase("")) {
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
							
							String g = "https://graph.facebook.com/me?access_token=" + access_token;
							URL u = new URL(g);
							URLConnection c = u.openConnection();
							BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream()));
							String inputLine;
							StringBuffer b = new StringBuffer();
							while ((inputLine = in.readLine()) != null)
								b.append(inputLine + "\n");
							in.close();
							graph = b.toString();
							JSONObject json = new JSONObject(graph);
							json.getString("id");
							IclubPersonModel model = new IclubPersonModel();
							model.setPId(UUID.randomUUID().toString());
							if (json.has("email")) {
								model.setPEmail(json.getString("email"));
							}
							model.setPFName(json.getString("first_name"));
							model.setPLName(json.getString("last_name"));
							if (json.has("gender"))
								model.setPGender(json.getString("gender") != null ? json.getString("gender").substring(0, 1).toUpperCase() : null);
							model.setPCrtdDt(new Timestamp(System.currentTimeMillis()));
							model.setIclubPerson(1 + "");
							
							SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
							try {
								if (json.has("birthday")) {
									
									Date date = formatter.parse(json.get("birthday").toString().replace("\"", ""));
									model.setPDob(new Timestamp(date.getTime()));
								}
							} catch (ParseException e) {
								e.printStackTrace();
							}
							
							WebClient client = IclubWebHelper.createCustomClient(U_BASE_URL + "add");
							
							ResponseModel response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
							client.close();
							if (response.getStatusCode() == 0) {
								updatePassword(model, access_token, "FB");
								
							} else {
								IclubWebHelper.addMessage("Fail :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
							}
						} catch (Exception e) {
							e.printStackTrace();
							throw new RuntimeException("ERROR in getting FB graph data. " + e);
						}
						
					} else {
						throw new RuntimeException("Access token and expires not found");
					}
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			} else if (code != null) {
				request.removeAttribute("code");
				String urlParameters = "code=" + code + "&client_id=" + BUNDLE.getString("client_id") + "&client_secret=" + BUNDLE.getString("client_secret") + "&redirect_uri=" + BUNDLE.getString("redirect_uri") + "&grant_type=" + BUNDLE.getString("grant_type");
				
				// post parameters
				URL url = new URL("https://accounts.google.com/o/oauth2/token");
				URLConnection urlConn = url.openConnection();
				urlConn.setDoOutput(true);
				OutputStreamWriter writer = new OutputStreamWriter(urlConn.getOutputStream());
				writer.write(urlParameters);
				writer.flush();
				writer.close();
				// get output in outputString
				String line, outputString = "";
				BufferedReader reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
				while ((line = reader.readLine()) != null) {
					outputString += line;
				}
				
				// get Access Token
				JsonObject json = (JsonObject) new JsonParser().parse(outputString);
				String access_token = json.get("access_token").getAsString();
				System.out.println(access_token);
				
				// get User Info
				try {
					String callUrl = "https://www.google.com/m8/feeds/contacts/default/full?access_token=" + access_token;
					url = new URL(callUrl);
					urlConn = url.openConnection();
					outputString = "";
					reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
					while ((line = reader.readLine()) != null) {
						outputString += line;
					}
					System.out.println(outputString);
				} catch (Exception e) {
					e.printStackTrace();
				}
				// get User Info
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
						model.setPCrtdDt(new Timestamp(System.currentTimeMillis()));
						model.setIclubPerson(1 + "");
						
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
						try {
							JsonObject jsonGet = (JsonObject) new JsonParser().parse(outputString);
							Date date = formatter.parse(jsonGet.get("birthday").toString().replace("\"", ""));
							model.setPDob(new Timestamp(date.getTime()));
						} catch (ParseException e) {
							e.printStackTrace();
						}
						WebClient client = IclubWebHelper.createCustomClient(U_BASE_URL + "add");
						
						ResponseModel response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
						client.close();
						
						if (response.getStatusCode() == 0) {
							updatePassword(model, access_token, "google");
							
						} else {
							IclubWebHelper.addMessage("Fail :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
						}
						
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			// Convert JSON response into Pojo class
			
		} catch (MalformedURLException e) {
			System.out.println(e);
		} catch (ProtocolException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
		System.out.println("leaving doGet");
	}
	
	public ResponseModel updatePassword(IclubPersonModel personModel, String access_token, String from) {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: updatePassword");
		try {
			
			IclubLoginModel model = new IclubLoginModel();
			WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "add");
			model.setLId(UUID.randomUUID().toString());
			
			model.setLCrtdDt(new Date(System.currentTimeMillis()));
			model.setLLastDate(new Date(System.currentTimeMillis()));
			model.setLName(personModel.getPEmail());
			model.setIclubPersonByLCrtdBy(personModel.getPId());
			model.setIclubPersonByLPersonId(personModel.getPId());
			model.setIclubRoleType(2l);
			
			ResponseModel response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
			if (response.getStatusCode() == 0) {
				IclubWebHelper.addObjectIntoSession(BUNDLE.getString("logged.in.user.id"), model.getIclubPersonByLPersonId());
				IclubWebHelper.addObjectIntoSession(BUNDLE.getString("logged.in.user.scname"), model.getLName());
				IclubWebHelper.addObjectIntoSession(BUNDLE.getString("logged.in.user.name"), personModel.getPFName() + (personModel.getPLName() == null ? "" : personModel.getPLName() + " "));
				IclubWebHelper.addObjectIntoSession(BUNDLE.getString("logged.in.role.id"), 1l);
				IclubWebHelper.addObjectIntoSession("googlelogin", true);
				NavigationHandler navigationHandler = FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
				navigationHandler.handleNavigation(FacesContext.getCurrentInstance(), null, "/pages/admin/cohorts/allCohorts.xhtml?faces-redirect=true&from=" + from + "&key=" + access_token);
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
	
	public void setUserMenu(boolean userMenu) {
		this.userMenu = userMenu;
	}
	
	public String getSelPage() {
		return selPage;
	}
	
	public void setSelPage(String selPage) {
		this.selPage = selPage;
	}
	
	public static void main(String[] args) {
		List<SelectItem> list = new ArrayList<SelectItem>();
		SelectItem itema = new SelectItem("aaa", "aaa");
		SelectItem itemb = new SelectItem("bbb", "bbb");
		list.add(itemb);
		list.add(itema);
		
		List<SelectItem> list2 = new ArrayList<SelectItem>();
		list2.add(itemb);
		list.removeAll(list2);
		System.out.println(list);
		
	}
}
