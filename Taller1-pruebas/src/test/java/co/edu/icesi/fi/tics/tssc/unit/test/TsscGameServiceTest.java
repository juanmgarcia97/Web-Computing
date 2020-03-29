package co.edu.icesi.fi.tics.tssc.unit.test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.model.TsscTopic;
import co.edu.icesi.fi.tics.tssc.repository.TsscGameRepository;
import co.edu.icesi.fi.tics.tssc.repository.TsscTopicRepository;
import co.edu.icesi.fi.tics.tssc.service.TsscGameServiceImp;
import co.edu.icesi.fi.tics.tssc.service.TsscTopicServiceImp;

@Nested
@RunWith(MockitoJUnitRunner.class)
class TsscGameServiceTest {

	@Mock
	TsscGameRepository gameRepository;
	
	@Mock
	TsscTopicRepository topicRepository;

	@InjectMocks
	TsscGameServiceImp gameService;
	
	@InjectMocks
	TsscTopicServiceImp topicService;

	TsscGame game;
	TsscTopic topic;
	
	@BeforeEach
	void initializeMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testSaveGameException() {
		game = null;
		topic = null;
		assertThrows(Exception.class, () -> {
			gameService.saveGame(game, topic);
		});
	}

	@Test
	void testSavGgameGroupsException() {
		game = new TsscGame();
		game.setNGroups(0);
		game.setNSprints(1);
		topic = null;
		assertThrows(Exception.class, () -> {
			gameService.saveGame(game, topic);
		});
	}

	@Test
	void testSaveGameSprintsException() {
		game = new TsscGame();
		game.setNGroups(2);
		game.setNSprints(0);
		topic = null;
		assertThrows(Exception.class, () -> {
			gameService.saveGame(game, topic);
		});
	}
	
	@Test
	void testSaveGameTopicException() {
		topic = null;
		game = new TsscGame();
		game.setNGroups(1);
		game.setNSprints(1);
		game.setTsscTopic(topic);
		assertThrows(Exception.class, () ->{
			gameService.saveGame(game, topic);
		});
	}

	@Test
	void testSaveGameNoException() {
		game = new TsscGame();
		game.setNGroups(1);
		game.setNSprints(1);
		topic = new TsscTopic();
		Optional<TsscTopic> findedTopic = Optional.of(topic);
		when(topicRepository.findById(topic.getId())).thenReturn(findedTopic);
		try {
			when(gameService.saveGame(game, topic)).thenReturn(game);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail();
		}
		assertTrue(game.getNGroups() > 0);
		assertTrue(game.getNSprints() > 0);
	}

	@Nested
	class EditGameTest {

		@Test
		void testEditGameException() {
			game = null;
			topic = null;
			assertThrows(Exception.class, () -> {
				gameService.editGame(game, topic);
			});
		}

		@Test
		void testEditGameSprintsException() throws Exception {
			game = new TsscGame();
			game.setNSprints(0);
			game.setNGroups(1);
			topic = null;
			assertThrows(Exception.class, () -> {
				gameService.editGame(game, topic);
			});
		}

		@Test
		void testEditGameGroupsException() {
			game = new TsscGame();
			game.setNSprints(1);
			game.setNGroups(0);
			topic = null;
			assertThrows(Exception.class, () -> {
				gameService.editGame(game, topic);
			});
		}
		
		@Test
		void testEditGameTopicException() {
			game = new TsscGame();
			topic = null;
			game.setNGroups(1);
			game.setNSprints(1);
			assertThrows(Exception.class, () ->{
				gameService.saveGame(game, topic);
			});
		}

		@Test
		void testEditGameNoException() {
			game = new TsscGame();
			game.setNSprints(1);
			game.setNGroups(1);
			topic = new TsscTopic();
			Optional<TsscTopic> findedTopic = Optional.of(topic);
			Optional<TsscGame> finded = Optional.of(game);
			
			try {
				assertNotNull(game);
				when(topicRepository.findById(topic.getId())).thenReturn(findedTopic);
				when(gameRepository.findById(game.getId())).thenReturn(finded);
				when(gameService.editGame(game, topic)).thenReturn(game);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				assertTrue(game.getNGroups() > 0);
				assertTrue(game.getNSprints() > 0);
			}
		}
	}

}
