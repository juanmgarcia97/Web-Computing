package co.edu.icesi.fi.tics.tssc.service;

import org.springframework.stereotype.Service;

import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.model.TsscStory;
import co.edu.icesi.fi.tics.tssc.repository.TsscStoryRepository;

@Service
public interface TsscStoryService {

	TsscStory saveStory(TsscStory newStory, TsscGame game) throws Exception;
	TsscStory editStory(TsscStory story, TsscGame game) throws Exception;
}
