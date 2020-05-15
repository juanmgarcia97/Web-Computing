package co.edu.icesi.fi.tics.tssc.delegate;

import java.util.List;
import java.util.Optional;

import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.model.TsscStory;
import co.edu.icesi.fi.tics.tssc.model.TsscTopic;

public interface TsscGameDelegate {

	TsscGame saveGame(TsscGame tsscGame);
	TsscGame editGame(TsscGame tsscGame);
	Iterable<TsscGame> findAll();
	Iterable<TsscStory> getStories();
	Iterable<TsscTopic> getTopics();
	void delete(TsscGame tsscGame);
	TsscGame findById(int id);
}
