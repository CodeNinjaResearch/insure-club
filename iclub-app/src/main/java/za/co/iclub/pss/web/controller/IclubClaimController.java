package za.co.iclub.pss.web.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.ContentDisposition;
import org.apache.cxf.jaxrs.ext.multipart.MultipartBody;
import org.apache.log4j.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import za.co.iclub.pss.model.ui.IclubClaimBean;
import za.co.iclub.pss.model.ui.IclubClaimStatusBean;
import za.co.iclub.pss.model.ui.IclubDocumentBean;
import za.co.iclub.pss.model.ui.IclubInsuranceItemBean;
import za.co.iclub.pss.model.ui.IclubInsuranceItemTypeBean;
import za.co.iclub.pss.model.ui.IclubPolicyBean;
import za.co.iclub.pss.model.ui.IclubPolicyStatusBean;
import za.co.iclub.pss.model.ui.IclubPropertyBean;
import za.co.iclub.pss.model.ui.IclubPropertyItemBean;
import za.co.iclub.pss.model.ui.IclubSupplMasterBean;
import za.co.iclub.pss.model.ui.IclubVehicleBean;
import za.co.iclub.pss.model.ui.IclubVehicleMasterBean;
import za.co.iclub.pss.model.ws.IclubClaimModel;
import za.co.iclub.pss.model.ws.IclubClaimStatusModel;
import za.co.iclub.pss.model.ws.IclubDocumentModel;
import za.co.iclub.pss.model.ws.IclubInsuranceItemModel;
import za.co.iclub.pss.model.ws.IclubInsuranceItemTypeModel;
import za.co.iclub.pss.model.ws.IclubPolicyModel;
import za.co.iclub.pss.model.ws.IclubPolicyStatusModel;
import za.co.iclub.pss.model.ws.IclubPropertyModel;
import za.co.iclub.pss.model.ws.IclubSupplItemModel;
import za.co.iclub.pss.model.ws.IclubSupplMasterModel;
import za.co.iclub.pss.model.ws.IclubVehicleMasterModel;
import za.co.iclub.pss.model.ws.IclubVehicleModel;
import za.co.iclub.pss.trans.IclubClaimStatusTrans;
import za.co.iclub.pss.trans.IclubClaimTrans;
import za.co.iclub.pss.trans.IclubDocumentTrans;
import za.co.iclub.pss.trans.IclubInsuranceItemTrans;
import za.co.iclub.pss.trans.IclubInsuranceItemTypeTrans;
import za.co.iclub.pss.trans.IclubPolicyStatusTrans;
import za.co.iclub.pss.trans.IclubPolicyTrans;
import za.co.iclub.pss.trans.IclubPropertyTrans;
import za.co.iclub.pss.trans.IclubSupplMasterTrans;
import za.co.iclub.pss.trans.IclubVehicleMasterTrans;
import za.co.iclub.pss.trans.IclubVehicleTrans;
import za.co.iclub.pss.util.IclubWebHelper;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@ManagedBean(name = "iclubClaimController")
@SessionScoped
public class IclubClaimController implements Serializable {

	private static final long serialVersionUID = -1299854691643272437L;
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("iclub-web");
	protected static final Logger LOGGER = Logger.getLogger(IclubClaimController.class);
	private static final String PCY_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubPolicyService/";
	private static final String II_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubInsuranceItemService/";
	private static final String BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubClaimService/";
	private static final String CS_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubClaimStatusService/";
	private static final String V_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubVehicleService/";
	private static final String PRO_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubPropertyService/";
	private static final String D_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubDocumentService/";
	private static final String IIT_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubInsuranceItemTypeService/";
	private static final String PS_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubPolicyStatusService/";
	private static final String SM_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubSupplMasterService/";
	private static final String SI_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubSupplMasterService/";
	private static final String VM_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubVehicleMasterService/";

	private List<IclubClaimStatusBean> claimStatusBeans;

	private List<IclubInsuranceItemTypeBean> insuranceItemTypebeans;

	private List<IclubPolicyStatusBean> policyStatusBeans;

	private List<IclubPolicyBean> policyBeans;

	private List<IclubClaimBean> beans;
	private List<IclubClaimBean> acBeans;
	private List<IclubClaimBean> allBeans;
	private List<IclubClaimBean> paBeans;

