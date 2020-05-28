package co.edu.icesi.fi.tics.tssc.delegate;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.model.TsscStory;
import co.edu.icesi.fi.tics.tssc.model.TsscTopic;

@Component
public class TsscGameDelegateImp implements TsscGameDelegate{

	public final static String URI = "http://localhost:8080/";
	
	private RestTemplate rest;
	
	public TsscGameDelegateImp() {
		rest  = new RestTemplate();
	}
	
	@Override
	public Iterable<TsscGame> findAll() {
		TsscGame[] games = rest.getForObject(URI + "api/games/", TsscGame[].class);
		List<TsscGame> gms = Arrays.asList(games);
		return gms;
	}
	
	@Override
	public TsscGame saveGame(TsscGame tsscGame) {
		TsscGame game = rest.postForEntity(URI + "api/games/", tsscGame, TsscGame.class).getBody();
		return game;
	}
	
	@Override
	public TsscGame findById(int id) {
		TsscGame game = rest.getForObject(URI + "api/games/" + id, TsscGame.class);
		return game;
	}
	
	@Override
	public void delete(TsscGame tsscGame) {
		rest.delete(URI + "api/games/" + tsscGame.getId());
	}

	@Override
	public TsscGame editGame(TsscGame tsscGame) {
		rest.put(URI + "api/games/", tsscGame, TsscGame.class);
		return tsscGame;
	}

	@Override
	public Iterable<TsscStory> getStories() {
		TsscStory[] stories = rest.getForObject(URI + "api/games/stories/", TsscStory[].class);
		List<TsscStory> sts = Arrays.asList(stories);
		return sts;
	}

	@Override
	public Iterable<TsscTopic> getTopics() {
		TsscTopic[] topics = rest.getForObject(URI + "api/games/topics/", TsscTopic[].class);
		List<TsscTopic> tps = Arrays.asList(topics);
		return tps;
	}
}
