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

import za.co.iclub.pss.orm.bean.IclubPaymentType;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubPaymentType entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubPaymentType
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubPaymentTypeDAO {
	private static final Logger log = LoggerFactory.getLogger(IclubPaymentTypeDAO.class);
	// property constants
	public static final String PT_SHORT_DESC = "ptShortDesc";
	public static final String PT_LONG_DESC = "ptLongDesc";
	public static final String PT_STATUS = "ptStatus";

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

	public void save(IclubPaymentType transientInstance) {
		log.debug("saving IclubPaymentType instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubPaymentType persistentInstance) {
		log.debug("deleting IclubPaymentType instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubPaymentType findById(java.lang.Long id) {
		log.debug("getting IclubPaymentType instance with id: " + id);
		try {
			IclubPaymentType instance = (IclubPaymentType) getCurrentSession().get("za.co.iclub.pss.orm.bean.IclubPaymentType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubPaymentType> findByExample(IclubPaymentType instance) {
		log.debug("finding IclubPaymentType instance by example");
		try {
			List<IclubPaymentType> results = (List<IclubPaymentType>) getCurrentSession().createCriteria("za.co.iclub.pss.orm.bean.IclubPaymentType").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IclubPaymentType instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IclubPaymentType as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubPaymentType> findByPtShortDesc(Object ptShortDesc) {
		return findByProperty(PT_SHORT_DESC, ptShortDesc);
	}

	public List<IclubPaymentType> findByPtLongDesc(Object ptLongDesc) {
		return findByProperty(PT_LONG_DESC, ptLongDesc);
	}

	public List<IclubPaymentType> findByPtStatus(Object ptStatus) {
		return findByProperty(PT_STATUS, ptStatus);
	}

	public List findAll() {
		log.debug("finding all IclubPaymentType instances");
		try {
			String queryString = "from IclubPaymentType";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubPaymentType merge(IclubPaymentType detachedInstance) {
		log.debug("merging IclubPaymentType instance");
		try {
			IclubPaymentType result = (IclubPaymentType) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubPaymentType instance) {
		log.debug("attaching dirty IclubPaymentType instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubPaymentType instance) {
		log.debug("attaching clean IclubPaymentType instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IclubPaymentTypeDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IclubPaymentTypeDAO) ctx.getBean("IclubPaymentTypeDAO");
	}
}