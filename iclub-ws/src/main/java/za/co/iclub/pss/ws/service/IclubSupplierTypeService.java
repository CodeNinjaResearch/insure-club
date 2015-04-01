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

import za.co.iclub.pss.orm.bean.IclubSupplMaster;
import za.co.iclub.pss.orm.bean.IclubSupplierType;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubNamedQueryDAO;
import za.co.iclub.pss.orm.dao.IclubSupplierTypeDAO;
import za.co.iclub.pss.ws.model.IclubSupplierTypeModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubSupplierTypeService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubSupplierTypeService {

	protected static final Logger LOGGER = Logger.getLogger(IclubSupplierTypeService.class);
	private IclubCommonDAO iclubCommonDAO;
	private IclubSupplierTypeDAO iclubSupplierTypeDAO;
	private IclubNamedQueryDAO iclubNamedQueryDAO;

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubSupplierTypeModel model) {
		try {
			IclubSupplierType iSt = new IclubSupplierType();

			iSt.setStId(iclubCommonDAO.getNextId(IclubSupplierType.class));
			iSt.setStLongDesc(model.getStLongDesc());
			iSt.setStShortDesc(model.getStShortDesc());
			iSt.setStStatus(model.getStStatus());

			iclubSupplierTypeDAO.save(iSt);

			LOGGER.info("Save Success with ID :: " + iSt.getStId());

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
	public ResponseModel mod(IclubSupplierTypeModel model) {
		try {
			IclubSupplierType iSt = new IclubSupplierType();

			iSt.setStId(model.getStId());
			iSt.setStLongDesc(model.getStLongDesc());
			iSt.setStShortDesc(model.getStShortDesc());
			iSt.setStStatus(model.getStStatus());

			iclubSupplierTypeDAO.merge(iSt);

			LOGGER.info("Merge Success with ID :: " + model.getStId());

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
			iclubSupplierTypeDAO.delete(iclubSupplierTypeDAO.findById(id));
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
	public <T extends IclubSupplierTypeModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubSupplierTypeDAO.findAll();

			for (Object object : batmod) {
				IclubSupplierType iSt = (IclubSupplierType) object;

				IclubSupplierTypeModel model = new IclubSupplierTypeModel();

				model.setStId(iSt.getStId());
				model.setStLongDesc(iSt.getStLongDesc());
				model.setStShortDesc(iSt.getStShortDesc());
				model.setStStatus(iSt.getStStatus());

				if (iSt.getIclubSupplMasters() != null && iSt.getIclubSupplMasters().size() > 0) {
					String[] supplMasters = new String[iSt.getIclubSupplMasters().size()];
					int i = 0;
					for (IclubSupplMaster supplMaster : iSt.getIclubSupplMasters()) {
						supplMasters[i] = supplMaster.getSmId();
						i++;
					}
					model.setIclubSupplMasters(supplMasters);
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
	public IclubSupplierTypeModel getById(@PathParam("id") Long id) {
		IclubSupplierTypeModel model = new IclubSupplierTypeModel();
		try {
			IclubSupplierType bean = iclubSupplierTypeDAO.findById(id);

			model.setStId(bean.getStId());
			model.setStLongDesc(bean.getStLongDesc());
			model.setStShortDesc(bean.getStShortDesc());
			model.setStStatus(bean.getStStatus());

			if (bean.getIclubSupplMasters() != null && bean.getIclubSupplMasters().size() > 0) {
				String[] supplMasters = new String[bean.getIclubSupplMasters().size()];
				int i = 0;
				for (IclubSupplMaster supplMaster : bean.getIclubSupplMasters()) {
					supplMasters[i] = supplMaster.getSmId();
					i++;
				}
				model.setIclubSupplMasters(supplMasters);
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
			List data = iclubNamedQueryDAO.getBySD(val, id, IclubSupplierType.class.getSimpleName());
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

	public IclubSupplierTypeDAO getIclubSupplierTypeDAO() {
		return iclubSupplierTypeDAO;
	}

	public void setIclubSupplierTypeDAO(IclubSupplierTypeDAO iclubSupplierTypeDAO) {
		this.iclubSupplierTypeDAO = iclubSupplierTypeDAO;
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
