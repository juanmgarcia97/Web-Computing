package co.edu.icesi.fi.tics.tssc.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.fi.tics.tssc.model.TsscTopic;
import co.edu.icesi.fi.tics.tssc.service.TsscTopicService;

@RestController
public class TsscTopicRestController {

	@Autowired
	private TsscTopicService topicService;
	
	@RequestMapping(value ="/api/topic/{id}", method = RequestMethod.GET)
	public TsscTopic findById(@PathVariable("id")long id) {
		return topicService.findById(id).get();
	}
	
	@RequestMapping(value ="/api/topic", method = RequestMethod.POST)
	public TsscTopic save(@RequestBody TsscTopic topic) {
		
		try {
			return topicService.saveTopic(topic);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	@RequestMapping(value ="/api/topic/{id}", method = RequestMethod.DELETE)
	public void delete (@PathVariable( "id") long id) {
		TsscTopic topic = topicService.findById(id).get();
		topicService.delete(topic);
	}
	
	@RequestMapping(value ="api/topic", method = RequestMethod.PUT)
	public TsscTopic update( @RequestBody TsscTopic topic) {
		try {
			return topicService.editTopic(topic);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return topic;
		}
	}
	
	@RequestMapping(value ="/api/topic", method = RequestMethod.GET)
	public Iterable<TsscTopic> findAll(){
		return topicService.findAll();
	}
}
