package za.co.iclub.pss.web.controller;

import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import za.co.iclub.pss.web.util.IclubWebHelper;

@ManagedBean(name = "iclubMenuController")
@SessionScoped
public class IclubMenuController implements Serializable {

	private static final long serialVersionUID = -5155234741934732730L;
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("iclub-web");
	private static final Logger LOGGER = Logger.getLogger(IclubMenuController.class);
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
		userMenu = (IclubWebHelper.getObjectIntoSession(BUNDLE.getString("logged.in.user.id")) != null);
		return userMenu;
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
