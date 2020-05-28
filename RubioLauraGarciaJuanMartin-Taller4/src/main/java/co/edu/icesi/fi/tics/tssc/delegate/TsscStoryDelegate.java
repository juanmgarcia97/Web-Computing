package co.edu.icesi.fi.tics.tssc.delegate;

import co.edu.icesi.fi.tics.tssc.model.TsscStory;

public interface TsscStoryDelegate {

	Iterable<TsscStory> findAll();
	TsscStory saveStory(TsscStory tsscGame);
	TsscStory findById(long id);
	void delete(TsscStory tsscGame);
	TsscStory editStory(TsscStory tsscGame);
}
