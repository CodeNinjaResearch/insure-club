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

import za.co.iclub.pss.orm.bean.IclubIdType;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubIdType entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubIdType
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubIdTypeDAO {
	private static final Logger log = LoggerFactory.getLogger(IclubIdTypeDAO.class);
	// property constants
	public static final String IT_SHORT_DESC = "itShortDesc";
	public static final String IT_LONG_DESC = "itLongDesc";
	public static final String IT_STATUS = "itStatus";

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

	public void save(IclubIdType transientInstance) {
		log.debug("saving IclubIdType instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubIdType persistentInstance) {
		log.debug("deleting IclubIdType instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubIdType findById(java.lang.Long id) {
		log.debug("getting IclubIdType instance with id: " + id);
		try {
			IclubIdType instance = (IclubIdType) getCurrentSession().get("za.co.iclub.pss.orm.bean.IclubIdType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubIdType> findByExample(IclubIdType instance) {
		log.debug("finding IclubIdType instance by example");
		try {
			List<IclubIdType> results = (List<IclubIdType>) getCurrentSession().createCriteria("za.co.iclub.pss.orm.bean.IclubIdType").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IclubIdType instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IclubIdType as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubIdType> findByItShortDesc(Object itShortDesc) {
		return findByProperty(IT_SHORT_DESC, itShortDesc);
	}

	public List<IclubIdType> findByItLongDesc(Object itLongDesc) {
		return findByProperty(IT_LONG_DESC, itLongDesc);
	}

	public List<IclubIdType> findByItStatus(Object itStatus) {
		return findByProperty(IT_STATUS, itStatus);
	}

	public List findAll() {
		log.debug("finding all IclubIdType instances");
		try {
			String queryString = "from IclubIdType";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubIdType merge(IclubIdType detachedInstance) {
		log.debug("merging IclubIdType instance");
		try {
			IclubIdType result = (IclubIdType) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubIdType instance) {
		log.debug("attaching dirty IclubIdType instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubIdType instance) {
		log.debug("attaching clean IclubIdType instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IclubIdTypeDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IclubIdTypeDAO) ctx.getBean("IclubIdTypeDAO");
	}
}