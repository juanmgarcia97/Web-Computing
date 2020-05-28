package co.edu.icesi.fi.tics.tssc.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.fi.tics.tssc.model.TsscTopic;

@Repository
@Scope("singleton")
public class TsscTopicDao implements ITsscTopicDao{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(TsscTopic entity) {
		// TODO Auto-generated method stub
		entityManager.persist(entity);
	}

	@Override
	public void update(TsscTopic entity) {
		// TODO Auto-generated method stub
		System.out.println(entity.getName() + " Dao chistoso");
		entityManager.merge(entity);
	}

	@Override
	public void delete(TsscTopic entity) {
		// TODO Auto-generated method stub
		entityManager.remove(entity);
	}

	@Override
	public TsscTopic findById(long id) {
		// TODO Auto-generated method stub
		return entityManager.find(TsscTopic.class, id);
	}

	@Override
	public List<TsscTopic> findByName(String name) {
		String jpql = "SELECT a FROM TsscTopic a WHERE a.name = '" + name + "'";
		return entityManager.createQuery(jpql, TsscTopic.class).getResultList();
	}

	@Override
	public List<TsscTopic> findByDescription(String description) {
		String jpql = "SELECT a FROM TsscTopic a WHERE a.description = '" + description + "'";
		return entityManager.createQuery(jpql, TsscTopic.class).getResultList();
	}
	
	@Override
	public List<TsscTopic> findAll() {
		String jpql = "SELECT a FROM TsscTopic a";
		return 	entityManager.createQuery(jpql, TsscTopic.class).getResultList();
	}



}
