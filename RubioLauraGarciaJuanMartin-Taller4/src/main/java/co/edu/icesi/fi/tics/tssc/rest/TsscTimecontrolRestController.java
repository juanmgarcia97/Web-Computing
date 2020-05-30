package co.edu.icesi.fi.tics.tssc.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.model.TsscTimecontrol;
import co.edu.icesi.fi.tics.tssc.service.TsscTimecontrolService;

@RestController
public class TsscTimecontrolRestController {

	private TsscTimecontrolService timeService;

	@Autowired
	public TsscTimecontrolRestController(TsscTimecontrolService timeService) {
		this.timeService = timeService;
	}

	@RequestMapping(value = "/api/times/", method = RequestMethod.GET)
	public Iterable<TsscTimecontrol> getTimes() {
		return timeService.findAll();
	}
	
	@RequestMapping(value = "/api/times/{id}", method = RequestMethod.GET)
	public TsscTimecontrol findById(@PathVariable("id") long id) {
		return timeService.findById(id).get();
	}

	@RequestMapping(value = "/api/times/", method = RequestMethod.POST)
	public TsscTimecontrol saveTime(@RequestBody TsscTimecontrol time) {
		TsscGame game = time.getTsscGame();
		try {
			if (game == null) {
				return timeService.saveTime(time);
			} else {
				return timeService.saveTimeGame(time, game);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value = "/api/times/", method = RequestMethod.PUT)
	public TsscTimecontrol editTime(@RequestBody TsscTimecontrol time) {
		TsscGame game = time.getTsscGame();
		try {
			if (game == null) {
				return timeService.editTime(time);
			} else {
				return timeService.editTimeGame(time, game);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value = "/api/times/{id}", method = RequestMethod.DELETE)
	public void deleteTime(@PathVariable ("id") long id) {
		TsscTimecontrol time = timeService.findById(id).get();
		timeService.delete(time);
	}
}
