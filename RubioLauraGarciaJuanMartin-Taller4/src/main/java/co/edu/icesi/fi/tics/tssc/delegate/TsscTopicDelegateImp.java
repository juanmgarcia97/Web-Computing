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
	public void saveTopic(TsscTopic entity) {
		TsscTopic topic = rest.postForEntity(URI + "api/topic", entity, TsscTopic.class).getBody();

		if (topic == null) {
			// hacer algo
		}
	}

	@Override
	public TsscTopic findById(long id) {
		TsscTopic topic = rest.getForObject(URI + "api/topic/" + id, TsscTopic.class);
		return topic;
	}

	@Override
	public void delete(long id) {
		rest.delete(URI + "api/topic" + id);
	}

	@Override
	public void editTopic(TsscTopic entity) {
		rest.patchForObject(URI + "api/topic", entity, TsscTopic.class);
	}

	@Override
	public Iterable<TsscTopic> findAll() {
		TsscTopic[] topics = rest.getForObject(URI + "api/topic", TsscTopic[].class);
		List<TsscTopic> to = Arrays.asList(topics);
		return to;

	}
}
