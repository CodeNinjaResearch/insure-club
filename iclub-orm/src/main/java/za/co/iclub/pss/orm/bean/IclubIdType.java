package za.co.iclub.pss.orm.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * IclubIdType entity. @author Venu Madhav Pattamatta
 */
@Entity
@Table(name = "iclub_id_type", catalog = "iclubdb")
public class IclubIdType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 4906075924247682385L;
	private Long itId;
	private String itShortDesc;
	private String itLongDesc;
	private String itStatus;

	// Constructors

	/** default constructor */
	public IclubIdType() {
	}

	/** minimal constructor */
	public IclubIdType(Long itId) {
		this.itId = itId;
	}

	/** full constructor */
	public IclubIdType(Long itId, String itShortDesc, String itLongDesc,
			String itStatus) {
		this.itId = itId;
		this.itShortDesc = itShortDesc;
		this.itLongDesc = itLongDesc;
		this.itStatus = itStatus;
	}

	// Property accessors
	@Id
	@Column(name = "it_id", unique = true, nullable = false)
	public Long getItId() {
		return this.itId;
	}

	public void setItId(Long itId) {
		this.itId = itId;
	}

	@Column(name = "it_short_desc", length = 4)
	public String getItShortDesc() {
		return this.itShortDesc;
	}

	public void setItShortDesc(String itShortDesc) {
		this.itShortDesc = itShortDesc;
	}

	@Column(name = "it_long_desc", length = 500)
	public String getItLongDesc() {
		return this.itLongDesc;
	}

	public void setItLongDesc(String itLongDesc) {
		this.itLongDesc = itLongDesc;
	}

	@Column(name = "it_status", length = 1)
	public String getItStatus() {
		return this.itStatus;
	}

	public void setItStatus(String itStatus) {
		this.itStatus = itStatus;
	}

}