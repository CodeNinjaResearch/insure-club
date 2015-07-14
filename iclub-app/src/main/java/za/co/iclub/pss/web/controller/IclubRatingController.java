package za.co.iclub.pss.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.UUID;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;

import za.co.iclub.pss.model.ui.IclubFieldBean;
import za.co.iclub.pss.model.ui.IclubRateEngineBean;
import za.co.iclub.pss.model.ui.IclubRateTypeBean;
import za.co.iclub.pss.model.ws.IclubFieldModel;
import za.co.iclub.pss.model.ws.IclubRateEngineModel;
import za.co.iclub.pss.model.ws.IclubRateTypeModel;
import za.co.iclub.pss.trans.IclubFieldTrans;
import za.co.iclub.pss.trans.IclubRateEngineTrans;
import za.co.iclub.pss.trans.IclubRateTypeTrans;
import za.co.iclub.pss.util.IclubWebHelper;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@ManagedBean(name = "iclubRatingController")
@SessionScoped
public class IclubRatingController implements Serializable {
	
	private static final long serialVersionUID = 8194018639763193542L;
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("iclub-web");
	protected static final Logger LOGGER = Logger.getLogger(IclubRateTypeController.class);
	private static final String RT_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubRateTypeService/";
	private static final String FLD_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubFieldService/";
	private static final String BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubRateEngineService/";
	private List<IclubRateEngineBean> beans;
	private boolean lookupSaveFlag;
	private Long selRateType;
	private IclubRateEngineBean bean;
	private boolean showAddPanel;
	private boolean showModPanel;
	private ResourceBundle labelBundle;
	private String sessionUserId;
	private boolean showRateEngineDlg;
	private Map<String, IclubRateEngineBean> rateEngineMap;
	
	private IclubRateTypeBean rateTypeBean;
	
