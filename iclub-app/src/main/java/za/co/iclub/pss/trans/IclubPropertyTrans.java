package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubPropertyBean;
import za.co.iclub.pss.model.ws.IclubPropertyModel;
import za.co.iclub.pss.orm.bean.IclubProperty;
import za.co.iclub.pss.orm.dao.IclubAccessTypeDAO;
import za.co.iclub.pss.orm.dao.IclubBarTypeDAO;
import za.co.iclub.pss.orm.dao.IclubCoverTypeDAO;
import za.co.iclub.pss.orm.dao.IclubOccupiedStatusDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.orm.dao.IclubPropUsageTypeDAO;
import za.co.iclub.pss.orm.dao.IclubPropertyTypeDAO;
import za.co.iclub.pss.orm.dao.IclubRoofTypeDAO;
import za.co.iclub.pss.orm.dao.IclubWallTypeDAO;

public class IclubPropertyTrans {
	
	private IclubPersonDAO iclubPersonDAO;
	private IclubCoverTypeDAO iclubCoverTypeDAO;
	private IclubPropUsageTypeDAO iclubPropUsageTypeDAO;
	private IclubOccupiedStatusDAO iclubOccupiedStatusDAO;
	private IclubPropertyTypeDAO iclubPropertyTypeDAO;
	private IclubWallTypeDAO iclubWallTypeDAO;
	private IclubAccessTypeDAO iclubAccessTypeDAO;
	private IclubBarTypeDAO iclubBarTypeDAO;
	private IclubRoofTypeDAO iclubRoofTypeDAO;
	
	public IclubPropertyBean fromWStoUI(IclubPropertyModel model) {
		
		IclubPropertyBean bean = new IclubPropertyBean();
		
		bean.setPId(model.getPId());
		bean.setPCrtdDt(model.getPCrtdDt());
		bean.setPEstValue(model.getPEstValue());
		bean.setPSecGatesYn(model.getPSecGatesYn());
		bean.setPNorobberyYn(model.getPNorobberyYn());
		bean.setPCompYn(model.getPCompYn());
		bean.setPRentFurYn(model.getPRentFurYn());
		bean.setPNoclaimYrs(model.getPNoclaimYrs());
		bean.setPPostalCd(model.getPPostalCd());
		bean.setPLong(model.getPLong());
		bean.setPLat(model.getPLat());
		bean.setPAddress(model.getPAddress());
		bean.setPRegNum(model.getPRegNum());
		bean.setPReplacementCost(model.getPReplacementCost());
		bean.setPContentCost(model.getPContentCost());
		bean.setIclubCoverType(model.getIclubCoverType());
		bean.setCtLongDesc(model.getCtLongDesc());
		bean.setIclubPropUsageType(model.getIclubPropUsageType());
		bean.setPutLongDesc(model.getPutLongDesc());
		bean.setIclubOccupiedStatus(model.getIclubOccupiedStatus());
		bean.setOsLongDesc(model.getOsLongDesc());
		bean.setIclubPropertyType(model.getIclubPropertyType());
		bean.setPtLongDesc(model.getPtLongDesc());
		bean.setIclubWallType(model.getIclubWallType());
		bean.setWtLongDesc(model.getWtLongDesc());
		bean.setIclubAccessType(model.getIclubAccessType());
		bean.setAtLongDesc(model.getAtLongDesc());
		bean.setIclubBarType(model.getIclubBarType());
		bean.setBtLongDesc(model.getBtLongDesc());
		bean.setPThatchType(model.getPThatchType());
		bean.setIclubRoofType(model.getIclubRoofType());
		bean.setPFNameAndLName(model.getPFNameAndLName());
		bean.setIclubPerson(model.getIclubPerson());
		bean.setPFNameAndLName(model.getPFNameAndLName());
		
		return bean;
	}
	
