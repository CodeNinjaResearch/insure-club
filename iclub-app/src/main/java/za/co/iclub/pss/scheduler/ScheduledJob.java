package za.co.iclub.pss.scheduler;

import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import za.co.iclub.pss.orm.bean.IclubCohortInvite;
import za.co.iclub.pss.orm.dao.IclubCohortInviteDAO;
import za.co.iclub.pss.orm.dao.IclubNamedQueryDAO;

public class ScheduledJob extends QuartzJobBean {
	
	private IclubCohortInviteDAO iclubCohortInviteDAO;
	
	private IclubNamedQueryDAO iclubNamedQueryDAO;
	
	private static final Logger LOGGER = Logger.getLogger(ScheduledJob.class);
	
	@SuppressWarnings("unchecked")
	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		
		LOGGER.info("--------Inside Scheduler-----");
		try {
			synchronized (this) {
				List<IclubCohortInvite> cohorsInviteList = iclubNamedQueryDAO.getIclubCohortInvitesByNotSentStatus();
				
				if (cohorsInviteList != null && cohorsInviteList.size() > 0) {
					cohorsInviteList = MailSender.sendMail(cohorsInviteList);
					
					for (IclubCohortInvite inviteBean : cohorsInviteList) {
						iclubCohortInviteDAO.merge(inviteBean);
					}
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public IclubCohortInviteDAO getIclubCohortInviteDAO() {
		return iclubCohortInviteDAO;
	}
	
	public void setIclubCohortInviteDAO(IclubCohortInviteDAO iclubCohortInviteDAO) {
		this.iclubCohortInviteDAO = iclubCohortInviteDAO;
	}
	
	public IclubNamedQueryDAO getIclubNamedQueryDAO() {
		return iclubNamedQueryDAO;
	}
	
	public void setIclubNamedQueryDAO(IclubNamedQueryDAO iclubNamedQueryDAO) {
		this.iclubNamedQueryDAO = iclubNamedQueryDAO;
	}
}
