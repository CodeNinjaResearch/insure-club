package za.co.iclub.pss.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.log4j.Logger;

import za.co.iclub.pss.web.bean.IclubPersonBean;
import za.co.iclub.pss.web.bean.IclubPurposeTypeBean;
import za.co.iclub.pss.web.bean.IclubVehicleMasterBean;
import za.co.iclub.pss.web.util.IclubWebHelper;
import za.co.iclub.pss.ws.model.IclubPurposeTypeModel;
import za.co.iclub.pss.ws.model.IclubVehicleMasterModel;

@ManagedBean(name = "iclubQuickQuoteController")
@SessionScoped
public class IclubQuickQuoteController implements Serializable {

	private static final long serialVersionUID = -6405843984156478759L;
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("iclub-web");
	protected static final Logger LOGGER = Logger.getLogger(IclubQuickQuoteController.class);
	private static final String PUR_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubPurposeTypeService/";
	private static final String VEH_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubVehicleMasterService/";
	private List<String> vmMakes;

	private String vmMake;

	private String vmModel;

	private String vmYear;

	private String purposeType;

	private IclubPersonBean personBean;

	private List<IclubVehicleMasterBean> vBeans;

	private List<IclubPurposeTypeBean> pBeans;

	private List<String> years;

	private String sessionUserId;

	public void initializePage() {

	}

