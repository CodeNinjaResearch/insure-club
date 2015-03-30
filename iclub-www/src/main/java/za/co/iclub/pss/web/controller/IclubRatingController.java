package za.co.iclub.pss.web.controller;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.log4j.Logger;

import za.co.iclub.pss.web.bean.IclubRateEngineBean;
import za.co.iclub.pss.web.bean.IclubRateTypeBean;
import za.co.iclub.pss.web.util.IclubWebHelper;
import za.co.iclub.pss.ws.model.IclubRateTypeModel;

@ManagedBean(name = "iclubRatingController")
@SessionScoped
public class IclubRatingController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8194018639763193542L;
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("iclub-web");
	protected static final Logger LOGGER = Logger.getLogger(IclubRateTypeController.class);
	private static final String RT_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubRateTypeService/";
	private List<IclubRateEngineBean> beans;
	private Long selRateType;

	public String refreshGrid() {

		if (selRateType != null) {
			try {
				IclubRateTypeBean bean = getRateTypeById(selRateType);
				if (bean.getRtType() != null && !bean.getRtType().trim().equalsIgnoreCase("")) {
					if (bean.getRtType().toUpperCase().equalsIgnoreCase("L")) {
						IclubWebHelper.addObjectIntoSession("ratetype", bean);
						return "lookup";
					} else if (bean.getRtType().toUpperCase().equalsIgnoreCase("V")) {
						IclubWebHelper.addObjectIntoSession("ratetype", bean);
						return "fixed";
					} else if (bean.getRtType().toUpperCase().equalsIgnoreCase("R")) {
						IclubWebHelper.addObjectIntoSession("ratetype", bean);
						return "range";
					}
				}

			} catch (Exception e) {
				LOGGER.error(e, e);
				IclubWebHelper.addMessage("RateType get error :: Web Service Error - Contact Admin" + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
			}
		} else {
			IclubWebHelper.addMessage("Select RateType", FacesMessage.SEVERITY_ERROR);
		}

		return "";
	}

	public IclubRateTypeBean getRateTypeById(Long rateTypeId) {

		WebClient client = IclubWebHelper.createCustomClient(RT_BASE_URL + "get/" + rateTypeId);
		IclubRateTypeModel model = (IclubRateTypeModel) client.accept(MediaType.APPLICATION_JSON).get(IclubRateTypeModel.class);
		client.close();
		IclubRateTypeBean bean = null;
		if (model != null) {

			bean = new IclubRateTypeBean();
			bean.setRtId(model.getRtId());
			bean.setRtLongDesc(model.getRtLongDesc());
			bean.setRtShortDesc(model.getRtShortDesc());
			bean.setRtStatus(model.getRtStatus());
			bean.setRtQuoteType(model.getRtQuoteType());
			bean.setIclubEntityType(model.getIclubEntityType());
			bean.setIclubInsuranceItemType(model.getIclubInsuranceItemType());
			bean.setIclubPerson(model.getIclubPerson());
			bean.setRtCrtdDt(model.getRtCrtdDt());
			bean.setRtType(model.getRtType());
			bean.setRtFieldNm(model.getRtFieldNm());
			if (model.getIclubRateEngines() != null && model.getIclubRateEngines().length > 0) {
				String[] rateEngines = new String[model.getIclubRateEngines().length];
				int i = 0;
				for (String rateEngine : model.getIclubRateEngines()) {
					rateEngines[i] = rateEngine;
					i++;
				}

				bean.setIclubRateEngines(rateEngines);
			}
		}

		return bean;
	}

	public List<IclubRateEngineBean> getBeans() {
		return beans;
	}

	public void setBeans(List<IclubRateEngineBean> beans) {
		this.beans = beans;
	}

	public Long getSelRateType() {
		return selRateType;
	}

	public void setSelRateType(Long selRateType) {
		this.selRateType = selRateType;
	}

}
