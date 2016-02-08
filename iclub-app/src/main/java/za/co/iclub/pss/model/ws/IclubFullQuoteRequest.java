package za.co.iclub.pss.model.ws;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubFullQuoteRequest")
public class IclubFullQuoteRequest {

	private IclubQuoteModel iclubQuoteModel;
	private IclubPersonModel iclubPersonModel;
	private List<IclubVehicleModel> iclubVehicleModels;
	private List<IclubPropertyModel> iclubPropertyModels;
	private List<IclubPropertyItemModel> iclubPropertyItemModels;
	private IclubDriverModel iclubDriverModel;
	private IclubAccountModel iclubAccountModel;
	private IclubPolicyModel iclubPolicyModel;
	private IclubClaimModel iclubClaimModel;

	public IclubQuoteModel getIclubQuoteModel() {
		return iclubQuoteModel;
	}

	public void setIclubQuoteModel(IclubQuoteModel iclubQuoteModel) {
		this.iclubQuoteModel = iclubQuoteModel;
	}

	public IclubDriverModel getIclubDriverModel() {
		return iclubDriverModel;
	}

	public void setIclubDriverModel(IclubDriverModel iclubDriverModel) {
		this.iclubDriverModel = iclubDriverModel;
	}

	public IclubPersonModel getIclubPersonModel() {
		return iclubPersonModel;
	}

	public void setIclubPersonModel(IclubPersonModel iclubPersonModel) {
		this.iclubPersonModel = iclubPersonModel;
	}

	public List<IclubVehicleModel> getIclubVehicleModels() {
		return iclubVehicleModels;
	}

	public void setIclubVehicleModels(List<IclubVehicleModel> iclubVehicleModels) {
		this.iclubVehicleModels = iclubVehicleModels;
	}

	public List<IclubPropertyModel> getIclubPropertyModels() {
		return iclubPropertyModels;
	}

	public void setIclubPropertyModels(List<IclubPropertyModel> iclubPropertyModels) {
		this.iclubPropertyModels = iclubPropertyModels;
	}

	public List<IclubPropertyItemModel> getIclubPropertyItemModels() {
		return iclubPropertyItemModels;
	}

	public void setIclubPropertyItemModels(List<IclubPropertyItemModel> iclubPropertyItemModels) {
		this.iclubPropertyItemModels = iclubPropertyItemModels;
	}

	public IclubAccountModel getIclubAccountModel() {
		return iclubAccountModel;
	}

	public void setIclubAccountModel(IclubAccountModel iclubAccountModel) {
		this.iclubAccountModel = iclubAccountModel;
	}

	public IclubPolicyModel getIclubPolicyModel() {
		return iclubPolicyModel;
	}

	public void setIclubPolicyModel(IclubPolicyModel iclubPolicyModel) {
		this.iclubPolicyModel = iclubPolicyModel;
	}

	public IclubClaimModel getIclubClaimModel() {
		return iclubClaimModel;
	}

	public void setIclubClaimModel(IclubClaimModel iclubClaimModel) {
		this.iclubClaimModel = iclubClaimModel;
	}
}
