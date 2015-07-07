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

import za.co.iclub.pss.orm.bean.IclubDriver;
import za.co.iclub.pss.orm.bean.IclubVehicle;
import za.co.iclub.pss.orm.dao.IclubAccessTypeDAO;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubDriverDAO;
import za.co.iclub.pss.orm.dao.IclubLicenseCodeDAO;
import za.co.iclub.pss.orm.dao.IclubMaritialStatusDAO;
import za.co.iclub.pss.orm.dao.IclubNamedQueryDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.ws.model.IclubDriverModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubDriverService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubDriverService {
	
	protected static final Logger LOGGER = Logger.getLogger(IclubDriverService.class);
	private IclubCommonDAO iclubCommonDAO;
	private IclubDriverDAO iclubDriverDAO;
	private IclubPersonDAO iclubPersonDAO;
	private IclubAccessTypeDAO iclubAccessTypeDAO;
	private IclubLicenseCodeDAO iclubLicenseCodeDAO;
	private IclubMaritialStatusDAO iclubMaritialStatusDAO;
	private IclubNamedQueryDAO iclubNamedQueryDAO;
	
	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubDriverModel model) {
		try {
			IclubDriver iCt = new IclubDriver();
			
			iCt.setDId(model.getDId());
			iCt.setDDob(model.getDDob());
			iCt.setDIssueDt(model.getDIssueDt());
			iCt.setDLicenseNum(model.getDLicenseNum());
			iCt.setDName(model.getDName());
			iCt.setDCrtdDt(model.getDCrtdDt());
			iCt.setDIssueYears(model.getDIssueYears());
			iCt.setIclubAccessTypeByDAccessTypeId(model.getIclubAccessTypeByDAccessTypeId() != null ? iclubAccessTypeDAO.findById(model.getIclubAccessTypeByDAccessTypeId()) : null);
			iCt.setIclubAccessTypeByDAccessStatusId(model.getIclubAccessTypeByDAccessStatusId() != null ? iclubAccessTypeDAO.findById(model.getIclubAccessTypeByDAccessStatusId()) : null);
			iCt.setIclubLicenseCode(model.getIclubLicenseCode() != null ? iclubLicenseCodeDAO.findById(model.getIclubLicenseCode()) : null);
			iCt.setIclubMaritialStatus(model.getIclubMaritialStatus() != null ? iclubMaritialStatusDAO.findById(model.getIclubMaritialStatus()) : null);
			iCt.setIclubPersonByDPersonId(model.getIclubPersonByDPersonId() != null && !model.getIclubPersonByDPersonId().trim().equalsIgnoreCase("") ? iclubPersonDAO.findById(model.getIclubPersonByDPersonId()) : null);
			iCt.setIclubPersonByDCrtdBy(model.getIclubPersonByDCrtdBy() != null && !model.getIclubPersonByDCrtdBy().trim().equalsIgnoreCase("") ? iclubPersonDAO.findById(model.getIclubPersonByDCrtdBy()) : null);
			
			iclubDriverDAO.save(iCt);
			
			LOGGER.info("Save Success with ID :: " + iCt.getDId());
			
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
	public ResponseModel mod(IclubDriverModel model) {
		try {
			IclubDriver iCt = new IclubDriver();
			
			iCt.setDId(model.getDId());
			iCt.setDDob(model.getDDob());
			iCt.setDIssueDt(model.getDIssueDt());
			iCt.setDLicenseNum(model.getDLicenseNum());
			iCt.setDName(model.getDName());
			iCt.setDCrtdDt(model.getDCrtdDt());
			iCt.setDIssueYears(model.getDIssueYears());
			iCt.setIclubAccessTypeByDAccessTypeId(model.getIclubAccessTypeByDAccessTypeId() != null ? iclubAccessTypeDAO.findById(model.getIclubAccessTypeByDAccessTypeId()) : null);
			iCt.setIclubAccessTypeByDAccessStatusId(model.getIclubAccessTypeByDAccessStatusId() != null ? iclubAccessTypeDAO.findById(model.getIclubAccessTypeByDAccessStatusId()) : null);
			iCt.setIclubLicenseCode(model.getIclubLicenseCode() != null ? iclubLicenseCodeDAO.findById(model.getIclubLicenseCode()) : null);
			iCt.setIclubMaritialStatus(model.getIclubMaritialStatus() != null ? iclubMaritialStatusDAO.findById(model.getIclubMaritialStatus()) : null);
			iCt.setIclubPersonByDPersonId(model.getIclubPersonByDPersonId() != null && !model.getIclubPersonByDPersonId().trim().equalsIgnoreCase("") ? iclubPersonDAO.findById(model.getIclubPersonByDPersonId()) : null);
			iCt.setIclubPersonByDCrtdBy(model.getIclubPersonByDCrtdBy() != null && !model.getIclubPersonByDCrtdBy().trim().equalsIgnoreCase("") ? iclubPersonDAO.findById(model.getIclubPersonByDCrtdBy()) : null);
			
			iclubDriverDAO.merge(iCt);
			
			LOGGER.info("Merge Success with ID :: " + model.getDId());
			
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
			iclubDriverDAO.delete(iclubDriverDAO.findById(id));
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
	public <T extends IclubDriverModel> List<T> list() {
		List<T> ret = new ArrayList<T>();
		
		try {
			List batmod = iclubDriverDAO.findAll();
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubDriver iCt = (IclubDriver) object;
					
					IclubDriverModel model = new IclubDriverModel();
					
					model.setDId(iCt.getDId());
					model.setDDob(iCt.getDDob());
					model.setDIssueDt(iCt.getDIssueDt());
					model.setDLicenseNum(iCt.getDLicenseNum());
					model.setDName(iCt.getDName());
					model.setDCrtdDt(iCt.getDCrtdDt());
					model.setDIssueYears(iCt.getDIssueYears());
					model.setIclubAccessTypeByDAccessStatusId(iCt.getIclubAccessTypeByDAccessStatusId() != null ? iCt.getIclubAccessTypeByDAccessStatusId().getAtId() : null);
					model.setIclubAccessTypeByDAccessTypeId(iCt.getIclubAccessTypeByDAccessTypeId() != null ? (iCt.getIclubAccessTypeByDAccessTypeId().getAtId()) : null);
					model.setIclubLicenseCode(iCt.getIclubLicenseCode() != null ? (iCt.getIclubLicenseCode().getLcId()) : null);
					model.setIclubMaritialStatus(iCt.getIclubMaritialStatus() != null ? (iCt.getIclubMaritialStatus().getMsId()) : null);
					model.setIclubPersonByDPersonId(iCt.getIclubPersonByDPersonId() != null ? (iCt.getIclubPersonByDPersonId().getPId()) : null);
					model.setIclubPersonByDCrtdBy(iCt.getIclubPersonByDCrtdBy() != null ? (iCt.getIclubPersonByDCrtdBy().getPId()) : null);
					
					if (iCt.getIclubVehicles() != null && iCt.getIclubVehicles().size() > 0) {
						String[] vehicles = new String[iCt.getIclubVehicles().size()];
						int i = 0;
						for (IclubVehicle vehicle : iCt.getIclubVehicles()) {
							
							vehicles[i] = vehicle.getVId();
							i++;
						}
						model.setIclubVehicles(vehicles);
					}
					
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
	public <T extends IclubDriverModel> List<T> getByUser(@PathParam("user") String user) {
		List<T> ret = new ArrayList<T>();
		
		try {
			List batmod = iclubNamedQueryDAO.findByUser(user, IclubDriver.class.getSimpleName());
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubDriver iCt = (IclubDriver) object;
					
					IclubDriverModel model = new IclubDriverModel();
					
					model.setDId(iCt.getDId());
					model.setDDob(iCt.getDDob());
					model.setDIssueDt(iCt.getDIssueDt());
					model.setDLicenseNum(iCt.getDLicenseNum());
					model.setDName(iCt.getDName());
					model.setDCrtdDt(iCt.getDCrtdDt());
					model.setDIssueYears(iCt.getDIssueYears());
					model.setIclubAccessTypeByDAccessStatusId(iCt.getIclubAccessTypeByDAccessStatusId() != null ? iCt.getIclubAccessTypeByDAccessStatusId().getAtId() : null);
					model.setIclubAccessTypeByDAccessTypeId(iCt.getIclubAccessTypeByDAccessTypeId() != null ? (iCt.getIclubAccessTypeByDAccessTypeId().getAtId()) : null);
					model.setIclubLicenseCode(iCt.getIclubLicenseCode() != null ? (iCt.getIclubLicenseCode().getLcId()) : null);
					model.setIclubMaritialStatus(iCt.getIclubMaritialStatus() != null ? (iCt.getIclubMaritialStatus().getMsId()) : null);
					model.setIclubPersonByDPersonId(iCt.getIclubPersonByDPersonId() != null ? (iCt.getIclubPersonByDPersonId().getPId()) : null);
					model.setIclubPersonByDCrtdBy(iCt.getIclubPersonByDCrtdBy() != null ? (iCt.getIclubPersonByDCrtdBy().getPId()) : null);
					
					if (iCt.getIclubVehicles() != null && iCt.getIclubVehicles().size() > 0) {
						String[] vehicles = new String[iCt.getIclubVehicles().size()];
						int i = 0;
						for (IclubVehicle vehicle : iCt.getIclubVehicles()) {
							
							vehicles[i] = vehicle.getVId();
							i++;
						}
						model.setIclubVehicles(vehicles);
					}
					
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
	public IclubDriverModel getById(@PathParam("id") String id) {
		IclubDriverModel model = new IclubDriverModel();
		try {
			IclubDriver bean = iclubDriverDAO.findById(id);
			
			model.setDId(bean.getDId());
			model.setDDob(bean.getDDob());
			model.setDIssueDt(bean.getDIssueDt());
			model.setDLicenseNum(bean.getDLicenseNum());
			model.setDName(bean.getDName());
			model.setDCrtdDt(bean.getDCrtdDt());
			model.setDIssueYears(bean.getDIssueYears());
			model.setIclubAccessTypeByDAccessStatusId(bean.getIclubAccessTypeByDAccessStatusId() != null ? bean.getIclubAccessTypeByDAccessStatusId().getAtId() : null);
			model.setIclubAccessTypeByDAccessTypeId(bean.getIclubAccessTypeByDAccessTypeId() != null ? (bean.getIclubAccessTypeByDAccessTypeId().getAtId()) : null);
			model.setIclubLicenseCode(bean.getIclubLicenseCode() != null ? (bean.getIclubLicenseCode().getLcId()) : null);
			model.setIclubMaritialStatus(bean.getIclubMaritialStatus() != null ? (bean.getIclubMaritialStatus().getMsId()) : null);
			model.setIclubPersonByDPersonId(bean.getIclubPersonByDPersonId() != null ? (bean.getIclubPersonByDPersonId().getPId()) : null);
			model.setIclubPersonByDCrtdBy(bean.getIclubPersonByDCrtdBy() != null ? (bean.getIclubPersonByDCrtdBy().getPId()) : null);
			
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
	
	@GET
	@Path("/getByPersonId/{id}")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public IclubDriverModel getByPersonId(@PathParam("id") String id) {
		IclubDriverModel model = new IclubDriverModel();
		try {
			IclubDriver bean = iclubNamedQueryDAO.findByPersonId(id);
			
			model.setDId(bean.getDId());
			model.setDDob(bean.getDDob());
			model.setDIssueDt(bean.getDIssueDt());
			model.setDLicenseNum(bean.getDLicenseNum());
			model.setDName(bean.getDName());
			model.setDCrtdDt(bean.getDCrtdDt());
			model.setDIssueYears(bean.getDIssueYears());
			model.setIclubAccessTypeByDAccessStatusId(bean.getIclubAccessTypeByDAccessStatusId() != null ? bean.getIclubAccessTypeByDAccessStatusId().getAtId() : null);
			model.setIclubAccessTypeByDAccessTypeId(bean.getIclubAccessTypeByDAccessTypeId() != null ? (bean.getIclubAccessTypeByDAccessTypeId().getAtId()) : null);
			model.setIclubLicenseCode(bean.getIclubLicenseCode() != null ? (bean.getIclubLicenseCode().getLcId()) : null);
			model.setIclubMaritialStatus(bean.getIclubMaritialStatus() != null ? (bean.getIclubMaritialStatus().getMsId()) : null);
			model.setIclubPersonByDPersonId(bean.getIclubPersonByDPersonId() != null ? (bean.getIclubPersonByDPersonId().getPId()) : null);
			model.setIclubPersonByDCrtdBy(bean.getIclubPersonByDCrtdBy() != null ? (bean.getIclubPersonByDCrtdBy().getPId()) : null);
			
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
	
	public IclubDriverDAO getIclubDriverDAO() {
		return iclubDriverDAO;
	}
	
	public void setIclubDriverDAO(IclubDriverDAO iclubDriverDAO) {
		this.iclubDriverDAO = iclubDriverDAO;
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
	
	public IclubAccessTypeDAO getIclubAccessTypeDAO() {
		return iclubAccessTypeDAO;
	}
	
	public void setIclubAccessTypeDAO(IclubAccessTypeDAO iclubAccessTypeDAO) {
		this.iclubAccessTypeDAO = iclubAccessTypeDAO;
	}
	
	public IclubLicenseCodeDAO getIclubLicenseCodeDAO() {
		return iclubLicenseCodeDAO;
	}
	
	public void setIclubLicenseCodeDAO(IclubLicenseCodeDAO iclubLicenseCodeDAO) {
		this.iclubLicenseCodeDAO = iclubLicenseCodeDAO;
	}
	
	public IclubMaritialStatusDAO getIclubMaritialStatusDAO() {
		return iclubMaritialStatusDAO;
	}
	
	public void setIclubMaritialStatusDAO(IclubMaritialStatusDAO iclubMaritialStatusDAO) {
		this.iclubMaritialStatusDAO = iclubMaritialStatusDAO;
	}
	
	public IclubNamedQueryDAO getIclubNamedQueryDAO() {
		return iclubNamedQueryDAO;
	}
	
	public void setIclubNamedQueryDAO(IclubNamedQueryDAO iclubNamedQueryDAO) {
		this.iclubNamedQueryDAO = iclubNamedQueryDAO;
	}
	
}
