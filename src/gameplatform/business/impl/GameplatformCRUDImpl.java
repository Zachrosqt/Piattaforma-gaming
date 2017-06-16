package gameplatform.business.impl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import gameplatform.business.GameplatformCRUD;

public class GameplatformCRUDImpl implements GameplatformCRUD{
	
	private volatile static GameplatformCRUDImpl istanza = null;
	
	private GameplatformCRUDImpl() {}
	
	public static GameplatformCRUDImpl getGameplatformCRUDImpl() {
        if (istanza == null) {            
            synchronized (GameplatformCRUDImpl.class){
                if (istanza == null)
                    istanza = new GameplatformCRUDImpl();
            }
        }
        return istanza;
    }
	
	@Override
	public <T> void save(T obj) {
		
		Configuration conf = new Configuration().configure();
		Session session = conf.buildSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		session.save(obj);
		
		session.getTransaction().commit();
		
		session.close();
		
	}

	@Override
	public <T> void update(T obj) {
		
		Configuration conf = new Configuration().configure();
		Session session = conf.buildSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		session.update(obj);
		
		session.getTransaction().commit();
		
		session.close();
		
	}

	@Override
	public <T> void saveOrUpdate(T obj) {
		
		Configuration conf = new Configuration().configure();
		Session session = conf.buildSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		session.saveOrUpdate(obj);
		
		session.getTransaction().commit();
		
		session.close();
		
	}

	@Override
	public <T> void delete(T obj) {
		
		Configuration conf = new Configuration().configure();
		Session session = conf.buildSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		session.delete(obj);
		
		session.getTransaction().commit();
		
		session.close();
	}

	@Override
	public <T> List<T> executeQuery(String query) {
		Configuration conf = new Configuration().configure();
		Session session = conf.buildSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Query insideQuery = session.createQuery(query);
		List<T> queryList = insideQuery.getResultList();
		session.getTransaction().commit();
		
		session.close();
		
		return queryList;
	}
}
