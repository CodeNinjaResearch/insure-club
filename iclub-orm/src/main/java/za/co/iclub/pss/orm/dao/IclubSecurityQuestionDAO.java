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

import za.co.iclub.pss.orm.bean.IclubSecurityQuestion;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubSecurityQuestion entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubSecurityQuestion
 * @author MyEclipse Persistence Tools
 */
@Transactional@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubSecurityQuestionDAO {
	private static final Logger log = Logger.getLogger(IclubSecurityQuestionDAO.class);
	// property constants
	public static final String SQ_SHORT_DESC = "sqShortDesc";
	public static final String SQ_LONG_DESC = "sqLongDesc";
	public static final String SQ_STATUS = "sqStatus";

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

	public void save(IclubSecurityQuestion transientInstance) {
		log.debug("saving IclubSecurityQuestion instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubSecurityQuestion persistentInstance) {
		log.debug("deleting IclubSecurityQuestion instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubSecurityQuestion findById(java.lang.Long id) {
		log.debug("getting IclubSecurityQuestion instance with id: " + id);
		try {
			IclubSecurityQuestion instance = (IclubSecurityQuestion) getCurrentSession().get("za.co.iclub.pss.orm.bean.IclubSecurityQuestion", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubSecurityQuestion> findByExample(IclubSecurityQuestion instance) {
		log.debug("finding IclubSecurityQuestion instance by example");
		try {
			List<IclubSecurityQuestion> results = (List<IclubSecurityQuestion>) getCurrentSession().createCriteria("za.co.iclub.pss.orm.bean.IclubSecurityQuestion").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IclubSecurityQuestion instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IclubSecurityQuestion as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubSecurityQuestion> findBySqShortDesc(Object sqShortDesc) {
		return findByProperty(SQ_SHORT_DESC, sqShortDesc);
	}

	public List<IclubSecurityQuestion> findBySqLongDesc(Object sqLongDesc) {
		return findByProperty(SQ_LONG_DESC, sqLongDesc);
	}

	public List<IclubSecurityQuestion> findBySqStatus(Object sqStatus) {
		return findByProperty(SQ_STATUS, sqStatus);
	}

	public List findAll() {
		log.debug("finding all IclubSecurityQuestion instances");
		try {
			String queryString = "from IclubSecurityQuestion";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubSecurityQuestion merge(IclubSecurityQuestion detachedInstance) {
		log.debug("merging IclubSecurityQuestion instance");
		try {
			IclubSecurityQuestion result = (IclubSecurityQuestion) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubSecurityQuestion instance) {
		log.debug("attaching dirty IclubSecurityQuestion instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubSecurityQuestion instance) {
		log.debug("attaching clean IclubSecurityQuestion instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IclubSecurityQuestionDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IclubSecurityQuestionDAO) ctx.getBean("IclubSecurityQuestionDAO");
	}
}