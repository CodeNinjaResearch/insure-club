package za.co.iclub.pss.web.controller;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import za.co.iclub.pss.util.IclubWebHelper;

@ManagedBean
@SessionScoped
@SuppressWarnings("serial")
public class ThemeView implements Serializable {
	
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("iclub-web");
	
	private String color;
	
	private String theme;
	
	private String webTheme;
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public void change(String color) {
		if (color.equals("green"))
			this.color = null;
		else
			this.color = "-" + color;
	}
	
	public String getTheme() {
		
		if (theme == null || theme.trim().equalsIgnoreCase("")) {
			theme = "";
		}
		
		return theme;
	}
	
	public void setTheme(String theme) {
		this.theme = theme;
	}
	
	public String getWebTheme() {
		if (webTheme == null || webTheme.trim().equalsIgnoreCase("")) {
			webTheme = "sentinel";
		}
		return webTheme;
	}
	
	public void setWebTheme(String webTheme) {
		this.webTheme = webTheme;
	}
	
	public String themeAction(String theme) {
		
		if (theme != null && !theme.trim().equalsIgnoreCase("")) {
			
			if (theme.equalsIgnoreCase("spark")) {
				webTheme = "spark";
				this.theme = "-spark";
				
			} else {
				webTheme = theme;
				this.theme = "";
			}
			
			if (IclubWebHelper.getObjectIntoSession(BUNDLE.getString("logged.in.user.id")) == null) {
				return "login" + this.theme;
			} else {
				IclubWebHelper.addObjectIntoSession(BUNDLE.getString("logged.in.user.theme"), this.theme);
			}
			
		}
		return "userDashboard";
		
	}
}
