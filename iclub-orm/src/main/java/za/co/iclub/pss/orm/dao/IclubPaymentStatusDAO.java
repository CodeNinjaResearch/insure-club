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

import za.co.iclub.pss.orm.bean.IclubPaymentStatus;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubPaymentStatus entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubPaymentStatus
 * @author Venu Madhav Pattamatta
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubPaymentStatusDAO {
	private static final Logger log = Logger.getLogger(IclubPaymentStatusDAO.class);
	// property constants
	public static final String PS_SHORT_DESC = "psShortDesc";
	public static final String PS_LONG_DESC = "psLongDesc";
	public static final String PS_STATUS = "psStatus";

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

	public void save(IclubPaymentStatus transientInstance) {
		log.debug("saving IclubPaymentStatus instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubPaymentStatus persistentInstance) {
		log.debug("deleting IclubPaymentStatus instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubPaymentStatus findById(java.lang.Long id) {
		log.debug("getting IclubPaymentStatus instance with id: " + id);
		try {
			IclubPaymentStatus instance = (IclubPaymentStatus) getCurrentSession().get("za.co.iclub.pss.orm.bean.IclubPaymentStatus", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubPaymentStatus> findByExample(IclubPaymentStatus instance) {
		log.debug("finding IclubPaymentStatus instance by example");
		try {
			List<IclubPaymentStatus> results = (List<IclubPaymentStatus>) getCurrentSession().createCriteria("za.co.iclub.pss.orm.bean.IclubPaymentStatus").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IclubPaymentStatus instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IclubPaymentStatus as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubPaymentStatus> findByPsShortDesc(Object psShortDesc) {
		return findByProperty(PS_SHORT_DESC, psShortDesc);
	}

	public List<IclubPaymentStatus> findByPsLongDesc(Object psLongDesc) {
		return findByProperty(PS_LONG_DESC, psLongDesc);
	}

	public List<IclubPaymentStatus> findByPsStatus(Object psStatus) {
		return findByProperty(PS_STATUS, psStatus);
	}

	public List findAll() {
		log.debug("finding all IclubPaymentStatus instances");
		try {
			String queryString = "from IclubPaymentStatus";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubPaymentStatus merge(IclubPaymentStatus detachedInstance) {
		log.debug("merging IclubPaymentStatus instance");
		try {
			IclubPaymentStatus result = (IclubPaymentStatus) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubPaymentStatus instance) {
		log.debug("attaching dirty IclubPaymentStatus instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubPaymentStatus instance) {
		log.debug("attaching clean IclubPaymentStatus instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IclubPaymentStatusDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IclubPaymentStatusDAO) ctx.getBean("IclubPaymentStatusDAO");
	}
}