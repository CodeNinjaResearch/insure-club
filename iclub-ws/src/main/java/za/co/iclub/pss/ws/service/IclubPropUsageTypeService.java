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

import za.co.iclub.pss.orm.bean.IclubPropUsageType;
import za.co.iclub.pss.orm.bean.IclubProperty;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubInsuranceItemTypeDAO;
import za.co.iclub.pss.orm.dao.IclubNamedQueryDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.orm.dao.IclubPropUsageTypeDAO;
import za.co.iclub.pss.ws.model.IclubPropUsageTypeModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubPropUsageTypeService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubPropUsageTypeService {

	protected static final Logger LOGGER = Logger.getLogger(IclubPropUsageTypeService.class);
	private IclubCommonDAO iclubCommonDAO;
	private IclubPropUsageTypeDAO iclubPropUsageTypeDAO;
	private IclubPersonDAO iclubPersonDAO;
	private IclubInsuranceItemTypeDAO iclubInsuranceItemTypeDAO;
	private IclubNamedQueryDAO iclubNamedQueryDAO;

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubPropUsageTypeModel model) {
		try {
			IclubPropUsageType iCPt = new IclubPropUsageType();

			iCPt.setPuId(iclubCommonDAO.getNextId(IclubPropUsageType.class));
			iCPt.setPuLongDesc(model.getPuLongDesc());
			iCPt.setPuShortDesc(model.getPuShortDesc());
			iCPt.setPuStatus(model.getPuStatus());

			iclubPropUsageTypeDAO.save(iCPt);

			LOGGER.info("Save Success with ID :: " + iCPt.getPuId());

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
	public ResponseModel mod(IclubPropUsageTypeModel model) {
		try {
			IclubPropUsageType iCPt = new IclubPropUsageType();

			iCPt.setPuId(model.getPuId());
			iCPt.setPuLongDesc(model.getPuLongDesc());
			iCPt.setPuShortDesc(model.getPuShortDesc());
			iCPt.setPuStatus(model.getPuStatus());

			iclubPropUsageTypeDAO.merge(iCPt);

			LOGGER.info("Merge Success with ID :: " + model.getPuId());

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
			iclubPropUsageTypeDAO.delete(iclubPropUsageTypeDAO.findById(id));
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
	public <T extends IclubPropUsageTypeModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubPropUsageTypeDAO.findAll();
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubPropUsageType iCPt = (IclubPropUsageType) object;

					IclubPropUsageTypeModel model = new IclubPropUsageTypeModel();

					model.setPuId(iCPt.getPuId());
					model.setPuLongDesc(iCPt.getPuLongDesc());
					model.setPuShortDesc(iCPt.getPuShortDesc());
					model.setPuStatus(iCPt.getPuStatus());

					if (iCPt.getIclubProperties() != null && iCPt.getIclubProperties().size() > 0) {
						String[] properties = new String[iCPt.getIclubProperties().size()];
						int i = 0;
						for (IclubProperty iclubProperty : iCPt.getIclubProperties()) {
							properties[i] = iclubProperty.getPId();
							i++;
						}
						model.setIclubProperties(properties);
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
	public <T extends IclubPropUsageTypeModel> List<T> getByUser(@PathParam("user") String user) {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubNamedQueryDAO.findByUser(user, IclubPropUsageType.class.getSimpleName());
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubPropUsageType iCPt = (IclubPropUsageType) object;

					IclubPropUsageTypeModel model = new IclubPropUsageTypeModel();

					model.setPuId(iCPt.getPuId());
					model.setPuLongDesc(iCPt.getPuLongDesc());
					model.setPuShortDesc(iCPt.getPuShortDesc());
					model.setPuStatus(iCPt.getPuStatus());

					if (iCPt.getIclubProperties() != null && iCPt.getIclubProperties().size() > 0) {
						String[] properties = new String[iCPt.getIclubProperties().size()];
						int i = 0;
						for (IclubProperty iclubProperty : iCPt.getIclubProperties()) {
							properties[i] = iclubProperty.getPId();
							i++;
						}
						model.setIclubProperties(properties);
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
	public IclubPropUsageTypeModel getById(@PathParam("id") Long id) {
		IclubPropUsageTypeModel model = new IclubPropUsageTypeModel();
		try {
			IclubPropUsageType bean = iclubPropUsageTypeDAO.findById(id);

			model.setPuId(bean.getPuId());
			model.setPuLongDesc(bean.getPuLongDesc());
			model.setPuShortDesc(bean.getPuShortDesc());
			model.setPuStatus(bean.getPuStatus());

			if (bean.getIclubProperties() != null && bean.getIclubProperties().size() > 0) {
				String[] properties = new String[bean.getIclubProperties().size()];
				int i = 0;
				for (IclubProperty iclubProperty : bean.getIclubProperties()) {
					properties[i] = iclubProperty.getPId();
					i++;
				}
				model.setIclubProperties(properties);
			}

		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return model;
	}

	public IclubPropUsageTypeDAO getIclubPropUsageTypeDAO() {
		return iclubPropUsageTypeDAO;
	}

	public void setIclubPropUsageTypeDAO(IclubPropUsageTypeDAO iclubPropUsageTypeDAO) {
		this.iclubPropUsageTypeDAO = iclubPropUsageTypeDAO;
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
