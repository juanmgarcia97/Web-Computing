package co.edu.icesi.fi.tics.tssc.daos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import co.edu.icesi.fi.tics.tssc.model.TsscGame;

public interface ITsscGameDao {

	public void save(TsscGame entity);
	public void update(TsscGame entity);
	public void delete(TsscGame entity);
	public List<TsscGame> findAll();
	public List<TsscGame> findByName(String name);
	public List<TsscGame> findByTopicDescription(String description);
	public List<TsscGame> findByTopicId(String id);
	public List<Object[]> findTopicsByDate(LocalDate date);
	public List<Object[]> findGamesLess10StoriesOrNoTimecontrols(LocalDate date);
	public List<TsscGame> findByDate(LocalDate init, LocalDate finish);
	public List<TsscGame> findByDateNTime(LocalDate date, LocalTime init, LocalTime finish);
	public TsscGame findById(long id);
}
