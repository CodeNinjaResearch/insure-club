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

import za.co.iclub.pss.orm.bean.IclubProductType;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubProductType entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubProductType
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubProductTypeDAO {
	private static final Logger log = LoggerFactory.getLogger(IclubProductTypeDAO.class);
	// property constants
	public static final String PT_SHORT_DESC = "ptShortDesc";
	public static final String PT_LONG_DESC = "ptLongDesc";
	public static final String PT_STATUS = "ptStatus";

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

	public void save(IclubProductType transientInstance) {
		log.debug("saving IclubProductType instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubProductType persistentInstance) {
		log.debug("deleting IclubProductType instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubProductType findById(java.lang.Long id) {
		log.debug("getting IclubProductType instance with id: " + id);
		try {
			IclubProductType instance = (IclubProductType) getCurrentSession().get("za.co.iclub.pss.orm.bean.IclubProductType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubProductType> findByExample(IclubProductType instance) {
		log.debug("finding IclubProductType instance by example");
		try {
			List<IclubProductType> results = (List<IclubProductType>) getCurrentSession().createCriteria("za.co.iclub.pss.orm.bean.IclubProductType").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IclubProductType instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IclubProductType as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubProductType> findByPtShortDesc(Object ptShortDesc) {
		return findByProperty(PT_SHORT_DESC, ptShortDesc);
	}

	public List<IclubProductType> findByPtLongDesc(Object ptLongDesc) {
		return findByProperty(PT_LONG_DESC, ptLongDesc);
	}

	public List<IclubProductType> findByPtStatus(Object ptStatus) {
		return findByProperty(PT_STATUS, ptStatus);
	}

	public List findAll() {
		log.debug("finding all IclubProductType instances");
		try {
			String queryString = "from IclubProductType";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubProductType merge(IclubProductType detachedInstance) {
		log.debug("merging IclubProductType instance");
		try {
			IclubProductType result = (IclubProductType) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubProductType instance) {
		log.debug("attaching dirty IclubProductType instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubProductType instance) {
		log.debug("attaching clean IclubProductType instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IclubProductTypeDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IclubProductTypeDAO) ctx.getBean("IclubProductTypeDAO");
	}
}