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

import za.co.iclub.pss.orm.bean.IclubOwnerType;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubOwnerType entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubOwnerType
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubOwnerTypeDAO {
	private static final Logger log = Logger.getLogger(IclubOwnerTypeDAO.class);
	// property constants
	public static final String OT_SHORT_DESC = "otShortDesc";
	public static final String OT_LONG_DESC = "otLongDesc";
	public static final String OT_STATUS = "otStatus";

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

	public void save(IclubOwnerType transientInstance) {
		log.debug("saving IclubOwnerType instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubOwnerType persistentInstance) {
		log.debug("deleting IclubOwnerType instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubOwnerType findById(java.lang.Long id) {
		log.debug("getting IclubOwnerType instance with id: " + id);
		try {
			IclubOwnerType instance = (IclubOwnerType) getCurrentSession().get("za.co.iclub.pss.orm.bean.IclubOwnerType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubOwnerType> findByExample(IclubOwnerType instance) {
		log.debug("finding IclubOwnerType instance by example");
		try {
			List<IclubOwnerType> results = (List<IclubOwnerType>) getCurrentSession().createCriteria("za.co.iclub.pss.orm.bean.IclubOwnerType").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IclubOwnerType instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IclubOwnerType as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubOwnerType> findByOtShortDesc(Object otShortDesc) {
		return findByProperty(OT_SHORT_DESC, otShortDesc);
	}

	public List<IclubOwnerType> findByOtLongDesc(Object otLongDesc) {
		return findByProperty(OT_LONG_DESC, otLongDesc);
	}

	public List<IclubOwnerType> findByOtStatus(Object otStatus) {
		return findByProperty(OT_STATUS, otStatus);
	}

	public List findAll() {
		log.debug("finding all IclubOwnerType instances");
		try {
			String queryString = "from IclubOwnerType";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubOwnerType merge(IclubOwnerType detachedInstance) {
		log.debug("merging IclubOwnerType instance");
		try {
			IclubOwnerType result = (IclubOwnerType) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubOwnerType instance) {
		log.debug("attaching dirty IclubOwnerType instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubOwnerType instance) {
		log.debug("attaching clean IclubOwnerType instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IclubOwnerTypeDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IclubOwnerTypeDAO) ctx.getBean("IclubOwnerTypeDAO");
	}
}