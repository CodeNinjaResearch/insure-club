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

import za.co.iclub.pss.orm.bean.IclubAssessmentType;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubAssessmentType entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubAssessmentType
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubAssessmentTypeDAO {
	private static final Logger log = LoggerFactory.getLogger(IclubAssessmentTypeDAO.class);
	// property constants
	public static final String AT_LONG_DESC = "atLongDesc";
	public static final String AT_SHORT_DESC = "atShortDesc";
	public static final String AT_STATUS = "atStatus";

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

	public void save(IclubAssessmentType transientInstance) {
		log.debug("saving IclubAssessmentType instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubAssessmentType persistentInstance) {
		log.debug("deleting IclubAssessmentType instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubAssessmentType findById(java.lang.Long id) {
		log.debug("getting IclubAssessmentType instance with id: " + id);
		try {
			IclubAssessmentType instance = (IclubAssessmentType) getCurrentSession().get("za.co.iclub.pss.orm.bean.IclubAssessmentType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubAssessmentType> findByExample(IclubAssessmentType instance) {
		log.debug("finding IclubAssessmentType instance by example");
		try {
			List<IclubAssessmentType> results = (List<IclubAssessmentType>) getCurrentSession().createCriteria("za.co.iclub.pss.orm.bean.IclubAssessmentType").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IclubAssessmentType instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IclubAssessmentType as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubAssessmentType> findByAtLongDesc(Object atLongDesc) {
		return findByProperty(AT_LONG_DESC, atLongDesc);
	}

	public List<IclubAssessmentType> findByAtShortDesc(Object atShortDesc) {
		return findByProperty(AT_SHORT_DESC, atShortDesc);
	}

	public List<IclubAssessmentType> findByAtStatus(Object atStatus) {
		return findByProperty(AT_STATUS, atStatus);
	}

	public List findAll() {
		log.debug("finding all IclubAssessmentType instances");
		try {
			String queryString = "from IclubAssessmentType";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubAssessmentType merge(IclubAssessmentType detachedInstance) {
		log.debug("merging IclubAssessmentType instance");
		try {
			IclubAssessmentType result = (IclubAssessmentType) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubAssessmentType instance) {
		log.debug("attaching dirty IclubAssessmentType instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubAssessmentType instance) {
		log.debug("attaching clean IclubAssessmentType instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IclubAssessmentTypeDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IclubAssessmentTypeDAO) ctx.getBean("IclubAssessmentTypeDAO");
	}
}