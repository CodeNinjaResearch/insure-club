package za.co.iclub.pss.model.ui.yahoo;

import java.util.List;

public class YahooContactBean {

	private String isConnection;
	private Long id;
	private List<YahooFieldBean> fields;
	private List<YahooCategorieBean> categories;
	private Long error;
	private Long restoredId;
	private String created;
	private String updated;
	private String uri;

	public String getIsConnection() {
		return isConnection;
	}

	public void setIsConnection(String isConnection) {
		this.isConnection = isConnection;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<YahooFieldBean> getFields() {
		return fields;
	}

	public void setFields(List<YahooFieldBean> fields) {
		this.fields = fields;
	}

	public List<YahooCategorieBean> getCategories() {
		return categories;
	}

	public void setCategories(List<YahooCategorieBean> categories) {
		this.categories = categories;
	}

	public Long getError() {
		return error;
	}

	public void setError(Long error) {
		this.error = error;
	}

	public Long getRestoredId() {
		return restoredId;
	}

	public void setRestoredId(Long restoredId) {
		this.restoredId = restoredId;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

}
