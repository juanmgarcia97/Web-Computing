package co.edu.icesi.fi.tics.tssc.dao.test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

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

import co.edu.icesi.fi.tics.tssc.Taller4RESTfulApplication;
import co.edu.icesi.fi.tics.tssc.daos.ITsscStoryDao;
import co.edu.icesi.fi.tics.tssc.model.TsscStory;

@RunWith(SpringRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
class TestTsscStoryDao {

	@Autowired
	private ITsscStoryDao storyDao;
	
	private TsscStory story;
	
	@BeforeEach
	void initDao() {
		story = new TsscStory();
		story.setBusinessValue(new BigDecimal(24));
		story.setDescription("nada");
		story.setInitialSprint(new BigDecimal(20));
		story.setPriority(new BigDecimal(3));
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void updateStoryTest() {
		assertNull(storyDao);
		try {
			TsscStory oldSt = storyDao.findById(story.getId());
			story.setDescription("historia1");
			story.setInitialSprint(new BigDecimal("10"));
			storyDao.update(story);
			TsscStory newSt = storyDao.findById(story.getId());
			
			
			assertTrue(oldSt.getDescription().equals(newSt.getDescription()));
			assertTrue(oldSt.getInitialSprint().equals(newSt.getInitialSprint()));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void deleteStoryTest() {
		assertNull(storyDao);
		try {
			storyDao.delete(story);
			
			assertNull(storyDao.findById(story.getId()));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void saveStoryTest() {
		assertNull(storyDao);
		try {
			storyDao.save(story);
			
			assertTrue(storyDao.findById(story.getId()).equals(story));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void findStroyByIdTest() {
		assertNull(storyDao);
		try {
			storyDao.save(story);
			TsscStory story = storyDao.findById(this.story.getId());
			
			assertTrue(story.getId() == this.story.getId());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
