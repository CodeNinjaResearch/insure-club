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

import za.co.iclub.pss.orm.bean.IclubExtras;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubExtras entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubExtras
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubExtrasDAO {
	private static final Logger log = LoggerFactory.getLogger(IclubExtrasDAO.class);
	// property constants
	public static final String _EDESC = "EDesc";
	public static final String _ESTATUS = "EStatus";

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

	public void save(IclubExtras transientInstance) {
		log.debug("saving IclubExtras instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubExtras persistentInstance) {
		log.debug("deleting IclubExtras instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubExtras findById(java.lang.Long id) {
		log.debug("getting IclubExtras instance with id: " + id);
		try {
			IclubExtras instance = (IclubExtras) getCurrentSession().get("za.co.iclub.pss.orm.bean.IclubExtras", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubExtras> findByExample(IclubExtras instance) {
		log.debug("finding IclubExtras instance by example");
		try {
			List<IclubExtras> results = (List<IclubExtras>) getCurrentSession().createCriteria("za.co.iclub.pss.orm.bean.IclubExtras").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IclubExtras instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IclubExtras as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubExtras> findByEDesc(Object EDesc) {
		return findByProperty(_EDESC, EDesc);
	}

	public List<IclubExtras> findByEStatus(Object EStatus) {
		return findByProperty(_ESTATUS, EStatus);
	}

	public List findAll() {
		log.debug("finding all IclubExtras instances");
		try {
			String queryString = "from IclubExtras";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubExtras merge(IclubExtras detachedInstance) {
		log.debug("merging IclubExtras instance");
		try {
			IclubExtras result = (IclubExtras) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubExtras instance) {
		log.debug("attaching dirty IclubExtras instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubExtras instance) {
		log.debug("attaching clean IclubExtras instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IclubExtrasDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IclubExtrasDAO) ctx.getBean("IclubExtrasDAO");
	}
}