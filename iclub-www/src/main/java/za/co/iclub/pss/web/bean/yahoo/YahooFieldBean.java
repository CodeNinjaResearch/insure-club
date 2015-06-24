package za.co.iclub.pss.web.bean.yahoo;

import java.util.List;

public class YahooFieldBean {
	
	String id;
	private String type;
	private Object value;
	private String editedBy;
	private List<String> flags;
	private List<String> categories;
	private String updated;
	private String created;
	private String uri;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public Object getValue() {
		return value;
	}
	
	public void setValue(Object value) {
		this.value = value;
	}
	
	public String getEditedBy() {
		return editedBy;
	}
	
	public void setEditedBy(String editedBy) {
		this.editedBy = editedBy;
	}
	
	public List<String> getFlags() {
		return flags;
	}
	
	public void setFlags(List<String> flags) {
		this.flags = flags;
	}
	
	public List<String> getCategories() {
		return categories;
	}
	
	public void setCategories(List<String> categories) {
		this.categories = categories;
	}
	
	public String getUpdated() {
		return updated;
	}
	
	public void setUpdated(String updated) {
		this.updated = updated;
	}
	
	public String getCreated() {
		return created;
	}
	
	public void setCreated(String created) {
		this.created = created;
	}
	
	public String getUri() {
		return uri;
	}
	
	public void setUri(String uri) {
		this.uri = uri;
	}
	
}
