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

import za.co.iclub.pss.orm.bean.IclubVehSecType;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubVehSecType entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubVehSecType
 * @author MyEclipse Persistence Tools
 */
@Transactional@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubVehSecTypeDAO {
	private static final Logger log = Logger.getLogger(IclubVehSecTypeDAO.class);
	// property constants
	public static final String VST_SHORT_DESC = "vstShortDesc";
	public static final String VST_LONG_DESC = "vstLongDesc";
	public static final String VST_STATUS = "vstStatus";

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

	public void save(IclubVehSecType transientInstance) {
		log.debug("saving IclubVehSecType instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubVehSecType persistentInstance) {
		log.debug("deleting IclubVehSecType instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubVehSecType findById(java.lang.Long id) {
		log.debug("getting IclubVehSecType instance with id: " + id);
		try {
			IclubVehSecType instance = (IclubVehSecType) getCurrentSession().get("za.co.iclub.pss.orm.bean.IclubVehSecType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubVehSecType> findByExample(IclubVehSecType instance) {
		log.debug("finding IclubVehSecType instance by example");
		try {
			List<IclubVehSecType> results = (List<IclubVehSecType>) getCurrentSession().createCriteria("za.co.iclub.pss.orm.bean.IclubVehSecType").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IclubVehSecType instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IclubVehSecType as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubVehSecType> findByVstShortDesc(Object vstShortDesc) {
		return findByProperty(VST_SHORT_DESC, vstShortDesc);
	}

	public List<IclubVehSecType> findByVstLongDesc(Object vstLongDesc) {
		return findByProperty(VST_LONG_DESC, vstLongDesc);
	}

	public List<IclubVehSecType> findByVstStatus(Object vstStatus) {
		return findByProperty(VST_STATUS, vstStatus);
	}

	public List findAll() {
		log.debug("finding all IclubVehSecType instances");
		try {
			String queryString = "from IclubVehSecType";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubVehSecType merge(IclubVehSecType detachedInstance) {
		log.debug("merging IclubVehSecType instance");
		try {
			IclubVehSecType result = (IclubVehSecType) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubVehSecType instance) {
		log.debug("attaching dirty IclubVehSecType instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubVehSecType instance) {
		log.debug("attaching clean IclubVehSecType instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IclubVehSecTypeDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IclubVehSecTypeDAO) ctx.getBean("IclubVehSecTypeDAO");
	}
}