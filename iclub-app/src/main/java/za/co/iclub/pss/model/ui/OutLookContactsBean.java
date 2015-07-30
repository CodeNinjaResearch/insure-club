package za.co.iclub.pss.model.ui;

public class OutLookContactsBean {
	
	private String id;
	private String first_name;
	private String last_name;
	private String name;
	private boolean is_friend;
	private boolean is_favorite;
	private String user_id;
	private Object email_hashes;
	private Object updated_time;
	private Integer birth_day;
	private Integer birth_month;
	private OutLookEmailsBean emails;
	private OutLookEmailsBean phones;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getFirst_name() {
		return first_name;
	}
	
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	
	public String getLast_name() {
		return last_name;
	}
	
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isIs_friend() {
		return is_friend;
	}
	
	public void setIs_friend(boolean is_friend) {
		this.is_friend = is_friend;
	}
	
	public boolean isIs_favorite() {
		return is_favorite;
	}
	
	public void setIs_favorite(boolean is_favorite) {
		this.is_favorite = is_favorite;
	}
	
	public String getUser_id() {
		return user_id;
	}
	
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	public Object getEmail_hashes() {
		return email_hashes;
	}
	
	public void setEmail_hashes(Object email_hashes) {
		this.email_hashes = email_hashes;
	}
	
	public Object getUpdated_time() {
		return updated_time;
	}
	
	public void setUpdated_time(Object updated_time) {
		this.updated_time = updated_time;
	}
	
	public Integer getBirth_day() {
		return birth_day;
	}
	
	public void setBirth_day(Integer birth_day) {
		this.birth_day = birth_day;
	}
	
	public Integer getBirth_month() {
		return birth_month;
	}
	
	public void setBirth_month(Integer birth_month) {
		this.birth_month = birth_month;
	}
	
	public OutLookEmailsBean getEmails() {
		return emails;
	}
	
	public void setEmails(OutLookEmailsBean emails) {
		this.emails = emails;
	}
	
	public OutLookEmailsBean getPhones() {
		return phones;
	}
	
	public void setPhones(OutLookEmailsBean phones) {
		this.phones = phones;
	}
	
}
