package za.co.iclub.pss.web.bean;

public class IclubBarTypeBean {

	private Long btId;
	private String btShortDesc;
	private String btLongDesc;
	private String btStatus;
	private String[] iclubProperties;

	public Long getBtId() {
		return btId;
	}

	public void setBtId(Long btId) {
		this.btId = btId;
	}

	public String getBtShortDesc() {
		return btShortDesc;
	}

	public void setBtShortDesc(String btShortDesc) {
		this.btShortDesc = btShortDesc;
	}

	public String getBtLongDesc() {
		return btLongDesc;
	}

	public void setBtLongDesc(String btLongDesc) {
		this.btLongDesc = btLongDesc;
	}

	public String getBtStatus() {
		return btStatus;
	}

	public void setBtStatus(String btStatus) {
		this.btStatus = btStatus;
	}

	public String[] getIclubProperties() {
		return iclubProperties;
	}

	public void setIclubProperties(String[] iclubProperties) {
		this.iclubProperties = iclubProperties;
	}

}
