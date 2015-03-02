package za.co.iclub.pss.orm.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * IclubQuoteStatus entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_quote_status", catalog = "iclubdb")
public class IclubQuoteStatus implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 3041167003838834380L;
	private Long qsId;
	private String qsShortDesc;
	private String qsLongDesc;
	private String qsStatus;

	// Constructors

	/** default constructor */
	public IclubQuoteStatus() {
	}

	/** minimal constructor */
	public IclubQuoteStatus(Long qsId) {
		this.qsId = qsId;
	}

	/** full constructor */
	public IclubQuoteStatus(Long qsId, String qsShortDesc, String qsLongDesc,
			String qsStatus) {
		this.qsId = qsId;
		this.qsShortDesc = qsShortDesc;
		this.qsLongDesc = qsLongDesc;
		this.qsStatus = qsStatus;
	}

	// Property accessors
	@Id
	@Column(name = "qs_id", unique = true, nullable = false)
	public Long getQsId() {
		return this.qsId;
	}

	public void setQsId(Long qsId) {
		this.qsId = qsId;
	}

	@Column(name = "qs_short_desc", length = 4)
	public String getQsShortDesc() {
		return this.qsShortDesc;
	}

	public void setQsShortDesc(String qsShortDesc) {
		this.qsShortDesc = qsShortDesc;
	}

	@Column(name = "qs_long_desc", length = 500)
	public String getQsLongDesc() {
		return this.qsLongDesc;
	}

	public void setQsLongDesc(String qsLongDesc) {
		this.qsLongDesc = qsLongDesc;
	}

	@Column(name = "qs_status", length = 1)
	public String getQsStatus() {
		return this.qsStatus;
	}

	public void setQsStatus(String qsStatus) {
		this.qsStatus = qsStatus;
	}

}