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

import za.co.iclub.pss.orm.bean.IclubPropUsageType;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubPropUsageType entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubPropUsageType
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubPropUsageTypeDAO {
	private static final Logger log = LoggerFactory.getLogger(IclubPropUsageTypeDAO.class);
	// property constants
	public static final String PUT_LONG_DESC = "putLongDesc";
	public static final String PUT_SHORT_DESC = "putShortDesc";
	public static final String PUT_STATUS = "putStatus";

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

	public void save(IclubPropUsageType transientInstance) {
		log.debug("saving IclubPropUsageType instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubPropUsageType persistentInstance) {
		log.debug("deleting IclubPropUsageType instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubPropUsageType findById(java.lang.Long id) {
		log.debug("getting IclubPropUsageType instance with id: " + id);
		try {
			IclubPropUsageType instance = (IclubPropUsageType) getCurrentSession().get("za.co.iclub.pss.orm.bean.IclubPropUsageType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubPropUsageType> findByExample(IclubPropUsageType instance) {
		log.debug("finding IclubPropUsageType instance by example");
		try {
			List<IclubPropUsageType> results = (List<IclubPropUsageType>) getCurrentSession().createCriteria("za.co.iclub.pss.orm.bean.IclubPropUsageType").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IclubPropUsageType instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IclubPropUsageType as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubPropUsageType> findByPutLongDesc(Object putLongDesc) {
		return findByProperty(PUT_LONG_DESC, putLongDesc);
	}

	public List<IclubPropUsageType> findByPutShortDesc(Object putShortDesc) {
		return findByProperty(PUT_SHORT_DESC, putShortDesc);
	}

	public List<IclubPropUsageType> findByPutStatus(Object putStatus) {
		return findByProperty(PUT_STATUS, putStatus);
	}

	public List findAll() {
		log.debug("finding all IclubPropUsageType instances");
		try {
			String queryString = "from IclubPropUsageType";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubPropUsageType merge(IclubPropUsageType detachedInstance) {
		log.debug("merging IclubPropUsageType instance");
		try {
			IclubPropUsageType result = (IclubPropUsageType) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubPropUsageType instance) {
		log.debug("attaching dirty IclubPropUsageType instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubPropUsageType instance) {
		log.debug("attaching clean IclubPropUsageType instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IclubPropUsageTypeDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IclubPropUsageTypeDAO) ctx.getBean("IclubPropUsageTypeDAO");
	}
}