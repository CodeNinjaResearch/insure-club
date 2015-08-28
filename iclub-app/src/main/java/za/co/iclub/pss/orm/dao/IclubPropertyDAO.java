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

import za.co.iclub.pss.orm.bean.IclubProperty;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubProperty entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubProperty
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({"unchecked","rawtypes"})
public class IclubPropertyDAO {
	private static final Logger log = LoggerFactory
			.getLogger(IclubPropertyDAO.class);
	// property constants
	public static final String _PREG_NUM = "PRegNum";
	public static final String _PADDRESS = "PAddress";
	public static final String _PLAT = "PLat";
	public static final String _PLONG = "PLong";
	public static final String _PPOSTAL_CD = "PPostalCd";
	public static final String _PNOCLAIM_YRS = "PNoclaimYrs";
	public static final String _PRENT_FUR_YN = "PRentFurYn";
	public static final String _PCOMP_YN = "PCompYn";
	public static final String _PNOROBBERY_YN = "PNorobberyYn";
	public static final String _PSEC_GATES_YN = "PSecGatesYn";
	public static final String _PEST_VALUE = "PEstValue";
	public static final String _PCONTENT_COST = "PContentCost";
	public static final String _PREPLACEMENT_COST = "PReplacementCost";
	public static final String _PTHATCH_TYPE = "PThatchType";

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

	public void save(IclubProperty transientInstance) {
		log.debug("saving IclubProperty instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubProperty persistentInstance) {
		log.debug("deleting IclubProperty instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubProperty findById(java.lang.String id) {
		log.debug("getting IclubProperty instance with id: " + id);
		try {
			IclubProperty instance = (IclubProperty) getCurrentSession().get(
					"za.co.iclub.pss.orm.bean.IclubProperty", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubProperty> findByExample(IclubProperty instance) {
		log.debug("finding IclubProperty instance by example");
		try {
			List<IclubProperty> results = (List<IclubProperty>) getCurrentSession()
					.createCriteria("za.co.iclub.pss.orm.bean.IclubProperty")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IclubProperty instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from IclubProperty as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubProperty> findByPRegNum(Object PRegNum) {
		return findByProperty(_PREG_NUM, PRegNum);
	}

	public List<IclubProperty> findByPAddress(Object PAddress) {
		return findByProperty(_PADDRESS, PAddress);
	}

	public List<IclubProperty> findByPLat(Object PLat) {
		return findByProperty(_PLAT, PLat);
	}

	public List<IclubProperty> findByPLong(Object PLong) {
		return findByProperty(_PLONG, PLong);
	}

	public List<IclubProperty> findByPPostalCd(Object PPostalCd) {
		return findByProperty(_PPOSTAL_CD, PPostalCd);
	}

	public List<IclubProperty> findByPNoclaimYrs(Object PNoclaimYrs) {
		return findByProperty(_PNOCLAIM_YRS, PNoclaimYrs);
	}

	public List<IclubProperty> findByPRentFurYn(Object PRentFurYn) {
		return findByProperty(_PRENT_FUR_YN, PRentFurYn);
	}

	public List<IclubProperty> findByPCompYn(Object PCompYn) {
		return findByProperty(_PCOMP_YN, PCompYn);
	}

	public List<IclubProperty> findByPNorobberyYn(Object PNorobberyYn) {
		return findByProperty(_PNOROBBERY_YN, PNorobberyYn);
	}

	public List<IclubProperty> findByPSecGatesYn(Object PSecGatesYn) {
		return findByProperty(_PSEC_GATES_YN, PSecGatesYn);
	}

	public List<IclubProperty> findByPEstValue(Object PEstValue) {
		return findByProperty(_PEST_VALUE, PEstValue);
	}

	public List<IclubProperty> findByPContentCost(Object PContentCost) {
		return findByProperty(_PCONTENT_COST, PContentCost);
	}

	public List<IclubProperty> findByPReplacementCost(Object PReplacementCost) {
		return findByProperty(_PREPLACEMENT_COST, PReplacementCost);
	}

	public List<IclubProperty> findByPThatchType(Object PThatchType) {
		return findByProperty(_PTHATCH_TYPE, PThatchType);
	}

	public List findAll() {
		log.debug("finding all IclubProperty instances");
		try {
			String queryString = "from IclubProperty";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubProperty merge(IclubProperty detachedInstance) {
		log.debug("merging IclubProperty instance");
		try {
			IclubProperty result = (IclubProperty) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubProperty instance) {
		log.debug("attaching dirty IclubProperty instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubProperty instance) {
		log.debug("attaching clean IclubProperty instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IclubPropertyDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (IclubPropertyDAO) ctx.getBean("IclubPropertyDAO");
	}
}