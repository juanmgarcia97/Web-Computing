package co.edu.icesi.fi.tics.tssc.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.model.TsscStory;
import co.edu.icesi.fi.tics.tssc.repository.TsscStoryRepository;

@Service
public interface TsscStoryService {

	TsscStory saveStory(TsscStory newStory) throws Exception;
	TsscStory saveStoryGame(TsscStory newStory, TsscGame game) throws Exception;
	TsscStory editStory(TsscStory story) throws Exception;
	TsscStory editStoryGame(TsscStory story, TsscGame game) throws Exception;
	Iterable<TsscStory> findAll();
	Optional<TsscStory> findById(Long id);
	void delete(TsscStory story);
	TsscGame getGame(TsscStory story);
}
