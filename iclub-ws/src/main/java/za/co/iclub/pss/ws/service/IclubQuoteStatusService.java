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

import za.co.iclub.pss.orm.bean.IclubQuote;
import za.co.iclub.pss.orm.bean.IclubQuoteStatus;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubQuoteStatusDAO;
import za.co.iclub.pss.ws.model.IclubQuoteStatusModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubQuoteStatusService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubQuoteStatusService {

	protected static final Logger LOGGER = Logger.getLogger(IclubQuoteStatusService.class);
	private IclubCommonDAO iclubCommonDAO;
	private IclubQuoteStatusDAO iclubQuoteStatusDAO;

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubQuoteStatusModel model) {
		try {
			IclubQuoteStatus iQs = new IclubQuoteStatus();

			iQs.setQsId(iclubCommonDAO.getNextId(IclubQuoteStatus.class));
			iQs.setQsLongDesc(model.getQsLongDesc());
			iQs.setQsShortDesc(model.getQsShortDesc());
			iQs.setQsStatus(model.getQsStatus());

			iclubQuoteStatusDAO.save(iQs);

			LOGGER.info("Save Success with ID :: " + iQs.getQsId());

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
	public ResponseModel mod(IclubQuoteStatusModel model) {
		try {
			IclubQuoteStatus iQs = new IclubQuoteStatus();

			iQs.setQsId(model.getQsId());
			iQs.setQsLongDesc(model.getQsLongDesc());
			iQs.setQsShortDesc(model.getQsShortDesc());
			iQs.setQsStatus(model.getQsStatus());

			iclubQuoteStatusDAO.merge(iQs);

			LOGGER.info("Merge Success with ID :: " + model.getQsId());

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
			iclubQuoteStatusDAO.delete(iclubQuoteStatusDAO.findById(id));
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
	public <T extends IclubQuoteStatusModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubQuoteStatusDAO.findAll();

			for (Object object : batmod) {
				IclubQuoteStatus iQs = (IclubQuoteStatus) object;

				IclubQuoteStatusModel model = new IclubQuoteStatusModel();

				model.setQsId(iQs.getQsId());
				model.setQsLongDesc(iQs.getQsLongDesc());
				model.setQsShortDesc(iQs.getQsShortDesc());
				model.setQsStatus(iQs.getQsStatus());

				if (iQs.getIclubQuotes() != null && iQs.getIclubQuotes().size() > 0) {
					String[] quotes = new String[iQs.getIclubQuotes().size()];
					int i = 0;
					for (IclubQuote quote : iQs.getIclubQuotes()) {
						quotes[i] = quote.getQId();
						i++;
					}
					model.setIclubQuotes(quotes);
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
	public IclubQuoteStatusModel getById(@PathParam("id") Long id) {
		IclubQuoteStatusModel model = new IclubQuoteStatusModel();
		try {
			IclubQuoteStatus bean = iclubQuoteStatusDAO.findById(id);

			model.setQsId(bean.getQsId());
			model.setQsLongDesc(bean.getQsLongDesc());
			model.setQsShortDesc(bean.getQsShortDesc());
			model.setQsStatus(bean.getQsStatus());
			
			if (bean.getIclubQuotes() != null && bean.getIclubQuotes().size() > 0) {
				String[] quotes = new String[bean.getIclubQuotes().size()];
				int i = 0;
				for (IclubQuote quote : bean.getIclubQuotes()) {
					quotes[i] = quote.getQId();
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
			List data = iclubQuoteStatusDAO.getQuoteStatusBySD(val, id);
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

	public IclubQuoteStatusDAO getIclubQuoteStatusDAO() {
		return iclubQuoteStatusDAO;
	}

	public void setIclubQuoteStatusDAO(IclubQuoteStatusDAO iclubQuoteStatusDAO) {
		this.iclubQuoteStatusDAO = iclubQuoteStatusDAO;
	}

	public IclubCommonDAO getIclubCommonDAO() {
		return iclubCommonDAO;
	}

	public void setIclubCommonDAO(IclubCommonDAO iclubCommonDAO) {
		this.iclubCommonDAO = iclubCommonDAO;
	}
}
