package co.edu.icesi.fi.tics.tssc.delegate.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.fi.tics.tssc.delegate.TsscGameDelegateImp;
import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.model.TsscStory;
import co.edu.icesi.fi.tics.tssc.model.TsscTopic;

@RunWith(MockitoJUnitRunner.class)
class GameDelegateTest {
	
	@Mock
	RestTemplate rest;
	
	@InjectMocks
	TsscGameDelegateImp delegate;
	
	TsscGame game;
	TsscGame game2;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
		game = new TsscGame();
		game.setNGroups(10);
		game.setNSprints(10);
		game.setName("Game1");
	}
	
	public void setUp2() {
		//setUp();
		
		Mockito.when(rest.postForEntity("http://localhost:8080/api/games/", game, TsscGame.class))
		.thenReturn(new ResponseEntity<>(game,HttpStatus.OK));
		delegate.saveGame(game);
	}
	public void setUp3() {
		
		game = new TsscGame();
		game.setNGroups(10);
		game.setNSprints(10);
		game.setName("Game1");
		
		game2= new TsscGame();
		game2.setNGroups(10);
		game2.setNSprints(10);
		game2.setName("Game2");
		
		Mockito.when(rest.postForEntity("http://localhost:8080/api/games/", game, TsscGame.class))
		.thenReturn(new ResponseEntity<>(game,HttpStatus.OK));
		delegate.saveGame(game);
		
		Mockito.when(rest.postForEntity("http://localhost:8080/api/games/", game2, TsscGame.class))
		.thenReturn(new ResponseEntity<>(game2,HttpStatus.OK));
		delegate.saveGame(game2);
		
	}
	public void setUp4() {
		
		game = new TsscGame();
		game.setNGroups(10);
		game.setNSprints(10);
		game.setName("Game1");
		
		game2= new TsscGame();
		game2.setNGroups(10);
		game2.setNSprints(10);
		game2.setName("Game2");
		
		TsscTopic topic = new TsscTopic();
		topic.setDefaultGroups(10);
		topic.setDefaultSprints(10);
		topic.setName("Topic1");
		game.setTsscTopic(topic);
		
		TsscTopic topic2 = new TsscTopic();
		topic2.setDefaultGroups(10);
		topic2.setDefaultSprints(10);
		topic2.setName("Topic2");
		game2.setTsscTopic(topic2);
		
		TsscStory story = new TsscStory();
		story = new TsscStory();
		story.setBusinessValue(new BigDecimal(10));
		story.setInitialSprint(new BigDecimal(10));
		story.setPriority(new BigDecimal(10));
		story.setDescription("Story1");
		List<TsscStory> list =new ArrayList<TsscStory>();
		list.add(story);
		game.setTsscStories(list);
		
		
		Mockito.when(rest.postForEntity("http://localhost:8080/api/games/", game, TsscGame.class))
		.thenReturn(new ResponseEntity<>(game,HttpStatus.OK));
		delegate.saveGame(game);
		
		Mockito.when(rest.postForEntity("http://localhost:8080/api/games/", game2, TsscGame.class))
		.thenReturn(new ResponseEntity<>(game2,HttpStatus.OK));
		delegate.saveGame(game2);
		
	}

	@Test
	public void saveGameTest() {
		
		Mockito.when(rest.postForEntity("http://localhost:8080/api/games/", game, TsscGame.class))
		.thenReturn(new ResponseEntity<>(game,HttpStatus.OK));
		
		TsscGame saved = delegate.saveGame(game);
		assertEquals(saved.getName(), "Game1");
	}
	
	@Test
	public void findByIdTest() {
		setUp2();
		Mockito.when(rest.getForObject("http://localhost:8080/api/games/0", TsscGame.class))
		.thenReturn(new ResponseEntity<TsscGame>(game,HttpStatus.OK).getBody());
		
		TsscGame found = delegate.findById(0);
		assertEquals(found.getName(), "Game1");
	}
	@Test
	public void editGameTest() {
		
		setUp2();
		
		game.setName("Game2");
		
		Mockito.when(rest.patchForObject("http://localhost:8080/api/games/", game, TsscGame.class))
		.thenReturn(new ResponseEntity<>(game,HttpStatus.OK).getBody());		
				
		delegate.editGame(game);		

		Mockito.when(rest.getForObject("http://localhost:8080/api/games/0",TsscGame.class))
		.thenReturn(new ResponseEntity<TsscGame>(game, HttpStatus.OK).getBody());		
		
		TsscGame edited = delegate.findById(0);
		
		assertEquals(edited.getName(), "Game2");
		
	}
	@Test
	public void deleteTest() {
		
		setUp2();		
		 delegate = mock(TsscGameDelegateImp.class);
		 
		Mockito.doNothing().when(delegate).delete(game);		
		
		Mockito.when(rest.getForObject("http://localhost:8080/api/games/0",TsscGame.class))
		.thenReturn(new ResponseEntity<TsscGame>(game, HttpStatus.OK).getBody());		
		
		TsscGame saved = delegate.findById(0);
		assertNull(saved);
	}
	

	@Test
	public void findAllTest() {
		
		setUp3();
		
		TsscGame[] games = {game,game2 };
		
		Mockito.when(rest.getForObject("http://localhost:8080/api/games/",TsscGame[].class))
		.thenReturn(new ResponseEntity<TsscGame[]>(games,HttpStatus.OK).getBody());		
		
		Iterable <TsscGame> juegos = delegate.findAll();	
		String names ="";
		for (TsscGame t: juegos) {
			names+= t.getName()+" ";
			
		}	
		//System.out.println(names);
		assertTrue(names.equals("Game1 Game2 "));
		
		
		
	}
	
	@Test
	public void getTopicsTest() {
		
		setUp4();
		
		TsscTopic[] games = {game.getTsscTopic(),game2.getTsscTopic() };
		
		Mockito.when(rest.getForObject("http://localhost:8080/api/games/topics/",TsscTopic[].class))
		.thenReturn(new ResponseEntity<TsscTopic[]>(games,HttpStatus.OK).getBody());		
		
		Iterable <TsscTopic> juegos = delegate.getTopics();	
		String names ="";
		for (TsscTopic t: juegos) {
			names+= t.getName()+" ";
		}	
		assertTrue(names.equals("Topic1 Topic2 "));
		
		
		
	}
	
	@Test
	public void getStoriesTest() {
		
		setUp4();
		
		TsscStory[] games = {game.getTsscStories().get(0) };
		
		Mockito.when(rest.getForObject("http://localhost:8080/api/games/stories/",TsscStory[].class))
		.thenReturn(new ResponseEntity<TsscStory[]>(games,HttpStatus.OK).getBody());		
		
		Iterable <TsscStory> juegos = delegate.getStories();	
		String names ="";
		for (TsscStory t: juegos) {
			names+= t.getDescription();
		}	
		assertTrue(names.equals("Story1"));
		
		
		
	}

}
