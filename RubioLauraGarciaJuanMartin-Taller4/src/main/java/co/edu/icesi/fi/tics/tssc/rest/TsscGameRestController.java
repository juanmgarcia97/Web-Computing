package co.edu.icesi.fi.tics.tssc.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.service.TsscGameService;
import co.edu.icesi.fi.tics.tssc.service.TsscTopicService;

@RestController
public class TsscGameRestController {

	
	private TsscGameService gameService;
	
	private TsscTopicService topicService;
	
	@Autowired
	public TsscGameRestController(TsscGameService gameService, TsscTopicService topicService) {
		this.gameService = gameService;
		this.topicService =topicService;
	}
	
	@RequestMapping(value = "api/cars", method = RequestMethod.GET)
	public Iterable<TsscGame> getGames() {
		return gameService.findAll();
	}
}
