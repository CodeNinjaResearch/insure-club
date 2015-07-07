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

import za.co.iclub.pss.orm.bean.IclubClaim;
import za.co.iclub.pss.orm.bean.IclubCohort;
import za.co.iclub.pss.orm.bean.IclubCohortClaim;
import za.co.iclub.pss.orm.bean.IclubCohortInvite;
import za.co.iclub.pss.orm.bean.IclubCohortPerson;
import za.co.iclub.pss.orm.bean.IclubPayment;
import za.co.iclub.pss.orm.bean.IclubPerson;
import za.co.iclub.pss.orm.bean.IclubPolicy;
import za.co.iclub.pss.orm.dao.IclubCohortDAO;
import za.co.iclub.pss.orm.dao.IclubCohortTypeDAO;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubNamedQueryDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.ws.model.IclubCohortModel;
import za.co.iclub.pss.ws.model.IclubCohortSummaryModel;
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
	
	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional
	public ResponseModel add(IclubCohortModel model) {
		try {
			
			IclubCohort iCC = new IclubCohort();
			
			iCC.setCId(model.getCId());
			iCC.setCName(model.getCName());
			iCC.setCEmail(model.getCEmail());
			iCC.setCInitDt(model.getCInitDt());
			iCC.setCFinalizeDt(model.getCFinalizeDt());
			iCC.setCTotalContrib(model.getCTotalContrib());
			iCC.setCCollectedContrib(model.getCCollectedContrib());
			iCC.setCCurMemberCnt(model.getCCurMemberCnt());
			iCC.setIclubCohortType(model.getIclubCohortType() != null ? iclubCohortTypeDAO.findById(model.getIclubCohortType()) : null);
			iCC.setCCrtdDt(model.getCCrtdDt());
			iCC.setIclubPersonByCPrimaryUserId(model.getIclubPersonByCPrimaryUserId() != null ? iclubPersonDAO.findById(model.getIclubPersonByCPrimaryUserId()) : null);
			iCC.setIclubPersonByCCrtdBy(model.getIclubPersonByCCrtdBy() != null ? iclubPersonDAO.findById(model.getIclubPersonByCCrtdBy()) : null);
			
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
				IclubCohort iCC = new IclubCohort();
				
				iCC.setCId(model.getCId());
				iCC.setCName(model.getCName());
				iCC.setCEmail(model.getCEmail());
				iCC.setCInitDt(model.getCInitDt());
				iCC.setCFinalizeDt(model.getCFinalizeDt());
				iCC.setCTotalContrib(model.getCTotalContrib());
				iCC.setCCollectedContrib(model.getCCollectedContrib());
				iCC.setCCurMemberCnt(model.getCCurMemberCnt());
				iCC.setIclubCohortType(model.getIclubCohortType() != null ? iclubCohortTypeDAO.findById(model.getIclubCohortType()) : null);
				iCC.setCCrtdDt(model.getCCrtdDt());
				iCC.setIclubPersonByCPrimaryUserId(model.getIclubPersonByCPrimaryUserId() != null ? iclubPersonDAO.findById(model.getIclubPersonByCPrimaryUserId()) : null);
				iCC.setIclubPersonByCCrtdBy(model.getIclubPersonByCCrtdBy() != null ? iclubPersonDAO.findById(model.getIclubPersonByCCrtdBy()) : null);
				
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
			IclubCohort iCC = new IclubCohort();
			
			iCC.setCId(model.getCId());
			iCC.setCName(model.getCName());
			iCC.setCEmail(model.getCEmail());
			iCC.setCInitDt(model.getCInitDt());
			iCC.setCFinalizeDt(model.getCFinalizeDt());
			iCC.setCTotalContrib(model.getCTotalContrib());
			iCC.setCCollectedContrib(model.getCCollectedContrib());
			iCC.setCCurMemberCnt(model.getCCurMemberCnt());
			iCC.setIclubCohortType(model.getIclubCohortType() != null ? iclubCohortTypeDAO.findById(model.getIclubCohortType()) : null);
			iCC.setCCrtdDt(model.getCCrtdDt());
			iCC.setIclubPersonByCPrimaryUserId(model.getIclubPersonByCPrimaryUserId() != null ? iclubPersonDAO.findById(model.getIclubPersonByCPrimaryUserId()) : null);
			iCC.setIclubPersonByCCrtdBy(model.getIclubPersonByCCrtdBy() != null ? iclubPersonDAO.findById(model.getIclubPersonByCCrtdBy()) : null);
			
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
					IclubCohort iclubC = (IclubCohort) object;
					IclubCohortModel iCC = new IclubCohortModel();
					
					iCC.setCId(iclubC.getCId());
					iCC.setCName(iclubC.getCName());
					iCC.setCEmail(iclubC.getCEmail());
					iCC.setCInitDt(iclubC.getCInitDt());
					iCC.setCFinalizeDt(iclubC.getCFinalizeDt());
					iCC.setCTotalContrib(iclubC.getCTotalContrib());
					iCC.setCCollectedContrib(iclubC.getCCollectedContrib());
					iCC.setCCurMemberCnt(iclubC.getCCurMemberCnt());
					iCC.setCCrtdDt(iclubC.getCCrtdDt());
					iCC.setIclubCohortType(iclubC.getIclubCohortType() != null ? (iclubC.getIclubCohortType()).getCtId() : null);
					iCC.setCCrtdDt(iclubC.getCCrtdDt());
					iCC.setIclubPersonByCPrimaryUserId(iclubC.getIclubPersonByCPrimaryUserId() != null ? (iclubC.getIclubPersonByCPrimaryUserId()).getPId() : null);
					iCC.setIclubPersonByCCrtdBy(iclubC.getIclubPersonByCCrtdBy() != null ? (iclubC.getIclubPersonByCCrtdBy()).getPId() : null);
					
					if (iclubC.getIclubCohortClaims() != null && iclubC.getIclubCohortClaims().size() > 0) {
						String[] iclubCohortClaims = new String[iclubC.getIclubCohortClaims().size()];
						int i = 0;
						for (IclubCohortClaim iclubCohortClaim : iclubC.getIclubCohortClaims()) {
							iclubCohortClaims[i] = iclubCohortClaim.getCcId();
							i++;
						}
						iCC.setIclubCohortClaims(iclubCohortClaims);
					}
					
					if (iclubC.getIclubCohortPersons() != null && iclubC.getIclubCohortPersons().size() > 0) {
						String[] iclubCohortPersons = new String[iclubC.getIclubCohortPersons().size()];
						int i = 0;
						for (IclubCohortPerson iclubCohortPerson : iclubC.getIclubCohortPersons()) {
							iclubCohortPersons[i] = iclubCohortPerson.getCpId();
							i++;
						}
						iCC.setIclubCohortPersons(iclubCohortPersons);
					}
					
					if (iclubC.getIclubPersons() != null && iclubC.getIclubPersons().size() > 0) {
						String[] iclubPersons = new String[iclubC.getIclubPersons().size()];
						int i = 0;
						for (IclubPerson iclubPerson : iclubC.getIclubPersons()) {
							iclubPersons[i] = iclubPerson.getPId();
							i++;
						}
						iCC.setIclubPersons(iclubPersons);
					}
					
					if (iclubC.getIclubCohortInvites() != null && iclubC.getIclubCohortInvites().size() > 0) {
						String[] iclubCohortInvites = new String[iclubC.getIclubCohortInvites().size()];
						int i = 0;
						for (IclubCohortInvite iclubCohortInvite : iclubC.getIclubCohortInvites()) {
							iclubCohortInvites[i] = iclubCohortInvite.getCiId();
							i++;
						}
						iCC.setIclubCohortInvites(iclubCohortInvites);
					}
					
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
					IclubCohort iclubC = (IclubCohort) object;
					IclubCohortModel iCC = new IclubCohortModel();
					
					iCC.setCId(iclubC.getCId());
					iCC.setCName(iclubC.getCName());
					iCC.setCEmail(iclubC.getCEmail());
					iCC.setCInitDt(iclubC.getCInitDt());
					iCC.setCFinalizeDt(iclubC.getCFinalizeDt());
					iCC.setCTotalContrib(iclubC.getCTotalContrib());
					iCC.setCCollectedContrib(iclubC.getCCollectedContrib());
					iCC.setCCurMemberCnt(iclubC.getCCurMemberCnt());
					iCC.setIclubCohortType(iclubC.getIclubCohortType() != null ? (iclubC.getIclubCohortType()).getCtId() : null);
					iCC.setCCrtdDt(iclubC.getCCrtdDt());
					iCC.setIclubPersonByCPrimaryUserId(iclubC.getIclubPersonByCPrimaryUserId() != null ? (iclubC.getIclubPersonByCPrimaryUserId()).getPId() : null);
					iCC.setIclubPersonByCCrtdBy(iclubC.getIclubPersonByCCrtdBy() != null ? (iclubC.getIclubPersonByCCrtdBy()).getPId() : null);
					
					if (iclubC.getIclubCohortClaims() != null && iclubC.getIclubCohortClaims().size() > 0) {
						String[] iclubCohortClaims = new String[iclubC.getIclubCohortClaims().size()];
						int i = 0;
						for (IclubCohortClaim iclubCohortClaim : iclubC.getIclubCohortClaims()) {
							iclubCohortClaims[i] = iclubCohortClaim.getCcId();
							i++;
						}
						iCC.setIclubCohortClaims(iclubCohortClaims);
					}
					
					if (iclubC.getIclubCohortPersons() != null && iclubC.getIclubCohortPersons().size() > 0) {
						String[] iclubCohortPersons = new String[iclubC.getIclubCohortPersons().size()];
						int i = 0;
						for (IclubCohortPerson iclubCohortPerson : iclubC.getIclubCohortPersons()) {
							iclubCohortPersons[i] = iclubCohortPerson.getCpId();
							i++;
						}
						iCC.setIclubCohortPersons(iclubCohortPersons);
					}
					
					if (iclubC.getIclubPersons() != null && iclubC.getIclubPersons().size() > 0) {
						String[] iclubPersons = new String[iclubC.getIclubPersons().size()];
						int i = 0;
						for (IclubPerson iclubPerson : iclubC.getIclubPersons()) {
							iclubPersons[i] = iclubPerson.getPId();
							i++;
						}
						iCC.setIclubPersons(iclubPersons);
					}
					
					if (iclubC.getIclubCohortInvites() != null && iclubC.getIclubCohortInvites().size() > 0) {
						String[] iclubCohortInvites = new String[iclubC.getIclubCohortInvites().size()];
						int i = 0;
						for (IclubCohortInvite iclubCohortInvite : iclubC.getIclubCohortInvites()) {
							iclubCohortInvites[i] = iclubCohortInvite.getCiId();
							i++;
						}
						iCC.setIclubCohortInvites(iclubCohortInvites);
					}
					
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
			
			model.setCId(bean.getCId());
			model.setCId(bean.getCId());
			model.setCName(bean.getCName());
			model.setCEmail(bean.getCEmail());
			model.setCInitDt(bean.getCInitDt());
			model.setCFinalizeDt(bean.getCFinalizeDt());
			model.setCTotalContrib(bean.getCTotalContrib());
			model.setCCollectedContrib(bean.getCCollectedContrib());
			model.setCCurMemberCnt(bean.getCCurMemberCnt());
			model.setIclubCohortType(bean.getIclubCohortType() != null ? (bean.getIclubCohortType()).getCtId() : null);
			model.setCCrtdDt(bean.getCCrtdDt());
			model.setIclubPersonByCPrimaryUserId(bean.getIclubPersonByCPrimaryUserId() != null ? (bean.getIclubPersonByCPrimaryUserId()).getPId() : null);
			model.setIclubPersonByCCrtdBy(bean.getIclubPersonByCCrtdBy() != null ? (bean.getIclubPersonByCCrtdBy()).getPId() : null);
			if (bean.getIclubCohortClaims() != null && bean.getIclubCohortClaims().size() > 0) {
				String[] iclubCohortClaims = new String[bean.getIclubCohortClaims().size()];
				int i = 0;
				for (IclubCohortClaim iclubCohortClaim : bean.getIclubCohortClaims()) {
					iclubCohortClaims[i] = iclubCohortClaim.getCcId();
					i++;
				}
				model.setIclubCohortClaims(iclubCohortClaims);
			}
			
			if (bean.getIclubCohortPersons() != null && bean.getIclubCohortPersons().size() > 0) {
				String[] iclubCohortPersons = new String[bean.getIclubCohortPersons().size()];
				int i = 0;
				for (IclubCohortPerson iclubCohortPerson : bean.getIclubCohortPersons()) {
					iclubCohortPersons[i] = iclubCohortPerson.getCpId();
					i++;
				}
				model.setIclubCohortPersons(iclubCohortPersons);
			}
			
			if (bean.getIclubPersons() != null && bean.getIclubPersons().size() > 0) {
				String[] iclubPersons = new String[bean.getIclubPersons().size()];
				int i = 0;
				for (IclubPerson iclubPerson : bean.getIclubPersons()) {
					iclubPersons[i] = iclubPerson.getPId();
					i++;
				}
				model.setIclubPersons(iclubPersons);
			}
			
			if (bean.getIclubCohortInvites() != null && bean.getIclubCohortInvites().size() > 0) {
				String[] iclubCohortInvites = new String[bean.getIclubCohortInvites().size()];
				int i = 0;
				for (IclubCohortInvite iclubCohortInvite : bean.getIclubCohortInvites()) {
					iclubCohortInvites[i] = iclubCohortInvite.getCiId();
					i++;
				}
				model.setIclubCohortInvites(iclubCohortInvites);
			}
			
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
		// Calendar cal = Calendar.getInstance();
		// cal.setTime(startDate);
		// cal.add(Calendar.YEAR, 1);
		// cal.add(Calendar.DATE, -1);
		// Date endDate = cal.getTime();
		if (batmod != null && batmod.size() > 0) {
			Double primumSinceI = 0.0;
			Double premiumPaidInYear = 0.0;
			
			for (Object object : batmod) {
				IclubPayment payment = (IclubPayment) object;
				if (payment != null && payment.getPValue() > 0) {
					primumSinceI = +payment.getPValue();
					if (payment.getPCrtdDt().compareTo(startDate) >= 0) {
						premiumPaidInYear = +payment.getPValue();
					}
				}
				
			}
			cSModel.setPremiumPaidInYear(premiumPaidInYear);
			cSModel.setPrimumSinceI(primumSinceI);
		}
		
		Double premiumForYear = 0.0;
		List batPmod = iclubNamedQueryDAO.getIclubPoliciesByCohortId(id, null);
		if (batmod != null && batmod.size() > 0) {
			
			for (Object object : batPmod) {
				IclubPolicy policy = (IclubPolicy) object;
				if (policy.getPPremium() != null && policy.getPPremium() > 0) {
					premiumForYear = +(policy.getPPremium() * 12);
				}
			}
			cSModel.setPremiumForYear(premiumForYear);
		}
		List batCmod = iclubNamedQueryDAO.getIclubClaimsByCohortId(id, null);
		if (batCmod != null && batCmod.size() > 0) {
			Double claimSinceI = 0.0;
			Double claimsInYear = 0.0;
			for (Object object : batCmod) {
				IclubClaim claim = (IclubClaim) object;
				if (claim != null && claim.getCValue() > 0) {
					claimSinceI = +claim.getCValue();
					if (claim.getCCrtdDt().compareTo(startDate) >= 0) {
						claimsInYear = +claim.getCValue();
					}
				}
				cSModel.setClaimSinceI(claimSinceI);
				cSModel.setClaimsInYear(claimsInYear);
			}
		}
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
		// Calendar cal = Calendar.getInstance();
		// cal.setTime(startDate);
		// cal.add(Calendar.YEAR, 1);
		// cal.add(Calendar.DATE, -1);
		// Date endDate = cal.getTime();
		if (batmod != null && batmod.size() > 0) {
			Double primumSinceI = 0.0;
			Double premiumPaidInYear = 0.0;
			
			for (Object object : batmod) {
				IclubPayment payment = (IclubPayment) object;
				if (payment != null && payment.getPValue() > 0) {
					primumSinceI = +payment.getPValue();
					if (payment.getPCrtdDt().compareTo(startDate) >= 0) {
						premiumPaidInYear = +payment.getPValue();
					}
				}
				
			}
			cSModel.setPremiumPaidInYear(premiumPaidInYear);
			cSModel.setPrimumSinceI(primumSinceI);
		}
		
		Double premiumForYear = 0.0;
		List batPmod = iclubNamedQueryDAO.getIclubPoliciesByCohortId(null, userId);
		if (batmod != null && batmod.size() > 0) {
			
			for (Object object : batPmod) {
				IclubPolicy policy = (IclubPolicy) object;
				if (policy.getPPremium() != null && policy.getPPremium() > 0) {
					premiumForYear = +(policy.getPPremium() * 12);
				}
			}
			cSModel.setPremiumForYear(premiumForYear);
		}
		
		List batCmod = iclubNamedQueryDAO.getIclubClaimsByCohortId(null, userId);
		if (batCmod != null && batCmod.size() > 0) {
			Double claimSinceI = 0.0;
			Double claimsInYear = 0.0;
			for (Object object : batCmod) {
				IclubClaim claim = (IclubClaim) object;
				if (claim != null && claim.getCValue() > 0) {
					claimSinceI = +claim.getCValue();
					if (claim.getCCrtdDt().compareTo(startDate) >= 0) {
						claimsInYear = +claim.getCValue();
					}
				}
				cSModel.setClaimSinceI(claimSinceI);
				cSModel.setClaimsInYear(claimsInYear);
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
	
}
