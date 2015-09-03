package za.co.iclub.pss.util;

import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.FacesException;
//import javax.faces.application.NavigationHandler;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

public class ViewExpiredExceptionHandler extends ExceptionHandlerWrapper {
	
	private ExceptionHandler wrapped;
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("iclub-web");
	
	ViewExpiredExceptionHandler(ExceptionHandler exception) {
		this.wrapped = exception;
	}
	
	@Override
	public ExceptionHandler getWrapped() {
		return wrapped;
	}
	
	@Override
	public void handle() throws FacesException {
		
		final Iterator<ExceptionQueuedEvent> i = getUnhandledExceptionQueuedEvents().iterator();
		while (i.hasNext()) {
			ExceptionQueuedEvent event = i.next();
			ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();
			
			Throwable t = context.getException();
			
			final FacesContext fc = FacesContext.getCurrentInstance();
			final Map<String, Object> requestMap = fc.getExternalContext().getRequestMap();
			// final NavigationHandler nav =
			// fc.getApplication().getNavigationHandler();
			
			try {
				
				if (t instanceof ViewExpiredException) {
					String userSessionId = null;
					try {
						userSessionId = (String) IclubWebHelper.getObjectIntoSession(BUNDLE.getString("logged.in.user.id"));
					} catch (Exception e) {
						
					}
					if (userSessionId == null) {
						requestMap.put("javax.servlet.error.message", "Session expired, try again!");
						String errorPageLocation = "/templates/login.xhtml?faces-redirect=true";
						fc.setViewRoot(fc.getApplication().getViewHandler().createView(fc, errorPageLocation));
						fc.getPartialViewContext().setRenderAll(true);
						fc.renderResponse();
					} else {
						requestMap.put("javax.servlet.error.message", "Session expired, try again!");
						String errorPageLocation = "/pages/dashboard/user/main.xhtml?faces-redirect=true";
						fc.setViewRoot(fc.getApplication().getViewHandler().createView(fc, errorPageLocation));
						fc.getPartialViewContext().setRenderAll(true);
						fc.renderResponse();
					}
					
				} else {
					/*
					 * requestMap.put("javax.servlet.error.message",
					 * t.getMessage()); nav.handleNavigation(fc, null,
					 * "/erro.xhtml");
					 */
				}
				
			} finally {
				i.remove();
			}
		}
		getWrapped().handle();
	}
	
}
