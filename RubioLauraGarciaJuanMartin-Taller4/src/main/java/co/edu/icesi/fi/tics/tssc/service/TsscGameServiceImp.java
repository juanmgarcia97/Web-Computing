package co.edu.icesi.fi.tics.tssc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.fi.tics.tssc.daos.ITsscGameDao;
import co.edu.icesi.fi.tics.tssc.daos.ITsscTopicDao;
import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.model.TsscStory;
import co.edu.icesi.fi.tics.tssc.model.TsscTimecontrol;
import co.edu.icesi.fi.tics.tssc.model.TsscTopic;
import co.edu.icesi.fi.tics.tssc.repository.TsscGameRepository;
import co.edu.icesi.fi.tics.tssc.repository.TsscTopicRepository;
@Service
public class TsscGameServiceImp implements TsscGameService {

	ITsscGameDao gameDao;
	ITsscTopicDao topicDao;

	@Autowired
	public TsscGameServiceImp(ITsscGameDao gameDao, ITsscTopicDao topicDao) {
		this.gameDao = gameDao;
		this.topicDao = topicDao;
	}

	@Override
	@Transactional
	public TsscGame saveGame(TsscGame newGame) throws Exception {
		if (newGame == null) {
			throw new Exception("Game does not exists");
		} else if (newGame.getNSprints() <= 0) {
			throw new Exception("InvalidNumberSprintsException");
		} else if (newGame.getNGroups() <= 0) {
			throw new Exception("InvalidNumberGroupsException");
		} else {
			gameDao.save(newGame);
			return newGame;
		}
	}
	
	@Override
	@Transactional
	public TsscGame saveGameTopic(TsscGame newGame, TsscTopic topic) throws Exception {
		if (newGame == null) {
			throw new Exception("Game does not exists");
		} else if (newGame.getNSprints() <= 0) {
			throw new Exception("InvalidNumberSprintsException");
		} else if (newGame.getNGroups() <= 0) {
			throw new Exception("InvalidNumberGroupsException");
		} else if(topic == null){
			throw new Exception("Topic does not exists");
		}else {
			newGame.setTsscTopic(topic);
			gameDao.save(newGame);
			return newGame;
		}
	}

	@Override
	@Transactional
	public TsscGame editGame(TsscGame game) throws Exception {
		if (game == null) {
			throw new Exception("Game does not exists");
		} else if (game.getNSprints() <= 0) {
			throw new Exception("InvalidNumberSprintsException");
		} else if (game.getNGroups() <= 0) {
			throw new Exception("InvalidNumberGroupsException");
		} else {
			gameDao.update(game);
			return game;
		}
	}
	
	@Transactional
	public TsscGame editGameTopic(TsscGame newGame, TsscTopic topic) throws Exception {
		if (newGame == null) {
			throw new Exception("Game does not exists");
		} else if (newGame.getNSprints() <= 0) {
			throw new Exception("InvalidNumberSprintsException");
		} else if (newGame.getNGroups() <= 0) {
			throw new Exception("InvalidNumberGroupsException");
		} else if(topic == null){
			throw new Exception("Topic does not exists");
		}else {
			newGame.setTsscTopic(topic);
			gameDao.update(newGame);
			return newGame;
		}
	}

	@Override
	@Transactional
	public TsscGame saveGame2(TsscGame newGame, TsscTopic topic) throws Exception {
		if (newGame == null) {
			throw new Exception("Game does not exists");
		} else if (topic == null) {
			throw new Exception("Topic does not exists");
		} else if (newGame.getNSprints() <= 0) {
			throw new Exception("InvalidNumberSprintsException");
		} else if (newGame.getNGroups() <= 0) {
			throw new Exception("InvalidNumberGroupsException");
		} else if (topicDao.findById(topic.getId()) == null) {
			throw new Exception("InvalidTopicException");
		} else {
			if (topic.getTsscStories() == null) {
				throw new Exception("Topic's stories do not exists");
			} else if (topic.getTsscTimecontrol() == null) {
				throw new Exception("Topic's timecontrols do not exists");
			} else {
				List<TsscStory> stories = topic.getTsscStories();
				List<TsscTimecontrol> times = topic.getTsscTimecontrol();
				newGame.setTsscTopic(topic);
				newGame.setTsscStories(stories);
				newGame.setTsscTimecontrol(times);
				gameDao.save(newGame);
				return newGame;
			}
		}
	}

	@Override
	public Iterable<TsscGame> findAll() {
		// TODO Auto-generated method stub
		return gameDao.findAll();
	}

	@Override
	public Optional<TsscGame> findById(Long id) {
		// TODO Auto-generated method stub
		return Optional.of(gameDao.findById(id));
	}

	@Override
	@Transactional
	public void delete(TsscGame game) {
		// TODO Auto-generated method stub
		gameDao.delete(game);
	}

	@Override
	public List<TsscStory> getStories(TsscGame game) {
		// TODO Auto-generated method stub
		return game.getTsscStories();
	}

}