	public IclubPropertyModel fromUItoWS(IclubPropertyBean bean) {
		
		IclubPropertyModel model = new IclubPropertyModel();
		
		model.setPId(bean.getPId());
		model.setPCrtdDt(bean.getPCrtdDt());
		model.setPEstValue(bean.getPEstValue());
		model.setPSecGatesYn(bean.getPSecGatesYn());
		model.setPNorobberyYn(bean.getPNorobberyYn());
		model.setPCompYn(bean.getPCompYn());
		model.setPRentFurYn(bean.getPRentFurYn());
		model.setPNoclaimYrs(bean.getPNoclaimYrs());
		model.setPPostalCd(bean.getPPostalCd());
		model.setPLong(bean.getPLong());
		model.setPLat(bean.getPLat());
		model.setPAddress(bean.getPAddress());
		model.setPRegNum(bean.getPRegNum());
		model.setPReplacementCost(bean.getPReplacementCost());
		model.setPContentCost(bean.getPContentCost());
		model.setIclubCoverType(bean.getIclubCoverType());
		model.setCtLongDesc(bean.getCtLongDesc());
		model.setIclubPropUsageType(bean.getIclubPropUsageType());
		model.setPutLongDesc(bean.getPutLongDesc());
		model.setIclubOccupiedStatus(bean.getIclubOccupiedStatus());
		model.setOsLongDesc(bean.getOsLongDesc());
		model.setIclubPropertyType(bean.getIclubPropertyType());
		model.setPtLongDesc(bean.getPtLongDesc());
		model.setIclubWallType(bean.getIclubWallType());
		model.setWtLongDesc(bean.getWtLongDesc());
		model.setIclubAccessType(bean.getIclubAccessType());
		model.setAtLongDesc(bean.getAtLongDesc());
		model.setIclubBarType(bean.getIclubBarType());
		model.setBtLongDesc(bean.getBtLongDesc());
		model.setPThatchType(bean.getPThatchType());
		model.setIclubRoofType(bean.getIclubRoofType());
		model.setPFNameAndLName(bean.getPFNameAndLName());
		model.setIclubPerson(bean.getIclubPerson());
		model.setPFNameAndLName(bean.getPFNameAndLName());
		
		return model;
	}
	
	public IclubPropertyModel fromORMtoWS(IclubProperty bean) {
		
		IclubPropertyModel model = new IclubPropertyModel();
		
		model.setPId(bean.getPId());
		model.setPCrtdDt(bean.getPCrtdDt());
		model.setPEstValue(bean.getPEstValue());
		model.setPSecGatesYn(bean.getPSecGatesYn());
		model.setPNorobberyYn(bean.getPNorobberyYn());
		model.setPCompYn(bean.getPCompYn());
		model.setPRentFurYn(bean.getPRentFurYn());
		model.setPNoclaimYrs(bean.getPNoclaimYrs());
		model.setPPostalCd(bean.getPPostalCd());
		model.setPLong(bean.getPLong());
		model.setPLat(bean.getPLat());
		model.setPAddress(bean.getPAddress());
		model.setPRegNum(bean.getPRegNum());
		model.setPReplacementCost(bean.getPReplacementCost());
		model.setPContentCost(bean.getPContentCost());
		model.setIclubCoverType(bean.getIclubCoverType() != null ? (bean.getIclubCoverType().getCtId()) : null);
		model.setCtLongDesc(bean.getIclubCoverType() != null ? (bean.getIclubCoverType().getCtLongDesc()) : null);
		model.setIclubPropUsageType(bean.getIclubPropUsageType() != null ? (bean.getIclubPropUsageType().getPutId()) : null);
		model.setPutLongDesc(bean.getIclubPropUsageType() != null ? (bean.getIclubPropUsageType().getPutLongDesc()) : null);
		model.setIclubOccupiedStatus(bean.getIclubOccupiedStatus() != null ? (bean.getIclubOccupiedStatus().getOsId()) : null);
		model.setOsLongDesc(bean.getIclubOccupiedStatus() != null ? (bean.getIclubOccupiedStatus().getOsLongDesc()) : null);
		model.setIclubPropertyType(bean.getIclubPropertyType() != null ? (bean.getIclubPropertyType().getPtId()) : null);
		model.setPtLongDesc(bean.getIclubPropertyType() != null ? (bean.getIclubPropertyType().getPtLongDesc()) : null);
		model.setIclubWallType(bean.getIclubWallType() != null ? (bean.getIclubWallType().getWtId()) : null);
		model.setWtLongDesc(bean.getIclubWallType() != null ? (bean.getIclubWallType().getWtLongDesc()) : null);
		model.setIclubAccessType(bean.getIclubAccessType() != null ? (bean.getIclubAccessType().getAtId()) : null);
		model.setAtLongDesc(bean.getIclubAccessType() != null ? (bean.getIclubAccessType().getAtLongDesc()) : null);
		model.setIclubPerson(bean.getIclubPerson() != null ? (bean.getIclubPerson().getPId()) : null);
		model.setIclubBarType(bean.getIclubBarType() != null ? (bean.getIclubBarType().getBtId()) : null);
		model.setBtLongDesc(bean.getIclubBarType() != null ? (bean.getIclubBarType().getBtLongDesc()) : null);
		model.setPThatchType(bean.getPThatchType());
		model.setIclubRoofType(bean.getIclubRoofType() != null ? (bean.getIclubRoofType().getRtId()) : null);
		model.setPFNameAndLName(bean.getIclubPerson() != null ? bean.getIclubPerson().getPFName() + " " + bean.getIclubPerson().getPLName() != null ? bean.getIclubPerson().getPLName() : "" : "");
		
		return model;
	}
	
