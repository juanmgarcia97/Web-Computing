package co.edu.icesi.fi.tics.tssc.controllers;

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
import co.edu.icesi.fi.tics.tssc.delegate.TsscStoryDelegate;
import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.model.TsscStory;
import co.edu.icesi.fi.tics.tssc.model.TsscTopic;
import co.edu.icesi.fi.tics.tssc.service.TsscGameService;
import co.edu.icesi.fi.tics.tssc.service.TsscStoryService;
import co.edu.icesi.fi.tics.tssc.validations.TsscGameValidation;
import co.edu.icesi.fi.tics.tssc.validations.TsscStoryValidation;

@Controller
public class TsscStoryController {

	TsscStoryDelegate storyDelegate;

	TsscGameDelegate gameDelegate;

	@Autowired
	public TsscStoryController(TsscStoryDelegate storyDelegate, TsscGameDelegate gameDelegate) {
		this.storyDelegate = storyDelegate;
		this.gameDelegate = gameDelegate;
	}

	@GetMapping("/stories")
	public String indexStory(Model model) {
		model.addAttribute("stories", storyDelegate.findAll());
		return "/stories/index";
	}

	@GetMapping("/stories/add")
	public String addStory(Model model) {
		model.addAttribute("tsscStory", new TsscStory());
		model.addAttribute("games", gameDelegate.findAll());
		return "stories/add-story";
	}

	@PostMapping("/stories/add")
	public String saveStory(@Validated(TsscStoryValidation.class) TsscStory story, BindingResult bindingResult,
			@RequestParam(value = "action", required = true) String action, Model model) {
		if (!action.equals("Cancelar")) {
			if (bindingResult.hasErrors()) {
				model.addAttribute("games", gameDelegate.findAll());
				return "stories/add-story";
			} else {
				try {
					
						storyDelegate.saveStory(story);
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				return "redirect:/stories/";
			}
		}
		return "redirect:/stories/";
	}

	@GetMapping("/stories/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		TsscStory story = storyDelegate.findById(id);
		if (story == null)
			throw new IllegalArgumentException("Invalid story Id:" + id);
		model.addAttribute("tsscStory", story);
		model.addAttribute("games", gameDelegate.findAll());
		return "stories/update-story";
	}

	@PostMapping("/stories/edit/{id}")
	public String updateStory(@Validated(TsscStoryValidation.class) TsscStory story, BindingResult bindingResult,
			@PathVariable("id") long id, @RequestParam(value = "action", required = true) String action, Model model) {
		if (action != null && !action.equals("Cancelar")) {
			if (bindingResult.hasErrors()) {
				model.addAttribute("games", gameDelegate.findAll());
				return "stories/update-story";
			} else {
				try {

					storyDelegate.editStory(story);

				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				return "redirect:/stories/";
			}
		}
		return "redirect:/stories/";
	}

	@GetMapping("/stories/del/{id}")
	public String deleteStory(@PathVariable("id") long id) {
		TsscStory story = storyDelegate.findById(id);
		storyDelegate.delete(story);
		return "redirect:/stories/";
	}

}
