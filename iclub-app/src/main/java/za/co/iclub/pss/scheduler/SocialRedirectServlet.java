package za.co.iclub.pss.scheduler;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;

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
			String from = request.getParameter("from");
			if (from != null) {
				if (from.equalsIgnoreCase("google")) {
					String cohortInviteId = request.getParameter("cohortInvId");
					String state = "";
					if (cohortInviteId != null && !cohortInviteId.trim().equalsIgnoreCase(""))
						state = Base64.encodeBase64URLSafeString(("{cohortInviteId :" + cohortInviteId + "}").getBytes());
					System.out.println("-------" + state);
					String redirectUrl = "https://accounts.google.com/o/oauth2/auth?scope=" + BUNDLE.getString("scope") + "&redirect_uri=" + BUNDLE.getString("redirect_uri") + "&response_type=code&client_id=" + BUNDLE.getString("client_id") + "&approval_prompt=force&state=" + state;
					try {
						response.sendRedirect(redirectUrl);
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else if (from.equalsIgnoreCase("yahoo")) {
					String cohortInviteId = request.getParameter("cohortInvId");
					String redirectUrl = "https://api.login.yahoo.com/oauth2/request_auth?redirect_uri=" + Y_BUNDLE.getString("redirect_uri") + (cohortInviteId != null ? "&cohortInviteId=" + cohortInviteId : "") + "&response_type=code&client_id=" + Y_BUNDLE.getString("client_id") + "&language=en-us";
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
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	
}