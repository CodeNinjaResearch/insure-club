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

import za.co.iclub.pss.orm.bean.IclubSupplMaster;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubSupplMaster entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubSupplMaster
 * @author MyEclipse Persistence Tools
 */
@Transactional@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubSupplMasterDAO {
	private static final Logger log = Logger.getLogger(IclubSupplMasterDAO.class);
	// property constants
	public static final String SM_NAME = "smName";
	public static final String SM_TRADE_NAME = "smTradeName";
	public static final String SM_REG_NUM = "smRegNum";
	public static final String SM_ADDRESS = "smAddress";
	public static final String SM_LAT = "smLat";
	public static final String SM_LONG = "smLong";
	public static final String SM_CR_LIMIT = "smCrLimit";
	public static final String SM_RATING = "smRating";

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

	public void save(IclubSupplMaster transientInstance) {
		log.debug("saving IclubSupplMaster instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubSupplMaster persistentInstance) {
		log.debug("deleting IclubSupplMaster instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubSupplMaster findById(java.lang.String id) {
		log.debug("getting IclubSupplMaster instance with id: " + id);
		try {
			IclubSupplMaster instance = (IclubSupplMaster) getCurrentSession().get("za.co.iclub.pss.orm.bean.IclubSupplMaster", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubSupplMaster> findByExample(IclubSupplMaster instance) {
		log.debug("finding IclubSupplMaster instance by example");
		try {
			List<IclubSupplMaster> results = (List<IclubSupplMaster>) getCurrentSession().createCriteria("za.co.iclub.pss.orm.bean.IclubSupplMaster").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IclubSupplMaster instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IclubSupplMaster as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubSupplMaster> findBySmName(Object smName) {
		return findByProperty(SM_NAME, smName);
	}

	public List<IclubSupplMaster> findBySmTradeName(Object smTradeName) {
		return findByProperty(SM_TRADE_NAME, smTradeName);
	}

	public List<IclubSupplMaster> findBySmRegNum(Object smRegNum) {
		return findByProperty(SM_REG_NUM, smRegNum);
	}

	public List<IclubSupplMaster> findBySmAddress(Object smAddress) {
		return findByProperty(SM_ADDRESS, smAddress);
	}

	public List<IclubSupplMaster> findBySmLat(Object smLat) {
		return findByProperty(SM_LAT, smLat);
	}

	public List<IclubSupplMaster> findBySmLong(Object smLong) {
		return findByProperty(SM_LONG, smLong);
	}

	public List<IclubSupplMaster> findBySmCrLimit(Object smCrLimit) {
		return findByProperty(SM_CR_LIMIT, smCrLimit);
	}

	public List<IclubSupplMaster> findBySmRating(Object smRating) {
		return findByProperty(SM_RATING, smRating);
	}

	public List findAll() {
		log.debug("finding all IclubSupplMaster instances");
		try {
			String queryString = "from IclubSupplMaster";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubSupplMaster merge(IclubSupplMaster detachedInstance) {
		log.debug("merging IclubSupplMaster instance");
		try {
			IclubSupplMaster result = (IclubSupplMaster) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubSupplMaster instance) {
		log.debug("attaching dirty IclubSupplMaster instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubSupplMaster instance) {
		log.debug("attaching clean IclubSupplMaster instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IclubSupplMasterDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IclubSupplMasterDAO) ctx.getBean("IclubSupplMasterDAO");
	}
}