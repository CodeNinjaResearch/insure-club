package za.co.iclub.pss.scheduler;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import za.co.iclub.pss.orm.dao.IclubCohortInviteDAO;
import za.co.iclub.pss.orm.dao.IclubNamedQueryDAO;

public class ScheduledJob extends QuartzJobBean {
	
	private IclubCohortInviteDAO iclubCohortInviteDAO;
	
	private IclubNamedQueryDAO iclubNamedQueryDAO;
	
	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("--------Inside Scheduler-----");
		iclubCohortInviteDAO.findAll();
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
