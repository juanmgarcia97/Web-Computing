package co.edu.icesi.fi.tics.tssc.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.fi.tics.tssc.delegate.TsscGameDelegate;
import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.model.TsscStory;
import co.edu.icesi.fi.tics.tssc.model.TsscTopic;
import co.edu.icesi.fi.tics.tssc.service.TsscGameService;
import co.edu.icesi.fi.tics.tssc.service.TsscTopicService;
import co.edu.icesi.fi.tics.tssc.validations.TsscGameValidation;
import co.edu.icesi.fi.tics.tssc.validations.TsscTopicValidation;

@Controller
public class TsscGameController {

	TsscGameDelegate gameDelegate;

	TsscTopicService topicService;

	@Autowired
	public TsscGameController(TsscGameDelegate gameDelegate, TsscTopicService topicService) {
		this.gameDelegate = gameDelegate;
		this.topicService = topicService;
		;
	}

	@GetMapping("/games/")
	public String indexGame(Model model) {
		model.addAttribute("games", gameDelegate.findAll());
		return "/games/index";
	}

	@GetMapping("/games/add")
	public String addGame(Model model) {
		model.addAttribute("tsscGame", new TsscGame());
		model.addAttribute("topics", topicService.findAll());
		return "games/add-game";
	}

	@PostMapping("/games/add")
	public String saveGame(@Validated(TsscGameValidation.class) TsscGame game, BindingResult bindingResult,
			@RequestParam(value = "action", required = true) String action, Model model) {
		if (!action.equals("Cancelar")) {
			if (bindingResult.hasErrors()) {
				model.addAttribute("topics", topicService.findAll());
				return "games/add-game";
			} else {
				try {
					TsscTopic topic = game.getTsscTopic();
					if (topic == null) {
						gameDelegate.saveGame(game);
					} else {
//						gameDelegate.saveGameTopic(game, topic);
					}

				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				return "redirect:/games/";
			}
		}
		return "redirect:/games/";
	}

	@GetMapping("/games/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		TsscGame game = gameDelegate.findById((int) id);
		if (game == null)
			throw new IllegalArgumentException("Invalid game Id:" + id);
		model.addAttribute("tsscGame", game);
		model.addAttribute("topics", topicService.findAll());
		return "games/update-game";
	}

	@PostMapping("/games/edit/{id}")
	public String updateGames(@Validated(TsscGameValidation.class) TsscGame game, BindingResult bindingResult,
			@PathVariable("id") long id, @RequestParam(value = "action", required = true) String action, Model model) {
		if (action != null && !action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				model.addAttribute("topics", topicService.findAll());
				return "games/update-game";
			} else {
				try {
					TsscTopic topic = game.getTsscTopic();
					if (topic == null) {
						gameDelegate.saveGame(game);
					} else {
//						gameDelegate.saveGame2(game, topic);
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				return "redirect:/games/";
			}
		}
		return "redirect:/games/";
	}
	
	@GetMapping("/games/stories/{id}")
	public String getStories(@PathVariable("id") long id, Model model) {
		TsscGame game = gameDelegate.findById((int) id);
		if (game == null)
			throw new IllegalArgumentException("Invalid game Id:" + id);
		List<TsscStory> stories = (List<TsscStory>) gameDelegate.getStories();
		
		model.addAttribute("tsscGame", game);
		model.addAttribute("stories", stories);
		return "games/stories";
	}

	@GetMapping("/games/del/{id}")
	public String deleteGame(@PathVariable("id") long id) {
		TsscGame game = gameDelegate.findById((int)id);
//				.orElseThrow(() -> new IllegalArgumentException("Invalid game Id:" + id));
		gameDelegate.delete(game);
		return "redirect:/games/";
	}
}
