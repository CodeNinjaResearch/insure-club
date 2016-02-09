package za.co.iclub.pss.orm.dao;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import za.co.iclub.pss.orm.bean.IclubCohortInvite;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubCohortInvite entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubCohortInvite
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubCohortInviteDAO {
	private static final Logger log = LoggerFactory.getLogger(IclubCohortInviteDAO.class);
	// property constants
	public static final String CI_INVITE_URI = "ciInviteUri";
	public static final String CI_INVITE_ACCEPT_YN = "ciInviteAcceptYn";
	public static final String CI_INVITE_FNAME = "ciInviteFName";
	public static final String CI_INVITE_LNAME = "ciInviteLName";
	public static final String CI_INVITE_SENT_STATUS = "ciInviteSentStatus";

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	protected void initDao() {
		// do nothing
	}

	public void save(IclubCohortInvite transientInstance) {
		log.debug("saving IclubCohortInvite instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubCohortInvite persistentInstance) {
		log.debug("deleting IclubCohortInvite instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubCohortInvite findById(java.lang.String id) {
		log.debug("getting IclubCohortInvite instance with id: " + id);
		try {
			IclubCohortInvite instance = (IclubCohortInvite) getCurrentSession().get("za.co.iclub.pss.orm.bean.IclubCohortInvite", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubCohortInvite> findByExample(IclubCohortInvite instance) {
		log.debug("finding IclubCohortInvite instance by example");
		try {
			List<IclubCohortInvite> results = (List<IclubCohortInvite>) getCurrentSession().createCriteria("za.co.iclub.pss.orm.bean.IclubCohortInvite").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IclubCohortInvite instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IclubCohortInvite as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubCohortInvite> findByCiInviteUri(Object ciInviteUri) {
		return findByProperty(CI_INVITE_URI, ciInviteUri);
	}

	public List<IclubCohortInvite> findByCiInviteAcceptYn(Object ciInviteAcceptYn) {
		return findByProperty(CI_INVITE_ACCEPT_YN, ciInviteAcceptYn);
	}

	public List<IclubCohortInvite> findByCiInviteFName(Object ciInviteFName) {
		return findByProperty(CI_INVITE_FNAME, ciInviteFName);
	}

	public List<IclubCohortInvite> findByCiInviteLName(Object ciInviteLName) {
		return findByProperty(CI_INVITE_LNAME, ciInviteLName);
	}

	public List<IclubCohortInvite> findByCiInviteSentStatus(Object ciInviteSentStatus) {
		return findByProperty(CI_INVITE_SENT_STATUS, ciInviteSentStatus);
	}

	public List findAll() {
		log.debug("finding all IclubCohortInvite instances");
		try {
			String queryString = "from IclubCohortInvite";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubCohortInvite merge(IclubCohortInvite detachedInstance) {
		log.debug("merging IclubCohortInvite instance");
		try {
			IclubCohortInvite result = (IclubCohortInvite) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubCohortInvite instance) {
		log.debug("attaching dirty IclubCohortInvite instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubCohortInvite instance) {
		log.debug("attaching clean IclubCohortInvite instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IclubCohortInviteDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IclubCohortInviteDAO) ctx.getBean("IclubCohortInviteDAO");
	}
}