	private IclubClaimBean bean;

	private IclubPolicyBean policyBean;

	private boolean iItemFalg;

	private boolean policyFlag;

	private List<IclubInsuranceItemBean> iItemBeans;

	private ResourceBundle labelBundle;

	private IclubVehicleBean vehicleBean;

	private boolean vehhicleFlag;

	private boolean propertyFlag;

	private IclubPropertyBean propertyBean;

	private String sessionUserId;
	private List<IclubDocumentBean> docs;

	private List<String> docIds;

	private StreamedContent file;

	private boolean viewClaimFlag;
	private boolean paVehicleFlag;
	private List<IclubClaimBean> acDashboardBeans;
	private List<IclubClaimBean> allDashboardBeans;
	private List<IclubSupplMasterBean> dDSupplMasterBeans;
	private List<IclubSupplMasterBean> oNSupplMasterBeans;
	private List<IclubVehicleBean> vehicleBeans;
	private IclubSupplMasterBean supplMasterBean;
	private List<IclubVehicleMasterBean> vBeans;
	private Map<String, String> years;
	private ArrayList<IclubPropertyItemBean> propertyItemBeans;

	public void viewClaimAction() {
		viewClaimFlag = true;
	}

	public String showModPanel() {
		return "edit.xhtml?faces-redirect=true";
	}

	public String assignAction() {

		if (vehicleBean != null) {
			dDSupplMasterBeans = getSupplMasterBeans(vehicleBean.getVDdLong(), vehicleBean.getVDdLat());
			oNSupplMasterBeans = getSupplMasterBeans(vehicleBean.getVOnLong(), vehicleBean.getVOnLat());

			return "assignSupplier.xhtml?faces-redirect=true";
		}
		return null;
	}

