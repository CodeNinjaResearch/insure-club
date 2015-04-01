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

import za.co.iclub.pss.orm.bean.IclubOccupation;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubOccupation entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubOccupation
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubOccupationDAO {
	private static final Logger log = Logger.getLogger(IclubOccupationDAO.class);
	// property constants
	public static final String _ODESC = "ODesc";
	public static final String _OSTATUS = "OStatus";

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

	public void save(IclubOccupation transientInstance) {
		log.debug("saving IclubOccupation instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubOccupation persistentInstance) {
		log.debug("deleting IclubOccupation instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubOccupation findById(java.lang.Long id) {
		log.debug("getting IclubOccupation instance with id: " + id);
		try {
			IclubOccupation instance = (IclubOccupation) getCurrentSession().get("za.co.iclub.pss.orm.bean.IclubOccupation", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubOccupation> findByExample(IclubOccupation instance) {
		log.debug("finding IclubOccupation instance by example");
		try {
			List<IclubOccupation> results = (List<IclubOccupation>) getCurrentSession().createCriteria("za.co.iclub.pss.orm.bean.IclubOccupation").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IclubOccupation instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IclubOccupation as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubOccupation> findByODesc(Object ODesc) {
		return findByProperty(_ODESC, ODesc);
	}

	public List<IclubOccupation> findByOStatus(Object OStatus) {
		return findByProperty(_OSTATUS, OStatus);
	}

	public List findAll() {
		log.debug("finding all IclubOccupation instances");
		try {
			String queryString = "from IclubOccupation";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubOccupation merge(IclubOccupation detachedInstance) {
		log.debug("merging IclubOccupation instance");
		try {
			IclubOccupation result = (IclubOccupation) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubOccupation instance) {
		log.debug("attaching dirty IclubOccupation instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubOccupation instance) {
		log.debug("attaching clean IclubOccupation instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IclubOccupationDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IclubOccupationDAO) ctx.getBean("IclubOccupationDAO");
	}
}