	public String refreshGrid() {
		lookupSaveFlag = false;
		showRateEngineDlg = false;
		rateEngineMap = new TreeMap<String, IclubRateEngineBean>();
		if (selRateType != null) {
			try {
				IclubRateTypeBean bean = getRateTypeById(selRateType);
				if (bean.getRtType() != null && !bean.getRtType().trim().equalsIgnoreCase("")) {
					if (bean.getRtType().toUpperCase().equalsIgnoreCase("L")) {
						IclubWebHelper.addObjectIntoSession("ratetype", bean);
						loadBeans();
						return "lookup";
					} else if (bean.getRtType().toUpperCase().equalsIgnoreCase("F")) {
						IclubWebHelper.addObjectIntoSession("ratetype", bean);
						loadBeans();
						return "fixed";
					} else if (bean.getRtType().toUpperCase().equalsIgnoreCase("R")) {
						IclubWebHelper.addObjectIntoSession("ratetype", bean);
						loadBeans();
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
			
			bean = IclubRateTypeTrans.fromWStoUI(model);
			
		}
		
		return bean;
	}
	
	public void loadBeans() {
		if (IclubWebHelper.getObjectIntoSession("ratetype") != null) {
			rateTypeBean = (IclubRateTypeBean) IclubWebHelper.getObjectIntoSession("ratetype");
			IclubWebHelper.addObjectIntoSession("ratetype", null);
		}
		
		if (rateTypeBean != null && rateTypeBean.getRtId() != null && rateTypeBean.getRtType().equalsIgnoreCase("L")) {
			
			WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "/get/rateType/" + rateTypeBean.getRtId());
			Collection<? extends IclubRateEngineModel> models = new ArrayList<IclubRateEngineModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubRateEngineModel.class));
			client.close();
			beans = new ArrayList<IclubRateEngineBean>();
			
			lookupSaveFlag = true;
			Map<String, IclubRateEngineBean> rateEngineMap = new TreeMap<String, IclubRateEngineBean>();
			if (models != null && models.size() > 0) {
				for (IclubRateEngineModel model : models) {
					
					IclubRateEngineBean bean = IclubRateEngineTrans.fromWStoUI(model);
					
					rateEngineMap.put(bean.getReBaseValue(), bean);
					beans.add(bean);
				}
			}
			IclubFieldBean fieldBean = getFieldById(rateTypeBean.getIclubField());
			
			if (fieldBean != null) {
				client = IclubWebHelper.createCustomClient(BASE_URL + "/list/lookupdetails/" + fieldBean.getFLTblName());
				Collection<? extends String> lookupModels = new ArrayList<String>(client.accept(MediaType.APPLICATION_JSON).getCollection(String.class));
				client.close();
				
				if (lookupModels != null && lookupModels.size() > 0 && lookupModels.size() != rateEngineMap.size()) {
					for (String model : lookupModels) {
						if (rateEngineMap.get(model) == null) {
							IclubRateEngineBean bean = new IclubRateEngineBean();
							bean.setReId(UUID.randomUUID().toString());
							bean.setReRate(0.0);
							bean.setReCrtdDt(new Date(System.currentTimeMillis()));
							bean.setReStatus("Y");
							bean.setReBaseValue(model);
							bean.setIclubRateType(rateTypeBean.getRtId());
							bean.setIclubPerson(getSessionUserId());
							beans.add(bean);
						}
					}
				}
			}
			
		} else {
			WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "/get/rateType/" + rateTypeBean.getRtId());
			Collection<? extends IclubRateEngineModel> models = new ArrayList<IclubRateEngineModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubRateEngineModel.class));
			client.close();
			beans = new ArrayList<IclubRateEngineBean>();
			
			if (models != null && models.size() > 0) {
				lookupSaveFlag = false;
				for (IclubRateEngineModel model : models) {
					
					IclubRateEngineBean bean = IclubRateEngineTrans.fromWStoUI(model);
					
					rateEngineMap.put(bean.getReBaseValue(), bean);
					beans.add(bean);
				}
			}
		}
	}
	
	public void onCellEdit(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		
		if (newValue != null && !newValue.equals(oldValue)) {
			IclubWebHelper.addMessage("Rate Changed :::: Old Rate: " + oldValue + ", New Rate:" + newValue, FacesMessage.SEVERITY_INFO);
		}
	}
	
	public IclubFieldBean getFieldById(Long fieldId) {
		
		WebClient client = IclubWebHelper.createCustomClient(FLD_BASE_URL + "get/" + fieldId);
		IclubFieldModel model = (IclubFieldModel) client.accept(MediaType.APPLICATION_JSON).get(IclubFieldModel.class);
		client.close();
		IclubFieldBean bean = null;
		if (model != null) {
			
			bean = IclubFieldTrans.fromWStoUI(model);
			
		}
		
		return bean;
	}
	
	public List<IclubRateEngineBean> getBeans() {
		if (beans == null) {
			beans = new ArrayList<IclubRateEngineBean>();
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
	
	public void addRateEngine() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: addRateEngine");
		try {
			if (validateForm(true, false)) {
				boolean lookupOrNot = true;
				if (rateTypeBean.getRtType().equalsIgnoreCase("R")) {
					lookupOrNot = validateLookUp();
					
				}
				if (lookupOrNot) {
					bean.setReId(UUID.randomUUID().toString());
					bean.setIclubRateType(rateTypeBean.getRtId());
					bean.setIclubPerson(getSessionUserId());
					bean.setReStatus("Y");
					rateEngineMap.put(bean.getReBaseValue(), bean);
					beans.add(bean);
					
					IclubWebHelper.addMessage(getLabelBundle().getString("rateengine") + " " + getLabelBundle().getString("add.success"), FacesMessage.SEVERITY_INFO);
					clearForm();
					bean.setReRate(0d);
					RequestContext.getCurrentInstance().addCallbackParam("saved", true);
				}
				
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("rateengine") + " " + getLabelBundle().getString("add.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
		
	}
	
	public void addIclubRateEngine() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: addIclubRateEngine");
		try {
			if (validateForm(true, false)) {
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "add");
				IclubRateEngineModel model = IclubRateEngineTrans.fromUItoWS(bean);
				
				model.setReId(UUID.randomUUID().toString());
				
				model.setReCrtdDt(new Date(System.currentTimeMillis()));
				
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
			if (validateForm(true, true)) {
				
				ResponseModel response = null;
				WebClient client = null;
				for (IclubRateEngineBean bean : beans) {
					IclubRateEngineModel model = IclubRateEngineTrans.fromUItoWS(bean);
					if (bean.getReCrtdDt() == null) {
						client = IclubWebHelper.createCustomClient(BASE_URL + "add");
					} else {
						client = IclubWebHelper.createCustomClient(BASE_URL + "mod");
					}
					model.setReCrtdDt(new Date(System.currentTimeMillis()));
					model.setIclubPerson(getSessionUserId());
					if (bean.getReCrtdDt() == null) {
						response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
					} else {
						response = client.accept(MediaType.APPLICATION_JSON).put(model, ResponseModel.class);
					}
					client.close();
					lookupSaveFlag = true;
				}
				
				if (response != null && response.getStatusCode() == 0) {
					IclubWebHelper.addMessage(getLabelBundle().getString("rateengine") + " " + getLabelBundle().getString("mod.success"), FacesMessage.SEVERITY_INFO);
					clearForm();
					loadBeans();
					
				} else {
					IclubWebHelper.addMessage(getLabelBundle().getString("rateengine") + " " + getLabelBundle().getString("mod.error") + " :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
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
			if (validateForm(false, false)) {
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "mod");
				IclubRateEngineModel model = IclubRateEngineTrans.fromUItoWS(bean);
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
	
	public void delIclubRateEngine(IclubRateEngineBean bean) {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: delIclubRateEngine");
		try {
			WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "del/" + bean.getReId());
			Response response = client.accept(MediaType.APPLICATION_JSON).get();
			if (response.getStatus() == 200) {
				IclubWebHelper.addMessage(getLabelBundle().getString("rateengine") + " " + getLabelBundle().getString("del.success"), FacesMessage.SEVERITY_INFO);
				rateEngineMap.remove((bean.getReBaseValue()));
				beans.remove(bean);
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
		showRateEngineDlg = true;
		bean = new IclubRateEngineBean();
		bean.setReRate(0d);
	}
	
	public void showModPanel() {
		showAddPanel = false;
		showModPanel = true;
	}
	
	public boolean validateForm(boolean flag, boolean bulkSave) {
		boolean ret = true;
		if (selRateType == null) {
			IclubWebHelper.addMessage(("Please Select Rate Type"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		
		if (!bulkSave && bean != null && (bean.getReBaseValue() == null || bean.getReBaseValue().trim().equalsIgnoreCase(""))) {
			IclubWebHelper.addMessage(("Base Value Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		} else if (rateTypeBean != null && rateTypeBean.getRtType().equalsIgnoreCase("L") && bean != null && bean.getReBaseValue() != null && !bean.getReBaseValue().trim().equalsIgnoreCase("") && rateEngineMap != null && rateEngineMap.get((bean.getReBaseValue())) != null) {
			IclubWebHelper.addMessage(("Duplicate Base Value"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		
		else if (rateTypeBean != null && (rateTypeBean.getRtType().equalsIgnoreCase("F")) && bean.getReBaseValue() != null && !bean.getReBaseValue().trim().equalsIgnoreCase("")) {
			WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "validate/baseValue/" + bean.getReBaseValue() + "/" + rateTypeBean.getRtId() + "/" + bean.getReId());
			ResponseModel message = client.accept(MediaType.APPLICATION_JSON).get(ResponseModel.class);
			client.close();
			if (message.getStatusCode() != 0) {
				IclubWebHelper.addMessage(message.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				ret = ret && false;
			}
		} else if (rateTypeBean != null && rateTypeBean.getRtType().equalsIgnoreCase("R") && bean.getReBaseValue() != null && !bean.getReBaseValue().trim().equalsIgnoreCase("")) {
			WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "validate/rangeValue/" + bean.getReBaseValue() + "/" + bean.getReMaxValue() + "/" + rateTypeBean.getRtId() + "/" + bean.getReId());
			ResponseModel message = client.accept(MediaType.APPLICATION_JSON).get(ResponseModel.class);
			client.close();
			if (message.getStatusCode() != 0) {
				IclubWebHelper.addMessage(message.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				ret = ret && false;
			}
		}
		if (!bulkSave && bean != null && bean.getReRate() == null) {
			IclubWebHelper.addMessage(("Rate Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		return ret;
	}
	
	public boolean validateLookUp() {
		
		if (rateEngineMap != null && rateEngineMap.size() > 0) {
			for (String baseValue : rateEngineMap.keySet()) {
				if ((new Double(baseValue) <= new Double(bean.getReBaseValue())) && (new Double(bean.getReBaseValue()) <= new Double(rateEngineMap.get(baseValue).getReMaxValue()))) {
					IclubWebHelper.addMessage(("Duplicate Base Value"), FacesMessage.SEVERITY_ERROR);
					return false;
				} else if ((new Double(baseValue) <= new Double(bean.getReMaxValue())) && (new Double(bean.getReMaxValue()) <= new Double(rateEngineMap.get(baseValue).getReMaxValue()))) {
					IclubWebHelper.addMessage(("Duplicate Base Value"), FacesMessage.SEVERITY_ERROR);
					return false;
				}
			}
			return true;
		} else {
			return true;
		}
		
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
	
	public boolean isShowRateEngineDlg() {
		return showRateEngineDlg;
	}
	
	public void setShowRateEngineDlg(boolean showRateEngineDlg) {
		this.showRateEngineDlg = showRateEngineDlg;
	}
	
	public Map<String, IclubRateEngineBean> getRateEngineMap() {
		return rateEngineMap;
	}
	
	public void setRateEngineMap(Map<String, IclubRateEngineBean> rateEngineMap) {
		this.rateEngineMap = rateEngineMap;
	}
	
}
