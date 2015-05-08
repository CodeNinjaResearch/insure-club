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
import org.springframework.transaction.annotation.Transactional;

import za.co.iclub.pss.orm.bean.IclubAccessType;
import za.co.iclub.pss.orm.bean.IclubDriver;
import za.co.iclub.pss.orm.bean.IclubProperty;
import za.co.iclub.pss.orm.bean.IclubVehicle;
import za.co.iclub.pss.orm.dao.IclubAccessTypeDAO;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubNamedQueryDAO;
import za.co.iclub.pss.ws.model.IclubAccessTypeModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubAccessTypeService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubAccessTypeService {
	
	private static final Logger LOGGER = Logger.getLogger(IclubAccessTypeService.class);
	private IclubAccessTypeDAO iclubAccessTypeDAO;
	private IclubCommonDAO iclubCommonDAO;
	private IclubNamedQueryDAO iclubNamedQueryDAO;
	
	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional
	public ResponseModel add(IclubAccessTypeModel model) {
		try {
			
			IclubAccessType acctype = new IclubAccessType();
			
			acctype.setAtId(iclubCommonDAO.getNextId(IclubAccessType.class));
			acctype.setAtLongDesc(model.getAtLongDesc());
			acctype.setAtShortDesc(model.getAtShortDesc());
			acctype.setAtStatus(model.getAtStatus());
			
			iclubAccessTypeDAO.save(acctype);
			
			LOGGER.info("Save Success with ID :: " + acctype.getAtId().longValue());
			
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
	@Transactional
	public ResponseModel mod(IclubAccessTypeModel model) {
		try {
			IclubAccessType acctype = new IclubAccessType();
			
			acctype.setAtId(model.getAtId());
			acctype.setAtLongDesc(model.getAtLongDesc());
			acctype.setAtShortDesc(model.getAtShortDesc());
			acctype.setAtStatus(model.getAtStatus());
			
			iclubAccessTypeDAO.merge(acctype);
			
			LOGGER.info("Save Success with ID :: " + model.getAtId().longValue());
			
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
	@Transactional
	public Response del(@PathParam("id") Long id) {
		try {
			iclubAccessTypeDAO.delete(iclubAccessTypeDAO.findById(id));
			return Response.ok().build();
		} catch (Exception e) {
			LOGGER.error(e, e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GET
	@Path("/validate/sd/{val}/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional
	public ResponseModel validateSd(@PathParam("val") String val, @PathParam("id") Long id) {
		try {
			List data = iclubNamedQueryDAO.getBySD(val, id, IclubAccessType.class.getSimpleName());
			ResponseModel message = new ResponseModel();
			if (data != null && data.size() > 0) {
				message.setStatusCode(1);
				message.setStatusDesc("Duplicate Value");
			} else {
				message.setStatusCode(0);
				message.setStatusDesc("Success");
			}
			return message;
		} catch (Exception e) {
			LOGGER.error(e, e);
			ResponseModel message = new ResponseModel();
			message.setStatusCode(2);
			message.setStatusDesc(e.getMessage());
			return message;
		}
		
	}
	
	@GET
	@Path("/list")
	@Produces("application/json")
	@Transactional
	public <T extends IclubAccessTypeModel> List<T> list() {
		List<T> ret = new ArrayList<T>();
		
		try {
			List batmod = iclubAccessTypeDAO.findAll();
			
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubAccessType iclubAtype = (IclubAccessType) object;
					IclubAccessTypeModel iCB = new IclubAccessTypeModel();
					
					iCB.setAtId(iclubAtype.getAtId().longValue());
					iCB.setAtLongDesc(iclubAtype.getAtLongDesc());
					iCB.setAtShortDesc(iclubAtype.getAtShortDesc());
					iCB.setAtStatus(iclubAtype.getAtStatus());
					
					if (iclubAtype.getIclubVehiclesForVOnAccessTypeId() != null && iclubAtype.getIclubVehiclesForVOnAccessTypeId().size() > 0) {
						String[] vehiclesForVOnAccessTypeIds = new String[iclubAtype.getIclubVehiclesForVOnAccessTypeId().size()];
						int i = 0;
						for (IclubVehicle vehicle : iclubAtype.getIclubVehiclesForVOnAccessTypeId()) {
							vehiclesForVOnAccessTypeIds[i] = vehicle.getVId();
							i++;
						}
						iCB.setIclubVehiclesForVOnAccessTypeId(vehiclesForVOnAccessTypeIds);
					}
					
					if (iclubAtype.getIclubVehiclesForVDdAccessTypeId() != null && iclubAtype.getIclubVehiclesForVDdAccessTypeId().size() > 0) {
						String[] vehiclesForVDdAccessTypeIds = new String[iclubAtype.getIclubVehiclesForVDdAccessTypeId().size()];
						int i = 0;
						for (IclubVehicle vehicle : iclubAtype.getIclubVehiclesForVDdAccessTypeId()) {
							vehiclesForVDdAccessTypeIds[i] = vehicle.getVId();
							i++;
						}
						iCB.setIclubVehiclesForVDdAccessTypeId(vehiclesForVDdAccessTypeIds);
					}
					
					if (iclubAtype.getIclubDriversForDAccessStatusId() != null && iclubAtype.getIclubDriversForDAccessStatusId().size() > 0) {
						String[] drivers = new String[iclubAtype.getIclubDriversForDAccessStatusId().size()];
						int i = 0;
						for (IclubDriver driver : iclubAtype.getIclubDriversForDAccessStatusId()) {
							drivers[i] = driver.getDId();
							i++;
						}
						iCB.setIclubDriversForDAccessStatusId(drivers);
					}
					if (iclubAtype.getIclubDriversForDAccessTypeId() != null && iclubAtype.getIclubDriversForDAccessTypeId().size() > 0) {
						String[] drivers = new String[iclubAtype.getIclubDriversForDAccessTypeId().size()];
						int i = 0;
						for (IclubDriver driver : iclubAtype.getIclubDriversForDAccessTypeId()) {
							drivers[i] = driver.getDId();
							i++;
						}
						iCB.setIclubDriversForDAccessTypeId(drivers);
					}
					
					if (iclubAtype.getIclubProperties() != null && iclubAtype.getIclubProperties().size() > 0) {
						String[] properties = new String[iclubAtype.getIclubProperties().size()];
						int i = 0;
						for (IclubProperty property : iclubAtype.getIclubProperties()) {
							properties[i] = property.getPId();
							i++;
						}
						iCB.setIclubProperties(properties);
					}
					
					ret.add((T) iCB);
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
	@Transactional
	public IclubAccessTypeModel getById(@PathParam("id") Long id) {
		IclubAccessTypeModel model = new IclubAccessTypeModel();
		try {
			IclubAccessType bean = iclubAccessTypeDAO.findById(id);
			
			model.setAtId(bean.getAtId().longValue());
			model.setAtLongDesc(bean.getAtLongDesc());
			model.setAtShortDesc(bean.getAtShortDesc());
			model.setAtStatus(bean.getAtStatus());
			
			if (bean.getIclubVehiclesForVOnAccessTypeId() != null && bean.getIclubVehiclesForVOnAccessTypeId().size() > 0) {
				String[] vehiclesForVOnAccessTypeIds = new String[bean.getIclubVehiclesForVOnAccessTypeId().size()];
				int i = 0;
				for (IclubVehicle vehicle : bean.getIclubVehiclesForVOnAccessTypeId()) {
					vehiclesForVOnAccessTypeIds[i] = vehicle.getVId();
					i++;
				}
				model.setIclubVehiclesForVOnAccessTypeId(vehiclesForVOnAccessTypeIds);
			}
			
			if (bean.getIclubVehiclesForVDdAccessTypeId() != null && bean.getIclubVehiclesForVDdAccessTypeId().size() > 0) {
				String[] vehiclesForVDdAccessTypeIds = new String[bean.getIclubVehiclesForVDdAccessTypeId().size()];
				int i = 0;
				for (IclubVehicle vehicle : bean.getIclubVehiclesForVDdAccessTypeId()) {
					vehiclesForVDdAccessTypeIds[i] = vehicle.getVId();
					i++;
				}
				model.setIclubVehiclesForVDdAccessTypeId(vehiclesForVDdAccessTypeIds);
			}
			
			if (bean.getIclubDriversForDAccessStatusId() != null && bean.getIclubDriversForDAccessStatusId().size() > 0) {
				String[] drivers = new String[bean.getIclubDriversForDAccessStatusId().size()];
				int i = 0;
				for (IclubDriver driver : bean.getIclubDriversForDAccessStatusId()) {
					drivers[i] = driver.getDId();
					i++;
				}
				model.setIclubDriversForDAccessStatusId(drivers);
			}
			if (bean.getIclubDriversForDAccessTypeId() != null && bean.getIclubDriversForDAccessTypeId().size() > 0) {
				String[] drivers = new String[bean.getIclubDriversForDAccessTypeId().size()];
				int i = 0;
				for (IclubDriver driver : bean.getIclubDriversForDAccessTypeId()) {
					drivers[i] = driver.getDId();
					i++;
				}
				model.setIclubDriversForDAccessTypeId(drivers);
			}
			
			if (bean.getIclubProperties() != null && bean.getIclubProperties().size() > 0) {
				String[] properties = new String[bean.getIclubProperties().size()];
				int i = 0;
				for (IclubProperty property : bean.getIclubProperties()) {
					properties[i] = property.getPId();
					i++;
				}
				model.setIclubProperties(properties);
			}
			
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return model;
	}
	
	public IclubAccessTypeDAO getIclubAccessTypeDAO() {
		return iclubAccessTypeDAO;
	}
	
	public void setIclubAccessTypeDAO(IclubAccessTypeDAO iclubAccessTypeDAO) {
		this.iclubAccessTypeDAO = iclubAccessTypeDAO;
	}
	
	public IclubCommonDAO getIclubCommonDAO() {
		return iclubCommonDAO;
	}
	
	public void setIclubCommonDAO(IclubCommonDAO iclubCommonDAO) {
		this.iclubCommonDAO = iclubCommonDAO;
	}
	
	public IclubNamedQueryDAO getIclubNamedQueryDAO() {
		return iclubNamedQueryDAO;
	}
	
	public void setIclubNamedQueryDAO(IclubNamedQueryDAO iclubNamedQueryDAO) {
		this.iclubNamedQueryDAO = iclubNamedQueryDAO;
	}
	
}
