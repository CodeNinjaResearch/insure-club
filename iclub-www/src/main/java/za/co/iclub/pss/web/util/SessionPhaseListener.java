package za.co.iclub.pss.web.util;

import java.util.ResourceBundle;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletResponse;

public class SessionPhaseListener implements PhaseListener {
	
	private static final long serialVersionUID = 5667405053728834122L;
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("iclub-web");
	
	@Override
	public void afterPhase(PhaseEvent pe) {
	}
	
	@Override
	public void beforePhase(PhaseEvent pe) {
		FacesContext context = pe.getFacesContext();
		ResourceBundle bundle = ResourceBundle.getBundle("iclub-web");
		boolean bypassAuth = Boolean.valueOf(bundle.getString("bypass.auth"));
		
		String userSessionId = (String) IclubWebHelper.getObjectIntoSession(BUNDLE.getString("logged.in.user.id"));
		boolean updateProfile = false;
		if (IclubWebHelper.getObjectIntoSession(BUNDLE.getString("logged.in.user.profile.update")) != null) {
			updateProfile = (boolean) IclubWebHelper.getObjectIntoSession(BUNDLE.getString("logged.in.user.profile.update"));
		}
		if (!bypassAuth) {
			if (!context.getViewRoot().getViewId().toLowerCase().contains("login")) {
				try {
					HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
					if (userSessionId == null) {
						context.getExternalContext().invalidateSession();
						response.sendRedirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/templates/login.xhtml");
						FacesContext.getCurrentInstance().responseComplete();
					} else if (!updateProfile) {
						response.sendRedirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/pages/user/register.xhtml?faces-redirect=true");
						FacesContext.getCurrentInstance().responseComplete();
					}
				} catch (Throwable t) {
					
				}
				
			}
		}
	}
	
	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RENDER_RESPONSE;
	}
	
}
