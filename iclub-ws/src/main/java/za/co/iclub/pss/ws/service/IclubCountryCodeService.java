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

import za.co.iclub.pss.orm.bean.IclubCountryCode;
import za.co.iclub.pss.orm.dao.IclubBankMasterDAO;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubCountryCodeDAO;
import za.co.iclub.pss.orm.dao.IclubOwnerTypeDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.ws.model.IclubCountryCodeModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubCountryCodeService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubCountryCodeService {

	private static final Logger LOGGER = Logger.getLogger(IclubCountryCodeService.class);
	private IclubCountryCodeDAO iclubCountryCodeDAO;
	private IclubCommonDAO iclubCommonDAO;
	private IclubOwnerTypeDAO iclubOwnerTypeDAO;
	private IclubBankMasterDAO iclubBankMasterDAO;
	private IclubPersonDAO iclubPersonDAO;

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional
	public ResponseModel add(IclubCountryCodeModel model) {
		try {

			IclubCountryCode iCCc = new IclubCountryCode();

			iCCc.setCcId(iclubCommonDAO.getNextId(IclubCountryCode.class).intValue());
			iCCc.setCcId(model.getCcId());
			iCCc.setCcIsoId(model.getCcIsoId());
			iCCc.setCcShortId(model.getCcShortId());
			iCCc.setCcCrtdDt(model.getCcCrtdDt());
			iCCc.setCcName(model.getCcName());
			iCCc.setIclubPerson(iclubPersonDAO.findById(model.getIclubPerson()));

			iclubCountryCodeDAO.save(iCCc);

			LOGGER.info("Save Success with ID :: " + iCCc.getCcId().longValue());

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
	public ResponseModel mod(IclubCountryCodeModel model) {
		try {
			IclubCountryCode iCCc = new IclubCountryCode();

			iCCc.setCcId(model.getCcId());
			iCCc.setCcId(model.getCcId());
			iCCc.setCcIsoId(model.getCcIsoId());
			iCCc.setCcShortId(model.getCcShortId());
			iCCc.setCcCrtdDt(model.getCcCrtdDt());
			iCCc.setCcName(model.getCcName());
			iCCc.setIclubPerson(iclubPersonDAO.findById(model.getIclubPerson()));

			iclubCountryCodeDAO.merge(iCCc);

			LOGGER.info("Save Success with ID :: " + model.getCcId().longValue());

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
	public Response del(@PathParam("id") Integer id) {
		try {
			iclubCountryCodeDAO.delete(iclubCountryCodeDAO.findById(id));
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
	public <T extends IclubCountryCodeModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubCountryCodeDAO.findAll();

			for (Object object : batmod) {
				IclubCountryCode iclubCc = (IclubCountryCode) object;
				IclubCountryCodeModel iCCc = new IclubCountryCodeModel();

				iCCc.setCcId(iclubCc.getCcId());
				iCCc.setCcId(iclubCc.getCcId());
				iCCc.setCcIsoId(iclubCc.getCcIsoId());
				iCCc.setCcShortId(iclubCc.getCcShortId());
				iCCc.setCcCrtdDt(iclubCc.getCcCrtdDt());
				iCCc.setCcName(iclubCc.getCcName());
				iCCc.setIclubPerson(iclubCc.getIclubPerson() != null ? iclubCc.getIclubPerson().getPId() : null);

				ret.add((T) iCCc);
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
	public <T extends IclubCountryCodeModel> List<T> getByUser(@PathParam("user") String user) {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubCountryCodeDAO.findByUser(user);

			for (Object object : batmod) {
				IclubCountryCode iclubCc = (IclubCountryCode) object;
				IclubCountryCodeModel iCCc = new IclubCountryCodeModel();

				iCCc.setCcId(iclubCc.getCcId());
				iCCc.setCcIsoId(iclubCc.getCcIsoId());
				iCCc.setCcShortId(iclubCc.getCcShortId());
				iCCc.setCcCrtdDt(iclubCc.getCcCrtdDt());
				iCCc.setCcName(iclubCc.getCcName());
				iCCc.setIclubPerson(iclubCc.getIclubPerson() != null ? iclubCc.getIclubPerson().getPId() : null);

				ret.add((T) iCCc);
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
	public IclubCountryCodeModel getById(@PathParam("id") Integer id) {
		IclubCountryCodeModel model = new IclubCountryCodeModel();
		try {
			IclubCountryCode bean = iclubCountryCodeDAO.findById(id);

			model.setCcId(bean.getCcId());
			model.setCcIsoId(bean.getCcIsoId());
			model.setCcShortId(bean.getCcShortId());
			model.setCcCrtdDt(bean.getCcCrtdDt());
			model.setCcName(bean.getCcName());
			model.setIclubPerson(bean.getIclubPerson() != null ? bean.getIclubPerson().getPId() : null);

		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return model;
	}

	public IclubCountryCodeDAO getIclubCountryCodeDAO() {
		return iclubCountryCodeDAO;
	}

	public void setIclubCountryCodeDAO(IclubCountryCodeDAO iclubCountryCodeDAO) {
		this.iclubCountryCodeDAO = iclubCountryCodeDAO;
	}

	public IclubCommonDAO getIclubCommonDAO() {
		return iclubCommonDAO;
	}

	public void setIclubCommonDAO(IclubCommonDAO iclubCommonDAO) {
		this.iclubCommonDAO = iclubCommonDAO;
	}

	public IclubOwnerTypeDAO getIclubOwnerTypeDAO() {
		return iclubOwnerTypeDAO;
	}

	public void setIclubOwnerTypeDAO(IclubOwnerTypeDAO iclubOwnerTypeDAO) {
		this.iclubOwnerTypeDAO = iclubOwnerTypeDAO;
	}

	public IclubBankMasterDAO getIclubBankMasterDAO() {
		return iclubBankMasterDAO;
	}

	public void setIclubBankMasterDAO(IclubBankMasterDAO iclubBankMasterDAO) {
		this.iclubBankMasterDAO = iclubBankMasterDAO;
	}

	public IclubPersonDAO getIclubPersonDAO() {
		return iclubPersonDAO;
	}

	public void setIclubPersonDAO(IclubPersonDAO iclubPersonDAO) {
		this.iclubPersonDAO = iclubPersonDAO;
	}

}
