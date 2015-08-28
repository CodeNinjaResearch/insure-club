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

import za.co.iclub.pss.orm.bean.IclubLogin;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubLogin entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubLogin
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({"unchecked","rawtypes"})
public class IclubLoginDAO {
	private static final Logger log = LoggerFactory
			.getLogger(IclubLoginDAO.class);
	// property constants
	public static final String _LNAME = "LName";
	public static final String _LPASSWD = "LPasswd";
	public static final String _LSEC_ANS = "LSecAns";
	public static final String _LPROVIDER_CD = "LProviderCd";
	public static final String _LPROVIDER_ID = "LProviderId";

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

	public void save(IclubLogin transientInstance) {
		log.debug("saving IclubLogin instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubLogin persistentInstance) {
		log.debug("deleting IclubLogin instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubLogin findById(java.lang.String id) {
		log.debug("getting IclubLogin instance with id: " + id);
		try {
			IclubLogin instance = (IclubLogin) getCurrentSession().get(
					"za.co.iclub.pss.orm.bean.IclubLogin", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubLogin> findByExample(IclubLogin instance) {
		log.debug("finding IclubLogin instance by example");
		try {
			List<IclubLogin> results = (List<IclubLogin>) getCurrentSession()
					.createCriteria("za.co.iclub.pss.orm.bean.IclubLogin")
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
		log.debug("finding IclubLogin instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from IclubLogin as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubLogin> findByLName(Object LName) {
		return findByProperty(_LNAME, LName);
	}

	public List<IclubLogin> findByLPasswd(Object LPasswd) {
		return findByProperty(_LPASSWD, LPasswd);
	}

	public List<IclubLogin> findByLSecAns(Object LSecAns) {
		return findByProperty(_LSEC_ANS, LSecAns);
	}

	public List<IclubLogin> findByLProviderCd(Object LProviderCd) {
		return findByProperty(_LPROVIDER_CD, LProviderCd);
	}

	public List<IclubLogin> findByLProviderId(Object LProviderId) {
		return findByProperty(_LPROVIDER_ID, LProviderId);
	}

	public List findAll() {
		log.debug("finding all IclubLogin instances");
		try {
			String queryString = "from IclubLogin";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubLogin merge(IclubLogin detachedInstance) {
		log.debug("merging IclubLogin instance");
		try {
			IclubLogin result = (IclubLogin) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubLogin instance) {
		log.debug("attaching dirty IclubLogin instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubLogin instance) {
		log.debug("attaching clean IclubLogin instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IclubLoginDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IclubLoginDAO) ctx.getBean("IclubLoginDAO");
	}
}