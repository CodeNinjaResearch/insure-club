package za.co.iclub.pss.web.bean.yahoo;

public class YahooCategorieBean {

	private String name;

	private Long id;
	private Long error;
	private String created;
	private String updated;
	private String uri;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getError() {
		return error;
	}

	public void setError(Long error) {
		this.error = error;
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
