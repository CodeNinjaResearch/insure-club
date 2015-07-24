package za.co.iclub.pss.scheduler;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SocialRedirectServlet
 */
@WebServlet("/SocialRedirectServlet")
public class SocialRedirectServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("iclub-web");
	
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
					String redirectUrl = "https://accounts.google.com/o/oauth2/auth?scope=" + BUNDLE.getString("scope") + "&redirect_uri=" + BUNDLE.getString("redirect_uri") + (cohortInviteId != null ? "&cohortInviteId=" + cohortInviteId : "") + "&response_type=code&client_id=" + BUNDLE.getString("client_id") + "&approval_prompt=force";
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
