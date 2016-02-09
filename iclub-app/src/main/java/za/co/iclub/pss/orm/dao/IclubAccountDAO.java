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

import za.co.iclub.pss.orm.bean.IclubAccount;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubAccount entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubAccount
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubAccountDAO {
	private static final Logger log = LoggerFactory.getLogger(IclubAccountDAO.class);
	// property constants
	public static final String _AACC_NUM = "AAccNum";
	public static final String _AOWNER_ID = "AOwnerId";
	public static final String _ASTATUS = "AStatus";

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

	public void save(IclubAccount transientInstance) {
		log.debug("saving IclubAccount instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubAccount persistentInstance) {
		log.debug("deleting IclubAccount instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubAccount findById(java.lang.String id) {
		log.debug("getting IclubAccount instance with id: " + id);
		try {
			IclubAccount instance = (IclubAccount) getCurrentSession().get("za.co.iclub.pss.orm.bean.IclubAccount", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubAccount> findByExample(IclubAccount instance) {
		log.debug("finding IclubAccount instance by example");
		try {
			List<IclubAccount> results = (List<IclubAccount>) getCurrentSession().createCriteria("za.co.iclub.pss.orm.bean.IclubAccount").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IclubAccount instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IclubAccount as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubAccount> findByAAccNum(Object AAccNum) {
		return findByProperty(_AACC_NUM, AAccNum);
	}

	public List<IclubAccount> findByAOwnerId(Object AOwnerId) {
		return findByProperty(_AOWNER_ID, AOwnerId);
	}

	public List<IclubAccount> findByAStatus(Object AStatus) {
		return findByProperty(_ASTATUS, AStatus);
	}

	public List findAll() {
		log.debug("finding all IclubAccount instances");
		try {
			String queryString = "from IclubAccount";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubAccount merge(IclubAccount detachedInstance) {
		log.debug("merging IclubAccount instance");
		try {
			IclubAccount result = (IclubAccount) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubAccount instance) {
		log.debug("attaching dirty IclubAccount instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubAccount instance) {
		log.debug("attaching clean IclubAccount instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IclubAccountDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IclubAccountDAO) ctx.getBean("IclubAccountDAO");
	}
}