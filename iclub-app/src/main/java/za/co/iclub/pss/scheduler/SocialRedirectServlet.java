package za.co.iclub.pss.scheduler;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;

import za.co.iclub.pss.util.IclubWebHelper;

/**
 * Servlet implementation class SocialRedirectServlet
 */
@WebServlet("/SocialRedirectServlet")
public class SocialRedirectServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("iclub-web");
	private static final ResourceBundle Y_BUNDLE = ResourceBundle.getBundle("yahoo-web");
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SocialRedirectServlet() {
		super();
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			HttpSession session = request.getSession(true);
			System.out.println("---------loca---" + session.getAttribute("loggedin_user_id"));
			
			if (session != null && session.getAttribute("loggedin_user_id") != null) {
				System.out.println("----------");
				response.sendRedirect(request.getContextPath() + "/pages/dashboard/user/main.xhtml");
			}
			
			String from = request.getParameter("from");
			String cohortInviteId = request.getParameter("cohortInvId");
			if (from != null) {
				if (from.equalsIgnoreCase("google")) {
					
					String redirectUrl = "https://accounts.google.com/o/oauth2/auth?scope=" + BUNDLE.getString("scope") + "&redirect_uri=" + BUNDLE.getString("redirect_uri") + "&response_type=code&client_id=" + BUNDLE.getString("client_id") + "&approval_prompt=force&state=" + getState("GOOGLE", cohortInviteId);
					try {
						response.sendRedirect(redirectUrl);
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else if (from.equalsIgnoreCase("yahoo")) {
					
					String redirectUrl = "https://api.login.yahoo.com/oauth2/request_auth?redirect_uri=" + Y_BUNDLE.getString("redirect_uri") + (cohortInviteId != null ? "&cohortInviteId=" + cohortInviteId : "") + "&response_type=code&client_id=" + Y_BUNDLE.getString("client_id") + "&language=en-us&state=" + getState("YAHOO", cohortInviteId);
					try {
						response.sendRedirect(redirectUrl);
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else if (from.equalsIgnoreCase("outlook")) {
					String redirectUrl = "https://login.live.com/oauth20_authorize.srf?scope=" + BUNDLE.getString("htm.scope") + "&redirect_uri=" + BUNDLE.getString("redirect_uri") + "&response_type=code&client_id="+BUNDLE.getString("htm.client_id")+"&state=" + getState("OUTLOOK", cohortInviteId);
					try {
						response.sendRedirect(redirectUrl);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} else {
				response.sendRedirect(BUNDLE.getString("login_uri"));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getState(String from, String cohortInviteId) {
		String state = "{from:" + from + (cohortInviteId != null ? ",cohortInviteId:" + cohortInviteId : "") + "}";
		try {
			if (IclubWebHelper.getObjectIntoSession("newInvite") != null) {
				state = "{from:" + from + (cohortInviteId != null ? ",cohortInviteId:" + cohortInviteId : "") + ",redirect:newInvite}";
			}
			
		} catch (Exception e) {
			
		}
		
		state = Base64.encodeBase64URLSafeString(state.getBytes());
		
		return state;
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	
}
