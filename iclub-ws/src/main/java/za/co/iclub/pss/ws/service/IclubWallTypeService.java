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
import za.co.iclub.pss.orm.bean.IclubWallType;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubNamedQueryDAO;
import za.co.iclub.pss.orm.dao.IclubWallTypeDAO;
import za.co.iclub.pss.ws.model.IclubWallTypeModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubWallTypeService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubWallTypeService {

	protected static final Logger LOGGER = Logger.getLogger(IclubWallTypeService.class);
	private IclubCommonDAO iclubCommonDAO;
	private IclubWallTypeDAO iclubWallTypeDAO;
	private IclubNamedQueryDAO iclubNamedQueryDAO;

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubWallTypeModel model) {
		try {
			IclubWallType iWt = new IclubWallType();

			iWt.setWtId(iclubCommonDAO.getNextId(IclubWallType.class));
			iWt.setWtLongDesc(model.getWtLongDesc());
			iWt.setWtShortDesc(model.getWtShortDesc());
			iWt.setWtStatus(model.getWtStatus());

			iclubWallTypeDAO.save(iWt);

			LOGGER.info("Save Success with ID :: " + iWt.getWtId());

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
	public ResponseModel mod(IclubWallTypeModel model) {
		try {
			IclubWallType iWt = new IclubWallType();

			iWt.setWtId(model.getWtId());
			iWt.setWtLongDesc(model.getWtLongDesc());
			iWt.setWtShortDesc(model.getWtShortDesc());
			iWt.setWtStatus(model.getWtStatus());

			iclubWallTypeDAO.merge(iWt);

			LOGGER.info("Merge Success with ID :: " + model.getWtId());

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
			iclubWallTypeDAO.delete(iclubWallTypeDAO.findById(id));
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
	public <T extends IclubWallTypeModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubWallTypeDAO.findAll();
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubWallType iWt = (IclubWallType) object;

					IclubWallTypeModel model = new IclubWallTypeModel();

					model.setWtId(iWt.getWtId());
					model.setWtLongDesc(iWt.getWtLongDesc());
					model.setWtShortDesc(iWt.getWtShortDesc());
					model.setWtStatus(iWt.getWtStatus());

					if (iWt.getIclubProperties() != null && iWt.getIclubProperties().size() > 0) {
						String[] properties = new String[iWt.getIclubProperties().size()];
						int i = 0;
						for (IclubProperty property : iWt.getIclubProperties()) {
							properties[i] = property.getPId();
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
	public IclubWallTypeModel getById(@PathParam("id") Long id) {
		IclubWallTypeModel model = new IclubWallTypeModel();
		try {
			IclubWallType bean = iclubWallTypeDAO.findById(id);

			model.setWtId(bean.getWtId());
			model.setWtLongDesc(bean.getWtLongDesc());
			model.setWtShortDesc(bean.getWtShortDesc());
			model.setWtStatus(bean.getWtStatus());

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

	@GET
	@Path("/validate/sd/{val}/{id}")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel validateSd(@PathParam("val") String val, @PathParam("id") Long id) {
		try {
			List data = iclubNamedQueryDAO.getBySD(val, id, IclubWallType.class.getSimpleName());
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

	public IclubWallTypeDAO getIclubWallTypeDAO() {
		return iclubWallTypeDAO;
	}

	public void setIclubWallTypeDAO(IclubWallTypeDAO iclubWallTypeDAO) {
		this.iclubWallTypeDAO = iclubWallTypeDAO;
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
