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

import za.co.iclub.pss.orm.bean.IclubEventType;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubEventType entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubEventType
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubEventTypeDAO {
	private static final Logger log = Logger.getLogger(IclubEventTypeDAO.class);
	// property constants
	public static final String ET_SHORT_DESC = "etShortDesc";
	public static final String ET_LONG_DESC = "etLongDesc";
	public static final String ET_STATUS = "etStatus";

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

	public void save(IclubEventType transientInstance) {
		log.debug("saving IclubEventType instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubEventType persistentInstance) {
		log.debug("deleting IclubEventType instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubEventType findById(java.lang.Long id) {
		log.debug("getting IclubEventType instance with id: " + id);
		try {
			IclubEventType instance = (IclubEventType) getCurrentSession().get("za.co.iclub.pss.orm.bean.IclubEventType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubEventType> findByExample(IclubEventType instance) {
		log.debug("finding IclubEventType instance by example");
		try {
			List<IclubEventType> results = (List<IclubEventType>) getCurrentSession().createCriteria("za.co.iclub.pss.orm.bean.IclubEventType").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IclubEventType instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IclubEventType as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubEventType> findByEtShortDesc(Object etShortDesc) {
		return findByProperty(ET_SHORT_DESC, etShortDesc);
	}

	public List<IclubEventType> findByEtLongDesc(Object etLongDesc) {
		return findByProperty(ET_LONG_DESC, etLongDesc);
	}

	public List<IclubEventType> findByEtStatus(Object etStatus) {
		return findByProperty(ET_STATUS, etStatus);
	}

	public List findAll() {
		log.debug("finding all IclubEventType instances");
		try {
			String queryString = "from IclubEventType";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubEventType merge(IclubEventType detachedInstance) {
		log.debug("merging IclubEventType instance");
		try {
			IclubEventType result = (IclubEventType) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubEventType instance) {
		log.debug("attaching dirty IclubEventType instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubEventType instance) {
		log.debug("attaching clean IclubEventType instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IclubEventTypeDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IclubEventTypeDAO) ctx.getBean("IclubEventTypeDAO");
	}
}