package co.edu.icesi.fi.tics.tssc.daos;

import java.util.List;

import co.edu.icesi.fi.tics.tssc.model.TsscStory;

public interface ITsscStoryDao {

	public void save(TsscStory entity);
	public void update(TsscStory entity);
	public void delete(TsscStory entity);
	public TsscStory findById(long id);
	public List<TsscStory> findAll();
}
