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

import za.co.iclub.pss.orm.bean.IclubPerson;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubPerson entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubPerson
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubPersonDAO {
	private static final Logger log = LoggerFactory.getLogger(IclubPersonDAO.class);
	// property constants
	public static final String _PTITLE = "PTitle";
	public static final String _PINITIALS = "PInitials";
	public static final String _PFNAME = "PFName";
	public static final String _PLNAME = "PLName";
	public static final String _PMOBILE = "PMobile";
	public static final String _PEMAIL = "PEmail";
	public static final String _PCONTACT_PREF = "PContactPref";
	public static final String _PGENDER = "PGender";
	public static final String _PID_NUM = "PIdNum";
	public static final String _PID_ISSUE_CNTRY = "PIdIssueCntry";
	public static final String _POCCUPATION = "POccupation";
	public static final String _PIS_PENSIONER = "PIsPensioner";
	public static final String _PADDRESS = "PAddress";
	public static final String _PLAT = "PLat";
	public static final String _PLONG = "PLong";
	public static final String _PZIP_CD = "PZipCd";
	public static final String _PAGE = "PAge";

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

	public void save(IclubPerson transientInstance) {
		log.debug("saving IclubPerson instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubPerson persistentInstance) {
		log.debug("deleting IclubPerson instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubPerson findById(java.lang.String id) {
		log.debug("getting IclubPerson instance with id: " + id);
		try {
			IclubPerson instance = (IclubPerson) getCurrentSession().get("za.co.iclub.pss.orm.bean.IclubPerson", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubPerson> findByExample(IclubPerson instance) {
		log.debug("finding IclubPerson instance by example");
		try {
			List<IclubPerson> results = (List<IclubPerson>) getCurrentSession().createCriteria("za.co.iclub.pss.orm.bean.IclubPerson").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IclubPerson instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IclubPerson as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubPerson> findByPTitle(Object PTitle) {
		return findByProperty(_PTITLE, PTitle);
	}

	public List<IclubPerson> findByPInitials(Object PInitials) {
		return findByProperty(_PINITIALS, PInitials);
	}

	public List<IclubPerson> findByPFName(Object PFName) {
		return findByProperty(_PFNAME, PFName);
	}

	public List<IclubPerson> findByPLName(Object PLName) {
		return findByProperty(_PLNAME, PLName);
	}

	public List<IclubPerson> findByPMobile(Object PMobile) {
		return findByProperty(_PMOBILE, PMobile);
	}

	public List<IclubPerson> findByPEmail(Object PEmail) {
		return findByProperty(_PEMAIL, PEmail);
	}

	public List<IclubPerson> findByPContactPref(Object PContactPref) {
		return findByProperty(_PCONTACT_PREF, PContactPref);
	}

	public List<IclubPerson> findByPGender(Object PGender) {
		return findByProperty(_PGENDER, PGender);
	}

	public List<IclubPerson> findByPIdNum(Object PIdNum) {
		return findByProperty(_PID_NUM, PIdNum);
	}

	public List<IclubPerson> findByPIdIssueCntry(Object PIdIssueCntry) {
		return findByProperty(_PID_ISSUE_CNTRY, PIdIssueCntry);
	}

	public List<IclubPerson> findByPOccupation(Object POccupation) {
		return findByProperty(_POCCUPATION, POccupation);
	}

	public List<IclubPerson> findByPIsPensioner(Object PIsPensioner) {
		return findByProperty(_PIS_PENSIONER, PIsPensioner);
	}

	public List<IclubPerson> findByPAddress(Object PAddress) {
		return findByProperty(_PADDRESS, PAddress);
	}

	public List<IclubPerson> findByPLat(Object PLat) {
		return findByProperty(_PLAT, PLat);
	}

	public List<IclubPerson> findByPLong(Object PLong) {
		return findByProperty(_PLONG, PLong);
	}

	public List<IclubPerson> findByPZipCd(Object PZipCd) {
		return findByProperty(_PZIP_CD, PZipCd);
	}

	public List<IclubPerson> findByPAge(Object PAge) {
		return findByProperty(_PAGE, PAge);
	}

	public List findAll() {
		log.debug("finding all IclubPerson instances");
		try {
			String queryString = "from IclubPerson";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubPerson merge(IclubPerson detachedInstance) {
		log.debug("merging IclubPerson instance");
		try {
			IclubPerson result = (IclubPerson) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubPerson instance) {
		log.debug("attaching dirty IclubPerson instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubPerson instance) {
		log.debug("attaching clean IclubPerson instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IclubPersonDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IclubPersonDAO) ctx.getBean("IclubPersonDAO");
	}
}