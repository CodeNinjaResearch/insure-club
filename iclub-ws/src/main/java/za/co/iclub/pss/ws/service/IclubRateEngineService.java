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

import za.co.iclub.pss.orm.bean.IclubRateEngine;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubInsuranceItemTypeDAO;
import za.co.iclub.pss.orm.dao.IclubNamedQueryDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.orm.dao.IclubRateEngineDAO;
import za.co.iclub.pss.orm.dao.IclubRateTypeDAO;
import za.co.iclub.pss.ws.model.IclubRateEngineModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubRateEngineService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubRateEngineService {
	
	protected static final Logger LOGGER = Logger.getLogger(IclubRateEngineService.class);
	private IclubCommonDAO iclubCommonDAO;
	private IclubRateEngineDAO iclubRateEngineDAO;
	private IclubPersonDAO iclubPersonDAO;
	private IclubInsuranceItemTypeDAO iclubInsuranceItemTypeDAO;
	private IclubRateTypeDAO iclubRateTypeDAO;
	private IclubNamedQueryDAO iclubNamedQueryDAO;
	
	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubRateEngineModel model) {
		try {
			IclubRateEngine iCt = new IclubRateEngine();
			
			iCt.setReId(model.getReId());
			iCt.setReRate(model.getReRate());
			iCt.setReCrtdDt(model.getReCrtdDt());
			iCt.setReStatus(model.getReStatus());
			iCt.setReMaxValue(model.getReMaxValue());
			iCt.setReBaseValue(model.getReBaseValue());
			iCt.setIclubRateType(model.getIclubRateType() != null ? iclubRateTypeDAO.findById(model.getIclubRateType()) : null);
			iCt.setIclubPerson(model.getIclubPerson() != null && !model.getIclubPerson().trim().equalsIgnoreCase("") ? iclubPersonDAO.findById(model.getIclubPerson()) : null);
			
			iclubRateEngineDAO.save(iCt);
			
			LOGGER.info("Save Success with ID :: " + iCt.getReId());
			
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
	public ResponseModel mod(IclubRateEngineModel model) {
		try {
			IclubRateEngine iCt = new IclubRateEngine();
			
			iCt.setReId(model.getReId());
			iCt.setReRate(model.getReRate());
			iCt.setReCrtdDt(model.getReCrtdDt());
			iCt.setReStatus(model.getReStatus());
			iCt.setReMaxValue(model.getReMaxValue());
			iCt.setReBaseValue(model.getReBaseValue());
			iCt.setIclubRateType(model.getIclubRateType() != null ? iclubRateTypeDAO.findById(model.getIclubRateType()) : null);
			iCt.setIclubPerson(model.getIclubPerson() != null && !model.getIclubPerson().trim().equalsIgnoreCase("") ? iclubPersonDAO.findById(model.getIclubPerson()) : null);
			
			iclubRateEngineDAO.merge(iCt);
			
			LOGGER.info("Merge Success with ID :: " + model.getReId());
			
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
			iclubRateEngineDAO.delete(iclubRateEngineDAO.findById(id));
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
	public <T extends IclubRateEngineModel> List<T> list() {
		List<T> ret = new ArrayList<T>();
		
		try {
			List batmod = iclubRateEngineDAO.findAll();
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubRateEngine iCt = (IclubRateEngine) object;
					
					IclubRateEngineModel model = new IclubRateEngineModel();
					
					model.setReId(iCt.getReId());
					model.setReRate(iCt.getReRate());
					model.setReCrtdDt(iCt.getReCrtdDt());
					model.setReStatus(iCt.getReStatus());
					model.setReMaxValue(iCt.getReMaxValue());
					model.setReBaseValue(iCt.getReBaseValue());
					model.setIclubRateType(iCt.getIclubRateType() != null ? (iCt.getIclubRateType().getRtId()) : null);
					model.setIclubPerson(iCt.getIclubPerson() != null ? (iCt.getIclubPerson().getPId()) : null);
					
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
	public <T extends IclubRateEngineModel> List<T> getByUser(@PathParam("user") String user) {
		List<T> ret = new ArrayList<T>();
		
		try {
			List batmod = iclubNamedQueryDAO.findByUser(user, IclubRateEngine.class.getSimpleName());
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubRateEngine iCt = (IclubRateEngine) object;
					
					IclubRateEngineModel model = new IclubRateEngineModel();
					
					model.setReId(iCt.getReId());
					model.setReRate(iCt.getReRate());
					model.setReCrtdDt(iCt.getReCrtdDt());
					model.setReStatus(iCt.getReStatus());
					model.setReMaxValue(iCt.getReMaxValue());
					model.setReBaseValue(iCt.getReBaseValue());
					model.setIclubRateType(iCt.getIclubRateType() != null ? (iCt.getIclubRateType().getRtId()) : null);
					model.setIclubPerson(iCt.getIclubPerson() != null ? (iCt.getIclubPerson().getPId()) : null);
					
					ret.add((T) model);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		
		return ret;
	}
	
	@GET
	@Path("/get/rateType/{rateType}")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public <T extends IclubRateEngineModel> List<T> getByRateType(@PathParam("rateType") String rateType) {
		List<T> ret = new ArrayList<T>();
		
		try {
			List batmod = iclubNamedQueryDAO.findByRateType(rateType);
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubRateEngine iCt = (IclubRateEngine) object;
					
					IclubRateEngineModel model = new IclubRateEngineModel();
					
					model.setReId(iCt.getReId());
					model.setReRate(iCt.getReRate());
					model.setReCrtdDt(iCt.getReCrtdDt());
					model.setReStatus(iCt.getReStatus());
					model.setReMaxValue(iCt.getReMaxValue());
					model.setReBaseValue(iCt.getReBaseValue());
					model.setIclubRateType(iCt.getIclubRateType() != null ? (iCt.getIclubRateType().getRtId()) : null);
					model.setIclubPerson(iCt.getIclubPerson() != null ? (iCt.getIclubPerson().getPId()) : null);
					
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
	public IclubRateEngineModel getById(@PathParam("id") String id) {
		IclubRateEngineModel model = new IclubRateEngineModel();
		try {
			IclubRateEngine bean = iclubRateEngineDAO.findById(id);
			
			model.setReId(bean.getReId());
			model.setReRate(bean.getReRate());
			model.setReCrtdDt(bean.getReCrtdDt());
			model.setReStatus(bean.getReStatus());
			model.setReMaxValue(bean.getReMaxValue());
			model.setReBaseValue(bean.getReBaseValue());
			model.setIclubRateType(bean.getIclubRateType() != null ? (bean.getIclubRateType().getRtId()) : null);
			model.setIclubPerson(bean.getIclubPerson() != null ? (bean.getIclubPerson().getPId()) : null);
			
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return model;
	}
	
	@GET
	@Path("/list/lookupdetails/{tableName}")
	@Produces("application/json")
	@Transactional
	public <T extends String> List<T> listAllMake(@PathParam("tableName") String tableName) {
		List<T> ret = new ArrayList<T>();
		try {
			List batmod = iclubCommonDAO.findAllLookValuesByTabelName(tableName);
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					String reDetails = (String) object;
					ret.add((T) reDetails);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return ret;
	}
	
	@GET
	@Path("/get/lookupdetails/{tableName}/{fieldId}")
	@Produces("application/json")
	@Transactional
	public String getlookupDetails(@PathParam("tableName") String tableName, @PathParam("fieldId") String fieldId) {
		String lookupDetails = "";
		try {
			lookupDetails = iclubCommonDAO.findAllLookValuesByTabelName(tableName, fieldId);
			
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return lookupDetails;
	}
	
	@GET
	@Path("/get/fieldValue/{fieldName}/{tableName}/{fieldId}")
	@Produces("application/json")
	@Transactional
	public String getFieldValueByFieldIdAndTableName(@PathParam("fieldName") String fieldName, @PathParam("tableName") String tableName, @PathParam("fieldId") String fieldId) {
		String batmod = null;
		try {
			Object batObj = iclubCommonDAO.getFieldValueByFieldNameAndId(fieldName, tableName, fieldId);
			
			if (batObj != null) {
				batmod = batObj.toString();
			}
			
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return batmod;
	}
	
	@GET
	@Path("/get/fieldValues/{fieldName}/{tableName}/{fieldId}")
	@Produces("application/json")
	@Transactional
	public String getFieldValuesByFieldIdAndTableName(@PathParam("fieldName") String fieldName, @PathParam("tableName") String tableName, @PathParam("fieldId") String fieldId) {
		String batmod = null;
		try {
			Object[] batObj = iclubCommonDAO.getFieldValuesByFieldNameAndId(fieldName, tableName, fieldId);
			
			if (batObj != null) {
				batmod = batObj[0].toString() + "@" + batObj[1].toString();
			}
			
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return batmod;
	}
	
	@GET
	@Path("/validate/baseValue/{baseValue}/{rateTypeId}/{reId}")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel validateFixedAndLookUp(@PathParam("baseValue") String baseValue, @PathParam("rateTypeId") Long rateTypeId, @PathParam("reId") String reId) {
		try {
			List data = iclubNamedQueryDAO.getIclubRateEngineByBaseValueAndRateTypeId(baseValue, rateTypeId, reId);
			ResponseModel message = new ResponseModel();
			if ((data != null) && (data.size() > 0)) {
				message.setStatusCode(Integer.valueOf(1));
				message.setStatusDesc("Duplicate Base Value");
			} else {
				message.setStatusCode(Integer.valueOf(0));
				message.setStatusDesc("Success");
			}
			return message;
		} catch (Exception e) {
			LOGGER.error(e, e);
			ResponseModel message = new ResponseModel();
			message.setStatusCode(Integer.valueOf(2));
			message.setStatusDesc(e.getMessage());
			return message;
		}
	}
	
	@GET
	@Path("/validate/rangeValue/{baseValue}/{maxValue}/{rateTypeId}/{reId}")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel validateRange(@PathParam("baseValue") String baseValue, @PathParam("maxValue") String maxValue, @PathParam("rateTypeId") Long rateTypeId, @PathParam("reId") String reId) {
		try {
			List data = iclubNamedQueryDAO.getIclubRateEngineByBaseMaxValueAndRateTypeId(baseValue, maxValue, rateTypeId, reId);
			ResponseModel message = new ResponseModel();
			if ((data != null) && (data.size() > 0)) {
				message.setStatusCode(Integer.valueOf(1));
				message.setStatusDesc("Duplicate Base Value");
			} else {
				message.setStatusCode(Integer.valueOf(0));
				message.setStatusDesc("Success");
			}
			return message;
		} catch (Exception e) {
			LOGGER.error(e, e);
			ResponseModel message = new ResponseModel();
			message.setStatusCode(Integer.valueOf(2));
			message.setStatusDesc(e.getMessage());
			return message;
		}
	}
	
	public IclubRateEngineDAO getIclubRateEngineDAO() {
		return iclubRateEngineDAO;
	}
	
	public void setIclubRateEngineDAO(IclubRateEngineDAO iclubRateEngineDAO) {
		this.iclubRateEngineDAO = iclubRateEngineDAO;
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
	
	public IclubInsuranceItemTypeDAO getIclubInsuranceItemTypeDAO() {
		return iclubInsuranceItemTypeDAO;
	}
	
	public void setIclubInsuranceItemTypeDAO(IclubInsuranceItemTypeDAO iclubInsuranceItemTypeDAO) {
		this.iclubInsuranceItemTypeDAO = iclubInsuranceItemTypeDAO;
	}
	
	public IclubRateTypeDAO getIclubRateTypeDAO() {
		return iclubRateTypeDAO;
	}
	
	public void setIclubRateTypeDAO(IclubRateTypeDAO iclubRateTypeDAO) {
		this.iclubRateTypeDAO = iclubRateTypeDAO;
	}
	
	public IclubNamedQueryDAO getIclubNamedQueryDAO() {
		return iclubNamedQueryDAO;
	}
	
	public void setIclubNamedQueryDAO(IclubNamedQueryDAO iclubNamedQueryDAO) {
		this.iclubNamedQueryDAO = iclubNamedQueryDAO;
	}
	
}
