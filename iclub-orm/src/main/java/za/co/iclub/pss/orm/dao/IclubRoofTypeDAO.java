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

import za.co.iclub.pss.orm.bean.IclubRoofType;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubRoofType entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubRoofType
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubRoofTypeDAO {
	private static final Logger log = Logger.getLogger(IclubRoofTypeDAO.class);
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

	public void save(IclubRoofType transientInstance) {
		log.debug("saving IclubRoofType instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubRoofType persistentInstance) {
		log.debug("deleting IclubRoofType instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubRoofType findById(java.lang.Long id) {
		log.debug("getting IclubRoofType instance with id: " + id);
		try {
			IclubRoofType instance = (IclubRoofType) getCurrentSession().get(
					"za.co.iclub.pss.orm.bean.IclubRoofType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubRoofType> findByExample(IclubRoofType instance) {
		log.debug("finding IclubRoofType instance by example");
		try {
			List<IclubRoofType> results = (List<IclubRoofType>) getCurrentSession()
					.createCriteria("za.co.iclub.pss.orm.bean.IclubRoofType")
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
		log.debug("finding IclubRoofType instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from IclubRoofType as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubRoofType> findByRtShortDesc(Object rtShortDesc) {
		return findByProperty(RT_SHORT_DESC, rtShortDesc);
	}

	public List<IclubRoofType> findByRtLongDesc(Object rtLongDesc) {
		return findByProperty(RT_LONG_DESC, rtLongDesc);
	}

	public List<IclubRoofType> findByRtStatus(Object rtStatus) {
		return findByProperty(RT_STATUS, rtStatus);
	}

	public List findAll() {
		log.debug("finding all IclubRoofType instances");
		try {
			String queryString = "from IclubRoofType";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubRoofType merge(IclubRoofType detachedInstance) {
		log.debug("merging IclubRoofType instance");
		try {
			IclubRoofType result = (IclubRoofType) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubRoofType instance) {
		log.debug("attaching dirty IclubRoofType instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubRoofType instance) {
		log.debug("attaching clean IclubRoofType instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IclubRoofTypeDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (IclubRoofTypeDAO) ctx.getBean("IclubRoofTypeDAO");
	}
}