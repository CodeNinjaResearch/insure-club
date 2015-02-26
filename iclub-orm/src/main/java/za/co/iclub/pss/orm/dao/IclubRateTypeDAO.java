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

import za.co.iclub.pss.orm.bean.IclubRateType;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubRateType entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubRateType
 * @author Venu Madhav Pattamatta
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubRateTypeDAO {
	private static final Logger log = Logger.getLogger(IclubRateTypeDAO.class);
	// property constants
	public static final String RT_SHORT_DESC = "rtShortDesc";
	public static final String RT_LONG_DESC = "rtLongDesc";
	public static final String RT_STATUS = "rtStatus";

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

	public void save(IclubRateType transientInstance) {
		log.debug("saving IclubRateType instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubRateType persistentInstance) {
		log.debug("deleting IclubRateType instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubRateType findById(java.lang.Long id) {
		log.debug("getting IclubRateType instance with id: " + id);
		try {
			IclubRateType instance = (IclubRateType) getCurrentSession().get(
					"za.co.iclub.pss.orm.bean.IclubRateType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubRateType> findByExample(IclubRateType instance) {
		log.debug("finding IclubRateType instance by example");
		try {
			List<IclubRateType> results = (List<IclubRateType>) getCurrentSession()
					.createCriteria("za.co.iclub.pss.orm.bean.IclubRateType")
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
		log.debug("finding IclubRateType instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from IclubRateType as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubRateType> findByRtShortDesc(Object rtShortDesc) {
		return findByProperty(RT_SHORT_DESC, rtShortDesc);
	}

	public List<IclubRateType> findByRtLongDesc(Object rtLongDesc) {
		return findByProperty(RT_LONG_DESC, rtLongDesc);
	}

	public List<IclubRateType> findByRtStatus(Object rtStatus) {
		return findByProperty(RT_STATUS, rtStatus);
	}

	public List findAll() {
		log.debug("finding all IclubRateType instances");
		try {
			String queryString = "from IclubRateType";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubRateType merge(IclubRateType detachedInstance) {
		log.debug("merging IclubRateType instance");
		try {
			IclubRateType result = (IclubRateType) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubRateType instance) {
		log.debug("attaching dirty IclubRateType instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubRateType instance) {
		log.debug("attaching clean IclubRateType instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IclubRateTypeDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (IclubRateTypeDAO) ctx.getBean("IclubRateTypeDAO");
	}
}