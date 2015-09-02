package za.co.iclub.pss.util;

import java.util.ResourceBundle;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

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
		String socilalogin = (String) IclubWebHelper.getObjectIntoSession("sociallogin");
		if (!bypassAuth) {
			if (!context.getViewRoot().getViewId().toLowerCase().contains("login")) {
				if (userSessionId == null && socilalogin == null) {
					context.getExternalContext().invalidateSession();
					context.getApplication().getNavigationHandler().handleNavigation(context, null, "/templates/login.xhtml?faces-redirect=true");
				}
			}
		}
	}
	
	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RENDER_RESPONSE;
	}
	
}
