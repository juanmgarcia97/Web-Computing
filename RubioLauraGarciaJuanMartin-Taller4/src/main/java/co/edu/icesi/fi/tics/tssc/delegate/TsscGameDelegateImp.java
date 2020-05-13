package co.edu.icesi.fi.tics.tssc.delegate;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.fi.tics.tssc.model.TsscGame;

@Component
public class TsscGameDelegateImp implements TsscGameDelegate{

	public final static String URI = "http://localhost:8081/";
	
	private RestTemplate rest;
	
	public TsscGameDelegateImp() {
		rest  = new RestTemplate();
	}
	
	@Override
	public Iterable<TsscGame> findAll() {
		TsscGame[] games = rest.getForObject(URI + "api/games", TsscGame[].class);
		List<TsscGame> gms;
		try {
			gms = Arrays.asList(games);
			return gms;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public TsscGame saveGame(TsscGame tsscGame) {
		TsscGame game = rest.postForEntity(URI + "api/games", tsscGame, TsscGame.class).getBody();
		return game;
	}
	
	@Override
	public TsscGame getGame(int id) {
		TsscGame game = rest.getForObject(URI + "api/games/" + id, TsscGame.class);
		return game;
	}
	
	@Override
	public void deleteGame(TsscGame tsscGame) {
		rest.delete(URI + "api/games/" + tsscGame.getId());
	}

	@Override
	public TsscGame editGame(TsscGame tsscGame) {
		// TODO Auto-generated method stub
		return null;
	}
}
