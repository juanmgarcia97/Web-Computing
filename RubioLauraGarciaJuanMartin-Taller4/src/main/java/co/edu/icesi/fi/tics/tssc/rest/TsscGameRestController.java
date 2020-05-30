package co.edu.icesi.fi.tics.tssc.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.model.TsscStory;
import co.edu.icesi.fi.tics.tssc.model.TsscTopic;
import co.edu.icesi.fi.tics.tssc.service.TsscGameService;
import co.edu.icesi.fi.tics.tssc.service.TsscTopicService;

@RestController
public class TsscGameRestController {

	private TsscGameService gameService;

	private TsscTopicService topicService;

	@Autowired
	public TsscGameRestController(TsscGameService gameService, TsscTopicService topicService) {
		this.gameService = gameService;
		this.topicService = topicService;
	}

	@RequestMapping(value = "/api/games/", method = RequestMethod.GET)
	public Iterable<TsscGame> getGames() {
		return gameService.findAll();
	}
	
	@RequestMapping(value = "/api/games/{id}", method = RequestMethod.GET)
	public TsscGame findById(@PathVariable("id") long id) {
		return gameService.findById(id).get();
	}

	@RequestMapping(value = "/api/games/", method = RequestMethod.POST)
	public TsscGame saveGame(@RequestBody TsscGame game) {
		TsscTopic topic = game.getTsscTopic();
		try {
			if (topic == null) {
				return gameService.saveGame(game);
			} else {
				return gameService.saveGameTopic(game, topic);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value = "/api/games/", method = RequestMethod.PUT)
	public TsscGame editGame(@RequestBody TsscGame game) {
		TsscTopic topic = game.getTsscTopic();
		try {
			if (topic == null) {
				return gameService.editGame(game);
			} else {
				return gameService.editGameTopic(game, topic);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value = "/api/games/stories", method = RequestMethod.GET)
	public Iterable<TsscStory> getStories(@RequestBody TsscGame game) {
		return gameService.getStories(game);
	}
	
	@RequestMapping(value = "/api/games/topics", method = RequestMethod.GET)
	public Iterable<TsscTopic> getTopics(@RequestBody TsscTopic topic) {
		return topicService.findAll();
	}
	
	@RequestMapping(value = "/api/games/{id}", method = RequestMethod.DELETE)
	public void deleteGame(@PathVariable ("id") long id) {
		TsscGame game = gameService.findById(id).get();
		gameService.delete(game);
	}
}
