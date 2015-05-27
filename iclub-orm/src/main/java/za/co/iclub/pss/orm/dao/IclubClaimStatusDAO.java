package za.co.iclub.pss.orm.dao;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import za.co.iclub.pss.orm.bean.IclubClaimStatus;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubClaimStatus entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubClaimStatus
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubClaimStatusDAO {
	private static final Logger log = Logger.getLogger(IclubClaimStatusDAO.class);
	// property constants
	public static final String CS_SHORT_DESC = "csShortDesc";
	public static final String CS_LONG_DESC = "csLongDesc";
	public static final String CS_STATUS = "csStatus";

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

	public void save(IclubClaimStatus transientInstance) {
		log.debug("saving IclubClaimStatus instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubClaimStatus persistentInstance) {
		log.debug("deleting IclubClaimStatus instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubClaimStatus findById(java.lang.Long id) {
		log.debug("getting IclubClaimStatus instance with id: " + id);
		try {
			IclubClaimStatus instance = (IclubClaimStatus) getCurrentSession().get("za.co.iclub.pss.orm.bean.IclubClaimStatus", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubClaimStatus> findByExample(IclubClaimStatus instance) {
		log.debug("finding IclubClaimStatus instance by example");
		try {
			List<IclubClaimStatus> results = (List<IclubClaimStatus>) getCurrentSession().createCriteria("za.co.iclub.pss.orm.bean.IclubClaimStatus").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IclubClaimStatus instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IclubClaimStatus as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubClaimStatus> findByCsShortDesc(Object csShortDesc) {
		return findByProperty(CS_SHORT_DESC, csShortDesc);
	}

	public List<IclubClaimStatus> findByCsLongDesc(Object csLongDesc) {
		return findByProperty(CS_LONG_DESC, csLongDesc);
	}

	public List<IclubClaimStatus> findByCsStatus(Object csStatus) {
		return findByProperty(CS_STATUS, csStatus);
	}

	public List findAll() {
		log.debug("finding all IclubClaimStatus instances");
		try {
			String queryString = "from IclubClaimStatus";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubClaimStatus merge(IclubClaimStatus detachedInstance) {
		log.debug("merging IclubClaimStatus instance");
		try {
			IclubClaimStatus result = (IclubClaimStatus) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubClaimStatus instance) {
		log.debug("attaching dirty IclubClaimStatus instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubClaimStatus instance) {
		log.debug("attaching clean IclubClaimStatus instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IclubClaimStatusDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IclubClaimStatusDAO) ctx.getBean("IclubClaimStatusDAO");
	}
}