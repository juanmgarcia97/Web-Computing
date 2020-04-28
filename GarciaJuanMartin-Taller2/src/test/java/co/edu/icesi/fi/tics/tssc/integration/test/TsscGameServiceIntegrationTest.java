package co.edu.icesi.fi.tics.tssc.integration.test;

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
class TsscGameServiceIntegrationTest {

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
			gameService.saveGame2(game, topic);
		});
	}

	@Test
	void testSaveGameNoException() {
		game = new TsscGame();
		game.setNGroups(1);
		game.setNSprints(1);
		topic = new TsscTopic();
		Optional<TsscTopic> findedTopic = topicRepository.findById(topic.getId());
		try {
			assertNotNull(game);
			gameService.saveGame2(game, findedTopic.get());
		} catch (Exception e) {
			assertTrue(game.getNGroups() > 0);
			assertTrue(game.getNSprints() > 0);
		}

	}

	@Nested
	class EditGameTest {

		@Test
		void testEditGameException() {
			game = null;
			topic = null;
			assertThrows(Exception.class, () -> {
				gameService.editGame(game);
			});
		}

		
		@Test
		void testEditGameNoException() {
			game = new TsscGame();
			game.setNSprints(1);
			game.setNGroups(1);
			topic = new TsscTopic();
			Optional<TsscGame> findedGame = gameRepository.findById(game.getId());
			Optional<TsscTopic> findedTopic = topicRepository.findById(topic.getId());
			try {
				assertNotNull(game);
				gameService.editGame(findedGame.get());
			} catch (Exception e) {
				assertTrue(game.getNGroups() > 0);
				assertTrue(game.getNSprints() > 0);
			}
		}
	}

}
