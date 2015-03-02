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

import za.co.iclub.pss.orm.bean.IclubConfig;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubConfig entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubConfig
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubConfigDAO {
	private static final Logger log = Logger
			.getLogger(IclubConfigDAO.class);
	// property constants
	public static final String _CKEY = "CKey";
	public static final String _CVALUE = "CValue";
	public static final String _CSTATUS = "CStatus";

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

	public void save(IclubConfig transientInstance) {
		log.debug("saving IclubConfig instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubConfig persistentInstance) {
		log.debug("deleting IclubConfig instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubConfig findById(java.lang.Long id) {
		log.debug("getting IclubConfig instance with id: " + id);
		try {
			IclubConfig instance = (IclubConfig) getCurrentSession().get(
					"za.co.iclub.pss.orm.bean.IclubConfig", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubConfig> findByExample(IclubConfig instance) {
		log.debug("finding IclubConfig instance by example");
		try {
			List<IclubConfig> results = (List<IclubConfig>) getCurrentSession()
					.createCriteria("za.co.iclub.pss.orm.bean.IclubConfig")
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
		log.debug("finding IclubConfig instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from IclubConfig as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubConfig> findByCKey(Object CKey) {
		return findByProperty(_CKEY, CKey);
	}

	public List<IclubConfig> findByCValue(Object CValue) {
		return findByProperty(_CVALUE, CValue);
	}

	public List<IclubConfig> findByCStatus(Object CStatus) {
		return findByProperty(_CSTATUS, CStatus);
	}

	public List findAll() {
		log.debug("finding all IclubConfig instances");
		try {
			String queryString = "from IclubConfig";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubConfig merge(IclubConfig detachedInstance) {
		log.debug("merging IclubConfig instance");
		try {
			IclubConfig result = (IclubConfig) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubConfig instance) {
		log.debug("attaching dirty IclubConfig instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubConfig instance) {
		log.debug("attaching clean IclubConfig instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IclubConfigDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (IclubConfigDAO) ctx.getBean("IclubConfigDAO");
	}
}