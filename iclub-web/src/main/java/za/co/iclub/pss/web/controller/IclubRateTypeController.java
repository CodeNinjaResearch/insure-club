package za.co.iclub.pss.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.log4j.Logger;

import za.co.iclub.pss.web.bean.IclubEntityTypeBean;
import za.co.iclub.pss.web.bean.IclubFieldBean;
import za.co.iclub.pss.web.bean.IclubInsuranceItemTypeBean;
import za.co.iclub.pss.web.bean.IclubRateTypeBean;
import za.co.iclub.pss.web.util.IclubWebHelper;
import za.co.iclub.pss.ws.model.IclubEntityTypeModel;
import za.co.iclub.pss.ws.model.IclubFieldModel;
import za.co.iclub.pss.ws.model.IclubInsuranceItemTypeModel;
import za.co.iclub.pss.ws.model.IclubRateTypeModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@ManagedBean(name = "iclubRateTypeController")
@SessionScoped
public class IclubRateTypeController implements Serializable {
	
	private static final long serialVersionUID = 6271776777151313314L;
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("iclub-web");
	protected static final Logger LOGGER = Logger.getLogger(IclubRateTypeController.class);
	private static final String BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubRateTypeService/";
	private static final String E_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubEntityTypeService/";
	private static final String F_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubFieldService/";
	private static final String IIT_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubInsuranceItemTypeService/";
	private List<IclubRateTypeBean> beans;
	private List<IclubRateTypeBean> dashBoardBeans;
	private List<IclubEntityTypeBean> entityTypeBeans;
	private List<IclubInsuranceItemTypeBean> insuranceItemTypeBeans;
	private List<IclubFieldBean> fieldBeans;
	private List<String> fields;
	private IclubRateTypeBean bean;
	private boolean showCreateCont;
	private boolean showViewCont;
	private boolean showEditCont;
	private boolean showMin;
	private boolean showMax;
	private boolean showLookup;
	private Long viewParam;
	private String sessionUserId;
	private String userName;
	private ResourceBundle labelBundle;
	private Map<String, String> quoteType;
	private Map<String, String> rateType;
	private String selEntityType;
	private String selQuoteType;
	private String selRateType;
	
	public void initializePage() {
		selQuoteType = null;
		selEntityType = null;
		selRateType = null;
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: initializePage");
		if (viewParam == null || viewParam.longValue() == 1)
			showView();
		else if (viewParam != null && viewParam.longValue() == 2)
			showEdit();
		
	}
	
