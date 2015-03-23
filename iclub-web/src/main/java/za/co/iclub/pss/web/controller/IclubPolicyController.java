package za.co.iclub.pss.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.log4j.Logger;

import za.co.iclub.pss.web.bean.IclubInsuranceItemBean;
import za.co.iclub.pss.web.bean.IclubPolicyBean;
import za.co.iclub.pss.web.bean.IclubPropertyBean;
import za.co.iclub.pss.web.bean.IclubVehicleBean;
import za.co.iclub.pss.web.util.IclubWebHelper;
import za.co.iclub.pss.ws.model.IclubInsuranceItemModel;
import za.co.iclub.pss.ws.model.IclubPolicyModel;
import za.co.iclub.pss.ws.model.IclubPropertyModel;
import za.co.iclub.pss.ws.model.IclubVehicleModel;

@ManagedBean(name = "iclubPolicyController")
@SessionScoped
public class IclubPolicyController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1299854691643272437L;
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("iclub-web");
	protected static final Logger LOGGER = Logger.getLogger(IclubPolicyController.class);
	private static final String PCY_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubPolicyService/";
	private static final String II_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubInsuranceItemService/";
	private static final String V_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubVehicleService/";
	private static final String PRO_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubPropertyService/";
	private List<IclubPolicyBean> beans;

	private boolean iItemFalg;

	private List<IclubInsuranceItemBean> iItemBeans;

	private IclubVehicleBean vehicleBean;

	private boolean vehhicleFlag;

	private boolean propertyFlag;

	private IclubPropertyBean propertyBean;

	private String sessionUserId;

	@SuppressWarnings("unchecked")
	public List<IclubPolicyBean> getBeans() {

		WebClient client = IclubWebHelper.createCustomClient(PCY_BASE_URL + "get/user/" + getSessionUserId());

		List<IclubPolicyModel> models = (ArrayList<IclubPolicyModel>) (client.accept(MediaType.APPLICATION_JSON).getCollection(IclubPolicyModel.class));

		if (models != null && models.size() > 0) {
			beans = new ArrayList<IclubPolicyBean>();
			for (IclubPolicyModel model : models) {
				if (model != null && model.getPId() != null) {
					IclubPolicyBean bean = new IclubPolicyBean();
					bean.setPId(model.getPId());
					bean.setPProrataPrm(model.getPProrataPrm());
					bean.setPPremium(model.getPPremium());
					bean.setPNumber(model.getPNumber());
					bean.setPDebitDt(model.getPDebitDt());
					bean.setPCrtdDt(model.getPCrtdDt());
					bean.setIclubAccount(model.getIclubAccount());
					bean.setIclubQuote(model.getIclubQuote());
					bean.setIclubPolicyStatus(model.getIclubPolicyStatus());
					bean.setIclubPerson(model.getIclubPerson());
					bean.setIclubPolicyStatus(model.getIclubPolicyStatus());

					if (model.getIclubClaims() != null && model.getIclubClaims().length > 0) {
						String[] claims = new String[model.getIclubClaims().length];
						int i = 0;
						for (String claim : model.getIclubClaims()) {
							claims[i] = claim;
							i++;
						}
						bean.setIclubClaims(claims);
					}

					if (model.getIclubPayments() != null && model.getIclubPayments().length > 0) {
						String[] payments = new String[model.getIclubPayments().length];
						int i = 0;
						for (String payment : model.getIclubPayments()) {
							payments[i] = payment;
							i++;
						}
						bean.setIclubClaims(payments);
					}
					beans.add(bean);
				}

			}
		}

		return beans;
	}

	public void setBeans(List<IclubPolicyBean> beans) {
		this.beans = beans;
	}

	@SuppressWarnings("unchecked")
	public String policyListener(IclubPolicyBean policyBean) {
		WebClient client = IclubWebHelper.createCustomClient(II_BASE_URL + "get/quoteId/" + policyBean.getIclubQuote());

		List<IclubInsuranceItemModel> models = (ArrayList<IclubInsuranceItemModel>) (client.accept(MediaType.APPLICATION_JSON).getCollection(IclubInsuranceItemModel.class));
		iItemBeans = new ArrayList<IclubInsuranceItemBean>();
		for (IclubInsuranceItemModel model : models) {
			IclubInsuranceItemBean bean = new IclubInsuranceItemBean();

			bean.setIiId(model.getIiId());
			bean.setIiItemId(model.getIiItemId());
			bean.setIiQuoteId(model.getIiQuoteId());
			bean.setIiCrtdDt(model.getIiCrtdDt());
			bean.setIclubInsuranceItemType(model.getIclubInsuranceItemType());
			bean.setIclubPerson(model.getIclubPerson());

			if (model.getIclubClaimItems() != null && model.getIclubClaimItems().length > 0) {
				String[] claimItems = new String[model.getIclubClaimItems().length];
				int i = 0;
				for (String claimItem : model.getIclubClaimItems()) {
					claimItems[i] = claimItem;
					i++;
				}
				bean.setIclubClaimItems(claimItems);
			}
			iItemFalg = true;
			iItemBeans.add(bean);

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
				vehicleBean = new IclubVehicleBean();

				vehicleBean.setVId(model.getVId());
				vehicleBean.setVOwner(model.getVOwner());
				vehicleBean.setVGearLockYn(model.getVGearLockYn());
				vehicleBean.setVImmYn(model.getVImmYn());
				vehicleBean.setVConcessReason(model.getVConcessReason());
				vehicleBean.setVConcessPrct(model.getVConcessPrct());
				vehicleBean.setVInsuredValue(model.getVInsuredValue());
				vehicleBean.setVYear(model.getVYear());
				vehicleBean.setVDdLong(model.getVDdLong());
				vehicleBean.setVDdLat(model.getVDdLat());
				vehicleBean.setVDdArea(model.getVDdArea());
				vehicleBean.setVOnLong(model.getVOnLong());
				vehicleBean.setVOnLat(model.getVOnLat());
				vehicleBean.setVOnArea(model.getVOnArea());
				vehicleBean.setVOdometer(model.getVOdometer());
				vehicleBean.setVCrtdDt(model.getVCrtdDt());
				vehicleBean.setVRegNum(model.getVRegNum());
				vehicleBean.setVEngineNr(model.getVEngineNr());
				vehicleBean.setVVin(model.getVVin());
				vehicleBean.setVNoclaimYrs(model.getVNoclaimYrs());
				vehicleBean.setIclubVehicleMaster(model.getIclubVehicleMaster());
				vehicleBean.setIclubPurposeType(model.getIclubPurposeType());
				vehicleBean.setIclubSecurityMaster(model.getIclubSecurityMaster());
				vehicleBean.setIclubPerson(model.getIclubPerson());
				vehicleBean.setIclubDriver(model.getIclubDriver());
				vehicleBean.setIclubSecurityDevice(model.getIclubSecurityDevice());
				vehicleBean.setIclubAccessTypeByVDdAccessTypeId(model.getIclubAccessTypeByVDdAccessTypeId());
				vehicleBean.setIclubAccessTypeByVOnAccessTypeId(model.getIclubAccessTypeByVOnAccessTypeId());
				client.close();
			}
		} else if (bean != null && bean.getIclubInsuranceItemType().compareTo(2l) == 0) {
			vehhicleFlag = false;
			propertyFlag = true;

			WebClient client = IclubWebHelper.createCustomClient(PRO_BASE_URL + "get/" + bean.getIiItemId());
			IclubPropertyModel model = (IclubPropertyModel) (client.accept(MediaType.APPLICATION_JSON).get(IclubPropertyModel.class));

			if (model != null && model.getPId() != null) {
				propertyBean = new IclubPropertyBean();
				propertyBean.setPId(model.getPId());
				propertyBean.setPCrtdDt(model.getPCrtdDt());
				propertyBean.setPEstValue(model.getPEstValue());
				propertyBean.setPSecGatesYn(model.getPSecGatesYn());
				propertyBean.setPNorobberyYn(model.getPNorobberyYn());
				propertyBean.setPCompYn(model.getPCompYn());
				propertyBean.setPRentFurYn(model.getPRentFurYn());
				propertyBean.setPNoclaimYrs(model.getPNoclaimYrs());
				propertyBean.setPPostalCd(model.getPPostalCd());
				propertyBean.setPLong(model.getPLong());
				propertyBean.setPLat(model.getPLat());
				propertyBean.setPAddress(model.getPAddress());
				propertyBean.setPRegNum(model.getPRegNum());
				propertyBean.setIclubCoverType(model.getIclubCoverType());
				propertyBean.setIclubPurposeType(model.getIclubPurposeType());
				propertyBean.setIclubOccupiedStatus(model.getIclubOccupiedStatus());
				propertyBean.setIclubPropertyType(model.getIclubPropertyType());
				propertyBean.setIclubWallType(model.getIclubWallType());
				propertyBean.setIclubAccessType(model.getIclubAccessType());
				propertyBean.setIclubPerson(model.getIclubPerson());
				propertyBean.setIclubBarType(model.getIclubBarType());
				propertyBean.setIclubThatchType(model.getIclubThatchType());
				propertyBean.setIclubRoofType(model.getIclubRoofType());
			}
			client.close();
		} else {
			vehhicleFlag = false;
			propertyFlag = false;

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

	public List<IclubInsuranceItemBean> getiItemBeans() {
		return iItemBeans;
	}

	public void setiItemBeans(List<IclubInsuranceItemBean> iItemBeans) {
		this.iItemBeans = iItemBeans;
	}

	public boolean isiItemFalg() {
		return iItemFalg;
	}

	public void setiItemFalg(boolean iItemFalg) {
		this.iItemFalg = iItemFalg;
	}

	public IclubVehicleBean getVehicleBean() {
		if (vehicleBean == null) {
			vehicleBean = new IclubVehicleBean();
		}
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

		if (propertyBean == null) {
			propertyBean = new IclubPropertyBean();
		}
		return propertyBean;
	}

	public void setPropertyBean(IclubPropertyBean propertyBean) {
		this.propertyBean = propertyBean;
	}

}
