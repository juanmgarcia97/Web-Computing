package co.edu.icesi.fi.tics.tssc.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.fi.tics.tssc.model.TsscTimecontrol;

@Repository
@Scope("singleton")
public class TsscTimecontrolDao implements ITsscTimecontrolDao{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(TsscTimecontrol entity) {
		// TODO Auto-generated method stub
		entityManager.persist(entity);
	}

	@Override
	public void update(TsscTimecontrol entity) {
		// TODO Auto-generated method stub
		entityManager.merge(entity);
	}

	@Override
	public void delete(TsscTimecontrol entity) {
		// TODO Auto-generated method stub
		entityManager.remove(entity);
	}

	@Override
	public TsscTimecontrol findById(long codigo) {
		// TODO Auto-generated method stub
		return entityManager.find(TsscTimecontrol.class, codigo);
	}

	@Override
	public List<TsscTimecontrol> findAll() {
		String jpql = "SELECT a FROM TsscTimecontrol a";
		return entityManager.createQuery(jpql, TsscTimecontrol.class).getResultList();
	}

}