	public void showView() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: showView");
		showCreateCont = false;
		showViewCont = true;
		showEditCont = false;
		viewParam = 1l;
	}
	
	public void showCreate() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: showCreate");
		bean = new IclubRateTypeBean();
		showCreateCont = true;
		showViewCont = false;
		showEditCont = false;
		viewParam = 1l;
	}
	
	public void showEdit() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: showEdit");
		showCreateCont = false;
		showViewCont = false;
		showEditCont = true;
		viewParam = 2l;
	}
	
	public void changeQuoteType(ValueChangeEvent valueChangeEvent) {
		selEntityType = null;
		selRateType = null;
	}
	
	public void changeEntityType(ValueChangeEvent valueChangeEvent) {
		selRateType = null;
	}
	
	public void changeRateType(ValueChangeEvent valueChangeEvent) {
		
	}
	
	public List<IclubRateTypeBean> getDashBoardBeans() {
		WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "/get/user/" + getSessionUserId());
		Collection<? extends IclubRateTypeModel> models = new ArrayList<IclubRateTypeModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubRateTypeModel.class));
		client.close();
		dashBoardBeans = new ArrayList<IclubRateTypeBean>();
		if (models != null && models.size() > 0) {
			for (IclubRateTypeModel model : models) {
				
				IclubRateTypeBean bean = new IclubRateTypeBean();
				
				bean.setRtId(model.getRtId());
				bean.setRtLongDesc(model.getRtLongDesc());
				bean.setRtShortDesc(model.getRtShortDesc());
				bean.setRtStatus(model.getRtStatus());
				bean.setRtQuoteType(model.getRtQuoteType());
				bean.setIclubField(model.getIclubField());
				bean.setIclubEntityType(model.getIclubEntityType());
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
					
					model.setIclubRateEngines(rateEngines);
				}
				
				dashBoardBeans.add(bean);
			}
		}
		return dashBoardBeans;
	}
	
	public void setDashBoardBeans(List<IclubRateTypeBean> dashBoardBeans) {
		this.dashBoardBeans = dashBoardBeans;
	}
	
	public void clearForm() {
		showCreateCont = false;
		showEditCont = false;
		bean = new IclubRateTypeBean();
	}
	
	public void addIclubRateType() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: addIclubRateType");
		try {
			if (validateForm(true)) {
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "add");
				IclubRateTypeModel model = new IclubRateTypeModel();
				
				model.setRtLongDesc(bean.getRtLongDesc());
				model.setRtShortDesc(bean.getRtShortDesc());
				model.setRtStatus(bean.getRtStatus());
				model.setRtQuoteType(bean.getRtQuoteType());
				model.setIclubEntityType(bean.getIclubEntityType());
				model.setIclubField(bean.getIclubField());
				model.setIclubInsuranceItemType(bean.getIclubInsuranceItemType());
				model.setIclubPerson(bean.getIclubPerson());
				model.setRtCrtdDt(new Date(System.currentTimeMillis()));
				model.setRtType(bean.getRtType());
				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {
					
					IclubWebHelper.addMessage(getLabelBundle().getString("ratetype") + " " + getLabelBundle().getString("add.success"), FacesMessage.SEVERITY_INFO);
					viewParam = 1l;
					showView();
				} else {
					IclubWebHelper.addMessage(getLabelBundle().getString("ratetype") + " " + getLabelBundle().getString("add.error") + " :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("ratetype") + " " + getLabelBundle().getString("add.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void modIclubRateType() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: modIclubRateType");
		try {
			if (validateForm(false)) {
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "mod");
				IclubRateTypeModel model = new IclubRateTypeModel();
				
				model.setRtId(bean.getRtId());
				model.setRtLongDesc(bean.getRtLongDesc());
				model.setRtShortDesc(bean.getRtShortDesc());
				model.setRtStatus(bean.getRtStatus());
				model.setRtQuoteType(bean.getRtQuoteType());
				model.setIclubField(bean.getIclubField());
				model.setIclubEntityType(bean.getIclubEntityType());
				model.setIclubInsuranceItemType(bean.getIclubInsuranceItemType());
				model.setIclubPerson(bean.getIclubPerson());
				model.setRtCrtdDt(bean.getRtCrtdDt());
				model.setRtType(bean.getRtType());
				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).put(model, ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {
					IclubWebHelper.addMessage(getLabelBundle().getString("ratetype") + " " + getLabelBundle().getString("mod.success"), FacesMessage.SEVERITY_INFO);
					viewParam = 1l;
					showView();
				} else {
					IclubWebHelper.addMessage(getLabelBundle().getString("ratetype") + " " + getLabelBundle().getString("mod.error") + " :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("ratetype") + " " + getLabelBundle().getString("mod.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void delIclubRateType() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: delIclubRateType");
		try {
			WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "del/" + bean.getRtId());
			Response response = client.accept(MediaType.APPLICATION_JSON).get();
			if (response.getStatus() == 200) {
				IclubWebHelper.addMessage(getLabelBundle().getString("ratetype") + " " + getLabelBundle().getString("del.success"), FacesMessage.SEVERITY_INFO);
				viewParam = 1l;
				showView();
			} else {
				IclubWebHelper.addMessage(getLabelBundle().getString("ratetype") + " " + getLabelBundle().getString("del.service.error"), FacesMessage.SEVERITY_ERROR);
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("ratetype") + " " + getLabelBundle().getString("del.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public boolean validateForm(boolean flag) {
		boolean ret = true;
		if (bean.getIclubField() == null) {
			IclubWebHelper.addMessage("Please Select Field", FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (bean.getIclubInsuranceItemType() == null) {
			IclubWebHelper.addMessage("Please Select Insureance Item Type", FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (bean.getIclubEntityType() == null) {
			IclubWebHelper.addMessage(("Please Select Enity Type"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (bean.getRtShortDesc() == null || bean.getRtShortDesc().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(("Short Description Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (bean.getRtStatus() == null || bean.getRtStatus().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(("Please Select Status"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		
		if (bean.getRtType() == null || bean.getRtType().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(("Rt Type Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		
		if (bean.getRtQuoteType() == null || bean.getRtQuoteType().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(("Quote Type Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		return ret;
	}
	
	public IclubRateTypeBean getBean() {
		if (bean == null)
			bean = new IclubRateTypeBean();
		return bean;
	}
	
	public void setBean(IclubRateTypeBean bean) {
		this.bean = bean;
	}
	
	public boolean isShowCreateCont() {
		return showCreateCont;
	}
	
	public void setShowCreateCont(boolean showCreateCont) {
		this.showCreateCont = showCreateCont;
	}
	
	public boolean isShowViewCont() {
		return showViewCont;
	}
	
	public void setShowViewCont(boolean showViewCont) {
		this.showViewCont = showViewCont;
	}
	
	public boolean isShowEditCont() {
		return showEditCont;
	}
	
	public void setShowEditCont(boolean showEditCont) {
		this.showEditCont = showEditCont;
	}
	
	public boolean isShowMin() {
		return showMin;
	}
	
	public void setShowMin(boolean showMin) {
		this.showMin = showMin;
	}
	
	public boolean isShowMax() {
		return showMax;
	}
	
	public void setShowMax(boolean showMax) {
		this.showMax = showMax;
	}
	
	public boolean isShowLookup() {
		return showLookup;
	}
	
	public void setShowLookup(boolean showLookup) {
		this.showLookup = showLookup;
	}
	
	public Long getViewParam() {
		return viewParam;
	}
	
	public void setViewParam(Long viewParam) {
		this.viewParam = viewParam;
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
	
	public String getUserName() {
		userName = IclubWebHelper.getObjectIntoSession(BUNDLE.getString("logged.in.user.scname")).toString();
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public ResourceBundle getLabelBundle() {
		if (labelBundle == null) {
			labelBundle = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "labels");
		}
		return labelBundle;
	}
	
	public void setLabelBundle(ResourceBundle labelBundle) {
		this.labelBundle = labelBundle;
	}
	
	public List<IclubRateTypeBean> getBeans() {
		
		WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "list");
		Collection<? extends IclubRateTypeModel> models = new ArrayList<IclubRateTypeModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubRateTypeModel.class));
		client.close();
		beans = new ArrayList<IclubRateTypeBean>();
		if (models != null && models.size() > 0) {
			for (IclubRateTypeModel model : models) {
				
				IclubRateTypeBean bean = new IclubRateTypeBean();
				
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
					
					model.setIclubRateEngines(rateEngines);
				}
				
				beans.add(bean);
			}
		}
		return beans;
	}
	
	public void setBeans(List<IclubRateTypeBean> beans) {
		this.beans = beans;
	}
	
	public List<IclubEntityTypeBean> getEntityTypeBeans() {
		
		WebClient client = IclubWebHelper.createCustomClient(E_BASE_URL + "list");
		Collection<? extends IclubEntityTypeModel> models = new ArrayList<IclubEntityTypeModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubEntityTypeModel.class));
		client.close();
		entityTypeBeans = new ArrayList<IclubEntityTypeBean>();
		if (models != null && models.size() > 0) {
			for (IclubEntityTypeModel model : models) {
				IclubEntityTypeBean bean = new IclubEntityTypeBean();
				bean.setEtId(model.getEtId());
				bean.setEtLongDesc(model.getEtLongDesc());
				bean.setEtShortDesc(model.getEtShortDesc());
				bean.setEtStatus(model.getEtStatus());
				entityTypeBeans.add(bean);
			}
		}
		return entityTypeBeans;
	}
	
	public void setEntityTypeBeans(List<IclubEntityTypeBean> entityTypeBeans) {
		this.entityTypeBeans = entityTypeBeans;
	}
	
	public List<String> getFields() {
		fields = new ArrayList<String>();
		switch (bean.getIclubEntityType().intValue()) {
		case 1:
			fields.add("Purpose Type");
			fields.add("No Claim Years");
			if (bean.getRtQuoteType().equalsIgnoreCase("f")) {
				fields.add("Safety Score");
				fields.add("Security Features");
				fields.add("Overnight Parking");
				fields.add("Comprehensive Years");
				fields.add("Cover Type");
				fields.add("Vehicle Modifications");
			}
			break;
		case 2:
			fields.add("Age");
			fields.add("Gender");
			fields.add("Maritial Status");
			fields.add("Years with License");
			break;
		case 3:
			break;
		default:
			break;
		}
		return fields;
	}
	
	public void setFields(List<String> fields) {
		this.fields = fields;
	}
	
	public Map<String, String> getQuoteType() {
		quoteType = new HashMap<String, String>();
		quoteType.put("F-Full", "F");
		quoteType.put("Q-Quick", "Q");
		return quoteType;
	}
	
	public void setQuoteType(Map<String, String> quoteType) {
		this.quoteType = quoteType;
	}
	
	public Map<String, String> getRateType() {
		rateType = new HashMap<String, String>();
		rateType.put("L-Lookup", "L");
		rateType.put("R-Range", "R");
		rateType.put("F-Fixed", "F");
		rateType.put("G-Geo Location", "G");
		return rateType;
	}
	
	public void setRateType(Map<String, String> rateType) {
		this.rateType = rateType;
	}
	
	public String getSelEntityType() {
		return selEntityType;
	}
	
	public void setSelEntityType(String selEntityType) {
		this.selEntityType = selEntityType;
	}
	
	public String getSelQuoteType() {
		return selQuoteType;
	}
	
	public void setSelQuoteType(String selQuoteType) {
		this.selQuoteType = selQuoteType;
	}
	
	public String getSelRateType() {
		return selRateType;
	}
	
	public void setSelRateType(String selRateType) {
		this.selRateType = selRateType;
	}
	
	public List<IclubInsuranceItemTypeBean> getInsuranceItemTypeBeans() {
		
		WebClient client = IclubWebHelper.createCustomClient(IIT_BASE_URL + "list");
		Collection<? extends IclubInsuranceItemTypeModel> models = new ArrayList<IclubInsuranceItemTypeModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubInsuranceItemTypeModel.class));
		client.close();
		insuranceItemTypeBeans = new ArrayList<IclubInsuranceItemTypeBean>();
		if (models != null && models.size() > 0) {
			for (IclubInsuranceItemTypeModel model : models) {
				IclubInsuranceItemTypeBean bean = new IclubInsuranceItemTypeBean();
				bean.setIitId(model.getIitId());
				bean.setIitLongDesc(model.getIitLongDesc());
				bean.setIitShortDesc(model.getIitShortDesc());
				bean.setIitStatus(model.getIitStatus());
				insuranceItemTypeBeans.add(bean);
			}
		}
		return insuranceItemTypeBeans;
	}
	
	public void setInsuranceItemTypeBeans(List<IclubInsuranceItemTypeBean> insuranceItemTypeBeans) {
		this.insuranceItemTypeBeans = insuranceItemTypeBeans;
	}
	
	public List<IclubFieldBean> getFieldBeans() {
		WebClient client = IclubWebHelper.createCustomClient(F_BASE_URL + "list");
		Collection<? extends IclubFieldModel> models = new ArrayList<IclubFieldModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubFieldModel.class));
		client.close();
		fieldBeans = new ArrayList<IclubFieldBean>();
		if (models != null && models.size() > 0) {
			for (IclubFieldModel model : models) {
				IclubFieldBean bean = new IclubFieldBean();
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
					for (Long rateType : model.getIclubRateTypes()) {
						rateTypes[i] = rateType;
						i++;
					}
					
					bean.setIclubRateTypes(rateTypes);
				}
				fieldBeans.add(bean);
			}
		}
		
		return fieldBeans;
	}
	
	public void setFieldBeans(List<IclubFieldBean> fieldBeans) {
		this.fieldBeans = fieldBeans;
	}
	
}
