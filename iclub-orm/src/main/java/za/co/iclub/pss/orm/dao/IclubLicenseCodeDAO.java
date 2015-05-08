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

import za.co.iclub.pss.orm.bean.IclubLicenseCode;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubLicenseCode entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubLicenseCode
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubLicenseCodeDAO {
	private static final Logger log = Logger.getLogger(IclubLicenseCodeDAO.class);
	// property constants
	public static final String LC_CATEGORY = "lcCategory";
	public static final String LC_DESC = "lcDesc";
	public static final String LC_STATUS = "lcStatus";

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

	public void save(IclubLicenseCode transientInstance) {
		log.debug("saving IclubLicenseCode instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubLicenseCode persistentInstance) {
		log.debug("deleting IclubLicenseCode instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubLicenseCode findById(java.lang.Long id) {
		log.debug("getting IclubLicenseCode instance with id: " + id);
		try {
			IclubLicenseCode instance = (IclubLicenseCode) getCurrentSession().get("za.co.iclub.pss.orm.bean.IclubLicenseCode", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubLicenseCode> findByExample(IclubLicenseCode instance) {
		log.debug("finding IclubLicenseCode instance by example");
		try {
			List<IclubLicenseCode> results = (List<IclubLicenseCode>) getCurrentSession().createCriteria("za.co.iclub.pss.orm.bean.IclubLicenseCode").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IclubLicenseCode instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IclubLicenseCode as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubLicenseCode> findByLcCategory(Object lcCategory) {
		return findByProperty(LC_CATEGORY, lcCategory);
	}

	public List<IclubLicenseCode> findByLcDesc(Object lcDesc) {
		return findByProperty(LC_DESC, lcDesc);
	}

	public List<IclubLicenseCode> findByLcStatus(Object lcStatus) {
		return findByProperty(LC_STATUS, lcStatus);
	}

	public List findAll() {
		log.debug("finding all IclubLicenseCode instances");
		try {
			String queryString = "from IclubLicenseCode";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubLicenseCode merge(IclubLicenseCode detachedInstance) {
		log.debug("merging IclubLicenseCode instance");
		try {
			IclubLicenseCode result = (IclubLicenseCode) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubLicenseCode instance) {
		log.debug("attaching dirty IclubLicenseCode instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubLicenseCode instance) {
		log.debug("attaching clean IclubLicenseCode instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IclubLicenseCodeDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IclubLicenseCodeDAO) ctx.getBean("IclubLicenseCodeDAO");
	}
}