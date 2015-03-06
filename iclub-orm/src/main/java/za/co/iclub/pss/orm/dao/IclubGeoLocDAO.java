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

import za.co.iclub.pss.orm.bean.IclubGeoLoc;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubGeoLoc entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubGeoLoc
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubGeoLocDAO {
	private static final Logger log = Logger.getLogger(IclubGeoLocDAO.class);
	// property constants
	public static final String GL_ADDRESS = "glAddress";
	public static final String GL_LAT = "glLat";
	public static final String GL_LONG = "glLong";
	public static final String GL_RATE = "glRate";

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

	public void save(IclubGeoLoc transientInstance) {
		log.debug("saving IclubGeoLoc instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubGeoLoc persistentInstance) {
		log.debug("deleting IclubGeoLoc instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubGeoLoc findById(java.lang.Long id) {
		log.debug("getting IclubGeoLoc instance with id: " + id);
		try {
			IclubGeoLoc instance = (IclubGeoLoc) getCurrentSession().get("za.co.iclub.pss.orm.bean.IclubGeoLoc", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubGeoLoc> findByExample(IclubGeoLoc instance) {
		log.debug("finding IclubGeoLoc instance by example");
		try {
			List<IclubGeoLoc> results = (List<IclubGeoLoc>) getCurrentSession().createCriteria("za.co.iclub.pss.orm.bean.IclubGeoLoc").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IclubGeoLoc instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IclubGeoLoc as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubGeoLoc> findByGlAddress(Object glAddress) {
		return findByProperty(GL_ADDRESS, glAddress);
	}

	public List<IclubGeoLoc> findByGlLat(Object glLat) {
		return findByProperty(GL_LAT, glLat);
	}

	public List<IclubGeoLoc> findByGlLong(Object glLong) {
		return findByProperty(GL_LONG, glLong);
	}

	public List<IclubGeoLoc> findByGlRate(Object glRate) {
		return findByProperty(GL_RATE, glRate);
	}

	public List findAll() {
		log.debug("finding all IclubGeoLoc instances");
		try {
			String queryString = "from IclubGeoLoc";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubGeoLoc merge(IclubGeoLoc detachedInstance) {
		log.debug("merging IclubGeoLoc instance");
		try {
			IclubGeoLoc result = (IclubGeoLoc) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubGeoLoc instance) {
		log.debug("attaching dirty IclubGeoLoc instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubGeoLoc instance) {
		log.debug("attaching clean IclubGeoLoc instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List findByUser(String userId) {
		log.debug("finding all IclubGeoLoc instances by user");
		try {
			Query queryObject = getCurrentSession().getNamedQuery("getGeoLocByUser");
			queryObject.setString("id", userId);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all by user failed", re);
			throw re;
		}
	}

	public static IclubGeoLocDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IclubGeoLocDAO) ctx.getBean("IclubGeoLocDAO");
	}
}