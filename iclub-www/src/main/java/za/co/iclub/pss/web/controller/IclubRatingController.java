package za.co.iclub.pss.web.controller;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.log4j.Logger;

import za.co.iclub.pss.web.bean.IclubFieldBean;
import za.co.iclub.pss.web.bean.IclubRateEngineBean;
import za.co.iclub.pss.web.bean.IclubRateTypeBean;
import za.co.iclub.pss.web.util.IclubWebHelper;
import za.co.iclub.pss.ws.model.IclubFieldModel;
import za.co.iclub.pss.ws.model.IclubRateEngineModel;
import za.co.iclub.pss.ws.model.IclubRateTypeModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@ManagedBean(name = "iclubRatingController")
@SessionScoped
public class IclubRatingController implements Serializable {

	private static final long serialVersionUID = 8194018639763193542L;
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("iclub-web");
	protected static final Logger LOGGER = Logger.getLogger(IclubRateTypeController.class);
	private static final String RT_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubRateTypeService/";
	private static final String FLD_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubFieldService/";
	private static final String BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubRateEngineService/";
	private List<IclubRateEngineBean> beans;
	private boolean lookupSaveFlag;
	private Long selRateType;
	private IclubRateEngineBean bean;
	private boolean showAddPanel;
	private boolean showModPanel;
	private ResourceBundle labelBundle;
	private String sessionUserId;

	private IclubRateTypeBean rateTypeBean;

