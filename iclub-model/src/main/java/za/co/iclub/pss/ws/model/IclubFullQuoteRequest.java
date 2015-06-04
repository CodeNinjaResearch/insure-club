package za.co.iclub.pss.ws.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubFullQuoteRequest")
public class IclubFullQuoteRequest {
	
	private IclubQuoteModel iclubQuoteModel;
	private IclubVehicleModel[] iclubVehicleModel;
	private IclubPropertyModel[] iclubPropertyModel;
	private IclubPropertyItemModel[] IclubPropertyItemModel;
	private IclubDriverModel iclubDriverModel;
	
	public IclubQuoteModel getIclubQuoteModel() {
		return iclubQuoteModel;
	}
	
	public void setIclubQuoteModel(IclubQuoteModel iclubQuoteModel) {
		this.iclubQuoteModel = iclubQuoteModel;
	}
	
	public IclubVehicleModel[] getIclubVehicleModel() {
		return iclubVehicleModel;
	}
	
	public void setIclubVehicleModel(IclubVehicleModel[] iclubVehicleModel) {
		this.iclubVehicleModel = iclubVehicleModel;
	}
	
	public IclubPropertyModel[] getIclubPropertyModel() {
		return iclubPropertyModel;
	}
	
	public void setIclubPropertyModel(IclubPropertyModel[] iclubPropertyModel) {
		this.iclubPropertyModel = iclubPropertyModel;
	}
	
	public IclubPropertyItemModel[] getIclubPropertyItemModel() {
		return IclubPropertyItemModel;
	}
	
	public void setIclubPropertyItemModel(IclubPropertyItemModel[] iclubPropertyItemModel) {
		IclubPropertyItemModel = iclubPropertyItemModel;
	}
	
	public IclubDriverModel getIclubDriverModel() {
		return iclubDriverModel;
	}
	
	public void setIclubDriverModel(IclubDriverModel iclubDriverModel) {
		this.iclubDriverModel = iclubDriverModel;
	}
}
