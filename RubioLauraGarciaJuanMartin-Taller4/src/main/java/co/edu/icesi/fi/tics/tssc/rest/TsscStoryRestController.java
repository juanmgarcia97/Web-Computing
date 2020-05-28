package co.edu.icesi.fi.tics.tssc.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.fi.tics.tssc.model.TsscStory;
import co.edu.icesi.fi.tics.tssc.service.TsscStoryService;

@RestController
public class TsscStoryRestController {

	@Autowired
	private TsscStoryService storyService;

	@RequestMapping(value = "/api/stories/{id}", method = RequestMethod.GET)
	public TsscStory findById(@PathVariable("id") long id) {
		return storyService.findById(id).get();
	}

	@RequestMapping(value = "/api/stories/", method = RequestMethod.POST)
	public TsscStory save(@RequestBody TsscStory story) {

		try {
			return storyService.saveStory(story);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	@RequestMapping(value = "/api/stories/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") long id) {
		TsscStory story = storyService.findById(id).get();
		storyService.delete(story);
	}

	@RequestMapping(value = "/api/stories/", method = RequestMethod.PUT)
	public TsscStory update(@RequestBody TsscStory story) {
		try {
			return storyService.editStory(story);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return story;
		}
	}

	@RequestMapping(value = "/api/stories/", method = RequestMethod.GET)
	public Iterable<TsscStory> findAll() {
		return storyService.findAll();
	}
}
