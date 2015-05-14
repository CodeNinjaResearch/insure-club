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

import za.co.iclub.pss.orm.bean.IclubSupplierType;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubSupplierType entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubSupplierType
 * @author MyEclipse Persistence Tools
 */
@Transactional@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubSupplierTypeDAO {
	private static final Logger log = Logger.getLogger(IclubSupplierTypeDAO.class);
	// property constants
	public static final String ST_SHORT_DESC = "stShortDesc";
	public static final String ST_LONG_DESC = "stLongDesc";
	public static final String ST_STATUS = "stStatus";

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

	public void save(IclubSupplierType transientInstance) {
		log.debug("saving IclubSupplierType instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubSupplierType persistentInstance) {
		log.debug("deleting IclubSupplierType instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubSupplierType findById(java.lang.Long id) {
		log.debug("getting IclubSupplierType instance with id: " + id);
		try {
			IclubSupplierType instance = (IclubSupplierType) getCurrentSession().get("za.co.iclub.pss.orm.bean.IclubSupplierType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubSupplierType> findByExample(IclubSupplierType instance) {
		log.debug("finding IclubSupplierType instance by example");
		try {
			List<IclubSupplierType> results = (List<IclubSupplierType>) getCurrentSession().createCriteria("za.co.iclub.pss.orm.bean.IclubSupplierType").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IclubSupplierType instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IclubSupplierType as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubSupplierType> findByStShortDesc(Object stShortDesc) {
		return findByProperty(ST_SHORT_DESC, stShortDesc);
	}

	public List<IclubSupplierType> findByStLongDesc(Object stLongDesc) {
		return findByProperty(ST_LONG_DESC, stLongDesc);
	}

	public List<IclubSupplierType> findByStStatus(Object stStatus) {
		return findByProperty(ST_STATUS, stStatus);
	}

	public List findAll() {
		log.debug("finding all IclubSupplierType instances");
		try {
			String queryString = "from IclubSupplierType";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubSupplierType merge(IclubSupplierType detachedInstance) {
		log.debug("merging IclubSupplierType instance");
		try {
			IclubSupplierType result = (IclubSupplierType) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubSupplierType instance) {
		log.debug("attaching dirty IclubSupplierType instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubSupplierType instance) {
		log.debug("attaching clean IclubSupplierType instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IclubSupplierTypeDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IclubSupplierTypeDAO) ctx.getBean("IclubSupplierTypeDAO");
	}
}