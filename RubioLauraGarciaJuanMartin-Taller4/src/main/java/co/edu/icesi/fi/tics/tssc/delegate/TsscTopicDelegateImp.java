package co.edu.icesi.fi.tics.tssc.delegate;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.fi.tics.tssc.model.TsscTopic;

@Component
public class TsscTopicDelegateImp implements TsscTopicDelegate{

	public final static String URI = "http://localhost:8080/";
	private RestTemplate rest;

	public TsscTopicDelegateImp() {
		rest = new RestTemplate();
	}

	@Override
	public TsscTopic saveTopic(TsscTopic entity) {
		TsscTopic topic = rest.postForEntity(URI + "api/topics/", entity, TsscTopic.class).getBody();
		return topic;
	}

	@Override
	public TsscTopic findById(long id) {
		TsscTopic topic = rest.getForObject(URI + "api/topics/" + id, TsscTopic.class);
		return topic;
	}

	@Override
	public void delete(long id) {
		rest.delete(URI + "api/topics/" + id);
	}

	@Override
	public void editTopic(TsscTopic entity) {
		rest.patchForObject(URI + "api/topics/", entity, TsscTopic.class);
	}

	@Override
	public Iterable<TsscTopic> findAll() {
		TsscTopic[] topics = rest.getForObject(URI + "api/topics/", TsscTopic[].class);
		
		List<TsscTopic> to = Arrays.asList(topics);
		
		return to;

	}
}
