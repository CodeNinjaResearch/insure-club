package za.co.iclub.pss.model.ui;

import java.util.List;

public class YahooProfileBean {
	
	private String guid;
	private List<YahooAdressBean> addresses;
	private String ageCategory;
	private Long birthYear;
	private String birthdate;
	private String created;
	private String disclosures;
	private Long displayAge;
	private List<YahooMailsBean> emails;
	private String familyName;
	String followMode;
	private String gender;
	private String givenName;
	private List<YahooMobileBean> phones;
	
	public String getGuid() {
		return guid;
	}
	
	public void setGuid(String guid) {
		this.guid = guid;
	}
	
	public List<YahooAdressBean> getAddresses() {
		return addresses;
	}
	
	public void setAddresses(List<YahooAdressBean> addresses) {
		this.addresses = addresses;
	}
	
	public String getAgeCategory() {
		return ageCategory;
	}
	
	public void setAgeCategory(String ageCategory) {
		this.ageCategory = ageCategory;
	}
	
	public Long getBirthYear() {
		return birthYear;
	}
	
	public void setBirthYear(Long birthYear) {
		this.birthYear = birthYear;
	}
	
	public String getBirthdate() {
		return birthdate;
	}
	
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	
	public Long getDisplayAge() {
		return displayAge;
	}
	
	public void setDisplayAge(Long displayAge) {
		this.displayAge = displayAge;
	}
	
	public List<YahooMailsBean> getEmails() {
		return emails;
	}
	
	public void setEmails(List<YahooMailsBean> emails) {
		this.emails = emails;
	}
	
	public String getFamilyName() {
		return familyName;
	}
	
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getGivenName() {
		return givenName;
	}
	
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	
	public List<YahooMobileBean> getPhones() {
		return phones;
	}
	
	public void setPhones(List<YahooMobileBean> phones) {
		this.phones = phones;
	}
	
	public String getCreated() {
		return created;
	}
	
	public void setCreated(String created) {
		this.created = created;
	}
	
	public String getDisclosures() {
		return disclosures;
	}
	
	public void setDisclosures(String disclosures) {
		this.disclosures = disclosures;
	}
	
	public String getFollowMode() {
		return followMode;
	}
	
	public void setFollowMode(String followMode) {
		this.followMode = followMode;
	}
	
}
