package co.edu.icesi.fi.tics.tssc.daos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.model.TsscStory;

@Repository
@Scope("singleton")
public class TsscGameDao implements ITsscGameDao{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(TsscGame entity) {
		// TODO Auto-generated method stub
		entityManager.persist(entity);
	}

	@Override
	public void update(TsscGame entity) {
		// TODO Auto-generated method stub
		entityManager.merge(entity);
	}

	@Override
	public void delete(TsscGame entity) {
		// TODO Auto-generated method stub
		entityManager.remove(entity);
	}

	@Override
	public TsscGame findById(long id) {
		// TODO Auto-generated method stub
		return entityManager.find(TsscGame.class, id);
	}

	@Override
	public List<TsscGame> findByName(String name) {
		String jpql = "SELECT a FROM TsscGame a WHERE a.tsscTopic.name = '" + name + "'";
		return entityManager.createQuery(jpql, TsscGame.class).getResultList();
	}

	@Override
	public List<TsscGame> findByDate(LocalDate init, LocalDate finish) {
		String jpql = "SELECT a FROM TsscGame a WHERE a.scheduledDate BETWEEN = '" + init + "' AND '" + finish + "'";
		return entityManager.createQuery(jpql, TsscGame.class).getResultList();
	}

	@Override
	public List<TsscGame> findByDateNTime(LocalDate date, LocalTime init, LocalTime finish) {
		String jpql = "SELECT a FROM TsscGame a WHERE a.scheduledDate = " + date + "AND WHERE a.scheduledTime BETWEEN = '" + init + "' AND '" + finish + "'";
		return entityManager.createQuery(jpql, TsscGame.class).getResultList();
	}

	@Override
	public List<TsscGame> findByTopicDescription(String description) {
		// TODO Auto-generated method stub
		String jpql = "SELECT a FROM TsscGame a WHERE a.tsscTopic.description = '" + description + "'";
		return entityManager.createQuery(jpql, TsscGame.class).getResultList();
	}

	@Override
	public List<TsscGame> findByTopicId(String id) {
		// TODO Auto-generated method stub
		String jpql = "SELECT a FROM TsscGame a WHERE a.tsscTopic.id = '" + id + "'";
		return entityManager.createQuery(jpql, TsscGame.class).getResultList();
	}

	@Override
	public List<Object[]> findTopicsByDate(LocalDate date) {
		String jpql = "SELECT a.tsscTopic, a.scheduledDate, COUNT(a) FROM TsscGame a,  WHERE a.scheduledDate = '" + date + "' GROUP BY a.tsscTopic";
		return entityManager.createQuery(jpql, Object[].class).getResultList();
	}

	@Override
	public List<Object[]> findGamesLess10StoriesOrNoTimecontrols(LocalDate date) {
		String A = "SELECT game FROM TsscGame game WHERE game.scheduledDate = '" + date + "' ";
		String B = "((SELECT story, COUNT(story) FROM TsscStory story WHERE story.tsscGame.scheduledDate = '" + date + "') < 10) ";
		String C = "((SELECT time , COUNT(time) FROM TsscTimecontrol time WHERE time.tsscGame.scheduledDate = '" + date + "') = 0)";
		String jpql = A + "AND " + B + "OR " + C;
		return entityManager.createQuery(jpql, Object[].class).getResultList();
	}
	
	@Override
	public List<TsscGame> findAll() {
		String jpql = "SELECT a FROM TsscGame a";
		return 	entityManager.createQuery(jpql, TsscGame.class).getResultList();
	}

}
