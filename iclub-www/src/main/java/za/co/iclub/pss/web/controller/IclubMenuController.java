package za.co.iclub.pss.web.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.util.Date;
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

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import za.co.iclub.pss.web.bean.GooglePojo;
import za.co.iclub.pss.web.util.IclubWebHelper;
import za.co.iclub.pss.ws.model.IclubLoginModel;
import za.co.iclub.pss.ws.model.IclubPersonModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

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
	
	public void googleLogin() {
		
		System.out.println("entering doGet");
		try {
			// get code
			HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			String code = request.getParameter("code");
			// format parameters to post
			if (code != null) {
				String urlParameters = "code=" + code + "&client_id=386352872692-uknquacomkku9e75tn2jgpjh5h27ognc.apps.googleusercontent.com" + "&client_secret=X1xK4cHAU2ewqmYk67eGYL0s" + "&redirect_uri=http://localhost:8080/iclub-www/Oauth2callback" + "&grant_type=authorization_code";
				
				// post parameters
				URL url = new URL("https://accounts.google.com/o/oauth2/token");
				URLConnection urlConn = url.openConnection();
				urlConn.setDoOutput(true);
				OutputStreamWriter writer = new OutputStreamWriter(urlConn.getOutputStream());
				writer.write(urlParameters);
				writer.flush();
				
				// get output in outputString
				String line, outputString = "";
				BufferedReader reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
				while ((line = reader.readLine()) != null) {
					outputString += line;
				}
				System.out.println(outputString);
				
				// get Access Token
				JsonObject json = (JsonObject) new JsonParser().parse(outputString);
				String access_token = json.get("access_token").getAsString();
				System.out.println(access_token);
				
				// get User Info
				try {
					String callUrl = "https://www.google.com/m8/feeds/contacts/default/full?max-results=8&access_token=" + access_token;
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
					System.out.println(outputString);
					GooglePojo data = new Gson().fromJson(outputString, GooglePojo.class);
					writer.close();
					reader.close();
					
					if (data != null && data.getEmail() != null) {
						IclubPersonModel model = new IclubPersonModel();
						model.setPId(UUID.randomUUID().toString());
						model.setPEmail(data.getEmail());
						model.setPFName(data.getName());
						model.setPLName(data.getFamily_name());
						model.setPGender(data.getGender());
						model.setPCrtdDt(new Timestamp(System.currentTimeMillis()));
						model.setIclubPerson(1 + "");
						WebClient client = IclubWebHelper.createCustomClient(U_BASE_URL + "add");
						
						ResponseModel response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
						client.close();
						
						if (response.getStatusCode() == 0) {
							
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
		// catch (ServiceException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		System.out.println("leaving doGet");
	}
	
	public ResponseModel updatePassword(IclubPersonModel personModel) {
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
				NavigationHandler navigationHandler = FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
				navigationHandler.handleNavigation(FacesContext.getCurrentInstance(), null, "/templates/home.xhtml?faces-redirect=true");
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
	
}
