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

import za.co.iclub.pss.orm.bean.IclubPolicy;
import za.co.iclub.pss.orm.bean.IclubQuote;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubCoverTypeDAO;
import za.co.iclub.pss.orm.dao.IclubInsurerMasterDAO;
import za.co.iclub.pss.orm.dao.IclubNamedQueryDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.orm.dao.IclubProductTypeDAO;
import za.co.iclub.pss.orm.dao.IclubQuoteDAO;
import za.co.iclub.pss.orm.dao.IclubQuoteStatusDAO;
import za.co.iclub.pss.ws.model.IclubQuoteModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubQuoteService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubQuoteService {

	protected static final Logger LOGGER = Logger.getLogger(IclubQuoteService.class);
	private IclubCommonDAO iclubCommonDAO;
	private IclubQuoteDAO iclubQuoteDAO;
	private IclubPersonDAO iclubPersonDAO;
	private IclubProductTypeDAO iclubProductTypeDAO;
	private IclubInsurerMasterDAO iclubInsurerMasterDAO;
	private IclubCoverTypeDAO iclubCoverTypeDAO;
	private IclubQuoteStatusDAO iclubQuoteStatusDAO;
	private IclubNamedQueryDAO iclubNamedQueryDAO;

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubQuoteModel model) {
		try {
			IclubQuote iCQ = new IclubQuote();

			iCQ.setQId(model.getQId());
			iCQ.setQCrtdDt(model.getQCrtdDt());
			iCQ.setQIsMatched(model.getQIsMatched());
			iCQ.setQPrevPremium(model.getQPrevPremium());
			iCQ.setQValidUntil(model.getQValidUntil());
			iCQ.setQMobile(model.getQMobile());
			iCQ.setQEmail(model.getQEmail());
			iCQ.setQGenPremium(model.getQGenPremium());
			iCQ.setQNumItems(model.getQNumItems());
			iCQ.setQGenDt(model.getQGenDt());
			iCQ.setQNumber(model.getQNumber());
			iCQ.setIclubInsurerMaster(model.getIclubInsurerMaster() != null ? iclubInsurerMasterDAO.findById(model.getIclubInsurerMaster()) : null);
			iCQ.setIclubCoverType(model.getIclubCoverType() != null ? iclubCoverTypeDAO.findById(model.getIclubCoverType()) : null);
			iCQ.setIclubQuoteStatus(model.getIclubQuoteStatus() != null ? iclubQuoteStatusDAO.findById(model.getIclubQuoteStatus()) : null);
			iCQ.setIclubProductType(model.getIclubProductType() != null ? iclubProductTypeDAO.findById(model.getIclubProductType()) : null);
			iCQ.setIclubPersonByQPersonId(model.getIclubPersonByQPersonId() != null && !model.getIclubPersonByQPersonId().trim().equalsIgnoreCase("") ? iclubPersonDAO.findById(model.getIclubPersonByQPersonId()) : null);
			iCQ.setIclubPersonByQCrtdBy(model.getIclubPersonByQCrtdBy() != null && !model.getIclubPersonByQCrtdBy().trim().equalsIgnoreCase("") ? iclubPersonDAO.findById(model.getIclubPersonByQCrtdBy()) : null);

			iclubQuoteDAO.save(iCQ);

			LOGGER.info("Save Success with ID :: " + iCQ.getQId());

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
	public ResponseModel mod(IclubQuoteModel model) {
		try {
			IclubQuote iCQ = new IclubQuote();

			iCQ.setQId(model.getQId());
			iCQ.setQCrtdDt(model.getQCrtdDt());
			iCQ.setQIsMatched(model.getQIsMatched());
			iCQ.setQPrevPremium(model.getQPrevPremium());
			iCQ.setQValidUntil(model.getQValidUntil());
			iCQ.setQMobile(model.getQMobile());
			iCQ.setQEmail(model.getQEmail());
			iCQ.setQGenPremium(model.getQGenPremium());
			iCQ.setQNumItems(model.getQNumItems());
			iCQ.setQGenDt(model.getQGenDt());
			iCQ.setQNumber(model.getQNumber());
			iCQ.setIclubInsurerMaster(model.getIclubInsurerMaster() != null ? iclubInsurerMasterDAO.findById(model.getIclubInsurerMaster()) : null);
			iCQ.setIclubCoverType(model.getIclubCoverType() != null ? iclubCoverTypeDAO.findById(model.getIclubCoverType()) : null);
			iCQ.setIclubQuoteStatus(model.getIclubQuoteStatus() != null ? iclubQuoteStatusDAO.findById(model.getIclubQuoteStatus()) : null);
			iCQ.setIclubProductType(model.getIclubProductType() != null ? iclubProductTypeDAO.findById(model.getIclubProductType()) : null);
			iCQ.setIclubPersonByQPersonId(model.getIclubPersonByQPersonId() != null && !model.getIclubPersonByQPersonId().trim().equalsIgnoreCase("") ? iclubPersonDAO.findById(model.getIclubPersonByQPersonId()) : null);
			iCQ.setIclubPersonByQCrtdBy(model.getIclubPersonByQCrtdBy() != null && !model.getIclubPersonByQCrtdBy().trim().equalsIgnoreCase("") ? iclubPersonDAO.findById(model.getIclubPersonByQCrtdBy()) : null);

			iclubQuoteDAO.merge(iCQ);

			LOGGER.info("Merge Success with ID :: " + model.getQId());

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
	public Response del(@PathParam("id") String id) {
		try {
			iclubQuoteDAO.delete(iclubQuoteDAO.findById(id));
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
	public <T extends IclubQuoteModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubQuoteDAO.findAll();

			for (Object object : batmod) {
				IclubQuote iCQ = (IclubQuote) object;

				IclubQuoteModel model = new IclubQuoteModel();

				model.setQId(iCQ.getQId());
				model.setQCrtdDt(iCQ.getQCrtdDt());
				model.setQIsMatched(iCQ.getQIsMatched());
				model.setQPrevPremium(iCQ.getQPrevPremium());
				model.setQValidUntil(iCQ.getQValidUntil());
				model.setQMobile(iCQ.getQMobile());
				model.setQEmail(iCQ.getQEmail());
				model.setQGenPremium(iCQ.getQGenPremium());
				model.setQNumItems(iCQ.getQNumItems());
				model.setQGenDt(iCQ.getQGenDt());
				model.setQNumber(iCQ.getQNumber());
				model.setIclubPersonByQCrtdBy(iCQ.getIclubPersonByQCrtdBy() != null ? (iCQ.getIclubPersonByQCrtdBy().getPId()) : null);
				model.setIclubProductType(iCQ.getIclubProductType() != null ? (iCQ.getIclubProductType().getPtId()) : null);
				model.setIclubInsurerMaster(iCQ.getIclubInsurerMaster() != null ? (iCQ.getIclubInsurerMaster().getImId()) : null);
				model.setIclubCoverType(iCQ.getIclubCoverType() != null ? (iCQ.getIclubCoverType().getCtId()) : null);
				model.setIclubQuoteStatus(iCQ.getIclubQuoteStatus() != null ? (iCQ.getIclubQuoteStatus().getQsId()) : null);
				model.setIclubPersonByQPersonId(iCQ.getIclubPersonByQPersonId() != null ? (iCQ.getIclubPersonByQPersonId().getPId()) : null);

				if (iCQ.getIclubPolicies() != null && iCQ.getIclubPolicies().size() > 0) {
					String[] policies = new String[iCQ.getIclubPolicies().size()];
					int i = 0;
					for (IclubPolicy policy : iCQ.getIclubPolicies()) {
						policies[i] = policy.getPId();
						i++;
					}
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
	public <T extends IclubQuoteModel> List<T> getByUser(@PathParam("user") String user) {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubNamedQueryDAO.findByUser(user, IclubQuote.class.getSimpleName());

			for (Object object : batmod) {
				IclubQuote iCQ = (IclubQuote) object;

				IclubQuoteModel model = new IclubQuoteModel();

				model.setQId(iCQ.getQId());
				model.setQCrtdDt(iCQ.getQCrtdDt());
				model.setQIsMatched(iCQ.getQIsMatched());
				model.setQPrevPremium(iCQ.getQPrevPremium());
				model.setQValidUntil(iCQ.getQValidUntil());
				model.setQMobile(iCQ.getQMobile());
				model.setQEmail(iCQ.getQEmail());
				model.setQGenPremium(iCQ.getQGenPremium());
				model.setQNumItems(iCQ.getQNumItems());
				model.setQGenDt(iCQ.getQGenDt());
				model.setQNumber(iCQ.getQNumber());
				model.setIclubPersonByQCrtdBy(iCQ.getIclubPersonByQCrtdBy() != null ? (iCQ.getIclubPersonByQCrtdBy().getPId()) : null);
				model.setIclubProductType(iCQ.getIclubProductType() != null ? (iCQ.getIclubProductType().getPtId()) : null);
				model.setIclubProductType(iCQ.getIclubProductType() != null ? (iCQ.getIclubProductType().getPtId()) : null);
				model.setIclubInsurerMaster(iCQ.getIclubInsurerMaster() != null ? (iCQ.getIclubInsurerMaster().getImId()) : null);
				model.setIclubCoverType(iCQ.getIclubCoverType() != null ? (iCQ.getIclubCoverType().getCtId()) : null);
				model.setIclubQuoteStatus(iCQ.getIclubQuoteStatus() != null ? (iCQ.getIclubQuoteStatus().getQsId()) : null);
				model.setIclubPersonByQPersonId(iCQ.getIclubPersonByQPersonId() != null ? (iCQ.getIclubPersonByQPersonId().getPId()) : null);

				if (iCQ.getIclubPolicies() != null && iCQ.getIclubPolicies().size() > 0) {
					String[] policies = new String[iCQ.getIclubPolicies().size()];
					int i = 0;
					for (IclubPolicy policy : iCQ.getIclubPolicies()) {
						policies[i] = policy.getPId();
						i++;
					}
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
	public IclubQuoteModel getById(@PathParam("id") String id) {
		IclubQuoteModel model = new IclubQuoteModel();
		try {
			IclubQuote bean = iclubQuoteDAO.findById(id);

			model.setQId(bean.getQId());
			model.setQCrtdDt(bean.getQCrtdDt());
			model.setQIsMatched(bean.getQIsMatched());
			model.setQPrevPremium(bean.getQPrevPremium());
			model.setQValidUntil(bean.getQValidUntil());
			model.setQMobile(bean.getQMobile());
			model.setQEmail(bean.getQEmail());
			model.setQGenPremium(bean.getQGenPremium());
			model.setQNumItems(bean.getQNumItems());
			model.setQGenDt(bean.getQGenDt());
			model.setQNumber(bean.getQNumber());
			model.setIclubPersonByQCrtdBy(bean.getIclubPersonByQCrtdBy() != null ? (bean.getIclubPersonByQCrtdBy().getPId()) : null);
			model.setIclubProductType(bean.getIclubProductType() != null ? (bean.getIclubProductType().getPtId()) : null);
			model.setIclubProductType(bean.getIclubProductType() != null ? (bean.getIclubProductType().getPtId()) : null);
			model.setIclubInsurerMaster(bean.getIclubInsurerMaster() != null ? (bean.getIclubInsurerMaster().getImId()) : null);
			model.setIclubCoverType(bean.getIclubCoverType() != null ? (bean.getIclubCoverType().getCtId()) : null);
			model.setIclubQuoteStatus(bean.getIclubQuoteStatus() != null ? (bean.getIclubQuoteStatus().getQsId()) : null);
			model.setIclubPersonByQPersonId(bean.getIclubPersonByQPersonId() != null ? (bean.getIclubPersonByQPersonId().getPId()) : null);

			if (bean.getIclubPolicies() != null && bean.getIclubPolicies().size() > 0) {
				String[] policies = new String[bean.getIclubPolicies().size()];
				int i = 0;
				for (IclubPolicy policy : bean.getIclubPolicies()) {
					policies[i] = policy.getPId();
					i++;
				}
			}

		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return model;
	}

	public IclubQuoteDAO getIclubQuoteDAO() {
		return iclubQuoteDAO;
	}

	public void setIclubQuoteDAO(IclubQuoteDAO iclubQuoteDAO) {
		this.iclubQuoteDAO = iclubQuoteDAO;
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

	public IclubProductTypeDAO getIclubProductTypeDAO() {
		return iclubProductTypeDAO;
	}

	public void setIclubProductTypeDAO(IclubProductTypeDAO iclubProductTypeDAO) {
		this.iclubProductTypeDAO = iclubProductTypeDAO;
	}

	public IclubInsurerMasterDAO getIclubInsurerMasterDAO() {
		return iclubInsurerMasterDAO;
	}

	public void setIclubInsurerMasterDAO(IclubInsurerMasterDAO iclubInsurerMasterDAO) {
		this.iclubInsurerMasterDAO = iclubInsurerMasterDAO;
	}

	public IclubCoverTypeDAO getIclubCoverTypeDAO() {
		return iclubCoverTypeDAO;
	}

	public void setIclubCoverTypeDAO(IclubCoverTypeDAO iclubCoverTypeDAO) {
		this.iclubCoverTypeDAO = iclubCoverTypeDAO;
	}

	public IclubQuoteStatusDAO getIclubQuoteStatusDAO() {
		return iclubQuoteStatusDAO;
	}

	public void setIclubQuoteStatusDAO(IclubQuoteStatusDAO iclubQuoteStatusDAO) {
		this.iclubQuoteStatusDAO = iclubQuoteStatusDAO;
	}

	public IclubNamedQueryDAO getIclubNamedQueryDAO() {
		return iclubNamedQueryDAO;
	}

	public void setIclubNamedQueryDAO(IclubNamedQueryDAO iclubNamedQueryDAO) {
		this.iclubNamedQueryDAO = iclubNamedQueryDAO;
	}

}
