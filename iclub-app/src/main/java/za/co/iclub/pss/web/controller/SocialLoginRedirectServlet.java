package za.co.iclub.pss.web.controller;

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
@WebServlet("/SocialLoginRedirect")
public class SocialLoginRedirectServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("iclub-web");
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SocialLoginRedirectServlet() {
		super();
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String code = request.getParameter("code");
			String cohortInviteId = request.getParameter("cohortInviteId");
			String state = request.getParameter("state");
			String from = request.getParameter("from");
			
			if (code != null) {
				
				String redirectUrl = BUNDLE.getString("local_redirect_uri") + "?code=" + code + "&cohortInviteId=" + cohortInviteId + "&state=" + state + "&from=" + from;
				try {
					HttpSession session = request.getSession(false);
					session.setAttribute("sociallogin", "sociallogin");
					redirectUrl = response.encodeRedirectURL(redirectUrl);
					response.sendRedirect(redirectUrl);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			} else {
				response.sendRedirect(BUNDLE.getString("login_uri"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
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
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	
}
