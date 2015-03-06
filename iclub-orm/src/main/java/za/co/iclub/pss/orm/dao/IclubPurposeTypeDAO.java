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

import za.co.iclub.pss.orm.bean.IclubPurposeType;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubPurposeType entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubPurposeType
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubPurposeTypeDAO {
	private static final Logger log = Logger.getLogger(IclubPurposeTypeDAO.class);
	// property constants
	public static final String PT_SHORT_DESC = "ptShortDesc";
	public static final String PT_LONG_DESC = "ptLongDesc";
	public static final String PT_STATUS = "ptStatus";

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

	public void save(IclubPurposeType transientInstance) {
		log.debug("saving IclubPurposeType instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubPurposeType persistentInstance) {
		log.debug("deleting IclubPurposeType instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubPurposeType findById(java.lang.Long id) {
		log.debug("getting IclubPurposeType instance with id: " + id);
		try {
			IclubPurposeType instance = (IclubPurposeType) getCurrentSession().get("za.co.iclub.pss.orm.bean.IclubPurposeType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubPurposeType> findByExample(IclubPurposeType instance) {
		log.debug("finding IclubPurposeType instance by example");
		try {
			List<IclubPurposeType> results = (List<IclubPurposeType>) getCurrentSession().createCriteria("za.co.iclub.pss.orm.bean.IclubPurposeType").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IclubPurposeType instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IclubPurposeType as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubPurposeType> findByPtShortDesc(Object ptShortDesc) {
		return findByProperty(PT_SHORT_DESC, ptShortDesc);
	}

	public List<IclubPurposeType> findByPtLongDesc(Object ptLongDesc) {
		return findByProperty(PT_LONG_DESC, ptLongDesc);
	}

	public List<IclubPurposeType> findByPtStatus(Object ptStatus) {
		return findByProperty(PT_STATUS, ptStatus);
	}

	public List findAll() {
		log.debug("finding all IclubPurposeType instances");
		try {
			String queryString = "from IclubPurposeType";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubPurposeType merge(IclubPurposeType detachedInstance) {
		log.debug("merging IclubPurposeType instance");
		try {
			IclubPurposeType result = (IclubPurposeType) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubPurposeType instance) {
		log.debug("attaching dirty IclubPurposeType instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubPurposeType instance) {
		log.debug("attaching clean IclubPurposeType instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List findByUser(String userId) {
		log.debug("finding all IclubPurposeType instances by user");
		try {
			Query queryObject = getCurrentSession().getNamedQuery("getPurposeTypeByUser");
			queryObject.setString("id", userId);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all by user failed", re);
			throw re;
		}
	}

	public static IclubPurposeTypeDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IclubPurposeTypeDAO) ctx.getBean("IclubPurposeTypeDAO");
	}
}