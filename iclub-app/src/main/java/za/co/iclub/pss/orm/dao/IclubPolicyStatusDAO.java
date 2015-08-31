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

import za.co.iclub.pss.orm.bean.IclubPolicyStatus;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubPolicyStatus entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubPolicyStatus
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubPolicyStatusDAO {
	private static final Logger log = LoggerFactory.getLogger(IclubPolicyStatusDAO.class);
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
	
	public void save(IclubPolicyStatus transientInstance) {
		log.debug("saving IclubPolicyStatus instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	public void delete(IclubPolicyStatus persistentInstance) {
		log.debug("deleting IclubPolicyStatus instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	public IclubPolicyStatus findById(java.lang.Long id) {
		log.debug("getting IclubPolicyStatus instance with id: " + id);
		try {
			IclubPolicyStatus instance = (IclubPolicyStatus) getCurrentSession().get("za.co.iclub.pss.orm.bean.IclubPolicyStatus", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List<IclubPolicyStatus> findByExample(IclubPolicyStatus instance) {
		log.debug("finding IclubPolicyStatus instance by example");
		try {
			List<IclubPolicyStatus> results = (List<IclubPolicyStatus>) getCurrentSession().createCriteria("za.co.iclub.pss.orm.bean.IclubPolicyStatus").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IclubPolicyStatus instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IclubPolicyStatus as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List<IclubPolicyStatus> findByPsShortDesc(Object psShortDesc) {
		return findByProperty(PS_SHORT_DESC, psShortDesc);
	}
	
	public List<IclubPolicyStatus> findByPsLongDesc(Object psLongDesc) {
		return findByProperty(PS_LONG_DESC, psLongDesc);
	}
	
	public List<IclubPolicyStatus> findByPsStatus(Object psStatus) {
		return findByProperty(PS_STATUS, psStatus);
	}
	
	public List findAll() {
		log.debug("finding all IclubPolicyStatus instances");
		try {
			String queryString = "from IclubPolicyStatus";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public IclubPolicyStatus merge(IclubPolicyStatus detachedInstance) {
		log.debug("merging IclubPolicyStatus instance");
		try {
			IclubPolicyStatus result = (IclubPolicyStatus) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}
	
	public void attachDirty(IclubPolicyStatus instance) {
		log.debug("attaching dirty IclubPolicyStatus instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public void attachClean(IclubPolicyStatus instance) {
		log.debug("attaching clean IclubPolicyStatus instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public static IclubPolicyStatusDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IclubPolicyStatusDAO) ctx.getBean("IclubPolicyStatusDAO");
	}
}