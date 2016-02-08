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

import za.co.iclub.pss.orm.bean.IclubClaimItem;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubClaimItem entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubClaimItem
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubClaimItemDAO {
	private static final Logger log = LoggerFactory.getLogger(IclubClaimItemDAO.class);
	// property constants
	public static final String CI_VALUE = "ciValue";
	public static final String CI_CRTD_BY = "ciCrtdBy";

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

	public void save(IclubClaimItem transientInstance) {
		log.debug("saving IclubClaimItem instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubClaimItem persistentInstance) {
		log.debug("deleting IclubClaimItem instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubClaimItem findById(java.lang.String id) {
		log.debug("getting IclubClaimItem instance with id: " + id);
		try {
			IclubClaimItem instance = (IclubClaimItem) getCurrentSession().get("za.co.iclub.pss.orm.bean.IclubClaimItem", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubClaimItem> findByExample(IclubClaimItem instance) {
		log.debug("finding IclubClaimItem instance by example");
		try {
			List<IclubClaimItem> results = (List<IclubClaimItem>) getCurrentSession().createCriteria("za.co.iclub.pss.orm.bean.IclubClaimItem").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IclubClaimItem instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IclubClaimItem as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubClaimItem> findByCiValue(Object ciValue) {
		return findByProperty(CI_VALUE, ciValue);
	}

	public List<IclubClaimItem> findByCiCrtdBy(Object ciCrtdBy) {
		return findByProperty(CI_CRTD_BY, ciCrtdBy);
	}

	public List findAll() {
		log.debug("finding all IclubClaimItem instances");
		try {
			String queryString = "from IclubClaimItem";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubClaimItem merge(IclubClaimItem detachedInstance) {
		log.debug("merging IclubClaimItem instance");
		try {
			IclubClaimItem result = (IclubClaimItem) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubClaimItem instance) {
		log.debug("attaching dirty IclubClaimItem instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubClaimItem instance) {
		log.debug("attaching clean IclubClaimItem instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IclubClaimItemDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IclubClaimItemDAO) ctx.getBean("IclubClaimItemDAO");
	}
}