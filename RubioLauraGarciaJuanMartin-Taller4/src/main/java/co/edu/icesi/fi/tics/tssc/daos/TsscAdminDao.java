package co.edu.icesi.fi.tics.tssc.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.fi.tics.tssc.model.TsscAdmin;

@Repository
@Scope("singleton")
public class TsscAdminDao implements ITsscAdminDao{

	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public void save(TsscAdmin entity) {
		// TODO Auto-generated method stub
		entityManager.persist(entity);
	}

	@Override
	public void update(TsscAdmin entity) {
		// TODO Auto-generated method stub
		entityManager.merge(entity);
	}

	@Override
	public void delete(TsscAdmin entity) {
		// TODO Auto-generated method stub
		entityManager.remove(entity);
	}

	@Override
	public TsscAdmin findById(long id) {
		return entityManager.find(TsscAdmin.class, id);
	}

	@Override
	public List<TsscAdmin> findAll() {
		String jpql = "Select a from TsscAdmin a";
		return 	entityManager.createQuery(jpql, TsscAdmin.class).getResultList();
	}

}
