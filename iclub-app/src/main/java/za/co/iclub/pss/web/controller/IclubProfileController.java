package za.co.iclub.pss.web.controller;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.ContentDisposition;
import org.apache.cxf.jaxrs.ext.multipart.MultipartBody;
import org.apache.log4j.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import za.co.iclub.pss.model.ui.IclubCountryCodeBean;
import za.co.iclub.pss.model.ui.IclubDocumentBean;
import za.co.iclub.pss.model.ui.IclubIdTypeBean;
import za.co.iclub.pss.model.ui.IclubLoginBean;
import za.co.iclub.pss.model.ui.IclubMaritialStatusBean;
import za.co.iclub.pss.model.ui.IclubOccupationBean;
import za.co.iclub.pss.model.ui.IclubPersonBean;
import za.co.iclub.pss.model.ui.IclubSecurityQuestionBean;
import za.co.iclub.pss.model.ws.IclubCountryCodeModel;
import za.co.iclub.pss.model.ws.IclubDocumentModel;
import za.co.iclub.pss.model.ws.IclubIdTypeModel;
import za.co.iclub.pss.model.ws.IclubLoginModel;
import za.co.iclub.pss.model.ws.IclubMaritialStatusModel;
import za.co.iclub.pss.model.ws.IclubOccupationModel;
import za.co.iclub.pss.model.ws.IclubPersonModel;
import za.co.iclub.pss.model.ws.IclubSecurityQuestionModel;
import za.co.iclub.pss.trans.IclubCountryCodeTrans;
import za.co.iclub.pss.trans.IclubDocumentTrans;
import za.co.iclub.pss.trans.IclubIdTypeTrans;
import za.co.iclub.pss.trans.IclubLoginTrans;
import za.co.iclub.pss.trans.IclubMaritialStatusTrans;
import za.co.iclub.pss.trans.IclubOccupationTrans;
import za.co.iclub.pss.trans.IclubPersonTrans;
import za.co.iclub.pss.trans.IclubSecurityQuestionTrans;
import za.co.iclub.pss.util.IclubWebHelper;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@ManagedBean(name = "iclubProfileController")
@SessionScoped
public class IclubProfileController implements Serializable {
	
	private static final long serialVersionUID = 4703596329233786371L;
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("iclub-web");
	private static final Logger LOGGER = Logger.getLogger(IclubProfileController.class);
	private static final String BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubPersonService/";
	private static final String OCN_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubOccupationService/";
	private static final String MS_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubMaritialStatusService/";
	private static final String IT_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubIdTypeService/";
	private static final String CCDE_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubCountryCodeService/";
	private static final String D_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubDocumentService/";
	private static final String LOG_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubLoginService/";
	private static final String SECQ_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubSecurityQuestionService/";
	
	private IclubPersonBean bean;
	
	private List<IclubOccupationBean> occupationBeans;
	
	private List<IclubCountryCodeBean> countryCodeBeans;
	
	private List<IclubMaritialStatusBean> maritialStatusBeans;
	
	private List<IclubSecurityQuestionBean> securityQuestionBeans;
	
	private List<IclubIdTypeBean> idTypeBeans;
	
	private String sessionUserId;
	
	private List<IclubDocumentBean> docs;
	
	private IclubLoginBean loginBean;
	
	private List<String> docIds;
	
	private StreamedContent file;
	
	private ResourceBundle labelBundle;
	
	private boolean updateLogin;
	
	private boolean loadBean;
	
	public void initializePage() {
		loadBean = true;
	}
	
