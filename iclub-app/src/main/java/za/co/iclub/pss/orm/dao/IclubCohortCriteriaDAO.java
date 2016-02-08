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

import za.co.iclub.pss.orm.bean.IclubCohortCriteria;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubCohortCriteria entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubCohortCriteria
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubCohortCriteriaDAO {
	private static final Logger log = LoggerFactory.getLogger(IclubCohortCriteriaDAO.class);
	// property constants
	public static final String ICC_AGE = "iccAge";
	public static final String ICC_GENDER = "iccGender";
	public static final String ICC_CLAIM_LAST_TW_YRS = "iccClaimLastTwYrs";
	public static final String ICC_CLAIM_LAST_YR = "iccClaimLastYr";

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

	public void save(IclubCohortCriteria transientInstance) {
		log.debug("saving IclubCohortCriteria instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubCohortCriteria persistentInstance) {
		log.debug("deleting IclubCohortCriteria instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubCohortCriteria findById(java.lang.String id) {
		log.debug("getting IclubCohortCriteria instance with id: " + id);
		try {
			IclubCohortCriteria instance = (IclubCohortCriteria) getCurrentSession().get("za.co.iclub.pss.orm.bean.IclubCohortCriteria", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubCohortCriteria> findByExample(IclubCohortCriteria instance) {
		log.debug("finding IclubCohortCriteria instance by example");
		try {
			List<IclubCohortCriteria> results = (List<IclubCohortCriteria>) getCurrentSession().createCriteria("za.co.iclub.pss.orm.bean.IclubCohortCriteria").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IclubCohortCriteria instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IclubCohortCriteria as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubCohortCriteria> findByIccAge(Object iccAge) {
		return findByProperty(ICC_AGE, iccAge);
	}

	public List<IclubCohortCriteria> findByIccGender(Object iccGender) {
		return findByProperty(ICC_GENDER, iccGender);
	}

	public List<IclubCohortCriteria> findByIccClaimLastTwYrs(Object iccClaimLastTwYrs) {
		return findByProperty(ICC_CLAIM_LAST_TW_YRS, iccClaimLastTwYrs);
	}

	public List<IclubCohortCriteria> findByIccClaimLastYr(Object iccClaimLastYr) {
		return findByProperty(ICC_CLAIM_LAST_YR, iccClaimLastYr);
	}

	public List findAll() {
		log.debug("finding all IclubCohortCriteria instances");
		try {
			String queryString = "from IclubCohortCriteria";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubCohortCriteria merge(IclubCohortCriteria detachedInstance) {
		log.debug("merging IclubCohortCriteria instance");
		try {
			IclubCohortCriteria result = (IclubCohortCriteria) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubCohortCriteria instance) {
		log.debug("attaching dirty IclubCohortCriteria instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubCohortCriteria instance) {
		log.debug("attaching clean IclubCohortCriteria instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IclubCohortCriteriaDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IclubCohortCriteriaDAO) ctx.getBean("IclubCohortCriteriaDAO");
	}
}