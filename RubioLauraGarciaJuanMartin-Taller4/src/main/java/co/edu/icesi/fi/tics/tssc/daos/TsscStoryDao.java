package co.edu.icesi.fi.tics.tssc.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.fi.tics.tssc.model.TsscStory;

@Repository
@Scope("singleton")
public class TsscStoryDao implements ITsscStoryDao{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(TsscStory entity) {
		// TODO Auto-generated method stub
		entityManager.persist(entity);
	}

	@Override
	public void update(TsscStory entity) {
		// TODO Auto-generated method stub
		entityManager.merge(entity);
	}

	@Override
	public void delete(TsscStory entity) {
		// TODO Auto-generated method stub
		entityManager.remove(entity);
	}

	@Override
	public TsscStory findById(long id) {
		// TODO Auto-generated method stub
		return entityManager.find(TsscStory.class, id);
	}

	@Override
	public List<TsscStory> findAll() {
		String jpql = "SELECT a FROM TsscStory a";
		return 	entityManager.createQuery(jpql, TsscStory.class).getResultList();
	}

}
