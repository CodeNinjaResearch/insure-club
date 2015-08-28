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

import za.co.iclub.pss.orm.bean.IclubMessageBoard;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubMessageBoard entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubMessageBoard
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({"unchecked","rawtypes"})
public class IclubMessageBoardDAO {
	private static final Logger log = LoggerFactory
			.getLogger(IclubMessageBoardDAO.class);
	// property constants
	public static final String MB_TITLE = "mbTitle";
	public static final String MB_CONTENT = "mbContent";
	public static final String MB_TAG = "mbTag";

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

	public void save(IclubMessageBoard transientInstance) {
		log.debug("saving IclubMessageBoard instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubMessageBoard persistentInstance) {
		log.debug("deleting IclubMessageBoard instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubMessageBoard findById(java.lang.String id) {
		log.debug("getting IclubMessageBoard instance with id: " + id);
		try {
			IclubMessageBoard instance = (IclubMessageBoard) getCurrentSession()
					.get("za.co.iclub.pss.orm.bean.IclubMessageBoard", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubMessageBoard> findByExample(IclubMessageBoard instance) {
		log.debug("finding IclubMessageBoard instance by example");
		try {
			List<IclubMessageBoard> results = (List<IclubMessageBoard>) getCurrentSession()
					.createCriteria(
							"za.co.iclub.pss.orm.bean.IclubMessageBoard")
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
		log.debug("finding IclubMessageBoard instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from IclubMessageBoard as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubMessageBoard> findByMbTitle(Object mbTitle) {
		return findByProperty(MB_TITLE, mbTitle);
	}

	public List<IclubMessageBoard> findByMbContent(Object mbContent) {
		return findByProperty(MB_CONTENT, mbContent);
	}

	public List<IclubMessageBoard> findByMbTag(Object mbTag) {
		return findByProperty(MB_TAG, mbTag);
	}

	public List findAll() {
		log.debug("finding all IclubMessageBoard instances");
		try {
			String queryString = "from IclubMessageBoard";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubMessageBoard merge(IclubMessageBoard detachedInstance) {
		log.debug("merging IclubMessageBoard instance");
		try {
			IclubMessageBoard result = (IclubMessageBoard) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubMessageBoard instance) {
		log.debug("attaching dirty IclubMessageBoard instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubMessageBoard instance) {
		log.debug("attaching clean IclubMessageBoard instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IclubMessageBoardDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (IclubMessageBoardDAO) ctx.getBean("IclubMessageBoardDAO");
	}
}