	public List<IclubSupplMasterBean> getSupplMasterBeans(Double smLong, Double smLat) {

		WebClient client = IclubWebHelper.createCustomClient(SM_BASE_URL + "getByLongAndLat/" + smLat + "/" + smLong);
		Collection<? extends IclubSupplMasterModel> models = new ArrayList<IclubSupplMasterModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubSupplMasterModel.class));
		client.close();
		ArrayList<IclubSupplMasterBean> supplMasterBeans = new ArrayList<IclubSupplMasterBean>();
		if (models != null && models.size() > 0) {
			for (IclubSupplMasterModel model : models) {

				IclubSupplMasterBean bean = IclubSupplMasterTrans.fromWStoUI(model);

				supplMasterBeans.add(bean);
			}
		}
		return supplMasterBeans;
	}

	public void assignSupplAction() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: addIclubSupplItem");
		try {
			if (supplMasterBean != null) {
				WebClient client = IclubWebHelper.createCustomClient(SI_BASE_URL + "add");

				IclubSupplItemModel model = new IclubSupplItemModel();
				model.setSiId(UUID.randomUUID().toString());
				model.setIclubPerson(getSessionUserId());
				model.setSiCrtdDt(new Date(System.currentTimeMillis()));
				model.setSiAssessNumber(IclubWebHelper.getRandomNumber());
				model.setIclubInsuranceItemType(1l);
				model.setSiItemId(vehicleBean.getVId());
				model.setIclubAssessmentType(1l);
				model.setIclubSupplMaster(supplMasterBean.getSmId());
				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {
					client = IclubWebHelper.createCustomClient(PCY_BASE_URL + "get/" + policyBean.getPId());
					IclubPolicyModel pModel = client.accept(MediaType.APPLICATION_JSON).get(IclubPolicyModel.class);
					client.close();

					pModel.setPCrtdDt(new Date(System.currentTimeMillis()).toString());
					pModel.setIclubPolicyStatus(4l);
					pModel.setIclubPerson(getSessionUserId());
					client = IclubWebHelper.createCustomClient(PCY_BASE_URL + "mod");
					response = client.accept(MediaType.APPLICATION_JSON).put(pModel, ResponseModel.class);
					client.close();
					if (response.getStatusCode() == 0)
						IclubWebHelper.addMessage(getLabelBundle().getString("claim") + " " + getLabelBundle().getString("mod.success"), FacesMessage.SEVERITY_INFO);
				} else {

					IclubWebHelper.addMessage(getLabelBundle().getString("claim") + " " + getLabelBundle().getString("mod.error") + " :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);

				}

			} else {

				IclubWebHelper.addMessage(getLabelBundle().getString("claim") + " " + getLabelBundle().getString("mod.error"), FacesMessage.SEVERITY_ERROR);

			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("claim") + " " + getLabelBundle().getString("mod.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}

	public String viewVehicleAction() {

		if (bean != null) {
			paVehicleFlag = true;
			IclubPolicyBean policyBean = new IclubPolicyBean();
			List<IclubInsuranceItemBean> vehicleIItemBeans = getInsuranceItemDetails(policyBean.getIclubQuote(), 1l);
			setVehicleDetails(vehicleIItemBeans);

		}
		return null;
	}

	public List<IclubInsuranceItemBean> getInsuranceItemDetails(String quoteId, Long itemTypeId) {

		List<IclubInsuranceItemBean> beans = new ArrayList<IclubInsuranceItemBean>();
		try {
			WebClient client = IclubWebHelper.createCustomClient(II_BASE_URL + "listByQuoteIdAndItemTypeId/" + quoteId + "/" + itemTypeId);

			Collection<? extends IclubInsuranceItemModel> models = new ArrayList<IclubInsuranceItemModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubInsuranceItemModel.class));
			client.close();

			if (models != null && models.size() > 0) {
				for (IclubInsuranceItemModel model : models) {
					IclubInsuranceItemBean bean = IclubInsuranceItemTrans.fromWStoUI(model);

					beans.add(bean);
				}
			}

		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage("Fail :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);

		}

		return beans;
	}

	public void setVehicleDetails(List<IclubInsuranceItemBean> vehilcItemBeans) {

		vehicleBeans = new ArrayList<IclubVehicleBean>();
		if (vehilcItemBeans != null) {

			for (IclubInsuranceItemBean itemBean : vehilcItemBeans) {
				try {
					WebClient client = IclubWebHelper.createCustomClient(V_BASE_URL + "get/" + itemBean.getIiItemId());

					IclubVehicleModel model = (client.accept(MediaType.APPLICATION_JSON).get(IclubVehicleModel.class));
					client.close();

					IclubVehicleBean vehicleBean = IclubVehicleTrans.fromWStoUI(model);

					vehicleBeans.add(vehicleBean);

				} catch (Exception e) {
					LOGGER.error(e, e);
					IclubWebHelper.addMessage("Fail :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
				}

			}
		}

	}

	@SuppressWarnings("unchecked")
	public String policyListener(IclubPolicyBean policyBean) {
		WebClient client = IclubWebHelper.createCustomClient(II_BASE_URL + "get/quoteId/" + policyBean.getIclubQuote());
		iItemFalg = false;
		this.policyBean = policyBean;
		List<IclubInsuranceItemModel> models = (ArrayList<IclubInsuranceItemModel>) (client.accept(MediaType.APPLICATION_JSON).getCollection(IclubInsuranceItemModel.class));
		iItemBeans = new ArrayList<IclubInsuranceItemBean>();
		if (models != null && models.size() > 0) {
			for (IclubInsuranceItemModel model : models) {
				IclubInsuranceItemBean bean = IclubInsuranceItemTrans.fromWStoUI(model);

				iItemBeans.add(bean);

			}
			iItemFalg = true;
		}
		return null;
	}

	public void iItemListener(IclubInsuranceItemBean bean) {
		if (bean != null && bean.getIclubInsuranceItemType().compareTo(1l) == 0) {
			vehhicleFlag = true;
			propertyFlag = false;
			WebClient client = IclubWebHelper.createCustomClient(V_BASE_URL + "get/" + bean.getIiItemId());

			IclubVehicleModel model = (IclubVehicleModel) (client.accept(MediaType.APPLICATION_JSON).get(IclubVehicleModel.class));
			if (model != null && model.getVId() != null) {
				vehicleBean = IclubVehicleTrans.fromWStoUI(model);

				client.close();
			}
		} else if (bean != null && bean.getIclubInsuranceItemType().compareTo(2l) == 0) {
			vehhicleFlag = false;
			propertyFlag = true;

			WebClient client = IclubWebHelper.createCustomClient(PRO_BASE_URL + "get/" + bean.getIiItemId());
			IclubPropertyModel model = (IclubPropertyModel) (client.accept(MediaType.APPLICATION_JSON).get(IclubPropertyModel.class));

			if (model != null && model.getPId() != null) {
				propertyBean = IclubPropertyTrans.fromWStoUI(model);

			}
			client.close();
		} else {
			vehhicleFlag = false;
			propertyFlag = false;

		}
	}

	public String addIclubClaim() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: addIclubClaim");
		try {
			if (validateForm(true)) {
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "add");
				IclubClaimModel model = IclubClaimTrans.fromUItoWS(bean);
				model.setCId(UUID.randomUUID().toString());
				model.setCCrtdDt(new Date(System.currentTimeMillis()));
				model.setCNumber(getCnumber());
				model.setIclubPolicy(policyBean.getPId());
				model.setIclubPerson(getSessionUserId());
				model.setIclubClaimStatus(1l);

				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {
					IclubWebHelper.addMessage(getLabelBundle().getString("claimstatus") + " " + getLabelBundle().getString("add.success"), FacesMessage.SEVERITY_INFO);
					bean = new IclubClaimBean();
				} else {
					IclubWebHelper.addMessage(getLabelBundle().getString("claimstatus") + " " + getLabelBundle().getString("add.error") + " :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("claimstatus") + " " + getLabelBundle().getString("add.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
			return "";
		}
		return "view.xhtml?faces-redirect=true";
	}

	public String modIclubClaim() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: modIclubMbComment");
		try {
			if (validateForm(false)) {
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "mod");
				IclubClaimModel model = IclubClaimTrans.fromUItoWS(bean);
				model.setCCrtdDt(new Date(System.currentTimeMillis()));
				model.setIclubPerson(getSessionUserId());

				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).put(model, ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {
					for (String doc : getDocIds()) {
						IclubDocumentModel docModel = new IclubDocumentModel();
						docModel.setDId(doc);
						docModel.setDEntityId(model.getCId().toString());
						docModel.setIclubEntityType(1l);
						WebClient docClient = IclubWebHelper.createCustomClient(D_BASE_URL + "mod");
						ResponseModel res = docClient.accept(MediaType.APPLICATION_JSON).put(docModel, ResponseModel.class);
						if (res.getStatusCode() == 0)
							LOGGER.info("Doc Merge Successful :: " + doc);
					}
					docIds = null;
					IclubWebHelper.addMessage(getLabelBundle().getString("thatchtype") + " " + getLabelBundle().getString("mod.success"), FacesMessage.SEVERITY_INFO);
				} else {
					IclubWebHelper.addMessage(getLabelBundle().getString("thatchtype") + " " + getLabelBundle().getString("mod.error") + " :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("thatchtype") + " " + getLabelBundle().getString("mod.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}

		return "view.xhtml?faces-redirect=true";
	}

	public String delIclubClaim() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: delIclubMbComment");
		try {
			WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "del/" + bean.getCId());
			Response response = client.accept(MediaType.APPLICATION_JSON).get();
			if (response.getStatus() == 200) {
				IclubWebHelper.addMessage(getLabelBundle().getString("thatchtype") + " " + getLabelBundle().getString("del.success"), FacesMessage.SEVERITY_INFO);
				clearForm();
			} else {
				IclubWebHelper.addMessage(getLabelBundle().getString("thatchtype") + " " + getLabelBundle().getString("del.service.error"), FacesMessage.SEVERITY_ERROR);
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("thatchtype") + " " + getLabelBundle().getString("del.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
		return "view.xhtml?faces-redirect=true";
	}

	public String clearForm() {

		return "view.xhtml?faces-redirect=true";

	}

	public boolean validateForm(boolean flag) {
		boolean ret = true;

		return ret;
	}

	public Long getCnumber() {
		Random r = new Random();
		int Low = 1000000;
		int High = 9999999;
		int R = r.nextInt(High - Low) + Low;
		SimpleDateFormat formate = new SimpleDateFormat("YYYYMMDD");
		return Long.parseLong((formate.format(new Date()) + R));

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

	@SuppressWarnings("unchecked")
	public List<IclubInsuranceItemBean> getiItemBeans() {
		if (IclubWebHelper.getObjectIntoSession("policyBean") != null) {

			policyBean = (IclubPolicyBean) IclubWebHelper.getObjectIntoSession("policyBean");
			WebClient client = IclubWebHelper.createCustomClient(II_BASE_URL + "get/quoteId/" + policyBean.getIclubQuote());
			IclubWebHelper.addObjectIntoSession("policyBean", null);
			List<IclubInsuranceItemModel> models = (ArrayList<IclubInsuranceItemModel>) (client.accept(MediaType.APPLICATION_JSON).getCollection(IclubInsuranceItemModel.class));
			iItemBeans = new ArrayList<IclubInsuranceItemBean>();
			for (IclubInsuranceItemModel model : models) {
				IclubInsuranceItemBean bean = IclubInsuranceItemTrans.fromWStoUI(model);

				iItemBeans.add(bean);
			}
		}
		return iItemBeans;
	}

	public void setiItemBeans(List<IclubInsuranceItemBean> iItemBeans) {
		this.iItemBeans = iItemBeans;
	}

	public IclubPolicyBean getPolicyBean() {

		if (policyBean == null)
			policyBean = new IclubPolicyBean();
		return policyBean;
	}

	public void setPolicyBean(IclubPolicyBean policyBean) {
		this.policyBean = policyBean;
	}

	public IclubClaimBean getBean() {
		if (bean == null)
			bean = new IclubClaimBean();
		return bean;
	}

	public void setBean(IclubClaimBean bean) {
		this.bean = bean;
	}

	public ResourceBundle getLabelBundle() {

		labelBundle = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "labels");
		return labelBundle;
	}

	public void setLabelBundle(ResourceBundle labelBundle) {
		this.labelBundle = labelBundle;
	}

	public String claimToPolicyListener(IclubClaimBean claimBean) {

		WebClient client = IclubWebHelper.createCustomClient(PCY_BASE_URL + "get/" + claimBean.getIclubPolicy());

		IclubPolicyModel model = (IclubPolicyModel) (client.accept(MediaType.APPLICATION_JSON).get(IclubPolicyModel.class));

		List<IclubPolicyModel> models = new ArrayList<IclubPolicyModel>();
		if (model != null && model.getPId() != null) {
			models.add(model);
		}
		policyFlag = false;
		if (models != null && models.size() > 0) {
			policyFlag = true;
			policyBeans = new ArrayList<IclubPolicyBean>();
			for (IclubPolicyModel modelT : models) {
				if (modelT != null && modelT.getPId() != null) {
					IclubPolicyBean bean = IclubPolicyTrans.fromWStoUI(modelT);

					policyBeans.add(bean);
				}

			}
		}

		return "";
	}

	public List<IclubClaimStatusBean> getClaimStatusBeans() {

		WebClient client = IclubWebHelper.createCustomClient(CS_BASE_URL + "list");
		Collection<? extends IclubClaimStatusModel> models = new ArrayList<IclubClaimStatusModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubClaimStatusModel.class));
		client.close();
		claimStatusBeans = new ArrayList<IclubClaimStatusBean>();
		if (models != null && models.size() > 0) {
			for (IclubClaimStatusModel model : models) {
				IclubClaimStatusBean bean = IclubClaimStatusTrans.fromWStoUI(model);
				claimStatusBeans.add(bean);
			}
		}
		return claimStatusBeans;
	}

	public void setClaimStatusBeans(List<IclubClaimStatusBean> claimStatusBeans) {
		this.claimStatusBeans = claimStatusBeans;
	}

	public List<IclubClaimBean> getBeans() {
		WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "get/user/" + getSessionUserId());
		Collection<? extends IclubClaimModel> models = new ArrayList<IclubClaimModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubClaimModel.class));
		client.close();
		beans = new ArrayList<IclubClaimBean>();
		if (models != null && models.size() > 0) {
			for (IclubClaimModel model : models) {
				IclubClaimBean bean = IclubClaimTrans.fromWStoUI(model);

				beans.add(bean);
			}
		}
		return beans;
	}

	public void setBeans(List<IclubClaimBean> beans) {
		this.beans = beans;
	}

	public List<IclubPolicyBean> getPolicyBeans() {

		return policyBeans;
	}

	public void setPolicyBeans(List<IclubPolicyBean> policyBeans) {
		this.policyBeans = policyBeans;
	}

	public boolean isPolicyFlag() {
		return policyFlag;
	}

	public void setPolicyFlag(boolean policyFlag) {
		this.policyFlag = policyFlag;
	}

	public boolean isiItemFalg() {
		return iItemFalg;
	}

	public void setiItemFalg(boolean iItemFalg) {
		this.iItemFalg = iItemFalg;
	}

	public IclubVehicleBean getVehicleBean() {
		return vehicleBean;
	}

	public void setVehicleBean(IclubVehicleBean vehicleBean) {
		this.vehicleBean = vehicleBean;
	}

	public boolean isVehhicleFlag() {
		return vehhicleFlag;
	}

	public void setVehhicleFlag(boolean vehhicleFlag) {
		this.vehhicleFlag = vehhicleFlag;
	}

	public boolean isPropertyFlag() {
		return propertyFlag;
	}

	public void setPropertyFlag(boolean propertyFlag) {
		this.propertyFlag = propertyFlag;
	}

	public IclubPropertyBean getPropertyBean() {
		return propertyBean;
	}

	public void setPropertyBean(IclubPropertyBean propertyBean) {
		this.propertyBean = propertyBean;
	}

	public void showDocumentUpload() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: showDocumentUpload");
		if (getDocIds().size() != 0) {
			for (String doc : getDocIds()) {
				WebClient client = IclubWebHelper.createCustomClient(D_BASE_URL + "del/" + doc);
				client.get();
				client.close();
			}
		}
		getDocIds().clear();
	}

	public void handleFileUpload(FileUploadEvent fue) {
		String docId = UUID.randomUUID().toString();

		try {
			IclubDocumentModel model = new IclubDocumentModel();
			model.setIclubPerson(getSessionUserId());
			model.setDCrtdDt(new Date(System.currentTimeMillis()));
			model.setDId(docId);
			model.setDEntityId(policyBean.getPId().toString());
			model.setIclubEntityType(1l);
			model.setDName(fue.getFile().getFileName());
			model.setDContent(fue.getFile().getContentType());
			model.setDSize(fue.getFile().getSize());

			WebClient client = IclubWebHelper.createCustomClient(D_BASE_URL + "add");
			ResponseModel response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
			client.close();

			if (response.getStatusCode() == 0) {
				ContentDisposition cd = new ContentDisposition("attachment;filename=" + fue.getFile().getFileName() + ";filetype=" + fue.getFile().getContentType());
				List<Attachment> attachments = new ArrayList<Attachment>();
				Attachment attachment = new Attachment(docId, fue.getFile().getInputstream(), cd);
				attachments.add(attachment);

				WebClient uploadClient = WebClient.create(D_BASE_URL + "upload");
				Response res = uploadClient.type("multipart/form-data").post(new MultipartBody(attachments));
				uploadClient.close();

				if (res.getStatus() == 200) {
					IclubWebHelper.addMessage(getLabelBundle().getString("doucmentuploadedsuccessfully"), FacesMessage.SEVERITY_INFO);
				} else {
					IclubWebHelper.addMessage(getLabelBundle().getString("doucmentuploadingfailed") + " :: " + (res.getHeaderString("status") != null ? res.getHeaderString("status") : res.getStatusInfo()), FacesMessage.SEVERITY_ERROR);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("doucmentuploadingerror") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}

	@SuppressWarnings("unchecked")
	public void downloadDocument(String selDocId) {
		try {
			WebClient client = WebClient.create(D_BASE_URL + "download/" + selDocId);
			client.type("multipart/form-data").accept(MediaType.MULTIPART_FORM_DATA);
			List<Attachment> attachments = (List<Attachment>) client.getCollection(Attachment.class);
			file = new DefaultStreamedContent(attachments.get(0).getDataHandler().getInputStream(), attachments.get(0).getContentDisposition().getParameter("filetype"), attachments.get(0).getContentDisposition().getParameter("filename"));
			client.close();
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("doucmentuploadingerror") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}

	public void deleteDocument(String selDocId) {
		try {
			WebClient client = IclubWebHelper.createCustomClient(D_BASE_URL + "del/" + selDocId);
			client.get();
			client.close();
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("doucmentuploadingerror") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}

	public List<IclubDocumentBean> getDocs() {
		if (policyBean != null && policyBean.getPId() != null) {
			WebClient client = IclubWebHelper.createCustomClient(D_BASE_URL + "get/entity/" + policyBean.getPId() + "" + "/1");
			Collection<? extends IclubDocumentModel> models = new ArrayList<IclubDocumentModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubDocumentModel.class));
			client.close();
			docs = new ArrayList<IclubDocumentBean>();
			if (models != null && models.size() > 0) {
				for (IclubDocumentModel model : models) {
					IclubDocumentBean bean = IclubDocumentTrans.fromWStoUI(model);

					docs.add(bean);
				}
			}
		} else {
			docs = new ArrayList<IclubDocumentBean>();
		}

		return docs;
	}

	public void setDocs(List<IclubDocumentBean> docs) {
		this.docs = docs;
	}

	public StreamedContent getFile() {
		return file;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}

	public List<String> getDocIds() {
		if (docIds == null) {
			docIds = new ArrayList<String>();
		}

		return docIds;
	}

	public void setDocIds(List<String> docIds) {
		this.docIds = docIds;
	}

	public List<IclubInsuranceItemTypeBean> getInsuranceItemTypebeans() {

		WebClient client = IclubWebHelper.createCustomClient(IIT_BASE_URL + "list");
		Collection<? extends IclubInsuranceItemTypeModel> models = new ArrayList<IclubInsuranceItemTypeModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubInsuranceItemTypeModel.class));
		client.close();
		insuranceItemTypebeans = new ArrayList<IclubInsuranceItemTypeBean>();
		if (models != null && models.size() > 0) {
			for (IclubInsuranceItemTypeModel model : models) {
				IclubInsuranceItemTypeBean bean = IclubInsuranceItemTypeTrans.fromWStoUI(model);

				insuranceItemTypebeans.add(bean);
			}
		}
		return insuranceItemTypebeans;
	}

	public void setInsuranceItemTypebeans(List<IclubInsuranceItemTypeBean> insuranceItemTypebeans) {
		this.insuranceItemTypebeans = insuranceItemTypebeans;
	}

	public List<IclubPolicyStatusBean> getPolicyStatusBeans() {
		WebClient client = IclubWebHelper.createCustomClient(PS_BASE_URL + "list");
		Collection<? extends IclubPolicyStatusModel> models = new ArrayList<IclubPolicyStatusModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubPolicyStatusModel.class));
		client.close();
		policyStatusBeans = new ArrayList<IclubPolicyStatusBean>();
		if (models != null && models.size() > 0) {
			for (IclubPolicyStatusModel model : models) {
				IclubPolicyStatusBean bean = IclubPolicyStatusTrans.fromWStoUI(model);

				policyStatusBeans.add(bean);
			}
		}
		return policyStatusBeans;
	}

	public void setPolicyStatusBeans(List<IclubPolicyStatusBean> policyStatusBeans) {
		this.policyStatusBeans = policyStatusBeans;
	}

	public boolean isViewClaimFlag() {
		return viewClaimFlag;
	}

	public void setViewClaimFlag(boolean viewClaimFlag) {
		this.viewClaimFlag = viewClaimFlag;
	}

	public List<IclubClaimBean> getAcBeans() {
		WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "get/statusId/" + 4);
		Collection<? extends IclubClaimModel> models = new ArrayList<IclubClaimModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubClaimModel.class));
		client.close();
		acBeans = new ArrayList<IclubClaimBean>();
		if (models != null && models.size() > 0) {
			for (IclubClaimModel model : models) {
				IclubClaimBean bean = IclubClaimTrans.fromWStoUI(model);

				acBeans.add(bean);
			}
		}
		return acBeans;
	}

	public void setAcBeans(List<IclubClaimBean> acBeans) {
		this.acBeans = acBeans;
	}

	public List<IclubClaimBean> getAllBeans() {
		WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "listOrderByCrtDt");
		Collection<? extends IclubClaimModel> models = new ArrayList<IclubClaimModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubClaimModel.class));
		client.close();
		allBeans = new ArrayList<IclubClaimBean>();
		if (models != null && models.size() > 0) {
			for (IclubClaimModel model : models) {
				IclubClaimBean bean = IclubClaimTrans.fromWStoUI(model);

				allBeans.add(bean);
			}
		}
		return allBeans;
	}

	public void setAllBeans(List<IclubClaimBean> allBeans) {
		this.allBeans = allBeans;
	}

	public List<IclubClaimBean> getPaBeans() {
		WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "get/statusId" + 2);
		Collection<? extends IclubClaimModel> models = new ArrayList<IclubClaimModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubClaimModel.class));
		client.close();
		paBeans = new ArrayList<IclubClaimBean>();
		if (models != null && models.size() > 0) {
			for (IclubClaimModel model : models) {
				IclubClaimBean bean = IclubClaimTrans.fromWStoUI(model);

				paBeans.add(bean);
			}
		}
		return paBeans;
	}

	public void setPaBeans(List<IclubClaimBean> paBeans) {
		this.paBeans = paBeans;
	}

	public boolean isPaVehicleFlag() {
		return paVehicleFlag;
	}

	public void setPaVehicleFlag(boolean paVehicleFlag) {
		this.paVehicleFlag = paVehicleFlag;
	}

	public List<IclubClaimBean> getAcDashboardBeans() {
		return acDashboardBeans;
	}

	public void setAcDashboardBeans(List<IclubClaimBean> acDashboardBeans) {
		this.acDashboardBeans = acDashboardBeans;
	}

	public List<IclubClaimBean> getAllDashboardBeans() {
		return allDashboardBeans;
	}

	public void setAllDashboardBeans(List<IclubClaimBean> allDashboardBeans) {
		this.allDashboardBeans = allDashboardBeans;
	}

	public List<IclubSupplMasterBean> getdDSupplMasterBeans() {
		return dDSupplMasterBeans;
	}

	public void setdDSupplMasterBeans(List<IclubSupplMasterBean> dDSupplMasterBeans) {
		this.dDSupplMasterBeans = dDSupplMasterBeans;
	}

	public List<IclubSupplMasterBean> getoNSupplMasterBeans() {
		return oNSupplMasterBeans;
	}

	public void setoNSupplMasterBeans(List<IclubSupplMasterBean> oNSupplMasterBeans) {
		this.oNSupplMasterBeans = oNSupplMasterBeans;
	}

	public List<IclubVehicleBean> getVehicleBeans() {
		return vehicleBeans;
	}

	public void setVehicleBeans(List<IclubVehicleBean> vehicleBeans) {
		this.vehicleBeans = vehicleBeans;
	}

	public IclubSupplMasterBean getSupplMasterBean() {
		if (supplMasterBean == null) {
			supplMasterBean = new IclubSupplMasterBean();
		}
		return supplMasterBean;
	}

	public void setSupplMasterBean(IclubSupplMasterBean supplMasterBean) {
		this.supplMasterBean = supplMasterBean;
	}

	public List<IclubVehicleMasterBean> getvBeans() {

		WebClient client = IclubWebHelper.createCustomClient(VM_BASE_URL + "list");
		Collection<? extends IclubVehicleMasterModel> models = new ArrayList<IclubVehicleMasterModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubVehicleMasterModel.class));
		client.close();
		vBeans = new ArrayList<IclubVehicleMasterBean>();
		years = new HashMap<String, String>();
		if (models != null && models.size() > 0) {

			for (IclubVehicleMasterModel model : models) {

				IclubVehicleMasterBean bean = IclubVehicleMasterTrans.fromWStoUI(model);

				if (model != null && model.getVmId() != null && model.getVmId().toString().equalsIgnoreCase(vehicleBean.getIclubVehicleMaster().toString())) {
					Calendar now = Calendar.getInstance();
					int currentYear = now.get(Calendar.YEAR);
					now.setTimeInMillis(model.getVmProdDt().getTime());
					int prodYear = now.get(Calendar.YEAR);
					for (int i = prodYear; i <= currentYear; i++) {
						years.put(i + "", model.getVmId().toString());
					}

				}
				vBeans.add(bean);
			}
		}
		return vBeans;
	}

	public Map<String, String> getYears() {
		return years;
	}

	public void setYears(HashMap<String, String> years) {
		this.years = years;
	}

	public void setvBeans(List<IclubVehicleMasterBean> vBeans) {
		this.vBeans = vBeans;
	}

	public ArrayList<IclubPropertyItemBean> getPropertyItemBeans() {
		return propertyItemBeans;
	}

	public void setPropertyItemBeans(ArrayList<IclubPropertyItemBean> propertyItemBeans) {
		this.propertyItemBeans = propertyItemBeans;
	}

}
