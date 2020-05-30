package co.edu.icesi.fi.tics.tssc.controllers;

import java.util.List;

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
import co.edu.icesi.fi.tics.tssc.delegate.TsscTimecontrolDelegate;
import co.edu.icesi.fi.tics.tssc.model.TsscTimecontrol;
import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.validations.TsscGameValidation;

@Controller
public class TsscTimecontrolController {

	private TsscTimecontrolDelegate timeDelegate;

	private TsscGameDelegate gameDelegate;

	@Autowired
	public TsscTimecontrolController(TsscTimecontrolDelegate timeDelegate, TsscGameDelegate gameDelegate) {
		// TODO Auto-generated constructor stub
		this.timeDelegate = timeDelegate;
		this.gameDelegate = gameDelegate;
	}

	@GetMapping("/times/")
	public String indexTime(Model model) {
		model.addAttribute("times", timeDelegate.findAll());
		return "/times/index";
	}

	@GetMapping("/times/add")
	public String addTime(Model model) {
		model.addAttribute("tsscTimecontrol", new TsscTimecontrol());
		model.addAttribute("games", gameDelegate.findAll());
		return "/times/add-time";
	}

	@PostMapping("/times/add")
	public String saveTime(TsscTimecontrol tsscTimecontrol, BindingResult bindingResult,
			@RequestParam(value = "action", required = true) String action, Model model) {
		if (!action.equals("Cancelar")) {
			if (bindingResult.hasErrors()) {
				model.addAttribute("games", gameDelegate.findAll());
				return "/times/add-time";
			} else {
				try {
					timeDelegate.saveTime(tsscTimecontrol);

				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				return "redirect:/times/";
			}
		}
		return "redirect:/times/";
	}

	@GetMapping("/times/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		TsscTimecontrol tsscTimecontrol = timeDelegate.findById(id);
		if (tsscTimecontrol == null)
			throw new IllegalArgumentException("Invalid tsscTimecontrol Id:" + id);
		model.addAttribute("tsscTimecontrol", tsscTimecontrol);
		model.addAttribute("games", gameDelegate.findAll());
		return "times/update-time";
	}

	@PostMapping("/times/edit/{id}")
	public String updateTime(TsscTimecontrol tsscTimecontrol, BindingResult bindingResult,
			@PathVariable("id") long id, @RequestParam(value = "action", required = true) String action, Model model) {
		if (action != null && !action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				model.addAttribute("tsscTimecontrol", timeDelegate.findAll());
				return "/times/update-time";
			} else {
				try {
					timeDelegate.editTime(tsscTimecontrol);

				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				return "redirect:/times/";
			}
		}
		return "redirect:/times/";
	}

	@GetMapping("/games/{id}")
	public String getGames(@PathVariable("id") long id, Model model) {
		TsscTimecontrol tsscTimecontrol = timeDelegate.findById(id);
		if (tsscTimecontrol == null)
			throw new IllegalArgumentException("Invalid tsscTimecontrol Id:" + id);
		List<TsscGame> games = (List<TsscGame>) gameDelegate.findAll();

		model.addAttribute("tsscTimecontrol", tsscTimecontrol);
		model.addAttribute("games", games);
		return "/games/";
	}

	@GetMapping("/times/del/{id}")
	public String deleteTime(@PathVariable("id") long id) {
		TsscTimecontrol tsscTimecontrol = timeDelegate.findById(id);
		timeDelegate.delete(tsscTimecontrol);
		return "redirect:/times/";
	}
}
