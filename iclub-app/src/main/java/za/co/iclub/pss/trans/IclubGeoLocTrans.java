package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubGeoLocBean;
import za.co.iclub.pss.model.ws.IclubGeoLocModel;
import za.co.iclub.pss.orm.bean.IclubGeoLoc;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;

public class IclubGeoLocTrans {
	
	public static IclubGeoLocBean fromWStoUI(IclubGeoLocModel model) {
		IclubGeoLocBean bean = new IclubGeoLocBean();
		
		bean.setGlProvince(model.getGlProvince());
		bean.setGlSuburb(model.getGlSuburb());
		bean.setGlId(model.getGlId());
		bean.setGlAddress(model.getGlAddress());
		bean.setGlLat(model.getGlLat());
		bean.setGlLong(model.getGlLong());
		bean.setGlRate(model.getGlRate());
		bean.setGlCrtdDt(model.getGlCrtdDt());
		bean.setIclubPerson(model.getIclubPerson());
		bean.setPFNameAndLName(model.getPFNameAndLName());
		
		return bean;
	}
	
	public static IclubGeoLocModel fromUItoWS(IclubGeoLocBean bean) {
		
		IclubGeoLocModel model = new IclubGeoLocModel();
		
		model.setGlProvince(bean.getGlProvince());
		model.setGlSuburb(bean.getGlSuburb());
		model.setGlId(bean.getGlId());
		model.setGlAddress(bean.getGlAddress());
		model.setGlLat(bean.getGlLat());
		model.setGlLong(bean.getGlLong());
		model.setGlRate(bean.getGlRate());
		model.setGlCrtdDt(bean.getGlCrtdDt());
		model.setIclubPerson(bean.getIclubPerson());
		model.setPFNameAndLName(bean.getPFNameAndLName());
		
		return model;
	}
	
	public static IclubGeoLocModel fromORMtoWS(IclubGeoLoc bean) {
		
		IclubGeoLocModel model = new IclubGeoLocModel();
		
		model.setGlProvince(bean.getGlProvince());
		model.setGlSuburb(bean.getGlSuburb());
		model.setGlId(bean.getGlId());
		model.setGlAddress(bean.getGlAddress());
		model.setGlLat(bean.getGlLat());
		model.setGlLong(bean.getGlLong());
		model.setGlRate(bean.getGlRate());
		model.setGlCrtdDt(bean.getGlCrtdDt());
		model.setIclubPerson(bean.getIclubPerson() != null ? bean.getIclubPerson().getPId() : null);
		model.setPFNameAndLName(bean.getIclubPerson() != null ? bean.getIclubPerson().getPFName() + " " + bean.getIclubPerson().getPLName() != null ? bean.getIclubPerson().getPLName() : "" : "");
		
		return model;
	}
	
	public static IclubGeoLoc fromWStoORM(IclubGeoLocModel model, IclubPersonDAO iclubPersonDAO) {
		
		IclubGeoLoc bean = new IclubGeoLoc();
		
		bean.setGlId(model.getGlId());
		bean.setGlAddress(model.getGlAddress());
		bean.setGlProvince(model.getGlProvince());
		bean.setGlSuburb(model.getGlSuburb());
		bean.setGlLat(model.getGlLat());
		bean.setGlLong(model.getGlLong());
		bean.setGlRate(model.getGlRate());
		bean.setGlCrtdDt(model.getGlCrtdDt());
		bean.setIclubPerson(iclubPersonDAO.findById(model.getIclubPerson()));
		
		return bean;
	}
	
}
