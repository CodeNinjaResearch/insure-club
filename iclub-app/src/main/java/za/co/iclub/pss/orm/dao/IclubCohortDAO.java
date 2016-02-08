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

import za.co.iclub.pss.orm.bean.IclubCohort;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubCohort entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubCohort
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubCohortDAO {
	private static final Logger log = LoggerFactory.getLogger(IclubCohortDAO.class);
	// property constants
	public static final String _CNAME = "CName";
	public static final String _CEMAIL = "CEmail";
	public static final String _CTOTAL_CONTRIB = "CTotalContrib";
	public static final String _CCOLLECTED_CONTRIB = "CCollectedContrib";
	public static final String _CCUR_MEMBER_CNT = "CCurMemberCnt";

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

	public void save(IclubCohort transientInstance) {
		log.debug("saving IclubCohort instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubCohort persistentInstance) {
		log.debug("deleting IclubCohort instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubCohort findById(java.lang.String id) {
		log.debug("getting IclubCohort instance with id: " + id);
		try {
			IclubCohort instance = (IclubCohort) getCurrentSession().get("za.co.iclub.pss.orm.bean.IclubCohort", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubCohort> findByExample(IclubCohort instance) {
		log.debug("finding IclubCohort instance by example");
		try {
			List<IclubCohort> results = (List<IclubCohort>) getCurrentSession().createCriteria("za.co.iclub.pss.orm.bean.IclubCohort").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IclubCohort instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IclubCohort as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubCohort> findByCName(Object CName) {
		return findByProperty(_CNAME, CName);
	}

	public List<IclubCohort> findByCEmail(Object CEmail) {
		return findByProperty(_CEMAIL, CEmail);
	}

	public List<IclubCohort> findByCTotalContrib(Object CTotalContrib) {
		return findByProperty(_CTOTAL_CONTRIB, CTotalContrib);
	}

	public List<IclubCohort> findByCCollectedContrib(Object CCollectedContrib) {
		return findByProperty(_CCOLLECTED_CONTRIB, CCollectedContrib);
	}

	public List<IclubCohort> findByCCurMemberCnt(Object CCurMemberCnt) {
		return findByProperty(_CCUR_MEMBER_CNT, CCurMemberCnt);
	}

	public List findAll() {
		log.debug("finding all IclubCohort instances");
		try {
			String queryString = "from IclubCohort";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubCohort merge(IclubCohort detachedInstance) {
		log.debug("merging IclubCohort instance");
		try {
			IclubCohort result = (IclubCohort) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubCohort instance) {
		log.debug("attaching dirty IclubCohort instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubCohort instance) {
		log.debug("attaching clean IclubCohort instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IclubCohortDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IclubCohortDAO) ctx.getBean("IclubCohortDAO");
	}
}