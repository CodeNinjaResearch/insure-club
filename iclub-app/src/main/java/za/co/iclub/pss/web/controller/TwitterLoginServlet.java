package za.co.iclub.pss.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterLoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		
		cb.setDebugEnabled(true).setOAuthConsumerKey("oINoOag2pIh1G7di2CIButdAR").setOAuthConsumerSecret("bKu1INz6edjn2l4YxN0zK12tuiHEnzuLklobhaQ32gAa4zQ3N1").setOAuthRequestTokenURL("https://api.twitter.com/oauth/request_token").setOAuthAuthorizationURL(("https://api.twitter.com/oauth/authorize")).setOAuthAccessTokenURL(("https://api.twitter.com/oauth/access_token"));
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();
		request.getSession().setAttribute("twitter", twitter);
		try {
			StringBuffer callbackURL = request.getRequestURL();
			int index = callbackURL.lastIndexOf("/");
			callbackURL.replace(index, callbackURL.length(), "").append("/TwitterCallback");
			
			RequestToken requestToken = twitter.getOAuthRequestToken(callbackURL.toString());
			request.getSession().setAttribute("requestToken", requestToken);
			response.sendRedirect(requestToken.getAuthenticationURL());
			
		} catch (TwitterException e) {
			throw new ServletException(e);
		}
		
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
