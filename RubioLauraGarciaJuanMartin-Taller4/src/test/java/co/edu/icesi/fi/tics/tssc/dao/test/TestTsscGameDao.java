package co.edu.icesi.fi.tics.tssc.dao.test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import co.edu.icesi.fi.tics.tssc.Taller4RESTfulApplication;
import co.edu.icesi.fi.tics.tssc.daos.ITsscGameDao;
import co.edu.icesi.fi.tics.tssc.daos.ITsscStoryDao;
import co.edu.icesi.fi.tics.tssc.daos.ITsscTimecontrolDao;
import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.model.TsscStory;
import co.edu.icesi.fi.tics.tssc.model.TsscTimecontrol;
import co.edu.icesi.fi.tics.tssc.model.TsscTopic;

@RunWith(SpringRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
class TestTsscGameDao {

	@Autowired
	private ITsscGameDao gameDao;
	@Autowired
	private ITsscStoryDao storyDao;
	@Autowired
	private ITsscTimecontrolDao timecontrolDao;
	
	private TsscGame game;
	private TsscGame gameW11Stories;
	private TsscTopic topic;
	private TsscStory story;
	private TsscTimecontrol time;
	
	@BeforeEach
	void initDao() {
		game = new TsscGame();
		game.setAdminPassword("123");
		game.setGuestPassword("123");
		game.setName("game1");
		game.setNGroups(4);
		game.setNSprints(4);
		game.setScheduledDate(LocalDate.of(2020, 4, 27));
		game.setScheduledTime(LocalTime.of(11, 11));
	}
	
	void initGameW11Stories() {
		gameW11Stories = new TsscGame();
		gameW11Stories.setAdminPassword("123");
		gameW11Stories.setGuestPassword("123");
		gameW11Stories.setName("game2");
		gameW11Stories.setNGroups(2);
		gameW11Stories.setNSprints(2);
		gameW11Stories.setScheduledDate(LocalDate.of(2020, 5, 10));
		gameW11Stories.setScheduledTime(LocalTime.of(10, 43));
	}
	
	void initTimecocntrol() {
		time = new TsscTimecontrol();
		time.setAutostart("true");
		time.setName("time1");
	}
	
	void initStory() {
		story = new TsscStory();
		story.setBusinessValue(new BigDecimal(4));
		story.setDescription("desc");
		story.setInitialSprint(new BigDecimal(4));
		story.setPriority(new BigDecimal(4));
	}
	
	void fillGamesWStories() {
		List<TsscStory> tsscStories = new ArrayList<TsscStory>();
		for (int i = 0; i <= 11; i++) {
			tsscStories.add(this.story);
		}
		this.gameW11Stories.setTsscStories(tsscStories);
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void updateGameTest() {
		assertNull(gameDao);
		try {
			game.setName("jueguito2");
			game.setNGroups(2);
			gameDao.update(game);
			
			assertTrue(gameDao.findById(game.getId()).equals(game));
			assertTrue(gameDao.findByName("jueguito2").equals(game));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void deleteGameTest() {
		assertNull(gameDao);
		try {
			gameDao.delete(game);
			
			assertNull(gameDao.findById(game.getId()));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void saveGameTest() {
		assertNull(gameDao);
		try {
			gameDao.save(game);
			
			assertTrue(gameDao.findById(game.getId()).equals(game));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void findGameByNameTest() {
		assertNull(gameDao);
		try {
			gameDao.save(this.game);
			List<TsscGame> game = gameDao.findByName("game1");
			
			assertTrue(game.get(0).getName().equals(this.game.getName()));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void findGameByIdTest() {
		assertNull(gameDao);
		try {
			gameDao.save(this.game);
			TsscGame game = gameDao.findById(this.game.getId());
			
			assertTrue(game.getId() == this.game.getId());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void findGamesLess10StoriesTest() {
		assertNull(gameDao);
		try {
			List<TsscStory> list = new ArrayList<>();
			list.add(this.story);
			this.game.setTsscStories(list);
			gameDao.save(this.game);
			gameDao.save(this.gameW11Stories);
			List<Object[]> games = gameDao.findGamesLess10StoriesOrNoTimecontrols(this.game.getScheduledDate());
			
			for (Object[] i : games) {
				assertNotNull(i);
				assertEquals(this.game, i);
				assertNotEquals(this.gameW11Stories, i);
			}
			assertTrue(game.getId() == this.game.getId());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
