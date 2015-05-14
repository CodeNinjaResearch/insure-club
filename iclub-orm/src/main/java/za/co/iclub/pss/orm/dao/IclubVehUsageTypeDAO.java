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

import za.co.iclub.pss.orm.bean.IclubVehUsageType;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubVehUsageType entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubVehUsageType
 * @author MyEclipse Persistence Tools
 */
@Transactional@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubVehUsageTypeDAO {
	private static final Logger log = Logger.getLogger(IclubVehUsageTypeDAO.class);
	// property constants
	public static final String VUT_LONG_DESC = "vutLongDesc";
	public static final String VUT_SHORT_DESC = "vutShortDesc";
	public static final String VUT_STATUS = "vutStatus";

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

	public void save(IclubVehUsageType transientInstance) {
		log.debug("saving IclubVehUsageType instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubVehUsageType persistentInstance) {
		log.debug("deleting IclubVehUsageType instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubVehUsageType findById(java.lang.Long id) {
		log.debug("getting IclubVehUsageType instance with id: " + id);
		try {
			IclubVehUsageType instance = (IclubVehUsageType) getCurrentSession().get("za.co.iclub.pss.orm.bean.IclubVehUsageType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubVehUsageType> findByExample(IclubVehUsageType instance) {
		log.debug("finding IclubVehUsageType instance by example");
		try {
			List<IclubVehUsageType> results = (List<IclubVehUsageType>) getCurrentSession().createCriteria("za.co.iclub.pss.orm.bean.IclubVehUsageType").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IclubVehUsageType instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IclubVehUsageType as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubVehUsageType> findByVutLongDesc(Object vutLongDesc) {
		return findByProperty(VUT_LONG_DESC, vutLongDesc);
	}

	public List<IclubVehUsageType> findByVutShortDesc(Object vutShortDesc) {
		return findByProperty(VUT_SHORT_DESC, vutShortDesc);
	}

	public List<IclubVehUsageType> findByVutStatus(Object vutStatus) {
		return findByProperty(VUT_STATUS, vutStatus);
	}

	public List findAll() {
		log.debug("finding all IclubVehUsageType instances");
		try {
			String queryString = "from IclubVehUsageType";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubVehUsageType merge(IclubVehUsageType detachedInstance) {
		log.debug("merging IclubVehUsageType instance");
		try {
			IclubVehUsageType result = (IclubVehUsageType) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubVehUsageType instance) {
		log.debug("attaching dirty IclubVehUsageType instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubVehUsageType instance) {
		log.debug("attaching clean IclubVehUsageType instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IclubVehUsageTypeDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IclubVehUsageTypeDAO) ctx.getBean("IclubVehUsageTypeDAO");
	}
}