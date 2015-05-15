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

import za.co.iclub.pss.orm.bean.IclubPayment;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubPayment entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubPayment
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubPaymentDAO {
	private static final Logger log = Logger.getLogger(IclubPaymentDAO.class);
	// property constants
	public static final String _PVALUE = "PValue";
	public static final String _PDR_CR_IND = "PDrCrInd";

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

	public void save(IclubPayment transientInstance) {
		log.debug("saving IclubPayment instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubPayment persistentInstance) {
		log.debug("deleting IclubPayment instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubPayment findById(java.lang.String id) {
		log.debug("getting IclubPayment instance with id: " + id);
		try {
			IclubPayment instance = (IclubPayment) getCurrentSession().get("za.co.iclub.pss.orm.bean.IclubPayment", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubPayment> findByExample(IclubPayment instance) {
		log.debug("finding IclubPayment instance by example");
		try {
			List<IclubPayment> results = (List<IclubPayment>) getCurrentSession().createCriteria("za.co.iclub.pss.orm.bean.IclubPayment").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IclubPayment instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IclubPayment as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubPayment> findByPValue(Object PValue) {
		return findByProperty(_PVALUE, PValue);
	}

	public List<IclubPayment> findByPDrCrInd(Object PDrCrInd) {
		return findByProperty(_PDR_CR_IND, PDrCrInd);
	}

	public List findAll() {
		log.debug("finding all IclubPayment instances");
		try {
			String queryString = "from IclubPayment";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubPayment merge(IclubPayment detachedInstance) {
		log.debug("merging IclubPayment instance");
		try {
			IclubPayment result = (IclubPayment) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubPayment instance) {
		log.debug("attaching dirty IclubPayment instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubPayment instance) {
		log.debug("attaching clean IclubPayment instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IclubPaymentDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IclubPaymentDAO) ctx.getBean("IclubPaymentDAO");
	}
}