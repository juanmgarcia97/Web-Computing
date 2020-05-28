package co.edu.icesi.fi.tics.tssc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.fi.tics.tssc.daos.ITsscGameDao;
import co.edu.icesi.fi.tics.tssc.daos.ITsscStoryDao;
import co.edu.icesi.fi.tics.tssc.daos.TsscStoryDao;
import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.model.TsscStory;
import co.edu.icesi.fi.tics.tssc.repository.TsscGameRepository;
import co.edu.icesi.fi.tics.tssc.repository.TsscStoryRepository;
@Service
public class TsscStoryServiceImp implements TsscStoryService{

	ITsscStoryDao storyDao;
	ITsscGameDao gameDao;
	
	@Autowired
	public TsscStoryServiceImp(ITsscStoryDao storyDao, ITsscGameDao gameDao) {
		this.storyDao = storyDao;
		this.gameDao = gameDao;
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
			storyDao.save(newStory);
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
		} else if(storyDao.findById(newStory.getTsscGame().getId()) == null){
			throw new Exception("InvalidTopicException");
		} else {
			newStory.setTsscGame(game);
			storyDao.save(newStory);
			return newStory;
		}
	}

	@Override
	@Transactional
	public TsscStory editStory(TsscStory story) throws Exception {
		if (story == null) {
			throw new Exception("NullPointerException");
		} else if (story.getBusinessValue().intValue() <= 0) {
			throw new Exception("InvalidNumberBusinessValueException");
		} else if (story.getInitialSprint().intValue() <= 0) {
			throw new Exception("InvalidNumberInitialSprintsException");
		} else if (story.getPriority().intValue() <= 0) {
			throw new Exception("InvalidNumberPriorityException");
		} else {
			storyDao.update(story);
			return story;
		}
	}
	
	@Override
	@Transactional
	public TsscStory editStoryGame(TsscStory newStory, TsscGame game) throws Exception {
		TsscStory story = storyDao.findById(newStory.getId());
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
		} else if(storyDao.findById(story.getTsscGame().getId()) == null){
			throw new Exception("InvalidTopicException");
		} else {
			newStory.setTsscGame(game);
			storyDao.save(story);
			return story;
		}
	}

	@Override
	public Iterable<TsscStory> findAll() {
		// TODO Auto-generated method stub
		return storyDao.findAll();
	}

	@Override
	public Optional<TsscStory> findById(Long id) {
		// TODO Auto-generated method stub
		return Optional.of(storyDao.findById(id));
	}

	@Override
	@Transactional
	public void delete(TsscStory story) {
		// TODO Auto-generated method stub
		storyDao.delete(story);
	}

	@Override
	public TsscGame getGame(TsscStory story) {
		// TODO Auto-generated method stub
		return null;
	}

}
