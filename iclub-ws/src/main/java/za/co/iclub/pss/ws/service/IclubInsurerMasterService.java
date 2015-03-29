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

import za.co.iclub.pss.orm.bean.IclubInsurerMaster;
import za.co.iclub.pss.orm.bean.IclubQuote;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubInsurerMasterDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.ws.model.IclubInsurerMasterModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubInsurerMasterService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubInsurerMasterService {

	private static final Logger LOGGER = Logger.getLogger(IclubInsurerMasterService.class);
	private IclubInsurerMasterDAO iclubInsurerMasterDAO;
	private IclubCommonDAO iclubCommonDAO;
	private IclubPersonDAO iclubPersonDAO;

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional
	public ResponseModel add(IclubInsurerMasterModel model) {
		try {

			IclubInsurerMaster iCIm = new IclubInsurerMaster();

			iCIm.setImId(iclubCommonDAO.getNextId(IclubInsurerMaster.class));
			iCIm.setImName(model.getImName());
			iCIm.setImLat(model.getImLat());
			iCIm.setImLong(model.getImLong());
			iCIm.setImTradeName(model.getImTradeName());
			iCIm.setImRegNum(model.getImRegNum());
			iCIm.setImLocation(model.getImLocation());
			iCIm.setImCrtdDt(model.getImCrtdDt());
			iCIm.setIclubPerson(iclubPersonDAO.findById(model.getIclubPerson()));

			iclubInsurerMasterDAO.save(iCIm);

			LOGGER.info("Save Success with ID :: " + iCIm.getImId().longValue());

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
	public ResponseModel mod(IclubInsurerMasterModel model) {
		try {
			IclubInsurerMaster iCIm = new IclubInsurerMaster();

			iCIm.setImId(model.getImId());
			iCIm.setImName(model.getImName());
			iCIm.setImLat(model.getImLat());
			iCIm.setImLong(model.getImLong());
			iCIm.setImTradeName(model.getImTradeName());
			iCIm.setImRegNum(model.getImRegNum());
			iCIm.setImLocation(model.getImLocation());
			iCIm.setImCrtdDt(model.getImCrtdDt());
			iCIm.setIclubPerson(iclubPersonDAO.findById(model.getIclubPerson()));

			iclubInsurerMasterDAO.merge(iCIm);

			LOGGER.info("Save Success with ID :: " + model.getImId().longValue());

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
			iclubInsurerMasterDAO.delete(iclubInsurerMasterDAO.findById(id));
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
	public <T extends IclubInsurerMasterModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubInsurerMasterDAO.findAll();

			for (Object object : batmod) {
				IclubInsurerMaster iCIMaster = (IclubInsurerMaster) object;
				IclubInsurerMasterModel iCIm = new IclubInsurerMasterModel();

				iCIm.setImId(iCIMaster.getImId());
				iCIm.setImName(iCIMaster.getImName());
				iCIm.setImLat(iCIMaster.getImLat());
				iCIm.setImLong(iCIMaster.getImLong());
				iCIm.setImTradeName(iCIMaster.getImTradeName());
				iCIm.setImRegNum(iCIMaster.getImRegNum());
				iCIm.setImLocation(iCIMaster.getImLocation());
				iCIm.setImCrtdDt(iCIMaster.getImCrtdDt());
				iCIm.setIclubPerson(iCIMaster.getIclubPerson() != null ? iCIMaster.getIclubPerson().getPId() : null);

				if (iCIMaster.getIclubQuotes() != null && iCIMaster.getIclubQuotes().size() > 0) {
					String[] iclubQuotes = new String[iCIMaster.getIclubQuotes().size()];
					int i = 0;
					for (IclubQuote iclubQuote : iCIMaster.getIclubQuotes()) {
						iclubQuotes[i] = iclubQuote.getQId();
						i++;
					}
					iCIm.setIclubQuotes(iclubQuotes);
				}

				ret.add((T) iCIm);
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
	public <T extends IclubInsurerMasterModel> List<T> getByUser(@PathParam("user") String user) {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubInsurerMasterDAO.findByUser(user);

			for (Object object : batmod) {
				IclubInsurerMaster iCIMaster = (IclubInsurerMaster) object;
				IclubInsurerMasterModel iCIm = new IclubInsurerMasterModel();

				iCIm.setImId(iCIMaster.getImId());
				iCIm.setImName(iCIMaster.getImName());
				iCIm.setImLat(iCIMaster.getImLat());
				iCIm.setImLong(iCIMaster.getImLong());
				iCIm.setImTradeName(iCIMaster.getImTradeName());
				iCIm.setImRegNum(iCIMaster.getImRegNum());
				iCIm.setImLocation(iCIMaster.getImLocation());
				iCIm.setImCrtdDt(iCIMaster.getImCrtdDt());
				iCIm.setIclubPerson(iCIMaster.getIclubPerson() != null ? iCIMaster.getIclubPerson().getPId() : null);
				if (iCIMaster.getIclubQuotes() != null && iCIMaster.getIclubQuotes().size() > 0) {
					String[] iclubQuotes = new String[iCIMaster.getIclubQuotes().size()];
					int i = 0;
					for (IclubQuote iclubQuote : iCIMaster.getIclubQuotes()) {
						iclubQuotes[i] = iclubQuote.getQId();
						i++;
					}
					iCIm.setIclubQuotes(iclubQuotes);
				}

				ret.add((T) iCIm);
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
	public IclubInsurerMasterModel getById(@PathParam("id") Long id) {
		IclubInsurerMasterModel model = new IclubInsurerMasterModel();
		try {
			IclubInsurerMaster bean = iclubInsurerMasterDAO.findById(id);

			model.setImId(bean.getImId());
			model.setImName(bean.getImName());
			model.setImLat(bean.getImLat());
			model.setImLong(bean.getImLong());
			model.setImTradeName(bean.getImTradeName());
			model.setImRegNum(bean.getImRegNum());
			model.setImLocation(bean.getImLocation());
			model.setImCrtdDt(bean.getImCrtdDt());
			model.setIclubPerson(bean.getIclubPerson() != null ? bean.getIclubPerson().getPId() : null);

			if (bean.getIclubQuotes() != null && bean.getIclubQuotes().size() > 0) {
				String[] iclubQuotes = new String[bean.getIclubQuotes().size()];
				int i = 0;
				for (IclubQuote iclubQuote : bean.getIclubQuotes()) {
					iclubQuotes[i] = iclubQuote.getQId();
					i++;
				}
				model.setIclubQuotes(iclubQuotes);
			}

		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return model;
	}

	public IclubInsurerMasterDAO getIclubInsurerMasterDAO() {
		return iclubInsurerMasterDAO;
	}

	public void setIclubInsurerMasterDAO(IclubInsurerMasterDAO iclubInsurerMasterDAO) {
		this.iclubInsurerMasterDAO = iclubInsurerMasterDAO;
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

}
