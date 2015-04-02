package za.co.iclub.pss.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;

import za.co.iclub.pss.web.bean.IclubCountryCodeBean;
import za.co.iclub.pss.web.bean.IclubIdTypeBean;
import za.co.iclub.pss.web.bean.IclubMaritialStatusBean;
import za.co.iclub.pss.web.bean.IclubOccupationBean;
import za.co.iclub.pss.web.bean.IclubPersonBean;
import za.co.iclub.pss.web.util.IclubWebHelper;
import za.co.iclub.pss.ws.model.IclubCountryCodeModel;
import za.co.iclub.pss.ws.model.IclubIdTypeModel;
import za.co.iclub.pss.ws.model.IclubMaritialStatusModel;
import za.co.iclub.pss.ws.model.IclubOccupationModel;
import za.co.iclub.pss.ws.model.IclubPersonModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@ManagedBean(name = "iclubProfileController")
@SessionScoped
public class IclubProfileController implements Serializable {

	private static final long serialVersionUID = 4703596329233786371L;
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("iclub-web");
	// private static final Logger LOGGER =
	// Logger.getLogger(IclubProductTypeController.class);
	private static final String BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubPersonService/";
	private static final String OCN_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubOccupationService/";
	private static final String MS_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubMaritialStatusService/";
	private static final String IT_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubIdTypeService/";
	private static final String CCDE_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubCountryCodeService/";

	private IclubPersonBean bean;

	private List<IclubOccupationBean> occupationBeans;

	private List<IclubCountryCodeBean> countryCodeBeans;

	private List<IclubMaritialStatusBean> maritialStatusBeans;

	private List<IclubIdTypeBean> idTypeBeans;
	private String sessionUserId;

	public void updatedPerson() {

		WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "mod");

		IclubPersonModel model = new IclubPersonModel();
		model.setPId(bean.getPId());
		model.setPCrtdDt(bean.getPCrtdDt());
		model.setPDob(bean.getPDob());
		model.setPEmail(bean.getPEmail());
		model.setPFName(bean.getPFName());
		model.setPIdNum(bean.getPIdNum());
		model.setPLName(bean.getPLName());
		model.setPMobile(bean.getPMobile());
		model.setPAddress(bean.getPAddress());
		model.setPContactPref(bean.getPContactPref());
		model.setPGender(bean.getPGender());
		model.setPContactPref(bean.getPContactPref());
		model.setPIdExpiryDt(bean.getPIdExpiryDt());
		model.setPInitials(bean.getPInitials());
		model.setPIsPensioner(bean.getPIsPensioner());
		model.setPIdIssueCntry(bean.getPIdIssueCntry().longValue());
		model.setPLat(bean.getPLat());
		model.setPLong(bean.getPLong());
		model.setPOccupation(bean.getPOccupation());
		model.setPTitle(bean.getPTitle());
		model.setPZipCd(bean.getPZipCd());
		model.setIclubIdType(bean.getIclubIdType());
		model.setIclubPerson(getSessionUserId());
		model.setIclubMaritialStatus(bean.getIclubMaritialStatus());

		ResponseModel response = client.accept(MediaType.APPLICATION_JSON).put(model, ResponseModel.class);
		client.close();

		if (response.getStatusCode() == 0) {

			IclubWebHelper.addMessage("Updated Successfully", FacesMessage.SEVERITY_INFO);

		} else {
			IclubWebHelper.addMessage("Fail :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
		}

	}

