package za.co.iclub.pss.ws.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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

import za.co.iclub.pss.model.ws.IclubCohortModel;
import za.co.iclub.pss.model.ws.IclubCohortSummaryModel;
import za.co.iclub.pss.orm.bean.IclubClaim;
import za.co.iclub.pss.orm.bean.IclubCohort;
import za.co.iclub.pss.orm.bean.IclubPayment;
import za.co.iclub.pss.orm.bean.IclubPolicy;
import za.co.iclub.pss.orm.dao.IclubCohortDAO;
import za.co.iclub.pss.orm.dao.IclubCohortTypeDAO;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubInsuranceItemTypeDAO;
import za.co.iclub.pss.orm.dao.IclubNamedQueryDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.trans.IclubCohortTrans;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubCohortService")
@SuppressWarnings({ "rawtypes", "unchecked", "deprecation" })
public class IclubCohortService {
	
	private static final Logger LOGGER = Logger.getLogger(IclubCohortService.class);
	private IclubCohortDAO iclubCohortDAO;
	private IclubCommonDAO iclubCommonDAO;
	private IclubPersonDAO iclubPersonDAO;
	private IclubNamedQueryDAO iclubNamedQueryDAO;
	private IclubCohortTypeDAO iclubCohortTypeDAO;
	private IclubInsuranceItemTypeDAO iclubInsuranceItemTypeDAO;
	
	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional
	public ResponseModel add(IclubCohortModel model) {
		try {
			
			IclubCohort iCC = IclubCohortTrans.fromWStoORM(model, iclubPersonDAO, iclubCohortTypeDAO, iclubInsuranceItemTypeDAO);
			
			iclubCohortDAO.save(iCC);
			
			LOGGER.info("Save Success with ID :: " + iCC.getCId());
			
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
	
	@POST
	@Path("/addList")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional
	public ResponseModel addList(Collection<? extends IclubCohortModel> models) {
		try {
			
			for (IclubCohortModel model : models) {
				IclubCohort iCC = IclubCohortTrans.fromWStoORM(model, iclubPersonDAO, iclubCohortTypeDAO, iclubInsuranceItemTypeDAO);
				iclubCohortDAO.save(iCC);
				
				LOGGER.info("Save Success with ID :: " + iCC.getCId());
			}
			
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
	public ResponseModel mod(IclubCohortModel model) {
		try {
			IclubCohort iCC = IclubCohortTrans.fromWStoORM(model, iclubPersonDAO, iclubCohortTypeDAO, iclubInsuranceItemTypeDAO);
			iclubCohortDAO.merge(iCC);
			
			LOGGER.info("Save Success with ID :: " + model.getCId());
			
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
	public Response del(@PathParam("id") String id) {
		try {
			iclubCohortDAO.delete(iclubCohortDAO.findById(id));
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
	public <T extends IclubCohortModel> List<T> list() {
		List<T> ret = new ArrayList<T>();
		
		try {
			List batmod = iclubCohortDAO.findAll();
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubCohort bean = (IclubCohort) object;
					IclubCohortModel iCC = IclubCohortTrans.fromORMtoWS(bean);
					
					ret.add((T) iCC);
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
	public <T extends IclubCohortModel> List<T> getByUser(@PathParam("user") String user) {
		List<T> ret = new ArrayList<T>();
		
		try {
			List batmod = iclubNamedQueryDAO.findByUser(user, IclubCohort.class.getSimpleName());
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubCohort bean = (IclubCohort) object;
					IclubCohortModel iCC = IclubCohortTrans.fromORMtoWS(bean);
					
					ret.add((T) iCC);
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
	@Transactional
	public IclubCohortModel getById(@PathParam("id") String id) {
		IclubCohortModel model = new IclubCohortModel();
		try {
			IclubCohort bean = iclubCohortDAO.findById(id);
			
			model = IclubCohortTrans.fromORMtoWS(bean);
			
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return model;
	}
	
	@GET
	@Path("/getCohortSummaryById/{id}")
	@Produces("application/json")
	@Transactional
	public IclubCohortSummaryModel getCohortSummaryById(@PathParam("id") String id) {
		
		IclubCohortSummaryModel cSModel = new IclubCohortSummaryModel();
		
		List batmod = iclubNamedQueryDAO.getIclubPaymentsByCohortId(id, null);
		Date startDate = new Date();
		startDate.setDate(01);
		startDate.setMonth(00);
		startDate.setMinutes(00);
		startDate.setSeconds(00);
		startDate.setHours(0);
		if (batmod != null && batmod.size() > 0) {
			Double primumSinceI = 0.0;
			Double premiumPaidInYear = 0.0;
			
			for (Object object : batmod) {
				IclubPayment payment = (IclubPayment) object;
				if (payment != null && payment.getPValue() > 0) {
					primumSinceI += payment.getPValue();
					if (payment.getPCrtdDt().compareTo(startDate) >= 0) {
						premiumPaidInYear += payment.getPValue();
					}
				}
				
			}
			cSModel.setPremiumPaidInYear(premiumPaidInYear);
			cSModel.setPrimumSinceI(primumSinceI);
		}
		
		Double premiumForYear = 0.0;
		List batPmod = iclubNamedQueryDAO.getIclubPoliciesByCohortId(id, null, null);
		if (batmod != null && batmod.size() > 0) {
			
			for (Object object : batPmod) {
				IclubPolicy policy = (IclubPolicy) object;
				if (policy.getPPremium() != null && policy.getPPremium() > 0) {
					premiumForYear += (policy.getPPremium() * 12);
				}
			}
			cSModel.setPremiumForYear(premiumForYear);
		}
		batPmod = iclubNamedQueryDAO.getIclubPoliciesByCohortId(id, null, 6l);
		if (batmod != null && batmod.size() > 0) {
			Double premiumApproved = 0.0;
			for (Object object : batPmod) {
				IclubPolicy policy = (IclubPolicy) object;
				if (policy.getPPremium() != null && policy.getPPremium() > 0) {
					premiumApproved += (policy.getPPremium() * 12);
				}
			}
			cSModel.setPremiumApproved(premiumApproved);
		}
		List batCmod = iclubNamedQueryDAO.getIclubClaimsByCohortId(id, null, null);
		if (batCmod != null && batCmod.size() > 0) {
			Double claimsLodged = 0.0;
			Double claimsInYear = 0.0;
			for (Object object : batCmod) {
				IclubClaim claim = (IclubClaim) object;
				if (claim != null && claim.getCValue() > 0) {
					claimsLodged += claim.getCValue();
					if (claim.getCCrtdDt().compareTo(startDate) >= 0) {
						claimsInYear += claim.getCValue();
					}
				}
				cSModel.setCliamsLodged(claimsLodged);
				cSModel.setClaimsInYear(claimsInYear);
			}
		}
		
		batCmod = iclubNamedQueryDAO.getIclubClaimsByCohortId(id, null, 5l);
		if (batCmod != null && batCmod.size() > 0) {
			Double premiumApproved = 0.0;
			for (Object object : batCmod) {
				IclubClaim claim = (IclubClaim) object;
				if (claim != null && claim.getCValue() > 0) {
					premiumApproved += claim.getCValue();
				}
				cSModel.setCalimApproved(premiumApproved);
				
			}
		}
		
		List batCImod = iclubNamedQueryDAO.getIclubClaimsByCohortId(id, null, 7l);
		if (batCImod != null && batCImod.size() > 0) {
			Double claimSinceI = 0.0;
			for (Object object : batCImod) {
				IclubClaim claim = (IclubClaim) object;
				if (claim != null && claim.getCValue() > 0) {
					claimSinceI += claim.getCValue();
				}
				cSModel.setClaimSinceI(claimSinceI);
				
			}
		}
		cSModel.setPoolAvailable(cSModel.getPremiumApproved() == null ? 0 : cSModel.getClaimsInYear() == null ? cSModel.getPremiumApproved() : (cSModel.getPremiumApproved() - cSModel.getClaimsInYear()));
		
		if (cSModel.getPremiumApproved() != null && cSModel.getCalimApproved() != null) {
			cSModel.setClaimRatio((cSModel.getPremiumApproved() == null || cSModel.getPremiumApproved() == 0) ? cSModel.getCalimApproved() : (cSModel.getCalimApproved() / cSModel.getPremiumApproved()));
		}
		
		Long noOfActiverMemebers = iclubNamedQueryDAO.getIclubPersonCountByCohortId(id).longValue();
		cSModel.setNumOfActMembers(noOfActiverMemebers);
		
		return cSModel;
	}
	
	@GET
	@Path("/getCohortSummaryByUserId/{userId}")
	@Produces("application/json")
	@Transactional
	public IclubCohortSummaryModel getCohortSummaryByUserId(@PathParam("userId") String userId) {
		
		IclubCohortSummaryModel cSModel = new IclubCohortSummaryModel();
		
		List batmod = iclubNamedQueryDAO.getIclubPaymentsByCohortId(null, userId);
		Date startDate = new Date();
		startDate.setDate(01);
		startDate.setMonth(00);
		startDate.setMinutes(00);
		startDate.setSeconds(00);
		startDate.setHours(0);
		if (batmod != null && batmod.size() > 0) {
			Double primumSinceI = 0.0;
			Double premiumPaidInYear = 0.0;
			
			for (Object object : batmod) {
				IclubPayment payment = (IclubPayment) object;
				if (payment != null && payment.getPValue() > 0) {
					primumSinceI += payment.getPValue();
					if (payment.getPCrtdDt().compareTo(startDate) >= 0) {
						premiumPaidInYear += payment.getPValue();
					}
				}
				
			}
			cSModel.setPremiumPaidInYear(premiumPaidInYear);
			cSModel.setPrimumSinceI(primumSinceI);
		}
		
		Double premiumForYear = 0.0;
		List batPmod = iclubNamedQueryDAO.getIclubPoliciesByCohortId(null, userId, null);
		if (batmod != null && batmod.size() > 0) {
			
			for (Object object : batPmod) {
				IclubPolicy policy = (IclubPolicy) object;
				if (policy.getPPremium() != null && policy.getPPremium() > 0) {
					premiumForYear += (policy.getPPremium() * 12);
				}
			}
			cSModel.setPremiumForYear(premiumForYear);
		}
		
		List batCmod = iclubNamedQueryDAO.getIclubClaimsByCohortId(null, userId, null);
		if (batCmod != null && batCmod.size() > 0) {
			Double claimsLodged = 0.0;
			Double claimsInYear = 0.0;
			for (Object object : batCmod) {
				IclubClaim claim = (IclubClaim) object;
				if (claim != null && claim.getCValue() > 0) {
					claimsLodged += claim.getCValue();
					if (claim.getCCrtdDt().compareTo(startDate) >= 0) {
						claimsInYear += claim.getCValue();
					}
				}
				cSModel.setCliamsLodged(claimsLodged);
				cSModel.setClaimsInYear(claimsInYear);
			}
		}
		
		List batCImod = iclubNamedQueryDAO.getIclubClaimsByCohortId(null, userId, 7l);
		if (batCImod != null && batCImod.size() > 0) {
			Double claimSinceI = 0.0;
			for (Object object : batCImod) {
				IclubClaim claim = (IclubClaim) object;
				if (claim != null && claim.getCValue() > 0) {
					claimSinceI += claim.getCValue();
				}
				cSModel.setClaimSinceI(claimSinceI);
				
			}
		}
		return cSModel;
	}
	
	public IclubCohortDAO getIclubCohortTypeDAO() {
		return iclubCohortDAO;
	}
	
	public void setIclubCohortDAO(IclubCohortDAO iclubCohortDAO) {
		this.iclubCohortDAO = iclubCohortDAO;
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
	
	public IclubNamedQueryDAO getIclubNamedQueryDAO() {
		return iclubNamedQueryDAO;
	}
	
	public void setIclubNamedQueryDAO(IclubNamedQueryDAO iclubNamedQueryDAO) {
		this.iclubNamedQueryDAO = iclubNamedQueryDAO;
	}
	
	public IclubCohortTypeDAO getIclubCohortTypeTypeDAO() {
		return iclubCohortTypeDAO;
	}
	
	public void setIclubCohortTypeDAO(IclubCohortTypeDAO iclubCohortTypeDAO) {
		this.iclubCohortTypeDAO = iclubCohortTypeDAO;
	}
	
	public IclubInsuranceItemTypeDAO getIclubInsuranceItemTypeDAO() {
		return iclubInsuranceItemTypeDAO;
	}
	
	public void setIclubInsuranceItemTypeDAO(IclubInsuranceItemTypeDAO iclubInsuranceItemTypeDAO) {
		this.iclubInsuranceItemTypeDAO = iclubInsuranceItemTypeDAO;
	}
	
}
