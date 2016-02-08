package za.co.iclub.pss.model.ws;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubQuickQuoteRequest")
public class IclubQuickQuoteRequest {

	private IclubQuoteModel iclubQuoteModel;
	private IclubPersonModel iclubPersonModel;
	private List<IclubVehicleModel> iclubVehicleModels;
	private List<IclubPropertyModel> iclubPropertyModels;
	private List<IclubPropertyItemModel> iclubPropertyItemModels;
	private IclubDriverModel iclubDriverModel;
	private boolean loginFlag;

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

	public IclubPersonModel getIclubPersonModel() {
		return iclubPersonModel;
	}

	public void setIclubPersonModel(IclubPersonModel iclubPersonModel) {
		this.iclubPersonModel = iclubPersonModel;
	}

	public boolean isLoginFlag() {
		return loginFlag;
	}

	public void setLoginFlag(boolean loginFlag) {
		this.loginFlag = loginFlag;
	}

}
