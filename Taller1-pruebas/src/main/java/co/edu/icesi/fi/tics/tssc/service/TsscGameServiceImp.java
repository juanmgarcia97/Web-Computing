package co.edu.icesi.fi.tics.tssc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.model.TsscStory;
import co.edu.icesi.fi.tics.tssc.model.TsscTimecontrol;
import co.edu.icesi.fi.tics.tssc.model.TsscTopic;
import co.edu.icesi.fi.tics.tssc.repository.TsscGameRepository;
import co.edu.icesi.fi.tics.tssc.repository.TsscTopicRepository;

@Service
public class TsscGameServiceImp implements TsscGameService {

	TsscGameRepository gameRepository;
	TsscTopicRepository topicRepository;

	@Autowired
	public TsscGameServiceImp(TsscGameRepository gameRepository, TsscTopicRepository topicRepository) {
		this.gameRepository = gameRepository;
		this.topicRepository = topicRepository;
	}

	@Override
	public TsscGame saveGame(TsscGame newGame, TsscTopic topic) throws Exception {
		if (newGame == null) {
			throw new Exception("Game does not exists");
		} else if (topic == null) {
			throw new Exception("Topic does not exists");
		} else if (newGame.getNSprints() <= 0) {
			throw new Exception("InvalidNumberSprintsException");
		} else if (newGame.getNGroups() <= 0) {
			throw new Exception("InvalidNumberGroupsException");
		} else if (topicRepository.findById(topic.getId()) == null) {
			throw new Exception("InvalidTopicException");
		} else {
			gameRepository.save(newGame);
			return newGame;
		}
	}

	@Override
	public TsscGame editGame(TsscGame newGame, TsscTopic topic) throws Exception {
		TsscGame game = gameRepository.findById(newGame.getId()).get();
		if (game == null) {
			throw new Exception("Game does not exists");
		} else if (topic == null) {
			throw new Exception("Topic does not exists");
		} else if (game.getNSprints() >= 0) {
			throw new Exception("InvalidNumberSprintsException");
		} else if (game.getNGroups() >= 0) {
			throw new Exception("InvalidNumberGroupsException");
		} else if (topicRepository.findById(topic.getId()) == null) {
			throw new Exception("InvalidTopicException");
		} else {
			gameRepository.save(game);
			return game;
		}
	}

	@Override
	public TsscGame saveGame2(TsscGame newGame, TsscTopic topic) throws Exception {
		if (newGame == null) {
			throw new Exception("Game does not exists");
		} else if (topic == null) {
			throw new Exception("Topic does not exists");
		} else if (newGame.getNSprints() <= 0) {
			throw new Exception("InvalidNumberSprintsException");
		} else if (newGame.getNGroups() <= 0) {
			throw new Exception("InvalidNumberGroupsException");
		} else if (topicRepository.findById(topic.getId()) == null) {
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
				gameRepository.save(newGame);
				return newGame;
			}
		}
	}

}
