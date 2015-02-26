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

import za.co.iclub.pss.orm.bean.IclubInsuranceItemType;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubInsuranceItemType entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubInsuranceItemType
 * @author Venu Madhav Pattamatta
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubInsuranceItemTypeDAO {
	private static final Logger log = Logger
			.getLogger(IclubInsuranceItemTypeDAO.class);
	// property constants
	public static final String IIT_SHORT_DESC = "iitShortDesc";
	public static final String IIT_LONG_DESC = "iitLongDesc";
	public static final String IIT_STATUS = "iitStatus";

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

	public void save(IclubInsuranceItemType transientInstance) {
		log.debug("saving IclubInsuranceItemType instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubInsuranceItemType persistentInstance) {
		log.debug("deleting IclubInsuranceItemType instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubInsuranceItemType findById(java.lang.Long id) {
		log.debug("getting IclubInsuranceItemType instance with id: " + id);
		try {
			IclubInsuranceItemType instance = (IclubInsuranceItemType) getCurrentSession()
					.get("za.co.iclub.pss.orm.bean.IclubInsuranceItemType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubInsuranceItemType> findByExample(
			IclubInsuranceItemType instance) {
		log.debug("finding IclubInsuranceItemType instance by example");
		try {
			List<IclubInsuranceItemType> results = (List<IclubInsuranceItemType>) getCurrentSession()
					.createCriteria(
							"za.co.iclub.pss.orm.bean.IclubInsuranceItemType")
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
		log.debug("finding IclubInsuranceItemType instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from IclubInsuranceItemType as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubInsuranceItemType> findByIitShortDesc(Object iitShortDesc) {
		return findByProperty(IIT_SHORT_DESC, iitShortDesc);
	}

	public List<IclubInsuranceItemType> findByIitLongDesc(Object iitLongDesc) {
		return findByProperty(IIT_LONG_DESC, iitLongDesc);
	}

	public List<IclubInsuranceItemType> findByIitStatus(Object iitStatus) {
		return findByProperty(IIT_STATUS, iitStatus);
	}

	public List findAll() {
		log.debug("finding all IclubInsuranceItemType instances");
		try {
			String queryString = "from IclubInsuranceItemType";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubInsuranceItemType merge(IclubInsuranceItemType detachedInstance) {
		log.debug("merging IclubInsuranceItemType instance");
		try {
			IclubInsuranceItemType result = (IclubInsuranceItemType) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubInsuranceItemType instance) {
		log.debug("attaching dirty IclubInsuranceItemType instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubInsuranceItemType instance) {
		log.debug("attaching clean IclubInsuranceItemType instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List getInsuranceItemTypeBySD(String sd, Long id) {
		log.debug("Fetching all Batch by Query :: getInsuranceItemTypeBySD");
		try {
			Query query = getCurrentSession().getNamedQuery("getInsuranceItemTypeBySD");
			query.setString("sd", sd);
			query.setLong("id", id);
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("Entity Cat", re);
			throw re;
		}
	}

	public static IclubInsuranceItemTypeDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (IclubInsuranceItemTypeDAO) ctx
				.getBean("IclubInsuranceItemTypeDAO");
	}
}