	public String refreshGrid() {
		lookupSaveFlag = false;
		if (selRateType != null) {
			try {
				IclubRateTypeBean bean = getRateTypeById(selRateType);
				if (bean.getRtType() != null && !bean.getRtType().trim().equalsIgnoreCase("")) {
					if (bean.getRtType().toUpperCase().equalsIgnoreCase("L")) {
						IclubWebHelper.addObjectIntoSession("ratetype", bean);
						return "lookup";
					} else if (bean.getRtType().toUpperCase().equalsIgnoreCase("F")) {
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
			bean.setIclubField(model.getIclubField());
			bean.setIclubInsuranceItemType(model.getIclubInsuranceItemType());
			bean.setIclubPerson(model.getIclubPerson());
			bean.setRtCrtdDt(model.getRtCrtdDt());
			bean.setRtType(model.getRtType());
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

	public IclubFieldBean getFieldById(Long fieldId) {

		WebClient client = IclubWebHelper.createCustomClient(FLD_BASE_URL + "get/" + fieldId);
		IclubFieldModel model = (IclubFieldModel) client.accept(MediaType.APPLICATION_JSON).get(IclubFieldModel.class);
		client.close();
		IclubFieldBean bean = null;
		if (model != null) {

			bean = new IclubFieldBean();
			bean.setFId(model.getFId());
			bean.setFName(model.getFName());
			bean.setFDesc(model.getFDesc());
			bean.setFStatus(model.getFStatus());
			bean.setFLTblName(model.getFLTblName());
			bean.setFRate(model.getFRate());
			bean.setIclubEntityType(model.getIclubEntityType() != null ? model.getIclubEntityType() : null);
			if (model.getIclubRateTypes() != null && model.getIclubRateTypes().length > 0) {
				Long[] rateTypes = new Long[model.getIclubRateTypes().length];
				int i = 0;
				for (Long rateType : bean.getIclubRateTypes()) {
					rateTypes[i] = rateType;
					i++;
				}

				bean.setIclubRateTypes(rateTypes);
			}
		}

		return bean;
	}

	public List<IclubRateEngineBean> getBeans() {
		if (IclubWebHelper.getObjectIntoSession("ratetype") != null) {
			rateTypeBean = (IclubRateTypeBean) IclubWebHelper.getObjectIntoSession("ratetype");
			IclubWebHelper.addObjectIntoSession("ratetype", null);
		}

		if (rateTypeBean != null && rateTypeBean.getRtId() != null) {
			WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "/get/rateType/" + rateTypeBean.getRtId());
			Collection<? extends IclubRateEngineModel> models = new ArrayList<IclubRateEngineModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubRateEngineModel.class));
			client.close();
			beans = new ArrayList<IclubRateEngineBean>();

			if (models != null && models.size() > 0) {
				lookupSaveFlag = false;
				for (IclubRateEngineModel model : models) {

					IclubRateEngineBean bean = new IclubRateEngineBean();
					bean.setReId(model.getReId());
					bean.setReRate(model.getReRate());
					bean.setReCrtdDt(model.getReCrtdDt());
					bean.setReStatus(model.getReStatus());
					bean.setReMaxValue(model.getReMaxValue());
					bean.setReBaseValue(model.getReBaseValue());
					bean.setIclubRateType(model.getIclubRateType());
					bean.setIclubPerson(model.getIclubPerson());
					beans.add(bean);
				}
			} else if (rateTypeBean.getRtType().equalsIgnoreCase("L")) {

				IclubFieldBean fieldBean = getFieldById(rateTypeBean.getIclubField());

				if (fieldBean != null) {
					client = IclubWebHelper.createCustomClient(BASE_URL + "/list/lookupdetails/" + fieldBean.getFLTblName());
					Collection<? extends String> lookupModels = new ArrayList<String>(client.accept(MediaType.APPLICATION_JSON).getCollection(String.class));
					client.close();
					beans = new ArrayList<IclubRateEngineBean>();

					if (lookupModels != null && lookupModels.size() > 0) {
						lookupSaveFlag = true;
						for (String model : lookupModels) {
							IclubRateEngineBean bean = new IclubRateEngineBean();
							bean.setReId(UUID.randomUUID().toString());
							bean.setReRate(0.0);
							bean.setReCrtdDt(new Timestamp(System.currentTimeMillis()));
							bean.setReStatus("Y");
							bean.setReBaseValue(model);
							bean.setIclubRateType(rateTypeBean.getRtId());
							bean.setIclubPerson(getSessionUserId());
							beans.add(bean);
						}
					}

				}
			}
		}
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

	public void addIclubRateEngine() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: addIclubRateEngine");
		try {
			if (validateForm(true)) {
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "add");
				IclubRateEngineModel model = new IclubRateEngineModel();

				model.setReId(UUID.randomUUID().toString());
				model.setReRate(bean.getReRate());
				model.setReCrtdDt(new Timestamp(System.currentTimeMillis()));
				model.setReStatus(bean.getReStatus());
				model.setReMaxValue(bean.getReMaxValue());
				model.setReBaseValue(bean.getReBaseValue());
				model.setIclubRateType(selRateType);
				model.setIclubPerson(getSessionUserId());

				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {
					IclubWebHelper.addMessage(getLabelBundle().getString("rateengine") + " " + getLabelBundle().getString("add.success"), FacesMessage.SEVERITY_INFO);
					clearForm();
				} else {
					IclubWebHelper.addMessage(getLabelBundle().getString("rateengine") + " " + getLabelBundle().getString("add.error") + " :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("rateengine") + " " + getLabelBundle().getString("add.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}

	public void addIclubRateEngines() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: addIclubRateEngine");
		try {
			if (validateForm(true)) {
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "add");
				ResponseModel response = null;
				for (IclubRateEngineBean bean : beans) {
					IclubRateEngineModel model = new IclubRateEngineModel();

					model.setReId(bean.getReId());
					model.setReRate(bean.getReRate());
					model.setReCrtdDt(new Timestamp(System.currentTimeMillis()));
					model.setReStatus(bean.getReStatus());
					model.setReMaxValue(bean.getReMaxValue());
					model.setReBaseValue(bean.getReBaseValue());
					model.setIclubRateType(bean.getIclubRateType());
					model.setIclubPerson(getSessionUserId());

					response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
					lookupSaveFlag = false;
				}
				client.close();
				if (response != null && response.getStatusCode() == 0) {
					IclubWebHelper.addMessage(getLabelBundle().getString("rateengine") + " " + getLabelBundle().getString("add.success"), FacesMessage.SEVERITY_INFO);
					clearForm();

				} else {
					IclubWebHelper.addMessage(getLabelBundle().getString("rateengine") + " " + getLabelBundle().getString("add.error") + " :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("rateengine") + " " + getLabelBundle().getString("add.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}

	public void modIclubRateEngine() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: modIclubRateEngine");
		try {
			if (validateForm(false)) {
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "mod");
				IclubRateEngineModel model = new IclubRateEngineModel();
				model.setReId(bean.getReId());
				model.setReRate(bean.getReRate());
				model.setReCrtdDt(new Timestamp(System.currentTimeMillis()));
				model.setReStatus(bean.getReStatus());
				model.setReMaxValue(bean.getReMaxValue());
				model.setReBaseValue(bean.getReBaseValue());
				model.setIclubRateType(selRateType);
				model.setIclubPerson(getSessionUserId());
				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).put(model, ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {
					IclubWebHelper.addMessage(getLabelBundle().getString("rateengine") + " " + getLabelBundle().getString("mod.success"), FacesMessage.SEVERITY_INFO);
					clearForm();
				} else {
					IclubWebHelper.addMessage(getLabelBundle().getString("rateengine") + " " + getLabelBundle().getString("mod.error") + " :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("rateengine") + " " + getLabelBundle().getString("mod.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}

	public void delIclubRateEngine() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: delIclubRateEngine");
		try {
			WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "del/" + bean.getReId());
			Response response = client.accept(MediaType.APPLICATION_JSON).get();
			if (response.getStatus() == 200) {
				IclubWebHelper.addMessage(getLabelBundle().getString("rateengine") + " " + getLabelBundle().getString("del.success"), FacesMessage.SEVERITY_INFO);
				clearForm();
			} else {
				IclubWebHelper.addMessage(getLabelBundle().getString("rateengine") + " " + getLabelBundle().getString("del.service.error"), FacesMessage.SEVERITY_ERROR);
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("rateengine") + " " + getLabelBundle().getString("del.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}

	public void clearForm() {
		showAddPanel = false;
		showModPanel = false;
		bean = new IclubRateEngineBean();
	}

	public void showAddPanel() {
		showAddPanel = true;
		showModPanel = false;
		bean = new IclubRateEngineBean();
	}

	public void showModPanel() {
		showAddPanel = false;
		showModPanel = true;
	}

	public boolean validateForm(boolean flag) {
		boolean ret = true;
		if (selRateType == null) {
			IclubWebHelper.addMessage(("Please Select Rate Type"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (bean.getReBaseValue() == null || bean.getReBaseValue().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(("Base Value Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (bean.getReRate() == null) {
			IclubWebHelper.addMessage(("Rate Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		return ret;
	}

	public IclubRateEngineBean getBean() {
		if (bean == null)
			bean = new IclubRateEngineBean();
		return bean;
	}

	public void setBean(IclubRateEngineBean bean) {
		this.bean = bean;
	}

	public boolean isShowAddPanel() {
		return showAddPanel;
	}

	public void setShowAddPanel(boolean showAddPanel) {
		this.showAddPanel = showAddPanel;
	}

	public boolean isShowModPanel() {
		return showModPanel;
	}

	public void setShowModPanel(boolean showModPanel) {
		this.showModPanel = showModPanel;
	}

	public ResourceBundle getLabelBundle() {

		labelBundle = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "labels");
		return labelBundle;
	}

	public void setLabelBundle(ResourceBundle labelBundle) {
		this.labelBundle = labelBundle;
	}

	public String getSessionUserId() {
		Object sessUsrId = IclubWebHelper.getObjectIntoSession(BUNDLE.getString("logged.in.user.id"));
		if (sessUsrId == null)
			sessionUserId = "1";
		else
			sessionUserId = sessUsrId.toString();
		return sessionUserId;
	}

	public void setSessionUserId(String sessionUserId) {
		this.sessionUserId = sessionUserId;
	}

	public boolean isLookupSaveFlag() {
		return lookupSaveFlag;
	}

	public void setLookupSaveFlag(boolean lookupSaveFlag) {
		this.lookupSaveFlag = lookupSaveFlag;
	}

}
