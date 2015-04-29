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

import za.co.iclub.pss.orm.bean.IclubClaim;
import za.co.iclub.pss.orm.bean.IclubPayment;
import za.co.iclub.pss.orm.bean.IclubPolicy;
import za.co.iclub.pss.orm.dao.IclubAccountDAO;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubNamedQueryDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.orm.dao.IclubPolicyDAO;
import za.co.iclub.pss.orm.dao.IclubPolicyStatusDAO;
import za.co.iclub.pss.orm.dao.IclubQuoteDAO;
import za.co.iclub.pss.ws.model.IclubPolicyModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubPolicyService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubPolicyService {

	protected static final Logger LOGGER = Logger.getLogger(IclubPolicyService.class);
	private IclubCommonDAO iclubCommonDAO;
	private IclubPolicyDAO iclubPolicyDAO;
	private IclubPersonDAO iclubPersonDAO;
	private IclubPolicyStatusDAO iclubPolicyStatusDAO;
	private IclubQuoteDAO iclubQuoteDAO;
	private IclubAccountDAO iclubAccountDAO;
	private IclubNamedQueryDAO iclubNamedQueryDAO;

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubPolicyModel model) {
		try {
			IclubPolicy iCP = new IclubPolicy();

			iCP.setPId(model.getPId());
			iCP.setPProrataPrm(model.getPProrataPrm());
			iCP.setPPremium(model.getPPremium());
			iCP.setPNumber(model.getPNumber());
			iCP.setPDebitDt(model.getPDebitDt());
			iCP.setPCrtdDt(model.getPCrtdDt());
			iCP.setIclubPolicyStatus(model.getIclubPolicyStatus() != null ? iclubPolicyStatusDAO.findById(model.getIclubPolicyStatus()) : null);
			iCP.setIclubQuote(model.getIclubQuote() != null ? iclubQuoteDAO.findById(model.getIclubQuote()) : null);
			iCP.setIclubAccount(model.getIclubAccount() != null ? iclubAccountDAO.findById(model.getIclubAccount()) : null);
			iCP.setIclubPerson(model.getIclubPerson() != null ? iclubPersonDAO.findById(model.getIclubPerson()) : null);
			iclubPolicyDAO.save(iCP);

			LOGGER.info("Save Success with ID :: " + iCP.getPId());

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
	public ResponseModel mod(IclubPolicyModel model) {
		try {
			IclubPolicy iCP = new IclubPolicy();

			iCP.setPId(model.getPId());
			iCP.setPProrataPrm(model.getPProrataPrm());
			iCP.setPPremium(model.getPPremium());
			iCP.setPNumber(model.getPNumber());
			iCP.setPDebitDt(model.getPDebitDt());
			iCP.setPCrtdDt(model.getPCrtdDt());
			iCP.setIclubPolicyStatus(model.getIclubPolicyStatus() != null ? iclubPolicyStatusDAO.findById(model.getIclubPolicyStatus()) : null);
			iCP.setIclubQuote(model.getIclubQuote() != null ? iclubQuoteDAO.findById(model.getIclubQuote()) : null);
			iCP.setIclubAccount(model.getIclubAccount() != null ? iclubAccountDAO.findById(model.getIclubAccount()) : null);
			iCP.setIclubPerson(model.getIclubPerson() != null ? iclubPersonDAO.findById(model.getIclubPerson()) : null);

			iclubPolicyDAO.merge(iCP);

			LOGGER.info("Merge Success with ID :: " + model.getPId());

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
			iclubPolicyDAO.delete(iclubPolicyDAO.findById(id));
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
	public <T extends IclubPolicyModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubPolicyDAO.findAll();
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubPolicy iCP = (IclubPolicy) object;

					IclubPolicyModel model = new IclubPolicyModel();

					model.setPId(iCP.getPId());
					model.setPProrataPrm(iCP.getPProrataPrm());
					model.setPPremium(iCP.getPPremium());
					model.setPNumber(iCP.getPNumber());
					model.setPDebitDt(iCP.getPDebitDt());
					model.setPCrtdDt(iCP.getPCrtdDt());
					model.setIclubAccount(iCP.getIclubAccount() != null ? (iCP.getIclubAccount().getAId()) : null);
					model.setIclubQuote(iCP.getIclubQuote() != null ? (iCP.getIclubQuote().getQId()) : null);
					model.setIclubPolicyStatus(iCP.getIclubPolicyStatus() != null ? (iCP.getIclubPolicyStatus().getPsId()) : null);
					model.setIclubPerson(iCP.getIclubPerson() != null ? (iCP.getIclubPerson().getPId()) : null);
					model.setIclubPolicyStatus(iCP.getIclubPolicyStatus() != null ? (iCP.getIclubPolicyStatus().getPsId()) : null);

					if (iCP.getIclubClaims() != null && iCP.getIclubClaims().size() > 0) {
						String[] claims = new String[iCP.getIclubClaims().size()];
						int i = 0;
						for (IclubClaim claim : iCP.getIclubClaims()) {
							claims[i] = claim.getCId();
							i++;
						}
						model.setIclubClaims(claims);
					}

					if (iCP.getIclubPayments() != null && iCP.getIclubPayments().size() > 0) {
						String[] payments = new String[iCP.getIclubPayments().size()];
						int i = 0;
						for (IclubPayment payment : iCP.getIclubPayments()) {
							payments[i] = payment.getPId();
							i++;
						}
						model.setIclubClaims(payments);
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
	public <T extends IclubPolicyModel> List<T> getByUser(@PathParam("user") String user) {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubNamedQueryDAO.findByUser(user, IclubPolicy.class.getSimpleName());
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubPolicy iCP = (IclubPolicy) object;

					IclubPolicyModel model = new IclubPolicyModel();

					model.setPId(iCP.getPId());
					model.setPProrataPrm(iCP.getPProrataPrm());
					model.setPPremium(iCP.getPPremium());
					model.setPNumber(iCP.getPNumber());
					model.setPDebitDt(iCP.getPDebitDt());
					model.setPCrtdDt(iCP.getPCrtdDt());
					model.setIclubAccount(iCP.getIclubAccount() != null ? (iCP.getIclubAccount().getAId()) : null);
					model.setIclubQuote(iCP.getIclubQuote() != null ? (iCP.getIclubQuote().getQId()) : null);
					model.setIclubPolicyStatus(iCP.getIclubPolicyStatus() != null ? (iCP.getIclubPolicyStatus().getPsId()) : null);
					model.setIclubPerson(iCP.getIclubPerson() != null ? (iCP.getIclubPerson().getPId()) : null);
					model.setIclubPolicyStatus(iCP.getIclubPolicyStatus() != null ? (iCP.getIclubPolicyStatus().getPsId()) : null);

					if (iCP.getIclubClaims() != null && iCP.getIclubClaims().size() > 0) {
						String[] claims = new String[iCP.getIclubClaims().size()];
						int i = 0;
						for (IclubClaim claim : iCP.getIclubClaims()) {
							claims[i] = claim.getCId();
							i++;
						}
						model.setIclubClaims(claims);
					}

					if (iCP.getIclubPayments() != null && iCP.getIclubPayments().size() > 0) {
						String[] payments = new String[iCP.getIclubPayments().size()];
						int i = 0;
						for (IclubPayment payment : iCP.getIclubPayments()) {
							payments[i] = payment.getPId();
							i++;
						}
						model.setIclubClaims(payments);
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
	@Path("/get/policyStauts/{statusId}")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public <T extends IclubPolicyModel> List<T> getByPolicyStatus(@PathParam("statusId") String statusId) {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubNamedQueryDAO.getIclubPolicyByPolicyStatus(statusId);
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubPolicy iCP = (IclubPolicy) object;

					IclubPolicyModel model = new IclubPolicyModel();

					model.setPId(iCP.getPId());
					model.setPProrataPrm(iCP.getPProrataPrm());
					model.setPPremium(iCP.getPPremium());
					model.setPNumber(iCP.getPNumber());
					model.setPDebitDt(iCP.getPDebitDt());
					model.setPCrtdDt(iCP.getPCrtdDt());
					model.setIclubAccount(iCP.getIclubAccount() != null ? (iCP.getIclubAccount().getAId()) : null);
					model.setIclubQuote(iCP.getIclubQuote() != null ? (iCP.getIclubQuote().getQId()) : null);
					model.setIclubPolicyStatus(iCP.getIclubPolicyStatus() != null ? (iCP.getIclubPolicyStatus().getPsId()) : null);
					model.setIclubPerson(iCP.getIclubPerson() != null ? (iCP.getIclubPerson().getPId()) : null);
					model.setIclubPolicyStatus(iCP.getIclubPolicyStatus() != null ? (iCP.getIclubPolicyStatus().getPsId()) : null);

					if (iCP.getIclubClaims() != null && iCP.getIclubClaims().size() > 0) {
						String[] claims = new String[iCP.getIclubClaims().size()];
						int i = 0;
						for (IclubClaim claim : iCP.getIclubClaims()) {
							claims[i] = claim.getCId();
							i++;
						}
						model.setIclubClaims(claims);
					}

					if (iCP.getIclubPayments() != null && iCP.getIclubPayments().size() > 0) {
						String[] payments = new String[iCP.getIclubPayments().size()];
						int i = 0;
						for (IclubPayment payment : iCP.getIclubPayments()) {
							payments[i] = payment.getPId();
							i++;
						}
						model.setIclubClaims(payments);
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
	public IclubPolicyModel getById(@PathParam("id") String id) {
		IclubPolicyModel model = new IclubPolicyModel();
		try {
			IclubPolicy bean = iclubPolicyDAO.findById(id);

			model.setPId(bean.getPId());
			model.setPProrataPrm(bean.getPProrataPrm());
			model.setPPremium(bean.getPPremium());
			model.setPNumber(bean.getPNumber());
			model.setPDebitDt(bean.getPDebitDt());
			model.setPCrtdDt(bean.getPCrtdDt());
			model.setIclubAccount(bean.getIclubAccount() != null ? (bean.getIclubAccount().getAId()) : null);
			model.setIclubQuote(bean.getIclubQuote() != null ? (bean.getIclubQuote().getQId()) : null);
			model.setIclubPolicyStatus(bean.getIclubPolicyStatus() != null ? (bean.getIclubPolicyStatus().getPsId()) : null);
			model.setIclubPerson(bean.getIclubPerson() != null ? (bean.getIclubPerson().getPId()) : null);
			model.setIclubPolicyStatus(bean.getIclubPolicyStatus() != null ? (bean.getIclubPolicyStatus().getPsId()) : null);

			if (bean.getIclubClaims() != null && bean.getIclubClaims().size() > 0) {
				String[] claims = new String[bean.getIclubClaims().size()];
				int i = 0;
				for (IclubClaim claim : bean.getIclubClaims()) {
					claims[i] = claim.getCId();
					i++;
				}
				model.setIclubClaims(claims);
			}

			if (bean.getIclubPayments() != null && bean.getIclubPayments().size() > 0) {
				String[] payments = new String[bean.getIclubPayments().size()];
				int i = 0;
				for (IclubPayment payment : bean.getIclubPayments()) {
					payments[i] = payment.getPId();
					i++;
				}
				model.setIclubClaims(payments);
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return model;
	}

	@GET
	@Path("/getByQuoteId/{quoteId}")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public IclubPolicyModel getByQuoteIdId(@PathParam("quoteId") String quoteId) {
		IclubPolicyModel model = new IclubPolicyModel();
		try {
			IclubPolicy bean = iclubNamedQueryDAO.findIclubPlolicyByQuoteId(quoteId);

			if (bean != null) {
				model.setPId(bean.getPId());
				model.setPProrataPrm(bean.getPProrataPrm());
				model.setPPremium(bean.getPPremium());
				model.setPNumber(bean.getPNumber());
				model.setPDebitDt(bean.getPDebitDt());
				model.setPCrtdDt(bean.getPCrtdDt());
				model.setIclubAccount(bean.getIclubAccount() != null ? (bean.getIclubAccount().getAId()) : null);
				model.setIclubQuote(bean.getIclubQuote() != null ? (bean.getIclubQuote().getQId()) : null);
				model.setIclubPolicyStatus(bean.getIclubPolicyStatus() != null ? (bean.getIclubPolicyStatus().getPsId()) : null);
				model.setIclubPerson(bean.getIclubPerson() != null ? (bean.getIclubPerson().getPId()) : null);
				model.setIclubPolicyStatus(bean.getIclubPolicyStatus() != null ? (bean.getIclubPolicyStatus().getPsId()) : null);

				if (bean.getIclubClaims() != null && bean.getIclubClaims().size() > 0) {
					String[] claims = new String[bean.getIclubClaims().size()];
					int i = 0;
					for (IclubClaim claim : bean.getIclubClaims()) {
						claims[i] = claim.getCId();
						i++;
					}
					model.setIclubClaims(claims);
				}

				if (bean.getIclubPayments() != null && bean.getIclubPayments().size() > 0) {
					String[] payments = new String[bean.getIclubPayments().size()];
					int i = 0;
					for (IclubPayment payment : bean.getIclubPayments()) {
						payments[i] = payment.getPId();
						i++;
					}
					model.setIclubClaims(payments);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return model;
	}

	public IclubPolicyDAO getIclubPolicyDAO() {
		return iclubPolicyDAO;
	}

	public void setIclubPolicyDAO(IclubPolicyDAO iclubPolicyDAO) {
		this.iclubPolicyDAO = iclubPolicyDAO;
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

	public IclubPolicyStatusDAO getIclubPolicyStatusDAO() {
		return iclubPolicyStatusDAO;
	}

	public void setIclubPolicyStatusDAO(IclubPolicyStatusDAO iclubPolicyStatusDAO) {
		this.iclubPolicyStatusDAO = iclubPolicyStatusDAO;
	}

	public IclubQuoteDAO getIclubQuoteDAO() {
		return iclubQuoteDAO;
	}

	public void setIclubQuoteDAO(IclubQuoteDAO iclubQuoteDAO) {
		this.iclubQuoteDAO = iclubQuoteDAO;
	}

	public IclubAccountDAO getIclubAccountDAO() {
		return iclubAccountDAO;
	}

	public void setIclubAccountDAO(IclubAccountDAO iclubAccountDAO) {
		this.iclubAccountDAO = iclubAccountDAO;
	}

	public IclubNamedQueryDAO getIclubNamedQueryDAO() {
		return iclubNamedQueryDAO;
	}

	public void setIclubNamedQueryDAO(IclubNamedQueryDAO iclubNamedQueryDAO) {
		this.iclubNamedQueryDAO = iclubNamedQueryDAO;
	}

}
