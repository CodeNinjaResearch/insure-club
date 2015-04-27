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

import za.co.iclub.pss.orm.bean.IclubSecurityMaster;
import za.co.iclub.pss.orm.bean.IclubVehicle;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubInsuranceItemTypeDAO;
import za.co.iclub.pss.orm.dao.IclubNamedQueryDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.orm.dao.IclubSecurityMasterDAO;
import za.co.iclub.pss.ws.model.IclubSecurityMasterModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubSecurityMasterService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubSecurityMasterService {

	private static final Logger LOGGER = Logger.getLogger(IclubSecurityMasterService.class);
	private IclubSecurityMasterDAO iclubSecurityMasterDAO;
	private IclubCommonDAO iclubCommonDAO;
	private IclubInsuranceItemTypeDAO iclubInsuranceItemTypeDAO;
	private IclubPersonDAO iclubPersonDAO;
	private IclubNamedQueryDAO iclubNamedQueryDAO;

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional
	public ResponseModel add(IclubSecurityMasterModel model) {
		try {

			IclubSecurityMaster iCSm = new IclubSecurityMaster();

			iCSm.setSmId(model.getSmId());
			iCSm.setSmCrtdDt(model.getSmCrtdDt());
			iCSm.setSmDesc(model.getSmDesc());
			iCSm.setSmStatus(model.getSmStatus());
			iCSm.setIclubInsuranceItemType(model.getIclubInsuranceItemType() != null ? iclubInsuranceItemTypeDAO.findById(model.getIclubInsuranceItemType()) : null);
			iCSm.setIclubPerson(iclubPersonDAO.findById(model.getIclubPerson()));
			iCSm.setSmStatus(model.getSmStatus());

			iclubSecurityMasterDAO.save(iCSm);

			LOGGER.info("Save Success with ID :: " + iCSm.getSmId());

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
	public ResponseModel mod(IclubSecurityMasterModel model) {
		try {
			IclubSecurityMaster iCSm = new IclubSecurityMaster();

			iCSm.setSmId(model.getSmId());
			iCSm.setSmCrtdDt(model.getSmCrtdDt());
			iCSm.setSmDesc(model.getSmDesc());
			iCSm.setSmStatus(model.getSmStatus());
			iCSm.setIclubInsuranceItemType(iclubInsuranceItemTypeDAO.findById(model.getIclubInsuranceItemType()));
			iCSm.setIclubPerson(iclubPersonDAO.findById(model.getIclubPerson()));
			iCSm.setSmStatus(model.getSmStatus());

			iclubSecurityMasterDAO.merge(iCSm);

			LOGGER.info("Save Success with ID :: " + model.getSmId());

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
	public Response del(@PathParam("id") String id) {
		try {
			iclubSecurityMasterDAO.delete(iclubSecurityMasterDAO.findById(id));
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
	public <T extends IclubSecurityMasterModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubSecurityMasterDAO.findAll();

			for (Object object : batmod) {
				IclubSecurityMaster iclubSMaster = (IclubSecurityMaster) object;
				IclubSecurityMasterModel iCSm = new IclubSecurityMasterModel();

				iCSm.setSmId(iclubSMaster.getSmId());
				iCSm.setSmCrtdDt(iclubSMaster.getSmCrtdDt());
				iCSm.setSmDesc(iclubSMaster.getSmDesc());
				iCSm.setSmStatus(iclubSMaster.getSmStatus());
				iCSm.setIclubInsuranceItemType(iclubSMaster.getIclubInsuranceItemType() != null ? iclubSMaster.getIclubInsuranceItemType().getIitId() : null);
				iCSm.setIclubPerson(iclubSMaster.getIclubPerson() != null ? iclubSMaster.getIclubPerson().getPId() : null);

				if (iclubSMaster.getIclubVehicles() != null && iclubSMaster.getIclubVehicles().size() > 0) {
					String[] vehicles = new String[iclubSMaster.getIclubVehicles().size()];
					int i = 0;
					for (IclubVehicle vehicle : iclubSMaster.getIclubVehicles()) {
						vehicles[i] = vehicle.getVId();
						i++;
					}
					iCSm.setIclubVehicles(vehicles);
				}

				ret.add((T) iCSm);
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
	public <T extends IclubSecurityMasterModel> List<T> getByUser(@PathParam("user") String user) {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubNamedQueryDAO.findByUser(user, IclubSecurityMaster.class.getSimpleName());

			for (Object object : batmod) {
				IclubSecurityMaster iclubSMaster = (IclubSecurityMaster) object;
				IclubSecurityMasterModel iCSm = new IclubSecurityMasterModel();

				iCSm.setSmId(iclubSMaster.getSmId());
				iCSm.setSmCrtdDt(iclubSMaster.getSmCrtdDt());
				iCSm.setSmDesc(iclubSMaster.getSmDesc());
				iCSm.setSmStatus(iclubSMaster.getSmStatus());
				iCSm.setIclubInsuranceItemType(iclubSMaster.getIclubInsuranceItemType() != null ? iclubSMaster.getIclubInsuranceItemType().getIitId() : null);
				iCSm.setIclubPerson(iclubSMaster.getIclubPerson() != null ? iclubSMaster.getIclubPerson().getPId() : null);
				if (iclubSMaster.getIclubVehicles() != null && iclubSMaster.getIclubVehicles().size() > 0) {
					String[] vehicles = new String[iclubSMaster.getIclubVehicles().size()];
					int i = 0;
					for (IclubVehicle vehicle : iclubSMaster.getIclubVehicles()) {
						vehicles[i] = vehicle.getVId();
						i++;
					}
					iCSm.setIclubVehicles(vehicles);
				}

				ret.add((T) iCSm);
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
	public IclubSecurityMasterModel getById(@PathParam("id") String id) {
		IclubSecurityMasterModel model = new IclubSecurityMasterModel();
		try {
			IclubSecurityMaster bean = iclubSecurityMasterDAO.findById(id);

			model.setSmId(bean.getSmId());
			model.setSmCrtdDt(bean.getSmCrtdDt());
			model.setSmDesc(bean.getSmDesc());
			model.setIclubInsuranceItemType(bean.getIclubInsuranceItemType() != null ? bean.getIclubInsuranceItemType().getIitId() : null);
			model.setIclubPerson(bean.getIclubPerson() != null ? bean.getIclubPerson().getPId() : null);
			model.setSmStatus(bean.getSmStatus());
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

	public IclubSecurityMasterDAO getIclubSecurityMasterDAO() {
		return iclubSecurityMasterDAO;
	}

	public void setIclubSecurityMasterDAO(IclubSecurityMasterDAO iclubSecurityMasterDAO) {
		this.iclubSecurityMasterDAO = iclubSecurityMasterDAO;
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

	public IclubNamedQueryDAO getIclubNamedQueryDAO() {
		return iclubNamedQueryDAO;
	}

	public void setIclubNamedQueryDAO(IclubNamedQueryDAO iclubNamedQueryDAO) {
		this.iclubNamedQueryDAO = iclubNamedQueryDAO;
	}

}