	public String updatedPerson() {
		
		try {
			if (validateForm(!true)) {
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "mod");
				
				IclubPersonModel model = IclubPersonTrans.fromUItoWS(bean);
				
				model.setIclubPerson(getSessionUserId());
				
				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).put(model, ResponseModel.class);
				client.close();
				
				if (response.getStatusCode() == 0) {
					for (String doc : getDocIds()) {
						IclubDocumentModel docModel = new IclubDocumentModel();
						docModel.setDId(doc);
						docModel.setDEntityId(model.getPId().toString());
						docModel.setIclubEntityType(1l);
						WebClient docClient = IclubWebHelper.createCustomClient(D_BASE_URL + "mod");
						ResponseModel res = docClient.accept(MediaType.APPLICATION_JSON).put(docModel, ResponseModel.class);
						if (res.getStatusCode() == 0)
							LOGGER.info("Doc Merge Successful :: " + doc);
					}
					docIds = null;
					IclubWebHelper.addMessage("Updated Successfully", FacesMessage.SEVERITY_INFO);
					return "userDashboard";
					
				} else {
					IclubWebHelper.addMessage("Fail :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				}
			} else {
				IclubWebHelper.addMessage("Fail :: ", FacesMessage.SEVERITY_ERROR);
			}
			
		} catch (Exception e) {
			IclubWebHelper.addMessage("Fail :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
		
		return "";
		
	}
	
	public String updatedUser() {
		
		try {
			if (validateForm(!true) && validateLoginForm(!updateLogin)) {
				
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "mod");
				
				IclubPersonModel model = IclubPersonTrans.fromUItoWS(bean);
				
				model.setIclubPerson(getSessionUserId());
				
				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).put(model, ResponseModel.class);
				client.close();
				
				if (response.getStatusCode() == 0) {
					return updateLogin();
				} else {
					IclubWebHelper.addMessage("Fail :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				}
			} else {
				IclubWebHelper.addMessage("Fail :: ", FacesMessage.SEVERITY_ERROR);
			}
			
		} catch (Exception e) {
			IclubWebHelper.addMessage("Fail :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
		
		return null;
		
	}
	
	public String updateLogin() {
		
		try {
			if (validateLoginForm(!updateLogin)) {
				IclubLoginModel model = new IclubLoginModel();
				WebClient client = null;
				
				if (loginBean.getLId() != null) {
					client = IclubWebHelper.createCustomClient(LOG_BASE_URL + "mod");
					model.setLId(loginBean.getLId());
					
				} else {
					client = IclubWebHelper.createCustomClient(LOG_BASE_URL + "add");
					model.setLId(UUID.randomUUID().toString());
				}
				model.setLCrtdDt(new Date(System.currentTimeMillis()));
				model = IclubLoginTrans.fromUItoWS(loginBean);
				
				model.setLPasswd(Base64.encodeBase64URLSafeString(DigestUtils.md5(loginBean.getLPasswd())));
				model.setIclubPersonAByLCrtdBy(bean.getPId());
				model.setIclubPersonBByLPersonId(getSessionUserId());
				model.setIclubRoleType(2l);
				
				ResponseModel response = null;
				if (updateLogin) {
					response = client.accept(MediaType.APPLICATION_JSON).put(model, ResponseModel.class);
				} else {
					response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
				}
				
				if (response.getStatusCode() == 0) {
					IclubWebHelper.addObjectIntoSession("social_update_profile", null);
					IclubWebHelper.addMessage("Personal Details Updated Successfully", FacesMessage.SEVERITY_INFO);
					loadBean = false;
					return "userDashboard";
				} else {
					IclubWebHelper.addMessage("Fail :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				}
			}
			
		} catch (Exception e) {
			IclubWebHelper.addMessage("Fail :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
		return null;
	}
	
	public void updatePassword() {
		
		try {
			if (validateLoginForm(!updateLogin)) {
				IclubLoginModel model = new IclubLoginModel();
				WebClient client = null;
				
				if (loginBean.getLId() != null) {
					client = IclubWebHelper.createCustomClient(BASE_URL + "mod");
					model.setLId(loginBean.getLId());
					
				} else {
					client = IclubWebHelper.createCustomClient(LOG_BASE_URL + "add");
					model.setLId(UUID.randomUUID().toString());
				}
				model.setLCrtdDt(new Date(System.currentTimeMillis()));
				model.setLLastDate(loginBean.getLLastDate());
				model.setLName(loginBean.getLName());
				model.setLPasswd(Base64.encodeBase64URLSafeString(DigestUtils.md5(loginBean.getLPasswd())));
				model.setLSecAns(loginBean.getLSecAns());
				model.setIclubPersonAByLCrtdBy(bean.getPId());
				model.setIclubPersonBByLPersonId(getSessionUserId());
				model.setIclubRoleType(2l);
				model.setIclubSecurityQuestion(loginBean.getIclubSecurityQuestion());
				
				ResponseModel response = null;
				if (updateLogin) {
					response = client.accept(MediaType.APPLICATION_JSON).put(model, ResponseModel.class);
				} else {
					response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
				}
				
				if (response.getStatusCode() == 0) {
					IclubWebHelper.addMessage("Personal Details Updated Successfully", FacesMessage.SEVERITY_INFO);
					
				} else {
					IclubWebHelper.addMessage("Fail :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				}
			}
			
		} catch (Exception e) {
			IclubWebHelper.addMessage("Fail :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public boolean validateLoginForm(boolean flag) {
		boolean ret = true;
		if (loginBean.getLPasswd() == null || loginBean.getLPasswd().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(("Password Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (loginBean.getIclubSecurityQuestion() == null) {
			IclubWebHelper.addMessage(("Please select Security Question"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		
		if (loginBean.getLSecAns() == null || loginBean.getLSecAns().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(("Security Ans Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		
		return ret;
	}
	
	public boolean validateForm(boolean flag) {
		boolean ret = true;
		if (bean.getPFName() == null || bean.getPFName().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(("First Name Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (bean.getPLName() == null || bean.getPLName().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(("Last Name Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (bean.getPMobile() == null || bean.getPMobile().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(("Mobile Number Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		
		if (bean.getPGender() == null || bean.getPGender().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(("Gender Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		
		if (bean.getPIdNum() == null || bean.getPIdNum().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(("Id Number Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (bean.getIclubIdType() == null) {
			IclubWebHelper.addMessage(("Please Select ID Type"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (bean.getPIsPensioner() == null || bean.getPIsPensioner().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(("Please Select Pensioner"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (bean.getPDob() == null) {
			IclubWebHelper.addMessage(("Please Select DOB"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		} else if (IclubWebHelper.calculateYearDiff(bean.getPDob().getTime()) <= 18) {
			IclubWebHelper.addMessage(("You must be over 18 years"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (bean.getPIdIssueDt() == null) {
			IclubWebHelper.addMessage(("Please Select IssueDate"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		} else if (IclubWebHelper.isCurrentDate(bean.getPIdIssueDt().getTime())) {
			IclubWebHelper.addMessage(("Issue Date Should be less than Current Date"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		} else if (bean.getPIdExpiryDt() != null && !IclubWebHelper.isCurrentDate(bean.getPIdExpiryDt().getTime())) {
			IclubWebHelper.addMessage(("Expiry Date Should be greater than Current Date"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		} else if (bean.getPIdExpiryDt() != null && !IclubWebHelper.isCurrentDate(bean.getPIdExpiryDt().getTime())) {
			IclubWebHelper.addMessage(("Expiry Date Should be greater than Current Date"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		} else if (((Long) bean.getPIdIssueDt().getTime()).compareTo(((Long) bean.getPIdExpiryDt().getTime())) >= 0) {
			IclubWebHelper.addMessage((" Id Issue Date Should be less than Expiry Date"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		String idValidate = IclubWebHelper.validateId(bean.getPIdNum(), bean.getPGender());
		if (idValidate != null && !idValidate.trim().equalsIgnoreCase("")) {
			
			IclubWebHelper.addMessage(idValidate, FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		return ret;
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
	
	public List<IclubMaritialStatusBean> getMaritialStatusBeans() {
		WebClient client = IclubWebHelper.createCustomClient(MS_BASE_URL + "list");
		Collection<? extends IclubMaritialStatusModel> models = new ArrayList<IclubMaritialStatusModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubMaritialStatusModel.class));
		client.close();
		maritialStatusBeans = new ArrayList<IclubMaritialStatusBean>();
		if (models != null && models.size() > 0) {
			for (IclubMaritialStatusModel model : models) {
				IclubMaritialStatusBean bean = IclubMaritialStatusTrans.fromWStoUI(model);
				
				maritialStatusBeans.add(bean);
			}
		}
		return maritialStatusBeans;
	}
	
	public void setMaritialStatusBeans(List<IclubMaritialStatusBean> maritialStatusBeans) {
		this.maritialStatusBeans = maritialStatusBeans;
	}
	
	public List<IclubIdTypeBean> getIdTypeBeans() {
		WebClient client = IclubWebHelper.createCustomClient(IT_BASE_URL + "list");
		Collection<? extends IclubIdTypeModel> models = new ArrayList<IclubIdTypeModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubIdTypeModel.class));
		client.close();
		idTypeBeans = new ArrayList<IclubIdTypeBean>();
		if (models != null && models.size() > 0) {
			for (IclubIdTypeModel model : models) {
				IclubIdTypeBean bean = IclubIdTypeTrans.fromWStoUI(model);
				idTypeBeans.add(bean);
			}
		}
		return idTypeBeans;
	}
	
	public void setIdTypeBeans(List<IclubIdTypeBean> idTypeBeans) {
		this.idTypeBeans = idTypeBeans;
	}
	
	public IclubPersonBean getBean() {
		if (bean == null) {
			bean = new IclubPersonBean();
			
		}
		if (!loadBean) {
			if (IclubWebHelper.getObjectIntoSession(BUNDLE.getString("logged.in.user.id")) != null) {
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "get/" + getSessionUserId());
				
				IclubPersonModel model = (IclubPersonModel) (client.accept(MediaType.APPLICATION_JSON).get(IclubPersonModel.class));
				client.close();
				bean = IclubPersonTrans.fromWStoUI(model);
				
				if (IclubWebHelper.getObjectIntoSession(BUNDLE.getString("logged.in.login.id")) != null) {
					client = IclubWebHelper.createCustomClient(LOG_BASE_URL + "get/" + IclubWebHelper.getObjectIntoSession(BUNDLE.getString("logged.in.login.id")).toString());
				} else {
					client = IclubWebHelper.createCustomClient(LOG_BASE_URL + "person/" + bean.getPFName());
				}
				
				IclubLoginModel loginModel = (IclubLoginModel) (client.accept(MediaType.APPLICATION_JSON).get(IclubLoginModel.class));
				if (loginModel != null && loginModel.getLId() != null) {
					updateLogin = true;
					loginBean = IclubLoginTrans.fromWStoUI(loginModel);
				} else {
					updateLogin = false;
					loginBean = new IclubLoginBean();
				}
			}
		}
		
		return bean;
	}
	
	public void validateID(AjaxBehaviorEvent event) {
		
		String idValidate = IclubWebHelper.validateId(bean.getPIdNum(), bean.getPGender());
		if (idValidate != null && !idValidate.trim().equalsIgnoreCase("")) {
			
			IclubWebHelper.addMessage(idValidate, FacesMessage.SEVERITY_ERROR);
		} else {
			String dateOfBirth = bean.getPIdNum().toString().substring(0, 6);
			SimpleDateFormat formatter = new SimpleDateFormat("ddMMyy");
			try {
				Date dateOfBirthD = formatter.parse(dateOfBirth);
				bean.setPDob(dateOfBirthD);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void setBean(IclubPersonBean bean) {
		this.bean = bean;
	}
	
	public List<IclubOccupationBean> getOccupationBeans() {
		WebClient client = IclubWebHelper.createCustomClient(OCN_BASE_URL + "list");
		Collection<? extends IclubOccupationModel> models = new ArrayList<IclubOccupationModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubOccupationModel.class));
		client.close();
		occupationBeans = new ArrayList<IclubOccupationBean>();
		if (models != null && models.size() > 0) {
			for (IclubOccupationModel model : models) {
				
				IclubOccupationBean bean = IclubOccupationTrans.fromWStoUI(model);
				
				occupationBeans.add(bean);
			}
		}
		return occupationBeans;
	}
	
	public void setOccupationBeans(List<IclubOccupationBean> occupationBeans) {
		this.occupationBeans = occupationBeans;
	}
	
	public List<IclubCountryCodeBean> getCountryCodeBeans() {
		WebClient client = IclubWebHelper.createCustomClient(CCDE_BASE_URL + "list");
		Collection<? extends IclubCountryCodeModel> models = new ArrayList<IclubCountryCodeModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubCountryCodeModel.class));
		client.close();
		countryCodeBeans = new ArrayList<IclubCountryCodeBean>();
		if (models != null && models.size() > 0) {
			for (IclubCountryCodeModel model : models) {
				IclubCountryCodeBean bean = IclubCountryCodeTrans.fromWStoUI(model);
				
				countryCodeBeans.add(bean);
			}
		}
		return countryCodeBeans;
	}
	
	public void setCountryCodeBeans(List<IclubCountryCodeBean> countryCodeBeans) {
		this.countryCodeBeans = countryCodeBeans;
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
		getDocIds().add(docId);
		try {
			IclubDocumentModel model = new IclubDocumentModel();
			model.setIclubPerson(getSessionUserId());
			model.setDCrtdDt(new Date(System.currentTimeMillis()));
			model.setDId(docId);
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
		if (bean != null && bean.getPId() != null) {
			WebClient client = IclubWebHelper.createCustomClient(D_BASE_URL + "get/entity/" + bean.getPId() + "" + "/1");
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
	
	public ResourceBundle getLabelBundle() {
		
		labelBundle = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "labels");
		return labelBundle;
	}
	
	public void setLabelBundle(ResourceBundle labelBundle) {
		this.labelBundle = labelBundle;
	}
	
	public IclubLoginBean getLoginBean() {
		
		if (loginBean == null) {
			loginBean = new IclubLoginBean();
			
		}
		return loginBean;
	}
	
	public void setLoginBean(IclubLoginBean loginBean) {
		this.loginBean = loginBean;
	}
	
	public List<IclubSecurityQuestionBean> getSecurityQuestionBeans() {
		
		WebClient client = IclubWebHelper.createCustomClient(SECQ_BASE_URL + "list");
		Collection<? extends IclubSecurityQuestionModel> models = new ArrayList<IclubSecurityQuestionModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubSecurityQuestionModel.class));
		client.close();
		securityQuestionBeans = new ArrayList<IclubSecurityQuestionBean>();
		if (models != null && models.size() > 0) {
			for (IclubSecurityQuestionModel model : models) {
				IclubSecurityQuestionBean bean = IclubSecurityQuestionTrans.fromWStoUI(model);
				
				securityQuestionBeans.add(bean);
			}
		}
		return securityQuestionBeans;
	}
	
	public void setSecurityQuestionBeans(List<IclubSecurityQuestionBean> securityQuestionBeans) {
		this.securityQuestionBeans = securityQuestionBeans;
	}
	
	public boolean isUpdateLogin() {
		return updateLogin;
	}
	
	public void setUpdateLogin(boolean updateLogin) {
		this.updateLogin = updateLogin;
	}
	
	public boolean isLoadBean() {
		return loadBean;
	}
	
	public void setLoadBean(boolean loadBean) {
		this.loadBean = loadBean;
	}
}
