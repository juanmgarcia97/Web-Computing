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

import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.model.TsscTopic;
import co.edu.icesi.fi.tics.tssc.service.TsscTopicService;
import co.edu.icesi.fi.tics.tssc.validations.TsscTopicValidation;

@Controller
public class TsscTopicController {

	TsscTopicService topicService;

	@Autowired
	public TsscTopicController(TsscTopicService topicService) {
		this.topicService = topicService;
	}

	@GetMapping("/topics/")
	public String indexTopic(Model model) {
		model.addAttribute("topics", topicService.findAll());
		return "/topics/index";
	}

	@GetMapping("/topics/add")
	public String addTopic(Model model) {
		model.addAttribute("tsscTopic", new TsscTopic());
		return "/topics/add-topic";
	}

	@PostMapping("/topics/add")
	public String saveTopic(@Validated(TsscTopicValidation.class) TsscTopic topic, BindingResult bindingResult,
			Model model, @RequestParam(value = "action", required = true) String action) {
		if (!action.equals("Cancelar")) {
			if (bindingResult.hasErrors()) {
//				model.addAttribute("myTopic", myTopic);
				return "topics/add-topic";
			} else {
				try {
					topicService.saveTopic(topic);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return "redirect:/topics/";
			}
		} else {
			return "redirect:/topics/";
		}
	}

	@GetMapping("/topics/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Optional<TsscTopic> topic = topicService.findById(id);
		if (topic == null)
			throw new IllegalArgumentException("Invalid topic Id:" + id);
		model.addAttribute("tsscTopic", topic.get());
		return "/topics/update-topic";
	}

	@PostMapping("/topics/edit/{id}")
	public String updateForm(@Validated(TsscTopicValidation.class) TsscTopic topic, BindingResult bindingResult,
			@RequestParam(value = "action", required = true) String action, Model model) {
		if (!action.equals("Cancelar")) {
			if (bindingResult.hasErrors()) {
				model.addAttribute("myTopic", topic);
				return "/topics/update-topic";
			} else {
				try {
					topicService.saveTopic(topic);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return "redirect:/topics/";
			}
		} else {
			return "redirect:/topics/";
		}
	}

	@GetMapping("/topics/del/{id}")
	public String deleteTopic(@PathVariable("id") long id) {
		TsscTopic topic = topicService.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid game Id:" + id));
		topicService.delete(topic);
		return "redirect:/topics/";
	}

}
