package za.co.iclub.pss.ws.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import za.co.iclub.pss.orm.bean.IclubPerson;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.ws.model.IclubPersonModel;

@Path("/IclubPersonService")
public class IclubPersonService {
	protected static final Logger LOGGER = Logger.getLogger(IclubPersonService.class);
	private IclubPersonDAO iclubPersonDAO;
	private IclubCommonDAO iclubCommonDAO;
	
	/*@POST
	@Path("/add")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubPersonModel model) {
		try {
			IclubPerson user = new IclubPerson();

			user.setPId(model.getPId());
			user.setPCrtdDt(model.getPCrtdDt());
			user.setPDob(model.getPDob());
			user.setPEmail(model.getPEmail());
			user.setPEmpId(model.getPEmpId());
			user.setPFbHandle(model.getPFbHandle());
			user.setPFName(model.getPFName());
			user.setPIdNum(model.getPIdNum());
			user.setPIdType(model.getPIdType());
			user.setPLName(model.getPLName());
			user.setPLiHandle(model.getPLiHandle());
			user.setPMiddleName(model.getPMiddleName());
			user.setPMobile(model.getPMobile() );
			user.setPTwHandle(model.getPTwHandle());
			user.setIclubPerson(model.getIclubPerson()!=null ? iclubPersonDAO.findById(model.getIclubPerson()) : null);
			user.setIclubEntityStatus(model.getIclubEntityStatus()!=null ? innomsEntityStatusDAO.findById(model.getIclubEntityStatus()) : null);

			iclubPersonDAO.save(user);

			LOGGER.info("Save Success with ID :: " + user.getPId());

			ResponseModel message = new ResponseModel();
			message.setStatusCode(Integer.valueOf(0));
			message.setStatusDesc("Success");
			return message;
		} catch (Exception e) {
			LOGGER.error(e, e);
			ResponseModel message = new ResponseModel();
			message.setStatusCode(Integer.valueOf(1));
			message.setStatusDesc(e.getMessage());
			return message;
		}
	}

	@PUT
	@Path("/mod")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel mod(IclubPersonModel model) {
		try {
			IclubPerson user = new IclubPerson();

			user.setPId(model.getPId());
			user.setPCrtdDt(model.getPCrtdDt());
			user.setPDob(model.getPDob());
			user.setPEmail(model.getPEmail());
			user.setPEmpId(model.getPEmpId());
			user.setPFbHandle(model.getPFbHandle());
			user.setPFName(model.getPFName());
			user.setPIdNum(model.getPIdNum());
			user.setPIdType(model.getPIdType());
			user.setPLName(model.getPLName());
			user.setPLiHandle(model.getPLiHandle());
			user.setPMiddleName(model.getPMiddleName());
			user.setPMobile(model.getPMobile());
			user.setPTwHandle(model.getPTwHandle());
			user.setIclubPerson(model.getIclubPerson()!=null ? iclubPersonDAO.findById(model.getIclubPerson()) : null);
			user.setIclubEntityStatus(model.getIclubEntityStatus()!=null ? innomsEntityStatusDAO.findById(model.getIclubEntityStatus()) : null);

			iclubPersonDAO.merge(user);

			LOGGER.info("Merge Success with ID :: " + model.getPId());

			ResponseModel message = new ResponseModel();
			message.setStatusCode(Integer.valueOf(0));
			message.setStatusDesc("Success");
			return message;
		} catch (Exception e) {
			LOGGER.error(e, e);
			ResponseModel message = new ResponseModel();
			message.setStatusCode(Integer.valueOf(1));
			message.setStatusDesc(e.getMessage());
			return message;
		}
	}

	@GET
	@Path("/del/{id}")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@Transactional(propagation = Propagation.REQUIRED)
	public Response del(@PathParam("id") String id) {
		try {
			iclubPersonDAO.delete(iclubPersonDAO.findById(id));
			return Response.ok().build();
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}

	@GET
	@Path("/list")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public <T extends IclubPersonModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubPersonDAO.findAll();

			for (Object object : batmod) {
				IclubPerson iUser = (IclubPerson) object;

				IclubPersonModel model = new IclubPersonModel();

				model.setPId(iUser.getPId());
				model.setPCrtdDt(iUser.getPCrtdDt());
				model.setPDob(iUser.getPDob());
				model.setPEmail(iUser.getPEmail());
				model.setPEmpId(iUser.getPEmpId());
				model.setPFbHandle(iUser.getPFbHandle());
				model.setPFName(iUser.getPFName());
				model.setPIdNum(iUser.getPIdNum());
				model.setPIdType(iUser.getPIdType());
				model.setPLName(iUser.getPLName());
				model.setPLiHandle(iUser.getPLiHandle());
				model.setPMiddleName(iUser.getPMiddleName());
				model.setPMobile(iUser.getPMobile());
				model.setPTwHandle(iUser.getPTwHandle());
				if(iUser.getIclubPerson()!=null)
				{
					model.setIclubPerson(iUser.getIclubPerson().getPId());
				}

				if (iUser.getIclubEntityStatus() != null)
					model.setIclubEntityStatus(iUser.getIclubEntityStatus().getEntityStatusId());
				
				  if(iUser.getIclubRewards()!=null && iUser.getIclubRewards().size()>0)	{
					  Set<IclubReward> iRew = iUser.getIclubRewards();
					  Long[] innomsReward = new Long[iRew.size()];
					  int i = 0;
					  for (IclubReward iR : iRew) {
						  innomsReward[i] = iR.getRewardId();
						  	i++;
					  }
					  model.setIclubRewards(innomsReward);
				  }
				if(iUser.getIclubEntityRevsForEntityRevCreatedBy()!=null && iUser.getIclubEntityRevsForEntityRevCreatedBy().size()>0)	{
					Set<IclubEntityRev> iEntityRev = iUser.getIclubEntityRevsForEntityRevCreatedBy();
					String[] innomsEntityRev = new String[iEntityRev.size()];
					int i = 0;
					for (IclubEntityRev iER : iEntityRev) {
						innomsEntityRev[i] = iER.getEntityRevEntityId();
						i++;
					}
					
					model.setIclubEntityRevsForEntityRevCreatedBy(innomsEntityRev);
				}  
				 
				if(iUser.getIclubGroupRevsForGroupRevCreatedBy()!=null && iUser.getIclubGroupRevsForGroupRevCreatedBy().size()>0)	{
					Set<IclubGroupRev> iGR = iUser.getIclubGroupRevsForGroupRevCreatedBy();
					String[] innomsGroupRev = new String[iGR.size()];
					int i = 0;
					for (IclubGroupRev iG : iGR) {
						innomsGroupRev[i] = iG.getGroupRevId();
						i++;
					}
					model.setIclubGroupRevsForGroupRevCreatedBy(innomsGroupRev);
				}  
				
				if(iUser.getIclubPersons()!=null && iUser.getIclubPersons().size()>0)	{
					Set<IclubPerson> iUsr = iUser.getIclubPersons();
					String[] iclubPerson = new String[iUsr.size()];
					int i = 0;
					for (IclubPerson iU : iUsr) {
						iclubPerson[i] = iU.getPId();
						i++;
					}
					model.setIclubPersons(iclubPerson);
				} 
				
				 
				
				 
				
				 
				
				if(iUser.getIclubLoginsForLoginCreatedBy()!=null && iUser.getIclubLoginsForLoginCreatedBy().size()>0)	{
					Set<IclubLogin> iLog = iUser.getIclubLoginsForLoginCreatedBy();
					String[] innomsLogin = new String[iLog.size()];
					int i = 0;
					for (IclubLogin iL : iLog) {
						innomsLogin[i] = iL.getLoginId();
						i++; 
					}
					model.setIclubLoginsForLoginCreatedBy(innomsLogin);
				} 
				
				if(iUser.getIclubGroupRevsForGroupRevUserId()!=null && iUser.getIclubGroupRevsForGroupRevUserId().size()>0)	{
					Set<IclubGroupRev> iGR = iUser.getIclubGroupRevsForGroupRevUserId();
					String[] innomsGroupRev = new String[iGR.size()];
					int i = 0;
					for (IclubGroupRev iG : iGR) {
						innomsGroupRev[i] = iG.getGroupRevId();
						i++;
					}
					model.setIclubGroupRevsForGroupRevUserId(innomsGroupRev);
				}  
				
				  
				if(iUser.getIclubIdeas()!=null && iUser.getIclubIdeas().size()>0)	{
					Set<IclubIdea> iIde = iUser.getIclubIdeas();
					String[] innomsIdea = new String[iIde.size()];
					int i = 0;
					for (IclubIdea iI : iIde) {
						innomsIdea[i] = iI.getIdeaId();
						i++;
					}
					
					model.setIclubIdeas(innomsIdea);
				}  
				
				if(iUser.getIclubAllocations()!=null && iUser.getIclubAllocations().size()>0)	{
					Set<IclubAllocation> iAll = iUser.getIclubAllocations();
					Long[] innomsAllocation = new Long[iAll.size()];
					int i = 0;
					for (IclubAllocation iA : iAll) {
						innomsAllocation[i] = iA.getAllocationId();
						i++;
					}
					model.setIclubAllocations(innomsAllocation);
				}  
				if(iUser.getIclubLoginsForLoginUserId()!=null && iUser.getIclubLoginsForLoginUserId().size()>0)	{
					Set<IclubLogin> iLog = iUser.getIclubLoginsForLoginUserId();
					String[] innomsLogin = new String[iLog.size()];
					int i = 0;
					for (IclubLogin iL : iLog) {
						innomsLogin[i] = iL.getLoginId();
						i++;
					} 
					model.setIclubLoginsForLoginUserId(innomsLogin);
				}  
				
				if(iUser.getIclubNewses()!=null && iUser.getIclubNewses().size()>0)	{
					Set<IclubNews> iNew = iUser.getIclubNewses();
					String[] innomsNews = new String[iNew.size()];
					int i = 0;
					for (IclubNews iN : iNew) {
						innomsNews[i] = iN.getNewsId();
						i++;
					}
					model.setIclubNewses(innomsNews);
				}  
				
				if(iUser.getIclubEntityRevsForEntityRevUserId()!=null && iUser.getIclubEntityRevsForEntityRevUserId().size()>0)	{
					Set<IclubEntityRev> iRev = iUser.getIclubEntityRevsForEntityRevUserId();
					String[] innomsEntityRev = new String[iRev.size()];
					int i = 0;
					for (IclubEntityRev iR : iRev) {
						innomsEntityRev[i] = iR.getEntityRevId();
						i++; 
					}
					model.setIclubEntityRevsForEntityRevUserId(innomsEntityRev);
				} 
				
				if(iUser.getIclubGroups()!=null && iUser.getIclubGroups().size()>0)	{
					Set<IclubGroup> iGro = iUser.getIclubGroups();
					Long[] innomsGroup = new Long[iGro.size()];
					int i = 0;
					for (IclubGroup iG : iGro) {
						innomsGroup[i] = iG.getGroupId();
						i++;
					}
					model.setIclubGroups(innomsGroup);
				}  
				
				if(iUser.getIclubGroups_1()!=null && iUser.getIclubGroups_1().size()>0)	{
					Set<IclubGroup> iGro = iUser.getIclubGroups_1();
					Long[] innomsGroup = new Long[iGro.size()];
					int i = 0;
					for (IclubGroup iG : iGro) {
						innomsGroup[i] = iG.getGroupId();
						i++;
					}
					
					model.setIclubGroups_1(innomsGroup);
				}
				 
			
				if(iUser.getIclubPageAccesses()!=null && iUser.getIclubPageAccesses().size()>0)	{
					Set<IclubPageAccess> iPag = iUser.getIclubPageAccesses();
					String[] innomsPageAccess = new String[iPag.size()];
					int i = 0;
					for (IclubPageAccess iP : iPag) {
						innomsPageAccess[i] = iP.getPageAccessId();
						i++;
					}
					model.setIclubPageAccesses(innomsPageAccess);
				}
				 
				if(iUser.getIclubChallenges()!=null && iUser.getIclubChallenges().size()>0)	{
					Set<IclubChallenge> iCha = iUser.getIclubChallenges();
					String[] IclubChallenge = new String[iCha.size()];
					int i = 0;
					for (IclubChallenge iC : iCha) {
						IclubChallenge[i] = iC.getChallengeId();
						i++;
					}
					model.setIclubChallenges(IclubChallenge);
				}
				 
				
				if(iUser.getIclubTags()!=null && iUser.getIclubTags().size()>0)	{
					Set<IclubTag> iTag = iUser.getIclubTags();
					String[] innomsTag = new String[iTag.size()];
					int i = 0;
					for (IclubTag iT : iTag) {
						innomsTag[i] = iT.getTagId();
						i++;
					}
					model.setIclubTags(innomsTag);
				}
				  
				
				if(iUser.getIclubSolutions()!=null && iUser.getIclubNotifications().size()>0)	{
					Set<IclubSolution> iSol = iUser.getIclubSolutions();
					String[] innomsSolution = new String[iSol.size()];
					int i = 0;
					for (IclubSolution iS : iSol) {
						innomsSolution[i] = iS.getSolutionId();
						i++;
					}
					model.setIclubSolutions(innomsSolution);
				}
				 
				if(iUser.getIclubNotifications()!=null && iUser.getIclubNotifications().size()>0)	{
					Set<IclubNotification> iNot = iUser.getIclubNotifications();
					String[] innomsNotification = new String[iNot.size()];
					int i = 0;
					for (IclubNotification iS : iNot) {
						innomsNotification[i] = iS.getNotificationId();
						i++;
					}
					model.setIclubNotifications(innomsNotification);
				}
				 
				 
				if(iUser.getIclubFieldAccesses()!=null && iUser.getIclubFieldAccesses().size()>0)	{
					Set<IclubFieldAccess> iFie = iUser.getIclubFieldAccesses();
					Long[] innomsFieldAccess = new Long[iFie.size()];
					int i = 0;
					for (IclubFieldAccess iF : iFie) {
						innomsFieldAccess[i] = iF.getFieldAccessId();
						i++;
					}
					model.setIclubFieldAccesses(innomsFieldAccess);
				}
				 
				if(iUser.getIclubPointsesForPointsUserId()!=null && iUser.getIclubPointsesForPointsUserId().size()>0)	{
					Set<IclubPoints> iSol = iUser.getIclubPointsesForPointsUserId();
					String[] innomsPoints = new String[iSol.size()];
					int i = 0;
					for (IclubPoints iS : iSol) {
						innomsPoints[i] = iS.getPointsId();
						i++;
					}
					model.setIclubPointsesForPointsUserId(innomsPoints);
				} 
				if(iUser.getIclubClaims()!=null && iUser.getIclubClaims().size()>0)	{
					Set<IclubClaim> iCal = iUser.getIclubClaims();
					String[] innomsClaim = new String[iCal.size()];
					int i = 0;
					for (IclubClaim iC : iCal) {
						innomsClaim[i] = iC.getClaimId();
						i++;
					}
					 model.setIclubClaims(innomsClaim);
				}
				
				if(iUser.getIclubGroupUsersForGroupUserCrtdBy()!=null && iUser.getIclubGroupRevsForGroupRevCreatedBy().size()>0)	{
					Set<IclubGroupUser> iGu = iUser.getIclubGroupUsersForGroupUserCrtdBy();
					String[] innomsGroupUser = new String[iGu.size()];
					int i = 0;
					for (IclubGroupUser iG : iGu) {
						innomsGroupUser[i] = iG.getGroupUserId();
						i++;
					}
					 model.setIclubGroupUsersForGroupUserCrtdBy(innomsGroupUser);
				}
				
				if(iUser.getIclubGroupUsersForGroupUserUserId()!=null && iUser.getIclubGroupUsersForGroupUserUserId().size()>0)	{
					Set<IclubGroupUser> iGUS = iUser.getIclubGroupUsersForGroupUserUserId();
					String[] innomsGroupUser = new String[iGUS.size()];
					int i = 0;
					for (IclubGroupUser iGUU : iGUS) {
						innomsGroupUser[i] = iGUU.getGroupUserId();
						i++;
					}
					 model.setIclubGroupUsersForGroupUserUserId(innomsGroupUser);
				}
				
				if(iUser.getIclubEvents()!=null && iUser.getIclubEvents().size()>0)	{
					Set<IclubEvent> iEv = iUser.getIclubEvents();
					String[] innomsEvent = new String[iEv.size()];
					int i = 0;
					for (IclubEvent iE : iEv) {
						innomsEvent[i] = iE.getEventId();
						i++;
					}
					 model.setIclubClaims(innomsEvent);
				}								 

				ret.add((T) model);
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
		}

		return ret;
	}
*/
	@GET
	@Path("/get/{id}")
	@Produces({ "application/json" })
	@Transactional(propagation = Propagation.REQUIRED)
	public IclubPersonModel getById(@PathParam("id") String id) {
		IclubPersonModel model = new IclubPersonModel();
		try {
			IclubPerson bean = iclubPersonDAO.findById(id);

			model.setPId(bean.getPId());
			model.setPCrtdDt(bean.getPCrtdDt());
			model.setPDob(bean.getPDob());
			model.setPEmail(bean.getPEmail());
			model.setPFName(bean.getPFName());
			model.setPIdNum(bean.getPIdNum());
			model.setPLName(bean.getPLName());
			model.setPMobile(bean.getPMobile());
			if (bean.getIclubPerson() != null) {
				model.setIclubPerson(bean.getIclubPerson().getPId());
			}

		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return model;
	}

	public IclubPersonDAO getIclubPersonDAO() {
		return iclubPersonDAO;
	}

	public void setIclubPersonDAO(IclubPersonDAO iclubPersonDAO) {
		this.iclubPersonDAO = iclubPersonDAO;
	}

	public IclubCommonDAO getIclubCommonDAO() {
		return iclubCommonDAO;
	}

	public void setIclubCommonDAO(IclubCommonDAO iclubCommonDAO) {
		this.iclubCommonDAO = iclubCommonDAO;
	}

	 

	 
}
