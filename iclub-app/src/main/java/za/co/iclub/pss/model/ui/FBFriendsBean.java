package za.co.iclub.pss.model.ui;

import java.io.Serializable;

public class FBFriendsBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -929864695899516986L;
	private FBDataBean data;
	private String paging;
//	private String summary;
	
	// public String getSummary() {
	// return summary;
	// }
	//
	// public void setSummary(String summary) {
	// this.summary = summary;
	// }
	//
	public FBDataBean getData() {
		return data;
	}
	
	public void setData(FBDataBean data) {
		this.data = data;
	}
	
	public String getPaging() {
		return paging;
	}
	
	public void setPaging(String paging) {
		this.paging = paging;
	}
	
}
