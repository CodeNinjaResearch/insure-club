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

import za.co.iclub.pss.orm.bean.IclubMbComment;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubMbComment entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubMbComment
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubMbCommentDAO {
	private static final Logger log = Logger.getLogger(IclubMbCommentDAO.class);
	// property constants
	public static final String MBC_DESC = "mbcDesc";

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

	public void save(IclubMbComment transientInstance) {
		log.debug("saving IclubMbComment instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubMbComment persistentInstance) {
		log.debug("deleting IclubMbComment instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubMbComment findById(java.lang.String id) {
		log.debug("getting IclubMbComment instance with id: " + id);
		try {
			IclubMbComment instance = (IclubMbComment) getCurrentSession().get("za.co.iclub.pss.orm.bean.IclubMbComment", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubMbComment> findByExample(IclubMbComment instance) {
		log.debug("finding IclubMbComment instance by example");
		try {
			List<IclubMbComment> results = (List<IclubMbComment>) getCurrentSession().createCriteria("za.co.iclub.pss.orm.bean.IclubMbComment").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IclubMbComment instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IclubMbComment as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubMbComment> findByMbcDesc(Object mbcDesc) {
		return findByProperty(MBC_DESC, mbcDesc);
	}

	public List findAll() {
		log.debug("finding all IclubMbComment instances");
		try {
			String queryString = "from IclubMbComment";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubMbComment merge(IclubMbComment detachedInstance) {
		log.debug("merging IclubMbComment instance");
		try {
			IclubMbComment result = (IclubMbComment) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubMbComment instance) {
		log.debug("attaching dirty IclubMbComment instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubMbComment instance) {
		log.debug("attaching clean IclubMbComment instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List findByUser(String userId) {
		log.debug("finding all IclubMbComment instances by user");
		try {
			Query queryObject = getCurrentSession().getNamedQuery("getMbCommentByUser");
			queryObject.setString("id", userId);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all by user failed", re);
			throw re;
		}
	}
	
	public static IclubMbCommentDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IclubMbCommentDAO) ctx.getBean("IclubMbCommentDAO");
	}
}