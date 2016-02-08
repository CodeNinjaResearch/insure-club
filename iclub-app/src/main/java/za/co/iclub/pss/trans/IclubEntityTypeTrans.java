package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubEntityTypeBean;
import za.co.iclub.pss.model.ws.IclubEntityTypeModel;
import za.co.iclub.pss.orm.bean.IclubEntityType;

public class IclubEntityTypeTrans {

	public static IclubEntityTypeBean fromWStoUI(IclubEntityTypeModel model) {
		IclubEntityTypeBean bean = new IclubEntityTypeBean();
		bean.setEtId(model.getEtId());
		bean.setEtLongDesc(model.getEtLongDesc());
		bean.setEtShortDesc(model.getEtShortDesc());
		bean.setEtStatus(model.getEtStatus());
		bean.setEtTblNm(model.getEtTblNm());
		return bean;
	}

	public static IclubEntityTypeModel fromUItoWS(IclubEntityTypeBean bean) {
		IclubEntityTypeModel model = new IclubEntityTypeModel();
		model.setEtId(bean.getEtId());
		model.setEtLongDesc(bean.getEtLongDesc());
		model.setEtShortDesc(bean.getEtShortDesc());
		model.setEtStatus(bean.getEtStatus());
		model.setEtTblNm(bean.getEtTblNm());
		return model;
	}

	public static IclubEntityTypeModel fromORMtoWS(IclubEntityType bean) {
		IclubEntityTypeModel model = new IclubEntityTypeModel();
		model.setEtId(bean.getEtId());
		model.setEtLongDesc(bean.getEtLongDesc());
		model.setEtShortDesc(bean.getEtShortDesc());
		model.setEtStatus(bean.getEtStatus());
		model.setEtTblNm(bean.getEtTblNm());
		return model;
	}

	public static IclubEntityType fromWStoORM(IclubEntityTypeModel model) {
		IclubEntityType bean = new IclubEntityType();

		bean.setEtId(model.getEtId());
		bean.setEtLongDesc(model.getEtLongDesc());
		bean.setEtShortDesc(model.getEtShortDesc());
		bean.setEtStatus(model.getEtStatus());
		bean.setEtTblNm(model.getEtTblNm());

		return bean;
	}

	private Long etId;
	private String etShortDesc;
	private String etLongDesc;
	private String etStatus;
	private String etTblNm;

	public Long getEtId() {
		return etId;
	}

	public void setEtId(Long etId) {
		this.etId = etId;
	}

	public String getEtShortDesc() {
		return etShortDesc;
	}

	public void setEtShortDesc(String etShortDesc) {
		this.etShortDesc = etShortDesc;
	}

	public String getEtLongDesc() {
		return etLongDesc;
	}

	public void setEtLongDesc(String etLongDesc) {
		this.etLongDesc = etLongDesc;
	}

	public String getEtStatus() {
		return etStatus;
	}

	public void setEtStatus(String etStatus) {
		this.etStatus = etStatus;
	}

	public String getEtTblNm() {
		return etTblNm;
	}

	public void setEtTblNm(String etTblNm) {
		this.etTblNm = etTblNm;
	}

}
