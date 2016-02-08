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

import za.co.iclub.pss.orm.bean.IclubVehicleType;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubVehicleType entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubVehicleType
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubVehicleTypeDAO {
	private static final Logger log = LoggerFactory.getLogger(IclubVehicleTypeDAO.class);
	// property constants
	public static final String VT_SHORT_DESC = "vtShortDesc";
	public static final String VT_LONG_DESC = "vtLongDesc";
	public static final String VT_STATUS = "vtStatus";

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

	public void save(IclubVehicleType transientInstance) {
		log.debug("saving IclubVehicleType instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubVehicleType persistentInstance) {
		log.debug("deleting IclubVehicleType instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubVehicleType findById(java.lang.Long id) {
		log.debug("getting IclubVehicleType instance with id: " + id);
		try {
			IclubVehicleType instance = (IclubVehicleType) getCurrentSession().get("za.co.iclub.pss.orm.bean.IclubVehicleType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubVehicleType> findByExample(IclubVehicleType instance) {
		log.debug("finding IclubVehicleType instance by example");
		try {
			List<IclubVehicleType> results = (List<IclubVehicleType>) getCurrentSession().createCriteria("za.co.iclub.pss.orm.bean.IclubVehicleType").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IclubVehicleType instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IclubVehicleType as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubVehicleType> findByVtShortDesc(Object vtShortDesc) {
		return findByProperty(VT_SHORT_DESC, vtShortDesc);
	}

	public List<IclubVehicleType> findByVtLongDesc(Object vtLongDesc) {
		return findByProperty(VT_LONG_DESC, vtLongDesc);
	}

	public List<IclubVehicleType> findByVtStatus(Object vtStatus) {
		return findByProperty(VT_STATUS, vtStatus);
	}

	public List findAll() {
		log.debug("finding all IclubVehicleType instances");
		try {
			String queryString = "from IclubVehicleType";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubVehicleType merge(IclubVehicleType detachedInstance) {
		log.debug("merging IclubVehicleType instance");
		try {
			IclubVehicleType result = (IclubVehicleType) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubVehicleType instance) {
		log.debug("attaching dirty IclubVehicleType instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubVehicleType instance) {
		log.debug("attaching clean IclubVehicleType instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IclubVehicleTypeDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IclubVehicleTypeDAO) ctx.getBean("IclubVehicleTypeDAO");
	}
}