package za.co.iclub.pss.orm.dao;

import java.util.List;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import static org.hibernate.criterion.Example.create;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import za.co.iclub.pss.orm.bean.IclubInviteStatus;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubInviteStatus entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubInviteStatus
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({"unchecked","rawtypes"})
public class IclubInviteStatusDAO {
	private static final Logger log = LoggerFactory
			.getLogger(IclubInviteStatusDAO.class);
	// property constants
	public static final String IS_LONG_DESC = "isLongDesc";
	public static final String IS_SHORT_DESC = "isShortDesc";
	public static final String IS_STATUS = "isStatus";

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

	public void save(IclubInviteStatus transientInstance) {
		log.debug("saving IclubInviteStatus instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubInviteStatus persistentInstance) {
		log.debug("deleting IclubInviteStatus instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubInviteStatus findById(java.lang.Long id) {
		log.debug("getting IclubInviteStatus instance with id: " + id);
		try {
			IclubInviteStatus instance = (IclubInviteStatus) getCurrentSession()
					.get("za.co.iclub.pss.orm.bean.IclubInviteStatus", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubInviteStatus> findByExample(IclubInviteStatus instance) {
		log.debug("finding IclubInviteStatus instance by example");
		try {
			List<IclubInviteStatus> results = (List<IclubInviteStatus>) getCurrentSession()
					.createCriteria(
							"za.co.iclub.pss.orm.bean.IclubInviteStatus")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IclubInviteStatus instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from IclubInviteStatus as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubInviteStatus> findByIsLongDesc(Object isLongDesc) {
		return findByProperty(IS_LONG_DESC, isLongDesc);
	}

	public List<IclubInviteStatus> findByIsShortDesc(Object isShortDesc) {
		return findByProperty(IS_SHORT_DESC, isShortDesc);
	}

	public List<IclubInviteStatus> findByIsStatus(Object isStatus) {
		return findByProperty(IS_STATUS, isStatus);
	}

	public List findAll() {
		log.debug("finding all IclubInviteStatus instances");
		try {
			String queryString = "from IclubInviteStatus";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubInviteStatus merge(IclubInviteStatus detachedInstance) {
		log.debug("merging IclubInviteStatus instance");
		try {
			IclubInviteStatus result = (IclubInviteStatus) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubInviteStatus instance) {
		log.debug("attaching dirty IclubInviteStatus instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubInviteStatus instance) {
		log.debug("attaching clean IclubInviteStatus instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IclubInviteStatusDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (IclubInviteStatusDAO) ctx.getBean("IclubInviteStatusDAO");
	}
}