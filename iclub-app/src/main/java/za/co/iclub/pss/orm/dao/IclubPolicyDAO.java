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

import za.co.iclub.pss.orm.bean.IclubPolicy;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubPolicy entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubPolicy
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubPolicyDAO {
	private static final Logger log = Logger.getLogger(IclubPolicyDAO.class);
	// property constants
	public static final String _PNUMBER = "PNumber";
	public static final String _PPREMIUM = "PPremium";
	public static final String _PPRORATA_PRM = "PProrataPrm";
	public static final String _PDEBIT_DT = "PDebitDt";
	public static final String _PCRTD_DT = "PCrtdDt";

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

	public void save(IclubPolicy transientInstance) {
		log.debug("saving IclubPolicy instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubPolicy persistentInstance) {
		log.debug("deleting IclubPolicy instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubPolicy findById(java.lang.String id) {
		log.debug("getting IclubPolicy instance with id: " + id);
		try {
			IclubPolicy instance = (IclubPolicy) getCurrentSession().get("za.co.iclub.pss.orm.bean.IclubPolicy", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubPolicy> findByExample(IclubPolicy instance) {
		log.debug("finding IclubPolicy instance by example");
		try {
			List<IclubPolicy> results = (List<IclubPolicy>) getCurrentSession().createCriteria("za.co.iclub.pss.orm.bean.IclubPolicy").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IclubPolicy instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IclubPolicy as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubPolicy> findByPNumber(Object PNumber) {
		return findByProperty(_PNUMBER, PNumber);
	}

	public List<IclubPolicy> findByPPremium(Object PPremium) {
		return findByProperty(_PPREMIUM, PPremium);
	}

	public List<IclubPolicy> findByPProrataPrm(Object PProrataPrm) {
		return findByProperty(_PPRORATA_PRM, PProrataPrm);
	}

	public List<IclubPolicy> findByPDebitDt(Object PDebitDt) {
		return findByProperty(_PDEBIT_DT, PDebitDt);
	}

	public List<IclubPolicy> findByPCrtdDt(Object PCrtdDt) {
		return findByProperty(_PCRTD_DT, PCrtdDt);
	}

	public List findAll() {
		log.debug("finding all IclubPolicy instances");
		try {
			String queryString = "from IclubPolicy";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubPolicy merge(IclubPolicy detachedInstance) {
		log.debug("merging IclubPolicy instance");
		try {
			IclubPolicy result = (IclubPolicy) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubPolicy instance) {
		log.debug("attaching dirty IclubPolicy instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubPolicy instance) {
		log.debug("attaching clean IclubPolicy instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IclubPolicyDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IclubPolicyDAO) ctx.getBean("IclubPolicyDAO");
	}
}