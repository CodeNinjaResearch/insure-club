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

import za.co.iclub.pss.orm.bean.IclubClaim;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubClaim entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubClaim
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubClaimDAO {
	private static final Logger log = Logger.getLogger(IclubClaimDAO.class);
	// property constants
	public static final String _CNUMBER = "CNumber";
	public static final String _CNUM_ITEMS = "CNumItems";
	public static final String _CVALUE = "CValue";

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

	public void save(IclubClaim transientInstance) {
		log.debug("saving IclubClaim instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubClaim persistentInstance) {
		log.debug("deleting IclubClaim instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubClaim findById(java.lang.String id) {
		log.debug("getting IclubClaim instance with id: " + id);
		try {
			IclubClaim instance = (IclubClaim) getCurrentSession().get("za.co.iclub.pss.orm.bean.IclubClaim", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubClaim> findByExample(IclubClaim instance) {
		log.debug("finding IclubClaim instance by example");
		try {
			List<IclubClaim> results = (List<IclubClaim>) getCurrentSession().createCriteria("za.co.iclub.pss.orm.bean.IclubClaim").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IclubClaim instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IclubClaim as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubClaim> findByCNumber(Object CNumber) {
		return findByProperty(_CNUMBER, CNumber);
	}

	public List<IclubClaim> findByCNumItems(Object CNumItems) {
		return findByProperty(_CNUM_ITEMS, CNumItems);
	}

	public List<IclubClaim> findByCValue(Object CValue) {
		return findByProperty(_CVALUE, CValue);
	}

	public List findAll() {
		log.debug("finding all IclubClaim instances");
		try {
			String queryString = "from IclubClaim";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubClaim merge(IclubClaim detachedInstance) {
		log.debug("merging IclubClaim instance");
		try {
			IclubClaim result = (IclubClaim) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubClaim instance) {
		log.debug("attaching dirty IclubClaim instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubClaim instance) {
		log.debug("attaching clean IclubClaim instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List findByUser(String userId) {
		log.debug("finding all IclubClaim instances by user");
		try {
			Query queryObject = getCurrentSession().getNamedQuery("getClaimByUser");
			queryObject.setString("id", userId);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all by user failed", re);
			throw re;
		}
	}
	
	public IclubClaim findByPolicyId(String policyId) {
		log.debug("finding all IclubClaim instances by user");
		try {
			Query queryObject = getCurrentSession().getNamedQuery("getClaimByPolicyId");
			queryObject.setString("policyId", policyId);
			return (IclubClaim)queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find all by user failed", re);
			throw re;
		}
	}

	public static IclubClaimDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IclubClaimDAO) ctx.getBean("IclubClaimDAO");
	}
}