	public void vmMakeValueChangeListener(ValueChangeEvent valueChangeEvent) {
		if (valueChangeEvent != null && valueChangeEvent.getNewValue() != null && !valueChangeEvent.getNewValue().toString().trim().equalsIgnoreCase("-1")) {

			WebClient client = IclubWebHelper.createCustomClient(VEH_BASE_URL + "getByMake/" + valueChangeEvent.getNewValue().toString());
			Collection<? extends IclubVehicleMasterModel> models = new ArrayList<IclubVehicleMasterModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubVehicleMasterModel.class));
			client.close();
			vBeans = new ArrayList<IclubVehicleMasterBean>();
			for (IclubVehicleMasterModel model : models) {
				IclubVehicleMasterBean bean = new IclubVehicleMasterBean();

				bean.setVmId(model.getVmId());

				bean.setVmMake(model.getVmMake());
				bean.setVmModel(model.getVmModel());
				bean.setVmMrktRate(model.getVmMrktRate());
				bean.setVmOrigRate(model.getVmOrigRate());
				bean.setVmRetRate(model.getVmRetRate());
				bean.setVmProdDt(model.getVmProdDt());
				bean.setVmCrtdDt(model.getVmCrtdDt());
				bean.setIclubPerson(model.getIclubPerson());

				if (model.getIclubVehicles() != null && model.getIclubVehicles().length > 0) {
					String[] vehicles = new String[model.getIclubVehicles().length];
					int i = 0;
					for (String vehicle : model.getIclubVehicles()) {
						vehicles[i] = vehicle;
						i++;
					}
					bean.setIclubVehicles(vehicles);
				}
				vBeans.add(bean);
			}

		} else {
			if (vBeans != null) {
				vBeans.clear();
			}
			if (years != null) {
				years.clear();
			}
		}
	}

	public void vmModelValueChangeListener(ValueChangeEvent valueChangeEvent) {
		if (valueChangeEvent != null && valueChangeEvent.getNewValue() != null && !valueChangeEvent.getNewValue().toString().trim().equalsIgnoreCase("-1")) {

			WebClient client = IclubWebHelper.createCustomClient(VEH_BASE_URL + "get/" + valueChangeEvent.getNewValue().toString());
			IclubVehicleMasterModel model = (IclubVehicleMasterModel) (client.accept(MediaType.APPLICATION_JSON).get(IclubVehicleMasterModel.class));
			client.close();
			years = new ArrayList<String>();
			if (model != null && model.getVmProdDt() != null) {
				Calendar now = Calendar.getInstance();
				int currentYear = now.get(Calendar.YEAR);
				now.setTimeInMillis(model.getVmProdDt().getTime());
				int prodYear = now.get(Calendar.YEAR);
				for (int i = prodYear; i <= currentYear; i++) {
					years.add(i + "");
				}

			}

		} else {
			if (years != null) {
				years.clear();
			}
		}
	}

	public void save() {

	}

	public List<String> getVmMakes() {

		WebClient client = IclubWebHelper.createCustomClient(VEH_BASE_URL + "/listAllMake");
		Collection<? extends String> models = new ArrayList<String>(client.accept(MediaType.APPLICATION_JSON).getCollection(String.class));

		vmMakes = new ArrayList<>(models);

		client.close();
		return vmMakes;
	}

	public void setVmMakes(List<String> vmMakes) {
		this.vmMakes = vmMakes;
	}

	public List<IclubVehicleMasterBean> getvBeans() {
		return vBeans;
	}

	public void setvBeans(List<IclubVehicleMasterBean> vBeans) {
		this.vBeans = vBeans;
	}

	public String getVmMake() {
		return vmMake;
	}

	public void setVmMake(String vmMake) {
		this.vmMake = vmMake;
	}

	public List<String> getYears() {
		return years;
	}

	public void setYears(List<String> years) {
		this.years = years;
	}

	public String getVmModel() {
		return vmModel;
	}

	public void setVmModel(String vmModel) {
		this.vmModel = vmModel;
	}

	public String getVmYear() {
		return vmYear;
	}

	public void setVmYear(String vmYear) {
		this.vmYear = vmYear;
	}

	public List<IclubPurposeTypeBean> getpBeans() {

		WebClient client = IclubWebHelper.createCustomClient(PUR_BASE_URL + "/get/insurnceitemtype/" + "1");
		Collection<? extends IclubPurposeTypeModel> models = new ArrayList<IclubPurposeTypeModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubPurposeTypeModel.class));
		client.close();
		pBeans = new ArrayList<IclubPurposeTypeBean>();
		for (IclubPurposeTypeModel model : models) {
			IclubPurposeTypeBean bean = new IclubPurposeTypeBean();

			bean.setPtId(model.getPtId());
			bean.setPtLongDesc(model.getPtLongDesc());
			bean.setPtShortDesc(model.getPtShortDesc());
			bean.setPtStatus(model.getPtStatus());
			bean.setPtCrtdDt(model.getPtCrtdDt());
			bean.setIclubPerson(model.getIclubPerson());
			bean.setIclubInsuranceItemType(model.getIclubInsuranceItemType());

			if (model.getIclubProperties() != null && model.getIclubProperties().length > 0) {
				String[] properties = new String[model.getIclubProperties().length];
				int i = 0;
				for (String iclubProperty : model.getIclubProperties()) {
					properties[i] = iclubProperty;
					i++;
				}
				bean.setIclubProperties(properties);
			}

			if (model.getIclubVehicles() != null && model.getIclubVehicles().length > 0) {
				String[] vehicles = new String[model.getIclubVehicles().length];
				int i = 0;
				for (String iclubVehicle : model.getIclubVehicles()) {
					vehicles[i] = iclubVehicle;
					i++;
				}
				bean.setIclubVehicles(vehicles);
			}
			pBeans.add(bean);
		}
		return pBeans;
	}

	public void setpBeans(List<IclubPurposeTypeBean> pBeans) {
		this.pBeans = pBeans;
	}

	public String getPurposeType() {
		return purposeType;
	}

	public void setPurposeType(String purposeType) {
		this.purposeType = purposeType;
	}

	public IclubPersonBean getPersonBean() {
		if (personBean == null)
			personBean = new IclubPersonBean();
		return personBean;
	}

	public void setPersonBean(IclubPersonBean personBean) {
		this.personBean = personBean;
	}

	public String getSessionUserId() {
		return sessionUserId;
	}

	public void setSessionUserId(String sessionUserId) {
		this.sessionUserId = sessionUserId;
	}

}
