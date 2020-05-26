package co.edu.icesi.fi.tics.tssc.delegate;

import co.edu.icesi.fi.tics.tssc.model.TsscTopic;

public interface TsscTopicDelegate {

	public TsscTopic saveTopic(TsscTopic entity);
	public TsscTopic findById(long id) ;
	public void delete(long id);
	public void editTopic(TsscTopic entity);
	public Iterable<TsscTopic> findAll();
}
