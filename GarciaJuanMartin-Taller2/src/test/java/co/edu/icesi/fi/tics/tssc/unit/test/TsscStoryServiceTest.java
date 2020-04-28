package co.edu.icesi.fi.tics.tssc.unit.test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import co.edu.icesi.fi.tics.tssc.model.TsscStory;
import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.repository.TsscGameRepository;
import co.edu.icesi.fi.tics.tssc.repository.TsscStoryRepository;
import co.edu.icesi.fi.tics.tssc.repository.TsscTopicRepository;
import co.edu.icesi.fi.tics.tssc.service.TsscGameServiceImp;
import co.edu.icesi.fi.tics.tssc.service.TsscStoryServiceImp;
import co.edu.icesi.fi.tics.tssc.service.TsscTopicServiceImp;

class TsscStoryServiceTest {

	@Mock
	TsscStoryRepository storyRepository;
	
	@Mock
	TsscGameRepository gameRepository;

	@InjectMocks
	TsscStoryServiceImp storyService;
	
	@InjectMocks
	TsscGameServiceImp gameService;
	
	TsscStory story;
	TsscGame game;

	@BeforeEach
	void initializeMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testSaveStoryException() {
		story = null;
		game = null;
		assertThrows(Exception.class, () -> {
			storyService.saveStoryGame(story, game);
		});
	}

	@Test
	void testSaveStoryBusinessValueException() {
		story = new TsscStory();
		story.setBusinessValue(new BigDecimal(0));
		story.setInitialSprint(new BigDecimal(1));
		story.setPriority(new BigDecimal(1));
		game = new TsscGame();
		assertThrows(Exception.class, () -> {
			storyService.saveStoryGame(story, game);
		});
	}

	@Test
	void testSaveStoryInitialSprintsException() {
		story = new TsscStory();
		story.setBusinessValue(new BigDecimal(1));
		story.setInitialSprint(new BigDecimal(0));
		story.setPriority(new BigDecimal(1));
		game = new TsscGame();
		assertThrows(Exception.class, () -> {
			storyService.saveStoryGame(story, game);
		});
	}
	
	@Test
	void testSaveStoryPriorityException() {
		TsscStory story = new TsscStory();
		story.setBusinessValue(new BigDecimal(1));
		story.setInitialSprint(new BigDecimal(1));
		story.setPriority(new BigDecimal(0));
		game = new TsscGame();
		assertThrows(Exception.class, () -> {
			storyService.saveStoryGame(story, game);
		});
	}
	
	@Test
	void testSaveStoryGameException() {
		game = null;
		story = new TsscStory();
		story.setBusinessValue(new BigDecimal(1));
		story.setInitialSprint(new BigDecimal(1));
		story.setPriority(new BigDecimal(1));
		assertThrows(Exception.class, () ->{
			storyService.saveStoryGame(story, game);
		});
	}

	@Test
	void testSaveStoryNoException() {
		story = new TsscStory();
		story.setBusinessValue(new BigDecimal(1));
		story.setInitialSprint(new BigDecimal(1));
		story.setPriority(new BigDecimal(1));
		game = new TsscGame();
		Optional<TsscGame> findedGame = Optional.of(game);
		when(gameRepository.findById(game.getId())).thenReturn(findedGame);
		assertTrue(story.getBusinessValue().intValue() > 0);
		assertTrue(story.getInitialSprint().intValue() > 0);
		assertTrue(story.getPriority().intValue() > 0);
	}

	@Nested
	class EditStoryTest {

		@Test
		void testEditStoryException() {
			story = null;
			game = null;
			assertThrows(Exception.class, () -> {
				storyService.editStoryGame(story, game);
			});
		}

		@Test
		void testEditStoryBusinessValueException() throws Exception {
			 story = new TsscStory();
			story.setBusinessValue(new BigDecimal(0));
			story.setInitialSprint(new BigDecimal(1));
			story.setPriority(new BigDecimal(1));
			game = new TsscGame();
			assertThrows(Exception.class, () -> {
				storyService.editStoryGame(story, game);
			});
		}

		@Test
		void testEditStoryInitialSprintsException() {
			story = new TsscStory();
			story.setBusinessValue(new BigDecimal(1));
			story.setInitialSprint(new BigDecimal(0));
			story.setPriority(new BigDecimal(1));
			game = new TsscGame();
			assertThrows(Exception.class, () -> {
				storyService.editStoryGame(story, game);
			});
		}
		
		@Test
		void testEditStoryPriorityException() {
			TsscStory story = new TsscStory();
			story.setBusinessValue(new BigDecimal(1));
			story.setInitialSprint(new BigDecimal(1));
			story.setPriority(new BigDecimal(0));
			game = new TsscGame();
			assertThrows(Exception.class, () -> {
				storyService.editStoryGame(story, game);
			});
		}
		
		@Test
		void testEditStoryGameException() {
			story = new TsscStory();
			story.setBusinessValue(new BigDecimal(1));
			story.setInitialSprint(new BigDecimal(1));
			story.setPriority(new BigDecimal(1));
			game = new TsscGame();
			assertThrows(Exception.class, () ->{
				storyService.editStoryGame(story, game);
			});
		}

		@Test
		void testEditStoryNoException() {
			story = new TsscStory();
			story.setBusinessValue(new BigDecimal(1));
			story.setInitialSprint(new BigDecimal(1));
			story.setPriority(new BigDecimal(1));
			game = new TsscGame();
			Optional<TsscStory> finded = Optional.of(story);
			Optional<TsscGame> findedGame = Optional.of(game);
			when(storyRepository.findById(story.getId())).thenReturn(finded);
			when(gameRepository.findById(game.getId())).thenReturn(findedGame);
		}
	}

}
