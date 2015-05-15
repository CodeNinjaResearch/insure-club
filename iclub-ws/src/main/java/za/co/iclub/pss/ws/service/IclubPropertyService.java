package za.co.iclub.pss.ws.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import za.co.iclub.pss.orm.bean.IclubProperty;
import za.co.iclub.pss.orm.dao.IclubAccessTypeDAO;
import za.co.iclub.pss.orm.dao.IclubBarTypeDAO;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubCoverTypeDAO;
import za.co.iclub.pss.orm.dao.IclubNamedQueryDAO;
import za.co.iclub.pss.orm.dao.IclubOccupiedStatusDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.orm.dao.IclubPropUsageTypeDAO;
import za.co.iclub.pss.orm.dao.IclubPropertyDAO;
import za.co.iclub.pss.orm.dao.IclubPropertyTypeDAO;
import za.co.iclub.pss.orm.dao.IclubRoofTypeDAO;
import za.co.iclub.pss.orm.dao.IclubWallTypeDAO;
import za.co.iclub.pss.ws.model.IclubPropertyModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubPropertyService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubPropertyService {
	
	protected static final Logger LOGGER = Logger.getLogger(IclubPropertyService.class);
	private IclubCommonDAO iclubCommonDAO;
	private IclubPropertyDAO iclubPropertyDAO;
	private IclubPersonDAO iclubPersonDAO;
	private IclubCoverTypeDAO iclubCoverTypeDAO;
	private IclubPropUsageTypeDAO iclubPropUsageTypeDAO;
	private IclubOccupiedStatusDAO iclubOccupiedStatusDAO;
	private IclubPropertyTypeDAO iclubPropertyTypeDAO;
	private IclubWallTypeDAO iclubWallTypeDAO;
	private IclubAccessTypeDAO iclubAccessTypeDAO;
	private IclubBarTypeDAO iclubBarTypeDAO;
	private IclubRoofTypeDAO iclubRoofTypeDAO;
	private IclubNamedQueryDAO iclubNamedQueryDAO;
	
	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubPropertyModel model) {
		try {
			IclubProperty iCP = new IclubProperty();
			
			iCP.setPId(model.getPId());
			iCP.setPCrtdDt(model.getPCrtdDt());
			iCP.setPEstValue(model.getPEstValue());
			iCP.setPSecGatesYn(model.getPSecGatesYn());
			iCP.setPNorobberyYn(model.getPNorobberyYn());
			iCP.setPCompYn(model.getPCompYn());
			iCP.setPRentFurYn(model.getPRentFurYn());
			iCP.setPNoclaimYrs(model.getPNoclaimYrs());
			iCP.setPPostalCd(model.getPPostalCd());
			iCP.setPLong(model.getPLong());
			iCP.setPLat(model.getPLat());
			iCP.setPAddress(model.getPAddress());
			iCP.setPRegNum(model.getPRegNum());
			iCP.setPReplacementCost(model.getPReplacementCost());
			iCP.setPContentCost(model.getPContentCost());
			iCP.setIclubRoofType(model.getIclubRoofType() != null ? iclubRoofTypeDAO.findById(model.getIclubRoofType()) : null);
			iCP.setPThatchType(model.getPThatchType());
			iCP.setIclubBarType(model.getIclubBarType() != null ? iclubBarTypeDAO.findById(model.getIclubBarType()) : null);
			iCP.setIclubAccessType(model.getIclubAccessType() != null ? iclubAccessTypeDAO.findById(model.getIclubAccessType()) : null);
			iCP.setIclubWallType(model.getIclubWallType() != null ? iclubWallTypeDAO.findById(model.getIclubWallType()) : null);
			iCP.setIclubPropertyType(model.getIclubPropertyType() != null ? iclubPropertyTypeDAO.findById(model.getIclubPropertyType()) : null);
			iCP.setIclubOccupiedStatus(model.getIclubOccupiedStatus() != null ? iclubOccupiedStatusDAO.findById(model.getIclubOccupiedStatus()) : null);
			iCP.setIclubPropUsageType(model.getIclubPropUsageType() != null ? iclubPropUsageTypeDAO.findById(model.getIclubPropUsageType()) : null);
			iCP.setIclubCoverType(model.getIclubCoverType() != null ? iclubCoverTypeDAO.findById(model.getIclubCoverType()) : null);
			iCP.setIclubPerson(model.getIclubPerson() != null ? iclubPersonDAO.findById(model.getIclubPerson()) : null);
			iclubPropertyDAO.save(iCP);
			
			LOGGER.info("Save Success with ID :: " + iCP.getPId());
			
			ResponseModel message = new ResponseModel();
			message.setStatusCode(0);
			message.setStatusDesc("Success");
			return message;
		} catch (Exception e) {
			LOGGER.error(e, e);
			ResponseModel message = new ResponseModel();
			message.setStatusCode(1);
			message.setStatusDesc(e.getMessage());
			return message;
		}
		
	}
	
	@PUT
	@Path("/mod")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel mod(IclubPropertyModel model) {
		try {
			IclubProperty iCP = new IclubProperty();
			
			iCP.setPId(model.getPId());
			iCP.setPCrtdDt(model.getPCrtdDt());
			iCP.setPEstValue(model.getPEstValue());
			iCP.setPSecGatesYn(model.getPSecGatesYn());
			iCP.setPNorobberyYn(model.getPNorobberyYn());
			iCP.setPCompYn(model.getPCompYn());
			iCP.setPRentFurYn(model.getPRentFurYn());
			iCP.setPNoclaimYrs(model.getPNoclaimYrs());
			iCP.setPPostalCd(model.getPPostalCd());
			iCP.setPLong(model.getPLong());
			iCP.setPLat(model.getPLat());
			iCP.setPAddress(model.getPAddress());
			iCP.setPRegNum(model.getPRegNum());
			iCP.setPReplacementCost(model.getPReplacementCost());
			iCP.setPContentCost(model.getPContentCost());
			iCP.setIclubRoofType(model.getIclubRoofType() != null ? iclubRoofTypeDAO.findById(model.getIclubRoofType()) : null);
			iCP.setPThatchType(model.getPThatchType());
			iCP.setIclubBarType(model.getIclubBarType() != null ? iclubBarTypeDAO.findById(model.getIclubBarType()) : null);
			iCP.setIclubAccessType(model.getIclubAccessType() != null ? iclubAccessTypeDAO.findById(model.getIclubAccessType()) : null);
			iCP.setIclubWallType(model.getIclubWallType() != null ? iclubWallTypeDAO.findById(model.getIclubWallType()) : null);
			iCP.setIclubPropertyType(model.getIclubPropertyType() != null ? iclubPropertyTypeDAO.findById(model.getIclubPropertyType()) : null);
			iCP.setIclubOccupiedStatus(model.getIclubOccupiedStatus() != null ? iclubOccupiedStatusDAO.findById(model.getIclubOccupiedStatus()) : null);
			iCP.setIclubPropUsageType(model.getIclubPropUsageType() != null ? iclubPropUsageTypeDAO.findById(model.getIclubPropUsageType()) : null);
			iCP.setIclubCoverType(model.getIclubCoverType() != null ? iclubCoverTypeDAO.findById(model.getIclubCoverType()) : null);
			iCP.setIclubPerson(model.getIclubPerson() != null ? iclubPersonDAO.findById(model.getIclubPerson()) : null);
			
			iclubPropertyDAO.merge(iCP);
			
			LOGGER.info("Merge Success with ID :: " + model.getPId());
			
			ResponseModel message = new ResponseModel();
			message.setStatusCode(0);
			message.setStatusDesc("Success");
			return message;
		} catch (Exception e) {
			LOGGER.error(e, e);
			ResponseModel message = new ResponseModel();
			message.setStatusCode(1);
			message.setStatusDesc(e.getMessage());
			return message;
		}
		
	}
	
	@GET
	@Path("/del/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public Response del(@PathParam("id") String id) {
		try {
			iclubPropertyDAO.delete(iclubPropertyDAO.findById(id));
			return Response.ok().build();
		} catch (Exception e) {
			LOGGER.error(e, e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GET
	@Path("/list")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public <T extends IclubPropertyModel> List<T> list() {
		List<T> ret = new ArrayList<T>();
		
		try {
			List batmod = iclubPropertyDAO.findAll();
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubProperty iCP = (IclubProperty) object;
					
					IclubPropertyModel model = new IclubPropertyModel();
					
					model.setPId(iCP.getPId());
					model.setPCrtdDt(iCP.getPCrtdDt());
					model.setPEstValue(iCP.getPEstValue());
					model.setPSecGatesYn(iCP.getPSecGatesYn());
					model.setPNorobberyYn(iCP.getPNorobberyYn());
					model.setPCompYn(iCP.getPCompYn());
					model.setPRentFurYn(iCP.getPRentFurYn());
					model.setPNoclaimYrs(iCP.getPNoclaimYrs());
					model.setPPostalCd(iCP.getPPostalCd());
					model.setPLong(iCP.getPLong());
					model.setPLat(iCP.getPLat());
					model.setPAddress(iCP.getPAddress());
					model.setPRegNum(iCP.getPRegNum());
					model.setPReplacementCost(iCP.getPReplacementCost());
					model.setPContentCost(iCP.getPContentCost());
					model.setIclubCoverType(iCP.getIclubCoverType() != null ? (iCP.getIclubCoverType().getCtId()) : null);
					model.setIclubPropUsageType(iCP.getIclubPropUsageType() != null ? (iCP.getIclubPropUsageType().getPutId()) : null);
					model.setIclubOccupiedStatus(iCP.getIclubOccupiedStatus() != null ? (iCP.getIclubOccupiedStatus().getOsId()) : null);
					model.setIclubPropertyType(iCP.getIclubPropertyType() != null ? (iCP.getIclubPropertyType().getPtId()) : null);
					model.setIclubWallType(iCP.getIclubWallType() != null ? (iCP.getIclubWallType().getWtId()) : null);
					model.setIclubAccessType(iCP.getIclubAccessType() != null ? (iCP.getIclubAccessType().getAtId()) : null);
					model.setIclubPerson(iCP.getIclubPerson() != null ? (iCP.getIclubPerson().getPId()) : null);
					model.setIclubBarType(iCP.getIclubBarType() != null ? (iCP.getIclubBarType().getBtId()) : null);
					model.setPThatchType(iCP.getPThatchType());
					model.setIclubRoofType(iCP.getIclubRoofType() != null ? (iCP.getIclubRoofType().getRtId()) : null);
					
					ret.add((T) model);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		
		return ret;
	}
	
	@GET
	@Path("/get/user/{user}")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public <T extends IclubPropertyModel> List<T> getByUser(@PathParam("user") String user) {
		List<T> ret = new ArrayList<T>();
		
		try {
			List batmod = iclubNamedQueryDAO.findByUser(user, IclubProperty.class.getSimpleName());
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubProperty iCP = (IclubProperty) object;
					
					IclubPropertyModel model = new IclubPropertyModel();
					
					model.setPId(iCP.getPId());
					model.setPCrtdDt(iCP.getPCrtdDt());
					model.setPEstValue(iCP.getPEstValue());
					model.setPSecGatesYn(iCP.getPSecGatesYn());
					model.setPNorobberyYn(iCP.getPNorobberyYn());
					model.setPCompYn(iCP.getPCompYn());
					model.setPRentFurYn(iCP.getPRentFurYn());
					model.setPNoclaimYrs(iCP.getPNoclaimYrs());
					model.setPPostalCd(iCP.getPPostalCd());
					model.setPLong(iCP.getPLong());
					model.setPLat(iCP.getPLat());
					model.setPAddress(iCP.getPAddress());
					model.setPRegNum(iCP.getPRegNum());
					model.setPReplacementCost(iCP.getPReplacementCost());
					model.setPContentCost(iCP.getPContentCost());
					model.setIclubCoverType(iCP.getIclubCoverType() != null ? (iCP.getIclubCoverType().getCtId()) : null);
					model.setIclubPropUsageType(iCP.getIclubPropUsageType() != null ? (iCP.getIclubPropUsageType().getPutId()) : null);
					model.setIclubOccupiedStatus(iCP.getIclubOccupiedStatus() != null ? (iCP.getIclubOccupiedStatus().getOsId()) : null);
					model.setIclubPropertyType(iCP.getIclubPropertyType() != null ? (iCP.getIclubPropertyType().getPtId()) : null);
					model.setIclubWallType(iCP.getIclubWallType() != null ? (iCP.getIclubWallType().getWtId()) : null);
					model.setIclubAccessType(iCP.getIclubAccessType() != null ? (iCP.getIclubAccessType().getAtId()) : null);
					model.setIclubPerson(iCP.getIclubPerson() != null ? (iCP.getIclubPerson().getPId()) : null);
					model.setIclubBarType(iCP.getIclubBarType() != null ? (iCP.getIclubBarType().getBtId()) : null);
					model.setPThatchType(iCP.getPThatchType());
					model.setIclubRoofType(iCP.getIclubRoofType() != null ? (iCP.getIclubRoofType().getRtId()) : null);
					
					ret.add((T) model);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		
		return ret;
	}
	
	@GET
	@Path("/get/{id}")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public IclubPropertyModel getById(@PathParam("id") String id) {
		IclubPropertyModel model = new IclubPropertyModel();
		try {
			IclubProperty bean = iclubPropertyDAO.findById(id);
			if (bean != null) {
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
				model.setIclubPropUsageType(bean.getIclubPropUsageType() != null ? (bean.getIclubPropUsageType().getPutId()) : null);
				model.setIclubOccupiedStatus(bean.getIclubOccupiedStatus() != null ? (bean.getIclubOccupiedStatus().getOsId()) : null);
				model.setIclubPropertyType(bean.getIclubPropertyType() != null ? (bean.getIclubPropertyType().getPtId()) : null);
				model.setIclubWallType(bean.getIclubWallType() != null ? (bean.getIclubWallType().getWtId()) : null);
				model.setIclubAccessType(bean.getIclubAccessType() != null ? (bean.getIclubAccessType().getAtId()) : null);
				model.setIclubPerson(bean.getIclubPerson() != null ? (bean.getIclubPerson().getPId()) : null);
				model.setIclubBarType(bean.getIclubBarType() != null ? (bean.getIclubBarType().getBtId()) : null);
				model.setPThatchType(bean.getPThatchType());
				model.setIclubRoofType(bean.getIclubRoofType() != null ? (bean.getIclubRoofType().getRtId()) : null);
			}
			
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return model;
	}
	
	public IclubPropertyDAO getIclubPropertyDAO() {
		return iclubPropertyDAO;
	}
	
	public void setIclubPropertyDAO(IclubPropertyDAO iclubPropertyDAO) {
		this.iclubPropertyDAO = iclubPropertyDAO;
	}
	
	public IclubCommonDAO getIclubCommonDAO() {
		return iclubCommonDAO;
	}
	
	public void setIclubCommonDAO(IclubCommonDAO iclubCommonDAO) {
		this.iclubCommonDAO = iclubCommonDAO;
	}
	
	public IclubPersonDAO getIclubPersonDAO() {
		return iclubPersonDAO;
	}
	
	public void setIclubPersonDAO(IclubPersonDAO iclubPersonDAO) {
		this.iclubPersonDAO = iclubPersonDAO;
	}
	
	public IclubCoverTypeDAO getIclubCoverTypeDAO() {
		return iclubCoverTypeDAO;
	}
	
	public void setIclubCoverTypeDAO(IclubCoverTypeDAO iclubCoverTypeDAO) {
		this.iclubCoverTypeDAO = iclubCoverTypeDAO;
	}
	
	public IclubPropUsageTypeDAO getIclubPropUsageTypeDAO() {
		return iclubPropUsageTypeDAO;
	}
	
	public void setIclubPropUsageTypeDAO(IclubPropUsageTypeDAO iclubPropUsageTypeDAO) {
		this.iclubPropUsageTypeDAO = iclubPropUsageTypeDAO;
	}
	
	public IclubOccupiedStatusDAO getIclubOccupiedStatusDAO() {
		return iclubOccupiedStatusDAO;
	}
	
	public void setIclubOccupiedStatusDAO(IclubOccupiedStatusDAO iclubOccupiedStatusDAO) {
		this.iclubOccupiedStatusDAO = iclubOccupiedStatusDAO;
	}
	
	public IclubPropertyTypeDAO getIclubPropertyTypeDAO() {
		return iclubPropertyTypeDAO;
	}
	
	public void setIclubPropertyTypeDAO(IclubPropertyTypeDAO iclubPropertyTypeDAO) {
		this.iclubPropertyTypeDAO = iclubPropertyTypeDAO;
	}
	
	public IclubWallTypeDAO getIclubWallTypeDAO() {
		return iclubWallTypeDAO;
	}
	
	public void setIclubWallTypeDAO(IclubWallTypeDAO iclubWallTypeDAO) {
		this.iclubWallTypeDAO = iclubWallTypeDAO;
	}
	
	public IclubAccessTypeDAO getIclubAccessTypeDAO() {
		return iclubAccessTypeDAO;
	}
	
	public void setIclubAccessTypeDAO(IclubAccessTypeDAO iclubAccessTypeDAO) {
		this.iclubAccessTypeDAO = iclubAccessTypeDAO;
	}
	
	public IclubBarTypeDAO getIclubBarTypeDAO() {
		return iclubBarTypeDAO;
	}
	
	public void setIclubBarTypeDAO(IclubBarTypeDAO iclubBarTypeDAO) {
		this.iclubBarTypeDAO = iclubBarTypeDAO;
	}
	
	public IclubRoofTypeDAO getIclubRoofTypeDAO() {
		return iclubRoofTypeDAO;
	}
	
	public void setIclubRoofTypeDAO(IclubRoofTypeDAO iclubRoofTypeDAO) {
		this.iclubRoofTypeDAO = iclubRoofTypeDAO;
	}
	
	public IclubNamedQueryDAO getIclubNamedQueryDAO() {
		return iclubNamedQueryDAO;
	}
	
	public void setIclubNamedQueryDAO(IclubNamedQueryDAO iclubNamedQueryDAO) {
		this.iclubNamedQueryDAO = iclubNamedQueryDAO;
	}
	
}
