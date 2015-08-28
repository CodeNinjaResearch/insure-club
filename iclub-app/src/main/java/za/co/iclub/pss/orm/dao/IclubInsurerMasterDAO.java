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

import za.co.iclub.pss.orm.bean.IclubInsurerMaster;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubInsurerMaster entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubInsurerMaster
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({"unchecked","rawtypes"})
public class IclubInsurerMasterDAO {
	private static final Logger log = LoggerFactory
			.getLogger(IclubInsurerMasterDAO.class);
	// property constants
	public static final String IM_NAME = "imName";
	public static final String IM_TRADE_NAME = "imTradeName";
	public static final String IM_LOCATION = "imLocation";
	public static final String IM_LAT = "imLat";
	public static final String IM_LONG = "imLong";
	public static final String IM_REG_NUM = "imRegNum";

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

	public void save(IclubInsurerMaster transientInstance) {
		log.debug("saving IclubInsurerMaster instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubInsurerMaster persistentInstance) {
		log.debug("deleting IclubInsurerMaster instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubInsurerMaster findById(java.lang.Long id) {
		log.debug("getting IclubInsurerMaster instance with id: " + id);
		try {
			IclubInsurerMaster instance = (IclubInsurerMaster) getCurrentSession()
					.get("za.co.iclub.pss.orm.bean.IclubInsurerMaster", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubInsurerMaster> findByExample(IclubInsurerMaster instance) {
		log.debug("finding IclubInsurerMaster instance by example");
		try {
			List<IclubInsurerMaster> results = (List<IclubInsurerMaster>) getCurrentSession()
					.createCriteria(
							"za.co.iclub.pss.orm.bean.IclubInsurerMaster")
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
		log.debug("finding IclubInsurerMaster instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from IclubInsurerMaster as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubInsurerMaster> findByImName(Object imName) {
		return findByProperty(IM_NAME, imName);
	}

	public List<IclubInsurerMaster> findByImTradeName(Object imTradeName) {
		return findByProperty(IM_TRADE_NAME, imTradeName);
	}

	public List<IclubInsurerMaster> findByImLocation(Object imLocation) {
		return findByProperty(IM_LOCATION, imLocation);
	}

	public List<IclubInsurerMaster> findByImLat(Object imLat) {
		return findByProperty(IM_LAT, imLat);
	}

	public List<IclubInsurerMaster> findByImLong(Object imLong) {
		return findByProperty(IM_LONG, imLong);
	}

	public List<IclubInsurerMaster> findByImRegNum(Object imRegNum) {
		return findByProperty(IM_REG_NUM, imRegNum);
	}

	public List findAll() {
		log.debug("finding all IclubInsurerMaster instances");
		try {
			String queryString = "from IclubInsurerMaster";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubInsurerMaster merge(IclubInsurerMaster detachedInstance) {
		log.debug("merging IclubInsurerMaster instance");
		try {
			IclubInsurerMaster result = (IclubInsurerMaster) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubInsurerMaster instance) {
		log.debug("attaching dirty IclubInsurerMaster instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubInsurerMaster instance) {
		log.debug("attaching clean IclubInsurerMaster instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IclubInsurerMasterDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (IclubInsurerMasterDAO) ctx.getBean("IclubInsurerMasterDAO");
	}
}