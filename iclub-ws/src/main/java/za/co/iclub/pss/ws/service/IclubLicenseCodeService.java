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
import za.co.iclub.pss.orm.bean.IclubLicenseCode;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubLicenseCodeDAO;
import za.co.iclub.pss.orm.dao.IclubNamedQueryDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.ws.model.IclubLicenseCodeModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubLicenseCodeService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubLicenseCodeService {

	protected static final Logger LOGGER = Logger.getLogger(IclubLicenseCodeService.class);
	private IclubCommonDAO iclubCommonDAO;
	private IclubLicenseCodeDAO iclubLicenseCodeDAO;
	private IclubPersonDAO iclubPersonDAO;
	private IclubNamedQueryDAO iclubNamedQueryDAO;

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubLicenseCodeModel model) {
		try {
			IclubLicenseCode iCt = new IclubLicenseCode();

			iCt.setLcId(iclubCommonDAO.getNextId(IclubLicenseCode.class));
			iCt.setLcDesc(model.getLcDesc());
			iCt.setLcCrtdDt(model.getLcCrtdDt());
			iCt.setLcStatus(model.getLcStatus());
			iCt.setIclubPerson(model.getIclubPerson() != null && !model.getIclubPerson().trim().equalsIgnoreCase("") ? iclubPersonDAO.findById(model.getIclubPerson()) : null);
			iCt.setLcCategory(model.getLcCategory());

			iclubLicenseCodeDAO.save(iCt);

			LOGGER.info("Save Success with ID :: " + iCt.getLcId());

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
	public ResponseModel mod(IclubLicenseCodeModel model) {
		try {
			IclubLicenseCode iCt = new IclubLicenseCode();

			iCt.setLcId(model.getLcId());
			iCt.setLcDesc(model.getLcDesc());
			iCt.setLcCrtdDt(model.getLcCrtdDt());
			iCt.setLcStatus(model.getLcStatus());
			iCt.setIclubPerson(model.getIclubPerson() != null && !model.getIclubPerson().trim().equalsIgnoreCase("") ? iclubPersonDAO.findById(model.getIclubPerson()) : null);
			iCt.setLcCategory(model.getLcCategory());

			iclubLicenseCodeDAO.merge(iCt);

			LOGGER.info("Merge Success with ID :: " + model.getLcId());

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
	public Response del(@PathParam("id") Long id) {
		try {
			iclubLicenseCodeDAO.delete(iclubLicenseCodeDAO.findById(id));
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
	public <T extends IclubLicenseCodeModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubLicenseCodeDAO.findAll();

			for (Object object : batmod) {
				IclubLicenseCode iCt = (IclubLicenseCode) object;

				IclubLicenseCodeModel model = new IclubLicenseCodeModel();

				model.setLcId(iCt.getLcId());
				model.setLcCrtdDt(iCt.getLcCrtdDt());
				model.setLcDesc(iCt.getLcDesc());
				model.setLcStatus(iCt.getLcStatus());
				model.setIclubPerson(iCt.getIclubPerson() != null ? (iCt.getIclubPerson().getPId()) : null);
				model.setLcCategory(iCt.getLcCategory());
				if (iCt.getIclubDrivers() != null && iCt.getIclubDrivers().size() > 0) {
					String[] drivers = new String[iCt.getIclubDrivers().size()];
					int i = 0;
					for (IclubDriver iclubMessage : iCt.getIclubDrivers()) {
						drivers[i] = iclubMessage.getDId();
						i++;
					}
					model.setIclubDrivers(drivers);
				}

				ret.add((T) model);
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
	public <T extends IclubLicenseCodeModel> List<T> getByUser(@PathParam("user") String user) {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubNamedQueryDAO.findByUser(user, IclubLicenseCode.class.getSimpleName());

			for (Object object : batmod) {
				IclubLicenseCode iCt = (IclubLicenseCode) object;

				IclubLicenseCodeModel model = new IclubLicenseCodeModel();

				model.setLcId(iCt.getLcId());
				model.setLcCrtdDt(iCt.getLcCrtdDt());
				model.setLcDesc(iCt.getLcDesc());
				model.setLcStatus(iCt.getLcStatus());
				model.setIclubPerson(iCt.getIclubPerson() != null ? (iCt.getIclubPerson().getPId()) : null);
				model.setLcCategory(iCt.getLcCategory());
				if (iCt.getIclubDrivers() != null && iCt.getIclubDrivers().size() > 0) {
					String[] drivers = new String[iCt.getIclubDrivers().size()];
					int i = 0;
					for (IclubDriver iclubMessage : iCt.getIclubDrivers()) {
						drivers[i] = iclubMessage.getDId();
						i++;
					}
					model.setIclubDrivers(drivers);
				}

				ret.add((T) model);
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
	public IclubLicenseCodeModel getById(@PathParam("id") Long id) {
		IclubLicenseCodeModel model = new IclubLicenseCodeModel();
		try {
			IclubLicenseCode bean = iclubLicenseCodeDAO.findById(id);

			model.setLcId(bean.getLcId());
			model.setLcCrtdDt(bean.getLcCrtdDt());
			model.setLcDesc(bean.getLcDesc());
			model.setLcStatus(bean.getLcStatus());
			model.setIclubPerson(bean.getIclubPerson() != null ? (bean.getIclubPerson().getPId()) : null);
			model.setLcCategory(bean.getLcCategory());
			if (bean.getIclubDrivers() != null && bean.getIclubDrivers().size() > 0) {
				String[] drivers = new String[bean.getIclubDrivers().size()];
				int i = 0;
				for (IclubDriver iclubMessage : bean.getIclubDrivers()) {
					drivers[i] = iclubMessage.getDId();
					i++;
				}
				model.setIclubDrivers(drivers);
			}

		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return model;
	}

	public IclubLicenseCodeDAO getIclubLicenseCodeDAO() {
		return iclubLicenseCodeDAO;
	}

	public void setIclubLicenseCodeDAO(IclubLicenseCodeDAO iclubLicenseCodeDAO) {
		this.iclubLicenseCodeDAO = iclubLicenseCodeDAO;
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
