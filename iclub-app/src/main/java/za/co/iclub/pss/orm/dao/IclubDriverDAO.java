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

import za.co.iclub.pss.orm.bean.IclubDriver;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubDriver entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubDriver
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubDriverDAO {
	private static final Logger log = LoggerFactory.getLogger(IclubDriverDAO.class);
	// property constants
	public static final String _DNAME = "DName";
	public static final String _DLICENSE_NUM = "DLicenseNum";
	public static final String _DISSUE_YEARS = "DIssueYears";
	public static final String _DLAST_CLAIM_DIFF = "DLastClaimDiff";
	public static final String _DLAST_CLAIM_YEAR = "DLastClaimYear";

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

	public void save(IclubDriver transientInstance) {
		log.debug("saving IclubDriver instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubDriver persistentInstance) {
		log.debug("deleting IclubDriver instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubDriver findById(java.lang.String id) {
		log.debug("getting IclubDriver instance with id: " + id);
		try {
			IclubDriver instance = (IclubDriver) getCurrentSession().get("za.co.iclub.pss.orm.bean.IclubDriver", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubDriver> findByExample(IclubDriver instance) {
		log.debug("finding IclubDriver instance by example");
		try {
			List<IclubDriver> results = (List<IclubDriver>) getCurrentSession().createCriteria("za.co.iclub.pss.orm.bean.IclubDriver").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IclubDriver instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IclubDriver as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubDriver> findByDName(Object DName) {
		return findByProperty(_DNAME, DName);
	}

	public List<IclubDriver> findByDLicenseNum(Object DLicenseNum) {
		return findByProperty(_DLICENSE_NUM, DLicenseNum);
	}

	public List<IclubDriver> findByDIssueYears(Object DIssueYears) {
		return findByProperty(_DISSUE_YEARS, DIssueYears);
	}

	public List<IclubDriver> findByDLastClaimDiff(Object DLastClaimDiff) {
		return findByProperty(_DLAST_CLAIM_DIFF, DLastClaimDiff);
	}

	public List<IclubDriver> findByDLastClaimYear(Object DLastClaimYear) {
		return findByProperty(_DLAST_CLAIM_YEAR, DLastClaimYear);
	}

	public List findAll() {
		log.debug("finding all IclubDriver instances");
		try {
			String queryString = "from IclubDriver";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubDriver merge(IclubDriver detachedInstance) {
		log.debug("merging IclubDriver instance");
		try {
			IclubDriver result = (IclubDriver) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubDriver instance) {
		log.debug("attaching dirty IclubDriver instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubDriver instance) {
		log.debug("attaching clean IclubDriver instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IclubDriverDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IclubDriverDAO) ctx.getBean("IclubDriverDAO");
	}
}