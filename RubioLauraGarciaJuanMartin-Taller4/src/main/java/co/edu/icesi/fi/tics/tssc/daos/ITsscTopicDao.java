package co.edu.icesi.fi.tics.tssc.daos;

import java.util.List;

import co.edu.icesi.fi.tics.tssc.model.TsscTopic;

public interface ITsscTopicDao {

	public void save(TsscTopic entity);
	public void update(TsscTopic entity);
	public void delete(TsscTopic entity);
	public TsscTopic findById(long id);
	public List<TsscTopic> findByName(String name);
	public List<TsscTopic> findByDescription(String description);
}
