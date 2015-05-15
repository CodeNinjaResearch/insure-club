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

import za.co.iclub.pss.orm.bean.IclubPclaimStatus;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubPclaimStatus entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubPclaimStatus
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubPclaimStatusDAO {
	private static final Logger log = Logger.getLogger(IclubPclaimStatusDAO.class);
	// property constants
	public static final String PS_SHORT_DESC = "psShortDesc";
	public static final String PS_LONG_DESC = "psLongDesc";
	public static final String PS_STATUS = "psStatus";

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

	public void save(IclubPclaimStatus transientInstance) {
		log.debug("saving IclubPclaimStatus instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubPclaimStatus persistentInstance) {
		log.debug("deleting IclubPclaimStatus instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubPclaimStatus findById(java.lang.Long id) {
		log.debug("getting IclubPclaimStatus instance with id: " + id);
		try {
			IclubPclaimStatus instance = (IclubPclaimStatus) getCurrentSession().get("za.co.iclub.pss.orm.bean.IclubPclaimStatus", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubPclaimStatus> findByExample(IclubPclaimStatus instance) {
		log.debug("finding IclubPclaimStatus instance by example");
		try {
			List<IclubPclaimStatus> results = (List<IclubPclaimStatus>) getCurrentSession().createCriteria("za.co.iclub.pss.orm.bean.IclubPclaimStatus").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IclubPclaimStatus instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IclubPclaimStatus as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubPclaimStatus> findByPsShortDesc(Object psShortDesc) {
		return findByProperty(PS_SHORT_DESC, psShortDesc);
	}

	public List<IclubPclaimStatus> findByPsLongDesc(Object psLongDesc) {
		return findByProperty(PS_LONG_DESC, psLongDesc);
	}

	public List<IclubPclaimStatus> findByPsStatus(Object psStatus) {
		return findByProperty(PS_STATUS, psStatus);
	}

	public List findAll() {
		log.debug("finding all IclubPclaimStatus instances");
		try {
			String queryString = "from IclubPclaimStatus";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubPclaimStatus merge(IclubPclaimStatus detachedInstance) {
		log.debug("merging IclubPclaimStatus instance");
		try {
			IclubPclaimStatus result = (IclubPclaimStatus) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubPclaimStatus instance) {
		log.debug("attaching dirty IclubPclaimStatus instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubPclaimStatus instance) {
		log.debug("attaching clean IclubPclaimStatus instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IclubPclaimStatusDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IclubPclaimStatusDAO) ctx.getBean("IclubPclaimStatusDAO");
	}
}