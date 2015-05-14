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

import za.co.iclub.pss.orm.bean.IclubQuoteStatus;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubQuoteStatus entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubQuoteStatus
 * @author MyEclipse Persistence Tools
 */
@Transactional@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubQuoteStatusDAO {
	private static final Logger log = Logger.getLogger(IclubQuoteStatusDAO.class);
	// property constants
	public static final String QS_SHORT_DESC = "qsShortDesc";
	public static final String QS_LONG_DESC = "qsLongDesc";
	public static final String QS_STATUS = "qsStatus";

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

	public void save(IclubQuoteStatus transientInstance) {
		log.debug("saving IclubQuoteStatus instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubQuoteStatus persistentInstance) {
		log.debug("deleting IclubQuoteStatus instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubQuoteStatus findById(java.lang.Long id) {
		log.debug("getting IclubQuoteStatus instance with id: " + id);
		try {
			IclubQuoteStatus instance = (IclubQuoteStatus) getCurrentSession().get("za.co.iclub.pss.orm.bean.IclubQuoteStatus", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubQuoteStatus> findByExample(IclubQuoteStatus instance) {
		log.debug("finding IclubQuoteStatus instance by example");
		try {
			List<IclubQuoteStatus> results = (List<IclubQuoteStatus>) getCurrentSession().createCriteria("za.co.iclub.pss.orm.bean.IclubQuoteStatus").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IclubQuoteStatus instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IclubQuoteStatus as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubQuoteStatus> findByQsShortDesc(Object qsShortDesc) {
		return findByProperty(QS_SHORT_DESC, qsShortDesc);
	}

	public List<IclubQuoteStatus> findByQsLongDesc(Object qsLongDesc) {
		return findByProperty(QS_LONG_DESC, qsLongDesc);
	}

	public List<IclubQuoteStatus> findByQsStatus(Object qsStatus) {
		return findByProperty(QS_STATUS, qsStatus);
	}

	public List findAll() {
		log.debug("finding all IclubQuoteStatus instances");
		try {
			String queryString = "from IclubQuoteStatus";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubQuoteStatus merge(IclubQuoteStatus detachedInstance) {
		log.debug("merging IclubQuoteStatus instance");
		try {
			IclubQuoteStatus result = (IclubQuoteStatus) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubQuoteStatus instance) {
		log.debug("attaching dirty IclubQuoteStatus instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubQuoteStatus instance) {
		log.debug("attaching clean IclubQuoteStatus instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IclubQuoteStatusDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IclubQuoteStatusDAO) ctx.getBean("IclubQuoteStatusDAO");
	}
}