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

import za.co.iclub.pss.orm.bean.IclubVehicle;
import za.co.iclub.pss.orm.bean.IclubVehicleMaster;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubNamedQueryDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.orm.dao.IclubVehicleMasterDAO;
import za.co.iclub.pss.ws.model.IclubVehicleMasterModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubVehicleMasterService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubVehicleMasterService {
	
	private static final Logger LOGGER = Logger.getLogger(IclubVehicleMasterService.class);
	private IclubVehicleMasterDAO iclubVehicleMasterDAO;
	private IclubCommonDAO iclubCommonDAO;
	private IclubPersonDAO iclubPersonDAO;
	private IclubNamedQueryDAO iclubNamedQueryDAO;
	
	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional
	public ResponseModel add(IclubVehicleMasterModel model) {
		try {
			
			IclubVehicleMaster iCVm = new IclubVehicleMaster();
			
			iCVm.setVmId(iclubCommonDAO.getNextId(IclubVehicleMaster.class));
			iCVm.setVmMake(model.getVmMake());
			iCVm.setVmModel(model.getVmModel());
			iCVm.setVmMrktRate(model.getVmMrktRate());
			iCVm.setVmOrigRate(model.getVmOrigRate());
			iCVm.setVmRetRate(model.getVmRetRate());
			iCVm.setVmProdDt(model.getVmProdDt());
			iCVm.setVmCrtdDt(model.getVmCrtdDt());
			iCVm.setVmRatePrct(model.getVmRatePrct());
			iCVm.setIclubPerson(iclubPersonDAO.findById(model.getIclubPerson()));
			
			iclubVehicleMasterDAO.save(iCVm);
			
			LOGGER.info("Save Success with ID :: " + iCVm.getVmId().longValue());
			
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
	public ResponseModel mod(IclubVehicleMasterModel model) {
		try {
			IclubVehicleMaster iCVm = new IclubVehicleMaster();
			
			iCVm.setVmId(model.getVmId());
			iCVm.setVmMake(model.getVmMake());
			iCVm.setVmModel(model.getVmModel());
			iCVm.setVmMrktRate(model.getVmMrktRate());
			iCVm.setVmOrigRate(model.getVmOrigRate());
			iCVm.setVmRetRate(model.getVmRetRate());
			iCVm.setVmProdDt(model.getVmProdDt());
			iCVm.setVmCrtdDt(model.getVmCrtdDt());
			iCVm.setVmRatePrct(model.getVmRatePrct());
			iCVm.setIclubPerson(iclubPersonDAO.findById(model.getIclubPerson()));
			
			iclubVehicleMasterDAO.merge(iCVm);
			
			LOGGER.info("Save Success with ID :: " + model.getVmId().longValue());
			
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
			iclubVehicleMasterDAO.delete(iclubVehicleMasterDAO.findById(id));
			return Response.ok().build();
		} catch (Exception e) {
			LOGGER.error(e, e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GET
	@Path("/list")
	@Produces("application/json")
	@Transactional
	public <T extends IclubVehicleMasterModel> List<T> list() {
		List<T> ret = new ArrayList<T>();
		
		try {
			List batmod = iclubVehicleMasterDAO.findAll();
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubVehicleMaster iclubVMaster = (IclubVehicleMaster) object;
					IclubVehicleMasterModel iCVm = new IclubVehicleMasterModel();
					
					iCVm.setVmId(iclubVMaster.getVmId());
					iCVm.setVmMake(iclubVMaster.getVmMake());
					iCVm.setVmModel(iclubVMaster.getVmModel());
					iCVm.setVmMrktRate(iclubVMaster.getVmMrktRate());
					iCVm.setVmOrigRate(iclubVMaster.getVmOrigRate());
					iCVm.setVmRetRate(iclubVMaster.getVmRetRate());
					iCVm.setVmProdDt(iclubVMaster.getVmProdDt());
					iCVm.setVmCrtdDt(iclubVMaster.getVmCrtdDt());
					iCVm.setVmRatePrct(iclubVMaster.getVmRatePrct());
					iCVm.setIclubPerson(iclubVMaster.getIclubPerson() != null ? iclubVMaster.getIclubPerson().getPId() : null);
					
					if (iclubVMaster.getIclubVehicles() != null && iclubVMaster.getIclubVehicles().size() > 0) {
						String[] vehicles = new String[iclubVMaster.getIclubVehicles().size()];
						int i = 0;
						for (IclubVehicle vehicle : iclubVMaster.getIclubVehicles()) {
							vehicles[i] = vehicle.getVId();
							i++;
						}
					}
					
					ret.add((T) iCVm);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		
		return ret;
	}
	
	@GET
	@Path("/listAllMake")
	@Produces("application/json")
	@Transactional
	public <T extends String> List<T> listAllMake() {
		List<T> ret = new ArrayList<T>();
		try {
			List batmod = iclubNamedQueryDAO.findAllVmMakes();
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					String vmMake = (String) object;
					ret.add((T) vmMake);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return ret;
	}
	
	@GET
	@Path("/getByMake/{vmMake}")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public <T extends IclubVehicleMasterModel> List<T> getByMake(@PathParam("vmMake") String vmMake) {
		List<T> ret = new ArrayList<T>();
		
		try {
			List batmod = iclubVehicleMasterDAO.findByVmMake(vmMake);
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubVehicleMaster iclubVMaster = (IclubVehicleMaster) object;
					IclubVehicleMasterModel iCVm = new IclubVehicleMasterModel();
					
					iCVm.setVmId(iclubVMaster.getVmId());
					iCVm.setVmMake(iclubVMaster.getVmMake());
					iCVm.setVmModel(iclubVMaster.getVmModel());
					iCVm.setVmMrktRate(iclubVMaster.getVmMrktRate());
					iCVm.setVmOrigRate(iclubVMaster.getVmOrigRate());
					iCVm.setVmRetRate(iclubVMaster.getVmRetRate());
					iCVm.setVmProdDt(iclubVMaster.getVmProdDt());
					iCVm.setVmCrtdDt(iclubVMaster.getVmCrtdDt());
					iCVm.setVmRatePrct(iclubVMaster.getVmRatePrct());
					iCVm.setIclubPerson(iclubVMaster.getIclubPerson() != null ? iclubVMaster.getIclubPerson().getPId() : null);
					
					if (iclubVMaster.getIclubVehicles() != null && iclubVMaster.getIclubVehicles().size() > 0) {
						String[] vehicles = new String[iclubVMaster.getIclubVehicles().size()];
						int i = 0;
						for (IclubVehicle vehicle : iclubVMaster.getIclubVehicles()) {
							vehicles[i] = vehicle.getVId();
							i++;
						}
						iCVm.setIclubVehicles(vehicles);
					}
					
					ret.add((T) iCVm);
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
	public <T extends IclubVehicleMasterModel> List<T> getByUser(@PathParam("user") String user) {
		List<T> ret = new ArrayList<T>();
		
		try {
			List batmod = iclubNamedQueryDAO.findByUser(user, IclubVehicleMaster.class.getSimpleName());
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubVehicleMaster iclubVMaster = (IclubVehicleMaster) object;
					IclubVehicleMasterModel iCVm = new IclubVehicleMasterModel();
					
					iCVm.setVmId(iclubVMaster.getVmId());
					iCVm.setVmMake(iclubVMaster.getVmMake());
					iCVm.setVmModel(iclubVMaster.getVmModel());
					iCVm.setVmMrktRate(iclubVMaster.getVmMrktRate());
					iCVm.setVmOrigRate(iclubVMaster.getVmOrigRate());
					iCVm.setVmRetRate(iclubVMaster.getVmRetRate());
					iCVm.setVmProdDt(iclubVMaster.getVmProdDt());
					iCVm.setVmCrtdDt(iclubVMaster.getVmCrtdDt());
					iCVm.setVmRatePrct(iclubVMaster.getVmRatePrct());
					iCVm.setIclubPerson(iclubVMaster.getIclubPerson() != null ? iclubVMaster.getIclubPerson().getPId() : null);
					
					if (iclubVMaster.getIclubVehicles() != null && iclubVMaster.getIclubVehicles().size() > 0) {
						String[] vehicles = new String[iclubVMaster.getIclubVehicles().size()];
						int i = 0;
						for (IclubVehicle vehicle : iclubVMaster.getIclubVehicles()) {
							vehicles[i] = vehicle.getVId();
							i++;
						}
						iCVm.setIclubVehicles(vehicles);
					}
					
					ret.add((T) iCVm);
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
	public IclubVehicleMasterModel getById(@PathParam("id") Long id) {
		IclubVehicleMasterModel model = new IclubVehicleMasterModel();
		try {
			IclubVehicleMaster bean = iclubVehicleMasterDAO.findById(id);
			
			model.setVmId(bean.getVmId());
			model.setVmMake(bean.getVmMake());
			model.setVmModel(bean.getVmModel());
			model.setVmMrktRate(bean.getVmMrktRate());
			model.setVmOrigRate(bean.getVmOrigRate());
			model.setVmRetRate(bean.getVmRetRate());
			model.setVmProdDt(bean.getVmProdDt());
			model.setVmCrtdDt(bean.getVmCrtdDt());
			model.setIclubPerson(bean.getIclubPerson() != null ? bean.getIclubPerson().getPId() : null);
			
			if (bean.getIclubVehicles() != null && bean.getIclubVehicles().size() > 0) {
				String[] vehicles = new String[bean.getIclubVehicles().size()];
				int i = 0;
				for (IclubVehicle vehicle : bean.getIclubVehicles()) {
					vehicles[i] = vehicle.getVId();
					i++;
				}
				model.setIclubVehicles(vehicles);
			}
			
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return model;
	}
	
	public IclubVehicleMasterDAO getIclubVehicleMasterDAO() {
		return iclubVehicleMasterDAO;
	}
	
	public void setIclubVehicleMasterDAO(IclubVehicleMasterDAO iclubVehicleMasterDAO) {
		this.iclubVehicleMasterDAO = iclubVehicleMasterDAO;
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
	
	public IclubNamedQueryDAO getIclubNamedQueryDAO() {
		return iclubNamedQueryDAO;
	}
	
	public void setIclubNamedQueryDAO(IclubNamedQueryDAO iclubNamedQueryDAO) {
		this.iclubNamedQueryDAO = iclubNamedQueryDAO;
	}
	
}
