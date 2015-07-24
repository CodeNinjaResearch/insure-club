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

import za.co.iclub.pss.orm.bean.IclubPropSecType;
import za.co.iclub.pss.orm.bean.IclubProperty;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubInsuranceItemTypeDAO;
import za.co.iclub.pss.orm.dao.IclubNamedQueryDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.orm.dao.IclubPropSecTypeDAO;
import za.co.iclub.pss.ws.model.IclubPropSecTypeModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubPropSecTypeService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubPropSecTypeService {

	protected static final Logger LOGGER = Logger.getLogger(IclubPropSecTypeService.class);
	private IclubCommonDAO iclubCommonDAO;
	private IclubPropSecTypeDAO iclubPropSecTypeDAO;
	private IclubPersonDAO iclubPersonDAO;
	private IclubInsuranceItemTypeDAO iclubInsuranceItemTypeDAO;
	private IclubNamedQueryDAO iclubNamedQueryDAO;

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubPropSecTypeModel model) {
		try {
			IclubPropSecType iCPt = new IclubPropSecType();

			iCPt.setPstId(iclubCommonDAO.getNextId(IclubPropSecType.class));
			iCPt.setPstLongDesc(model.getPstLongDesc());
			iCPt.setPstShortDesc(model.getPstShortDesc());
			iCPt.setPstStatus(model.getPstStatus());

			iclubPropSecTypeDAO.save(iCPt);

			LOGGER.info("Save Success with ID :: " + iCPt.getPstId());

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
	public ResponseModel mod(IclubPropSecTypeModel model) {
		try {
			IclubPropSecType iCPt = new IclubPropSecType();

			iCPt.setPstId(model.getPstId());
			iCPt.setPstLongDesc(model.getPstLongDesc());
			iCPt.setPstShortDesc(model.getPstShortDesc());
			iCPt.setPstStatus(model.getPstStatus());

			iclubPropSecTypeDAO.merge(iCPt);

			LOGGER.info("Merge Success with ID :: " + model.getPstId());

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
			iclubPropSecTypeDAO.delete(iclubPropSecTypeDAO.findById(id));
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
	public <T extends IclubPropSecTypeModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubPropSecTypeDAO.findAll();
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubPropSecType iCPt = (IclubPropSecType) object;

					IclubPropSecTypeModel model = new IclubPropSecTypeModel();

					model.setPstId(iCPt.getPstId());
					model.setPstLongDesc(iCPt.getPstLongDesc());
					model.setPstShortDesc(iCPt.getPstShortDesc());
					model.setPstStatus(iCPt.getPstStatus());

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
	public <T extends IclubPropSecTypeModel> List<T> getByUser(@PathParam("user") String user) {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubNamedQueryDAO.findByUser(user, IclubPropSecType.class.getSimpleName());
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubPropSecType iCPt = (IclubPropSecType) object;

					IclubPropSecTypeModel model = new IclubPropSecTypeModel();

					model.setPstId(iCPt.getPstId());
					model.setPstLongDesc(iCPt.getPstLongDesc());
					model.setPstShortDesc(iCPt.getPstShortDesc());
					model.setPstStatus(iCPt.getPstStatus());

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
	public IclubPropSecTypeModel getById(@PathParam("id") Long id) {
		IclubPropSecTypeModel model = new IclubPropSecTypeModel();
		try {
			IclubPropSecType bean = iclubPropSecTypeDAO.findById(id);

			model.setPstId(bean.getPstId());
			model.setPstLongDesc(bean.getPstLongDesc());
			model.setPstShortDesc(bean.getPstShortDesc());
			model.setPstStatus(bean.getPstStatus());

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

	@GET
	@Path("/validate/sd/{val}/{id}")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel validateSd(@PathParam("val") String val, @PathParam("id") Long id) {
		try {
			List data = iclubNamedQueryDAO.getBySD(val, id, IclubPropSecType.class.getSimpleName());
			ResponseModel message = new ResponseModel();
			if ((data != null) && (data.size() > 0)) {
				message.setStatusCode(Integer.valueOf(1));
				message.setStatusDesc("Duplicate Value");
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

	public IclubPropSecTypeDAO getIclubPropSecTypeDAO() {
		return iclubPropSecTypeDAO;
	}

	public void setIclubPropSecTypeDAO(IclubPropSecTypeDAO iclubPropSecTypeDAO) {
		this.iclubPropSecTypeDAO = iclubPropSecTypeDAO;
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
