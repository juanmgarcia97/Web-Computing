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
		TsscGame[] games = rest.getForObject(URI + "games", TsscGame[].class);
		List<TsscGame> gms;
		try {
			gms = Arrays.asList(games);
			return gms;
		} catch (Exception e) {
//			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public TsscGame saveGame(TsscGame tsscGame) {
		TsscGame game = rest.postForEntity(URI + "games", tsscGame, TsscGame.class).getBody();
		return game;
	}
	
	@Override
	public TsscGame findById(int id) {
		TsscGame game = rest.getForObject(URI + "games/" + id, TsscGame.class);
		return game;
	}
	
	@Override
	public void delete(TsscGame tsscGame) {
		rest.delete(URI + "games/" + tsscGame.getId());
	}

	@Override
	public TsscGame editGame(TsscGame tsscGame) {
		TsscGame game = rest.patchForObject(URI + "games", tsscGame, TsscGame.class);
		return game;
	}

	@Override
	public Iterable<TsscStory> getStories() {
		TsscStory[] stories = rest.getForObject(URI + "api/games/stories", TsscStory[].class);
		List<TsscStory> sts;
		try {
			sts = Arrays.asList(stories);
			return sts;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Iterable<TsscTopic> getTopics() {
		TsscTopic[] topics = rest.getForObject(URI + "games/stories", TsscTopic[].class);
		List<TsscTopic> tps;
		try {
			tps = Arrays.asList(topics);
			return tps;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
