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

import za.co.iclub.pss.orm.bean.IclubProductType;
import za.co.iclub.pss.orm.bean.IclubQuote;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubNamedQueryDAO;
import za.co.iclub.pss.orm.dao.IclubProductTypeDAO;
import za.co.iclub.pss.ws.model.IclubProductTypeModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubProductTypeService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubProductTypeService {

	protected static final Logger LOGGER = Logger.getLogger(IclubProductTypeService.class);
	private IclubCommonDAO iclubCommonDAO;
	private IclubProductTypeDAO iclubProductTypeDAO;
	private IclubNamedQueryDAO iclubNamedQueryDAO;

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubProductTypeModel model) {
		try {
			IclubProductType iPt = new IclubProductType();

			iPt.setPtId(iclubCommonDAO.getNextId(IclubProductType.class));
			iPt.setPtLongDesc(model.getPtLongDesc());
			iPt.setPtShortDesc(model.getPtShortDesc());
			iPt.setPtStatus(model.getPtStatus());

			iclubProductTypeDAO.save(iPt);

			LOGGER.info("Save Success with ID :: " + iPt.getPtId());

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
	public ResponseModel mod(IclubProductTypeModel model) {
		try {
			IclubProductType iPt = new IclubProductType();

			iPt.setPtId(model.getPtId());
			iPt.setPtLongDesc(model.getPtLongDesc());
			iPt.setPtShortDesc(model.getPtShortDesc());
			iPt.setPtStatus(model.getPtStatus());

			iclubProductTypeDAO.merge(iPt);

			LOGGER.info("Merge Success with ID :: " + model.getPtId());

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
			iclubProductTypeDAO.delete(iclubProductTypeDAO.findById(id));
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
	public <T extends IclubProductTypeModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubProductTypeDAO.findAll();

			for (Object object : batmod) {
				IclubProductType iPt = (IclubProductType) object;

				IclubProductTypeModel model = new IclubProductTypeModel();

				model.setPtId(iPt.getPtId());
				model.setPtLongDesc(iPt.getPtLongDesc());
				model.setPtShortDesc(iPt.getPtShortDesc());
				model.setPtStatus(iPt.getPtStatus());

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
	public IclubProductTypeModel getById(@PathParam("id") Long id) {
		IclubProductTypeModel model = new IclubProductTypeModel();
		try {
			IclubProductType bean = iclubProductTypeDAO.findById(id);

			model.setPtId(bean.getPtId());
			model.setPtLongDesc(bean.getPtLongDesc());
			model.setPtShortDesc(bean.getPtShortDesc());
			model.setPtStatus(bean.getPtStatus());

			if (bean.getIclubQuotes() != null && bean.getIclubQuotes().size() > 0) {
				String[] quotes = new String[bean.getIclubQuotes().size()];
				int i = 0;
				for (IclubQuote iclubQuote : bean.getIclubQuotes()) {
					quotes[i] = iclubQuote.getQId();
					i++;
				}
				model.setIclubQuotes(quotes);
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
			List data = iclubNamedQueryDAO.getBySD(val, id, IclubProductType.class.getSimpleName());
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

	public IclubProductTypeDAO getIclubProductTypeDAO() {
		return iclubProductTypeDAO;
	}

	public void setIclubProductTypeDAO(IclubProductTypeDAO iclubProductTypeDAO) {
		this.iclubProductTypeDAO = iclubProductTypeDAO;
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
