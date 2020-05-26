package co.edu.icesi.fi.tics.tssc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.model.TsscStory;
import co.edu.icesi.fi.tics.tssc.repository.TsscGameRepository;
import co.edu.icesi.fi.tics.tssc.repository.TsscStoryRepository;
@Service
public class TsscStoryServiceImp implements TsscStoryService{

	TsscStoryRepository storyRepository;
	TsscGameRepository gameRepository;
	
	@Autowired
	public TsscStoryServiceImp(TsscStoryRepository storyRepository, TsscGameRepository gameRepository) {
		this.storyRepository = storyRepository;
		this.gameRepository = gameRepository;
	}
	
	@Override
	@Transactional
	public TsscStory saveStory(TsscStory newStory) throws Exception {
		if (newStory == null) {
			throw new Exception("Story does not exists");
		} else if (newStory.getBusinessValue().intValue() <= 0) {
			throw new Exception("InvalidNumberBusinessValueException");
		} else if (newStory.getInitialSprint().intValue() <= 0) {
			throw new Exception("InvalidNumberInitialSprintsException");
		} else if (newStory.getPriority().intValue() <= 0) {
			throw new Exception("InvalidNumberPriorityException");
		} else {
			storyRepository.save(newStory);
			return newStory;
		}
	}
	
	@Override
	@Transactional
	public TsscStory saveStoryGame(TsscStory newStory, TsscGame game) throws Exception {
		if (newStory == null) {
			throw new Exception("Story does not exists");
		} else if (game == null) {
			throw new Exception("Game does not exists");
		} else if (newStory.getBusinessValue().intValue() <= 0) {
			throw new Exception("InvalidNumberBusinessValueException");
		} else if (newStory.getInitialSprint().intValue() <= 0) {
			throw new Exception("InvalidNumberInitialSprintsException");
		} else if (newStory.getPriority().intValue() <= 0) {
			throw new Exception("InvalidNumberPriorityException");
		} else if(storyRepository.findById(newStory.getTsscGame().getId()) == null){
			throw new Exception("InvalidTopicException");
		} else {
			newStory.setTsscGame(game);
			storyRepository.save(newStory);
			return newStory;
		}
	}

	@Override
	@Transactional
	public TsscStory editStory(TsscStory newStory) throws Exception {
		TsscStory story = storyRepository.findById(newStory.getId()).get();
		if (story == null) {
			throw new Exception("NullPointerException");
		} else if (story.getBusinessValue().intValue() <= 0) {
			throw new Exception("InvalidNumberBusinessValueException");
		} else if (story.getInitialSprint().intValue() <= 0) {
			throw new Exception("InvalidNumberInitialSprintsException");
		} else if (story.getPriority().intValue() <= 0) {
			throw new Exception("InvalidNumberPriorityException");
		} else {
			storyRepository.save(story);
			return story;
		}
	}
	
	@Override
	@Transactional
	public TsscStory editStoryGame(TsscStory newStory, TsscGame game) throws Exception {
		TsscStory story = storyRepository.findById(newStory.getId()).get();
		if (story == null) {
			throw new Exception("NullPointerException");
		} else if (game == null) {
			throw new Exception("Game does not exists");
		} else if (story.getBusinessValue().intValue() <= 0) {
			throw new Exception("InvalidNumberBusinessValueException");
		} else if (story.getInitialSprint().intValue() <= 0) {
			throw new Exception("InvalidNumberInitialSprintsException");
		} else if (story.getPriority().intValue() <= 0) {
			throw new Exception("InvalidNumberPriorityException");
		} else if(storyRepository.findById(story.getTsscGame().getId()) == null){
			throw new Exception("InvalidTopicException");
		} else {
			newStory.setTsscGame(game);
			storyRepository.save(story);
			return story;
		}
	}

	@Override
	public Iterable<TsscStory> findAll() {
		// TODO Auto-generated method stub
		return storyRepository.findAll();
	}

	@Override
	public Optional<TsscStory> findById(Long id) {
		// TODO Auto-generated method stub
		return storyRepository.findById(id);
	}

	@Override
	@Transactional
	public void delete(TsscStory story) {
		// TODO Auto-generated method stub
		storyRepository.delete(story);
	}

	@Override
	public TsscGame getGame(TsscStory story) {
		// TODO Auto-generated method stub
		return null;
	}

}
