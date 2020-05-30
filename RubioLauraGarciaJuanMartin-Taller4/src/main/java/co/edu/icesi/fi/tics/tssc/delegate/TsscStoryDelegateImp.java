package co.edu.icesi.fi.tics.tssc.delegate;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.fi.tics.tssc.model.TsscStory;

@Component
public class TsscStoryDelegateImp implements TsscStoryDelegate{

public final static String URI = "http://localhost:8080/";
	
	private RestTemplate rest;
	
	public TsscStoryDelegateImp() {
		rest  = new RestTemplate();
	}
	
	@Override
	public Iterable<TsscStory> findAll() {
		TsscStory[] stories = rest.getForObject(URI + "api/stories/", TsscStory[].class);
		List<TsscStory> sts = Arrays.asList(stories);
		return sts;
		
	}
	
	@Override
	public TsscStory saveStory(TsscStory tsscStory) {
		TsscStory story = rest.postForEntity(URI + "api/stories/", tsscStory, TsscStory.class).getBody();
		return story;
	}
	
	@Override
	public TsscStory findById(long id) {
		TsscStory story = rest.getForObject(URI + "api/stories/" + id, TsscStory.class);
		return story;
	}
	
	@Override
	public void delete(TsscStory tsscStory) {
		rest.delete(URI + "api/stories/" + tsscStory.getId());
	}

	@Override
	public TsscStory editStory(TsscStory tsscStory) {
		rest.put(URI + "api/stories/", tsscStory, TsscStory.class);
		return tsscStory;
	}

}
