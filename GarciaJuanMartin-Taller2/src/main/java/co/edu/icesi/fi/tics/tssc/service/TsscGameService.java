package co.edu.icesi.fi.tics.tssc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.model.TsscStory;
import co.edu.icesi.fi.tics.tssc.model.TsscTopic;

@Service
public interface TsscGameService {

	TsscGame saveGame(TsscGame newGame) throws Exception;
	TsscGame saveGameTopic(TsscGame newGame, TsscTopic topic) throws Exception;
	TsscGame editGame(TsscGame newGame) throws Exception;
	TsscGame saveGame2(TsscGame newGame, TsscTopic topic) throws Exception;
	Iterable<TsscGame> findAll();
	Optional<TsscGame> findById(Long id);
	void delete(TsscGame game);
	List<TsscStory> getStories(TsscGame game);
}