	public IclubProperty fromWStoORM(IclubPropertyModel model) {
		
		IclubProperty bean = new IclubProperty();
		
		bean.setPId(model.getPId());
		bean.setPCrtdDt(model.getPCrtdDt());
		bean.setPEstValue(model.getPEstValue());
		bean.setPSecGatesYn(model.getPSecGatesYn());
		bean.setPNorobberyYn(model.getPNorobberyYn());
		bean.setPCompYn(model.getPCompYn());
		bean.setPRentFurYn(model.getPRentFurYn());
		bean.setPNoclaimYrs(model.getPNoclaimYrs());
		bean.setPPostalCd(model.getPPostalCd());
		bean.setPLong(model.getPLong());
		bean.setPLat(model.getPLat());
		bean.setPAddress(model.getPAddress());
		bean.setPRegNum(model.getPRegNum());
		bean.setPReplacementCost(model.getPReplacementCost());
		bean.setPContentCost(model.getPContentCost());
		bean.setIclubRoofType(model.getIclubRoofType() != null ? iclubRoofTypeDAO.findById(model.getIclubRoofType()) : null);
		bean.setPThatchType(model.getPThatchType());
		bean.setIclubBarType(model.getIclubBarType() != null ? iclubBarTypeDAO.findById(model.getIclubBarType()) : null);
		bean.setIclubAccessType(model.getIclubAccessType() != null ? iclubAccessTypeDAO.findById(model.getIclubAccessType()) : null);
		bean.setIclubWallType(model.getIclubWallType() != null ? iclubWallTypeDAO.findById(model.getIclubWallType()) : null);
		bean.setIclubPropertyType(model.getIclubPropertyType() != null ? iclubPropertyTypeDAO.findById(model.getIclubPropertyType()) : null);
		bean.setIclubOccupiedStatus(model.getIclubOccupiedStatus() != null ? iclubOccupiedStatusDAO.findById(model.getIclubOccupiedStatus()) : null);
		bean.setIclubPropUsageType(model.getIclubPropUsageType() != null ? iclubPropUsageTypeDAO.findById(model.getIclubPropUsageType()) : null);
		bean.setIclubCoverType(model.getIclubCoverType() != null ? iclubCoverTypeDAO.findById(model.getIclubCoverType()) : null);
		bean.setIclubPerson(model.getIclubPerson() != null ? iclubPersonDAO.findById(model.getIclubPerson()) : null);
		
		return bean;
	}
}
