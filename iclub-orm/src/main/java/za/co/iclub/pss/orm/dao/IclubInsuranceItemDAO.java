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

import za.co.iclub.pss.orm.bean.IclubInsuranceItem;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubInsuranceItem entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubInsuranceItem
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubInsuranceItemDAO {
	private static final Logger log = Logger.getLogger(IclubInsuranceItemDAO.class);
	// property constants
	public static final String II_QUOTE_ID = "iiQuoteId";
	public static final String II_ITEM_ID = "iiItemId";

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

	public void save(IclubInsuranceItem transientInstance) {
		log.debug("saving IclubInsuranceItem instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubInsuranceItem persistentInstance) {
		log.debug("deleting IclubInsuranceItem instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubInsuranceItem findById(java.lang.String id) {
		log.debug("getting IclubInsuranceItem instance with id: " + id);
		try {
			IclubInsuranceItem instance = (IclubInsuranceItem) getCurrentSession().get("za.co.iclub.pss.orm.bean.IclubInsuranceItem", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubInsuranceItem> findByExample(IclubInsuranceItem instance) {
		log.debug("finding IclubInsuranceItem instance by example");
		try {
			List<IclubInsuranceItem> results = (List<IclubInsuranceItem>) getCurrentSession().createCriteria("za.co.iclub.pss.orm.bean.IclubInsuranceItem").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IclubInsuranceItem instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IclubInsuranceItem as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubInsuranceItem> findByIiQuoteId(Object iiQuoteId) {
		return findByProperty(II_QUOTE_ID, iiQuoteId);
	}

	public List<IclubInsuranceItem> findByIiItemId(Object iiItemId) {
		return findByProperty(II_ITEM_ID, iiItemId);
	}

	public List findAll() {
		log.debug("finding all IclubInsuranceItem instances");
		try {
			String queryString = "from IclubInsuranceItem";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubInsuranceItem merge(IclubInsuranceItem detachedInstance) {
		log.debug("merging IclubInsuranceItem instance");
		try {
			IclubInsuranceItem result = (IclubInsuranceItem) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubInsuranceItem instance) {
		log.debug("attaching dirty IclubInsuranceItem instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubInsuranceItem instance) {
		log.debug("attaching clean IclubInsuranceItem instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List findByUser(String userId) {
		log.debug("finding all IclubInsuranceItem instances by user");
		try {
			Query queryObject = getCurrentSession().getNamedQuery("getInsuranceItemByUser");
			queryObject.setString("id", userId);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all by user failed", re);
			throw re;
		}
	}

	public static IclubInsuranceItemDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IclubInsuranceItemDAO) ctx.getBean("IclubInsuranceItemDAO");
	}
}