	public String getSessionUserId() {
		if (sessionUserId == null) {
			sessionUserId = IclubWebHelper.getObjectIntoSession(BUNDLE.getString("logged.in.user.id")).toString();
		}
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
		for (IclubMaritialStatusModel model : models) {
			IclubMaritialStatusBean bean = new IclubMaritialStatusBean();
			bean.setMsId(model.getMsId());
			bean.setMsLongDesc(model.getMsLongDesc());
			bean.setMsShortDesc(model.getMsShortDesc());
			bean.setMsStatus(model.getMsStatus());
			maritialStatusBeans.add(bean);
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
		for (IclubIdTypeModel model : models) {
			IclubIdTypeBean bean = new IclubIdTypeBean();
			bean.setItId(model.getItId());
			bean.setItLongDesc(model.getItLongDesc());
			bean.setItShortDesc(model.getItShortDesc());
			bean.setItStatus(model.getItStatus());
			idTypeBeans.add(bean);
		}
		return idTypeBeans;
	}

	public void setIdTypeBeans(List<IclubIdTypeBean> idTypeBeans) {
		this.idTypeBeans = idTypeBeans;
	}

	public IclubPersonBean getBean() {
		if (getSessionUserId() != null) {
			WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "get/" + getSessionUserId());

			IclubPersonModel model = (IclubPersonModel) (client.accept(MediaType.APPLICATION_JSON).get(IclubPersonModel.class));
			bean = new IclubPersonBean();
			bean.setPId(model.getPId());
			bean.setPCrtdDt(model.getPCrtdDt());
			bean.setPDob(model.getPDob());
			bean.setPEmail(model.getPEmail());
			bean.setPFName(model.getPFName());
			bean.setPIdNum(model.getPIdNum());
			bean.setPLName(model.getPLName());
			bean.setPMobile(model.getPMobile());
			bean.setPAddress(model.getPAddress());
			bean.setPContactPref(model.getPContactPref());
			bean.setPGender(model.getPGender());
			bean.setPContactPref(model.getPContactPref());
			bean.setPIdExpiryDt(model.getPIdExpiryDt());
			bean.setPInitials(model.getPInitials());
			bean.setPIsPensioner(model.getPIsPensioner());
			bean.setPIdIssueCntry(model.getPIdIssueCntry());
			bean.setPLat(model.getPLat());
			bean.setPLong(model.getPLong());
			bean.setPOccupation(model.getPOccupation());
			bean.setPTitle(model.getPTitle());
			bean.setPZipCd(model.getPZipCd());
			bean.setIclubIdType(model.getIclubIdType());
			bean.setIclubPerson(model.getIclubPerson());
			bean.setIclubMaritialStatus(model.getIclubMaritialStatus());
		}
		return bean;
	}

	public void setBean(IclubPersonBean bean) {
		this.bean = bean;
	}

	public List<IclubOccupationBean> getOccupationBeans() {
		WebClient client = IclubWebHelper.createCustomClient(OCN_BASE_URL + "list");
		Collection<? extends IclubOccupationModel> models = new ArrayList<IclubOccupationModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubOccupationModel.class));
		client.close();
		occupationBeans = new ArrayList<IclubOccupationBean>();
		for (IclubOccupationModel model : models) {

			IclubOccupationBean bean = new IclubOccupationBean();

			bean.setOId(model.getOId());
			bean.setODesc(model.getODesc());
			bean.setOCrtdDt(model.getOCrtdDt());
			bean.setOStatus(model.getOStatus());
			bean.setIclubPerson(model.getIclubPerson());

			occupationBeans.add(bean);
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
		for (IclubCountryCodeModel model : models) {
			IclubCountryCodeBean bean = new IclubCountryCodeBean();
			bean.setCcId(model.getCcId().intValue());
			bean.setCcIsoId(model.getCcIsoId());
			bean.setCcShortId(model.getCcShortId());
			bean.setCcCrtdDt(model.getCcCrtdDt());
			bean.setCcName(model.getCcName());
			bean.setIclubPerson(model.getIclubPerson());
			countryCodeBeans.add(bean);
		}
		return countryCodeBeans;
	}

	public void setCountryCodeBeans(List<IclubCountryCodeBean> countryCodeBeans) {
		this.countryCodeBeans = countryCodeBeans;
	}

}
