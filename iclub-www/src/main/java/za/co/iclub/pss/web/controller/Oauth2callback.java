package za.co.iclub.pss.web.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import za.co.iclub.pss.web.bean.GooglePojo;

import com.google.gdata.client.contacts.ContactsService;
import com.google.gdata.data.Link;
import com.google.gdata.data.contacts.ContactEntry;
import com.google.gdata.data.contacts.ContactFeed;
import com.google.gdata.data.contacts.GroupMembershipInfo;
import com.google.gdata.data.extensions.Email;
import com.google.gdata.data.extensions.ExtendedProperty;
import com.google.gdata.data.extensions.Im;
import com.google.gdata.data.extensions.Name;
import com.google.gdata.util.ServiceException;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Oauth2callback extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public Oauth2callback() {
		super();
		
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("entering doGet");
		try {
			// get code
			String code = request.getParameter("code");
			// format parameters to post
			String urlParameters = "code=" + code + "&client_id=386352872692-uknquacomkku9e75tn2jgpjh5h27ognc.apps.googleusercontent.com" + "&client_secret=X1xK4cHAU2ewqmYk67eGYL0s" + "&redirect_uri=http://localhost:8080/iclub-www/Oauth2callback" + "&grant_type=authorization_code";
			
			// post parameters
			URL url = new URL("https://accounts.google.com/o/oauth2/token");
			URLConnection urlConn = url.openConnection();
			urlConn.setDoOutput(true);
			OutputStreamWriter writer = new OutputStreamWriter(urlConn.getOutputStream());
			writer.write(urlParameters);
			writer.flush();
			
			// get output in outputString
			String line, outputString = "";
			BufferedReader reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
			while ((line = reader.readLine()) != null) {
				outputString += line;
			}
			System.out.println(outputString);
			
			// get Access Token
			JsonObject json = (JsonObject) new JsonParser().parse(outputString);
			String access_token = json.get("access_token").getAsString();
			System.out.println(access_token);
			
			// get User Info
			try {
				String callUrl = "https://www.google.com/m8/feeds/contacts/default/full?max-results=8&access_token=" + access_token;
				url = new URL(callUrl);
				urlConn = url.openConnection();
				outputString = "";
				reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
				while ((line = reader.readLine()) != null) {
					outputString += line;
				}
				System.out.println(outputString);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				String callUrl1 = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=" + access_token;
				url = new URL(callUrl1);
				urlConn = url.openConnection();
				outputString = "";
				reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
				while ((line = reader.readLine()) != null) {
					outputString += line;
				}
				System.out.println(outputString);
				GooglePojo data = new Gson().fromJson(outputString, GooglePojo.class);
				System.out.println(data);
				writer.close();
				reader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			// Convert JSON response into Pojo class
			
			ContactsService myService = new ContactsService("iclub");
			URL feedUrl = new URL("https://www.google.com/m8/feeds/contacts/default/full?access_token=" + access_token);
			ContactFeed resultFeed = myService.getFeed(feedUrl, ContactFeed.class);
			// Print the results
			System.out.println(resultFeed.getTitle().getPlainText());
			for (ContactEntry entry : resultFeed.getEntries()) {
				if (entry.hasName()) {
					Name name = entry.getName();
					if (name.hasFullName()) {
						String fullNameToDisplay = name.getFullName().getValue();
						if (name.getFullName().hasYomi()) {
							fullNameToDisplay += " (" + name.getFullName().getYomi() + ")";
						}
						System.out.println("\\\t\\\t" + fullNameToDisplay);
					} else {
						System.out.println("\\\t\\\t (no full name found)");
					}
					if (name.hasNamePrefix()) {
						System.out.println("\\\t\\\t" + name.getNamePrefix().getValue());
					} else {
						System.out.println("\\\t\\\t (no name prefix found)");
					}
					if (name.hasGivenName()) {
						String givenNameToDisplay = name.getGivenName().getValue();
						if (name.getGivenName().hasYomi()) {
							givenNameToDisplay += " (" + name.getGivenName().getYomi() + ")";
						}
						System.out.println("\\\t\\\t" + givenNameToDisplay);
					} else {
						System.out.println("\\\t\\\t (no given name found)");
					}
					if (name.hasAdditionalName()) {
						String additionalNameToDisplay = name.getAdditionalName().getValue();
						if (name.getAdditionalName().hasYomi()) {
							additionalNameToDisplay += " (" + name.getAdditionalName().getYomi() + ")";
						}
						System.out.println("\\\t\\\t" + additionalNameToDisplay);
					} else {
						System.out.println("\\\t\\\t (no additional name found)");
					}
					if (name.hasFamilyName()) {
						String familyNameToDisplay = name.getFamilyName().getValue();
						if (name.getFamilyName().hasYomi()) {
							familyNameToDisplay += " (" + name.getFamilyName().getYomi() + ")";
						}
						System.out.println("\\\t\\\t" + familyNameToDisplay);
					} else {
						System.out.println("\\\t\\\t (no family name found)");
					}
					if (name.hasNameSuffix()) {
						System.out.println("\\\t\\\t" + name.getNameSuffix().getValue());
					} else {
						System.out.println("\\\t\\\t (no name suffix found)");
					}
				} else {
					System.out.println("\t (no name found)");
				}
				System.out.println("Email addresses:");
				for (Email email : entry.getEmailAddresses()) {
					System.out.print(" " + email.getAddress());
					if (email.getRel() != null) {
						System.out.print(" rel:" + email.getRel());
					}
					if (email.getLabel() != null) {
						System.out.print(" label:" + email.getLabel());
					}
					if (email.getPrimary()) {
						System.out.print(" (primary) ");
					}
					System.out.print("\n");
				}
				System.out.println("IM addresses:");
				for (Im im : entry.getImAddresses()) {
					System.out.print(" " + im.getAddress());
					if (im.getLabel() != null) {
						System.out.print(" label:" + im.getLabel());
					}
					if (im.getRel() != null) {
						System.out.print(" rel:" + im.getRel());
					}
					if (im.getProtocol() != null) {
						System.out.print(" protocol:" + im.getProtocol());
					}
					if (im.getPrimary()) {
						System.out.print(" (primary) ");
					}
					System.out.print("\n");
				}
				System.out.println("Groups:");
				for (GroupMembershipInfo group : entry.getGroupMembershipInfos()) {
					String groupHref = group.getHref();
					System.out.println("  Id: " + groupHref);
				}
				System.out.println("Extended Properties:");
				for (ExtendedProperty property : entry.getExtendedProperties()) {
					if (property.getValue() != null) {
						System.out.println("  " + property.getName() + "(value) = " + property.getValue());
					} else if (property.getXmlBlob() != null) {
						System.out.println("  " + property.getName() + "(xmlBlob)= " + property.getXmlBlob().getBlob());
					}
				}
				Link photoLink = entry.getContactPhotoLink();
				String photoLinkHref = photoLink.getHref();
				System.out.println("Photo Link: " + photoLinkHref);
				if (photoLink.getEtag() != null) {
					System.out.println("Contact Photo's ETag: " + photoLink.getEtag());
				}
				System.out.println("Contact's ETag: " + entry.getEtag());
			}
			
		} catch (MalformedURLException e) {
			System.out.println(e);
		} catch (ProtocolException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		System.out.println("leaving doGet");
	}
	
}