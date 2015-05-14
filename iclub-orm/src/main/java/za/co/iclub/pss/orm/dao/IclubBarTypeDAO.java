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

import za.co.iclub.pss.orm.bean.IclubBarType;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubBarType entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubBarType
 * @author MyEclipse Persistence Tools
 */
@Transactional@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubBarTypeDAO {
	private static final Logger log = Logger.getLogger(IclubBarTypeDAO.class);
	// property constants
	public static final String BT_SHORT_DESC = "btShortDesc";
	public static final String BT_LONG_DESC = "btLongDesc";
	public static final String BT_STATUS = "btStatus";

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

	public void save(IclubBarType transientInstance) {
		log.debug("saving IclubBarType instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubBarType persistentInstance) {
		log.debug("deleting IclubBarType instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubBarType findById(java.lang.Long id) {
		log.debug("getting IclubBarType instance with id: " + id);
		try {
			IclubBarType instance = (IclubBarType) getCurrentSession().get("za.co.iclub.pss.orm.bean.IclubBarType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubBarType> findByExample(IclubBarType instance) {
		log.debug("finding IclubBarType instance by example");
		try {
			List<IclubBarType> results = (List<IclubBarType>) getCurrentSession().createCriteria("za.co.iclub.pss.orm.bean.IclubBarType").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IclubBarType instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IclubBarType as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubBarType> findByBtShortDesc(Object btShortDesc) {
		return findByProperty(BT_SHORT_DESC, btShortDesc);
	}

	public List<IclubBarType> findByBtLongDesc(Object btLongDesc) {
		return findByProperty(BT_LONG_DESC, btLongDesc);
	}

	public List<IclubBarType> findByBtStatus(Object btStatus) {
		return findByProperty(BT_STATUS, btStatus);
	}

	public List findAll() {
		log.debug("finding all IclubBarType instances");
		try {
			String queryString = "from IclubBarType";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubBarType merge(IclubBarType detachedInstance) {
		log.debug("merging IclubBarType instance");
		try {
			IclubBarType result = (IclubBarType) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubBarType instance) {
		log.debug("attaching dirty IclubBarType instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubBarType instance) {
		log.debug("attaching clean IclubBarType instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IclubBarTypeDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IclubBarTypeDAO) ctx.getBean("IclubBarTypeDAO");
	}
}