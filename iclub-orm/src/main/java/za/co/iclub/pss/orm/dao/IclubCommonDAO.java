package za.co.iclub.pss.orm.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubCommonDAO {

	private static final Logger log = Logger.getLogger(IclubCommonDAO.class);
	
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

	public Long getNextId(Class clazz) {
		Long ret = -1l;
		try {
			AbstractEntityPersister persister = (AbstractEntityPersister) sessionFactory.getClassMetadata(clazz);
			String sql = "select ifnull(max(" + persister.getIdentifierColumnNames()[0] + "),0)+1 from " + persister.getTableName();
			SQLQuery query = getCurrentSession().createSQLQuery(sql);
			List res = query.list();
			if (res != null && res.size() > 0)
				ret = Long.valueOf(res.get(0).toString());
		} catch (Exception e) {
			log.error(e, e);
			throw new RuntimeException(e);
		}
		return ret;
	}

	public Long[] getNextIds(Class clazz, int num) {
		Long[] ret = new Long[num];
		try {
			AbstractEntityPersister persister = (AbstractEntityPersister) sessionFactory.getClassMetadata(clazz);
			String sql = "select ifnull(max(" + persister.getIdentifierColumnNames()[0] + "),0)+1 from " + persister.getTableName();
			SQLQuery query = getCurrentSession().createSQLQuery(sql);
			List res = query.list();
			if (res != null && res.size() > 0)
				ret[0] = Long.valueOf(res.get(0).toString());
			for (int i = 1; i < num; i++)
				ret[i] = ret[0] + i;
		} catch (Exception e) {
			log.error(e, e);
			throw new RuntimeException(e);
		}
		return ret;
	}

	public static IclubCommonDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IclubCommonDAO) ctx.getBean("IclubCommonDAO");
	}

}