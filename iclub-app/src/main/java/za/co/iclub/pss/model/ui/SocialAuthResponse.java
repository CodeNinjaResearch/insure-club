package za.co.iclub.pss.model.ui;

public class SocialAuthResponse {

	private String token_type;
	private Long expires_in;
	private String scope;
	private String access_token;
	private String authentication_token;
	private String user_id;

	public String getToken_type() {
		return token_type;
	}

	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}

	public Long getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(Long expires_in) {
		this.expires_in = expires_in;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getAuthentication_token() {
		return authentication_token;
	}

	public void setAuthentication_token(String authentication_token) {
		this.authentication_token = authentication_token;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

}
