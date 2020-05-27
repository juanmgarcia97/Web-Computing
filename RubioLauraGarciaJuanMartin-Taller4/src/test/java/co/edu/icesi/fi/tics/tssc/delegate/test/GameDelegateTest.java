package co.edu.icesi.fi.tics.tssc.delegate.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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

@RunWith(MockitoJUnitRunner.class)
class GameDelegateTest {
	
	@Mock
	RestTemplate rest;
	
	@InjectMocks
	TsscGameDelegateImp delegate;
	
	TsscGame game;
	TsscGame game2;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		game = new TsscGame();
		game.setNGroups(10);
		game.setNSprints(10);
		game.setName("Game1");
	}
	
	public void setUp2() {
		Mockito.when(rest.postForEntity("http://localhost:8080/api/games/", game, TsscGame.class))
		.thenReturn(new ResponseEntity<>(game,HttpStatus.OK));
		delegate.saveGame(game);
	}
	public void setUp3() {
		
		
		Mockito.when(rest.postForEntity("http://localhost:8080/api/games/", game, TsscGame.class))
		.thenReturn(new ResponseEntity<>(game,HttpStatus.OK));
		delegate.saveGame(game);
		
		game2= new TsscGame();
		game.setNGroups(10);
		game.setNSprints(10);
		game.setName("Game2");
		
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
		//REVISAR
		setUp2();		
		 delegate = mock(TsscGameDelegateImp.class);
		 
		Mockito.doNothing().when(delegate).delete(game);	
		
		
		verify(delegate,times(1)).delete(game);
		
		Mockito.when(rest.getForObject("http://localhost:8080/api/games/0",TsscGame.class))
		.thenReturn(new ResponseEntity<TsscGame>(game, HttpStatus.OK).getBody());		
		
		TsscGame saved = delegate.findById(0);
		assertNull(saved);
	}
	
	@Test
	public void findAllTest() {
		//PENDIENTE
		
		setUp3();
		
		TsscGame[] games = {game , game2};
		
		Mockito.when(rest.getForObject("http://localhost:8080/api/games/",TsscGame[].class))
		.thenReturn(new ResponseEntity<TsscGame[]>(games,HttpStatus.OK).getBody());		
		
		Iterable <TsscGame> temas = delegate.findAll();	
		System.out.println(temas);
		String names ="";
		
		for (TsscGame t: temas) {
			names+= t.getName()+" ";
			
		}	
		System.out.println(names);
		assertTrue(names.equals("Game1 Game2 "));
		
		
		
	}
	

}
